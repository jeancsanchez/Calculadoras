package com.example.jean.estoque


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calculadora.*
import kotlinx.android.synthetic.main.partial_resultado.*

class CalculadoraFragment : Fragment() {

    private var visor = ""

    companion object {
        private const val CLEAR = 0
        private const val SOMAR = "+"
        private const val SUB = "-"
        private const val DIV = "/"
        private const val MULT = "*"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculadora, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnUm.setOnClickListener { digitar(1) }
        btnDois.setOnClickListener { digitar(2) }
        btnTres.setOnClickListener { digitar(3) }
        btnQuatro.setOnClickListener { digitar(4) }
        btnCinco.setOnClickListener { digitar(5) }
        btnSeis.setOnClickListener { digitar(6) }
        btnSete.setOnClickListener { digitar(7) }
        btnOito.setOnClickListener { digitar(8) }
        btnNove.setOnClickListener { digitar(9) }
        btnZero.setOnClickListener { digitar(0) }

        btnSoma.setOnClickListener { digitar(SOMAR) }
        btnSub.setOnClickListener { digitar(SUB) }
        btnMult.setOnClickListener { digitar(MULT) }
        btnIgual.setOnClickListener { mostrarResultado() }
        btnClear.setOnClickListener {
            txtResultado.text = "0"
            visor = "0"
        }
    }

    private fun digitar(digito: Any) {
        if (visor == "0") {
            visor = ""
        }

        visor = visor.plus(digito.toString())
        txtResultado.text = visor
    }

    private fun mostrarResultado() {
        var total: Long = 0

        if (SOMAR in visor) {
            val valores = visor.split(SOMAR)
            valores.forEachIndexed { index, item ->
                if (item.isEmpty().not()) {
                    try {
                        total += item.toLong() + valores[index + 1].toLong()
                    } catch (e: java.lang.IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (SUB in visor) {
            val valores = visor.split(SUB)
            valores.forEachIndexed { index, item ->
                if (item.isEmpty().not()) {
                    try {
                        total += item.toLong() - valores[index + 1].toLong()
                    } catch (e: java.lang.IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (MULT in visor) {
            val valores = visor.split(MULT)
            valores.forEachIndexed { index, item ->
                if (item.isEmpty().not()) {
                    try {
                        total += item.toLong() * valores[index + 1].toLong()
                    } catch (e: IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        visor = total.toString()
        txtResultado.text = visor
    }
}
