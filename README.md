# 🎬 DAMFlix

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Android SDK](https://img.shields.io/badge/API-29%2B%20%7C%20SDK%2037-3DDC84.svg?style=flat&logo=android)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20%2F%20Material3-4285F4.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Room ORM](https://img.shields.io/badge/Database-Room%202.8.5-003B57.svg?style=flat&logo=sqlite)](https://developer.android.com/training/data-storage/room)
[![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Repository-FF6F00.svg?style=flat&logo=android)](https://developer.android.com/topic/architecture)

**DAMFlix** es una aplicación móvil nativa para Android desarrollada en **Kotlin** con **Jetpack Compose** y **Material Design 3**. La aplicación ofrece la gestión completa de una videoteca personal con persistencia de datos local mediante el ORM **Room**, arquitectura **MVVM (Model-View-ViewModel)** y **Repository Pattern**.

El proyecto forma parte de la actividad práctica del módulo de *Programación Multimedia y Dispositivos Móviles (PGL)* del Ciclo Formativo de Grado Superior en **Desarrollo de Aplicaciones Multiplataforma (2º DAM)**.

---

## 📌 Características Principales

- 🎨 **Interfaz Declarativa Moderna:** Diseñada íntegramente en **Jetpack Compose** siguiendo las directrices de **Material Design 3**.
- 🔀 **Navegación Modular y Enrutamiento Tipado:** Gestión del flujo de pantallas con `Jetpack Navigation Compose`, barra lateral de navegación (`ModalNavigationDrawer`), barra inferior (`NavigationBar`) y rutas fuertemente tipadas (`Ruta.Main`, `Ruta.Catalogo`, `Ruta.Formulario`, `Ruta.Detalle`).
- 💾 **Persistencia de Datos con Room ORM:** Almacenamiento local SQLite gestionado mediante **Room (v2.8.5)** con procesamiento KSP, consultas reactivas con `Flow` y ejecuciones en segundo plano (`Dispatchers.IO`).
- 🏛️ **Arquitectura MVVM + Repository:** Desacoplamiento de responsabilidades, clase personalizada de aplicación (`DAMFlixApp`) para inyección de dependencias, gestión del estado con `StateFlow` y recolección reactiva con `collectAsStateWithLifecycle()`.
- 🔍 **Pantalla de Detalle (`PantallaDetalleScreen`):** Consulta individualizada de películas con gestión de estados mediante `DetalleUiState` (`Cargando`, `Exito`, `Error`) y `DetallePeliculaViewModelFactory`.
- 👤 **Gestión de Perfil (`PerfilScreen`):** Visualización del avatar de usuario, nombre, rol cinéfilo y métricas personales (películas vistas y reseñas).
- 🎞️ **Catálogo Dinámico (`CatalogoScreen`):** Renderizado eficiente mediante `LazyColumn` y tarjetas interactivos (`Card`). Permite navegar a la vista de detalle y alternar el estado de favorita en tiempo real.
- ✍️ **Registro de Películas (`FormularioScreen`):** Formulario con validación en tiempo real (`OutlinedTextField`, `Checkbox`), permitiendo registrar título, director, año de estreno e indicación de película vista.
- 📱 **Soporte Edge-to-Edge:** Experiencia visual adaptada a pantallas completas e inmersivas en Android 10+.

---

## 🏗️ Arquitectura y Flujo de Datos

La aplicación está estructurada respetando la separación de capas recomendada por la guía oficial de arquitectura de Android:

```mermaid
graph TD
    App[DAMFlixApp\n(Application Class)] -->|Proporciona| DB[(Room Database\ndamflix_room.db)]
    App -->|Proporciona| Repo[PeliculaRepository\n(data.repository)]
    DB <-->|PeliculaDao| Repo
    Repo <-->|Operaciones Reactivas / Flow| VM1[PeliculaViewModel\n(ui.viewmodel)]
    Repo <-->|Consultas por ID| VM2[DetallePeliculaViewModel\n(ui.viewmodel)]
    VM1 <-->|Observa StateFlow / Eventos| UI1[CatalogoScreen / FormularioScreen]
    VM2 <-->|Observa DetalleUiState| UI2[PantallaDetalleScreen]
```

1. **Capa de Datos (`com.example.damflix.data`):**
   - **Local (`data.local`):** `AppDatabase` (Base de datos Room `damflix_room.db`), `PeliculaDao` (Interfaz DAO con consultas `@Query`, `@Insert`, `@Update`, `@Delete`) y `PeliculaEntity` (Entidad `@Entity(tableName = "peliculas")`).
   - **Repository (`data.repository`):** `PeliculaRepository` abstrae el acceso a los datos del DAO Room.
   - **Model (`data.model`):** `Pelicula` data class que representa la modelo de película.

2. **Capa de Presentación / UI (`com.example.damflix.ui`):**
   - **ViewModel (`ui.viewmodel`):** `PeliculaViewModel` (gestión del catálogo) y `DetallePeliculaViewModel` (gestión de la vista de detalle con `DetalleUiState` y `DetallePeliculaViewModelFactory`).
   - **Navegación (`ui.navigation`):** `NavClass` define la clase sellada `Ruta` para gestionar el enrutamiento fuertemente tipado.
   - **Pantallas (`ui.screens`):** Composables reactivos (`MainScreen`, `CatalogoScreen`, `FormularioScreen`, `PantallaDetalleScreen`, `PerfilScreen`).
   - **Tema (`ui.theme`):** Configuración visual Material 3 (`Color.kt`, `Theme.kt`, `Type.kt`).

---

## 🛠️ Tecnologías y Librerías

| Tecnología / Librería | Descripción / Uso |
| :--- | :--- |
| **Kotlin** | Lenguaje de desarrollo principal (`v2.2.10`) |
| **Jetpack Compose** | UI Toolkit declarativo moderno (`BOM 2026.02.01`) |
| **Material Design 3** | Componentes de diseño (`Scaffold`, `CenterAlignedTopAppBar`, `NavigationBar`, `ModalNavigationDrawer`, `Card`, `OutlinedTextField`, `Checkbox`, `Button`, `CircularProgressIndicator`) |
| **Navigation Compose** | Enrutamiento de navegación entre pantallas (`v2.10.2`) |
| **Room ORM** | Persistencia relacional de datos SQLite (`v2.8.5` con `KSP`) |
| **Lifecycle & ViewModel Compose** | Gestión de estado y ciclo de vida (`v2.8.7` / `lifecycle-runtime-compose`) |
| **Kotlin Coroutines & Flow** | Concurrencia reactiva y operaciones asíncronas (`StateFlow`, `Flow`) |
| **Gradle Version Catalog** | Gestión centralizada de dependencias con `libs.versions.toml` y Kotlin DSL |

---

## 📐 Especificaciones Técnicas

- **Min SDK:** `29` (Android 10)
- **Target SDK / Compile SDK:** `37` (Android 16)
- **Java Compatibility:** Java 11
- **AGP (Android Gradle Plugin):** `9.5.1`
- **KSP (Kotlin Symbol Processing):** `2.3.6`

---

## 📂 Estructura del Proyecto

```text
DAMFlix/
├── app/
│   ├── build.gradle.kts                   # Configuración Gradle del módulo de la app
│   └── src/
│       ├── main/
│       │   ├── java/com/example/damflix/
│       │   │   ├── DAMFlixApp.kt          # Clase Application (Instanciación lazy del Repositorio y BD)
│       │   │   ├── MainActivity.kt         # Punto de entrada (Edge-to-Edge y DAMFlixTheme)
│       │   │   ├── data/                   # Capa de Datos
│       │   │   │   ├── local/
│       │   │   │   │   ├── AppDatabase.kt  # Base de datos Room (damflix_room.db)
│       │   │   │   │   ├── PeliculaDao.kt  # Interfaz DAO (Queries, Insert, Update, Delete)
│       │   │   │   │   └── PeliculaEntity.kt # Entidad Room
│       │   │   │   ├── model/
│       │   │   │   │   └── Pelicula.kt     # Data class de película
│       │   │   │   └── repository/
│       │   │       └── PeliculaRepository.kt # Abstracción de acceso a datos
│       │   │   └── ui/                     # Capa de Interfaz de Usuario / Presentación
│       │   │       ├── navigation/
│       │   │       │   └── NavClass.kt     # Rutas de navegación (Sealed Class Ruta)
│       │   │       ├── screens/
│       │   │       │   ├── MainScreen.kt   # Contenedor principal (Scaffold, NavigationDrawer, NavHost)
│       │   │       │   ├── CatalogoScreen.kt # Vista de catálogo (LazyColumn, Card, Favoritos)
│       │   │       │   ├── FormularioScreen.kt # Formulario de registro de películas
│       │   │       │   ├── PantallaDetalleScreen.kt # Vista detallada de una película
│       │   │       │   └── PerfilScreen.kt # Vista de perfil de usuario y métricas
│       │   │       ├── theme/              # Tema Material 3 (Color.kt, Theme.kt, Type.kt)
│       │   │       └── viewmodel/
│       │   │           ├── PeliculaViewModel.kt # ViewModel principal del catálogo
│       │   │           ├── DetallePeliculaViewModel.kt # ViewModel de la pantalla de detalle
│       │   │           ├── DetallePeliculaViewModelFactory.kt # Factory para DetallePeliculaViewModel
│       │   │           └── DetalleUiState.kt # Estados de UI de detalle (Cargando, Exito, Error)
│       │   ├── res/                        # Recursos estáticos (Imágenes, Strings, Colores, Iconos)
│       │   └── AndroidManifest.xml         # Manifiesto de la aplicación Android (.DAMFlixApp)
├── gradle/
│   └── libs.versions.toml                 # Catálogo centralizado de versiones y dependencias
├── build.gradle.kts                       # Gradle build script de nivel raíz
└── README.md                              # Documentación completa del proyecto
```

---

## 🚀 Requisitos e Instalación

### Requisitos Previos

1. **Android Studio** (versión Ladybug / 2024.2.1 o posterior, compatible con Kotlin 2.x y AGP 9.x).
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
