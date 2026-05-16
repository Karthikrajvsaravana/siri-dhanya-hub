package com.example.siridhanyahub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.siridhanyahub.ui.screens.buy.DirectBuyScreen
import com.example.siridhanyahub.ui.screens.buy.SellerDetailScreen
import com.example.siridhanyahub.ui.screens.health.HealthBenefitsScreen
import com.example.siridhanyahub.ui.screens.home.HomeScreen
import com.example.siridhanyahub.ui.screens.home.HomeScreenKannada
import com.example.siridhanyahub.ui.screens.mandi.MandiWatchScreen
import com.example.siridhanyahub.ui.screens.mandi.MandiWatchScreenKannada
import com.example.siridhanyahub.ui.screens.recipe.RecipeDetailScreen
import com.example.siridhanyahub.ui.screens.recipe.RecipeLabScreen
import com.example.siridhanyahub.ui.screens.recipe.RecipeLabScreenKannada
import com.example.siridhanyahub.ui.screens.saved.SavedScreen
import com.example.siridhanyahub.ui.screens.search.SearchScreen
import com.example.siridhanyahub.ui.screens.splash.SplashScreen
import com.example.siridhanyahub.ui.screens.welcome.WelcomeScreen
import com.example.siridhanyahub.ui.screens.ProfileScreen.kt.ProfileScreen
import com.example.siridhanyahub.ui.screens.profile.ProfileScreenKannada

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController    = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController = navController)
        }

        composable("welcome") {
            WelcomeScreen(onContinueClicked = { language ->
                val destination = if (language == "ಕನ್ನಡ" || language == "Kannada") "home_kn" else "home"
                navController.navigate(destination) {
                    popUpTo("welcome") { inclusive = true }
                }
            })
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("home_kn") {
            HomeScreenKannada(navController = navController)
        }

        composable("search") {
            SearchScreen(navController = navController)
        }

        composable("mandi") {
            MandiWatchScreen(navController = navController)
        }

        composable("mandi_kn") {
            MandiWatchScreenKannada(navController = navController)
        }

        composable("recipe") {
            RecipeLabScreen(navController = navController)
        }

        composable("recipe_kn") {
            RecipeLabScreenKannada(navController = navController)
        }

        composable(
            route     = "recipe_detail/{recipeName}",
            arguments = listOf(navArgument("recipeName") { type = NavType.StringType; defaultValue = "" })
        ) { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            RecipeDetailScreen(navController = navController, recipeName = recipeName)
        }

        composable("health") {
            HealthBenefitsScreen(navController = navController)
        }

        composable("direct_buy") {
            DirectBuyScreen(navController = navController)
        }

        composable(
            route     = "seller_detail/{sellerName}",
            arguments = listOf(navArgument("sellerName") { type = NavType.StringType; defaultValue = "" })
        ) { backStackEntry ->
            val sellerName = backStackEntry.arguments?.getString("sellerName") ?: ""
            SellerDetailScreen(navController = navController, sellerName = sellerName)
        }

        composable("saved") {
            SavedScreen(navController = navController)
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }

        composable("profile_kn") {
            ProfileScreenKannada(navController = navController)
        }

        // ── Fallback Kannada Routes ──────────────────────────────────────────
        composable("search_kn") {
            // SearchScreenKannada.kt (doesn't exist yet, falling back)
            SearchScreen(navController = navController)
        }
        composable("saved_kn") {
            com.example.siridhanyahub.ui.screens.saved.SavedScreenKannada(navController = navController)
        }
        composable("health_kn") {
            // HealthBenefitsScreenKannada.kt (doesn't exist yet, falling back)
            HealthBenefitsScreen(navController = navController)
        }
        composable("direct_buy_kn") {
            // DirectBuyScreenKannada.kt (doesn't exist yet, falling back)
            DirectBuyScreen(navController = navController)
        }
        composable(
            route     = "seller_detail_kn/{sellerName}",
            arguments = listOf(navArgument("sellerName") { type = NavType.StringType; defaultValue = "" })
        ) { backStackEntry ->
            // SellerDetailScreenKannada.kt (doesn't exist yet, falling back)
            val sellerName = backStackEntry.arguments?.getString("sellerName") ?: ""
            SellerDetailScreen(navController = navController, sellerName = sellerName)
        }
        composable(
            route     = "recipe_detail_kn/{recipeName}",
            arguments = listOf(navArgument("recipeName") { type = NavType.StringType; defaultValue = "" })
        ) { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            com.example.siridhanyahub.ui.screens.recipe.RecipeDetailScreenKannada(navController = navController, recipeName = recipeName)
        }
    }
}