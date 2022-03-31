package mx.kodemia.appclimaedwin.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.kodemia.appclimaedwin.service.WeatherService
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.appclimaedwin.data.network.WeatherEntity
import java.io.IOException


class MianActivityViewModel : ViewModel() {

    //Service
    private val  serviceWeather = WeatherService()

    //LiveData
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()
    val weatherEntity = MutableLiveData<WeatherEntity>()


    /*Se realiza la peticion a la API,  MainActivitiViewModel Recibe la respuesta
    * despues se manda a la MainActivityView */

    fun getWeather() {
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceWeather.getDatos()
            //Validacion
            try {
                if (response.isSuccessful){
                    weatherEntity.postValue(response.body())
                }else {
                    error.postValue(response.message())
                    Log.e("Something paso", "Ago paso")
                }
                cargando.postValue(false)
            }catch (error1:IOException){
                error.postValue(error1.localizedMessage)
                cargando.postValue(false)
            }
        }
    }

}