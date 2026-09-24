package com.example.learnmath

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class menuAdicao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.menu_adicao)

        val btnAdd = findViewById<Button>(R.id.btnAddN1)
        val btnAdd2 = findViewById<Button>(R.id.btnAddN2)
        val btnBack = findViewById<Button>(R.id.btnAddBack)
        val btnAddN3 = findViewById<Button>(R.id.btnAddN3)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AdicaoSimples::class.java)

            intent.putExtra("nivel", "Fácil")


            startActivity(intent)
        }

        btnAdd2.setOnClickListener {

            val intent = Intent(this, AdicaoSimples::class.java)

            intent.putExtra("nivel", "Médio"   )


            startActivity(intent)

        }

        btnAddN3.setOnClickListener {
            val intent = Intent(this, AdicaoAvancado::class.java)

            startActivity(intent)
        }


        btnBack.setOnClickListener {
           this.finish()
        }
    }
}
