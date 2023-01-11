package com.geektech.marvelapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geektech.marvelapp.R
import com.geektech.marvelapp.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
    }

    private fun initClicker() {
        loginPassword()
        openIncludeReg()
    }


    private fun openIncludeReg() {
        with(binding) {
            inAuth.btnRegistration.setOnClickListener {
                authContainer.visibility = View.GONE
                regContainer.visibility = View.VISIBLE
            }
        }
        signUp()
    }

    private fun signUp() {
        with(binding.inReg) {
            btnReg.setOnClickListener {
                viewModel.regNewUser(
                    etMail.text.toString(),
                    etPassword.text.toString(),
                    requireActivity()
                )
            }
            viewModel.regResultLive.observe(viewLifecycleOwner) { info ->
                if (info.result) {
                    findNavController().navigate(R.id.homeFragment)
                } else {
                    Log.e("aga", "signUp: ${info.exceptionMessage}")

                }
            }
        }
    }

    private fun loginPassword() {
        with(binding.inAuth) {
            btnLogin.setOnClickListener {
                viewModel.passwordAuth(
                    etMail.text.toString(),
                    etPassword.text.toString(),
                    requireActivity()
                )
            }
            viewModel.authPasswordResultLive.observe(viewLifecycleOwner) { info ->
                if (info.result) {
                    findNavController().navigate(R.id.homeFragment)
                } else {
                    viewModel.checkPasswordAuth(info.exceptionMessage.toString())
                    viewModel.exceptionMessageLive.observe(viewLifecycleOwner) {
                        etPassword.error = it
                    }
                }
            }
        }
    }
}


