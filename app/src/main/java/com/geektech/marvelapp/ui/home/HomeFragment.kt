package com.geektech.marvelapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.geektech.marvelapp.databinding.FragmentHomeBinding
import com.geektech.marvelapp.ui.home.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        addData()


        binding.btnNews.setOnClickListener {
            viewModel.getCastModel("tt14311386")
            viewModel.liveActorsId.observe(viewLifecycleOwner) {
                Log.e("aga", "actors data: ${it}")
            }

        }

    }

    private fun initAdapter() {
        feedAdapter = FeedAdapter()
        binding.rvFeed.adapter = feedAdapter
    }

    private fun addData() {
        viewModel.getFilmModel("spiderman")
        viewModel.liveFilmData.observe(viewLifecycleOwner) {
            feedAdapter.addData(it)
        }
    }

}

