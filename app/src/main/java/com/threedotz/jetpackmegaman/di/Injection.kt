package com.threedotz.jetpackmegaman.di

import com.threedotz.jetpackmegaman.data.MegamanRepository

object Injection {
    fun provideRepository():MegamanRepository{
        return MegamanRepository.getInstance()
    }
}