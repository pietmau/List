package com.pppp.travelchecklist.login.viewmodel

interface LoginModel {
    fun isUserLoggedIn(): Boolean
    fun shouldAppBeEnabled(isOn: () -> Unit, isOff: () -> Unit)
}
