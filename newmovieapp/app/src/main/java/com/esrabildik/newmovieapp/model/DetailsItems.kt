package com.esrabildik.newmovieapp.model

class DetailsItems
    (
    val id: Int,
    val title: String,
    val poster: String,
    val runtime: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val country: String,
    val awards: String,
    val metascore: String,
    val imdb_rating: String,
    val imdb_votes: String,
    val imdb_id: String,
    val type: String,
    val genres: List<String>,
    val images: List<String>
) {
}