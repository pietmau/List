package com.pppp.travelchecklist.login.viewmodel

import com.google.android.play.core.missingsplits.MissingSplitsManager
import com.google.android.play.core.missingsplits.MissingSplitsManagerFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pppp.travelchecklist.BuildConfig

private val GOOGLE_PLAY_PACKAGE_NAME = "com.vending.google"

class FirebaseKillSwitch(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val source: String?,
    private val missingSplitsManager: MissingSplitsManager
) : KillSwitch {

    override fun shouldAppBeEnabled(isOn: () -> Unit, isOff: (message: String?) -> Unit) {
        if (BuildConfig.DEBUG) {
            isOn()
            return
        }
        if (isInstalledFromStrangeSource()) {
            isOff("Installed from $source")
            return
        }
        firebaseFirestore
            .collection("app_config")
            .get()
            .addOnSuccessListener { snapshot ->
                val document = snapshot.documents.firstOrNull()
                if (!isAppEnabled(document)) {
                    isOff("App not enabled")
                    return@addOnSuccessListener
                }
                if (!isVersionEnabled(document)) {
                    isOff("Version not supported ${BuildConfig.VERSION_NAME}")
                    return@addOnSuccessListener
                }
                isOn()
            }
    }


    private fun isInstalledFromStrangeSource() = missingSplitsManager.disableAppIfMissingRequiredSplits()

    private fun isAppEnabled(document: DocumentSnapshot?) = document?.getBoolean("is_app_enabled") ?: false

    private fun isVersionEnabled(document: DocumentSnapshot?) =
        (document?.get("supported_versions") as? List<String>)?.contains(BuildConfig.VERSION_NAME) == true

    override fun isUserLoggedIn() = auth.currentUser != null
}