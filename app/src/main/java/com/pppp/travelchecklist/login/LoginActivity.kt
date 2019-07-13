package com.pppp.travelchecklist.login

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.pppp.travelchecklist.application.App
import javax.inject.Inject
import com.firebase.ui.auth.ErrorCodes
import android.app.Activity
import com.firebase.ui.auth.IdpResponse
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val REQUEST_CODE: Int = 857

    @Inject
    lateinit var viewStates: Producer<ViewState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as? App)?.appComponent?.with(LoginModule(this))?.inject(this)
        viewStates.states.observe(this, Observer<ViewState> { render(it) })
    }

    private fun render(state: ViewState?) {
        when (state) {
            is ViewState.UserNotLoggedIn -> login()
            is ViewState.UserLoggedIn -> proceed()
        }
    }

    private fun proceed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun login() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(
                    listOf(AuthUI.IdpConfig.GoogleBuilder().build(), AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.PhoneBuilder().build())
                )
                .build(), REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode === Activity.RESULT_OK) {
                proceed()
            } else {
                if (response == null) {
                    return
                }
                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    return
                }
            }
        }
    }
}