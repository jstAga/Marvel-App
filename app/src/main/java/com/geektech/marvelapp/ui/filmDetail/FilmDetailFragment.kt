package com.geektech.marvelapp.ui.filmDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.marvelapp.databinding.FragmentFilmDetailBinding
import com.geektech.marvelapp.ui.filmDetail.adapter.CastAdapter


class FilmDetailFragment : BaseFragment<FragmentFilmDetailBinding, FilmDetailViewModel>() {
    override fun getViewModelClass(): Class<FilmDetailViewModel> = FilmDetailViewModel::class.java
    override fun getViewBinding(): FragmentFilmDetailBinding =
        FragmentFilmDetailBinding.inflate(layoutInflater)

    private val adapter by lazy { CastAdapter() }

    override fun initViews() {
        super.initViews()
        binding.rvCast.adapter = adapter
    }


}