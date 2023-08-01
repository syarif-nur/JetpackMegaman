package com.threedotz.jetpackmegaman.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.threedotz.jetpackmegaman.di.Injection
import com.threedotz.jetpackmegaman.model.Megaman
import com.threedotz.jetpackmegaman.ui.ViewModelFactory
import com.threedotz.jetpackmegaman.ui.common.UiState
import com.threedotz.jetpackmegaman.ui.components.MegamanItem
import com.threedotz.jetpackmegaman.ui.components.SearchBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.threedotz.jetpackmegaman.ui.components.ScrollToTopButton
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMegaman()
            }
            is UiState.Success -> {
                val query by viewModel.query
                HomeContent(
                    listMegaman = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::search
                )
            }
            is UiState.Error -> {}
        }

    }
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    listMegaman: List<Megaman>,
    navigateToDetail: (Long) -> Unit,
    onQueryChange: (String) -> Unit,
    query: String,
) {
    Box(modifier = modifier){
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(modifier = modifier, state = listState) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = onQueryChange,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            items(listMegaman) { data ->
                MegamanItem(
                    name = data.title,
                    year = data.year,
                    photoUrl = data.photo,
                    modifier = Modifier.clickable {
                        navigateToDetail(
                            data.id
                        )
                    }
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp, end = 20.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch { listState.scrollToItem(index = 0) }
            })
        }
    }
}
