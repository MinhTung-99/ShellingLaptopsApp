package com.shelllaptop.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {
    fun <T:Any> ioThenMain(work: suspend  (() -> T?), callback: ( (T?) -> Unit ) ) =

        // launch a new coroutine in background and continue
        // Dispatchers.Main <=> main UI thread of Android
        CoroutineScope(Dispatchers.Main).launch {
            // do background task
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                // Dispatchers.IO runs backgroundThread
                // update UI

                return@rt work()
            }.await()
            callback(data)
        }
}