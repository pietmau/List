package com.pppp.travelchecklist.selector.view

import android.content.Context
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ViewFlipper
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.selector.SelectorModule
import javax.inject.Inject

class SelectorView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @Inject lateinit var controller: SelectorController
    @BindView(R.id.flipper) lateinit var flipper: ViewFlipper

    init {
        if (context !is AppCompatActivity) throw UnsupportedOperationException("Must be used within an AppCompatActivity")
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.selector_custom_view, this, true)
        (context.applicationContext as? App)?.appComponent?.with(SelectorModule(context as AppCompatActivity))?.inject(this)
        ButterKnife.bind(this)
        addSubViews()
        setUp()
    }

    private fun setUp() {
        flipper.displayedChild = controller.displayedChild
        getWhoIsTravellingView()?.whoIsTravelling = controller.whoIsTravelling
    }

    private fun addSubViews() {
        addViewToFlipper(R.layout.who_is_travelling)
        addViewToFlipper(R.layout.two)
        addViewToFlipper(R.layout.three)
        addViewToFlipper(R.layout.four)
    }

    private fun addViewToFlipper(@LayoutRes layout: Int) {
        flipper.addView((context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(layout, null, false))
    }

    private fun addViewToFlipper(view: View) {
        flipper.addView(view)
    }

    @OnClick(R.id.previous)
    fun onPreviousClicked() {
        flipper.showPrevious()
        controller.displayedChild = flipper.displayedChild
    }

    @OnClick(R.id.next)
    fun onNextClicked() {
        flipper.showNext()
        controller.displayedChild = flipper.displayedChild
    }

    override fun onSaveInstanceState(): Parcelable {
        controller.setWhoIsTravellling(getWhoIsTravellingView()?.whoIsTravelling)
        return super.onSaveInstanceState()
    }

    private fun getWhoIsTravellingView(): WhoIsTravellingView? {
        val whoIsTravelling = getChildAt(0) as? WhoIsTravellingView
        return whoIsTravelling
    }
}