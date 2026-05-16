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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Timer
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
import com.example.siridhanyahub.data.Recipe
import com.example.siridhanyahub.data.RecipeRepository
import com.example.siridhanyahub.ui.components.BottomNavBar
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


@Composable
fun RecipeLabScreen(navController: NavHostController) {

    val categories = listOf("Breakfast", "Lunch", "Snacks", "Dessert")
    var selectedCategory by remember { mutableStateOf("Breakfast") }
    val recipes = RecipeRepository.getRecipesByCategory(selectedCategory)

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
                    Icon(Icons.Default.ArrowBack, "Back", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Recipe Lab", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Discover the power of ancient grains.", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
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
                Text("Search millet recipes...", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
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
                            navController.navigate("recipe_detail/${hero.title}")
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
                                Text(hero.image, fontSize = 64.sp)
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
                                    "Today's Healthy Pick",
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
                                Text(hero.cookingTime, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
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
                text = "$selectedCategory Recipes",
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
                    RecipeListCard(recipe = recipe) {
                        navController.navigate("recipe_detail/${recipe.title}")
                    }
                }
            }
        }

        // ── Embedded Nav Footer ───────────────────────────────────────────
        item {
            Spacer(modifier = Modifier.height(8.dp))
            BottomNavBar(navController)
        }
    }
}

@Composable
fun RecipeListCard(recipe: Recipe, onClick: () -> Unit) {
    val isSaved = SavedItemsManager.isRecipeSaved(recipe.title)
    // Determine category for the save model
    val category = recipe.category

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
                Text(recipe.image, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(recipe.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = Color(0xFF1C2B1A))
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    recipe.healthTags.take(3).forEach { tag ->
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
                    Text(recipe.cookingTime, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("· ${recipe.difficulty}", style = MaterialTheme.typography.labelSmall, color = MossGreen)
                }
            }
            IconButton(onClick = {
                SavedItemsManager.toggleRecipe(
                    SavedItemsManager.SavedRecipe(
                        title = recipe.title,
                        category = category,
                        time = recipe.cookingTime,
                        emoji = recipe.image
                    )
                )
            }) {
                Icon(
                    if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = if (isSaved) "Unsave" else "Save",
                    tint = MossGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// Keep old RecipeCard alias for backward compatibility
@Composable
fun RecipeCard(image: Int, title: String, tags: String) {
    val fallbackTags = tags.split(" · ")
    RecipeListCard(
        recipe = Recipe(
            id = "legacy",
            title = title,
            category = "General",
            milletType = "Mixed",
            image = "🌾",
            shortDescription = "",
            cookingTime = "20 min",
            difficulty = "Easy",
            calories = "200 kcal",
            servings = 2,
            healthTags = fallbackTags,
            ingredients = emptyList(),
            cookingSteps = emptyList(),
            healthBenefits = emptyList()
        ),
        onClick = {}
    )
}