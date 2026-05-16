package com.example.siridhanyahub.ui.screens.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

data class SellerInfo(
    val name: String,
    val location: String,
    val stock: String,
    val price: String,
    val millet: String,
    val description: String,
    val rating: String
)

val allSellers = listOf(
    SellerInfo("Malnad Millet FPO",       "Davanagere", "35 quintals", "₹3,350/qntl", "Ragi",   "Premium organically grown Ragi from Davanagere's highlands. Direct from 120+ farmer households.", "4.8★"),
    SellerInfo("GreenHarvest Collective", "Hubli",      "20 quintals", "₹3,180/qntl", "Navane", "Award-winning Navane (Foxtail Millet) cultivation collective with FSSAI certification.",           "4.6★"),
    SellerInfo("Raitha Siri Organics",    "Mysuru",     "18 quintals", "₹2,950/qntl", "Sajje",  "Family-run organic Sajje (Pearl Millet) farm supplying to major retailers across Karnataka.",      "4.7★"),
    SellerInfo("Dharwad Grain Trust",     "Dharwad",    "28 quintals", "₹2,780/qntl", "Baragu", "Traditional Baragu (Proso Millet) grown using century-old sustainable farming methods.",            "4.5★")
)

@Composable
fun FarmerCard(
    seller: SellerInfo,
    onCardClick: () -> Unit,
    onCallClick: () -> Unit,
    onBuyClick:  () -> Unit
) {
    var buyRequested by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(20.dp)).clickable { onCardClick() },
        shape  = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFFD8E8D4)), Alignment.Center) {
                    Text("👨‍🌾", fontSize = 24.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(seller.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Spacer(Modifier.width(6.dp))
                        Icon(Icons.Default.Verified, null, tint = MossGreen, modifier = Modifier.size(14.dp))
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(3.dp))
                        Text(seller.location, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                        Spacer(Modifier.width(8.dp))
                        Text(seller.rating, style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F2EC))) {
                Row(Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("STOCK", style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text("${seller.stock} ${seller.millet}", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("PRICE", style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text(seller.price, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MossGreen)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onCallClick,
                    modifier = Modifier.weight(1f),
                    shape  = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Default.Call, null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Call", style = MaterialTheme.typography.labelMedium)
                }
                Button(
                    onClick = { buyRequested = !buyRequested; onBuyClick() },
                    modifier = Modifier.weight(2f),
                    shape  = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = if (buyRequested) SoftGreen else MossGreen)
                ) {
                    Text(
                        if (buyRequested) "Request Sent ✓" else "Request Buy",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
