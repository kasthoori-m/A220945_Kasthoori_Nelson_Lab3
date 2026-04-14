package com.example.a220945_kasthoori_nelson_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.a220945_kasthoori_nelson_lab3.ui.theme.EduQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Applies the custom Material Design theme
            EduQuestTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    EduQuestLab3()
                }
            }
        }
    }
}

@Composable
fun EduQuestLab3() {
    // --- STATE MANAGEMENT ---
    var nameInput by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("User") }
    var progressInput by remember { mutableStateOf("") }
    var progressPercentage by remember { mutableFloatStateOf(0.0f) }

    // THE MAIN COLUMN
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 8.dp) // Adjusted padding
            .verticalScroll(rememberScrollState()) // THIS MAKES IT SCROLLABLE!
    ) {

        // PUSHES THE HEADER DOWN A BIT
        Spacer(modifier = Modifier.height(32.dp))

        // 1. HEADER (Upgraded to Two-Tone Dashboard Style)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    // Text is now white (onPrimary) so it pops against the navy blue
                    Text(text = "Welcome back,", fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
                    Text(text = displayName, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onPrimary)
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                        contentDescription = "Profile",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. INPUT CARD (Using Material Card)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Update Profile & Progress", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(12.dp))

                // Inside the Update Profile Card:
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("User Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp) // Added here!
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = progressInput,
                    onValueChange = { progressInput = it },
                    label = { Text("Progress % (0-100)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp) // Added here!
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (nameInput.isNotEmpty()) displayName = nameInput
                        val p = progressInput.toFloatOrNull()
                        if (p != null) progressPercentage = (p / 100f).coerceIn(0f, 1f)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Refresh Dashboard")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. COURSE PROGRESS SECTION
        Text(text = "Course Progress", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Software Requirements Engineering", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(12.dp))

                // The Track
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(Color(0xFFE2E8F0), shape = RoundedCornerShape(4.dp))
                ) {
                    // The Dynamic Fill
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progressPercentage)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
                    )
                }
                Text(
                    text = "${(progressPercentage * 100).toInt()}% Mastered",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. EXPLORE QUESTS (Animated Cards with Theme Colors)
        Text(text = "Explore Quests (Tap to Expand)", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(12.dp))

        // Row 1
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            AnimatedSubjectCard(
                "Math", "Algebra", "Master linear equations, graphing, and geometry.",
                MaterialTheme.colorScheme.primary, // Uses your custom Navy Blue
                Color.White,
                Modifier.weight(1f)
            )
            AnimatedSubjectCard(
                "Science", "Physics", "Explore Newton's laws, thermodynamics, and the physical world.",
                MaterialTheme.colorScheme.secondary, // Uses your custom Teal
                Color.White,
                Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 2
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            AnimatedSubjectCard(
                "Computing", "Java", "Learn object-oriented programming and software design.",
                MaterialTheme.colorScheme.secondary, // Uses your custom Teal
                Color.White,
                Modifier.weight(1f)
            )
            AnimatedSubjectCard(
                "Ethics", "Society", "Understand the impact of technology on society and ethics.",
                MaterialTheme.colorScheme.primary, // Uses your custom Navy Blue
                Color.White,
                Modifier.weight(1f)
            )
        }
    }
}

// TASK 3: EXPANDABLE ANIMATED CARD
@Composable
fun AnimatedSubjectCard(title: String, subtitle: String, details: String, bg: Color, txt: Color, modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(24.dp), // Bubbly corners
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp, // Higher shadow
            pressedElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(containerColor = bg)
    ) {
        // Everything go inside this 1 Column
        Column(modifier = Modifier.padding(16.dp)) {

            // The Icon and Title Row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                    contentDescription = null,
                    tint = txt,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, color = txt, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // The Subtitle
            Text(
                text = subtitle,
                color = txt,
                fontSize = 14.sp,
                lineHeight = 16.sp // Added line height to match details
            )

            // The Expanding Details
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = details, color = txt, fontSize = 13.sp, lineHeight = 16.sp)
            }
        }
    }
}
