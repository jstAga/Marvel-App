package com.geektech.marvelapp.ui.filmDetail

import androidx.lifecycle.LiveData
import com.geektech.marvelapp.data.remote.model.imbd.ActorModel
import com.geektech.marvelapp.data.remote.model.imbd.FilmDetailModel
import com.geektech.marvelapp.ui.filmDetail.repository.FilmDetailRepository
import com.geektech.marvelapp.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(private val repository: FilmDetailRepository) :
    BaseViewModel() {
    lateinit var liveFilmDetail: LiveData<Resource<FilmDetailModel>>
    lateinit var liveCast: LiveData<Resource<ArrayList<String>>>
    lateinit var liveActor: LiveData<Resource<ActorModel>>

    fun getDetail(filmId: String) {
        liveFilmDetail = repository.getDetail(filmId)
    }

    fun getCast(filmId: String) {
        liveCast = repository.getCast(filmId)
    }

    fun getActor(actorId: String) {
        liveActor = repository.getActor(actorId)
    }
}