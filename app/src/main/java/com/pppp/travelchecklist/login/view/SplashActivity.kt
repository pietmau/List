package com.pppp.travelchecklist.login.view

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.pppp.travelchecklist.application.App
import javax.inject.Inject
import android.app.Activity
import android.app.AlertDialog
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.login.di.LoginModule
import com.pppp.travelchecklist.login.viewmodel.LoginViewModel
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val REQUEST_CODE: Int = 857
    @Inject
    lateinit var viewStates: ViewStatesProducer<LoginViewModel.LoginViewState>
    @Inject
    lateinit var analytics: AnalyticsLogger
    @Inject
    lateinit var viewActions: ViewActionsConsumer<LoginViewModel.LoginViewAction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        (application as? App)?.appComponent?.with(LoginModule(this))?.inject(this)
        viewStates.states.observe(this, Observer<LoginViewModel.LoginViewState> { render(it) })
    }

    private fun render(state: LoginViewModel.LoginViewState) =
        when (state) {
            is LoginViewModel.LoginViewState.UserNotLoggedIn -> login()
            is LoginViewModel.LoginViewState.AppEnabled -> onLoginSuccessful()
            is LoginViewModel.LoginViewState.Kill -> kill()
        }

    private fun kill() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.app_not_supported)
            .setMessage(R.string.app_not_supported_message)
            .setPositiveButton(R.string.close_app) { _, _ -> finish() }
            .create().show()
    }

    private fun onLoginSuccessful() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun login() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.FacebookBuilder().build(),
                        AuthUI.IdpConfig.AnonymousBuilder().build()
                    )
                )
                .build(), REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE) {
            if (resultCode === Activity.RESULT_OK) {
                viewActions.accept(LoginViewModel.LoginViewAction.UserLoggedInSuccessfully)
                return
            }
            analytics.onLoginFlowFailure(resultCode)
        }
        finish()
    }
}