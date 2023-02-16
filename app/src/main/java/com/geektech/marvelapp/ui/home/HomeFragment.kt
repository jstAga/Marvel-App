package com.geektech.marvelapp.ui.home

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.geektech.marvelapp.R
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.marvelapp.databinding.FragmentHomeBinding
import com.geektech.marvelapp.ui.home.adapter.FeedAdapter
import com.geektech.marvelapp.utils.Constants.Companion.FILM_ID
import com.geektech.youtubeapi.core.network.result.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
    private val feedAdapter by lazy { FeedAdapter(this::onClick) }

    override fun initViews() {
        super.initViews()
        binding.rvFeed.adapter = feedAdapter
    }

    override fun initObserver() {
        super.initObserver()
        getFilm()
    }

    private val filmNames =
        arrayListOf("batman", " iron man", " captain america", "Deadpool", "hulk")

    private fun getFilm() {
        viewModel.getFilm(filmNames[0])
        viewModel.liveFilmData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.rvFeed.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { it1 -> feedAdapter.addData(it1) }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    binding.rvFeed.visibility = View.GONE
                }
            }
        }
    }

    private fun onClick(filmId: String) {
        findNavController().navigate(R.id.filmDetailFragment, bundleOf(FILM_ID to filmId))
    }
}