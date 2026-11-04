package com.example.damflix.data.model

data class Pelicula(
    val id: Int = 0,
    val titulo: String,
    val director: String,
    val anio: Int,
    val esVista: Boolean = false,
    val esFavorita: Boolean = false
)
