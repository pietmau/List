package com.pppp.travelchecklist.model

import com.google.firebase.firestore.FirebaseFirestore
import com.pppp.travelchecklist.model.listfieldsdatabase.FirestoreTravelChecklistFields
import com.pppp.travelchecklist.model.listfieldsdatabase.TravelChecklistFieldsDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideTravelChecklistFieldsDatabase(firestore: FirebaseFirestore): TravelChecklistFieldsDatabase =
        FirestoreTravelChecklistFields(firestore)
}