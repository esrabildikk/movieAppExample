package com.esrabildik.newmovieapp.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.esrabildik.newmovieapp.R
import com.esrabildik.newmovieapp.base.BaseViewHolder
import com.esrabildik.newmovieapp.databinding.BestMovieCardLayoutBinding
import com.esrabildik.newmovieapp.model.Movie
import com.esrabildik.newmovieapp.view.DetailsMovieFragment

class BestMovieViewHolder(parent: ViewGroup, inflater: LayoutInflater):
    BaseViewHolder<BestMovieCardLayoutBinding>(binding = BestMovieCardLayoutBinding.inflate(inflater,parent,false))
{
    fun bind(movie: Movie){
        with(binding){
            bestMovieText.text = movie.title

            val requestOptions : RequestOptions
            requestOptions = RequestOptions().transform(CenterCrop(),RoundedCorners(25))
            Glide.with(itemView.context)
                .load(movie.poster)
                .apply(requestOptions)
                .into(bestMovieImage)


            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("MovieID", movie.id)
                itemView.findNavController().navigate(R.id.action_homeFragment_to_detailsMovieFragment, bundle)
            }



        }
    }


}


