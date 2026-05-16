package com.example.siridhanyahub.ui.screens.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
private val TimelineLine  = Color(0xFFD0E6C8)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

@Composable
fun SellerDetailScreen(navController: NavHostController, sellerName: String) {

    val seller = allSellers.find { it.name == sellerName }
        ?: SellerInfo(sellerName, "Karnataka", "Available", "Contact for price", "Mixed Millets",
            "A trusted millet farmer from Karnataka dedicated to quality produce.", "4.5★")

    var buyRequested by remember { mutableStateOf(false) }

    val products = remember(seller.millet) {
        listOf(
            "${seller.millet} – Grade A"   to seller.price,
            "${seller.millet} – Grade B"   to seller.price.replace("3,", "3,0").replace("2,", "2,7"),
            "${seller.millet} Flour – 5kg" to "₹820/bag"
        )
    }

    val trustBadges = listOf("FSSAI Certified", "Organic", "Direct Farm", "Karnataka Govt Approved")

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(IvoryBg).statusBarsPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        // ── Header ─────────────────────────────────────────────────────────
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(38.dp).clip(CircleShape).background(ChipBg).clickable { navController.popBackStack() }, Alignment.Center) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Seller Profile", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Verified Karnataka Farmer", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        // ── Seller Hero Card ────────────────────────────────────────────────
        item {
            Card(Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(22.dp)), RoundedCornerShape(22.dp), CardDefaults.cardColors(TrustBg)) {
                Column(Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(72.dp).clip(RoundedCornerShape(20.dp)).background(Color(0xFFD8E8D4)), Alignment.Center) {
                            Text("👨‍🌾", fontSize = 36.sp)
                        }
                        Spacer(Modifier.width(16.dp))
                        Column(Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(seller.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Spacer(Modifier.width(6.dp))
                                Icon(Icons.Default.Verified, null, tint = MossGreen, modifier = Modifier.size(16.dp))
                            }
                            Spacer(Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(4.dp))
                                Text(seller.location, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(seller.rating, style = MaterialTheme.typography.titleSmall, color = MossGreen, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    Text(seller.description, style = MaterialTheme.typography.bodySmall, color = TextSecondary, lineHeight = 20.sp)
                }
            }
        }

        // ── Trust Badges ────────────────────────────────────────────────────
        item {
            Text("Trust Badges", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                trustBadges.take(2).forEach { badge ->
                    Box(Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(ChipBg).padding(horizontal = 10.dp, vertical = 8.dp), Alignment.Center) {
                        Text(badge, style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                trustBadges.drop(2).forEach { badge ->
                    Box(Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(ChipBg).padding(horizontal = 10.dp, vertical = 8.dp), Alignment.Center) {
                        Text(badge, style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        // ── Product Listings ────────────────────────────────────────────────
        item {
            Text("Available Products", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.6.sp)
            Spacer(Modifier.height(8.dp))
            Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(CardWhite)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    products.forEachIndexed { i, (product, price) ->
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(Modifier.size(8.dp).clip(CircleShape).background(MossGreen.copy(alpha = 0.5f)))
                                Spacer(Modifier.width(12.dp))
                                Text(product, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
                            }
                            Text(price, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MossGreen)
                        }
                        if (i < products.lastIndex) HorizontalDivider(color = Color(0xFFE8E4DC), thickness = 0.6.dp)
                    }
                }
            }
        }

        // ── Stock Info ──────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(Modifier.weight(1f).shadow(4.dp, RoundedCornerShape(18.dp)), RoundedCornerShape(18.dp), CardDefaults.cardColors(CardWhite)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("AVAILABLE", style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(6.dp))
                        Text(seller.stock, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    }
                }
                Card(Modifier.weight(1f).shadow(4.dp, RoundedCornerShape(18.dp)), RoundedCornerShape(18.dp), CardDefaults.cardColors(CardWhite)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("BEST PRICE", style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(6.dp))
                        Text(seller.price, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = MossGreen)
                    }
                }
            }
        }

        // ── Contact Actions ─────────────────────────────────────────────────
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick  = {},
                    modifier = Modifier.weight(1f).height(52.dp),
                    shape    = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Call, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Call", style = MaterialTheme.typography.labelMedium)
                }
                Button(
                    onClick  = { buyRequested = !buyRequested },
                    modifier = Modifier.weight(2f).height(52.dp),
                    shape    = RoundedCornerShape(16.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = if (buyRequested) SoftGreen else MossGreen)
                ) {
                    Icon(Icons.Default.ShoppingBasket, null, tint = Color.White, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(if (buyRequested) "Request Sent ✓" else "Request Buy", color = Color.White, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(8.dp)); BottomNavBar(navController) }
    }
}
