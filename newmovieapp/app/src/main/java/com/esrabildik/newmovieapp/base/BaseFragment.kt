package com.esrabildik.newmovieapp.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.esrabildik.newmovieapp.model.DetailsItems
import com.esrabildik.newmovieapp.model.MovieListResponse
import com.esrabildik.newmovieapp.service.movieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseFragment<VB : ViewBinding>
    (private val bindingInflater: (inflater: LayoutInflater, parent: ViewGroup?, attachParent: Boolean) -> VB) :
    Fragment() {
    val BASE_URL = "https://moviesapi.ir/api/v1/"

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun loadData(page: Int, dataListener: (MovieListResponse) -> Unit) {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(movieAPI::class.java)
        val call = service.getData(page)
        call.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    val movieListResponse = response.body()
                    Log.d("API_RESPONSE", "Response: $movieListResponse")
                    // Verileri listener'a gönder
                    movieListResponse?.let {
                        dataListener.invoke(it)
                    }
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e("API_RESPONSE", "Failed to make API call: ${t.message}")
            }
        })
    }



}


