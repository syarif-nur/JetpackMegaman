package com.threedotz.jetpackmegaman.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.threedotz.jetpackmegaman.R
import com.threedotz.jetpackmegaman.di.Injection
import com.threedotz.jetpackmegaman.ui.ViewModelFactory
import com.threedotz.jetpackmegaman.ui.common.UiState
import com.threedotz.jetpackmegaman.ui.components.FavoriteButton
import com.threedotz.jetpackmegaman.ui.theme.JetpackMegamanTheme

@Composable
fun DetailScreen(
    megamanId: Long,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateBack: () -> Unit,
    navigateToFavorite: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            UiState.Loading -> {
                viewModel.getMegamanById(megamanId)
            }
            is UiState.Success -> {
                val data = uiState.data
                val context = LocalContext.current
                DetailContent(
                    image = data.photo,
                    title = data.title,
                    year = data.year,
                    description = data.description,
                    onBackClick = navigateBack,
                    onAddToFavorite = {
                        viewModel.addToFavorite(data.id)
                        Toast.makeText(context,"Berhasil Ditambahkan ke Favorite",Toast.LENGTH_SHORT).show()
                        navigateToFavorite()
                    }
                )
            }
            is UiState.Error -> {
            }
        }
    }
}


@Composable
fun DetailContent(
    image: String,
    title: String,
    year: String,
    description: String,
    onBackClick: () -> Unit,
    onAddToFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = year,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.secondary
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            FavoriteButton(
                text = stringResource(R.string.add_to_favorite),
                onClick = {
                    onAddToFavorite()
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetpackMegamanTheme {
        DetailContent(
            image = "null",
            "Megaman X",
            "1995",
            "haloo",
            onBackClick = {},
            onAddToFavorite = {}
        )
    }
}
