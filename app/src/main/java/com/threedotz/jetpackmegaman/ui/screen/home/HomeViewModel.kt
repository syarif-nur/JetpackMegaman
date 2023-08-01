package com.threedotz.jetpackmegaman.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threedotz.jetpackmegaman.data.MegamanRepository
import com.threedotz.jetpackmegaman.model.Megaman
import com.threedotz.jetpackmegaman.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MegamanRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Megaman>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<Megaman>>> get() = _uiState

    fun getAllMegaman(){
        viewModelScope.launch {
            repository.getMegaman().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
                .collect{ listMegaman ->
                    _uiState.value = UiState.Success(listMegaman)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String){
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchMegaman(newQuery).catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
                .collect{listMegaman ->
                    _uiState.value = UiState.Success(listMegaman)
                }
        }
    }
}