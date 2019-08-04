package com.pppp.travelchecklist.newlist.model.models

import com.pietrantuono.entities.TagsGroup
import com.pppp.entities.pokos.TagsGroupImpl
import com.pppp.travelchecklist.api.Client
import io.reactivex.Single
import retrofit2.Call

class InitialTagsRepositoryImpl(private val client: Client) : InitialTagsRepository {

    override fun getTagGroups() = client.getTagsGroup()

    override fun getTagsGroupCall(): Call<List<TagsGroup>> = client.getTagsGroupCall() as Call<List<TagsGroup>>

}

interface InitialTagsRepository {
    fun getTagGroups(): Single<out List<TagsGroup>>

    fun getTagsGroupCall(): Call<List<TagsGroup>>
}