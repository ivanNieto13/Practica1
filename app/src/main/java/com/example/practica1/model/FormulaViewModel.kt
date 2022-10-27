package com.example.practica1.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practica1.R


class FormulaViewModel(application: Application) : AndroidViewModel(application) {
    private val formulas = listOf(
        Formula(
            1,
            getString(R.string.app_formulas_area_triangulo),
            listOf(
                Variables(getString(R.string.app_formulas_base)),
                Variables(getString(R.string.app_formulas_altura))
            ),
            "triangle"
        ),
        Formula(
            2,
            getString(R.string.app_formulas_perimetro_rectangulo),
            listOf(
                Variables(getString(R.string.app_formulas_base)),
                Variables(getString(R.string.app_formulas_altura))
            ),
            "rectangle"
        ),
        Formula(
            3,
            getString(R.string.app_formulas_volumen_cubo),
            listOf(Variables(getString(R.string.app_formulas_longitud))),
            "cube"
        ),
    )
    private val _selectedFormula = MutableLiveData<Formula>().apply {
        value = formulas[0]
    }
    var formula: LiveData<Formula> = _selectedFormula

    fun selectItem(position: Int) {
        _selectedFormula.value = formulas[position]
    }

    fun getFormulas(): List<Formula> {
        return formulas
    }

    fun getString(some_string: Int): String {
        return getApplication<Application>().resources.getString(some_string)
    }
}
