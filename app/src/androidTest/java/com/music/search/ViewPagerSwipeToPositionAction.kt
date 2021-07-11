package com.music.search

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.viewpager.widget.ViewPager
import org.hamcrest.Description
import org.hamcrest.Matcher

class ViewPagerSwipeToPositionAction(private val mPosition: Int) : ViewAction {
    override fun getConstraints(): Matcher<View?> {
        return object : Matcher<View?> {
            override fun matches(item: Any): Boolean {
                return item is ViewPager
            }

            override fun describeMismatch(item: Any, mismatchDescription: Description) {}
            override fun _dont_implement_Matcher___instead_extend_BaseMatcher_() {}
            override fun describeTo(description: Description) {}
        }
    }

    override fun getDescription(): String {
        return "set current item for a view pager"
    }

    override fun perform(uiController: UiController, view: View) {
        if (view is ViewPager) {
            view.currentItem = mPosition
        }
    }

}