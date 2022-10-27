package com.example.practica1.view.activities

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.practica1.databinding.FragmentFormulasBinding
import com.example.practica1.model.FormulaViewModel


class FormulasFragment : Fragment() {
    private var _binding: FragmentFormulasBinding? = null
    private val formulaViewModel: FormulaViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFormulasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formulas = ArrayList<String>()

        for (formula in formulaViewModel.getFormulas()) {
            formulas.add(formula.titulo)
        }

        with(binding) {
            spinner.adapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_list_item_1,
                formulas
            )
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                formulaViewModel.selectItem(position)

                if (FormFragment().isAdded) {
                    if (savedInstanceState != null) {
                        parentFragmentManager.putFragment(
                            savedInstanceState,
                            "FormFragment",
                            FormFragment()
                        )
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}