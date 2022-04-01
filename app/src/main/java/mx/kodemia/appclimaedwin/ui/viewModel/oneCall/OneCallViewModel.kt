package mx.kodemia.appclimaedwin.ui.viewModel.oneCall

import android.location.Location
import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.appclimaedwin.data.model.oneCall.OneCallEntity
import mx.kodemia.appclimaedwin.data.network.openWeather.WeatherEntity
import mx.kodemia.appclimaedwin.service.OpenWeatherService
import java.io.IOException

class OneCallViewModel: ViewModel() {
    private val  serviceWeather = OpenWeatherService()


    //LiveData
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()
    val oneCallEntity = MutableLiveData<OneCallEntity>()


    /*Se realiza la peticion a la API,  MainActivitiViewModel Recibe la respuesta
    * despues se manda a la MainActivityView */

    fun getDatosOneCall(lat: String, lon: String, units: String?, lang: String?, apiId: String) {
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceWeather.getDatosOneCall(lat,lon,units,lang,apiId)
            //Validacion
            try {
                if (response.isSuccessful){
                    oneCallEntity.postValue(response.body())
                }else {
                    error.postValue(response.message())
                    Log.e("Something paso", "Algo Malo paso en paso")
                }
                cargando.postValue(false)
            }catch (error1: IOException){
                error.postValue(error1.localizedMessage)
                cargando.postValue(false)
            }
        }
    }



}