package com.pppp.travelchecklist.selector.view

import android.content.Context
import android.os.Parcelable
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.viewpager.ViewViewPager
import javax.inject.Inject

class SelectorView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @Inject lateinit var controller: SelectorController
    @BindView(R.id.flipper) lateinit var flipper: ViewViewPager
    @BindView(R.id.next) lateinit var next: View
    @BindView(R.id.previous) lateinit var previous: View

    private val whoIsTravellingView: WhoIsTravellingView?
        get() = getChildAt(0) as? WhoIsTravellingView

    init {
        if (context !is AppCompatActivity) throw UnsupportedOperationException("Must be used within an AppCompatActivity")
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.selector_custom_view, this, true)
        (context.applicationContext as? App)?.appComponent?.with(SelectorModule(context as AppCompatActivity))?.inject(this)
        ButterKnife.bind(this)
        setUp()
    }

    private fun setUp() {
        whoIsTravellingView?.whoIsTravelling = controller.whoIsTravelling
        flipper.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                onPageChanged()
            }
        })
        onPageChanged()
    }

    @OnClick(R.id.previous)
    fun onPreviousClicked() {
        flipper.showPrevious()
    }

    private fun onPageChanged() {
        controller.displayedChild = flipper.currentItem
        if (flipper.canGoToNext) {
            next.visibility = View.VISIBLE
        } else {
            next.visibility = View.INVISIBLE
        }
        if (flipper.canGoToPrevious) {
            previous.visibility = View.VISIBLE
        } else {
            previous.visibility = View.INVISIBLE
        }
    }

    @OnClick(R.id.next)
    fun onNextClicked() {
        flipper.showNext()
    }

    override fun onSaveInstanceState(): Parcelable {
        controller.setWhoIsTravellling(whoIsTravellingView?.whoIsTravelling)
        return super.onSaveInstanceState()
    }

}