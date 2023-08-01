package com.threedotz.jetpackmegaman.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.threedotz.jetpackmegaman.data.MegamanRepository
import com.threedotz.jetpackmegaman.ui.screen.detail.DetailViewModel
import com.threedotz.jetpackmegaman.ui.screen.favorite.FavoriteViewModel
import com.threedotz.jetpackmegaman.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MegamanRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}