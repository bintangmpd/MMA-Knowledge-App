package com.example.fruitapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val title: String,
    val description: String,
    val photo: Int,
    val teknik_dasar: String,
):Parcelable
