package com.example.practica1.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica1.R

class MainActivity : AppCompatActivity() {
    private lateinit var formulasFragment: FormulasFragment
    private lateinit var formFragment: FormFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formulasFragment = FormulasFragment()
        formFragment = FormFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.flFormulasFragmentContainer, formulasFragment)
            .add(R.id.flFormFragmentContainer, formFragment)
            .commit()
    }
}