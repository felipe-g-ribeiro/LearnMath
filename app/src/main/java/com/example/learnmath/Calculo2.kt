package com.example.learnmath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Calculo2 : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var numero1 = 0
    private var numero2 = 0
    private var resultadoCorreto = 0
    private var progresso = 0
    private val totalPerguntas = 10
    private var vidas = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculo2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvNumero1 = view.findViewById<TextView>(R.id.tvM1)
        val tvNumero2 = view.findViewById<TextView>(R.id.tvM2)
        val tvOperador = view.findViewById<TextView>(R.id.tvdOperador)
        val tvResultado = view.findViewById<TextView>(R.id.tvdResultado)
        val tvProgresso = view.findViewById<TextView>(R.id.tvProgresso)
        val barra = view.findViewById<ProgressBar>(R.id.tvdBarraDiv)

        val btn1 = view.findViewById<Button>(R.id.tvdOpcao1)
        val btn2 = view.findViewById<Button>(R.id.tvdOpcao2)
        val btn3 = view.findViewById<Button>(R.id.tvdOpcao3)
        val btn4 = view.findViewById<Button>(R.id.tvdOpcao4)

        val btnProxima = view.findViewById<Button>(R.id.tvdProxima)

        val cor1 = view.findViewById<ImageView>(R.id.coracao1)
        val cor2 = view.findViewById<ImageView>(R.id.coracao2)
        val cor3 = view.findViewById<ImageView>(R.id.coracao3)

        fun atualizarVidas() {
            cor1.visibility = if (vidas >= 1) View.VISIBLE else View.INVISIBLE
            cor2.visibility = if (vidas >= 2) View.VISIBLE else View.INVISIBLE
            cor3.visibility = if (vidas >= 3) View.VISIBLE else View.INVISIBLE
        }

        fun resetarVidas() {
            vidas = 3
            atualizarVidas()
        }

        fun gerarNovaPergunta() {
            tvResultado.text = "?"
            numero2 = Random.nextInt(2, 10)
            val multiplo = Random.nextInt(2, 10)
            numero1 = numero2 * multiplo
            resultadoCorreto = numero1 / numero2

            tvNumero1.text = numero1.toString()
            tvNumero2.text = numero2.toString()
            tvOperador.text = "÷"

            val respostas = mutableListOf(
                resultadoCorreto,
                resultadoCorreto + Random.nextInt(1, 4),
                resultadoCorreto - Random.nextInt(1, 4),
                resultadoCorreto + Random.nextInt(2, 6)
            ).shuffled()

            btn1.text = respostas[0].toString()
            btn2.text = respostas[1].toString()
            btn3.text = respostas[2].toString()
            btn4.text = respostas[3].toString()
        }

        fun verificarResposta(valor: Int) {
            if (valor == resultadoCorreto) {
                tvResultado.text = resultadoCorreto.toString()
                progresso++
                tvProgresso.text = "$progresso/$totalPerguntas"
                barra.progress = (progresso * 100) / totalPerguntas
                Toast.makeText(requireContext(), "Acertou!", Toast.LENGTH_SHORT).show()
            } else {
                vidas--
                atualizarVidas()
                if (vidas == 0) {
                    Toast.makeText(requireContext(), "Tente Outra Vez!", Toast.LENGTH_LONG).show()
                    progresso = 0
                    tvProgresso.text = "$progresso/$totalPerguntas"
                    barra.progress = 0
                    resetarVidas()
                    gerarNovaPergunta()
                } else {
                    Toast.makeText(requireContext(), "Errou!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btn1.setOnClickListener { verificarResposta(btn1.text.toString().toInt()) }
        btn2.setOnClickListener { verificarResposta(btn2.text.toString().toInt()) }
        btn3.setOnClickListener { verificarResposta(btn3.text.toString().toInt()) }
        btn4.setOnClickListener { verificarResposta(btn4.text.toString().toInt()) }

        btnProxima.setOnClickListener {
            gerarNovaPergunta()
        }

        atualizarVidas()
        gerarNovaPergunta()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Calculo2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
