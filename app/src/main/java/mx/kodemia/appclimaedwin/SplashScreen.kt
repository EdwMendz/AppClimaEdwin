package mx.kodemia.appclimaedwin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.appclimaedwin.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenBinding: ActivitySplashScreenBinding =
            ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)
    }
}