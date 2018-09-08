package com.pppp.database

import com.pppp.entities.Tag
import io.reactivex.Single

interface CheckListDatabase {
    fun getTags(): Single<List<Tag>>

    companion object {
        const val TAGS = "tags"
    }

    fun start()
    fun stop()
}