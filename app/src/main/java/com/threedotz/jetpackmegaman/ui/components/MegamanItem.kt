package com.threedotz.jetpackmegaman.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.threedotz.jetpackmegaman.ui.theme.JetpackMegamanTheme

@Composable
fun MegamanItem(
    name: String,
    year: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(50.dp)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
            Text(
                text = year,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun MegamanItemPreview() {
    JetpackMegamanTheme {
        MegamanItem(
            "Megaman X 1",
            "1998",
            "https://static.wikia.nocookie.net/capcomdatabase/images/a/a9/MMXCMLogo.png/revision/latest?cb=20110110230939"
        )
    }
}