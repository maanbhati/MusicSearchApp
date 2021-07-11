package com.music.search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert

class RecyclerViewItemStringDataAssertion(textViewId: Int, expectedText: String, position: Int) :
    ViewAssertion {
    private val mMatcher: Matcher<String> = Matchers.`is`(expectedText)
    private val mTextViewId: Int = textViewId
    private val mPosition: Int = position
    override fun check(view: View, noViewFoundException: NoMatchingViewException) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val item = recyclerView.layoutManager!!.findViewByPosition(mPosition)!!
        val textView = item.findViewById<View>(mTextViewId) as TextView
        Assert.assertTrue(mMatcher.matches(textView.text.toString()))
    }
}