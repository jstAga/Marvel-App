package com.geektech.marvelapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geektech.marvelapp.data.remote.FilmModel
import com.geektech.marvelapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    lateinit var filmData: LiveData<FilmModel>

    fun getFilmModel(title: String) {
        filmData = repository.getFilmModel(title)

    }
}