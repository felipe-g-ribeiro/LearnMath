package com.example.learnmath

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import kotlin.random.Random

class Subtracao : ComponentActivity() {

    private var n1 = 0
    private var n2 = 0
    private var respostas = IntArray(3)

    private var nivel = 0
    private var acertosSeguidos = 0
    private var errosSeguidos = 0

    private lateinit var barra: ProgressBar
    private var progresso = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.subtracao)

        barra = findViewById(R.id.progressBar)
        barra.max = 100
        barra.progress = 0

        findViewById<Button>(R.id.subInicio).setOnClickListener {
            this.finish()
        }

        findViewById<Button>(R.id.btnS1).setOnClickListener { Verificar(0, R.id.btnS1) }
        findViewById<Button>(R.id.btnS2).setOnClickListener { Verificar(1, R.id.btnS2) }
        findViewById<Button>(R.id.btnS3).setOnClickListener { Verificar(2, R.id.btnS3) }

        findViewById<Button>(R.id.btnNovoS).setOnClickListener {
            NovoCalculo()
        }

        NovoCalculo()
    }

    private fun NovoCalculo() {
        Limpar()

        val tvNivel = findViewById<TextView>(R.id.tvNivel)

        val (min, max) = when (nivel) {
            0 -> Pair(1, 20).also { tvNivel.text = "Nível: Fácil" }
            1 -> Pair(20, 60).also { tvNivel.text = "Nível: Médio" }
            else -> Pair(60, 99).also { tvNivel.text = "Nível: Difícil" }
        }

        n1 = Random.nextInt(min, max)
        n2 = Random.nextInt(min, n1 + 1)

        findViewById<TextView>(R.id.tvS1).text = n1.toString()
        findViewById<TextView>(R.id.tvS2).text = n2.toString()

        val resultado = n1 - n2

        val errada1 = resultado + Random.nextInt(1, 10)
        var errada2 = resultado - Random.nextInt(1, 10)
        if (errada2 < 0) errada2 = resultado + Random.nextInt(2, 12)

        val lista = mutableSetOf(resultado, errada1, errada2)
        while (lista.size < 3) {
            val nova = resultado + Random.nextInt(1, 15)
            if (nova >= 0) lista.add(nova)
        }

        respostas = lista.toIntArray()
        respostas.shuffle()

        findViewById<Button>(R.id.btnS1).text = respostas[0].toString()
        findViewById<Button>(R.id.btnS2).text = respostas[1].toString()
        findViewById<Button>(R.id.btnS3).text = respostas[2].toString()
    }

    private fun Limpar() {

        findViewById<Button>(R.id.btnS1).setTextColor(android.graphics.Color.WHITE)
        findViewById<Button>(R.id.btnS2).setTextColor(android.graphics.Color.WHITE)
        findViewById<Button>(R.id.btnS3).setTextColor(android.graphics.Color.WHITE)

        findViewById<TextView>(R.id.tvS1).setBackgroundColor(android.graphics.Color.TRANSPARENT)
        findViewById<TextView>(R.id.tvS2).setBackgroundColor(android.graphics.Color.TRANSPARENT)
    }

    private fun Verificar(pos: Int, btnId: Int) {
        val resultado = n1 - n2
        Limpar()

        val tv1 = findViewById<TextView>(R.id.tvS1)
        val tv2 = findViewById<TextView>(R.id.tvS2)
        val botao = findViewById<Button>(btnId)

        if (respostas[pos] == resultado) {

            botao.setTextColor(android.graphics.Color.GREEN)

            acertosSeguidos++
            errosSeguidos = 0

            progresso += 20
            if (progresso > 100) progresso = 100
            barra.progress = progresso

            if (acertosSeguidos >= 5 && nivel < 2) {
                nivel++
                acertosSeguidos = 0

                progresso = 0
                barra.progress = progresso
            }

        } else {

            botao.setTextColor(android.graphics.Color.RED)

            errosSeguidos++
            acertosSeguidos = 0

            progresso -= 10
            if (progresso < 0) progresso = 0
            barra.progress = progresso

            if (errosSeguidos >= 5 && nivel > 0) {
                nivel--
                errosSeguidos = 0

                progresso = 0
                barra.progress = progresso
            }
        }
    }
}
