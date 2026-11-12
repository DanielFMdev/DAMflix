package com.example.damflix.ui.viewmodel

import com.example.damflix.data.local.PeliculaEntity

sealed interface DetalleUiState {
    object Cargando : DetalleUiState
    data class Exito(val pelicula: PeliculaEntity) : DetalleUiState
    data class Error(val mensajeResId: Int) : DetalleUiState
}
