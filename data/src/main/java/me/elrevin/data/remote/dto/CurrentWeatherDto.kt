package me.elrevin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated") var lastUpdated: String? = null,
    @SerializedName("last_updated_epoch") var lastUpdatedTimestamp: Int? = null,
    @SerializedName("temp_c") var temp: Double? = null,
    @SerializedName("is_day") var isDay: Int? = null,
    @SerializedName("condition") var condition: ConditionDto? = ConditionDto(),
    @SerializedName("wind_kph") var wind: Double? = null,
    @SerializedName("wind_degree") var windDegree: Int? = null,
    @SerializedName("wind_dir") var windDir: String? = null,
    @SerializedName("pressure_mb") var pressure: Int? = null,
    @SerializedName("precip_mm") var precip: Int? = null,
    @SerializedName("humidity") var humidity: Int? = null,
    @SerializedName("cloud") var cloud: Int? = null,
    @SerializedName("feelslike_c") var feelslike: Double? = null,
    @SerializedName("gust_kph") var gust: Double? = null

)