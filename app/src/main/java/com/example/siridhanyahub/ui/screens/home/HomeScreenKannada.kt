package com.example.siridhanyahub.ui.screens.home

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val ChipBg        = Color(0xFFEAF2E8)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

private val NavMossGreen  = Color(0xFF2D5A27)
private val NavChipBg     = Color(0xFFEAF2E8)
private val NavTextIdle   = Color(0xFF6B7C6A)

// ── Dynamic millet market pulse data (Kannada) ─────────────────────────────
private val milletPulseKannada = mapOf(
    "ರಾಗಿ"   to Triple("↑ 4.2%", "ಬೆಂಗಳೂರು", "+4.2%"),
    "ನವಣೆ" to Triple("↑ 1.8%", "ಮೈಸೂರು",    "+1.8%"),
    "ಸಜ್ಜೆ"  to Triple("↑ 3.1%", "ದಾವಣಗೆರೆ","+3.1%"),
    "ಬರಗು" to Triple("↑ 0.9%", "ಬೆಂಗಳೂರು", "+0.9%")
)

@Composable
fun HomeScreenKannada(navController: NavController) {

    val milletList = listOf("ರಾಗಿ", "ನವಣೆ", "ಸಜ್ಜೆ", "ಬರಗು")
    var selectedMillet by remember { mutableStateOf("ರಾಗಿ") }
    val pulse = milletPulseKannada[selectedMillet] ?: milletPulseKannada["ರಾಗಿ"]!!

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(IvoryBg).statusBarsPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        // ── Header ──────────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(48.dp).clip(CircleShape).background(MossGreen).clickable { navController.navigate("profile_kn") { launchSingleTop = true } }, Alignment.Center) {
                    Text("👨", fontSize = 22.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("ನಮಸ್ಕಾರ, ಕಾರ್ತಿಕ್", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MossGreen)
                    Text("ಸಿರಿ-ಧಾನ್ಯ ಹಬ್‌ಗೆ ಮರಳಿ ಸ್ವಾಗತ", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                // Notification bell
                Box(Modifier.size(40.dp).clip(CircleShape).background(ChipBg).clickable {}, Alignment.Center) {
                    Icon(Icons.Default.Notifications, "ಅಧಿಸೂಚನೆಗಳು", tint = MossGreen, modifier = Modifier.size(20.dp))
                }
            }
        }

        // ── Search Bar ────────────────────────────────────────────────────
        item {
            Row(
                Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(18.dp)).background(CardWhite)
                    .clickable { navController.navigate("search_kn") }.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, "ಹುಡುಕಿ", tint = TextSecondary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(10.dp))
                Text("ಬೆಲೆಗಳು, ಪಾಕವಿಧಾನಗಳು, ಆರೋಗ್ಯ ಸಲಹೆಗಳನ್ನು ಹುಡುಕಿ...", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
        }

        // ── Market Pulse Card (dynamic + clickable) ────────────────────────
        item {
            Card(
                modifier = Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(22.dp)).clickable { navController.navigate("mandi_kn") },
                shape  = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = ChipBg)
            ) {
                Row(Modifier.fillMaxWidth().padding(18.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Column {
                        Text("ಇಂದಿನ ಮಾರುಕಟ್ಟೆ ಸ್ಥಿತಿ", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = SoftGreen, letterSpacing = 0.6.sp)
                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(selectedMillet, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Spacer(Modifier.width(8.dp))
                            Text(pulse.first, style = MaterialTheme.typography.bodyMedium, color = MossGreen, fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.width(10.dp))
                            Text(pulse.second, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                        }
                        Spacer(Modifier.height(4.dp))
                        Text("ಪೂರ್ಣ ಮಾರುಕಟ್ಟೆ ಡೇಟಾವನ್ನು ವೀಕ್ಷಿಸಲು ಟ್ಯಾಪ್ ಮಾಡಿ →", style = MaterialTheme.typography.labelSmall, color = SoftGreen)
                    }
                    Box(Modifier.size(52.dp).clip(CircleShape).background(CardWhite.copy(alpha = 0.8f)), Alignment.Center) {
                        Icon(Icons.Default.ShowChart, null, tint = MossGreen, modifier = Modifier.size(26.dp))
                    }
                }
            }
        }

        // ── Feature Grid ──────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeFeatureCardKannada("ಮಂಡಿ ವಾಚ್", "ಲೈವ್ ಬೆಲೆಗಳು",     Icons.Default.ShowChart,      Modifier.weight(1f)) { navController.navigate("mandi_kn") }
                HomeFeatureCardKannada("ಪಾಕವಿಧಾನ ಪ್ರಯೋಗಾಲಯ",  "ಆರೋಗ್ಯಕರ ಅಡುಗೆ", Icons.Default.LocalDining,    Modifier.weight(1f)) { navController.navigate("recipe_kn") }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeFeatureCardKannada("ಆರೋಗ್ಯ",     "ಪೌಷ್ಟಿಕಾಂಶ ಮಾಹಿತಿ",  Icons.Default.Favorite,       Modifier.weight(1f)) { navController.navigate("health_kn") }
                HomeFeatureCardKannada("ನೇರ ಖರೀದಿ", "ರೈತರಿಂದ",      Icons.Default.ShoppingBasket, Modifier.weight(1f)) { navController.navigate("direct_buy_kn") }
            }
        }

        // ── Popular Millets ────────────────────────────────────────────────
        item {
            Text("ಜನಪ್ರಿಯ ಸಿರಿಧಾನ್ಯಗಳು", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
            Spacer(Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(milletList) { millet ->
                    val selected = millet == selectedMillet
                    Box(
                        Modifier.clip(RoundedCornerShape(18.dp)).background(if (selected) MossGreen else ChipBg)
                            .clickable { selectedMillet = millet }.padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(millet, style = MaterialTheme.typography.labelMedium,
                            color = if (selected) CardWhite else SoftGreen,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
                    }
                }
            }
        }

        // ── Quick Recommendations ──────────────────────────────────────────
        item {
            Text("ತ್ವರಿತ ಶಿಫಾರಸುಗಳು", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeRecommendationCardKannada("ಸಿರಿಧಾನ್ಯ ಉಪ್ಪಿಟ್ಟು ಸ್ಪೆಷಲ್", "ಕಡಿಮೆ-ಜಿಐ ಉಪಹಾರ · ನವಣೆ")    { navController.navigate("recipe_detail_kn/Navane Upma") }
                HomeRecommendationCardKannada("ಕಬ್ಬಿಣಾಂಶ ವರ್ಧಕ ಮಾರ್ಗದರ್ಶಿ",    "ನವಣೆಯ ಶಕ್ತಿ ಪ್ರಯೋಜನಗಳು")        { navController.navigate("health_kn") }
                HomeRecommendationCardKannada("ಇಂದಿನ ಅತ್ಯುತ್ತಮ ರಾಗಿ ಬೆಲೆ","ದಾವಣಗೆರೆ ಮಾರುಕಟ್ಟೆಯಲ್ಲಿ ₹3,420") { navController.navigate("mandi_kn") }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(16.dp)); BottomNavBarKannada(navController) }
    }
}

@Composable
fun HomeFeatureCardKannada(title: String, subtitle: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier.height(120.dp).shadow(4.dp, RoundedCornerShape(20.dp)).clickable { onClick() },
        shape  = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite)
    ) {
        Column(Modifier.fillMaxSize().padding(16.dp), Arrangement.SpaceBetween) {
            Box(Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(ChipBg), Alignment.Center) {
                Icon(icon, null, tint = MossGreen, modifier = Modifier.size(20.dp))
            }
            Column {
                Text(title,    style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text(subtitle, style = MaterialTheme.typography.bodySmall,  color = TextSecondary)
            }
        }
    }
}

@Composable
fun HomeRecommendationCardKannada(title: String, subtitle: String, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)).clickable { onClick() },
        shape  = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite)
    ) {
        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(ChipBg), Alignment.Center) {
                Text("🌾", fontSize = 24.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title,    style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Spacer(Modifier.height(2.dp))
                Text(subtitle, style = MaterialTheme.typography.bodySmall,  color = TextSecondary)
            }
            Icon(Icons.Default.ChevronRight, null, tint = TextSecondary, modifier = Modifier.size(18.dp))
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
                    val selected = currentRoute == item.route || (currentRoute == null && item.route == "home_kn")
                    EmbeddedNavItemKannada(
                        icon     = item.icon,
                        label    = item.label,
                        selected = selected,
                        onClick  = {
                            if (!selected) {
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
