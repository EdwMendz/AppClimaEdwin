package mx.kodemia.appclimaedwin.data.model.oneCall

import java.io.Serializable

data class OneCallEntity(
    val current: Current,
    val hourly: List<Current>,
    var city: CityEntity?
): Serializable
