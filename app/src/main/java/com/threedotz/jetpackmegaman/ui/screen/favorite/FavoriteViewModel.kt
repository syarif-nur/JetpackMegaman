package com.threedotz.jetpackmegaman.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threedotz.jetpackmegaman.data.MegamanRepository
import com.threedotz.jetpackmegaman.model.Megaman
import com.threedotz.jetpackmegaman.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MegamanRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Megaman>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Megaman>>> get() = _uiState

    fun getMegamanFavorite(){
        viewModelScope.launch {
            repository.getMegamanFavorite().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
                .collect{ listMegaman ->
                    _uiState.value = UiState.Success(listMegaman)
                }
        }
    }

    fun updateFavoriteScreen(megamanId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateFavorite(megamanId,isFavorite)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getMegamanFavorite()
                    }
                }
        }
    }
}