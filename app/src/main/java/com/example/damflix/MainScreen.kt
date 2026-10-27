package com.example.damflix

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val peliculas = remember {
        mutableStateListOf(
            Pelicula(
                id = 1,
                titulo = "Pelicula",
                director = "Director",
                anio = 2023,
                esFavorita = false
            )
        )
    }

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(

                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500),
                    titleContentColor = colorResource(id = R.color.white)
                ),

                title = {
                    Text(
                        stringResource(id = R.string.app_name)
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

                    IconButton(onClick = { navController.navigate(Ruta.Main.path) }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                        )
                    }

                    IconButton(onClick = { navController.navigate(Ruta.Formulario.path) }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "AñadirPelicula",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                        )
                    }

                    IconButton(onClick = { navController.navigate(Ruta.Catalogo.path) }) {
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
        NavHost(
            navController = navController,
            startDestination = Ruta.Main.path,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Ruta.Main.path) {
                PerfilScreen()
            }
            composable(Ruta.Formulario.path) {
                FormularioScreen(
                    onGuardarPelicula = { nuevaPelicula ->
                        peliculas.add(nuevaPelicula)
                        navController.navigate(Ruta.Catalogo.path)
                    },
                    onVolver = {
                        navController.navigate(Ruta.Main.path)
                    }
                )
            }
            composable(Ruta.Catalogo.path) {
                CatalogoScreen(
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
}

@Composable
private fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Perfil",
            modifier = Modifier
                .padding(top = 100.dp, bottom = 20.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp),
            text = stringResource(id = R.string.userName),
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            modifier = Modifier
                .padding(bottom = 50.dp),
            text = stringResource(id = R.string.rol),
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Vistas: " + stringResource(id = R.string.peliculas),
                modifier = Modifier
                    .padding(start = 30.dp)
            )

            Text(
                text = "Reseñas: " + stringResource(id = R.string.reseñas),
                modifier = Modifier
                    .padding(end = 30.dp)
            )
        }
    }
}
