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
    suspend fun getDatosWeather(id: Long,
                                units: String?,
                                lang: String?,
                                appID: String): Response<WeatherEntity> {

        return withContext(Dispatchers.IO) {
            val respuesta = retrofitWeather.getWheterById(id,units,lang,appID)
                //3523202L, "metric", "sp",
                //"1b96dc7f7bd358dc23b5d5926d7d2572"
            respuesta
        }
    }

    //Service de OneCall
    suspend fun getDatosOneCall( lat: String,
                                 lon: String,
                                 units: String?,
                                 lang: String?,
                                 apiId: String): Response<OneCallEntity> {
        return withContext(Dispatchers.IO) {
            val respuesta = retrofitOneCall.getOneCallByLatLng(
                lat,//"30.489772",
                lon,//"-99.771335",
                units,//"metric",
                lang,//lang//"sp",
                apiId//"1b96dc7f7bd358dc23b5d5926d7d2572"
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

