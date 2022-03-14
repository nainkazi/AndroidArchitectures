package com.dev.androidarchitectures.data.model

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("name")
    var countriesName: CountriesName
)
