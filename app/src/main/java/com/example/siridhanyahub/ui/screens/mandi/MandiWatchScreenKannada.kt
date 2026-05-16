package com.example.siridhanyahub.ui.screens.mandi

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.random.Random

// ── Theme Colors ─────────────────────────────────────────────────────────────
private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val ChipBg        = Color(0xFFEAF2E8)
private val SoftGreenCard = Color(0xFFE3EFE0)
private val BarInactive   = Color(0xFFD9DDD4)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)
private val TrendUp       = Color(0xFF2E7D32)
private val TrendDown     = Color(0xFFC62828)
private val LiveRed       = Color(0xFFD32F2F)
private val HotOrange     = Color(0xFFE65100)

private val NavMossGreen  = Color(0xFF2D5A27)
private val NavChipBg     = Color(0xFFEAF2E8)
private val NavTextIdle   = Color(0xFF6B7C6A)

// ── Data key ─────────────────────────────────────────────────────────────────
private data class MarketKeyKannada(val market: String, val millet: String)

// ── Base prices (realistic per-quintal INR) ──────────────────────────────────
private val basePricesKannada = mapOf(
    MarketKeyKannada("ಬೆಂಗಳೂರು", "ರಾಗಿ")     to 3420,
    MarketKeyKannada("ಬೆಂಗಳೂರು", "ನವಣೆ")   to 3120,
    MarketKeyKannada("ಬೆಂಗಳೂರು", "ಸಜ್ಜೆ")    to 2890,
    MarketKeyKannada("ಬೆಂಗಳೂರು", "ಬರಗು")   to 2650,
    MarketKeyKannada("ದಾವಣಗೆರೆ", "ರಾಗಿ")    to 3560,
    MarketKeyKannada("ದಾವಣಗೆರೆ", "ನವಣೆ")  to 3240,
    MarketKeyKannada("ದಾವಣಗೆರೆ", "ಸಜ್ಜೆ")   to 2980,
    MarketKeyKannada("ದಾವಣಗೆರೆ", "ಬರಗು")  to 2730,
    MarketKeyKannada("ಮೈಸೂರು", "ರಾಗಿ")        to 3180,
    MarketKeyKannada("ಮೈಸೂರು", "ನವಣೆ")      to 2950,
    MarketKeyKannada("ಮೈಸೂರು", "ಸಜ್ಜೆ")       to 2720,
    MarketKeyKannada("ಮೈಸೂರು", "ಬರಗು")      to 2480
)

@Composable
fun MandiWatchScreenKannada(navController: NavController) {

    val markets    = listOf("ಬೆಂಗಳೂರು", "ದಾವಣಗೆರೆ", "ಮೈಸೂರು")
    val milletList = listOf("ರಾಗಿ", "ನವಣೆ", "ಸಜ್ಜೆ", "ಬರಗು")

    var selectedMarket by remember { mutableStateOf("ಬೆಂಗಳೂರು") }
    var selectedMillet by remember { mutableStateOf("ರಾಗಿ") }

    // ── Live price state per MarketKeyKannada ────────────────────────────────────
    val livePrices    = remember { mutableStateMapOf<MarketKeyKannada, Int>() }
    val previousPrices = remember { mutableStateMapOf<MarketKeyKannada, Int>() }
    var tickCount     by remember { mutableIntStateOf(0) }
    var lastUpdateMs  by remember { mutableLongStateOf(System.currentTimeMillis()) }

    // Seed initial prices
    LaunchedEffect(Unit) {
        basePricesKannada.forEach { (key, price) ->
            livePrices[key] = price
            previousPrices[key] = price
        }
    }

    // ── Live ticker coroutine ────────────────────────────────────────────
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L + Random.nextLong(2000L))
            basePricesKannada.keys.forEach { key ->
                val current = livePrices[key] ?: basePricesKannada[key]!!
                previousPrices[key] = current
                val base = basePricesKannada[key]!!
                val delta = Random.nextInt(-40, 41).let {
                    if (it in -4..4) if (it >= 0) 5 else -5 else it
                }
                val newPrice = (current + delta).coerceIn(
                    (base * 0.90).toInt(),
                    (base * 1.12).toInt()
                )
                livePrices[key] = newPrice
            }
            tickCount++
            lastUpdateMs = System.currentTimeMillis()
        }
    }

    // ── Current selection helpers ─────────────────────────────────────────
    val currentKey   = MarketKeyKannada(selectedMarket, selectedMillet)
    val currentPrice = livePrices[currentKey] ?: basePricesKannada[currentKey]!!
    val prevPrice    = previousPrices[currentKey] ?: currentPrice
    val priceDiff    = currentPrice - prevPrice
    val basePrice    = basePricesKannada[currentKey]!!
    val totalChange  = currentPrice - basePrice
    val pctChange    = if (basePrice != 0) (totalChange.toFloat() / basePrice * 100f) else 0f
    val isUp         = pctChange >= 0f

    // ── Find highest-price market for current millet ─────────────────────
    val highestMarket = markets.maxByOrNull { m ->
        livePrices[MarketKeyKannada(m, selectedMillet)] ?: 0
    } ?: "ಬೆಂಗಳೂರು"

    // ── Pulsing LIVE dot ─────────────────────────────────────────────────
    val infiniteTransition = rememberInfiniteTransition(label = "live_pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            tween(800, easing = LinearEasing), RepeatMode.Reverse
        ), label = "pulse_alpha"
    )
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            tween(800, easing = LinearEasing), RepeatMode.Reverse
        ), label = "pulse_scale"
    )

    // ── "Updated X seconds ago" ──────────────────────────────────────────
    var secondsAgo by remember { mutableIntStateOf(0) }
    LaunchedEffect(lastUpdateMs) {
        secondsAgo = 0
        while (true) {
            delay(1000L)
            secondsAgo++
        }
    }
    val updatedText = when {
        secondsAgo < 3  -> "ಇದೀಗ ನವೀಕರಿಸಲಾಗಿದೆ"
        secondsAgo < 60 -> "${secondsAgo} ಸೆಕೆಂಡುಗಳ ಹಿಂದೆ ನವೀಕರಿಸಲಾಗಿದೆ"
        else             -> "1 ನಿಮಿಷಕ್ಕೂ ಹಿಂದೆ ನವೀಕರಿಸಲಾಗಿದೆ"
    }

    // ── Build 7-day simulated history for mini bar chart ──────────────────
    val barHeights = remember(selectedMarket, selectedMillet, tickCount) {
        val base = basePricesKannada[MarketKeyKannada(selectedMarket, selectedMillet)]!!
        List(7) { i ->
            val noise = Random.nextInt(-60, 61)
            ((base + noise - (base * 0.90).toInt()).toFloat() /
                    ((base * 1.12).toInt() - (base * 0.90).toInt()).toFloat())
                .coerceIn(0.15f, 1f)
        }
    }

    // ── UI ───────────────────────────────────────────────────────────────
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(IvoryBg)
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }

        // ── Header + LIVE badge ──────────────────────────────────────────
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(ChipBg)
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "ಹಿಂದೆ",
                            tint = MossGreen, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("ಮಾರುಕಟ್ಟೆ ವೀಕ್ಷಣೆ",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text(updatedText,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary)
                    }
                }
                // LIVE badge
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(LiveRed.copy(alpha = 0.12f))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .scale(pulseScale)
                            .alpha(pulseAlpha)
                            .clip(CircleShape)
                            .background(LiveRed)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("ಲೈವ್", style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold, color = LiveRed,
                        letterSpacing = 1.sp)
                }
            }
        }

        // ── Market Selector ──────────────────────────────────────────────
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(18.dp))
                    .clickable {
                        val idx = markets.indexOf(selectedMarket)
                        selectedMarket = markets[(idx + 1) % markets.size]
                    },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("$selectedMarket ಮಾರುಕಟ್ಟೆ",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Text("ಮಾರುಕಟ್ಟೆ ಬದಲಾಯಿಸಲು ಟ್ಯಾಪ್ ಮಾಡಿ",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary)
                    }
                    Icon(Icons.Default.KeyboardArrowDown,
                        contentDescription = null, tint = MossGreen)
                }
            }
        }

        // ── Millet Chips ─────────────────────────────────────────────────
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(milletList) { millet ->
                    val sel = millet == selectedMillet
                    val bgColor by animateColorAsState(
                        if (sel) MossGreen else ChipBg, label = "chip_bg"
                    )
                    val txtColor by animateColorAsState(
                        if (sel) CardWhite else SoftGreen, label = "chip_txt"
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(bgColor)
                            .clickable { selectedMillet = millet }
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(millet,
                            style = MaterialTheme.typography.labelMedium,
                            color = txtColor,
                            fontWeight = if (sel) FontWeight.SemiBold else FontWeight.Normal)
                    }
                }
            }
        }

        // ── Live Price Card ──────────────────────────────────────────────
        item {
            val trendColor by animateColorAsState(
                if (isUp) TrendUp else TrendDown, label = "trend_color"
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(6.dp, RoundedCornerShape(22.dp)),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = SoftGreenCard)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("ಇಂದಿನ ಬೆಲೆ · $selectedMillet",
                            style = MaterialTheme.typography.labelMedium,
                            color = SoftGreen, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(8.dp))
                        // Animated price number
                        AnimatedContent(
                            targetState = currentPrice,
                            transitionSpec = {
                                (slideInVertically { it / 2 } + fadeIn()) togetherWith
                                        (slideOutVertically { -it / 2 } + fadeOut())
                            }, label = "price_anim"
                        ) { price ->
                            Text("₹${"%,d".format(price)}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold, color = TextPrimary)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("/ ಕ್ವಿಂಟಾಲ್‌ಗೆ · $selectedMarket",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary)
                    }
                    // Trend circle
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .background(trendColor.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                if (isUp) Icons.Default.KeyboardArrowUp
                                else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = trendColor,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${if (isUp) "↑" else "↓"} ${
                                    "%.1f".format(pctChange.absoluteValue)
                                }%",
                                style = MaterialTheme.typography.labelSmall,
                                color = trendColor,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // ── All Millets Quick View ───────────────────────────────────────
        item {
            Text("ಎಲ್ಲಾ ಸಿರಿಧಾನ್ಯಗಳು · $selectedMarket",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold, color = TextSecondary,
                letterSpacing = 0.6.sp)
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                milletList.forEach { millet ->
                    val key = MarketKeyKannada(selectedMarket, millet)
                    val price = livePrices[key] ?: basePricesKannada[key]!!
                    val prev  = previousPrices[key] ?: price
                    val diff  = price - prev
                    val up    = diff >= 0
                    val color = if (up) TrendUp else TrendDown
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(3.dp, RoundedCornerShape(16.dp))
                            .clickable { selectedMillet = millet },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (millet == selectedMillet)
                                SoftGreenCard else CardWhite
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(millet,
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary, fontWeight = FontWeight.Medium)
                            Spacer(modifier = Modifier.height(4.dp))
                            AnimatedContent(
                                targetState = price,
                                transitionSpec = {
                                    fadeIn(tween(300)) togetherWith fadeOut(tween(300))
                                }, label = "mini_price"
                            ) { p ->
                                Text("₹${"%,d".format(p)}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold, color = TextPrimary)
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                "${if (up) "↑" else "↓"}${diff.absoluteValue}",
                                style = MaterialTheme.typography.labelSmall,
                                color = color, fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }

        // ── 7-Day Trend Chart ────────────────────────────────────────────
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(22.dp)),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("7 ದಿನಗಳ ಪ್ರವೃತ್ತಿ",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Icon(Icons.Default.ShowChart,
                            contentDescription = null,
                            tint = TextSecondary, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    val labels = listOf("ಸೋ", "ಮಂ", "ಬು", "ಗು", "ಶು", "ಶನಿ", "ಭಾ")
                    val maxBarH = 130.dp
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        barHeights.forEachIndexed { i, frac ->
                            val animH by animateFloatAsState(
                                frac, tween(500), label = "bar_$i"
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .width(34.dp)
                                        .height(maxBarH * animH)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            if (i == 6) Brush.verticalGradient(
                                                listOf(MossGreen, SoftGreen)
                                            ) else Brush.verticalGradient(
                                                listOf(BarInactive, BarInactive)
                                            )
                                        )
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(labels[i],
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary)
                            }
                        }
                    }
                }
            }
        }

        // ── Info Cards (Highest Today + Hot Market) ──────────────────────
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Highest Today
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(4.dp, RoundedCornerShape(18.dp)),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🏆", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("ಇಂದಿನ ಗರಿಷ್ಠ",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(highestMarket,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        val hPrice = livePrices[MarketKeyKannada(highestMarket, selectedMillet)]
                            ?: basePricesKannada[MarketKeyKannada(highestMarket, selectedMillet)]!!
                        Text("₹${"%,d".format(hPrice)}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MossGreen, fontWeight = FontWeight.Bold)
                    }
                }
                // Hot Market
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(4.dp, RoundedCornerShape(18.dp)),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🔥", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("ಹಾಟ್ ಮಾರುಕಟ್ಟೆ",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("ದಾವಣಗೆರೆ",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold, color = HotOrange)
                        Text("ಹೆಚ್ಚಿನ ಏರಿಳಿತ",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary)
                    }
                }
            }
        }

        // ── Recent Live Updates ──────────────────────────────────────────
        item {
            Text("ಇತ್ತೀಚಿನ ನವೀಕರಣಗಳು",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold, color = TextSecondary,
                letterSpacing = 0.6.sp)
        }
        item {
            val updates = remember(tickCount) {
                val msgs = mutableListOf<Triple<String, String, String>>()
                basePricesKannada.keys.shuffled().take(3).forEach { key ->
                    val p = livePrices[key] ?: basePricesKannada[key]!!
                    val pp = previousPrices[key] ?: p
                    val d = p - pp
                    if (d != 0) {
                        val dir = if (d > 0) "ಏರಿಕೆ" else "ಇಳಿಕೆ"
                        msgs.add(
                            Triple(
                                "${key.market}ಯಲ್ಲಿ ${key.millet} ಬೆಲೆಗಳು $dir",
                                "₹${"%,d".format(p)} (${if (d > 0) "+" else ""}$d)",
                                if (d > 0) "↑" else "↓"
                            )
                        )
                    }
                }
                if (msgs.isEmpty()) {
                    msgs.add(Triple("ಮಾರುಕಟ್ಟೆ ಸ್ಥಿರವಾಗಿದೆ", "ಯಾವುದೇ ಪ್ರಮುಖ ಬದಲಾವಣೆಗಳಿಲ್ಲ", "•"))
                }
                msgs
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(18.dp)),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    updates.forEachIndexed { idx, (title, subtitle, arrow) ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val dotColor = when (arrow) {
                                "↑"  -> TrendUp
                                "↓"  -> TrendDown
                                else -> TextSecondary
                            }
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(dotColor)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium, color = TextPrimary)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary)
                            }
                        }
                        if (idx < updates.lastIndex) {
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        // ── Bottom Nav ───────────────────────────────────────────────────
        item {
            Spacer(modifier = Modifier.height(8.dp))
            BottomNavBarKannada(navController)
        }
    }
}

private data class NavItem(val label: String, val icon: ImageVector, val route: String)

private val navItemsKannada = listOf(
    NavItem("ಮುಖಪುಟ",    Icons.Default.Home,     "home_kn"),
    NavItem("ಹುಡುಕು",  Icons.Default.Search,   "search_kn"),
    NavItem("ಉಳಿಸಿದವು",   Icons.Default.Bookmark, "saved_kn"),
    NavItem("ಪ್ರೊಫೈಲ್", Icons.Default.Person,   "profile_kn")
)

@Composable
fun BottomNavBarKannada(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFFFFFFF))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItemsKannada.forEach { item ->
                    val selected = currentRoute == item.route || (currentRoute == null && item.route == "mandi_kn") || (currentRoute == "mandi_kn" && item.route == "home_kn") == false
                    // Handle actual selection logic accurately for embedded mock nav
                    val isActuallySelected = currentRoute == item.route || (currentRoute == "mandi_kn" && item.label == "ಮುಖಪುಟ") == false // wait, if route is mandi_kn, nothing is selected unless specified. But Mandi is not in bottom bar. Bottom bar has home, search, saved, profile. So none of them is exactly "mandi_kn".
                    // Wait, Mandi Watch is opened from Home. It is not in the bottom bar.
                    // The original BottomNavBar sets selected = currentRoute == item.route.
                    val isSelected = currentRoute == item.route
                    EmbeddedNavItemKannada(
                        icon     = item.icon,
                        label    = item.label,
                        selected = isSelected,
                        onClick  = {
                            if (!isSelected) {
                                navController.navigate(item.route) {
                                    popUpTo("home_kn") { saveState = true }
                                    launchSingleTop = true
                                    restoreState    = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmbeddedNavItemKannada(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(if (selected) NavChipBg else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 7.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(if (selected) NavMossGreen else Color.Transparent)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) NavMossGreen else NavTextIdle,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (selected) NavMossGreen else NavTextIdle,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}
