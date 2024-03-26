package com.esrabildik.newmovieapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.esrabildik.newmovieapp.R
import com.esrabildik.newmovieapp.base.BaseFragment
import com.esrabildik.newmovieapp.databinding.FragmentDetailsMovieBinding
import com.esrabildik.newmovieapp.model.DetailsItems
import com.esrabildik.newmovieapp.model.MovieListResponse
import com.esrabildik.newmovieapp.service.movieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailsMovieFragment :
    BaseFragment<FragmentDetailsMovieBinding>(FragmentDetailsMovieBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt("MovieID")
        Log.d("MOVİE ID: ", "Movie id: $movieId")

        if (movieId != null) {
            loadDetailsData(movieId)
        }
    }

    fun loadDetailsData(id: Int) {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(movieAPI::class.java)
        val call = service.getDetailData(id)
        call.enqueue(object : Callback<DetailsItems> {
            override fun onResponse(
                call: Call<DetailsItems>,
                response: Response<DetailsItems>
            ) {
                if (response.isSuccessful) {
                   val detailsItems = response.body()!!
                    Log.d("API_RESPONSE", "Response - ID: ${detailsItems.id}, Title: ${detailsItems.title}, Poster: ${detailsItems.poster}, Runtime: ${detailsItems.runtime}, Director: ${detailsItems.director}, Writer: ${detailsItems.writer}, Actors: ${detailsItems.actors}, Plot: ${detailsItems.plot}, Country: ${detailsItems.country}, Awards: ${detailsItems.awards}, Metascore: ${detailsItems.metascore}, IMDB Rating: ${detailsItems.imdb_rating}, IMDB Votes: ${detailsItems.imdb_votes}, IMDB ID: ${detailsItems.imdb_id}, Type: ${detailsItems.type}, Genres: ${detailsItems.genres}, Images: ${detailsItems.images}")
                    val requestOptions : RequestOptions
                    requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(25))
                    Glide.with(requireContext())
                        .load(detailsItems.poster)
                        .apply(requestOptions)
                        .into(binding.MovieImageView)

                    binding.movieSummaryTxt.setText(detailsItems.plot)
                    binding.starTextView.setText(detailsItems.imdb_rating)
                    binding.timeTextView.setText(detailsItems.runtime)
                    binding.movieTitleTextView.setText(detailsItems.title)

                } else {
                    Log.e("API_RESPONSE", "Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailsItems>, t: Throwable) {
                Log.e("API_RESPONSE", "Failed to make API call: ${t.message}")
            }
        })
    }

}
