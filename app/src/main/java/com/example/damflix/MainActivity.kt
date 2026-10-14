@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.damflix

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import com.example.damflix.ui.theme.DAMFlixTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAMFlixTheme {
                //Pantalla()
                Formulario()
            }
        }
    }
}

@Composable
fun Pantalla() {
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

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    IconButton(onClick = {  }) {
                        Image(
                            painter = painterResource(id = R.drawable.perfil),
                            contentDescription = "irPerfil",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)

        ) {

            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Perfil",
                modifier = Modifier
                    .padding(all = 30.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 68.dp),
                text = stringResource(id = R.string.userName),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 130.dp),
                text = stringResource(id = R.string.rol),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .padding(innerPadding)
            ) {

                Text(
                    text = "Vistas: " + stringResource(id = R.string.peliculas),
                    modifier = Modifier
                        .padding(horizontal = 65.dp)
                )

                Text(
                    text = "Reseñas: " + stringResource(id = R.string.reseñas),
                )
            }
        }
    }
}

@Composable
fun Formulario() {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = titulo,
                    onValueChange = {titulo = it},
                    label = { Text(text = stringResource(R.string.tituloPelicula)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = director,
                    onValueChange = { director = it },
                    label = { Text(text = stringResource(R.string.directorPelicula)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = anio,
                    onValueChange = { anio = it },
                    label = { Text(text = stringResource(R.string.anioPelicula)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = esVista,
                        onCheckedChange = { esVista = it },
                    )
                    Text(text = stringResource(R.string.peliculaVista))
                }

                Button(
                    onClick = {  },
                    enabled = titulo.isNotBlank() && director.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.btnGuardar))
                }

                Button(
                    onClick = {  },
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.btnVolver))
                }
            }
        }
    }
}