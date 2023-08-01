package com.threedotz.jetpackmegaman.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threedotz.jetpackmegaman.data.MegamanRepository
import com.threedotz.jetpackmegaman.model.Megaman
import com.threedotz.jetpackmegaman.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MegamanRepository): ViewModel() {
    private val _uiState:MutableStateFlow<UiState<Megaman>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Megaman>> get()= _uiState

    fun getMegamanById(megamanId: Long){
        viewModelScope.launch {
            _uiState.value =UiState.Loading
            _uiState.value =UiState.Success(repository.getMegamanById(megamanId))
        }
    }
    fun addToFavorite(megamanId: Long){
        viewModelScope.launch {
            repository.updateFavorite(megamanId,true)
        }
    }

}