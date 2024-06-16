package com.example.appclubdeportivo.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.appclubdeportivo.R

data class PersonalizedText(val text: String, val color: Color= Color.Black, val backgroundColor: Color? = null)

// Composable para las tarjetas personalizadas de Cliente y Empleado, soporta hasta 6 campos y el diseño está distribuido de acuerdo al Figma
// Se puede personalizar más cada campo con la data class PersonalizedText
@Composable
fun GenericCard(
    field1: PersonalizedText,
    field2: PersonalizedText,
    field3: PersonalizedText,
    field4: PersonalizedText,
    field5: PersonalizedText,
    field6: PersonalizedText,
    onEditClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) { Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF76ABAE).copy(alpha = 0.8f),
                        Color(0xFF76ABAE).copy(alpha = 0.6f),
                        Color(0xFFF5F5F5).copy(alpha = 1f),
                    )
                )
            )
            .padding(16.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(field1.text, style = MaterialTheme.typography.bodyMedium, color = field1.color, modifier = Modifier.background(color = field1.backgroundColor ?: Color.Transparent))
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.edit_24px),
                        contentDescription = "Edit Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(field2.text, style = MaterialTheme.typography.bodyMedium, color = field2.color, modifier = Modifier.background(color = field2.backgroundColor ?: Color.Transparent))
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(field3.text, style = MaterialTheme.typography.bodyMedium, color = field3.color, modifier = Modifier.background(color = field3.backgroundColor ?: Color.Transparent))
                Text(field4.text, style = MaterialTheme.typography.bodyMedium, color = field4.color, modifier = Modifier.background(color = field4.backgroundColor ?: Color.Transparent))
            }
            Text(field5.text, style = MaterialTheme.typography.bodySmall)
            Text(field6.text, style = MaterialTheme.typography.bodySmall)

        }
    }
    }
}
