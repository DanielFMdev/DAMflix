package com.example.damflix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.damflix.R

@Composable
fun PerfilScreen() {
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
