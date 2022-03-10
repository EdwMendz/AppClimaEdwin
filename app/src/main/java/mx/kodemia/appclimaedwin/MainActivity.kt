package mx.kodemia.appclimaedwin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
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
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var units = false

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lanzarPeticion()
    }

fun lanzarPeticion(){
    if(isOnline(applicationContext)) {
        showViews(true, false)
        lifecycleScope.launch {
            apiResponse(getWeather())
        }

    }else {
        mensajeEmergente(this, getString(R.string.error_internet))
        binding.detailsContainer.isVisible = false
    }
}


    private suspend fun getWeather(): WeatherEntity = withContext(Dispatchers.IO)
    {
        showViews(true,false)

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
        var unitSymbol = "°C"
        if(units){
            unitSymbol = "°F"
        }

        try {
            val temp = "${weatherEntity.main.temp.toInt()}$unitSymbol"
            val cityName = weatherEntity.name
            val country = weatherEntity.sys.country
            val addres = "$cityName, $country"
            val weatherDescription = weatherEntity.weather[0].description
            var status = ""
            if (weatherDescription.isNotEmpty()){
                 status = weatherEntity.weather[0].description.uppercase()
            }
            val dt = weatherEntity.dt

            val updateAt = getString(R.string.updated)+ SimpleDateFormat("hh:mm a",
            Locale.ENGLISH).format(Date(dt*1000))
           // val dateNow = Calendar.getInstance().time
            val tempMin = "Min: ${weatherEntity.main.temp_min.toInt()}°"
            val tempMax = "Max: ${weatherEntity.main.temp_max.toInt()}°"
            val icon = weatherEntity.weather[0].icon
            val iconUrl = "https://openweathermap.org/img/w/$icon.png"
            val sunrise = weatherEntity.sys.sunrise
            val sunriseFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
            val sunset = weatherEntity.sys.sunset
            val sunsetFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
            val wind = "${weatherEntity.wind.speed} km/h"
            val pressure = "${weatherEntity.main.pressure} mb"
            val humidity = "${weatherEntity.main.pressure}%"
            val feelsLike = getString(R.string.sensacion) + weatherEntity.main.feels_like.toInt() + unitSymbol
            binding.apply {
                tvAddress.text = addres
                tvDate.text = updateAt
                tvTemperatura.text = temp
                tvStatus.text = status
                tvTempMin.text = tempMin
                tvTempMax.text = tempMax
                tvSunrise.text=sunriseFormat
                tvSunset.text = sunsetFormat
                tvWind.text = wind
                tvPressure.text = pressure
                tvHumidity.text = humidity
                tvFeelsLike.text = feelsLike

                ivLogo1.load(iconUrl)
                detailsContainer.isVisible = true
                cardContainer.isVisible = true
                showViews(false, true)
            }
        }catch (exception:Exception){
            showError("Ha oucrrido un error")
            showViews(false,true)
        }

    }

    private fun showViews(progresVisible:Boolean, imageVisible:Boolean){
        binding.progressBarIndicator.isVisible = progresVisible
        binding.ivSun.isVisible = imageVisible
    }
    private fun showError(message2:String){
        Toast.makeText(this,message2,Toast.LENGTH_LONG).show()
    }
}