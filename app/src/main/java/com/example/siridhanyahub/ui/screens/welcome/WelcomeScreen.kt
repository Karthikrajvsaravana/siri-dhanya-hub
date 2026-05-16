package com.example.siridhanyahub.ui.screens.welcome

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siridhanyahub.R

@Composable
fun WelcomeScreen(onContinueClicked: (String) -> Unit = {}) {

    var selectedLanguage by remember { mutableStateOf("English") }

    // Premium Theme Colors
    val ivoryBackground = Color(0xFFFAF8F5)
    val darkMossGreen = Color(0xFF2D5A27)
    val textPrimary = Color(0xFF1E293B)
    val textSecondary = Color(0xFF64748B)

    Column(
            modifier =
                    Modifier.fillMaxSize()
                            .background(ivoryBackground)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo
        Image(
                painter = painterResource(id = R.drawable.siri_dhanya_logo),
                contentDescription = "Siri Dhanya Logo",
                modifier =
                        Modifier.size(120.dp)
                                .clip(RoundedCornerShape(60.dp))
                                .background(Color.White)
                                .padding(12.dp),
                contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Subtitle
        Text(
                text = "Empowering the Millet Ecosystem",
                color = darkMossGreen,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Heading
        Text(
                text = "Welcome to\nSiri-Dhanya Hub",
                color = textPrimary,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 42.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Description
        Text(
                text = "Smart support for farmers, families and healthy millet living.",
                color = textSecondary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Welcome Banner
        Card(
                modifier =
                        Modifier.fillMaxWidth()
                                .height(220.dp)
                                .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Image(
                    painter = painterResource(id = R.drawable.welcome_banner),
                    contentDescription = "Welcome Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Language Title
        Text(
                text = "Choose Your Language",
                color = textPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Language Buttons
        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LanguageButton(
                    text = "English",
                    isSelected = selectedLanguage == "English",
                    onClick = { selectedLanguage = "English" },
                    modifier = Modifier.weight(1f),
                    selectedColor = darkMossGreen
            )

            LanguageButton(
                    text = "ಕನ್ನಡ",
                    isSelected = selectedLanguage == "ಕನ್ನಡ",
                    onClick = { selectedLanguage = "ಕನ್ನಡ" },
                    modifier = Modifier.weight(1f),
                    selectedColor = darkMossGreen
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Helper Text
        Text(
                text = "You can change this anytime later.",
                color = textSecondary,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Continue Button
        Button(
                onClick = { onContinueClicked(selectedLanguage) },
                modifier = Modifier.fillMaxWidth().height(58.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = darkMossGreen)
        ) {
            Text(
                    text = "Continue",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "→", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun LanguageButton(
        text: String,
        isSelected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        selectedColor: Color
) {

    val backgroundColor = if (isSelected) selectedColor else Color.White

    val contentColor = if (isSelected) Color.White else selectedColor

    val borderColor = if (isSelected) selectedColor else selectedColor.copy(alpha = 0.4f)

    Box(
            modifier =
                    modifier.clip(RoundedCornerShape(18.dp))
                            .background(backgroundColor)
                            .border(
                                    width = 1.dp,
                                    color = borderColor,
                                    shape = RoundedCornerShape(18.dp)
                            )
                            .clickable { onClick() }
                            .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
    ) {
        Text(
                text = text,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
        )
    }
}
