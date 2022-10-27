package com.example.practica1.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica1.R
import com.example.practica1.view.fragments.ResultadoFragment

class Resultado : AppCompatActivity() {

    private lateinit var resultadoFragment: ResultadoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resultado)

        val bundle = intent.extras

        resultadoFragment = ResultadoFragment()
        resultadoFragment.arguments = bundle
        resultadoFragment.show(supportFragmentManager, getString(R.string.app_formulas_tag_resultado))

    }
}