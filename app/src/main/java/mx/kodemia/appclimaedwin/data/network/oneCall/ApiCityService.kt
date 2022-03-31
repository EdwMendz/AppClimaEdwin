package mx.kodemia.appclimaedwin.data.network.oneCall

import mx.kodemia.appclimaedwin.data.model.oneCall.CityEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCityService {
    @GET("geo/1.0/reverse")
    suspend fun getCitiesByLatLng(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response<List<CityEntity>>
}