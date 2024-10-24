package com.mi.myapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        val animationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        val splashText: TextView = findViewById(R.id.splash_text)

        splashText.setScaleX(0f); // Comienza en escala 0
        splashText.setScaleY(0f); // Comienza en escala 0
        splashText.setAlpha(0f); // Comienza invisible

        splashText.animate()
            .scaleX(1f) // Regresa a la escala normal
            .scaleY(1f) // Regresa a la escala normal
            .alpha(1f) // Cambia a completamente visible
            .setDuration(1000)
            .setStartDelay(500)
            .start();

       /* animationView.addAnimatorListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
                finish() // Cerramos el Splash para que no vuelva al pulsar "back"
            }


        })*/



        Handler(Looper.getMainLooper()).postDelayed({
            // Iniciar SignInActivity después de la animación
            val intent = Intent(this@SplashActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()  // Cierra el SplashActivity para que no vuelva al pulsar atrás
        }, 4000)
    }
}