package mx.kodemia.appclimaedwin.data.model.oneCall

import com.google.gson.annotations.SerializedName
import mx.kodemia.appclimaedwin.data.model.openWeather.Weather
import java.io.Serializable

data class Current(val dt: Long,
                   val temp: Double,
                   @SerializedName(value = "feels_like")
                       val feelsLike: Double,
                   @SerializedName(value = "wind_speed")
                       val wind: Double,
                   val pressure: Int,
                   val humidity: Int,
                   val weather: List<Weather>,
): Serializable