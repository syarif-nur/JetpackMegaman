package com.threedotz.jetpackmegaman.model

data class Megaman(
    val id: Long,
    val title: String,
    val description: String,
    val year: String,
    val photo: String,
    val isFavorite: Boolean = false
)