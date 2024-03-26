package com.esrabildik.newmovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esrabildik.newmovieapp.base.BaseListAdapter
import com.esrabildik.newmovieapp.model.Movie

class BestMovieAdapter() : BaseListAdapter<Movie>(
    itemsSame = { old, new -> old.id == new.id},
    contentsSame = { old, new -> old == new }
){
    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return BestMovieViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BestMovieViewHolder) {
            holder.bind(movie = getItem(position))
        }
    }

}