package com.geektech.marvelapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geektech.marvelapp.data.remote.model.CastModel
import com.geektech.marvelapp.data.remote.model.FilmModel
import com.geektech.marvelapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    lateinit var liveFilmData: LiveData<FilmModel>
    var MutableActorsId: MutableLiveData<CastModel> = MutableLiveData<CastModel>()
    var liveActorsId: LiveData<CastModel> = MutableActorsId

    fun getFilmModel(title: String) {
        liveFilmData = repository.getFilmModel(title)
    }

    fun getCastModel(filmId: String) {
        val data = cutActorsId(repository.getCastModel(filmId))
        MutableActorsId = (repository.getCastModel(filmId))
//        MutableActorsId.postValue(CastModel(repository.getCastModel(title)))
        liveActorsId = MutableActorsId
    }

    private fun cutActorsId(model: MutableLiveData<CastModel>) : MutableLiveData<CastModel>{
        val data = model.value?.data
        val result = MutableLiveData<CastModel>()
        if (data != null) {
            for ((index, value) in data.withIndex()) {
                data[index] = value.removePrefix("/name/").removeSuffix("/")
            }
         result.postValue(CastModel(data))
        }
        return result
    }
}


