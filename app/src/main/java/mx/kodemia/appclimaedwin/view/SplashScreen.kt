package mx.kodemia.appclimaedwin.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import mx.kodemia.appclimaedwin.R
import mx.kodemia.appclimaedwin.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    init()

}
private fun init(){
    val splashScreenBinding: ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)
    setContentView(splashScreenBinding.root)


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.hide(WindowInsets.Type.statusBars())
    } else {
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    val splashAnimation = AnimationUtils.loadAnimation(this@SplashScreen, R.anim.splash)
    splashScreenBinding.ivMoon.animation = splashAnimation

    splashAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            splashScreenBinding.ivMoon.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
}
}
