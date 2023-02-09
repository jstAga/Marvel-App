package com.geektech.marvelapp.ui.shop.repository

import androidx.lifecycle.MutableLiveData
import com.geektech.marvelapp.data.remote.MarvelApi
import com.geektech.marveltest2.model.ComicResponse
import com.geektech.youtubeapi.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ShopRepository @Inject constructor(private val marvelApi: MarvelApi) {

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
}