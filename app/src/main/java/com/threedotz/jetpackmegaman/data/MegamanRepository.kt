package com.threedotz.jetpackmegaman.data

import com.threedotz.jetpackmegaman.model.Megaman
import com.threedotz.jetpackmegaman.model.MegamanData
import kotlinx.coroutines.flow.*


private val listMegaman = mutableListOf<Megaman>()

class MegamanRepository {

    init {
        if (listMegaman.isEmpty()) {
            MegamanData.megaman.forEach {
                listMegaman.add(
                    Megaman(
                        it.id,
                        it.title,
                        it.description,
                        it.year,
                        it.photo,
                        isFavorite = false
                    )
                )
            }
        }
    }

    fun getMegaman(): Flow<List<Megaman>> {
        return flowOf(listMegaman)
    }


    fun getMegamanById(megamanId: Long): Megaman {
        return listMegaman.first {
            it.id == megamanId
        }
    }

    fun updateFavorite(megamanId: Long,isFavorite: Boolean): Flow<Boolean> {
        val index = listMegaman.indexOfFirst { it.id == megamanId }
        val result = if (index >= 0) {
            val megaman = listMegaman[index]
            listMegaman[index] = megaman.copy(id = megaman.id, isFavorite = isFavorite)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getMegamanFavorite(): Flow<List<Megaman>> {
        return getMegaman()
            .map { megaman ->
                megaman.filter { megaman ->
                    megaman.isFavorite
                }
            }
    }

    fun searchMegaman(query: String): Flow<List<Megaman>>{
        return getMegaman()
            .map { megaman ->
                megaman.filter {
                    it.title.contains(query,ignoreCase = true)
                }
            }
    }


    companion object {
        @Volatile
        private var instance: MegamanRepository? = null

        fun getInstance(): MegamanRepository =
            instance ?: synchronized(this) {
                MegamanRepository().apply {
                    instance = this
                }
            }
    }
}