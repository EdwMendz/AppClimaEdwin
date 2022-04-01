package mx.kodemia.appclimaedwin.ui.view.currentWeather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import coil.load
import mx.kodemia.appclimaedwin.R
import mx.kodemia.appclimaedwin.databinding.ActivityMainBinding
import mx.kodemia.appclimaedwin.ui.view.oneCall.OnecallActivityView
import mx.kodemia.appclimaedwin.ui.viewModel.currentWeather.MianActivityViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivityView : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    //viewModel
    val viewModel: MianActivityViewModel by viewModels()

    //uniSimbol
    val unitSymbol = "°F"

    private var units = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observadores()
        button()

    }


    private fun observadores() {
        val id:Long = 3523202L
        viewModel.getWeather(id,getString(R.string.units),getString(R.string.lang),getString(R.string.api_key))
        //Progresbar
        viewModel.cargando.observe(this) { cargando ->
            cargando(cargando)
        }
        //ViewModelweathereEntity
        viewModel.weatherEntity.observe(this) { weatherEntity ->
            val temp = "${weatherEntity.main.temp.toInt()}$unitSymbol"
            val cityName = weatherEntity.name
            val country = weatherEntity.sys.country
            val addres = "$cityName, $country"
            val weatherDescription = weatherEntity.weather[0].description
            var status = ""
            if (weatherDescription.isNotEmpty()) {
                status = weatherEntity.weather[0].description.uppercase()
            }
            val dt = weatherEntity.dt

            val updateAt = getString(R.string.updated) + SimpleDateFormat(
                "hh:mm a",
                Locale.ENGLISH
            ).format(Date(dt * 1000))

            val tempMin = "Min: ${weatherEntity.main.temp_min.toInt()}°"
            val tempMax = "Max: ${weatherEntity.main.temp_max.toInt()}°"
            val icon = weatherEntity.weather[0].icon
            val iconUrl = "https://openweathermap.org/img/w/$icon.png"
            val sunrise = weatherEntity.sys.sunrise
            val sunriseFormat =
                SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
            val sunset = weatherEntity.sys.sunset
            val sunsetFormat =
                SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
            val wind = "${weatherEntity.wind.speed} km/h"
            val pressure = "${weatherEntity.main.pressure} mb"
            val humidity = "${weatherEntity.main.pressure}%"
            val feelsLike =
                getString(R.string.sensacion) + weatherEntity.main.feels_like.toInt() + unitSymbol
            binding.apply {
                tvAddress.text = addres
                tvDate.text = updateAt
                tvTemperatura.text = temp
                tvStatus.text = status
                tvTempMin.text = tempMin
                tvTempMax.text = tempMax
                tvSunrise.text = sunriseFormat
                tvSunset.text = sunsetFormat
                tvWind.text = wind
                tvPressure.text = pressure
                tvHumidity.text = humidity
                tvFeelsLike.text = feelsLike

                ivLogo1.load(iconUrl)


            }
            //peticion
        }
    }


    //Mostrar ProgresBar e imagenes
    private fun cargando(cargando: Boolean) {
        binding.apply {
            if (cargando) {
                progressBarIndicator.visibility = View.VISIBLE
                ivSun.visibility = View.GONE
                detailsContainer.visibility = View.GONE
                cardContainer.visibility = View.GONE
                btnInicio.visibility = View.GONE
            } else {
                progressBarIndicator.visibility = View.GONE
                ivSun.visibility = View.VISIBLE
                detailsContainer.visibility = View.VISIBLE
                cardContainer.visibility = View.VISIBLE
                btnInicio.visibility = View.VISIBLE
            }
        }
    }


    //IniciarBinding
    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //LanzarACtivityButton
    private fun lanzarActivity(){
        val intent= Intent(this@MainActivityView, OnecallActivityView::class.java)
        startActivity(intent)
    }

    //lanzar button
    private fun button(){
        binding.btnInicio.setOnClickListener {
            lanzarActivity()
        }
    }
}