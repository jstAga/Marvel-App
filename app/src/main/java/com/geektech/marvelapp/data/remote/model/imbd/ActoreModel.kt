package com.geektech.marvelapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ActorModel(
 @SerializedName("image") val image : Image? = null,
 @SerializedName("name") val name : String? = null,

)
data class Image (
 @SerializedName("url") val url : String ,
)
