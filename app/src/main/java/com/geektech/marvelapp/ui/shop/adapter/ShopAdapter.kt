package com.geektech.marvelapp.ui.shop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.marvelapp.R
import com.geektech.marvelapp.databinding.ItemShopBinding
import com.geektech.marvelapp.data.remote.model.marvel.Comic

class ShopAdapter(private val onSaveClick: (Comic) -> Boolean) :
    Adapter<ShopAdapter.ShopViewHolder>() {

    private val data = arrayListOf<Comic>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<Comic>) {
        data.clear()
        data.addAll(newData)
//        data.shuffle()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            ItemShopBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ShopViewHolder(private val binding: ItemShopBinding) : ViewHolder(binding.root) {
        private val icSaved by lazy { ContextCompat.getDrawable(itemView.context , R.drawable.ic_saved)}
        private val icSave by lazy { ContextCompat.getDrawable(itemView.context , R.drawable.ic_save)}

        fun bind(model: Comic) {
            with(binding) {
                if (model.thumbnail?.path != null && model.thumbnail!!.extension != null && model.title != null) {
                    ivImage.load(model.thumbnail!!.path + "." + model.thumbnail!!.extension)
                    tvTitle.text = model.title
                }
                ivSave.setOnClickListener {
                    if (onSaveClick(model)) {
                        ivSave.load(icSaved)
                    } else {
                        ivSave.load(icSave)
                    }
                }
            }
        }
    }
}

