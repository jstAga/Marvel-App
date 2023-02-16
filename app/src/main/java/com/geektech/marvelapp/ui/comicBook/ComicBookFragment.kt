package com.geektech.marvelapp.ui.comicBook

import android.util.Log
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.marvelapp.data.local.room.ComicEntity
import com.geektech.marvelapp.databinding.FragmentComicBookBinding
import com.geektech.marvelapp.ui.comicBook.adapter.ComicAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicBookFragment : BaseFragment<FragmentComicBookBinding, ComicViewModel>() {

    private val adapter by lazy { ComicAdapter(this::onClick) }

    override fun getViewModelClass(): Class<ComicViewModel> = ComicViewModel::class.java
    override fun getViewBinding(): FragmentComicBookBinding =
        FragmentComicBookBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        binding.rvComicBook.adapter = adapter
    }

    override fun initObserver() {
        super.initObserver()
        getAllComic()
    }

    private fun getAllComic() {
        viewModel.getAllComic()
        viewModel.liveComic.observe(viewLifecycleOwner) {
            Log.e("aga", "getAllComic: "+ it, )
            adapter.addData(it)
        }
    }

    private fun onClick(model : ComicEntity){
        viewModel.deleteComic(model)
    }
}