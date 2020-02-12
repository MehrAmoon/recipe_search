package com.amoon.recipelist.ui.main


import android.app.Activity
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso

import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.amoon.recipelist.App

import com.amoon.recipelist.R
import com.amoon.recipelist.di.DaggerAppComponent
import com.amoon.recipelist.ui.MainActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import dagger.internal.Factory

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.security.Provider

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var stringToBeShowed: String

    // Match the last item by matching its text.
    private val lastItemID = "17834"


    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBeShowed = "Create the perfect healthy meal"
    }




    @Test
    fun mainActivityCorrectDisplayTests() {
        onView(withId(R.id.createMealText)).perform(typeText(stringToBeShowed))
        onView(withText(stringToBeShowed)).check(matches(isDisplayed()))
    }

    // Last item should not exist if the list wasn't scrolled down.
    @Test
    fun lastItem_NotDisplayed() {
        onView(withText(lastItemID)).check(doesNotExist())
    }


    //  Check the item is created.
    @Test
    fun list_Scrolls() {
        onRow(lastItemID)?.check(matches(isCompletelyDisplayed()))
    }


    // check the item click
    @Test
    fun mainActivityClickItemTests() {
        val linearLayout = onView(
            allOf(
                withId(R.id.item_relative), isDisplayed()
            )
        )
        linearLayout.perform(click())
    }


    private fun onRow(str: String): DataInteraction? {
        return Espresso.onData(hasEntry(equalTo(mActivityTestRule), `is`(str)))
    }


}
