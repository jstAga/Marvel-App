package com.geektech.marvelapp.ui.shop

import android.view.View
import android.widget.Toast
import com.geektech.marvelapp.databinding.FragmentShopBinding
import com.geektech.marvelapp.ui.shop.adapter.ShopAdapter
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.marvelapp.data.local.room.ComicEntity
import com.geektech.marvelapp.data.remote.model.marvel.Comic
import com.geektech.youtubeapi.core.network.result.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding, ShopViewModel>() {

    private val adapter by lazy { ShopAdapter(this::onSaveClick) }
    private var localComics: List<ComicEntity> = listOf()

    override fun getViewModelClass(): Class<ShopViewModel> = ShopViewModel::class.java
    override fun getViewBinding(): FragmentShopBinding = FragmentShopBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        binding.rvShop.adapter = adapter
    }

    override fun initObserver() {
        super.initObserver()
        getComics()
        getLocalComics()
    }

    private fun getLocalComics() {
        viewModel.getAllComic()
        viewModel.liveLocalComics?.observe(viewLifecycleOwner) {
            localComics = it
        }
    }

    private fun getComics() {
        viewModel.getComics()
        viewModel.liveRemoteComics?.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.rvShop.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                    adapter.addData(it.data?.data?.results!!)
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.rvShop.visibility = View.GONE
            }
        }
    }

    private fun onSaveClick(model: Comic): Boolean {
        var isSaved = false
        viewModel.isComicsSaved(localComics, model)
        viewModel.liveIsComicsSaved.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.deleteComic(it)
                isSaved = true
            } else {
                viewModel.insertComic(ComicEntity(
                    model.title,
                    model.thumbnail?.path,
                    model.thumbnail?.extension,
                    model.description,
                    0))
                isSaved = false
            }
        }
        return isSaved
    }
}


