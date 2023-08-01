package com.threedotz.jetpackmegaman.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.threedotz.jetpackmegaman.ui.theme.JetpackMegamanTheme

@Composable
fun FavoriteButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
    }
}

@Composable
@Preview(showBackground = true)
fun OrderButtonPreview() {
    JetpackMegamanTheme() {
        FavoriteButton(
            text = "Add To Favorite",
            onClick = {}
        )
    }
}