package com.pppp.travelchecklist.selector.view.viewpager.fragments.models

import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.api.Client
import io.reactivex.Single

class RetrofitRepository(private val client: Client) : CheckListDatabase {

    override fun getTagGroups(): Single<out List<TagsGroup>> = client.getTagsGroup()

}

interface CheckListDatabase {
    fun getTagGroups(): Single<out List<TagsGroup>>
}