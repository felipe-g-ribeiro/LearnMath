package com.example.learnmath

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import kotlin.random.Random


// Surgir dois numeros aleatorios que a soma vai até ao 9
// O utilizador clica num botao de 1 a 9 e abre a funcao
// Verifica se pressionado corresponde ao total
// Se esta correto metem as duas caixas a verde, caso vermelho
// Botao Novo = novo calculo
// Adicionar novas funcionalidas
class AdicaoSimples : ComponentActivity() {

    var n1: Int = 0
    var n2: Int = 0

    private var nivel: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.adicao_simples)

        nivel = intent.getStringExtra("nivel") ?: ""
        findViewById<TextView>(R.id.tvInf).text = "Nivel: $nivel"

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnHome = findViewById<Button>(R.id.btnHome)

        val tvN1 = findViewById<TextView>(R.id.tvN1)
        val tvN2 = findViewById<TextView>(R.id.tvN2)

        val botoes = listOf(
            findViewById<Button>(R.id.btnN1),
            findViewById<Button>(R.id.btnN2),
            findViewById<Button>(R.id.btnN3),
            findViewById<Button>(R.id.btnN4),
            findViewById<Button>(R.id.btnN5),
            findViewById<Button>(R.id.btnN6),
            findViewById<Button>(R.id.btnN7),
            findViewById<Button>(R.id.btnN8),
            findViewById<Button>(R.id.btnN9)
        )
        botoes.forEachIndexed { index, botao ->
            botao.setOnClickListener {
                verificarResultado(index + 1, botao.id)
            }
        }


        NovoCalculo()

        val btnNovo = findViewById<Button>(R.id.btnNovo)

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



    }
    private fun NovoCalculo(){

        var max:Int = 9
        if(nivel == "Fácil"){
            max = 5
        }


        do {
            n1 = Random.nextInt(from = 1, until = max)
            n2 = Random.nextInt(from = 1, until = max)
        } while (n1 + n2 >= 10)

        val tvN1 = findViewById<TextView>(R.id.tvN1)
        val tvN2 = findViewById<TextView>(R.id.tvN2)

        tvN1.text = n1.toString()
        tvN2.text = n2.toString()

        tvN1.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        tvN2.setBackgroundColor(android.graphics.Color.TRANSPARENT)

    }

    private fun verificarResultado(valor: Int, botaoId: Int) {
        val soma = n1 + n2

        val tvN1 = findViewById<TextView>(R.id.tvN1)
        val tvN2 = findViewById<TextView>(R.id.tvN2)

        if (valor == soma) {
            tvN1.setBackgroundColor(android.graphics.Color.GREEN)
            tvN2.setBackgroundColor(android.graphics.Color.GREEN)
            findViewById<Button>(botaoId).setBackgroundColor(android.graphics.Color.GREEN)


            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show()


        } else {
            tvN1.setBackgroundColor(android.graphics.Color.RED)
            tvN2.setBackgroundColor(android.graphics.Color.RED)
            findViewById<Button>(botaoId).setBackgroundColor(android.graphics.Color.RED)

            Toast.makeText(this, "Tente Novamente!", Toast.LENGTH_SHORT).show()
        }

    }
}








