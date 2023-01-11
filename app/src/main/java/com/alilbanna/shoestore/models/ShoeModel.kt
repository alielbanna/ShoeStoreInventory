package com.alilbanna.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoeModel(
    var name: String,
    var size: Int,
    var company: String,
    var description: String,
    var shoeModel: Int,
    val modelsAvailable: MutableList<String> = mutableListOf(
        "model1",
        "model2",
        "model3",
        "model4",
        "model5",
        "model6",
        "model7",
        "model8",
        "model9",
        "model10",
    )
) : Parcelable