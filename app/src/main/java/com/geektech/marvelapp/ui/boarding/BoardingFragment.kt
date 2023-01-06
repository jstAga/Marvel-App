package com.geektech.marvelapp.ui.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.marvelapp.databinding.FragmentBoardingBinding
import com.geektech.marvelapp.databinding.FragmentSplashScreenBinding


class BoardingFragment : Fragment() {
    private lateinit var binding: FragmentBoardingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }
}