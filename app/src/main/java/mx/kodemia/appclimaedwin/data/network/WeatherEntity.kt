package mx.kodemia.appclimaedwin.data.network

import mx.kodemia.appclimaedwin.data.model.Main
import mx.kodemia.appclimaedwin.data.model.Sys
import mx.kodemia.appclimaedwin.data.model.Weather
import mx.kodemia.appclimaedwin.data.model.Wind

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
