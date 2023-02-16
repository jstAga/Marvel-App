package com.geektech.marvelapp.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.local.room.ComicEntity
import com.geektech.marvelapp.ui.shop.repository.ShopRepository
import com.geektech.marvelapp.data.remote.model.marvel.ComicResponse
import com.geektech.marvelapp.core.network.result.Resource
import com.geektech.marvelapp.data.remote.model.marvel.Comic
import com.geektech.youtubeapi.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val repository: ShopRepository) : BaseViewModel() {
    var liveRemoteComics: LiveData<Resource<ComicResponse>>? = null
    var liveLocalComics: LiveData<List<ComicEntity>>? = null
    private var mutableIsComicsSaved: MutableLiveData<ComicEntity> = MutableLiveData<ComicEntity>()
    var liveIsComicsSaved: MutableLiveData<ComicEntity> = mutableIsComicsSaved

    fun getComics() {
        liveRemoteComics = repository.getComics()
    }

    fun insertComic(model: ComicEntity) {
        repository.insertComic(model)
    }

    fun deleteComic(model: ComicEntity) {
        repository.deleteComic(model)
    }

    fun getAllComic() {
        liveLocalComics = repository.getAllComic()
    }

    fun isComicsSaved(localComics: List<ComicEntity>, checkComic: Comic) {
        val result = MutableLiveData<ComicEntity>()
        if (localComics.isNotEmpty()) {
            for (localComic in localComics) {
                if (checkComic.id == localComic.id || checkComic.thumbnail?.path == localComic.path) {
                    result.value = localComic
                    break
                }
            }
        }
        mutableIsComicsSaved.value = result.value
    }
}