package com.geektech.marvelapp.ui.comicBook.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.marvelapp.data.local.room.ComicEntity
import com.geektech.marvelapp.databinding.ItemComicBookBinding

class ComicAdapter(private val onClick: (ComicEntity) -> Unit) : Adapter<ComicAdapter.ComicViewHolder>() {

    private val data = arrayListOf<ComicEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<ComicEntity>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ComicViewHolder(private val binding: ItemComicBookBinding) :
        ViewHolder(binding.root) {
        fun bind(model: ComicEntity) {
            with(binding) {
                tvTitle.text = model.title
                ivImage.load(model.path + "." + model.extension)
                ivFavorite.setOnClickListener { onClick(model) }
            }
        }
    }
}