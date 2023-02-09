package com.geektech.marvelapp.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geektech.marvelapp.ui.shop.repository.ShopRepository
import com.geektech.marveltest2.model.ComicResponse
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val repository: ShopRepository) : BaseViewModel(){

    lateinit var liveComicResponse: LiveData<Resource<ComicResponse>>

    fun getComics() {
        liveComicResponse = repository.getComics()
    }
}