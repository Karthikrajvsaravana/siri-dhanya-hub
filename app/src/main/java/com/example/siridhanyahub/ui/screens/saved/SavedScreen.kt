package com.example.siridhanyahub.ui.screens.saved

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.siridhanyahub.ui.components.BottomNavBar
import com.example.siridhanyahub.ui.state.SavedItemsManager

private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val ChipBg        = Color(0xFFEAF2E8)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

@Composable
fun SavedScreen(navController: NavHostController) {

    val tabs = listOf("Recipes", "Sellers", "Health", "Market")
    var selectedTab by remember { mutableStateOf("Recipes") }

    val totalCount = SavedItemsManager.savedRecipes.size +
            SavedItemsManager.savedSellers.size +
            SavedItemsManager.savedHealthInsights.size +
            SavedItemsManager.savedMarketItems.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(IvoryBg)
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        // ── Header ──────────────────────────────────────────────────────────
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(38.dp).clip(CircleShape).background(ChipBg)
                        .clickable { navController.popBackStack() },
                    Alignment.Center
                ) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Saved", style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("$totalCount items bookmarked",
                        style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        // ── Tab chips ───────────────────────────────────────────────────────
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(tabs) { tab ->
                    val sel = tab == selectedTab
                    val bg by animateColorAsState(
                        if (sel) MossGreen else ChipBg, label = "tab_bg"
                    )
                    val count = when (tab) {
                        "Recipes" -> SavedItemsManager.savedRecipes.size
                        "Sellers" -> SavedItemsManager.savedSellers.size
                        "Health"  -> SavedItemsManager.savedHealthInsights.size
                        "Market"  -> SavedItemsManager.savedMarketItems.size
                        else      -> 0
                    }
                    Box(
                        Modifier.clip(RoundedCornerShape(18.dp)).background(bg)
                            .clickable { selectedTab = tab }
                            .padding(horizontal = 18.dp, vertical = 10.dp)
                    ) {
                        Text(
                            "$tab ($count)",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (sel) CardWhite else SoftGreen,
                            fontWeight = if (sel) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        // ── Content based on selected tab ───────────────────────────────────
        when (selectedTab) {
            "Recipes" -> {
                if (SavedItemsManager.savedRecipes.isEmpty()) {
                    item { EmptyState("🌾", "No saved recipes yet",
                        "Browse the Recipe Lab and tap the bookmark to save recipes.",
                        "Go to Recipe Lab") { navController.navigate("recipe") { launchSingleTop = true } }
                    }
                } else {
                    items(SavedItemsManager.savedRecipes.toList(), key = { it.title }) { recipe ->
                        Card(
                            modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp))
                                .clickable { navController.navigate("recipe_detail/${recipe.title}") },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = CardWhite)
                        ) {
                            Row(Modifier.fillMaxWidth().padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(62.dp).clip(RoundedCornerShape(14.dp)).background(ChipBg),
                                    Alignment.Center) {
                                    Text(recipe.emoji, fontSize = 28.sp)
                                }
                                Spacer(Modifier.width(14.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(recipe.title, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg)
                                            .padding(horizontal = 8.dp, vertical = 3.dp)) {
                                            Text(recipe.category, style = MaterialTheme.typography.labelSmall,
                                                color = SoftGreen)
                                        }
                                        Spacer(Modifier.width(8.dp))
                                        Icon(Icons.Default.Timer, null, tint = TextSecondary,
                                            modifier = Modifier.size(12.dp))
                                        Spacer(Modifier.width(3.dp))
                                        Text(recipe.time, style = MaterialTheme.typography.labelSmall,
                                            color = TextSecondary)
                                    }
                                }
                                IconButton(onClick = { SavedItemsManager.toggleRecipe(recipe) }) {
                                    Icon(Icons.Default.Bookmark, "Remove", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
            "Sellers" -> {
                if (SavedItemsManager.savedSellers.isEmpty()) {
                    item { EmptyState("👨‍🌾", "No saved sellers yet",
                        "Browse Direct Buy and bookmark your favorite farmers.",
                        "Go to Direct Buy") { navController.navigate("direct_buy") { launchSingleTop = true } }
                    }
                } else {
                    items(SavedItemsManager.savedSellers.toList(), key = { it.name }) { seller ->
                        Card(
                            modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp))
                                .clickable { navController.navigate("seller_detail/${seller.name}") },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = CardWhite)
                        ) {
                            Row(Modifier.fillMaxWidth().padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp))
                                    .background(Color(0xFFD8E8D4)), Alignment.Center) {
                                    Text("👨‍🌾", fontSize = 24.sp)
                                }
                                Spacer(Modifier.width(14.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(seller.name, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(4.dp))
                                    Row {
                                        Text(seller.location, style = MaterialTheme.typography.bodySmall,
                                            color = TextSecondary)
                                        Spacer(Modifier.width(8.dp))
                                        Text(seller.rating, style = MaterialTheme.typography.labelSmall,
                                            color = MossGreen, fontWeight = FontWeight.SemiBold)
                                    }
                                    Spacer(Modifier.height(2.dp))
                                    Text("${seller.millet} · ${seller.price}",
                                        style = MaterialTheme.typography.labelSmall, color = SoftGreen)
                                }
                                IconButton(onClick = { SavedItemsManager.toggleSeller(seller) }) {
                                    Icon(Icons.Default.Bookmark, "Remove", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
            "Health" -> {
                if (SavedItemsManager.savedHealthInsights.isEmpty()) {
                    item { EmptyState("💚", "No saved health insights yet",
                        "Explore Health Benefits and save wellness cards.",
                        "Go to Health Benefits") { navController.navigate("health") { launchSingleTop = true } }
                    }
                } else {
                    items(SavedItemsManager.savedHealthInsights.toList(), key = { it.title }) { insight ->
                        Card(
                            modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = CardWhite)
                        ) {
                            Row(Modifier.fillMaxWidth().padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(ChipBg),
                                    Alignment.Center) {
                                    Icon(Icons.Default.Favorite, null, tint = MossGreen,
                                        modifier = Modifier.size(24.dp))
                                }
                                Spacer(Modifier.width(14.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(insight.title, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(4.dp))
                                    Row {
                                        Box(Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg)
                                            .padding(horizontal = 8.dp, vertical = 3.dp)) {
                                            Text(insight.category, style = MaterialTheme.typography.labelSmall,
                                                color = SoftGreen)
                                        }
                                        Spacer(Modifier.width(8.dp))
                                        Box(Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg)
                                            .padding(horizontal = 8.dp, vertical = 3.dp)) {
                                            Text(insight.tag, style = MaterialTheme.typography.labelSmall,
                                                color = MossGreen, fontWeight = FontWeight.SemiBold)
                                        }
                                    }
                                }
                                IconButton(onClick = { SavedItemsManager.toggleHealthInsight(insight) }) {
                                    Icon(Icons.Default.Bookmark, "Remove", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
            "Market" -> {
                if (SavedItemsManager.savedMarketItems.isEmpty()) {
                    item { EmptyState("📊", "No market watchlist items yet",
                        "Go to Mandi Watch and bookmark millet-market pairs.",
                        "Go to Mandi Watch") { navController.navigate("mandi") { launchSingleTop = true } }
                    }
                } else {
                    items(SavedItemsManager.savedMarketItems.toList(),
                        key = { "${it.millet}_${it.market}" }) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = CardWhite)
                        ) {
                            Row(Modifier.fillMaxWidth().padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(ChipBg),
                                    Alignment.Center) {
                                    Icon(Icons.Default.ShowChart, null, tint = MossGreen,
                                        modifier = Modifier.size(24.dp))
                                }
                                Spacer(Modifier.width(14.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(item.millet, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(2.dp))
                                    Text("${item.market} Market",
                                        style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                                }
                                IconButton(onClick = { SavedItemsManager.toggleMarketItem(item) }) {
                                    Icon(Icons.Default.Bookmark, "Remove", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(8.dp)); BottomNavBar(navController) }
    }
}

@Composable
private fun EmptyState(
    emoji: String,
    title: String,
    subtitle: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column(
        Modifier.fillMaxWidth().padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(emoji, fontSize = 48.sp)
        Spacer(Modifier.height(12.dp))
        Text(title, style = MaterialTheme.typography.titleMedium, color = TextSecondary)
        Spacer(Modifier.height(8.dp))
        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MossGreen)
        ) {
            Text(buttonText, color = Color.White)
        }
    }
}
