package com.example.mentalrage.fragments
import CardClass
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mentalrage.R
import com.example.mentalrage.ui.CardAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeMentalRage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_mental_rage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.RecyclerView)
        val userNameTextView: TextView = view.findViewById(R.id.userName)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val currentUserUID = currentUser.uid

            // Consultar Firestore para obtener el nombre del usuario
            db.collection("jugador")
                .document(currentUserUID)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        // Obtener el nombre del documento y mostrarlo en el TextView
                        val userName = document.getString("name")
                        if (userName != null) {
                            userNameTextView.text = userName

                            // Lista de elementos

        val cardList = listOf(
            CardClass(R.drawable.aleatorio1, "Aleatoria", "#4DF238", "#6EEF5D"),
            CardClass(R.drawable.ciencia1, "Ciencia", "#53A8F3","#2DADC9"),
            CardClass(R.drawable.geo1, "Geografía", "#21B02A","#35D347"),
            CardClass(R.drawable.mates1, "Matematicas", "#CD580E","#FF6D12"),
            CardClass(R.drawable.deportes1, "Deportes", "#518FE3","#0E5DCD"),
            CardClass(R.drawable.cultura_g1, "Cultura General", "#A9BEDB","#0B1D39"),
            CardClass(R.drawable.animals1, "Animales", "#F74F4F","#FF9898"),
            CardClass(R.drawable.entrete1, "Entretenimiento", "#F7FF98","#6AE5FA"),


        )

        val adapter = CardAdapter(cardList) { card ->
            // Acción al hacer clic en una tarjeta
            val intent = Intent(requireContext(), FriendsMentalRage::class.java)
            intent.putExtra("EXTRA_TEXT", card.text)
            startActivity(intent)
        }

        // Configurar LayoutManager y adaptador
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

} } }
}
}

