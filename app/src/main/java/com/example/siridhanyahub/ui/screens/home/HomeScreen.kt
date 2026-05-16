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
import com.example.siridhanyahub.ui.components.BottomNavBar

private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val AccentGreen   = Color(0xFF7FB069)
private val ChipBg        = Color(0xFFEAF2E8)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

// ── Dynamic millet market pulse data ─────────────────────────────────────────
private val milletPulse = mapOf(
    "Ragi"   to Triple("↑ 4.2%", "Bengaluru", "+4.2%"),
    "Navane" to Triple("↑ 1.8%", "Mysuru",    "+1.8%"),
    "Sajje"  to Triple("↑ 3.1%", "Davanagere","+3.1%"),
    "Baragu" to Triple("↑ 0.9%", "Bengaluru", "+0.9%")
)

@Composable
fun HomeScreen(navController: NavController) {

    val milletList = listOf("Ragi", "Navane", "Sajje", "Baragu")
    var selectedMillet by remember { mutableStateOf("Ragi") }
    val pulse = milletPulse[selectedMillet] ?: milletPulse["Ragi"]!!

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(IvoryBg).statusBarsPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        // ── Header ──────────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(48.dp).clip(CircleShape).background(MossGreen).clickable { navController.navigate("profile") { launchSingleTop = true } }, Alignment.Center) {
                    Text("👨", fontSize = 22.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Namaskara, Karthik", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MossGreen)
                    Text("Welcome back to Siri-Dhanya Hub", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                // Notification bell
                Box(Modifier.size(40.dp).clip(CircleShape).background(ChipBg).clickable {}, Alignment.Center) {
                    Icon(Icons.Default.Notifications, "Notifications", tint = MossGreen, modifier = Modifier.size(20.dp))
                }
            }
        }

        // ── Search Bar ────────────────────────────────────────────────────
        item {
            Row(
                Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(18.dp)).background(CardWhite)
                    .clickable { navController.navigate("search") }.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, "Search", tint = TextSecondary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(10.dp))
                Text("Search prices, recipes, health tips...", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
        }

        // ── Market Pulse Card (dynamic + clickable) ────────────────────────
        item {
            Card(
                modifier = Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(22.dp)).clickable { navController.navigate("mandi") },
                shape  = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = ChipBg)
            ) {
                Row(Modifier.fillMaxWidth().padding(18.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Column {
                        Text("TODAY'S MARKET PULSE", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = SoftGreen, letterSpacing = 0.6.sp)
                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(selectedMillet, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Spacer(Modifier.width(8.dp))
                            Text(pulse.first, style = MaterialTheme.typography.bodyMedium, color = MossGreen, fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.width(10.dp))
                            Text(pulse.second, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                        }
                        Spacer(Modifier.height(4.dp))
                        Text("Tap to view full market data →", style = MaterialTheme.typography.labelSmall, color = SoftGreen)
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
                HomeFeatureCard("Mandi Watch", "Live prices",     Icons.Default.ShowChart,      Modifier.weight(1f)) { navController.navigate("mandi") }
                HomeFeatureCard("Recipe Lab",  "Healthy cooking", Icons.Default.LocalDining,    Modifier.weight(1f)) { navController.navigate("recipe") }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeFeatureCard("Health",     "Nutritional info",  Icons.Default.Favorite,       Modifier.weight(1f)) { navController.navigate("health") }
                HomeFeatureCard("Direct Buy", "From farmers",      Icons.Default.ShoppingBasket, Modifier.weight(1f)) { navController.navigate("direct_buy") }
            }
        }

        // ── Popular Millets ────────────────────────────────────────────────
        item {
            Text("Popular Millets", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
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
            Text("Quick Recommendations", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeRecommendationCard("Millet Upma Special", "Low-GI breakfast · Navane")    { navController.navigate("recipe_detail/Navane Upma") }
                HomeRecommendationCard("Iron Boost Guide",    "Navane energy benefits")        { navController.navigate("health") }
                HomeRecommendationCard("Best Ragi Price Today","₹3,420 in Davanagere Market") { navController.navigate("mandi") }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(16.dp)); BottomNavBar(navController) }
    }
}

@Composable
fun HomeFeatureCard(title: String, subtitle: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
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
fun HomeRecommendationCard(title: String, subtitle: String, onClick: () -> Unit = {}) {
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