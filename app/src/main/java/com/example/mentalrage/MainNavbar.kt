package com.example.mentalrage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mentalrage.fragments.FriendsMentalRage
import com.example.mentalrage.fragments.HomeMentalRage
import com.example.mentalrage.fragments.StatsFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class MainNavbar : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navbar)

        val bottomNavBar = findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)

        // Agregar modelos al navbar con IDs únicos
        bottomNavBar.add(
            CurvedBottomNavigation.Model(1, "Inicio", R.drawable.friends)
        )
        bottomNavBar.add(
            CurvedBottomNavigation.Model(2, "Amigos", R.drawable.battle)
        )
        bottomNavBar.add(
            CurvedBottomNavigation.Model(3, "Estadísticas", R.drawable.medalla)
        )

        // Configurar el listener para manejar clics en el navbar
        bottomNavBar.setOnClickMenuListener {
            when (it.id) {
                1 -> { Log.d("MainNavbar", "Clic en 'Inicio'")
                    replaceFragment(StatsFragment())}
                2 -> replaceFragment(HomeMentalRage())
                3 -> replaceFragment(FriendsMentalRage())
            }
        }

        replaceFragment(HomeMentalRage())
        bottomNavBar.show(2)
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainNavBar, fragment)
        transaction.addToBackStack(null)  // Asegúrate de añadir el fragmento a la pila de retroceso si quieres permitir la navegación hacia atrás
        transaction.commit()
    }

}

