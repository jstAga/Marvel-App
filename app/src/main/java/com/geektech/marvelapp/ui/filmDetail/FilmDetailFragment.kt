package com.geektech.marvelapp.ui.filmDetail

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.geektech.marvelapp.R
import com.geektech.marvelapp.core.ui.BaseFragment
import com.geektech.marvelapp.databinding.FragmentFilmDetailBinding
import com.geektech.marvelapp.ui.filmDetail.adapter.CastAdapter
import com.geektech.marvelapp.utils.Constants.Companion.FILM_ID
import com.geektech.youtubeapi.core.network.result.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmDetailFragment : BaseFragment<FragmentFilmDetailBinding, FilmDetailViewModel>() {
    private val adapter by lazy { CastAdapter() }
    private val filmId by lazy { arguments?.getString(FILM_ID) }
    private val arrowUp by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_up)!! }
    private val arrowDown by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_down)!! }

    override fun getViewModelClass(): Class<FilmDetailViewModel> = FilmDetailViewModel::class.java
    override fun getViewBinding(): FragmentFilmDetailBinding =
        FragmentFilmDetailBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        binding.rvCast.adapter = adapter
        arrowUp.setBounds(0, 0, arrowUp.intrinsicHeight, arrowUp.intrinsicWidth)
        arrowDown.setBounds(0, 0, arrowDown.intrinsicHeight, arrowDown.intrinsicWidth)
    }

    override fun initObserver() {
        super.initObserver()
        viewLifecycleOwner.lifecycleScope.launch {
            getDetail()
            getCast()
        }
    }

    override fun initListener() {
        super.initListener()
        expandCast()
        expandStoryline()
    }

    private fun expandCast() {
        var isCastExpanded = false
        binding.tvCast.setOnClickListener {
            if (isCastExpanded) {
                binding.tvCast.setCompoundDrawables(null, null, arrowDown, null)
                isCastExpanded = false
            } else {
                binding.tvCast.setCompoundDrawables(null, null, arrowUp, null)
                isCastExpanded = true
            }
        }
    }

    private fun getCast() {
        viewModel.getCast(filmId.toString())
        viewModel.liveCast.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.e("aga", "getCast: " + it.data)
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    Log.e("aga", "getCast LOADING")
                }
            }
        }
    }


    private fun expandStoryline() {
        var isStorylineExpanded = false
        binding.tvStoryline.setOnClickListener {
            if (isStorylineExpanded) {
                binding.tvStoryline.setCompoundDrawables(null, null, arrowDown, null)
                isStorylineExpanded = false
                binding.tvExpandedStoryline.visibility = View.VISIBLE
            } else {
                binding.tvStoryline.setCompoundDrawables(null, null, arrowUp, null)
                isStorylineExpanded = true
                binding.tvExpandedStoryline.visibility = View.GONE
            }
        }
    }

    private fun getDetail() {
        Log.e("aga", "initObserver: " + arguments?.getString(FILM_ID))
        viewModel.getDetail(filmId.toString())
        viewModel.liveFilmDetail.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.tvTitle.text = it.data?.title?.title
                    binding.tvExpandedStoryline.text = it.data?.plotSummary?.text
                    binding.ivImage.load(it.data?.title?.image?.url)
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    //                    binding.rvFeed.visibility = View.GONE
                }
            }
        }
    }
}