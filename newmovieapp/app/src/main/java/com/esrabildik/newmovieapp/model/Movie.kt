package com.esrabildik.newmovieapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var country: String,
    var genres: List<String>,
    var id: Int,
    var images: List<String>,
    var imdb_rating: String,
    var poster: String,
    var title: String,
    var year: String
) : Parcelable