package com.example.siridhanyahub.ui.screens.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val ChipBg        = Color(0xFFEAF2E8)
private val TrustBg       = Color(0xFFE3EFE0)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)



@Composable
fun DirectBuyScreen(navController: NavHostController) {
    val milletList = listOf("All", "Ragi", "Navane", "Sajje", "Baragu")
    var selectedMillet by remember { mutableStateOf("All") }

    val displayedSellers = remember(selectedMillet) {
        if (selectedMillet == "All") allSellers
        else allSellers.filter { it.millet == selectedMillet }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(IvoryBg).statusBarsPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(38.dp).clip(CircleShape).background(ChipBg).clickable { navController.popBackStack() }, Alignment.Center) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Direct Buy", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Fresh from Karnataka's farms", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        item {
            Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)), RoundedCornerShape(18.dp), CardDefaults.cardColors(CardWhite)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(ChipBg), Alignment.Center) {
                            Icon(Icons.Default.LocationOn, null, tint = MossGreen, modifier = Modifier.size(18.dp))
                        }
                        Spacer(Modifier.width(12.dp))
                        Text("Karnataka Region", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    }
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = TextSecondary)
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(milletList) { millet ->
                    val sel = millet == selectedMillet
                    Box(
                        Modifier.clip(RoundedCornerShape(18.dp)).background(if (sel) MossGreen else ChipBg)
                            .clickable { selectedMillet = millet }.padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(millet, style = MaterialTheme.typography.labelMedium,
                            color = if (sel) CardWhite else SoftGreen,
                            fontWeight = if (sel) FontWeight.SemiBold else FontWeight.Normal)
                    }
                }
            }
        }

        item {
            Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(TrustBg)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(44.dp).clip(CircleShape).background(CardWhite.copy(alpha = 0.75f)), Alignment.Center) {
                        Icon(Icons.Default.Verified, null, tint = MossGreen, modifier = Modifier.size(22.dp))
                    }
                    Spacer(Modifier.width(14.dp))
                    Column {
                        Text("${allSellers.size} Trusted Farmer Producer Organizations", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Spacer(Modifier.height(4.dp))
                        Text("Verified directly from source for highest quality.", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    }
                }
            }
        }

        item {
            Text("Available Farmers", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
            Spacer(Modifier.height(8.dp))
            if (displayedSellers.isEmpty()) {
                Text("No sellers available for $selectedMillet right now.", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    displayedSellers.forEach { seller ->
                        FarmerCard(
                            seller = seller,
                            onCardClick    = { navController.navigate("seller_detail/${seller.name}") },
                            onCallClick    = { /* Show call UI — placeholder, no CALL_PHONE permission needed */ },
                            onBuyClick     = { /* Request Buy confirmation placeholder */ }
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(8.dp)); BottomNavBar(navController) }
    }
}

