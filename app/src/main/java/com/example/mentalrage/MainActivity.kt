package com.example.mentalrage
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvRegistrarse: TextView = findViewById(R.id.tv_registrarse)
        val buttonToLogin: Button = findViewById(R.id.login1)

        // Establecer el listener para el botón
        buttonToLogin.setOnClickListener {
            // Crear un Intent para navegar
            val intent = Intent(this, Forminiciosesion::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.entrada_anim1, R.anim.salida_anim1)
    }
        // Configura el click listener
        tvRegistrarse.setOnClickListener {

            val intent2 = Intent(this, Formregister::class.java)
            startActivity(intent2)
        }

    }
}