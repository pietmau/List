package com.pppp.travelchecklist.login.viewmodel

interface KillSwitch {
    fun isUserLoggedIn(): Boolean
    fun shouldAppBeEnabled(isOn: () -> Unit, isOff: (meessage: String?) -> Unit)
}
