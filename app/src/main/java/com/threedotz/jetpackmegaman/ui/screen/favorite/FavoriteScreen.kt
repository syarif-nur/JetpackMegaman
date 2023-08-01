package com.threedotz.jetpackmegaman.ui.screen.favorite

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.threedotz.jetpackmegaman.di.Injection
import com.threedotz.jetpackmegaman.model.Megaman
import com.threedotz.jetpackmegaman.ui.ViewModelFactory
import com.threedotz.jetpackmegaman.ui.common.UiState
import com.threedotz.jetpackmegaman.ui.components.FavoriteItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMegamanFavorite()
            }
            is UiState.Success -> {
                val context = LocalContext.current
                FavoriteContent(
                    listMegaman = uiState.data,
                    modifier = modifier,
                    onRemoveFromFavorite = { megamanId, isFavorite ->
                        viewModel.updateFavoriteScreen(megamanId,isFavorite)
                        Toast.makeText(context,"Data Berhasil DIhapus dari Favorite",Toast.LENGTH_SHORT).show()
                    }
                )
            }
            is UiState.Error -> {

            }
        }

    }
}
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    listMegaman: List<Megaman>,
    onRemoveFromFavorite: (id: Long, isFavorite: Boolean)-> Unit
) {
    LazyColumn(modifier = modifier) {
        items(listMegaman) { data ->
            FavoriteItem(
                data.id,
                name = data.title,
                year = data.year,
                photoUrl = data.photo,
                modifier = modifier,
                onRemoveFromFavorite ={
                    onRemoveFromFavorite(data.id,false)
                }
            )
            Divider()
        }
    }
}