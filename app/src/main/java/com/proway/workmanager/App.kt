package com.proway.workmanager

import android.app.Application
import androidx.work.*
import com.proway.workmanager.ui.main.work.PeriodicWorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startWorker()
        //FirebaseApp.initializeApp(applicationContext)
    }

    fun startWorker(){

        val workerManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<PeriodicWorkManager>(
            15,
            TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        workerManager.enqueueUniquePeriodicWork(
            UUID.randomUUID().toString(),
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

}
