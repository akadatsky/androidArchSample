package com.akadatsky.gif4work

import android.app.Application
import com.akadatsky.gif4work.di.networkModule
import com.akadatsky.gif4work.di.repoModule
import com.akadatsky.gif4work.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    uiModule,
                    repoModule,
                    networkModule
                )
            )
        }
    }

}