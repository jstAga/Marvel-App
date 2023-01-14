package com.geektech.marvelapp.data.remote

import com.google.gson.annotations.SerializedName

data class FilmModel (
    @SerializedName("results") var results : List<ResultsModel>,
    )

data class ResultsModel(
    @SerializedName("id") var id : String,
    @SerializedName("image") var image : ImageModel,
    @SerializedName("title") var title : String,
)

data class ImageModel(
    @SerializedName("url") var url : String
)
