package com.geektech.marvelapp.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ComicEntity(
    var title: String? = null,
    var path: String? = null,
    var extension: String? = null,
    var description: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    var isSaved : Boolean = false
) : java.io.Serializable


