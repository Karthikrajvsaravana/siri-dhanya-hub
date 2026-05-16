package com.example.siridhanyahub.ui.screens.recipe

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
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
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

// ── Palette ─────────────────────────────────────────────────────────────────────
private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val ChipBg        = Color(0xFFEAF2E8)
private val FeaturedBadge = Color(0xFFB7D0A7)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

private val NavMossGreen  = Color(0xFF2D5A27)
private val NavChipBg     = Color(0xFFEAF2E8)
private val NavTextIdle   = Color(0xFF6B7C6A)

data class RecipeKannada(
    val title: String,
    val englishTitle: String,
    val tags: List<String>,
    val difficulty: String,
    val time: String,
    val emoji: String
)

internal val categoryRecipesKannada = mapOf(
    "ಬೆಳಗಿನ ಉಪಹಾರ" to listOf(
        RecipeKannada("ನವಣೆ ಉಪ್ಮಾ", "Navane Upma", listOf("ಕಡಿಮೆ ಜಿಐ", "ಹೆಚ್ಚಿನ ಫೈಬರ್"), "ಸುಲಭ", "15 ನಿಮಿಷ", "🌾"),
        RecipeKannada("ರಾಗಿ ಇಡ್ಲಿ", "Ragi Idli", listOf("ಪ್ರೋಟೀನ್", "ಕಡಿಮೆ ಜಿಐ"), "ಸುಲಭ", "20 ನಿಮಿಷ", "🫓"),
        RecipeKannada("ಸಿರಿಧಾನ್ಯ ಪೊಂಗಲ್", "Millet Pongal", listOf("ಶಕ್ತಿ", "ಫೈಬರ್"), "ಮಧ್ಯಮ", "25 ನಿಮಿಷ", "🍚")
    ),
    "ಮಧ್ಯಾಹ್ನದ ಊಟ" to listOf(
        RecipeKannada("ಸಜ್ಜೆ ರೊಟ್ಟಿ", "Sajje Roti", listOf("ಫೈಬರ್ ಸಮೃದ್ಧ", "ಕಬ್ಬಿಣಾಂಶ"), "ಮಧ್ಯಮ", "25 ನಿಮಿಷ", "🫓"),
        RecipeKannada("ಸಿರಿಧಾನ್ಯ ಕಿಚಡಿ", "Millet Khichdi", listOf("ಸಮತೋಲಿತ", "ಪ್ರೋಟೀನ್"), "ಸುಲಭ", "30 ನಿಮಿಷ", "🍲"),
        RecipeKannada("ಬರಗು ರೈಸ್ ಬೌಲ್", "Baragu Rice Bowl", listOf("ಕಡಿಮೆ ಕ್ಯಾಲೋರಿ", "ಹೆಚ್ಚಿನ ಫೈಬರ್"), "ಸುಲಭ", "20 ನಿಮಿಷ", "🥣")
    ),
    "ತಿಂಡಿಗಳು" to listOf(
        RecipeKannada("ರಾಗಿ ಕುಕೀಸ್", "Ragi Cookies", listOf("ಕ್ಯಾಲ್ಸಿಯಂ", "ಶಕ್ತಿ"), "ಸುಲಭ", "35 ನಿಮಿಷ", "🍪"),
        RecipeKannada("ಸಿರಿಧಾನ್ಯ ಕಟ್ಲೆಟ್", "Millet Cutlet", listOf("ಪ್ರೋಟೀನ್", "ಕಡಿಮೆ ಕೊಬ್ಬು"), "ಮಧ್ಯಮ", "30 ನಿಮಿಷ", "🥙"),
        RecipeKannada("ನವಣೆ ಚಿವ್ಡಾ", "Foxtail Chivda", listOf("ಹಗುರವಾದ", "ಗರಿಗರಿಯಾದ"), "ಸುಲಭ", "20 ನಿಮಿಷ", "🥜")
    ),
    "ಸಿಹಿ ಪದಾರ್ಥಗಳು" to listOf(
        RecipeKannada("ರಾಗಿ ಹಲ್ವಾ", "Ragi Halwa", listOf("ಕ್ಯಾಲ್ಸಿಯಂ", "ಕಬ್ಬಿಣಾಂಶ"), "ಮಧ್ಯಮ", "30 ನಿಮಿಷ", "🍮"),
        RecipeKannada("ಸಿರಿಧಾನ್ಯ ಪಾಯಸ", "Millet Payasam", listOf("ಸಾಂಪ್ರದಾಯಿಕ", "ಸಿಹಿ"), "ಸುಲಭ", "25 ನಿಮಿಷ", "🍵"),
        RecipeKannada("ನವಣೆ ಲಾಡು", "Navane Ladoo", listOf("ಶಕ್ತಿ", "ಫೈಬರ್"), "ಸುಲಭ", "20 ನಿಮಿಷ", "🟡")
    )
)

@Composable
fun RecipeLabScreenKannada(navController: NavHostController) {

    val categories = listOf("ಬೆಳಗಿನ ಉಪಹಾರ", "ಮಧ್ಯಾಹ್ನದ ಊಟ", "ತಿಂಡಿಗಳು", "ಸಿಹಿ ಪದಾರ್ಥಗಳು")
    var selectedCategory by remember { mutableStateOf("ಬೆಳಗಿನ ಉಪಹಾರ") }
    val recipes = categoryRecipesKannada[selectedCategory] ?: emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(IvoryBg)
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }

        // ── Header ────────────────────────────────────────────────────────
        item {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(38.dp).clip(CircleShape).background(ChipBg)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.ArrowBack, "ಹಿಂದೆ", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("ಪಾಕಶಾಲೆ", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("ಪ್ರಾಚೀನ ಧಾನ್ಯಗಳ ಶಕ್ತಿಯನ್ನು ಅನ್ವೇಷಿಸಿ.", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        // ── Search Pill ───────────────────────────────────────────────────
        item {
            Row(
                modifier = Modifier.fillMaxWidth().height(50.dp)
                    .clip(RoundedCornerShape(18.dp)).background(CardWhite).padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.LocalDining, null, tint = TextSecondary, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("ಸಿರಿಧಾನ್ಯ ಪಾಕವಿಧಾನಗಳನ್ನು ಹುಡುಕಿ...", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
        }

        // ── Category Chips ────────────────────────────────────────────────
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(categories) { category ->
                    val sel = category == selectedCategory
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(if (sel) MossGreen else ChipBg)
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            category,
                            style = MaterialTheme.typography.labelMedium,
                            color = if (sel) CardWhite else SoftGreen,
                            fontWeight = if (sel) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        // ── Featured Hero Card (first recipe in category) ─────────────────
        item {
            val hero = recipes.firstOrNull()
            if (hero != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(22.dp))
                        .clickable {
                            navController.navigate("recipe_detail_kn/${hero.englishTitle}")
                        },
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite)
                ) {
                    Column {
                        // ── Image placeholder ────────────────────────────
                        Box(
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                                .background(ChipBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(hero.emoji, fontSize = 64.sp)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = hero.title,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MossGreen,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            // Badge
                            Box(
                                modifier = Modifier.align(Alignment.TopStart).padding(14.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(FeaturedBadge)
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    "ಇಂದಿನ ಆರೋಗ್ಯಕರ ಆಯ್ಕೆ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF24451F),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(hero.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Timer, null, tint = MossGreen, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(hero.time, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("·", color = TextSecondary)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("⚡ ${hero.difficulty}", style = MaterialTheme.typography.bodySmall, color = MossGreen, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }

        // ── Section label ─────────────────────────────────────────────────
        item {
            Text(
                text = "$selectedCategory ಪಾಕವಿಧಾನಗಳು",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                letterSpacing = 0.6.sp
            )
        }

        // ── Recipe list (skip hero, show rest) ────────────────────────────
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                recipes.drop(1).forEach { recipe ->
                    RecipeListCardKannada(recipe = recipe) {
                        navController.navigate("recipe_detail_kn/${recipe.englishTitle}")
                    }
                }
            }
        }

        // ── Embedded Nav Footer ───────────────────────────────────────────
        item {
            Spacer(modifier = Modifier.height(8.dp))
            BottomNavBarKannada(navController)
        }
    }
}

@Composable
fun RecipeListCardKannada(recipe: RecipeKannada, onClick: () -> Unit) {
    val isSaved = SavedItemsManager.isRecipeSaved(recipe.englishTitle)
    // Determine category for the save model
    val category = categoryRecipesKannada.entries
        .firstOrNull { entry -> entry.value.any { it.title == recipe.title } }
        ?.key ?: "ಬೆಳಗಿನ ಉಪಹಾರ"

    Card(
        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)).clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(72.dp).clip(RoundedCornerShape(14.dp)).background(ChipBg),
                contentAlignment = Alignment.Center
            ) {
                Text(recipe.emoji, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(recipe.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = Color(0xFF1C2B1A))
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    recipe.tags.forEach { tag ->
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(ChipBg).padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(tag, style = MaterialTheme.typography.labelSmall, color = SoftGreen)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Timer, null, tint = TextSecondary, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(recipe.time, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("· ${recipe.difficulty}", style = MaterialTheme.typography.labelSmall, color = MossGreen)
                }
            }
            IconButton(onClick = {
                SavedItemsManager.toggleRecipe(
                    SavedItemsManager.SavedRecipe(
                        title = recipe.englishTitle,
                        category = category,
                        time = recipe.time,
                        emoji = recipe.emoji
                    )
                )
            }) {
                Icon(
                    if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = if (isSaved) "ಅಳಿಸಿ" else "ಪಾಕವಿಧಾನ ಉಳಿಸಿ",
                    tint = MossGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// Keep old RecipeCard alias for backward compatibility
@Composable
fun RecipeCardKannada(image: Int, title: String, tags: String) {
    RecipeListCardKannada(
        recipe = RecipeKannada(title, title, tags.split(" · "), "ಸುಲಭ", "20 ನಿಮಿಷ", "🌾"),
        onClick = {}
    )
}

private data class NavItem(val label: String, val icon: ImageVector, val route: String)

private val navItemsKannada = listOf(
    NavItem("ಮುಖಪುಟ",    Icons.Default.Home,     "home_kn"),
    NavItem("ಹುಡುಕು",  Icons.Default.Search,   "search_kn"),
    NavItem("ಉಳಿಸಿದವು",   Icons.Default.Bookmark, "saved_kn"),
    NavItem("ಪ್ರೊಫೈಲ್", Icons.Default.Person,   "profile_kn")
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
