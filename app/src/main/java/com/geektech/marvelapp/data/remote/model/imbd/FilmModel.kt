package com.geektech.marvelapp.data.remote.model.imbd

import com.google.gson.annotations.SerializedName

data class FilmModel (
    @SerializedName("results") var results : ArrayList<ResultsModel>
    )

data class ResultsModel(
    @SerializedName("id") var filmID : String? = null,      //film id
    @SerializedName("image") val image : ImageModel? = null,
    @SerializedName("title") val title : String? = null,
)

data class ImageModel(
    @SerializedName("url") val url : String
)