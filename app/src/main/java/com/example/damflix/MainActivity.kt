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
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.LineHeightStyle
import com.example.damflix.ui.theme.DAMFlixTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAMFlixTheme {

                Pantalla()

            }
        }
    }
}

var customModifier = Modifier.padding(all = 15.dp)

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
                    )
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.purple_500),
                contentColor = colorResource(id = R.color.white)
            ) {
                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "irPerfil",
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)


        ) {
            Text(
                modifier = customModifier,
                text = "Usuario: " + stringResource(id = R.string.userName)
            )
            Text(
                modifier = customModifier,
                text = "Rol: " + stringResource(id = R.string.rol)
            )
            Text(
                modifier = customModifier,
                text = "Peliculas vistas: " + stringResource(id = R.string.peliculas)
            )
            Text(
                modifier = customModifier,
                text = "Nº de reseñas: " + stringResource(id = R.string.reseñas)
            )
        }
    }
}
