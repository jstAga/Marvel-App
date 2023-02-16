package com.geektech.marvelapp.ui.home

import androidx.lifecycle.LiveData
import com.geektech.marvelapp.data.remote.model.imbd.FilmModel
import com.geektech.marvelapp.ui.home.repostitory.HomeRepository
import com.geektech.marvelapp.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : BaseViewModel() {
    lateinit var liveFilmData: LiveData<Resource<FilmModel>>

    fun getFilm(title: String) {
        liveFilmData = repository.getFilm(title)
    }
}


