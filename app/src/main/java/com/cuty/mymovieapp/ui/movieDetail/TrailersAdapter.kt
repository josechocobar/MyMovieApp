package com.cuty.mymovieapp.ui.movieDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Trailer
import com.cuty.mymovieapp.ui.recycler.TrailerBaseViewHolder

class TrailersAdapter(
    private val context: Context,
    private val trailerList:List<Trailer>,
    private val trailerClickListener : OnTrailerClickListener
) : RecyclerView.Adapter<TrailerBaseViewHolder<*>>(){
    interface OnTrailerClickListener{
        fun onTrailerClick(trailer:Trailer, position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerBaseViewHolder<*> {
        return TrailerViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_trailer, parent,false)
        )
    }

    override fun onBindViewHolder(holder: TrailerBaseViewHolder<*>, position: Int) {
        when(holder){
            is TrailerViewHolder -> trailerList[position].let {
                holder.bind(it,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }
    inner class TrailerViewHolder(itemView:View) : TrailerBaseViewHolder<Trailer>(itemView){
        val trailerName : TextView = itemView.findViewById(R.id.tv_trailer_number)
        override fun bind(item: Trailer, position: Int) {
            trailerName.text = item.name
            itemView.setOnClickListener {
                trailerClickListener.onTrailerClick(item,position)
            }
        }

    }
}