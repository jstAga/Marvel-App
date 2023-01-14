package com.geektech.marvelapp.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImbdApi {

    @GET("title/v2/find")
    fun findFilmModel(
        @Query("title") title: String,
        @Query("titleType") titleType: String = "movie|short",
        @Query("limit") limit: Int = 20,
        @Query("keyword") keyword: String = "marvel",
        @Header("X-RapidAPI-Key") key: String = "522d966999msha190ba44d1b26c6p169d5cjsn8794069ff072",
        @Header("X-RapidAPI-Host") host: String = "imdb8.p.rapidapi.com",
    ): Call<FilmModel>
}