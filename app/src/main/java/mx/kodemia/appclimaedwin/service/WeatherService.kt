package mx.kodemia.appclimaedwin.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.appclimaedwin.core.RetrofitInstance
import mx.kodemia.appclimaedwin.data.network.ApiWeaterService
import mx.kodemia.appclimaedwin.data.network.WeatherEntity
import retrofit2.Response

class WeatherService {
    //Se instancia el servicio de retrofit con la peticion de WeatherService
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiWeaterService::class.java)

    //Se crea la funcion para para mandar la peticion con los parametros harcodeados(por ahora)

    suspend fun getDatos(): Response<WeatherEntity> {
        return withContext(Dispatchers.IO) {
            val respuesta = retrofit.getWheterById(
                3523202L, "metric", "sp",
                "1b96dc7f7bd358dc23b5d5926d7d2572"
            )
            respuesta

        }
    }
}



//
//private val retrofit = RetrofitInstance.getRetrofit(context).create(ListTransaction::class.java)
//
//suspend fun ListTransaction(): Response<ListaTransaccionesResponse> {
//    return withContext(Dispatchers.IO){
//        val response = retrofit.listTransaction()
//        response
//    }
//}
//
//}