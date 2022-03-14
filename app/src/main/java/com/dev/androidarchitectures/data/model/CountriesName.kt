package com.dev.androidarchitectures.data.model

import com.google.gson.annotations.SerializedName

data class CountriesName(
    @SerializedName("official")
    var countryName: String? = null
)
