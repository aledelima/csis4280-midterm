package ca.douglascollege.csis4280.fragments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Taster(
    val name: String,
    val twitterHandle: String,
    var wines: List<Wine>
): Parcelable

/*
@Parcelize
data class Taster(
    val wineTitle: String,
    val price: String,
    val designation: String,
    val winery: String,
    val country: String
): Parcelable

 */