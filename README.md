# 🎬 DAMFlix

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Android SDK](https://img.shields.io/badge/API-29%2B%20%7C%20SDK%2037-3DDC84.svg?style=flat&logo=android)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20%2F%20Material3-4285F4.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Gradle](https://img.shields.io/badge/Gradle-9.x%20(Version%20Catalog)-02303A.svg?style=flat&logo=gradle)](https://gradle.org)

**DAMFlix** es una aplicación móvil nativa para Android desarrollada con **Kotlin** y **Jetpack Compose** (Material Design 3). El proyecto forma parte del módulo de *Programación Multimedia y Dispositivos Móviles (PGL)* del Ciclo Formativo de Grado Superior en **Desarrollo de Aplicaciones Multiplataforma (2º DAM)**.

La aplicación permite visualizar el perfil del usuario, su rol dentro de la comunidad cinéfila (como crítico de cine), así como estadísticas clave sobre películas vistas y reseñas publicadas.

---

## 📌 Características Principales

- 🎨 **Interfaz de Usuario Moderna:** Diseñada completamente de forma declarativa con **Jetpack Compose** y **Material Design 3**.
- 👤 **Perfil de Usuario:** Muestra la información personal del usuario (`Usuario`, `Rol`), películas vistas y recuento total de reseñas realizas.
- 🖼️ **Avatar y Elementos Gráficos:** Incorporación de avatares circulares en la barra superior (`CenterAlignedTopAppBar`) y en la barra de navegación inferior (`BottomAppBar`).
- 📱 **Soporte Edge-to-Edge:** Experiencia inmersiva adaptada a las guías de diseño de Android actual.
- ⚙️ **Arquitectura Limpia y Modularización de Dependencias:** Uso de **Gradle Version Catalog** (`libs.versions.toml`) y sintaxis Kotlin DSL (`build.gradle.kts`).

---

## 🛠️ Tecnologías y Librerías

| Tecnología / Librería | Descripción / Uso |
| :--- | :--- |
| **Kotlin** | Lenguaje principal de desarrollo (`v2.2.10`) |
| **Jetpack Compose** | Framework UI declarativo de Android con Material 3 (`BOM 2026.02.01`) |
| **AndroidX Core KTX & Lifecycle** | Extensiones KTX para el ciclo de vida y componentes esenciales de Android |
| **Material Design 3** | Componentes de UI (`Scaffold`, `CenterAlignedTopAppBar`, `BottomAppBar`, `Text`, `Image`) |
| **Gradle Version Catalog** | Gestión centralizada y moderna de dependencias mediante `libs.versions.toml` |

---

## 📐 Especificaciones Técnicas

- **Min SDK:** `29` (Android 10)
- **Target SDK / Compile SDK:** `37` (Android 16)
- **Java Compatibility:** Java 11
- **Gradle Plugin:** `9.4.1`

---

## 📂 Estructura del Proyecto

```text
DAMFlix/
├── app/
│   ├── build.gradle.kts            # Configuración del módulo de la app
│   └── src/
│       ├── main/
│       │   ├── java/com/example/damflix/
│       │   │   ├── MainActivity.kt # Actividad principal y composables de la UI
│       │   │   └── ui/theme/       # Definiciones de Tema, Colores y Tipografía (M3)
│       │   ├── res/                # Recursos estáticos (Imágenes, Strings, Colores)
│       │   └── AndroidManifest.xml # Manifiesto de la aplicación
├── gradle/
│   └── libs.versions.toml          # Catálogo de versiones de dependencias
├── build.gradle.kts                # Configuración raíz del proyecto
└── README.md                       # Documentación del proyecto
```

---

## 🚀 Requisitos e Instalación

### Requisitos Previos

1. **Android Studio** (versión reciente compatible con Kotlin 2.x y AGP 9.x).
2. **JDK 11** o superior configurado en Android Studio.
3. Dispositivo físico o Emulador Android con **Android 10 (API 29)** o superior.

### Pasos para Ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/DAMFlix.git
   cd DAMFlix
   ```

2. **Abrir en Android Studio:**
   - Abre Android Studio y selecciona `Open an Existing Project`.
   - Selecciona la carpeta raíz `DAMFlix`.

3. **Sincronizar el proyecto con Gradle:**
   - Deja que Gradle descargue las dependencias y construya la estructura del proyecto (`Sync Project with Gradle Files`).

4. **Ejecutar la Aplicación:**
   - Selecciona tu dispositivo/emulador en la barra superior.
   - Presiona el botón **Run** `(Shift + F10)` o `▶`.

---

## 👨‍💻 Autor

- **Daniel Flores Medina**
- **Curso:** 2º Curso de Desarrollo de Aplicaciones Multiplataforma (2º DAM)
- **Módulo:** Programación Multimedia y Dispositivos Móviles (PGL)

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia academica para uso didáctico y personal dentro del marco formativo del Grado Superior DAM.
