package com.example.damflix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.damflix.R
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import kotlinx.coroutines.launch
import com.example.damflix.data.repository.PeliculaRepository
import com.example.damflix.ui.navigation.Ruta
import com.example.damflix.ui.viewmodel.PeliculaViewModel
import com.example.damflix.data.local.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val opcionesMenu = listOf(Ruta.Main, Ruta.Catalogo, Ruta.Formulario)

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { PeliculaRepository(db.peliculaDao()) }
    val viewModel = remember { PeliculaViewModel(repository) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                
                Box(modifier = Modifier.fillMaxWidth().padding(24.dp),
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.align(Alignment.Center),
                        color = colorResource(id = R.color.purple_500)
                    )
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                opcionesMenu.forEach { opcion ->
                    NavigationDrawerItem(
                        icon = { Icon(opcion.icon, contentDescription = opcion.label) },
                        label = { Text(opcion.label) },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = colorResource(id = R.color.purple_500),
                            selectedTextColor = colorResource(id = R.color.white),
                            selectedIconColor = colorResource(id = R.color.white)
                        ),
                        selected = rutaActual == opcion.path,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            if (rutaActual != opcion.path) {
                                navController.navigate(opcion.path) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(

            topBar = {
                CenterAlignedTopAppBar(

                    colors = topAppBarColors(
                        containerColor = colorResource(id = R.color.purple_500),
                        titleContentColor = colorResource(id = R.color.white),
                        navigationIconContentColor = colorResource(id = R.color.white)
                    ),

                    title = {
                        Text(
                            stringResource(id = R.string.app_name)
                        )
                    },

                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
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
                        viewModel = viewModel,
                        onGuardarPelicula = {
                            navController.navigate(Ruta.Catalogo.path)
                        },
                        onVolver = {
                            navController.navigate(Ruta.Main.path)
                        }
                    )
                }
                composable(Ruta.Catalogo.path) {
                    CatalogoScreen(
                        viewModel = viewModel,
                        onToggleFavorito = { peliculaId ->
                            val pelicula =
                                viewModel.listaPeliculas.value.find { it.id == peliculaId }
                            if (pelicula != null) {
                                viewModel.conmutarFavorito(pelicula)
                            }
                        }
                    )
                }
            }
        }
    }
}
