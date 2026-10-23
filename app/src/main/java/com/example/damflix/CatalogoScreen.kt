package com.example.damflix

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen() {
    
    val peliculas = remember { mutableStateListOf(
        Pelicula(1, "Interstellar", "Christopher Nolan", 2014,  false),
        Pelicula(2, "Blade", "Ridley Scott", 1998, false),
        Pelicula(3, "Fast and Furious", "Justin Lin", 2001, false),
    )}

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500),
                    titleContentColor = colorResource(id = R.color.white)
                ),

                title = {
                    Text(
                        stringResource(id = R.string.catalogoScreen)
                    )
                },

                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .padding(20.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    )
                }
            )
        },

        bottomBar = {
            BottomAppBar(

                containerColor = colorResource(id = R.color.purple_500),
                contentColor = colorResource(id = R.color.white)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                        )
                    }

                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "AñadirPelicula",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                        )
                    }

                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "ListaPeliculas",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            ListaPeliculas(
                peliculas = peliculas,
                onToggleFavorito = { peliculaId ->
                    val index = peliculas.indexOfFirst { it.id == peliculaId }
                    if (index != -1) {
                        peliculas[index] = peliculas[index].copy(
                            esFavorita = !peliculas[index].esFavorita
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ListaPeliculas(peliculas: List<Pelicula>, onToggleFavorito: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = peliculas, key = { p -> p.id}) { pelicula ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                )  {
                    Text(text = pelicula.titulo, modifier = Modifier.weight(1f))
                    Text(text = pelicula.director, modifier = Modifier.weight(1f))
                    Text(text = pelicula.anio.toString(), modifier = Modifier.weight(0.5f))

                    IconButton(onClick = {onToggleFavorito(pelicula.id)}) {
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