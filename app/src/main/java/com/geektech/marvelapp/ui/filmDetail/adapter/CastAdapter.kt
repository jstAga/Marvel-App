package com.geektech.marvelapp.ui.filmDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.marvelapp.data.remote.model.imbd.ActorModel
import com.geektech.marvelapp.databinding.ItemCastBinding

class CastAdapter() : Adapter<CastAdapter.CastViewHolder>() {
    private val data = arrayListOf<ActorModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class CastViewHolder(private val binding: ItemCastBinding) : ViewHolder(binding.root) {
        fun bind(model: ActorModel) {
//            binding.ivAvatar.load(model)
        }
    }
}