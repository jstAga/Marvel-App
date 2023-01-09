package com.geektech.marvelapp.ui.boarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.marvelapp.databinding.ItemBoardingBinding
import com.geektech.marvelapp.model.Board

class BoardingAdapter(val skipBoarding: () -> Unit) :
    Adapter<BoardingAdapter.BoardingViewHolder>() {
    val data = listOf(
        Board(
            "Welcome to the Marvel Universe",
            "https://i.pinimg.com/564x/b4/c4/ab/b4c4ab99761386b5c0b5fbb4dbdef625.jpg"
        ),
        Board(
            "You can read comic books, learn about movies",
            "https://i.pinimg.com/564x/b4/c4/ab/b4c4ab99761386b5c0b5fbb4dbdef625.jpg"
        ),
        Board(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elite",
            "https://i.pinimg.com/564x/b4/c4/ab/b4c4ab99761386b5c0b5fbb4dbdef625.jpg"
        ),
    )

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