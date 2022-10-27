package com.example.practica1.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.practica1.R
import com.example.practica1.view.activities.MainActivity


class ResultadoFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val resultado =
                arguments?.getDouble(it.getString(R.string.app_formula_resultado)).toString()

            builder.setMessage(resultado)
                .setPositiveButton(
                    it.getString(R.string.app_home)
                ) { dialog, id ->
                    val intent = Intent(it, MainActivity::class.java)
                    startActivity(intent)
                }
            builder.setTitle(it.getString(R.string.app_resultado_titulo))
            builder.create()
        } ?: throw IllegalStateException("")
    }

}