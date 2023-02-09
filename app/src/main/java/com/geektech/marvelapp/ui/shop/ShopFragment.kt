package com.geektech.marvelapp.ui.shop

import android.view.View
import android.widget.Toast
import com.geektech.marvelapp.databinding.FragmentShopBinding
import com.geektech.marvelapp.ui.shop.adapter.ShopAdapter
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.youtubeapi.core.network.result.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding, ShopViewModel>() {
    override fun getViewModelClass(): Class<ShopViewModel> = ShopViewModel::class.java
    override fun getViewBinding(): FragmentShopBinding = FragmentShopBinding.inflate(layoutInflater)
    private val adapter by lazy { ShopAdapter() }

    override fun initViews() {
        super.initViews()
        binding.rvShop.adapter = adapter
    }

    override fun initObserver() {
        super.initObserver()
        getComics()
    }

    private fun getComics() {
        viewModel.getComics()
        viewModel.liveComicResponse.observe(viewLifecycleOwner) {
            when (it.status){
                Status.SUCCESS -> {
                    binding.rvShop.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                    it.data?.data?.results?.let { it1 -> adapter.addData(it1) }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.rvShop.visibility = View.GONE
            }
        }
    }
}


