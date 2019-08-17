package com.pppp.travelchecklist.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.isNetworkAvailable
import java.util.concurrent.atomic.AtomicLong

private const val BACKOFF_FACTOR = 2

class NetworkCheckerImpl(private val context: Context) : NetworkChecker, BroadcastReceiver() {
    private var intentFilter: IntentFilter? = null

    private val delayInMillSeconds = AtomicLong(10 * 1000)

    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun isNetworkAvailable() = context.isNetworkAvailable()

    override fun checkNetworkConnectionRepeatedly(success: (() -> Unit)?, failure: ((NetworkChecker.ErrorMessage) -> Unit)?) {
        registerReceverIfAppropriate()
        handler.removeCallbacksAndMessages(null)
        if (isNetworkAvailable()) {
            onSuccess(success)
        } else {
            onFailure(failure, success)
        }

    }

    private fun registerReceverIfAppropriate() {
        if (intentFilter != null) {
            return
        }
        intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        context.registerReceiver(NetworkCheckerImpl@ this, intentFilter)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        //TODO
    }

    private fun onFailure(failure: ((NetworkChecker.ErrorMessage) -> Unit)?, success: (() -> Unit)?) {
        failure?.invoke(getMessage())
        handler.postDelayed({
            delayInMillSeconds.set((delayInMillSeconds.get() * BACKOFF_FACTOR).toLong())
            checkNetworkConnectionRepeatedly(success, failure)
        }, delayInMillSeconds.get())
    }

    private fun onSuccess(success: (() -> Unit)?) {
        success?.invoke()
    }

    private fun getMessage(): NetworkChecker.ErrorMessage {
        return NetworkChecker.ErrorMessage("${context.getString(R.string.trying_again)}${(delayInMillSeconds.get() / 1000).toInt()}${context.getString(R.string.in_x_seconds)}")
    }

    override fun cancelNetworkChecks() {
        handler.removeCallbacksAndMessages(null)
        context.unregisterReceiver(this)
        intentFilter = null
    }
}