package com.geektech.marvelapp.data.remote

import com.geektech.marvelapp.BuildConfig
import com.geektech.marvelapp.data.remote.model.imbd.ActorModel
import com.geektech.marvelapp.data.remote.model.imbd.FilmModel
import com.geektech.marvelapp.data.remote.model.imbd.FilmDetailModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImdbApi {

    // RV FEED
    @GET("title/v2/find")
    fun getFilm(
        @Query("title") title: String,
        @Query("titleType") titleType: String = "movie",
        @Query("limit") limit: Int = 5,
        @Query("runtimeMin") runtimeMin: Int = 80,
        @Query("userRatingMin") userRatingMin: Int = 7,
        @Query("keyword") keyword: String = "marvel",
        @Query("primaryCountry") primaryCountry: String = "us",
        @Header("X-RapidAPI-Key") key: String = BuildConfig.IMDB_API_KEY,
        @Header("X-RapidAPI-Host") host: String = BuildConfig.IMDB_HOST,
    ): Call<FilmModel>

    @GET("title/get-overview-details")
    fun getDetail(
        @Query("tconst") filmId: String,
        @Query("currentCountry") currentCountry: String = "US",
        @Header("X-RapidAPI-Key") key: String = BuildConfig.IMDB_API_KEY,
        @Header("X-RapidAPI-Host") host: String = BuildConfig.IMDB_HOST,
    ) : Call<FilmDetailModel>

    //TOP CAST
    // return list of actors`s id
    @GET("title/get-top-cast")
     fun getCast(
        @Query("tconst") filmId: String,
        @Header("X-RapidAPI-Key") key: String = "000bf1ebb5msh8a7447e0afd8473p199aa9jsn7967e472ee5d",
        @Header("X-RapidAPI-Host") host: String = "imdb8.p.rapidapi.com",
    ): Call<ArrayList<String>>

    // return actor model
    @GET("actors/get-bio")
     fun getActor(
        @Query("nconst") actorId: String,
        @Header("X-RapidAPI-Key") key: String = "000bf1ebb5msh8a7447e0afd8473p199aa9jsn7967e472ee5d",
        @Header("X-RapidAPI-Host") host: String = "imdb8.p.rapidapi.com",
    ): Call<ActorModel>
}