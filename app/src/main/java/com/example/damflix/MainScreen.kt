package com.example.damflix

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Perfil",
                modifier = Modifier
                    .padding(vertical = 20.dp)
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
                        .padding(horizontal = 10.dp)
                )

                Text(
                    text = "Reseñas: " + stringResource(id = R.string.reseñas),
                )
            }
        }
    }
}