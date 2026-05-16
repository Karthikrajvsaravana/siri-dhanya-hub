package com.example.siridhanyahub.ui.screens.splash


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.siridhanyahub.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1200),
        label = ""
    )

    LaunchedEffect(true) {

        startAnimation = true

        delay(3500)

        navController.navigate("welcome") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.splash_bg),
        contentDescription = "Splash Background",
        modifier = Modifier
            .fillMaxSize()
            .alpha(alphaAnimation.value),
        contentScale = ContentScale.Crop
    )
}