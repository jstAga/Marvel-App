package com.geektech.marvelapp.ui.splashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.marvelapp.R
import com.geektech.marvelapp.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateAfterSplash()
    }

    private fun navigateAfterSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser?.uid != null) {
                findNavController().navigate(R.id.homeFragment)
            } else {
                findNavController().navigate(R.id.boardingFragment)
            }
        }, 3000)
    }
}