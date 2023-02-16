package com.geektech.marvelapp.data.remote.model.marvel

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ComicResponse(
    @SerializedName("data")
    val data: Data? = null
)

data class Data(
    var limit: Int,
    @SerializedName("results") var results: List<Comic>? = null,
)

data class Comic(
    @SerializedName("title") @Expose var title: String? = null,
    @SerializedName("thumbnail") @Expose var thumbnail: Thumbnail? = null,
    @SerializedName("description") @Expose var description: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id : Int
)

data class Thumbnail(
    @SerializedName("path") @Expose var path: String? = null,
    @SerializedName("extension") @Expose var extension: String? = null,
)