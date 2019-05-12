package com.example.modulbankhw5

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CurrencyResponse (
    @SerializedName("base")
    val base: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("rates")
    val rates: MutableMap<String,BigDecimal>)
