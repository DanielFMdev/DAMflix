package com.example.damflix

import android.app.Application
import com.example.damflix.data.local.AppDatabase
import com.example.damflix.data.repository.PeliculaRepository

class DAMFlixApp : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { PeliculaRepository(database.peliculaDao()) }
}
