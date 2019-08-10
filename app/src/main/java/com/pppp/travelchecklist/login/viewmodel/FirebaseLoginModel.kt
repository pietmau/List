package com.pppp.travelchecklist.login.viewmodel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.R

private val TWETNTYFOUR_HOURS = 24L

class FirebaseLoginModel( // TODO add offline warning
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(), val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : LoginModel {

    override fun shouldAppBeEnabled(isOn: () -> Unit, isOff: () -> Unit) {
        firebaseFirestore
            .collection("app_config").get().addOnSuccessListener { snapshot ->
                val document = snapshot.documents.firstOrNull()
                val isAppEnabled = document?.getBoolean("is_app_enabled") ?: false
                val supportedVersions = (document?.get("supported_versions") as? List<String>) ?: emptyList()
                if (isAppEnabled && supportedVersions.contains(BuildConfig.VERSION_NAME)) {
                    isOn()
                } else {
                    isOff()
                }

            }
    }

    override fun isUserLoggedIn() = auth.currentUser != null
}