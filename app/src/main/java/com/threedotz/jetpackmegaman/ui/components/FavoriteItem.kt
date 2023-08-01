package com.threedotz.jetpackmegaman.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
fun FavoriteItem(
    megamanId: Long,
    name: String,
    year: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    onRemoveFromFavorite: (id: Long) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
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
                    .width(200.dp)
            )
            Text(
                text = year,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .width(200.dp)
            )
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    onRemoveFromFavorite(megamanId)
                }
        )
    }
}

@Preview
@Composable
fun FavoritePreview() {
    JetpackMegamanTheme() {
        FavoriteItem(
            5,
            name = "test",
            year = "1998",
            photoUrl = "null",
            onRemoveFromFavorite = {}
        )
    }
}