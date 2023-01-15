package com.geektech.marvelapp.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.remote.model.FilmModel
import com.geektech.marvelapp.data.remote.ImbdApi
import com.geektech.marvelapp.data.remote.model.ActorModel
import com.geektech.marvelapp.data.remote.model.CastModel
import com.geektech.marvelapp.data.remote.model.ResultsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val imbdpApi: ImbdApi) {
    fun getFilmModel(title: String): MutableLiveData<FilmModel> {
        val liveFilm = MutableLiveData<FilmModel>()
        imbdpApi.findFilmModel(title).enqueue(object : Callback<FilmModel> {
            override fun onResponse(call: Call<FilmModel>, response: Response<FilmModel>) {
                if (response.isSuccessful) {
                    liveFilm.postValue(filmModelSort(response.body()!!))
                }
            }
            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                Log.e("aga", "Repository/getFilmModel: ${t.message}")
            }
        })
        return liveFilm
    }

    //sort remote data
    private fun filmModelSort(dataFilmModel: FilmModel): FilmModel {
        val result = ArrayList<ResultsModel>()
        for (i in dataFilmModel.results) {
            if (i.image != null && i.filmID != null && i.title != null) {
                i.filmID = cutId(i.filmID!!)
                result.add(i)
            }
        }
        return FilmModel(result)
    }

    // cut film id
    // it is to search top cast
    private fun cutId(newId: String): String {
        return newId.removePrefix("/title/").removeSuffix("/")
    }

    fun getCastModel(filmId: String): MutableLiveData<CastModel> {
        val castList = MutableLiveData<CastModel>()
        imbdpApi.findCastList(filmId).enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
                val data = response.body()
                castList.value = data?.let { CastModel(it) }
            }
            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Log.e("aga", "Repository/getCastModel: ${t.message}")
            }
        })
        return castList
    }

    fun getActorModel(actorId: String) {
        var actorModel = ActorModel()
        imbdpApi.findActorModel(actorId).enqueue(object : Callback<ActorModel> {
            override fun onResponse(call: Call<ActorModel>, response: Response<ActorModel>) {
                actorModel = response.body()!!
                Log.e("aga", "actor model:" + response.body())
            }

            override fun onFailure(call: Call<ActorModel>, t: Throwable) {
                Log.e("aga", "Repository/getActorModel: ${t.message}")
            }
        })
//        return actorModel
    }


}


