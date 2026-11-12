package com.example.damflix.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.damflix.ui.viewmodel.DetallePeliculaViewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.damflix.R
import com.example.damflix.ui.viewmodel.DetalleUiState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme

@Composable
fun PantallaDetalleScreen(
    peliculaId: Int,
    viewModel: DetallePeliculaViewModel,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
){
    LaunchedEffect(peliculaId) {
        viewModel.cargarDetallePelicula(peliculaId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onVolver) {
            Text(text = stringResource(R.string.volverCatalogo))
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {

            is DetalleUiState.Cargando -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = stringResource(R.string.cargarDatos))
                    }
                }
            }

            is DetalleUiState.Exito -> {
                Card(modifier = Modifier.fillMaxWidth()) {

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = state.pelicula.titulo,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Director/a:  ${state.pelicula.director}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = "Año:  ${state.pelicula.anio}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = if (state.pelicula.esVista) stringResource(R.string.peliculaVista) else stringResource(R.string.peliculaPendiente),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            is DetalleUiState.Error -> {
                Text(
                    text = stringResource(state.mensajeResId),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}