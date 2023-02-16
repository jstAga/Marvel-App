package com.geektech.marvelapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.marvelapp.data.remote.model.imbd.FilmModel
import com.geektech.marvelapp.data.remote.model.imbd.ResultsModel
import com.geektech.marvelapp.databinding.ItemFeedBinding

class FeedAdapter(private val onClick: (String) -> Unit) : Adapter<FeedAdapter.FeedViewHolder>() {
    private val data = arrayListOf<ResultsModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: FilmModel) {
        data.clear()
        newData.results.let { data.addAll(it) }
        data.shuffle()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            ItemFeedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class FeedViewHolder(private val binding: ItemFeedBinding) : ViewHolder(binding.root) {
        fun bind(model: ResultsModel) {
            with(binding) {
                ivBackground.load(model.image!!.url)
                tvTitle.text = model.title
            }
            itemView.setOnClickListener { onClick(model.filmID.toString()) }
        }
    }
}