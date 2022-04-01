package mx.kodemia.appclimaedwin.data.network.openWeather

import mx.kodemia.appclimaedwin.data.model.oneCall.CityEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeaterService {
    @GET("data/2.5/weather")
    suspend fun getWheterById( //Corrutina
        @Query("id") long: Long, //Ubicacion por id
        @Query("units") units:String?, //Unidad de medidad grados centigrados
        @Query("lang") lang:String?,//Lenguage
        @Query("appid") appid:String? ): Response<WeatherEntity>//Lenguage

}