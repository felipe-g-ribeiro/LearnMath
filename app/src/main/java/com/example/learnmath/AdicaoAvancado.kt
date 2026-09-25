package com.example.learnmath

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class AdicaoAvancado : ComponentActivity() {

    var n1: Int = 0
    var n2: Int = 0
    var numeros = IntArray(3) // guarda os valores dos botões

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicao_avancado)

        val btnBack = findViewById<Button>(R.id.btnBack2)
        val btnHome = findViewById<Button>(R.id.btnHome2)
        val btnNovo = findViewById<Button>(R.id.btnaNovo)

        // 🔹 Botão Novo
        btnNovo.setOnClickListener {
            NovoCalculo()
        }

        // 🔹 Botões de resposta
        findViewById<Button>(R.id.btnaN1).setOnClickListener {
            Verificar(num = 0, btn = R.id.btnaN1)
        }
        findViewById<Button>(R.id.btnaN2).setOnClickListener {
            Verificar(num = 1, btn = R.id.btnaN2)
        }
        findViewById<Button>(R.id.btnaN3).setOnClickListener {
            Verificar(num = 2, btn = R.id.btnaN3)
        }

        // 🔹 Botão Voltar
        btnBack.setOnClickListener {
            this.finish()
        }

        // 🔹 Botão Home
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        // 🔹 Gera o primeiro cálculo ao abrir
        NovoCalculo()
    }

    // 🔹 Função para gerar novos números e respostas
    private fun NovoCalculo() {
        Limpar()

        do {
            n1 = Random.nextInt(10, 90)
            n2 = Random.nextInt(10, 90)
        } while (n1 + n2 >= 99)

        val tvN1 = findViewById<TextView>(R.id.tvaN1)
        val tvN2 = findViewById<TextView>(R.id.tvaN2)

        val btnN1 = findViewById<Button>(R.id.btnaN1)
        val btnN2 = findViewById<Button>(R.id.btnaN2)
        val btnN3 = findViewById<Button>(R.id.btnaN3)

        tvN1.text = n1.toString()
        tvN2.text = n2.toString()




        numeros[0] = n1 + n2
        numeros[1] = Random.nextInt(from = 10, until = 100)
        numeros[2] = Random.nextInt(from = 10, until = 100)


        numeros.shuffle()

        btnN1.text = numeros[0].toString()
        btnN2.text = numeros[1].toString()
        btnN3.text = numeros[2].toString()
    }

    // 🔹 Função para limpar cores dos botões
    private fun Limpar() {
        findViewById<Button>(R.id.btnaN1).setBackgroundColor(android.graphics.Color.WHITE)
        findViewById<Button>(R.id.btnaN2).setBackgroundColor(android.graphics.Color.WHITE)
        findViewById<Button>(R.id.btnaN3).setBackgroundColor(android.graphics.Color.WHITE)
        findViewById<TextView>(R.id.tvaN1).setBackgroundColor(android.graphics.Color.TRANSPARENT)
        findViewById<TextView>(R.id.tvaN2).setBackgroundColor(android.graphics.Color.TRANSPARENT)
    }

    // 🔹 Função para verificar se o resultado está certo
    private fun Verificar(num: Int, btn: Int) {
        val soma = n1 + n2
        Limpar()

        val tvN1 = findViewById<TextView>(R.id.tvaN1)
        val tvN2 = findViewById<TextView>(R.id.tvaN2)

        if (numeros[num] == soma) {
            tvN1.setBackgroundColor(android.graphics.Color.GREEN)
            tvN2.setBackgroundColor(android.graphics.Color.GREEN)
            findViewById<Button>(btn).setBackgroundColor(android.graphics.Color.GREEN)
        } else {
            tvN1.setBackgroundColor(android.graphics.Color.RED)
            tvN2.setBackgroundColor(android.graphics.Color.RED)
            findViewById<Button>(btn).setBackgroundColor(android.graphics.Color.RED)
        }
    }
}
