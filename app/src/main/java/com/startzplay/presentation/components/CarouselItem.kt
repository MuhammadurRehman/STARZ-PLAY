package com.startzplay.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.startzplay.presentation.models.Movie

@Composable
fun CarouselItem(title: String, items: List<Movie>, onclicked: (movie:Movie) -> Unit) {

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(title, style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            for (item in items.sortedBy { it.name }) {
                Box(
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .background(Color.Black)
                        .clickable {
                            onclicked(item)
                        }
                ){
                    LoadImage(url = item.imageUrl, modifier = Modifier.fillMaxSize().align(Alignment.Center))
                }
            }
        }
    }

}