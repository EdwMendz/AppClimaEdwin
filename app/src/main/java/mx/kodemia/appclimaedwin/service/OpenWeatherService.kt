package mx.kodemia.appclimaedwin.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.appclimaedwin.R
import mx.kodemia.appclimaedwin.core.RetrofitInstance
import mx.kodemia.appclimaedwin.data.model.oneCall.CityEntity
import mx.kodemia.appclimaedwin.data.model.oneCall.OneCallEntity
import mx.kodemia.appclimaedwin.data.network.oneCall.ApiOneService
import mx.kodemia.appclimaedwin.data.network.oneCall.ApiCityService
import mx.kodemia.appclimaedwin.data.network.openWeather.ApiWeaterService
import mx.kodemia.appclimaedwin.data.network.openWeather.WeatherEntity
import retrofit2.Response


class OpenWeatherService {
    //Se instancia el servicio de retrofit con la peticion de WeatherService
    private val retrofitWeather =
        RetrofitInstance.getRetrofitWeather().create(ApiWeaterService::class.java)
    private val retrofitOneCall =
        RetrofitInstance.getRetrofitWeather().create(ApiOneService::class.java)
    private val retrofitCity =
        RetrofitInstance.getRetrofitWeather().create(ApiCityService::class.java)
    //Se crea la funcion para para mandar la peticion con los parametros harcodeados(por ahora)


    //Service de current
    suspend fun getDatosWeather(): Response<WeatherEntity> {
        return withContext(Dispatchers.IO) {
            val respuesta = retrofitWeather.getWheterById(
                3523202L, "metric", "sp",
                "1b96dc7f7bd358dc23b5d5926d7d2572"
            )
            respuesta
        }
    }

    //Service de OneCall
    suspend fun getDatosOneCall(): Response<OneCallEntity> {
        return withContext(Dispatchers.IO) {
            val respuesta = retrofitOneCall.getOneCallByLatLng(
                "30.489772",
                "-99.771335", "metric", "sp",
                "1b96dc7f7bd358dc23b5d5926d7d2572"
            )
            respuesta
        }
    }

    //Service de ObtenerCity
    suspend fun getCityNameByLatLng(latitude: String, longitude: String): Response<List<CityEntity>> {
        return withContext(Dispatchers.IO) {
//        var result: CityEntity? = null
            val respuesta = retrofitCity.getCitiesByLatLng(
                latitude, longitude, "1b96dc7f7bd358dc23b5d5926d7d2572"
            )
            respuesta
        }
    }
}

