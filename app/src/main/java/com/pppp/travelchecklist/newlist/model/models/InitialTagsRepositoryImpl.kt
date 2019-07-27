package com.pppp.travelchecklist.newlist.model.models

import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.api.Client
import io.reactivex.Single

class InitialTagsRepositoryImpl(private val client: Client) : InitialTagsRepository {

    override fun getTagGroups(): Single<out List<TagsGroup>> = client.getTagsGroup()

}

interface InitialTagsRepository {
    fun getTagGroups(): Single<out List<TagsGroup>>
}