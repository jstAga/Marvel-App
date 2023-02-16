package com.geektech.marvelapp.data.remote

import com.geektech.marvelapp.utils.Constants
import com.geektech.marvelapp.data.remote.model.marvel.ComicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/comics")
    fun getComics(
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("apikey") apikey: String = "3e794e5ed62fd1f9a317dde6718e63da",
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit: String = Constants.LIMIT,
    ): Call<ComicResponse>
}