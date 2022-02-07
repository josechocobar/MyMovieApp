package com.cuty.mymovieapp.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_table")
@Parcelize
data class Movie(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idroom")
    val idRoom : Int,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    @ColumnInfo(name = "popular")
    var popular: Boolean,
    @ColumnInfo(name = "top_rated")
    var topRated: Boolean
) : Parcelable