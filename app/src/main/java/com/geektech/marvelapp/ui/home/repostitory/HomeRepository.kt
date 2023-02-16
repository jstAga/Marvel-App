package com.geektech.marvelapp.ui.home.repostitory

import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.remote.ImdbApi
import com.geektech.marvelapp.data.remote.model.imbd.FilmModel
import com.geektech.marvelapp.data.remote.model.imbd.ResultsModel
import com.geektech.marvelapp.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val imbdpApi: ImdbApi) {

    fun getFilm(title: String): MutableLiveData<Resource<FilmModel>> {
        val data = MutableLiveData<Resource<FilmModel>>()
        data.value = Resource.loading()
        imbdpApi.getFilm(title).enqueue(object : Callback<FilmModel> {
            override fun onResponse(call: Call<FilmModel>, response: Response<FilmModel>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body()?.let { filmModelSort(it) })
                }
            }

            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                data.value = Resource.error(t.stackTrace.toString(), null, null)
            }
        })
        return data
    }

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
}