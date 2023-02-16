package com.geektech.marvelapp.ui.comicBook.repository

import androidx.lifecycle.LiveData
import com.geektech.marvelapp.data.local.room.ComicDao
import com.geektech.marvelapp.data.local.room.ComicEntity
import javax.inject.Inject

class ComicRepository @Inject constructor(private val comicDao: ComicDao) {

    fun getAllComic() : LiveData<List<ComicEntity>> {
        return comicDao.getAllComic()
    }

    fun deleteComic(model: ComicEntity){
        comicDao.deleteComic(model)
    }
}