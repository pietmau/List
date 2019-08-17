package com.pppp.travelchecklist.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.isNetworkAvailable
import java.util.concurrent.atomic.AtomicLong

private const val BACKOFF_FACTOR = 2

class NetworkCheckerImpl(private val context: Context) : NetworkChecker {

    private val delayInMillSeconds = AtomicLong(10 * 1000)

    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun isNetworkAvailable() = context.isNetworkAvailable()

    override fun checkNetworkConnectionRepeatedly(success: (() -> Unit)?, failure: ((NetworkChecker.ErrorMessage) -> Unit)?) {
        handler.removeCallbacksAndMessages(null)
        if (!isNetworkAvailable()) {
            failure?.invoke(getMessage())
        }
        handler.postDelayed({
            if (isNetworkAvailable()) {
                success?.invoke()
            } else {
                delayInMillSeconds.set((delayInMillSeconds.get() * BACKOFF_FACTOR).toLong())
                checkNetworkConnectionRepeatedly(success, failure)
            }
        }, delayInMillSeconds.get())
    }

    private fun getMessage(): NetworkChecker.ErrorMessage {
        return NetworkChecker.ErrorMessage("${context.getString(R.string.trying_again)}${(delayInMillSeconds.get() / 1000).toInt()}${context.getString(R.string.in_x_seconds)}")
    }

    override fun cancelNetworkChecks() {
        handler.removeCallbacksAndMessages(null)
    }
}