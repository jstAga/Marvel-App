package com.geektech.marvelapp.ui.filmDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.marvelapp.databinding.FragmentFilmDetailBinding
import com.geektech.marvelapp.ui.filmDetail.adapter.CastAdapter


class FilmDetailFragment : Fragment() {
    private lateinit var binding: FragmentFilmDetailBinding
    private lateinit var adapter: CastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = CastAdapter()
        binding.rvCast.adapter = adapter
    }

}