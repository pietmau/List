package com.pppp.travelchecklist.selector.view.model

class Selection(
    val whoIsTravelling: List<SelectionItem.WhoIsTravellingSelectionItem>,
    val wereAreYouFlying: SelectionItem.WereAreYouFlyingSelectionItem?,
    val expectedWeatherSelectionItem: SelectionItem.ExpectedWeatherSelectionItem?,
    val accommodationSelectionItem: SelectionItem.AccommodationSelectionItem?,
    val plannedActivitiesSelectionItem: List<SelectionItem.PlannedActivitiesSelectionItem>,
    val longOrShortTripSelectionItem: SelectionItem.LongOrShortTripSelectionItem?
) {

    sealed class SelectionItem {

        sealed class WhoIsTravellingSelectionItem : SelectionItem() {
            class Male : WhoIsTravellingSelectionItem()
            class Fermale : WhoIsTravellingSelectionItem()
            class Babies : WhoIsTravellingSelectionItem()
            class Toddlers : WhoIsTravellingSelectionItem()
        }

        data class WereAreYouFlyingSelectionItem(val country: String) : SelectionItem()

        sealed class ExpectedWeatherSelectionItem : SelectionItem() {
            class Hot : ExpectedWeatherSelectionItem()
            class Cold : ExpectedWeatherSelectionItem()
        }

        sealed class AccommodationSelectionItem : SelectionItem() {
            class Hotel : AccommodationSelectionItem()
            class Hostel : AccommodationSelectionItem()
            class Camping : AccommodationSelectionItem()
        }

        sealed class PlannedActivitiesSelectionItem : SelectionItem() {
            class BackPacking : PlannedActivitiesSelectionItem()
            class Fishing : PlannedActivitiesSelectionItem()
            class Diving : PlannedActivitiesSelectionItem()
            class Beach : PlannedActivitiesSelectionItem()
            class Work : PlannedActivitiesSelectionItem()
            class GoingOut : PlannedActivitiesSelectionItem()
            class Hiking : PlannedActivitiesSelectionItem()
            class Skiing : PlannedActivitiesSelectionItem()
        }

        sealed class LongOrShortTripSelectionItem : SelectionItem() {
            class Short : LongOrShortTripSelectionItem()
            class Long : LongOrShortTripSelectionItem()
        }
    }
}