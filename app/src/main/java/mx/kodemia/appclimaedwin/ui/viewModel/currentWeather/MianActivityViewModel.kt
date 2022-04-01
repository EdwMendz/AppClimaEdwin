package mx.kodemia.appclimaedwin.ui.viewModel.currentWeather

import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.appclimaedwin.data.network.openWeather.WeatherEntity
import mx.kodemia.appclimaedwin.service.OpenWeatherService
import java.io.IOException


class MianActivityViewModel : ViewModel() {

    //Service
    private val  serviceWeather = OpenWeatherService()

    //LiveData
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()
    val weatherEntity = MutableLiveData<WeatherEntity>()
    val button = MutableLiveData<Button>()


    /*Se realiza la peticion a la API,  MainActivitiViewModel Recibe la respuesta
    * despues se manda a la MainActivityView */

    fun getWeather(id: Long,
                   units: String?,
                   lang: String?,
                   appID: String) {
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceWeather.getDatosWeather(id,units,lang,appID)
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