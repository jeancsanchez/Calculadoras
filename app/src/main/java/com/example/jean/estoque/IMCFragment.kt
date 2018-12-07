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
            val altura = edtAltura.text.toString().toInt() / 100
            val resultado = (peso / (altura * altura))

            txtResultado.text = resultado.toString()

        } catch (e: Exception) {
            txtResultado.text = 0.toString()
        }
    }
}
