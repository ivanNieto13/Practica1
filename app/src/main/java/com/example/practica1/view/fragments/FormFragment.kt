package com.example.practica1.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.practica1.R
import com.example.practica1.databinding.FragmentFormBinding
import com.example.practica1.model.FormulaViewModel


class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    private var resultado : Double = 0.0

    private var etA: String = ""
    private var etB: String = ""

    private lateinit var triangle: String
    private lateinit var rectangle: String
    private lateinit var cube: String

    private val formulaViewModel: FormulaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFormBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val formulaImageView: ImageView = binding.ivFormula

        formulaViewModel.formula.observe(viewLifecycleOwner) {
            triangle = getString(R.string.app_formulas_forma_triangulo)
            rectangle = getString(R.string.app_formulas_forma_rectangulo)
            cube = getString(R.string.app_formulas_forma_cubo)
            when (it.tipo) {
                triangle -> {
                    formulaImageView.setImageResource(R.drawable.ic_triangle)
                    binding.etA.hint = it.variables[0].nombre
                    binding.etB.hint = it.variables[1].nombre
                    binding.etB.isVisible = true
                }
                rectangle -> {
                    formulaImageView.setImageResource(R.drawable.ic_rectangle)
                    binding.etA.hint = it.variables[0].nombre
                    binding.etB.hint = it.variables[1].nombre
                    binding.etB.isVisible = true
                }
                cube -> {
                    formulaImageView.setImageResource(R.drawable.ic_cube)
                    binding.etA.hint = it.variables[0].nombre
                    binding.etB.isVisible = false
                }
            }
        }

        binding.btnCalcular.setOnClickListener {
            calcular(it)
        }

        return root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun calcular(view: View) {
        val parametros = Bundle()
        val intent = Intent(view.context, Resultado::class.java)
        val formula = formulaViewModel.formula.value

        etA = binding.etA.text.toString()
        etB = binding.etB.text.toString()

        var etADouble: Double
        var etBDouble = 0.0


        if (etA.isEmpty()) {
            binding.etA.error = view.context.getString(R.string.app_formula_error)
            return
        } else {
            etADouble = etA.toDouble()
        }

        if (etB.isEmpty() && formula?.tipo != "cube") {
            binding.etB.error = view.context.getString(R.string.app_formula_error)
            return
        } else if (etB.isNotEmpty()) {
            etBDouble = etB.toDouble()
        }

        when(formula?.tipo) {
            triangle -> {
                resultado = etADouble * etBDouble / 2
            }
            rectangle -> {
                resultado = 2 * (etADouble + etBDouble)
            }
            cube -> {
                resultado = etADouble * etADouble * etADouble
            }
        }

        parametros.apply {
            putDouble(view.context.getString(R.string.app_formula_resultado), resultado)
        }

        intent.putExtras(parametros)

        startActivity(intent)

    }

}