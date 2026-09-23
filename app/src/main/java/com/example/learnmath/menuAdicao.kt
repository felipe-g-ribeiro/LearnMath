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
        val btnBack = findViewById<Button>(R.id.btnAddBack)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AdicaoSimples::class.java)
            startActivity(intent)
        }
        btnBack.setOnClickListener {
           this.finish()
        }
    }
}
