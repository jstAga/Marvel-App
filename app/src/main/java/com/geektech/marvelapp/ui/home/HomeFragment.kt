package com.geektech.marvelapp.ui.home

import android.view.View
import android.widget.Toast
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.marvelapp.databinding.FragmentHomeBinding
import com.geektech.marvelapp.ui.home.adapter.FeedAdapter
import com.geektech.youtubeapi.core.network.result.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel >() {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
    private val feedAdapter by lazy { FeedAdapter() }

        override fun initViews() {
            super.initViews()
            binding.rvFeed.adapter = feedAdapter
        }

    override fun initObserver() {
        super.initObserver()
        getFilm()
    }

    private fun getFilm() {
        viewModel.getFilm("spiderman")
        viewModel.liveFilmData.observe(viewLifecycleOwner) {
//            feedAdapter.addData(it)
            when (it.status){
                Status.SUCCESS -> {
                    binding.rvFeed.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { it1 -> feedAdapter.addData(it1) }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.rvFeed.visibility = View.GONE
            }
        }
    }
}

