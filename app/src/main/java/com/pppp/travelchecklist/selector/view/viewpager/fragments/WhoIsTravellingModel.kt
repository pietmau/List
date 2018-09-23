package com.pppp.travelchecklist.selector.view.viewpager.fragments

import com.pppp.entities.Tag
import io.reactivex.Observable

interface WhoIsTravellingModel {

    fun getWhoIsTravelling(): Observable<List<Tag>>

    fun onWhoisTravellingSelected(traveller: Tag)

}
