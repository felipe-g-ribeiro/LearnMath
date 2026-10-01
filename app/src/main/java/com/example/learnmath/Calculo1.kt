package com.example.learnmath

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Calculo1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Calculo1 : Fragment() {

    private var n1: Int = 0
    private var n2: Int = 0
    private var resultado: Int = 0


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculo1, container, false)
    }


    // Código a ser implementado em onViewCreated
    // A chamada a elementos passam 1º pela view e depois pelo fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val btnM1 = view.findViewById<Button>(R.id.btnM1)
        val btnM2 = view.findViewById<Button>(R.id.btnM2)
        val btnM3 = view.findViewById<Button>(R.id.btnM3)
        val btnNovo = view.findViewById<Button>(R.id.btnNovoM)

        val roboFeliz = view.findViewById<ImageView>(R.id.imageView3)
        val roboTriste = view.findViewById<ImageView>(R.id.imageView4)
        val txtIncentivo = view.findViewById<TextView>(R.id.txtIncentivo)

        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)



        fun novoCalculo() {


            btnM1.isEnabled = true
            btnM2.isEnabled = true
            btnM3.isEnabled = true

            btnM1.setBackgroundColor(Color.LTGRAY)
            btnM2.setBackgroundColor(Color.LTGRAY)
            btnM3.setBackgroundColor(Color.LTGRAY)

            roboFeliz.visibility = View.GONE
            roboTriste.visibility = View.GONE
            txtIncentivo.visibility = View.GONE

            // Dificuldade baseada no rating
            val dificuldade = ratingBar.rating.toInt()

            // Quanto maior o rating, maior o intervalo dos números
            val maxValor = when (dificuldade) {
                0 -> 5
                1 -> 7
                2 -> 8
                3 -> 9
                4 -> 10
                else -> 10
            }

            n1 = Random.nextInt(1, maxValor)
            n2 = Random.nextInt(1, maxValor)
            resultado = n1 * n2

            val tvM1 = view.findViewById<TextView>(R.id.tvM1)
            val tvM2 = view.findViewById<TextView>(R.id.tvM2)

            tvM1.text = n1.toString()
            tvM2.text = n2.toString()



            val respostas = mutableListOf(
                resultado,
                resultado + Random.nextInt(1, 4),
                (resultado - Random.nextInt(1, 4)).coerceAtLeast(1)
            ).shuffled()

            btnM1.text = respostas[0].toString()
            btnM2.text = respostas[1].toString()
            btnM3.text = respostas[2].toString()
        }


        fun verificarResposta(valor: Int) {


            btnM1.isEnabled = false
            btnM2.isEnabled = false
            btnM3.isEnabled = false

            btnM1.setBackgroundColor(Color.LTGRAY)
            btnM2.setBackgroundColor(Color.LTGRAY)
            btnM3.setBackgroundColor(Color.LTGRAY)


            when (valor) {
                btnM1.text.toString().toInt() -> btnM1.setBackgroundColor(Color.CYAN)
                btnM2.text.toString().toInt() -> btnM2.setBackgroundColor(Color.CYAN)
                btnM3.text.toString().toInt() -> btnM3.setBackgroundColor(Color.CYAN)
            }

            if (valor == resultado) {
                roboFeliz.visibility = View.VISIBLE
                roboTriste.visibility = View.GONE

                txtIncentivo.visibility = View.VISIBLE
                txtIncentivo.text = "Parabéns!"
                txtIncentivo.setTextColor(Color.parseColor("#009739"))

                if (ratingBar.rating < ratingBar.numStars) {
                    ratingBar.rating += 1
                }

            } else {
                roboFeliz.visibility = View.GONE
                roboTriste.visibility = View.VISIBLE

                txtIncentivo.visibility = View.VISIBLE
                txtIncentivo.text = "Foi quase!"
                txtIncentivo.setTextColor(Color.parseColor("#E74C3C"))
            }
        }


        btnM1.setOnClickListener { verificarResposta(btnM1.text.toString().toInt()) }
        btnM2.setOnClickListener { verificarResposta(btnM2.text.toString().toInt()) }
        btnM3.setOnClickListener { verificarResposta(btnM3.text.toString().toInt()) }

        btnNovo.setOnClickListener { novoCalculo() }


        novoCalculo()
    }


}


    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Calculo1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Calculo1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
*/