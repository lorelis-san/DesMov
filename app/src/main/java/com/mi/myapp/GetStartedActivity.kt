package com.mi.myapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mi.myapp.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {
    //ete primero
    private var binding: ActivityGetStartedBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // se puede usar este en root
        //setContentView(R.layout.activity_get_started)

        //agregamnos segundo
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.cvGetStarted?.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        //Pa probar:
//        val getStartBtn: CardView = findViewById(R.id.cvGetStarted)
//        getStartBtn.setOnClickListener {
//            startActivity(Intent(this,SignInActivity::class.java))
//            finish()
//        }

//        //ESTA COSA ME GENERA UN BUCLE ESTA A UNA NOMAS DIRE
        val auth = Firebase.auth
//        if (auth.currentUser != null)
//            startActivity(Intent(this, MainActivity::class.java))
//        finish()

        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        finish()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}