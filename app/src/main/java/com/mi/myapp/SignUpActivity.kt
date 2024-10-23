package com.mi.myapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mi.myapp.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {
    //ete primero
    private var binding: ActivitySignUpBinding? = null

    //----ete despues de todos 22:28
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // se puede usar este en root 13:17
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //---- ete 22:28
        auth = Firebase.auth

        ///setContentView(R.layout.activity_sign_up)
        //agregamnos segundo
        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        //holii 26:04
        binding?.btnSignUp?.setOnClickListener { registerUser() }

        //, si ? , si si  , a ya
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun registerUser() {
        val name = binding?.etSinUpName?.text.toString()
        val email = binding?.etSinUpEmail?.text.toString()
        val password = binding?.etSinUpPassword?.text.toString()
        if (validateForm(name, email, password)) {
            showProgressBar()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        showToast(this, "user id creado okidoki")
                        hideProgressBar()
                        startActivity(Intent(this, MainActivity::class.java))
//                        val rndInt = (1..12).random()
//                        val image = "profilephoto/profilepic${rndInt}.png"
//                        val firebaseUser: FirebaseUser? = task.result.user
//                        val userInfo = User(firebaseUser?.uid, name, firebaseUser?.email, image)
//                        FireStoreClass().registerUser(userInfo)
//                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Oops! Something went wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                    hideProgressBar()
                }
        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                binding?.tilName?.error = "Enter name"
                false
            }

            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }

            TextUtils.isEmpty(password) -> {
                binding?.tilPassword?.error = "Enter password"
                false
            }

            else -> {
                true
            }
        }
    }
}