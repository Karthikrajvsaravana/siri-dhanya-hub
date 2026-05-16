package com.example.siridhanyahub.ui.screens.health

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
import androidx.compose.ui.graphics.vector.ImageVector
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
private val WellnessBg    = Color(0xFFE3EFE0)
private val TipBg         = Color(0xFFEBF3E8)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

private data class BenefitData(val title: String, val subtitle: String, val tag: String, val icon: ImageVector)
private data class HealthContent(
    val heroTitle: String, val heroSubtitle: String, val score: String,
    val benefits: List<BenefitData>, val doctorNote: String, val dailyTip: String
)

private val healthData = mapOf(
    "Diabetes" to HealthContent(
        "Ragi Wellness Score", "Low-GI powerhouse for blood sugar control", "92% Wellness Match",
        listOf(
            BenefitData("Blood Sugar Control", "Low GI slows glucose absorption",       "Low GI",    Icons.Default.MonitorHeart),
            BenefitData("Insulin Sensitivity", "Regular millet improves sensitivity",   "Glycemic",  Icons.Default.Favorite),
            BenefitData("Fiber-Rich",          "Soluble fiber reduces sugar spikes",    "Fiber",     Icons.Default.Spa),
            BenefitData("Sustained Energy",    "Avoids energy crashes post meals",      "Energy",    Icons.Default.LocalFireDepartment)
        ),
        "Doctors recommend replacing white rice with millets for better long-term glycemic control in Type-2 Diabetes management.",
        "Replace white rice with Ragi or Navane for one meal a day to significantly reduce your blood sugar spikes."
    ),
    "Weight Loss" to HealthContent(
        "Navane Slimming Score", "High fiber keeps you full longer", "88% Wellness Match",
        listOf(
            BenefitData("High Satiety",     "Keeps hunger at bay for 4+ hours",    "Satiety",    Icons.Default.Spa),
            BenefitData("Low Calorie",      "High nutrients at fewer calories",     "Low Cal",    Icons.Default.LocalFireDepartment),
            BenefitData("Metabolism Boost", "B vitamins accelerate fat metabolism", "Metabolism", Icons.Default.MonitorHeart),
            BenefitData("Lean Protein",     "Supports muscle retention during diet","Protein",    Icons.Default.Shield)
        ),
        "Nutritionists recommend a millet-first diet plan for sustainable weight management without nutrient deficiency.",
        "Swap your evening snack with a bowl of Foxtail Millet upma to cut 300 kcal per day effortlessly."
    ),
    "Heart Health" to HealthContent(
        "Baragu Heart Score", "Omega-rich grains for cardiovascular care", "90% Wellness Match",
        listOf(
            BenefitData("Cholesterol Control","Reduces LDL cholesterol levels",      "Lipid",      Icons.Default.Favorite),
            BenefitData("Blood Pressure",     "Magnesium relaxes blood vessels",     "Mg-Rich",    Icons.Default.MonitorHeart),
            BenefitData("Antioxidants",       "Polyphenols reduce oxidative stress", "Antioxidant",Icons.Default.Shield),
            BenefitData("Heart Fiber",        "Reduces arterial plaque buildup",     "Fiber",      Icons.Default.Spa)
        ),
        "Cardiologists in Karnataka recommend Baragu (Proso Millet) as a heart-friendly daily grain for patients with hypertension.",
        "Include a millet porridge with flaxseeds every morning to naturally support healthy blood pressure levels."
    ),
    "Energy" to HealthContent(
        "Sajje Energy Score", "Complex carbs for all-day vitality", "95% Wellness Match",
        listOf(
            BenefitData("Iron Boost",     "Supports hemoglobin and oxygen supply","Iron",      Icons.Default.LocalFireDepartment),
            BenefitData("B-Vitamins",     "Convert food to usable energy",        "B-Complex", Icons.Default.Favorite),
            BenefitData("Slow Release",   "Complex carbs for steady energy",      "Low GI",    Icons.Default.MonitorHeart),
            BenefitData("Bone Strength",  "Calcium fuels muscle contraction",     "Calcium",   Icons.Default.Shield)
        ),
        "Sports nutritionists recommend Sajje (Pearl Millet) for pre-workout energy and post-workout recovery.",
        "Eat a Sajje roti 30 minutes before exercise to fuel your workout with slow-release complex carbohydrates."
    )
)

@Composable
fun HealthBenefitsScreen(navController: NavHostController) {
    val categories = listOf("Diabetes", "Weight Loss", "Heart Health", "Energy")
    var selectedCategory by remember { mutableStateOf("Diabetes") }
    val content = healthData[selectedCategory] ?: healthData["Diabetes"]!!

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(IvoryBg).statusBarsPadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(Modifier.height(8.dp)) }

        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(38.dp).clip(CircleShape).background(ChipBg).clickable { navController.popBackStack() }, Alignment.Center) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = MossGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Health Benefits", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Millet powered wellness insights", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        item {
            Card(Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(22.dp)), RoundedCornerShape(22.dp), CardDefaults.cardColors(WellnessBg)) {
                Row(Modifier.fillMaxWidth().padding(20.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(content.heroTitle,    style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold,     color = TextPrimary)
                        Spacer(Modifier.height(6.dp))
                        Text(content.heroSubtitle, style = MaterialTheme.typography.bodySmall,   color = TextSecondary)
                        Spacer(Modifier.height(14.dp))
                        Box(Modifier.clip(RoundedCornerShape(12.dp)).background(MossGreen).padding(horizontal = 14.dp, vertical = 7.dp)) {
                            Text(content.score, style = MaterialTheme.typography.labelSmall, color = CardWhite, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Spacer(Modifier.width(12.dp))
                    Box(Modifier.size(68.dp).clip(CircleShape).background(CardWhite.copy(alpha = 0.75f)), Alignment.Center) {
                        Icon(Icons.Default.Favorite, null, tint = MossGreen, modifier = Modifier.size(32.dp))
                    }
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(categories) { cat ->
                    val sel = cat == selectedCategory
                    Box(
                        Modifier.clip(RoundedCornerShape(18.dp)).background(if (sel) MossGreen else ChipBg)
                            .clickable { selectedCategory = cat }.padding(horizontal = 18.dp, vertical = 10.dp)
                    ) {
                        Text(cat, style = MaterialTheme.typography.labelMedium, color = if (sel) CardWhite else SoftGreen,
                            fontWeight = if (sel) FontWeight.SemiBold else FontWeight.Normal)
                    }
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                content.benefits.forEach { b ->
                    BenefitCard(b.title, b.subtitle, b.tag, b.icon)
                }
            }
        }

        item {
            Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(CardWhite)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Doctor Recommendation", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Spacer(Modifier.height(8.dp))
                    Text(content.doctorNote, style = MaterialTheme.typography.bodySmall, color = TextSecondary, lineHeight = 20.sp)
                }
            }
        }

        item {
            Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(TipBg)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Daily Wellness Tip", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = MossGreen)
                    Spacer(Modifier.height(8.dp))
                    Text(content.dailyTip, style = MaterialTheme.typography.bodySmall, color = TextSecondary, lineHeight = 20.sp)
                }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
        item { Spacer(Modifier.height(8.dp)); BottomNavBar(navController) }
    }
}

@Composable
fun BenefitCard(title: String, subtitle: String, tag: String, icon: ImageVector) {
    Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)), RoundedCornerShape(18.dp), CardDefaults.cardColors(CardWhite)) {
        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(ChipBg), Alignment.Center) {
                Icon(icon, null, tint = MossGreen, modifier = Modifier.size(24.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title,    style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Spacer(Modifier.height(3.dp))
                Text(subtitle, style = MaterialTheme.typography.bodySmall,  color = TextSecondary)
                Spacer(Modifier.height(8.dp))
                Box(Modifier.clip(RoundedCornerShape(10.dp)).background(ChipBg).padding(horizontal = 10.dp, vertical = 4.dp)) {
                    Text(tag, style = MaterialTheme.typography.labelSmall, color = MossGreen, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}