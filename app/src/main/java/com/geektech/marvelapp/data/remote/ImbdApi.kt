package com.geektech.marvelapp.data.remote

import com.geektech.marvelapp.data.remote.model.ActorModel
import com.geektech.marvelapp.data.remote.model.FilmModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImbdApi {

    // RV FEED

    @GET("title/v2/find")
    fun findFilmModel(
        @Query("title") title: String,
        @Query("titleType") titleType: String = "movie|short",
        @Query("limit") limit: Int = 20,
        @Query("keyword") keyword: String = "marvel",
        @Header("X-RapidAPI-Key") key: String = "522d966999msha190ba44d1b26c6p169d5cjsn8794069ff072",
        @Header("X-RapidAPI-Host") host: String = "imdb8.p.rapidapi.com",
    ): Call<FilmModel>


    //TOP CAST

    // return list of actors`s id
    @GET("title/get-top-cast")
    fun findCastList(
        @Query("tconst") filmId: String,
        @Header("X-RapidAPI-Key") key: String = "522d966999msha190ba44d1b26c6p169d5cjsn8794069ff072",
        @Header("X-RapidAPI-Host") host: String = "imdb8.p.rapidapi.com",
    ): Call<ArrayList<String>>

    // return actor model
    @GET("actors/get-bio")
    fun findActorModel(
        @Query("nconst") actorId: String,
        @Header("X-RapidAPI-Key") key: String = "522d966999msha190ba44d1b26c6p169d5cjsn8794069ff072",
        @Header("X-RapidAPI-Host") host: String = "imdb8.p.rapidapi.com",
    ): Call<ActorModel>
}