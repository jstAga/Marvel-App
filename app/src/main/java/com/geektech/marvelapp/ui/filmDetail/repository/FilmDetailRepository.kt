package com.geektech.marvelapp.ui.filmDetail.repository

import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.remote.ImdbApi
import com.geektech.marvelapp.data.remote.model.imbd.ActorModel
import com.geektech.marvelapp.data.remote.model.imbd.FilmDetailModel
import com.geektech.marvelapp.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FilmDetailRepository @Inject constructor(private val imdbApi: ImdbApi) {

    fun getDetail(filmId: String): MutableLiveData<Resource<FilmDetailModel>> {
        val data = MutableLiveData<Resource<FilmDetailModel>>()
        data.value = Resource.loading()
        imdbApi.getDetail(filmId).enqueue(object : Callback<FilmDetailModel> {
            override fun onResponse(
                call: Call<FilmDetailModel>,
                response: Response<FilmDetailModel>
            ) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<FilmDetailModel>, t: Throwable) {
                data.value = Resource.error(t.stackTrace.toString(), null, null)
            }
        })
        return data
    }

    fun getCast(filmId: String): MutableLiveData<Resource<ArrayList<String>>> {
        val data = MutableLiveData<Resource<ArrayList<String>>>()
        data.value = Resource.loading()
        imdbApi.getCast(filmId).enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body()?.let { cutActorsId(it) })
                }
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                data.value = Resource.error(t.stackTrace.toString(), null, null)
            }
        })
        return data
    }

    fun cutActorsId(data: ArrayList<String>): ArrayList<String> {
        val result = arrayListOf<String>()
        if (data.size >= 10) {
            for (i in 0..10) {
                result.add(data[i].removePrefix("/name/").removeSuffix("/"))
            }
        } else {
            for (i in 0..data.size) {
                result.add(data[i].removePrefix("/name/").removeSuffix("/"))
            }
        }
        return result
    }

    fun getActor(actorId: String): MutableLiveData<Resource<ActorModel>> {
        val data = MutableLiveData<Resource<ActorModel>>()
        data.value = Resource.loading()
        imdbApi.getActor(actorId).enqueue(object : Callback<ActorModel> {
            override fun onResponse(call: Call<ActorModel>, response: Response<ActorModel>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<ActorModel>, t: Throwable) {
                data.value = Resource.error(t.stackTrace.toString(), null, null)
            }
        })
        return data
    }
}