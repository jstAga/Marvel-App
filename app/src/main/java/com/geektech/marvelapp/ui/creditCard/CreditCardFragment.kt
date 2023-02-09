package com.geektech.marvelapp.ui.creditCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.marvelapp.databinding.FragmentComicBookBinding
import com.geektech.marvelapp.databinding.FragmentCreditCardBinding


class CreditCardFragment : Fragment() {
    private lateinit var binding: FragmentCreditCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentCreditCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}