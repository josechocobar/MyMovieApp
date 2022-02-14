package com.cuty.mymovieapp.ui.movieDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Cast
import com.cuty.mymovieapp.ui.recycler.BaseViewHolder
import com.cuty.mymovieapp.ui.recycler.CastBaseViewHolder

class CastAdapter(
    private val context: Context,
    private val castList: List<Cast>,
    private val itemClickListener: OnTrailerClickListener
) : RecyclerView.Adapter<CastBaseViewHolder<*>>() {
    interface OnTrailerClickListener {
        fun onCastClick(actor:Cast, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastBaseViewHolder<*> {
        return CastViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_actors_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CastBaseViewHolder<*>, position: Int) {
        when (holder) {
            is CastViewHolder -> castList[position].let {
                holder.bind(it, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    inner class CastViewHolder(itemView: View) : CastBaseViewHolder<Cast>(itemView) {
        val trailerName: TextView = itemView.findViewById(R.id.tv_actor_item)
        override fun bind(item: Cast, position: Int) {
            trailerName.text = item.name
            itemView.setOnClickListener {
                itemClickListener.onCastClick(item, position)
            }
        }
    }
}
