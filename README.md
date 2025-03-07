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

## Project Structure: 

<p class="has-line-data" data-line-start="0" data-line-end="14">com.example.fetchrewards<br>
│── data (Handles API and Repository)<br>
│   ├── model (Data models)<br>
│   ├── remote (Retrofit API service)<br>
│   ├── repository (Implements FetchRepository)<br>
│── domain (Business Logic)<br>
│   ├── model (Domain models)<br>
│   ├── repository (FetchRepository interface)<br>
│   ├── usecase (Business logic for fetching and sorting data)<br>
│── presentation (UI &amp; ViewModel)<br>
│   ├── ui (Jetpack Compose screens)<br>
│   ├── viewmodel (FetchViewModel)<br>
│── di (Dependency Injection with Koin)<br>
│── MainActivity.kt</p>

## 📸 Screenshots
<img width="530" alt="Screenshot 2025-03-07 at 2 45 48 AM" src="https://github.com/user-attachments/assets/64c74079-51e4-4f1b-82d0-077910661114" />
<img width="304" alt="Screenshot 2025-03-07 at 2 46 01 AM" src="https://github.com/user-attachments/assets/87347459-f380-4334-ae27-a5388e01e042" />
<img width="300" alt="Screenshot 2025-03-07 at 2 46 13 AM" src="https://github.com/user-attachments/assets/0230c6c2-ef0e-4a27-871a-4117ce3146c3" />
- 

## IDE Version
Android Studio Ladybug Feature Drop | 2024.2.2 Patch 1
