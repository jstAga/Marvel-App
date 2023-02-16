package com.geektech.marvelapp.ui.shop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.local.room.ComicDao
import com.geektech.marvelapp.data.local.room.ComicEntity
import com.geektech.marvelapp.data.remote.MarvelApi
import com.geektech.marvelapp.data.remote.model.marvel.ComicResponse
import com.geektech.marvelapp.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ShopRepository @Inject constructor(
    private val marvelApi: MarvelApi,
    private val comicDao: ComicDao
) {

    fun getComics(): MutableLiveData<Resource<ComicResponse>> {
        val data = MutableLiveData<Resource<ComicResponse>>()
        data.value = Resource.loading()
        marvelApi.getComics().enqueue(object : Callback<ComicResponse> {
            override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                data.value = Resource.error(t.message.toString(), null, null)
            }
        })
        return data
    }

    fun getAllComic() : LiveData<List<ComicEntity>> {
        return comicDao.getAllComic()
    }

    fun insertComic(model: ComicEntity) {
        comicDao.insertComic(model)
    }

    fun deleteComic(model: ComicEntity){
        comicDao.deleteComic(model)
    }
}