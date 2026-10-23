package com.example.damflix

data class Pelicula(
    val id: Int,
    val titulo: String,
    val director: String,
    val anio: Int,
    var esFavorita: Boolean = false
)
