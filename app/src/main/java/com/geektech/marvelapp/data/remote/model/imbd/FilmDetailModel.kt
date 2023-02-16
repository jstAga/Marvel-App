package com.geektech.marvelapp.data.remote.model.imbd

import com.google.gson.annotations.SerializedName

data class FilmDetailModel (
    @SerializedName("id") var id : String ,
    @SerializedName("title") var title : Title,
    @SerializedName("plotSummary") var plotSummary : PlotSummary
)

data class Image (
    @SerializedName("url") var url : String
)

data class Title (
    @SerializedName("image") var image : Image,
    @SerializedName("title") var title : String,
    @SerializedName("year") var year : Int
)

data class PlotSummary (
    @SerializedName("text") var text : String
)