package com.pppp.travelchecklist.notifications.bootreceiver

import com.pppp.entities.pokos.TravelCheckListImpl

interface BootReceiverModel {
    suspend fun updateIems()
}