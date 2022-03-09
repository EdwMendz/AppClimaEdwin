package mx.kodemia.appclimaedwin.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WeaterService {
    @GET("data/2.5/weather")
    suspend fun getWheterById( //Corrutina
        @Query("id") long: Long, //Ubicacion por id
        @Query("units") units:String?, //Unidad de medidad grados centigrados
        @Query("lang") lang:String?,
        @Query("appid") appid:String? ): WeatherEntity//Lenguage

}