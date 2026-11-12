package com.example.damflix.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Ruta(val path: String, val label: String, val icon: ImageVector) {
    object Main: Ruta(
        path = "main",
        label = "Main",
        icon = Icons.Default.Home
    )

    object Formulario: Ruta(
        path = "formulario",
        label = "Formulario",
        icon = Icons.Default.Add
    )

    object Catalogo: Ruta(
        path = "catalogo",
        label = "Catalogo",
        icon = Icons.AutoMirrored.Filled.List
    )

    object Detalle: Ruta(
        path = "detalle/{peliculaId}",
        label = "Detalle",
        icon = Icons.Filled.Search
    ) {
        fun crearRuta(peliculaId: Int) = "detalle/$peliculaId"
    }
}
