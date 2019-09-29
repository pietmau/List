package com.pppp.travelchecklist.login.viewmodel

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pppp.travelchecklist.BuildConfig

private val GOOGLE_PLAY_PACKAGE_NAME = "com.vending.google"

class FirebaseKillSwitch(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val source: String?
) : KillSwitch {

    override fun shouldAppBeEnabled(isOn: () -> Unit, isOff: () -> Unit) {
        if (BuildConfig.DEBUG) {
            isOn()
            return
        }
        if (isInstalledFromStrangeSource()) {
            isOff()
            return
        }
        firebaseFirestore
            .collection("app_config")
            .get()
            .addOnSuccessListener { snapshot ->
                val document = snapshot.documents.firstOrNull()
                if (!isAppEnabled(document)) {
                    isOff()
                    return@addOnSuccessListener
                }
                if (!isVersionEnabled(document)) {
                    isOff()
                    return@addOnSuccessListener
                }
                isOn()
            }
    }

    private fun isInstalledFromStrangeSource() = !source.equals(GOOGLE_PLAY_PACKAGE_NAME)

    private fun isAppEnabled(document: DocumentSnapshot?) = document?.getBoolean("is_app_enabled") ?: false

    private fun isVersionEnabled(document: DocumentSnapshot?) =
        (document?.get("supported_versions") as? List<String>)?.contains(BuildConfig.VERSION_NAME) == true

    override fun isUserLoggedIn() = auth.currentUser != null
}