# 🎬 DAMFlix

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Android SDK](https://img.shields.io/badge/API-29%2B%20%7C%20SDK%2037-3DDC84.svg?style=flat&logo=android)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20%2F%20Material3-4285F4.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![SQLite](https://img.shields.io/badge/Database-SQLite-003B57.svg?style=flat&logo=sqlite)](https://www.sqlite.org)
[![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Repository-FF6F00.svg?style=flat&logo=android)](https://developer.android.com/topic/architecture)

**DAMFlix** es una aplicación móvil nativa para Android desarrollada en **Kotlin** con **Jetpack Compose** y **Material Design 3**. La aplicación ofrece la gestión completa de una videoteca personal con persistencia de datos local en **SQLite**, arquitectura **MVVM (Model-View-ViewModel)** y **Repository Pattern**.

El proyecto forma parte de la actividad práctica del módulo de *Programación Multimedia y Dispositivos Móviles (PGL)* del Ciclo Formativo de Grado Superior en **Desarrollo de Aplicaciones Multiplataforma (2º DAM)**.

---

## 📌 Características Principales

- 🎨 **Interfaz Declarativa Moderna:** Diseñada íntegramente en **Jetpack Compose** siguiendo las directrices de **Material Design 3**.
- 🔀 **Navegación Modular:** Gestión del flujo de pantallas con `Jetpack Navigation Compose` y enrutamiento fuertemente tipado mediante la clase sellada `Ruta`.
- 💾 **Persistencia de Datos SQLite:** Almacenamiento local mediante `SQLiteOpenHelper` (`damflix.db`) y operaciones asíncronas con Corrutinas de Kotlin en hilos en segundo plano (`Dispatchers.IO`).
- 🏛️ **Arquitectura MVVM + Repository:** Desacoplamiento completo de responsabilidades, gestión del estado con `StateFlow` y recolección reactiva mediante `collectAsStateWithLifecycle()`.
- 👤 **Gestión de Perfil (`PerfilScreen`):** Visualización del avatar de usuario, nombre, rol cinéfilo y métricas personales (películas vistas y reseñas).
- 🎞️ **Catálogo Dinámico (`CatalogoScreen`):** Renderizado eficiente mediante `LazyColumn` y tarjetas `Card`. Permite explorar las películas y alternar el estado de favorita en tiempo real.
- ✍️ **Registro de Películas (`FormularioScreen`):** Formulario con validación en tiempo real (`OutlinedTextField`, `Checkbox`), permitiendo registrar título, director, año de estreno e indicación de película vista.
- 📱 **Soporte Edge-to-Edge:** Experiencia visual adaptada a pantallas completas e inmersivas en Android 10+.

---

## 🏗️ Arquitectura y Flujo de Datos

La aplicación está estructurada respetando la separación de capas recomendada por la guía oficial de arquitectura de Android:

```mermaid
graph TD
    UI[Capa de Presentación / UI\n(MainScreen / Catalogo / Formulario / Perfil)] <-->|Observa StateFlow / Notifica Eventos| VM[PeliculaViewModel\n(ui.viewmodel)]
    VM <-->|Invocaciones Asíncronas| Repo[PeliculaRepository\n(data.repository)]
    Repo <-->|Operaciones SQL / Dispatchers.IO| DB[(SQLite Database\ndamflix.db)]
```

1. **Capa de Datos (`com.example.damflix.data`):**
   - **Local (`data.local`):** `DAMFlixDbHelper` administra la creación y actualizaciones de la base de datos `damflix.db` (Tabla `peliculas`).
   - **Repository (`data.repository`):** `PeliculaRepository` abstrae el acceso a la base de datos local y ejecuta consultas SQL asíncronas (`obtenerTodas`, `insertar`, `actualizar`) sobre `Dispatchers.IO`.
   - **Model (`data.model`):** `Pelicula` data class que representa la entidad de datos del catálogo.

2. **Capa de Presentación / UI (`com.example.damflix.ui`):**
   - **ViewModel (`ui.viewmodel`):** `PeliculaViewModel` retiene y gestiona el estado de la UI exponiendo un `StateFlow<List<Pelicula>>`.
   - **Navegación (`ui.navigation`):** `NavClass` define la clase sellada `Ruta` para gestionar el enrutamiento fuertemente tipado.
   - **Pantallas (`ui.screens`):** Composables reactivos (`MainScreen`, `CatalogoScreen`, `FormularioScreen`, `PerfilScreen`).
   - **Tema (`ui.theme`):** Configuración visual Material 3 (`Color.kt`, `Theme.kt`, `Type.kt`).

---

## 🛠️ Tecnologías y Librerías

| Tecnología / Librería | Descripción / Uso |
| :--- | :--- |
| **Kotlin** | Lenguaje de desarrollo principal (`v2.2.10`) |
| **Jetpack Compose** | UI Toolkit declarativo moderno (`BOM 2026.02.01`) |
| **Material Design 3** | Componentes de diseño (`Scaffold`, `CenterAlignedTopAppBar`, `BottomAppBar`, `Card`, `OutlinedTextField`, `Checkbox`, `Button`) |
| **Navigation Compose** | Enrutamiento de navegación entre pantallas (`v2.10.2`) |
| **Lifecycle & ViewModel Compose** | Gestión de estado y ciclo de vida (`v2.11.0` / `lifecycle-runtime-compose`) |
| **SQLite** | Base de datos relacional integrada (`SQLiteOpenHelper`) |
| **Kotlin Coroutines & Flow** | Concurrencia reactiva y operaciones asíncronas (`StateFlow`) |
| **Gradle Version Catalog** | Gestión centralizada de dependencias con `libs.versions.toml` y Kotlin DSL |

---

## 📐 Especificaciones Técnicas

- **Min SDK:** `29` (Android 10)
- **Target SDK / Compile SDK:** `37` (Android 16)
- **Java Compatibility:** Java 11
- **AGP (Android Gradle Plugin):** `9.4.1`

---

## 📂 Estructura del Proyecto

```text
DAMFlix/
├── app/
│   ├── build.gradle.kts             # Configuración Gradle del módulo de la app
│   └── src/
│       ├── main/
│       │   ├── java/com/example/damflix/
│       │   │   ├── MainActivity.kt       # Punto de entrada (Edge-to-Edge y DAMFlixTheme)
│       │   │   ├── data/                 # Capa de Datos
│       │   │   │   ├── local/
│       │   │   │   │   └── DAMFlixDbHelper.kt # Helper SQLite (Base de datos damflix.db)
│       │   │   │   ├── model/
│       │   │   │   │   └── Pelicula.kt       # Entidad de modelo (Data Class)
│       │   │   │   └── repository/
│       │   │       └── PeliculaRepository.kt # Acceso asíncrono a datos
│       │   │   └── ui/                   # Capa de Interfaz de Usuario / Presentación
│       │   │       ├── navigation/
│       │   │       │   └── NavClass.kt       # Rutas de navegación (Sealed Class)
│       │   │       ├── screens/
│       │   │       │   ├── MainScreen.kt     # Contenedor principal (Scaffold, TopBar, BottomBar, NavHost)
│       │   │       │   ├── CatalogoScreen.kt # Vista de catálogo (LazyColumn, Card, Favoritos)
│       │   │       │   ├── FormularioScreen.kt # Vista de formulario para registrar películas
│       │   │       │   └── PerfilScreen.kt   # Vista de perfil de usuario y métricas
│       │   │       ├── theme/            # Tema Material 3
│       │   │       │   ├── Color.kt
│       │   │       │   ├── Theme.kt
│       │   │       │   └── Type.kt
│       │   │       └── viewmodel/
│       │   │           └── PeliculaViewModel.kt # ViewModel con StateFlow para gestión de estado
│       │   ├── res/                      # Recursos estáticos (Imágenes, Strings, Colores, Iconos)
│       │   └── AndroidManifest.xml       # Manifiesto de la aplicación Android
├── gradle/
│   └── libs.versions.toml               # Catálogo centralizado de versiones y dependencias
├── build.gradle.kts                     # Gradle build script de nivel raíz
└── README.md                            # Documentación completa del proyecto
```

---

## 🚀 Requisitos e Instalación

### Requisitos Previos

1. **Android Studio** (versión compatible con Kotlin 2.x y AGP 9.x).
2. **JDK 11** o superior.
3. Dispositivo físico o emulador con **Android 10 (API 29)** o superior.

### Pasos para Ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/DAMFlix.git
   cd DAMFlix
   ```

2. **Abrir en Android Studio:**
   - Abre Android Studio y selecciona `Open an Existing Project`.
   - Selecciona la carpeta `DAMFlix`.

3. **Sincronizar Gradle:**
   - Pulsa en `Sync Project with Gradle Files` para descargar las dependencias.

4. **Ejecutar la Aplicación:**
   - Selecciona un emulador o dispositivo físico conectado.
   - Presiona `Run` `(Shift + F10)` o el botón `▶`.

---

## 👨‍💻 Autor

- **Daniel Flores Medina**
- **Curso:** 2º Curso de Desarrollo de Aplicaciones Multiplataforma (2º DAM)
- **Módulo:** Programación Multimedia y Dispositivos Móviles (PGL)

---

## 📄 Licencia

Este proyecto se distribuye bajo licencia académica para uso didáctico dentro del marco formativo del Grado Superior DAM.
