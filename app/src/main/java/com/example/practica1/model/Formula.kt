package com.example.practica1.model

data class Formula(var id: Long, var titulo: String, var variables: List<Variables>, var tipo: String)

data class Variables(var nombre: String)
