package com.shopping.android

import android.app.Application
import androidx.datastore.preferences.createDataStore
import com.shopping.common.LocalStorageImpl
import com.shopping.common.ServiceLocator

private const val ShoppingDataStore = "Shopping Data Store"

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator(
            LocalStorageImpl(
                createDataStore(
                    name = ShoppingDataStore
                )
            )
        )
    }

}