package com.geektech.marvelapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ComicDao {

    @Insert
    fun insertComic(comic: ComicEntity)

    @Query("SELECT * FROM ComicEntity ORDER BY id ASC")
    fun getAllComic(): LiveData<List<ComicEntity>>

    @Delete
    fun deleteComic(comic: ComicEntity)
}