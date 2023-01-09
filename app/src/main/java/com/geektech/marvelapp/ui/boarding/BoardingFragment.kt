package com.geektech.marvelapp.ui.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.marvelapp.R
import com.geektech.marvelapp.databinding.FragmentBoardingBinding
import com.geektech.marvelapp.ui.boarding.adapter.BoardingAdapter


class BoardingFragment : Fragment() {
    private lateinit var binding: FragmentBoardingBinding
    private lateinit var adapter: BoardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = BoardingAdapter { skipBoarding() }
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
    }

    private fun skipBoarding() {
        findNavController().navigate(R.id.authFragment)
    }
}