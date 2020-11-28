package com.burgowzer.businesscard

import android.app.Application
import io.realm.Realm

class DataStorage: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}