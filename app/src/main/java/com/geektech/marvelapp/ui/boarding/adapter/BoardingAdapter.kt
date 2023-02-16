package com.geektech.marvelapp.ui.boarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.marvelapp.databinding.ItemBoardingBinding
import com.geektech.marvelapp.data.remote.model.Board
import com.geektech.marvelapp.utils.Constants

class BoardingAdapter(val skipBoarding: () -> Unit) :
    Adapter<BoardingAdapter.BoardingViewHolder>() {

    val data = Constants.boardingData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingViewHolder {
        return BoardingViewHolder(
            ItemBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: BoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class BoardingViewHolder(private val binding: ItemBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(model: Board) {
            with(binding) {
                tvDesc.text = model.desc
                ivImage.load(model.image)

                if (adapterPosition == data.lastIndex) {
                    btnSkip.text = "get started"
                }

                btnSkip.setOnClickListener { skipBoarding() }
            }
        }
    }
}