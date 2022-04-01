package mx.kodemia.appclimaedwin.data.network.oneCall

import mx.kodemia.appclimaedwin.data.model.oneCall.OneCallEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiOneService {
        @GET("data/2.5/onecall")
        suspend fun getOneCallByLatLng(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("units") units: String?,
            @Query("lang") lang: String?,
            @Query("appid") appid: String
        ):Response<OneCallEntity>
    }
