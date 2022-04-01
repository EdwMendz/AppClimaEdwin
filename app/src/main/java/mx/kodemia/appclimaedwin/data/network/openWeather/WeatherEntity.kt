package mx.kodemia.appclimaedwin.data.network.openWeather

import mx.kodemia.appclimaedwin.data.model.openWeather.Main
import mx.kodemia.appclimaedwin.data.model.openWeather.Sys
import mx.kodemia.appclimaedwin.data.model.openWeather.Weather
import mx.kodemia.appclimaedwin.data.model.openWeather.Wind

data class WeatherEntity(
    val base: String,
    val main: Main,
    val sys: Sys,
    val id: Int,
    val name: String,
    val wind: Wind,
    val weather: List<Weather>,
    val dt: Long
)
