package com.example.damflix

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    onGuardarPelicula: (Pelicula) -> Unit,
    onVolver: () -> Unit = {}
) {
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
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        KeyboardCapitalization.Sentences
                    )
                )

                OutlinedTextField(
                    value = director,
                    onValueChange = { director = it },
                    label = { Text(text = stringResource(R.string.directorPelicula)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        KeyboardCapitalization.Words
                    )
                )

                OutlinedTextField(
                    value = anio,
                    onValueChange = { anio = it },
                    label = { Text(text = stringResource(R.string.anioPelicula)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
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
                    onClick = {
                        val nuevaPelicula = Pelicula(
                            id = System.currentTimeMillis().toInt(),
                            titulo = titulo,
                            director = director,
                            anio = anio.toIntOrNull() ?: 0,
                            esFavorita = false
                        )
                        onGuardarPelicula(nuevaPelicula)
                    },
                    enabled = titulo.isNotBlank() && director.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.btnGuardar))
                }

                Button(
                    onClick = onVolver,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.btnVolver))
                }
            }
        }
    }
}