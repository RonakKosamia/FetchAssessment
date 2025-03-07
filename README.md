<h1 align="center">Fetch Rewards Assessment App</h1>

## 🚀 Overview
This is a **Jetpack Compose-based Android application** that retrieves and displays data from
[Fetch Rewards API](https://fetch-hiring.s3.amazonaws.com/hiring.json).

## Tech Stack

It follows **Clean Architecture + MVVM**, using:
**Jetpack Compose** for UI  
**Retrofit** for networking  
**Koin** for Dependency Injection
**StateFlow** for reactive UI updates  
**Kotlin Coroutines** for async operations

### Project Structure: 

com.example.fetchrewards
│── data (Handles API and Repository)
│   ├── model (Data models)
│   ├── remote (Retrofit API service)
│   ├── repository (Implements FetchRepository)
│── domain (Business Logic)
│   ├── model (Domain models)
│   ├── repository (FetchRepository interface)
│   ├── usecase (Business logic for fetching and sorting data)
│── presentation (UI & ViewModel)
│   ├── ui (Jetpack Compose screens)
│   ├── viewmodel (FetchViewModel)
│── di (Dependency Injection with Koin)
│── MainActivity.kt

## 📸 Screenshots


- 
## IDE Version
Android Studio Ladybug Feature Drop | 2024.2.2 Patch 1
