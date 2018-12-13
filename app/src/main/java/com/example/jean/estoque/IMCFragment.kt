package com.example.jean.estoque


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_imc.*
import kotlinx.android.synthetic.main.partial_resultado.*

class IMCFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_imc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtImcPeso?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(texto: CharSequence?, p1: Int, p2: Int, p3: Int) {
                texto?.let { calcularImc() }
            }

        })


        edtAltura?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(texto: CharSequence?, p1: Int, p2: Int, p3: Int) {
                texto?.let { calcularImc() }
            }
        })
    }


    private fun calcularImc() {
        try {
            val peso = edtImcPeso.text.toString().toFloat()
            var altura = edtAltura.text.toString().toFloat()

            if (altura > 100) {
                altura /= 100
            }

            val resultado = (peso / (altura * altura))
            txtResultado.text = String.format("%.2f", resultado)

            mostrarResultado(resultado)

        } catch (e: Exception) {
            txtResultado.text = e.message
        }
    }

    private fun mostrarResultado(resultado: Float) {
        if (resultado < 17) {
            textResultadoFigura.text = "Muito abaixo do peso"
            imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fit3))
            return
        }

        if (resultado in 17.0..18.49) {
            textResultadoFigura.text = "Abaixo do peso"
            imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fit2))
            return
        }

        if (resultado in 18.5..24.99) {
            textResultadoFigura.text = "Peso normal"
            imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fit1))
            return
        }

        if (resultado in 25.0..29.99) {
            textResultadoFigura.text = "Acima do peso"
            imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fat1))
            return
        }

        if (resultado in 30.0..34.99) {
            textResultadoFigura.text = "Obesidade I"
            imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fat2))
            return
        }

        if (resultado in 35.0..39.99) {
            textResultadoFigura.text = "Obesidade II  (servera)"
            imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fat2))
            return
        }

        textResultadoFigura.text = "Obesidade III (mórbida)"
        imgResultadoFigura.setImageDrawable(resources.getDrawable(R.drawable.fat3))
    }
}
