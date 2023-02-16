package com.geektech.marvelapp.ui.comicBook

import androidx.lifecycle.LiveData
import com.geektech.marvelapp.data.local.room.ComicEntity
import com.geektech.marvelapp.ui.comicBook.repository.ComicRepository
import com.geektech.youtubeapi.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val repository: ComicRepository): BaseViewModel() {
    lateinit var liveComic : LiveData<List<ComicEntity>>

    fun getAllComic() {
        liveComic = repository.getAllComic()
    }

    fun deleteComic(model: ComicEntity) {
        repository.deleteComic(model)
    }
}