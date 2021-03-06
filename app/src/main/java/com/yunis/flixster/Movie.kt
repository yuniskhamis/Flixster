package com.yunis.flixster

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie(
    val movieId: Int,
    private val posterPath: String,
    private val backDropPath: String,
    val voteAverage: Double,
    val title: String,
    val overview: String
) :Parcelable {
    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    @IgnoredOnParcel
    val posterImageUrl2 = "https://image.tmdb.org/t/p/w300/$backDropPath"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie>{
            val movies = mutableListOf<Movie>()
            for(i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("backdrop_path"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }
}
