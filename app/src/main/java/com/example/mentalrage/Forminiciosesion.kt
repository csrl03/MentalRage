package com.example.mentalrage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Forminiciosesion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_inicio_sesion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backArrowButton: View = findViewById(R.id.back_arrow_button_instance)
        backArrowButton.setOnClickListener {
            // Acción al hacer clic
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.entrada_anim1, R.anim.salida_anim1)
            finish()
        }

        val continueArrowButton: View = findViewById(R.id.continue_arrow_button_instance)
        val emailField = findViewById<EditText>(R.id.et_nombre)
        val passwordField = findViewById<EditText>(R.id.et_contrasena)
        val auth = FirebaseAuth.getInstance()

        continueArrowButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString()

            // Validar que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete ambos campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Iniciar sesión con Firebase Authentication
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Si la autenticación es exitosa, redirigir a la siguiente pantalla
                        val intent2 = Intent(this, MainNavbar::class.java)
                        startActivity(intent2)
                        overridePendingTransition(R.anim.entrada_anim1, R.anim.salida_anim1)
                        finish()
                    } else {
                        // Si los datos no son correctos, mostrar un mensaje de error
                        Toast.makeText(this, "Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
