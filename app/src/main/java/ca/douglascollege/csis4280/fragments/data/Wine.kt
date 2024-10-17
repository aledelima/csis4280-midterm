package ca.douglascollege.csis4280.fragments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wine(
    val title: String,
    val price: String,
    val description: String,
    val variety: String,
    val province: String,
    val country: String,
    val winery: String,
    var taster: Taster
): Parcelable