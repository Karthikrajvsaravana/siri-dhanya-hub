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
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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

private val NavMossGreen  = Color(0xFF2D5A27)
private val NavChipBg     = Color(0xFFEAF2E8)
private val NavTextIdle   = Color(0xFF6B7C6A)

// ── Title Translation ────────────────────────────────────────────────────────────
private val titleTranslations = mapOf(
    "Navane Upma" to "ನವಣೆ ಉಪ್ಪಿಟ್ಟು",
    "Ragi Idli" to "ರಾಗಿ ಇಡ್ಲಿ",
    "Millet Pongal" to "ಸಿರಿಧಾನ್ಯ ಪೊಂಗಲ್"
)

// ── Static detail data keyed by English recipe name for logic ────────────────────
private data class RecipeDetailKannada(
    val emoji: String,
    val healthTags: List<String>,
    val whyHealthy: String,
    val ingredients: List<Pair<String, String>>,
    val steps: List<Pair<String, String>>,
    val servings: Int = 2
)

private val recipeDetailsKannada: Map<String, RecipeDetailKannada> = mapOf(
    "Navane Upma" to RecipeDetailKannada(
        emoji = "🌾",
        healthTags = listOf("ಕಡಿಮೆ-ಜಿಐ", "ಹೆಚ್ಚಿನ ಫೈಬರ್", "ಸುಲಭ"),
        whyHealthy = "ಕಡಿಮೆ-ಗ್ಲೈಸೆಮಿಕ್ ಸಿರಿಧಾನ್ಯಗಳ ಮೂಲಕ ಜೀರ್ಣಕ್ರಿಯೆಯನ್ನು ಬೆಂಬಲಿಸುತ್ತದೆ ಮತ್ತು ನಿರಂತರ ಶಕ್ತಿಯನ್ನು ಒದಗಿಸುತ್ತದೆ. ಅಗತ್ಯ ಖನಿಜಗಳಿಂದ ಸಮೃದ್ಧವಾಗಿದೆ ಮತ್ತು ಸಸ್ಯ ಆಧಾರಿತ ಪ್ರೋಟೀನ್‌ನ ಉತ್ತಮ ಮೂಲವಾಗಿದೆ.",
        ingredients = listOf(
            "ನವಣೆ" to "1 ಕಪ್",
            "ಮಿಶ್ರ ತರಕಾರಿಗಳು" to "½ ಕಪ್",
            "ಸಾಸಿವೆ" to "1 ಚಮಚ",
            "ಕರಿಬೇವು" to "ಸ್ವಲ್ಪ",
            "ತುರಿದ ತೆಂಗಿನಕಾಯಿ" to "2 ಚಮಚ"
        ),
        steps = listOf(
            "ಸಿರಿಧಾನ್ಯ ಹುರಿಯಿರಿ" to "ಪರಿಮಳ ಬರುವವರೆಗೆ ಒಣಗಿಸಿ ಹುರಿಯಿರಿ.",
            "ಒಗ್ಗರಣೆ ಸೇರಿಸಿ" to "ಸಾಸಿವೆ ಮತ್ತು ಕರಿಬೇವು ಹುರಿಯಿರಿ.",
            "ತರಕಾರಿ ಸೇರಿಸಿ" to "ತಾಜಾ ಕಾಲೋಚಿತ ತರಕಾರಿಗಳನ್ನು ಮಿಶ್ರಣ ಮಾಡಿ.",
            "ನೀರು ಸೇರಿಸಿ" to "2 ಕಪ್ ನೀರು ಸೇರಿಸಿ ಕುದಿಸಿ.",
            "ಬೇಯಿಸಿ" to "ಮೃದುವಾಗುವವರೆಗೆ ಕಡಿಮೆ ಉರಿಯಲ್ಲಿ ಬೇಯಿಸಿ."
        )
    ),
    "Ragi Idli" to RecipeDetailKannada(
        emoji = "🫓",
        healthTags = listOf("ಪ್ರೋಟೀನ್", "ಕಡಿಮೆ-ಜಿಐ", "ಪ್ರೋಬಯಾಟಿಕ್"),
        whyHealthy = "ಹುದುಗಿಸಿದ ರಾಗಿ ಹಿಟ್ಟು ಕರುಳಿನ ಆರೋಗ್ಯಕ್ಕಾಗಿ ಪ್ರೋಬಯಾಟಿಕ್‌ಗಳನ್ನು ನೀಡುತ್ತದೆ ಮತ್ತು ಬೆಳಗಿನ ಶಕ್ತಿಗಾಗಿ ಹೆಚ್ಚಿನ ಪ್ರೋಟೀನ್ ನೀಡುತ್ತದೆ.",
        ingredients = listOf(
            "ರಾಗಿ ಹಿಟ್ಟು" to "2 ಕಪ್",
            "ಉದ್ದಿನ ಬೇಳೆ" to "½ ಕಪ್",
            "ಉಪ್ಪು" to "ರುಚಿಗೆ ತಕ್ಕಷ್ಟು",
            "ನೀರು" to "ಅಗತ್ಯವಿರುವಷ್ಟು",
            "ಎಣ್ಣೆ" to "1 ಚಮಚ"
        ),
        steps = listOf(
            "ಉದ್ದಿನ ಬೇಳೆ ನೆನೆಸಿ" to "ರಾತ್ರಿಯಿಡೀ ನೆನೆಸಿ ನಯವಾದ ಹಿಟ್ಟಾಗಿ ರುಬ್ಬಿ.",
            "ರಾಗಿ ಹಿಟ್ಟಿನೊಂದಿಗೆ ಮಿಶ್ರಣ" to "ರಾಗಿ ಹಿಟ್ಟಿನೊಂದಿಗೆ ಸೇರಿಸಿ 8 ಗಂಟೆ ಹುದುಗಲು ಬಿಡಿ.",
            "ಉಪ್ಪು ಸೇರಿಸಿ" to "ಬೇಯಿಸುವ ಮೊದಲು ಉಪ್ಪು ಸೇರಿಸಿ.",
            "ಮೌಲ್ಡ್‌ಗಳಿಗೆ ಹಾಕಿ" to "ಇಡ್ಲಿ ಮೌಲ್ಡ್‌ಗಳಿಗೆ ಮುಕ್ಕಾಲು ಭಾಗ ಹಿಟ್ಟು ಹಾಕಿ.",
            "ಹಬೆಯಲ್ಲಿ ಬೇಯಿಸಿ" to "12 ನಿಮಿಷಗಳ ಕಾಲ ಹಬೆಯಲ್ಲಿ ಬೇಯಿಸಿ."
        )
    ),
    "Millet Pongal" to RecipeDetailKannada(
        emoji = "🍚",
        healthTags = listOf("ಶಕ್ತಿ", "ಫೈಬರ್", "ಬೆಚ್ಚಗಿನ"),
        whyHealthy = "ಸಿರಿಧಾನ್ಯ ಪೊಂಗಲ್ ನಿಧಾನ-ಬಿಡುಗಡೆ ಶಕ್ತಿಯನ್ನು ಒದಗಿಸುತ್ತದೆ ಮತ್ತು ಸ್ನಾಯುಗಳ ಕಾರ್ಯಕ್ಕಾಗಿ ಮೆಗ್ನೀಸಿಯಮ್‌ನಿಂದ ತುಂಬಿರುತ್ತದೆ.",
        ingredients = listOf(
            "ಮಿಶ್ರ ಸಿರಿಧಾನ್ಯಗಳು" to "1 ಕಪ್",
            "ಹೆಸರು ಬೇಳೆ" to "¼ ಕಪ್",
            "ತುಪ್ಪ" to "2 ಚಮಚ",
            "ಮೆಣಸು" to "1 ಚಮಚ",
            "ಜೀರಿಗೆ" to "1 ಚಮಚ"
        ),
        steps = listOf(
            "ಬೇಳೆ ಬೇಯಿಸಿ" to "ಸಿರಿಧಾನ್ಯಗಳೊಂದಿಗೆ ಬೇಳೆಯನ್ನು ಮೃದುವಾಗುವವರೆಗೆ ಬೇಯಿಸಿ.",
            "ಒಗ್ಗರಣೆ" to "ತುಪ್ಪ ಬಿಸಿ ಮಾಡಿ, ಜೀರಿಗೆ ಮತ್ತು ಮೆಣಸು ಸೇರಿಸಿ.",
            "ಮಿಶ್ರಣ ಮಾಡಿ" to "ಬೇಳೆ ಮಿಶ್ರಣವನ್ನು ಒಗ್ಗರಣೆಯೊಂದಿಗೆ ಸೇರಿಸಿ.",
            "ರುಚಿ ಸರಿಹೊಂದಿಸಿ" to "ರುಚಿಗೆ ತಕ್ಕಷ್ಟು ಉಪ್ಪು ಮತ್ತು ತುಪ್ಪ ಸೇರಿಸಿ.",
            "ಬಿಸಿ ಬಿಸಿಯಾಗಿ ಬಡಿಸಿ" to "ಗೋಡಂಬಿ ಸೇರಿಸಿ ತಕ್ಷಣ ಬಡಿಸಿ."
        )
    )
)

// Fallback detail for any recipe not in the map
private fun fallbackDetailKannada(name: String) = RecipeDetailKannada(
    emoji = "🌾",
    healthTags = listOf("ಆರೋಗ್ಯಕರ", "ಸಿರಿಧಾನ್ಯ", "ನೈಸರ್ಗಿಕ"),
    whyHealthy  = "ಇದು ಫೈಬರ್, ಖನಿಜಗಳು ಮತ್ತು ನಿರಂತರ ಶಕ್ತಿಯಿಂದ ತುಂಬಿದ ಪೌಷ್ಟಿಕ ಪ್ರಾಚೀನ ಧಾನ್ಯದ ಭಕ್ಷ್ಯವಾಗಿದೆ.",
    ingredients = listOf(
        "ಸಿರಿಧಾನ್ಯ" to "1 ಕಪ್",
        "ತರಕಾರಿಗಳು" to "½ ಕಪ್",
        "ಮಸಾಲೆಗಳು" to "ರುಚಿಗೆ",
        "ನೀರು" to "2 ಕಪ್",
        "ಎಣ್ಣೆ" to "1 ಚಮಚ"
    ),
    steps = listOf(
        "ಸಿರಿಧಾನ್ಯ ಸಿದ್ಧಪಡಿಸಿ" to "ಸಿರಿಧಾನ್ಯವನ್ನು ತೊಳೆದು 20 ನಿಮಿಷ ನೆನೆಸಿ.",
        "ಬೇಸ್ ಬೇಯಿಸಿ" to "ಎಣ್ಣೆಯಲ್ಲಿ ಮಸಾಲೆಗಳನ್ನು ಹುರಿಯಿರಿ.",
        "ಸಿರಿಧಾನ್ಯ ಸೇರಿಸಿ" to "ನೆನೆಸಿದ ಸಿರಿಧಾನ್ಯವನ್ನು ಸೇರಿಸಿ ಚೆನ್ನಾಗಿ ಬೆರೆಸಿ.",
        "ನೀರು ಸೇರಿಸಿ" to "ನೀರು ಸೇರಿಸಿ ಕುದಿಸಿ.",
        "ಪೂರ್ಣಗೊಳಿಸಿ" to "ಸಂಪೂರ್ಣವಾಗಿ ಬೇಯುವವರೆಗೆ ಕಡಿಮೆ ಉರಿಯಲ್ಲಿಡಿ."
    )
)

@Composable
fun RecipeDetailScreenKannada(navController: NavHostController, recipeName: String) {

    val detail = recipeDetailsKannada[recipeName] ?: fallbackDetailKannada(recipeName)
    val isSaved = SavedItemsManager.isRecipeSaved(recipeName)
    val displayTitle = titleTranslations[recipeName] ?: recipeName

    // Determine category from recipe data (using top-level English map for logic)
    val englishRecipe = RecipeRepository.getRecipeByTitle(recipeName)
    val recipeCategory = englishRecipe?.category ?: "Breakfast"
    val recipeTime = englishRecipe?.cookingTime ?: "20 min"

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
                    Icon(Icons.Default.ArrowBack, "ಹಿಂದೆ", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "ಪಾಕವಿಧಾನದ ವಿವರಗಳು",
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
                    modifier = Modifier.fillMaxWidth().height(220.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(detail.emoji, fontSize = 80.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            displayTitle,
                            style = MaterialTheme.typography.labelLarge,
                            color = MossGreen,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // ── 3. Title + Tags ───────────────────────────────────────────────
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    displayTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
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
                        Text("ಇದು ಏಕೆ ಆರೋಗ್ಯಕರ", style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        detail.whyHealthy,
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
                            Text("ಪದಾರ್ಥಗಳು", style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                        }
                        Text("${detail.servings} ಜನರಿಗೆ", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    detail.ingredients.forEachIndexed { index, (name, qty) ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(MossGreen.copy(alpha = 0.4f)))
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(name, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
                            }
                            Text(qty, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MossGreen)
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
                        Text("ಅಡುಗೆಯ ಹಾದಿ", style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    detail.steps.forEachIndexed { index, (stepTitle, stepDesc) ->
                        CookingStepKannada(
                            stepNumber = index + 1,
                            title = stepTitle,
                            description = stepDesc,
                            isLast = index == detail.steps.lastIndex
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
                            title = recipeName, // Keep English name for logical consistency
                            category = recipeCategory,
                            time = recipeTime,
                            emoji = detail.emoji
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
                    text = if (isSaved) "ಪಾಕವಿಧಾನ ಉಳಿಸಲಾಗಿದೆ ✓" else "ಪಾಕವಿಧಾನ ಉಳಿಸಿ",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // ── 8. Bottom Navigation Bar ──────────────────────────────────────
        item {
            BottomNavBarKannada(navController)
        }
    }
}

// ── Cooking step timeline composable ─────────────────────────────────────────────
@Composable
private fun CookingStepKannada(
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

// ── Bottom Nav ───────────────────────────────────────────────────────────
private data class RecipeDetailNavItemKannada(val label: String, val icon: ImageVector, val route: String)

private val navItemsKannada = listOf(
    RecipeDetailNavItemKannada("ಮುಖಪುಟ",    Icons.Default.Home,     "home_kn"),
    RecipeDetailNavItemKannada("ಹುಡುಕು",  Icons.Default.Search,   "search_kn"),
    RecipeDetailNavItemKannada("ಉಳಿಸಿದವು",   Icons.Default.Bookmark, "saved_kn"),
    RecipeDetailNavItemKannada("ಪ್ರೊಫೈಲ್", Icons.Default.Person,   "profile_kn")
)

@Composable
private fun BottomNavBarKannada(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
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
                    // Technically none of these is the recipe detail screen itself,
                    // but we can just use standard routing back to main flow.
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
