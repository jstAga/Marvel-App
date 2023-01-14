package com.geektech.marvelapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.marvelapp.data.remote.FilmModel
import com.geektech.marvelapp.data.remote.ResultsModel
import com.geektech.marvelapp.databinding.ItemFeedBinding

class FeedAdapter() : Adapter<FeedAdapter.FeedViewHolder>() {
    private val data = arrayListOf<ResultsModel>()

    fun addData(newData: FilmModel) {
        data.clear()
        data.addAll(newData.results)
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
                if (model.image != null && model.title != null) {
                    ivBackground.load(model.image.url)
                    tvTitle.text = model.title
                } else {
                    ivBackground.load("https://icon-library.com/images/failed-icon/failed-icon-7.jpg")
                }
            }
        }
    }
}

/*
FilmModel(results=[ResultsModel(id=/title/tt14311386/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BNmMzODkwNDktMTkyMy00MmU5LWE4MGMtYzIzZjdjNmJiZDRiXkEyXkFqcGdeQXVyNDU1NDQ0NzE@._V1_.jpg), title=Miles Morales Ultimate Spiderman),
ResultsModel(id=/title/tt2705436/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BYjFhN2RjZTctMzA2Ni00NzE2LWJmYjMtNDAyYTllOTkyMmY3XkEyXkFqcGdeQXVyNTA0OTU0OTQ@._V1_.jpg), title=Italian Spiderman),
ResultsModel(id=/title/tt12122034/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BNjA2NmZhOGEtZTQ5OS00MDI0LTg4N2UtYTRmOTllM2I2NDlhXkEyXkFqcGdeQXVyNTU4OTE5Nzc@._V1_.jpg), title=Spiderman the Verse),
ResultsModel(id=/title/tt18351128/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BYzYzZDViNWYtNWViMS00NDMxLThlN2YtZjFkOWMwODkzNzhiXkEyXkFqcGdeQXVyMTUwMzM4NzU0._V1_.jpg), title=The Amazing Spiderman 2 Webb Cut),
 ResultsModel(id=/title/tt24154050/, image=null, title=Hyundai Ioniq 5: Spiderman Only Way Home),
  ResultsModel(id=/title/tt3696826/, image=null, title=Spiderman 5),
   ResultsModel(id=/title/tt0100669/, image=null, title=Spiderman),
   ResultsModel(id=/title/tt2084949/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BMjQ4MzcxNDU3N15BMl5BanBnXkFtZTgwOTE1MzMxNzE@._V1_.jpg), title=Superman, Spiderman or Batman), ResultsModel(id=/title/tt7475898/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BMTg3MjVkY2EtNGY3NS00N2QzLThlNmItZWRkZTI4YTBiMzM0XkEyXkFqcGdeQXVyODE5Mzk0NjM@._V1_.jpg), title=Logic Feat. Damian Lemar Hudson: Black Spiderman), ResultsModel(id=/title/tt5978586/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BZDlmMGQwYmItNTNmOS00OTNkLTkxNTYtNDM3ZWVlMWUyZDIzXkEyXkFqcGdeQXVyMTA5Mzk5Mw@@._V1_.jpg), title=Spiderman in Cannes), ResultsModel(id=/title/tt2586634/, image=null, title=Amazing Spiderman Syndrome), ResultsModel(id=/title/tt1785572/, image=null, title=Spiderman), ResultsModel(id=/title/tt6889550/, image=null, title=Spiderman: The Beginning of a New Era), ResultsModel(id=/title/tt9146610/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BYWU5YTg2ODgtYjY5Mi00ZDJhLTkyYjktYWRmNTc3ZjQ4YmJkXkEyXkFqcGdeQXVyODE4Njg5ODQ@._V1_.jpg), title=Discount Spiderman 2), ResultsModel(id=/title/tt6554480/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BM2MxYzkwODctMWNiMC00OTNiLTgwN2UtMmM4ZmU0ZWEyMjJlXkEyXkFqcGdeQXVyNDA5NzAyMjQ@._V1_.jpg), title=Yellow Spiderman), ResultsModel(id=/title/tt7575846/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BNDM5ODA3ZDYtN2VjNy00OTI0LWFlNWYtYTc5ZjJlOTlhNWJlXkEyXkFqcGdeQXVyODE4Njg5ODQ@._V1_.jpg), title=Discount Spiderman: Origins), ResultsModel(id=/title/tt5460250/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BNGQxNWIxYmItMjk5NS00MWEwLTkyZDYtMWYyMWQ4OTg2ZTNlXkEyXkFqcGdeQXVyODE2NDgwMzM@._V1_.jpg), title=Inside the Editing Room of 'Spiderman 3'), ResultsModel(id=/title/tt19635840/, image=null, title=Spiderman (Fanmade Stop motion) by Chrisoryx), ResultsModel(id=/title/tt20859394/, image=null, title=Spiderman and the Three Diamonds), ResultsModel(id=/title/tt21880788/, image=ImageModel(url=https://m.media-amazon.com/images/M/MV5BNGFjNmRkZDMtMzcxNS00NDJjLWIyZTAtNmI5Y2Q1NGRmMmZmXkEyXkFqcGdeQXVyNzQyMTI3NDY@._V1_.jpg), title=Spiderman Short Film)])
*/