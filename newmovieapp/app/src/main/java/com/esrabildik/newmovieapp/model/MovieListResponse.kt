package com.esrabildik.newmovieapp.model


data class MovieListResponse(
    var `data`: List<Movie>,
    var metadata: MetaData
)
