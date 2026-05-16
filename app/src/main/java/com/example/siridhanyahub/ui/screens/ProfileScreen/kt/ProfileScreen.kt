package com.example.siridhanyahub.ui.screens.ProfileScreen.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.siridhanyahub.ui.components.BottomNavBar

private val IvoryBg       = Color(0xFFF5F2EC)
private val CardWhite     = Color(0xFFFFFFFF)
private val MossGreen     = Color(0xFF2D5A27)
private val SoftGreen     = Color(0xFF4A7C40)
private val AccentGreen   = Color(0xFF7FB069)
private val ChipBg        = Color(0xFFEAF2E8)
private val DividerColor  = Color(0xFFE8E4DC)
private val TextPrimary   = Color(0xFF1C2B1A)
private val TextSecondary = Color(0xFF6B7C6A)

@Composable
fun ProfileScreen(navController: NavHostController) {

    var notificationsEnabled by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(IvoryBg).statusBarsPadding().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // ── Top App Bar ─────────────────────────────────────────────────────
        item {
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(40.dp).clip(CircleShape).background(MossGreen)
                        .clickable { navController.popBackStack() },
                    Alignment.Center
                ) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = Color.White, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Text("Siri-Dhanya Hub", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Settings, "Settings", tint = TextSecondary, modifier = Modifier.size(22.dp))
                }
            }
        }

        // ── Profile Avatar ───────────────────────────────────────────────────
        item {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier.size(110.dp).clip(CircleShape)
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.radialGradient(listOf(AccentGreen, MossGreen))
                        )
                        .border(4.dp, CardWhite, CircleShape)
                        .shadow(8.dp, CircleShape),
                    Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(56.dp))
                }
                Spacer(Modifier.height(14.dp))
                Text("Karthik", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = SoftGreen, letterSpacing = 0.5.sp)
                Spacer(Modifier.height(4.dp))
                Text("Millet Farmer · Karnataka", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            }
            Spacer(Modifier.height(22.dp))
        }

        // ── Stats Row ────────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("5" to "Saved", "3" to "Markets", "2" to "Farmers").forEach { (num, label) ->
                    Card(Modifier.weight(1f).shadow(4.dp, RoundedCornerShape(16.dp)), RoundedCornerShape(16.dp), CardDefaults.cardColors(CardWhite)) {
                        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(num,   style = MaterialTheme.typography.titleLarge,  fontWeight = FontWeight.Bold,     color = MossGreen)
                            Text(label, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        // ── About Card ────────────────────────────────────────────────────────
        item {
            Card(Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(CardWhite)) {
                Text(
                    "Siri-Dhanya Hub empowers millet farmers and helps families make healthier food choices through smart digital guidance.",
                    style = MaterialTheme.typography.bodyMedium, color = TextSecondary,
                    textAlign = TextAlign.Center, lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
        }

        // ── Settings Header ──────────────────────────────────────────────────
        item {
            Text("Settings & Preferences", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.8.sp, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(10.dp))
        }

        // ── Settings Card ─────────────────────────────────────────────────────
        item {
            Card(Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(CardWhite)) {
                Column {
                    SettingItem(Icons.Default.Bookmark, "Saved Recipes") {
                        navController.navigate("saved") { launchSingleTop = true }
                    }
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    SettingItem(Icons.Default.Notifications, "Notification Alerts",
                        trailingContent = {
                            Switch(
                                checked = notificationsEnabled,
                                onCheckedChange = { notificationsEnabled = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = MossGreen, checkedTrackColor = ChipBg)
                            )
                        }
                    ) {}
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    SettingItem(Icons.Default.Language, "Language Settings") {
                        navController.navigate("welcome") { launchSingleTop = true }
                    }
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    SettingItem(Icons.Default.Info, "About Millets") {
                        navController.navigate("health") { launchSingleTop = true }
                    }
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    SettingItem(Icons.Default.ContactSupport, "Contact Support") {}
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    SettingItem(Icons.Default.SystemUpdate, "App Version", trailingLabel = "v2.1.0", showChevron = false) {}
                }
            }
            Spacer(Modifier.height(22.dp))
        }

        // ── Appreciation Card ─────────────────────────────────────────────────
        item {
            Card(Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(20.dp)), RoundedCornerShape(20.dp), CardDefaults.cardColors(Color(0xFFF0EDE6))) {
                Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text("Made with love for Karnataka's Millet Ecosystem", style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(8.dp))
                    Text("❤️", fontSize = 18.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        item { Spacer(Modifier.height(8.dp)); BottomNavBar(navController) }
    }
}

@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    trailingLabel: String? = null,
    showChevron: Boolean = true,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(ChipBg), Alignment.Center) {
            Icon(icon, title, tint = MossGreen, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(14.dp))
        Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, color = TextPrimary, modifier = Modifier.weight(1f))
        when {
            trailingContent != null -> trailingContent()
            trailingLabel   != null -> Text(trailingLabel, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            showChevron             -> Icon(Icons.Default.ChevronRight, null, tint = TextSecondary, modifier = Modifier.size(18.dp))
        }
    }
}
