package com.example.damflix.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.damflix.ui.viewmodel.PeliculaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    viewModel: PeliculaViewModel,
    onToggleFavorito: (Int) -> Unit
) {
    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            ListaPeliculas(viewModel, onToggleFavorito)
        }
    }
}

@Composable
fun ListaPeliculas(viewModel: PeliculaViewModel, onToggleFavorito: (Int) -> Unit) {

    val peliculas by viewModel.listaPeliculas.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = peliculas, key = { p -> p.id }) { pelicula ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = pelicula.titulo, modifier = Modifier.weight(1f))
                    Text(text = pelicula.director, modifier = Modifier.weight(1f))
                    Text(text = pelicula.anio.toString(), modifier = Modifier.weight(0.5f))

                    IconButton(onClick = { onToggleFavorito(pelicula.id) }) {
                        Icon(
                            imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (pelicula.esFavorita) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
