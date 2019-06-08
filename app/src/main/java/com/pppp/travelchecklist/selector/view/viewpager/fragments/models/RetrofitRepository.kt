package com.pppp.travelchecklist.selector.view.viewpager.fragments.models

import com.pietrantuono.entities.TagsGroup
import com.pppp.database.CheckListDatabase
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.entities.pokos.TagsGroupImpl
import com.pppp.travelchecklist.api.Client
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RetrofitRepository(private val client: Client) : CheckListDatabase {
    override fun getTags(): Single<List<TagImpl>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribeToTagsAndUpdates(): Observable<List<TagImpl>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTag(tag: TagImpl, key: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItems(): Single<List<CheckListItemImpl>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribeToItemsAndUpdates(): Observable<List<CheckListItemImpl>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveItem(item: CheckListItemImpl, key: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(): Single<List<CategoryImpl>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCategory(category: CategoryImpl, key: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribeToCategoriesAndUpdates(): Observable<List<CategoryImpl>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTagGroups(): Single<out List<TagsGroup>> = client.getTagsGroup()

    override fun subscribeToGroupsAndUpdates(): Observable<List<TagsGroup>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTagGroup(group: TagsGroupImpl, key: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}