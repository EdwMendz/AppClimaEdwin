package mx.kodemia.appclimaedwin.network

import mx.kodemia.appclimaedwin.model.Main
import mx.kodemia.appclimaedwin.model.Sys
import mx.kodemia.appclimaedwin.model.Weather
import mx.kodemia.appclimaedwin.model.Wind

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
