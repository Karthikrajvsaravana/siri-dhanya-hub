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
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.siridhanyahub.ui.state.SavedItemsManager

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

// Translations
private val recipeTitleTranslations = mapOf(
    "Navane Upma" to "ನವಣೆ ಉಪ್ಪಿಟ್ಟು",
    "Ragi Idli" to "ರಾಗಿ ಇಡ್ಲಿ",
    "Millet Pongal" to "ಸಿರಿಧಾನ್ಯ ಪೊಂಗಲ್"
)

private val categoryTranslations = mapOf(
    "Breakfast" to "ಬೆಳಗಿನ ತಿಂಡಿ",
    "Meals"     to "ಊಟ",
    "Snacks"    to "ತಿಂಡಿಗಳು",
    "Baking"    to "ಬೇಕಿಂಗ್"
)

private val sellerNameTranslations = mapOf(
    "Ramesh Gowda"     to "ರಮೇಶ್ ಗೌಡ",
    "Lakshmi Naturals" to "ಲಕ್ಷ್ಮಿ ನ್ಯಾಚುರಲ್ಸ್",
    "Siddaganga Farms" to "ಸಿದ್ದಗಂಗಾ ಫಾರ್ಮ್ಸ್"
)

private val sellerLocationTranslations = mapOf(
    "Mandya, Karnataka"   to "ಮಂಡ್ಯ, ಕರ್ನಾಟಕ",
    "Tumakuru, Karnataka" to "ತುಮಕೂರು, ಕರ್ನಾಟಕ",
    "Hassan, Karnataka"   to "ಹಾಸನ, ಕರ್ನಾಟಕ"
)

private val milletTranslations = mapOf(
    "Ragi"   to "ರಾಗಿ",
    "Navane" to "ನವಣೆ",
    "Sajje"  to "ಸಜ್ಜೆ",
    "Harka"  to "ಹರ್ಕ",
    "Korle"  to "ಕೊರ್ಲೆ",
    "Oodalu" to "ಊದಲು",
    "Baragu" to "ಬರಗು",
    "Same"   to "ಸಾಮೆ"
)

private val marketTranslations = mapOf(
    "Bengaluru"  to "ಬೆಂಗಳೂರು",
    "Mysuru"     to "ಮೈಸೂರು",
    "Hubballi"   to "ಹುಬ್ಬಳ್ಳಿ",
    "Davanagere" to "ದಾವಣಗೆರೆ"
)

private val healthTitleTranslations = mapOf(
    "Low GI for Blood Sugar" to "ರಕ್ತದ ಸಕ್ಕರೆಗೆ ಕಡಿಮೆ ಜಿಐ",
    "Rich in Calcium"        to "ಕ್ಯಾಲ್ಸಿಯಂ ಸಮೃದ್ಧವಾಗಿದೆ",
    "High Dietary Fiber"     to "ಹೆಚ್ಚಿನ ಫೈಬರ್",
    "Gluten Free"            to "ಗ್ಲುಟನ್ ಮುಕ್ತ"
)

private val healthCategoryTranslations = mapOf(
    "Diabetes Care" to "ಮಧುಮೇಹ ಆರೈಕೆ",
    "Bone Health"   to "ಮೂಳೆ ಆರೋಗ್ಯ",
    "Digestion"     to "ಜೀರ್ಣಕ್ರಿಯೆ",
    "Allergy Safe"  to "ಅಲರ್ಜಿ ಮುಕ್ತ"
)

@Composable
fun SavedScreenKannada(navController: NavHostController) {

    val tabs = listOf("ಪಾಕವಿಧಾನಗಳು", "ಮಾರಾಟಗಾರರು", "ಆರೋಗ್ಯ", "ಮಾರುಕಟ್ಟೆ")
    var selectedTab by remember { mutableStateOf("ಪಾಕವಿಧಾನಗಳು") }

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
                    Icon(Icons.Default.ArrowBack, "ಹಿಂದೆ", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("ಉಳಿಸಿದವು", style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("$totalCount ವಸ್ತುಗಳನ್ನು ಉಳಿಸಲಾಗಿದೆ",
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
                        "ಪಾಕವಿಧಾನಗಳು" -> SavedItemsManager.savedRecipes.size
                        "ಮಾರಾಟಗಾರರು" -> SavedItemsManager.savedSellers.size
                        "ಆರೋಗ್ಯ"  -> SavedItemsManager.savedHealthInsights.size
                        "ಮಾರುಕಟ್ಟೆ"  -> SavedItemsManager.savedMarketItems.size
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
            "ಪಾಕವಿಧಾನಗಳು" -> {
                if (SavedItemsManager.savedRecipes.isEmpty()) {
                    item { EmptyStateKannada("🌾", "ಇನ್ನೂ ಯಾವುದೇ ಪಾಕವಿಧಾನಗಳನ್ನು ಉಳಿಸಿಲ್ಲ",
                        "ಪಾಕವಿಧಾನ ಪ್ರಯೋಗಾಲಯವನ್ನು ಬ್ರೌಸ್ ಮಾಡಿ ಮತ್ತು ಉಳಿಸಲು ಬುಕ್‌ಮಾರ್ಕ್ ಅನ್ನು ಟ್ಯಾಪ್ ಮಾಡಿ.",
                        "ಪಾಕವಿಧಾನ ಪ್ರಯೋಗಾಲಯಕ್ಕೆ ಹೋಗಿ") { navController.navigate("recipe_kn") { launchSingleTop = true } }
                    }
                } else {
                    items(SavedItemsManager.savedRecipes.toList(), key = { it.title }) { recipe ->
                        Card(
                            modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp))
                                .clickable { navController.navigate("recipe_detail_kn/${recipe.title}") },
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
                                    Text(recipeTitleTranslations[recipe.title] ?: recipe.title, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg)
                                            .padding(horizontal = 8.dp, vertical = 3.dp)) {
                                            Text(categoryTranslations[recipe.category] ?: recipe.category, style = MaterialTheme.typography.labelSmall,
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
                                    Icon(Icons.Default.Bookmark, "ತೆಗೆದುಹಾಕಿ", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
            "ಮಾರಾಟಗಾರರು" -> {
                if (SavedItemsManager.savedSellers.isEmpty()) {
                    item { EmptyStateKannada("👨‍🌾", "ಇನ್ನೂ ಯಾವುದೇ ಮಾರಾಟಗಾರರನ್ನು ಉಳಿಸಿಲ್ಲ",
                        "ನೇರ ಖರೀದಿಯನ್ನು ಬ್ರೌಸ್ ಮಾಡಿ ಮತ್ತು ನಿಮ್ಮ ನೆಚ್ಚಿನ ರೈತರನ್ನು ಉಳಿಸಿ.",
                        "ನೇರ ಖರೀದಿಗೆ ಹೋಗಿ") { navController.navigate("buy_kn") { launchSingleTop = true } }
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
                                    Text(sellerNameTranslations[seller.name] ?: seller.name, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(4.dp))
                                    Row {
                                        Text(sellerLocationTranslations[seller.location] ?: seller.location, style = MaterialTheme.typography.bodySmall,
                                            color = TextSecondary)
                                        Spacer(Modifier.width(8.dp))
                                        Text(seller.rating, style = MaterialTheme.typography.labelSmall,
                                            color = MossGreen, fontWeight = FontWeight.SemiBold)
                                    }
                                    Spacer(Modifier.height(2.dp))
                                    Text("${milletTranslations[seller.millet] ?: seller.millet} · ${seller.price}",
                                        style = MaterialTheme.typography.labelSmall, color = SoftGreen)
                                }
                                IconButton(onClick = { SavedItemsManager.toggleSeller(seller) }) {
                                    Icon(Icons.Default.Bookmark, "ತೆಗೆದುಹಾಕಿ", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
            "ಆರೋಗ್ಯ" -> {
                if (SavedItemsManager.savedHealthInsights.isEmpty()) {
                    item { EmptyStateKannada("💚", "ಇನ್ನೂ ಯಾವುದೇ ಆರೋಗ್ಯ ಮಾಹಿತಿಯನ್ನು ಉಳಿಸಿಲ್ಲ",
                        "ಆರೋಗ್ಯ ಪ್ರಯೋಜನಗಳನ್ನು ಅನ್ವೇಷಿಸಿ ಮತ್ತು ವೆಲ್ನೆಸ್ ಕಾರ್ಡ್‌ಗಳನ್ನು ಉಳಿಸಿ.",
                        "ಆರೋಗ್ಯ ಪ್ರಯೋಜನಗಳಿಗೆ ಹೋಗಿ") { navController.navigate("health_kn") { launchSingleTop = true } }
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
                                    Text(healthTitleTranslations[insight.title] ?: insight.title, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(4.dp))
                                    Row {
                                        Box(Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg)
                                            .padding(horizontal = 8.dp, vertical = 3.dp)) {
                                            Text(healthCategoryTranslations[insight.category] ?: insight.category, style = MaterialTheme.typography.labelSmall,
                                                color = SoftGreen)
                                        }
                                        Spacer(Modifier.width(8.dp))
                                        Box(Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg)
                                            .padding(horizontal = 8.dp, vertical = 3.dp)) {
                                            // Tags usually translate to simple words, fall back if no direct map
                                            Text(insight.tag, style = MaterialTheme.typography.labelSmall,
                                                color = MossGreen, fontWeight = FontWeight.SemiBold)
                                        }
                                    }
                                }
                                IconButton(onClick = { SavedItemsManager.toggleHealthInsight(insight) }) {
                                    Icon(Icons.Default.Bookmark, "ತೆಗೆದುಹಾಕಿ", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
            "ಮಾರುಕಟ್ಟೆ" -> {
                if (SavedItemsManager.savedMarketItems.isEmpty()) {
                    item { EmptyStateKannada("📊", "ಮಾರುಕಟ್ಟೆಯ ವೀಕ್ಷಣಾ ಪಟ್ಟಿಯಲ್ಲಿ ಇನ್ನೂ ಏನೂ ಇಲ್ಲ",
                        "ಮಂಡಿ ವಾಚ್‌ಗೆ ಹೋಗಿ ಮತ್ತು ಸಿರಿಧಾನ್ಯ-ಮಾರುಕಟ್ಟೆ ಜೋಡಿಗಳನ್ನು ಉಳಿಸಿ.",
                        "ಮಂಡಿ ವಾಚ್‌ಗೆ ಹೋಗಿ") { navController.navigate("mandi_kn") { launchSingleTop = true } }
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
                                    Text(milletTranslations[item.millet] ?: item.millet, style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Spacer(Modifier.height(2.dp))
                                    Text("${marketTranslations[item.market] ?: item.market} ಮಾರುಕಟ್ಟೆ",
                                        style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                                }
                                IconButton(onClick = { SavedItemsManager.toggleMarketItem(item) }) {
                                    Icon(Icons.Default.Bookmark, "ತೆಗೆದುಹಾಕಿ", tint = MossGreen,
                                        modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(8.dp)); BottomNavBarKannada(navController) }
    }
}

@Composable
private fun EmptyStateKannada(
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
        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
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

// ── Bottom Nav ───────────────────────────────────────────────────────────
private data class SavedNavItemKannada(val label: String, val icon: ImageVector, val route: String)

private val navItemsKannada = listOf(
    SavedNavItemKannada("ಮುಖಪುಟ",    Icons.Default.Home,     "home_kn"),
    SavedNavItemKannada("ಹುಡುಕು",  Icons.Default.Search,   "search_kn"),
    SavedNavItemKannada("ಉಳಿಸಿದವು",   Icons.Default.Bookmark, "saved_kn"),
    SavedNavItemKannada("ಪ್ರೊಫೈಲ್", Icons.Default.Person,   "profile_kn")
)

@Composable
private fun BottomNavBarKannada(navController: NavHostController) {
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
