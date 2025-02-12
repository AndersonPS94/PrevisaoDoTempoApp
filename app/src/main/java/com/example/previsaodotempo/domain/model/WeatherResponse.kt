import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("hourly") val hourly: HourlyData,
    @SerializedName("daily") val daily: DailyData
)


data class HourlyData(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperatures: List<Double>,
    @SerializedName("wind_speed_10m") val windSpeeds: List<Double>,
    @SerializedName("relative_humidity_2m") val humidityLevels: List<Double>,
    @SerializedName("precipitation_probability") val precipitationProbability: List<Double>,
    @SerializedName("weathercode") val weatherCodes: List<Int>
)


data class DailyData(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m_max") val maxTemperatures: List<Double>,
    @SerializedName("temperature_2m_min") val minTemperatures: List<Double>,
    @SerializedName("weathercode") val weatherCodes: List<Int>,

)
