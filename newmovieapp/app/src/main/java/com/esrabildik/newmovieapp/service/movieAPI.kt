package com.esrabildik.newmovieapp.service

import com.esrabildik.newmovieapp.model.DetailsItems
import com.esrabildik.newmovieapp.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface movieAPI {
    @GET("movies?page=")
    fun getData(@Query("page") page: Int): Call<MovieListResponse>

    @GET("movies/{id}") // URL'nin sonuna /id ekleniyor
    fun getDetailData(@Path("id") id: Int): Call<DetailsItems>
}