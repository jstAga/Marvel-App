package com.geektech.marvelapp.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.remote.FilmModel
import com.geektech.marvelapp.data.remote.ImbdApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val imbdpApi : ImbdApi) {
    fun getFilmModel(title:String): MutableLiveData<FilmModel> {
        val liveFilm = MutableLiveData<FilmModel>()
        imbdpApi.findFilmModel(title).enqueue(object :Callback<FilmModel>{
            override fun onResponse(call: Call<FilmModel>, response: Response<FilmModel>) {
                if (response.isSuccessful){
                    liveFilm.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                Log.e("aga", "onFailure: ${t.message}" )
            }

        })
        return liveFilm
    }
}
