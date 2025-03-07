package com.example.fetchrewards

import android.app.Application
import com.example.fetchrewards.di.appModule
import org.koin.core.context.startKoin

class FetchRewardsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}