package mx.kodemia.appclimaedwin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.appclimaedwin.databinding.ActivityMainBinding
import mx.kodemia.appclimaedwin.extra.estaEnLinea
import mx.kodemia.appclimaedwin.extra.mensajeEmergente
import mx.kodemia.appclimaedwin.network.WeatherEntity

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lanzarPeticion()
    }






fun lanzarPeticion(){
    if(estaEnLinea(applicationContext)) {

    }else {
        mensajeEmergente(this, getString(R.string.error_internet))
    }
}




    private fun apiResponse(weatherEntity:WeatherEntity){


    }
}