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
import com.google.firebase.firestore.FirebaseFirestore

class Formregister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formregister)

        val db = FirebaseFirestore.getInstance()
        val backArrowButton: View = findViewById(R.id.back_arrow_button_instance)
        val continueArrowButton: View = findViewById(R.id.continue_arrow_button_instance)
        val auth = FirebaseAuth.getInstance()
        val nameField = findViewById<EditText>(R.id.et_nombre)
        val emailField = findViewById<EditText>(R.id.et_email)
        val passwordField = findViewById<EditText>(R.id.et_contrasena)
        val confirmPasswordField = findViewById<EditText>(R.id.et_confirm_contrasena)
        val loginButton = findViewById<View>(R.id.continue_arrow_button)

        // Botón de retroceso
        backArrowButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.entrada_anim1, R.anim.salida_anim1)
            finish()
        }

        // Botón de registro
        loginButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString()
            val confirmPassword = confirmPasswordField.text.toString()

            // Validar campos vacíos
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar contraseñas
            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear usuario en Firebase Auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            // Datos del usuario
                            val userData = hashMapOf(
                                "name" to name,
                                "email" to email,
                                "uid" to user.uid
                            )

                            // Guardar en Firestore
                            db.collection("jugador").document(user.uid)
                                .set(userData)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show()
                                    // Redirigir a la siguiente pantalla
                                    val intent2 = Intent(this, MainNavbar::class.java)
                                    startActivity(intent2)
                                    overridePendingTransition(R.anim.entrada_anim1, R.anim.salida_anim1)
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this, "Error al guardar datos: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                    } else {
                        // Mostrar error si falla la creación del usuario
                        Toast.makeText(
                            this,
                            "Error al crear usuario: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        // Ajustar las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
