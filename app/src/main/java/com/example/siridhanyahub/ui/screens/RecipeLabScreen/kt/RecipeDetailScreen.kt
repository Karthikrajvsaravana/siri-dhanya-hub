package com.example.siridhanyahub.ui.screens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.siridhanyahub.ui.state.SavedItemsManager

// ── Palette ──────────────────────────────────────────────────────────────────────
private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val ChipBg        = Color(0xFFEAF2E8)
private val TimelineLine  = Color(0xFFD0E6C8)
private val DividerColor  = Color(0xFFE8E4DC)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

@Composable
fun RecipeDetailScreen(navController: NavHostController, recipeName: String) {

    val detail = RecipeRepository.getRecipeByTitle(recipeName) ?: Recipe(
        id = "fallback",
        title = recipeName,
        category = "General",
        milletType = "Mixed Millet",
        image = "🌾",
        shortDescription = "$recipeName is a nutritious ancient grain dish packed with fiber, minerals, and sustained energy.",
        cookingTime = "20 min",
        difficulty = "Easy",
        calories = "250 kcal",
        servings = 2,
        healthTags = listOf("Healthy", "Millet", "Natural"),
        ingredients = listOf("Millet" to "1 cup", "Water" to "2 cups"),
        cookingSteps = listOf("Prepare" to "Cook the millet."),
        healthBenefits = listOf("Provides energy.")
    )
    val isSaved = SavedItemsManager.isRecipeSaved(recipeName)

    // Determine category from recipe data
    val recipeCategory = detail.category

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(IvoryBg)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // ── 1. Top Header ─────────────────────────────────────────────────
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(38.dp).clip(CircleShape).background(ChipBg)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "Recipe Detail",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ── 2. Hero Image Placeholder ─────────────────────────────────────
        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).shadow(8.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = ChipBg)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(detail.image, fontSize = 64.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(recipeName, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MossGreen)
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color.White.copy(alpha = 0.5f)).padding(horizontal = 10.dp, vertical = 4.dp)) {
                                Text(detail.category, style = MaterialTheme.typography.labelMedium, color = SoftGreen)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.Timer, null, tint = SoftGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(detail.cookingTime, style = MaterialTheme.typography.labelMedium, color = SoftGreen)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("· ${detail.difficulty}", style = MaterialTheme.typography.labelMedium, color = MossGreen)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("· ${detail.calories}", style = MaterialTheme.typography.labelMedium, color = SoftGreen)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // ── 3. Title + Tags ───────────────────────────────────────────────
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    detail.healthTags.forEach { tag ->
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(ChipBg).padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(tag, style = MaterialTheme.typography.labelMedium, color = SoftGreen, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ── 4. Why It's Healthy Card ──────────────────────────────────────
        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).shadow(4.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Eco, null, tint = MossGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("WHY IT'S HEALTHY", style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        detail.shortDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 22.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        // ── 5. Ingredients Card ───────────────────────────────────────────
        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).shadow(4.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocalDining, null, tint = MossGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("INGREDIENTS", style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                        }
                        Text("${detail.servings} Servings", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    detail.ingredients.forEachIndexed { index, ingredient ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(MossGreen.copy(alpha = 0.4f)))
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(ingredient.first, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, color = TextPrimary)
                            }
                            Text(ingredient.second, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MossGreen)
                        }
                        if (index < detail.ingredients.lastIndex) {
                            HorizontalDivider(color = DividerColor, thickness = 0.6.dp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        // ── 6. Cooking Journey Timeline ───────────────────────────────────
        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).shadow(4.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocalDining, null, tint = MossGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("COOKING JOURNEY", style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    detail.cookingSteps.forEachIndexed { index, (stepTitle, stepDesc) ->
                        CookingStep(
                            stepNumber = index + 1,
                            title = stepTitle,
                            description = stepDesc,
                            isLast = index == detail.cookingSteps.lastIndex
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ── 7. Save Recipe Button ─────────────────────────────────────────
        item {
            Button(
                onClick = {
                    SavedItemsManager.toggleRecipe(
                        SavedItemsManager.SavedRecipe(
                            title = recipeName,
                            category = detail.category,
                            time = detail.cookingTime,
                            emoji = detail.image
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSaved) SoftGreen else MossGreen
                )
            ) {
                Icon(
                    if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (isSaved) "Recipe Saved ✓" else "Save Recipe",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        
        // ── Health Benefits ────────────────────────────────────────────────────────
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).shadow(4.dp, RoundedCornerShape(22.dp)),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = ChipBg)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Health Benefits", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MossGreen)
                    Spacer(modifier = Modifier.height(12.dp))
                    detail.healthBenefits.forEach { benefit ->
                        Row(modifier = Modifier.padding(bottom = 6.dp), verticalAlignment = Alignment.Top) {
                            Text("•", color = MossGreen, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 8.dp))
                            Text(benefit, style = MaterialTheme.typography.bodyMedium, color = TextSecondary, lineHeight = 20.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ── Cooking step timeline composable ─────────────────────────────────────────────
@Composable
private fun CookingStep(
    stepNumber: Int,
    title: String,
    description: String,
    isLast: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Timeline column
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Step circle
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(ChipBg)
                    .border(1.5.dp, MossGreen.copy(alpha = 0.4f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "$stepNumber",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MossGreen
                )
            }
            // Connecting line (except last step)
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(44.dp)
                        .background(TimelineLine)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        // Step content
        Column(modifier = Modifier.padding(bottom = if (isLast) 0.dp else 12.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Spacer(modifier = Modifier.height(3.dp))
            Text(description, style = MaterialTheme.typography.bodySmall, color = TextSecondary, lineHeight = 18.sp)
        }
    }
}
