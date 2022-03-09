package mx.kodemia.appclimaedwin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.kodemia.appclimaedwin.databinding.ActivityMainBinding
import mx.kodemia.appclimaedwin.extra.isOnline
import mx.kodemia.appclimaedwin.extra.mensajeEmergente
import mx.kodemia.appclimaedwin.network.WeaterService
import mx.kodemia.appclimaedwin.network.WeatherEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lanzarPeticion()
    }

fun lanzarPeticion(){
    if(isOnline(applicationContext)) {
        lifecycleScope.launch {
            apiResponse(getWeather())
        }

    }else {
        mensajeEmergente(this, getString(R.string.error_internet))
    }
}


    private suspend fun getWeather(): WeatherEntity = withContext(Dispatchers.IO)
    {
        showViews(true)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service:WeaterService =
            retrofit.create(WeaterService::class.java) // llamada para levantar servicio

        //Ahora le pasare long ciudad y el appid
        service.getWheterById(
            3523202L,"metric","sp",
            "7315adee6f294c42a82154aced02171d"
        )
    }

    private fun apiResponse(weatherEntity:WeatherEntity){
        try {
            val temp = "${weatherEntity.main.temp.toInt()}°"
            val cityName = weatherEntity.name
            val country = weatherEntity.sys.country
            val addres = "$cityName, $country"
            val dateNow = Calendar.getInstance().time
            val tempMin = "Min: ${weatherEntity.main.temp_min.toInt()}°"
            val tempMax = "Max: ${weatherEntity.main.temp_max.toInt()}°"


            binding.apply {
                tvTemperatura.text=temp
                tvAddress.text=cityName
                tvDate.text=addres
                //tvDate.text=dateNow
                tvTempMax.text=tempMax
                tvTempMin.text=tempMin
            }

        }catch (exception:Exception){
            showError("Ha oucrrido un error")
        }

    }

    private fun showViews(message:Boolean){

    }
    private fun showError(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}