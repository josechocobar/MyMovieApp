package com.cuty.mymovieapp.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.utils.Constants.IMG_URL

class PopularAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
    private val itemClickListener: OnMovieItemClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnMovieItemClickListener {
        fun onMovieClick(item: Movie, position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MovieViewHolder(
                LayoutInflater.from(context).inflate(R.layout.rv_card, parent,false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MovieViewHolder -> movieList[position].let { holder.bind(it,position) }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView){
        val movieTitle : TextView = itemView.findViewById(R.id.tv_motie_title)
        override fun bind(item: Movie,position: Int){
            movieTitle.text = item.title
            Glide.with(context).load("$IMG_URL${item.poster_path}").transform(RoundedCorners(200)).centerCrop().into(itemView.findViewById(R.id.image_portrait))
            itemView.setOnClickListener {
                itemClickListener.onMovieClick(item, position)
            }
        }
    }


}