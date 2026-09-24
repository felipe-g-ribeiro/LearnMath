package com.example.learnmath

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class AdicaoAvancado : ComponentActivity() {

    var n1: Int = 0
    var n2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.adicao_avancado)

        val btnBack = findViewById<Button>(R.id.btnBack2)
        val btnHome = findViewById<Button>(R.id.btnHome2)

        val btnNovo = findViewById<Button>(R.id.btnaNovo)

        btnNovo.setOnClickListener {

            NovoCalculo()
        }

        btnBack.setOnClickListener {
            this.finish()
        }
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        NovoCalculo()
    }

    private fun NovoCalculo() {

        do {
            n1 = Random.nextInt(from = 1, until = 99)
            n2 = Random.nextInt(from = 1, until = 99)
        } while (n1 + n2 >= 99)

        val tvN1 = findViewById<TextView>(R.id.tvaN1)
        val tvN2 = findViewById<TextView>(R.id.tvaN2)

        tvN1.text = n1.toString()
        tvN2.text = n2.toString()

    }
}


