package com.geektech.marvelapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ComicEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun getDao() : ComicDao
}