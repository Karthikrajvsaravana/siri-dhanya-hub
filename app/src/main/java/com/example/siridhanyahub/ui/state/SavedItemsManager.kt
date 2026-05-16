package com.example.siridhanyahub.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * Centralized singleton that holds all saved items across the app session.
 * Uses Compose snapshot-aware lists so every screen recomposes automatically.
 */
object SavedItemsManager {

    // ── Saved Recipes ────────────────────────────────────────────────────────
    data class SavedRecipe(
        val title: String,
        val category: String,
        val time: String,
        val emoji: String
    )

    val savedRecipes: SnapshotStateList<SavedRecipe> = mutableStateListOf()

    fun toggleRecipe(recipe: SavedRecipe) {
        val existing = savedRecipes.find { it.title == recipe.title }
        if (existing != null) savedRecipes.remove(existing) else savedRecipes.add(recipe)
    }

    fun isRecipeSaved(title: String): Boolean = savedRecipes.any { it.title == title }

    // ── Saved Sellers ────────────────────────────────────────────────────────
    data class SavedSeller(
        val name: String,
        val location: String,
        val millet: String,
        val price: String,
        val rating: String
    )

    val savedSellers: SnapshotStateList<SavedSeller> = mutableStateListOf()

    fun toggleSeller(seller: SavedSeller) {
        val existing = savedSellers.find { it.name == seller.name }
        if (existing != null) savedSellers.remove(existing) else savedSellers.add(seller)
    }

    fun isSellerSaved(name: String): Boolean = savedSellers.any { it.name == name }

    // ── Saved Health Insights ────────────────────────────────────────────────
    data class SavedHealthInsight(
        val title: String,
        val category: String,
        val tag: String
    )

    val savedHealthInsights: SnapshotStateList<SavedHealthInsight> = mutableStateListOf()

    fun toggleHealthInsight(insight: SavedHealthInsight) {
        val existing = savedHealthInsights.find { it.title == insight.title }
        if (existing != null) savedHealthInsights.remove(existing) else savedHealthInsights.add(insight)
    }

    fun isHealthInsightSaved(title: String): Boolean = savedHealthInsights.any { it.title == title }

    // ── Saved Market Watchlist ────────────────────────────────────────────────
    data class SavedMarketItem(
        val millet: String,
        val market: String
    )

    val savedMarketItems: SnapshotStateList<SavedMarketItem> = mutableStateListOf()

    fun toggleMarketItem(item: SavedMarketItem) {
        val existing = savedMarketItems.find { it.millet == item.millet && it.market == item.market }
        if (existing != null) savedMarketItems.remove(existing) else savedMarketItems.add(item)
    }

    fun isMarketItemSaved(millet: String, market: String): Boolean =
        savedMarketItems.any { it.millet == millet && it.market == market }
}
