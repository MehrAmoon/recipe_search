package com.amoon.recipelist.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnit4
import com.amoon.recipelist.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest(java: Class<DetailFragment>) {

    private lateinit var stringToBeShowed: String


    @Before
    fun initValidString() {
        stringToBeShowed = "69 Special"
    }


    @Test
    fun mainActivityCorrectDisplayTests() {
        Espresso.onView(ViewMatchers.withId(R.id.createMealText)).perform(ViewActions.typeText(stringToBeShowed))
        Espresso.onView(ViewMatchers.withText(stringToBeShowed))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



    @Test
    fun detailFragmentWholeTests(){

        val constraintLayout = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.gitDetailView),
                ViewMatchers.isDisplayed()
            )
        )
        constraintLayout.perform(ViewActions.click())


        val appCompatButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.recipeButton), ViewMatchers.withText("VIEW WEBSITE"),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatButton.perform(ViewActions.click())

        Espresso.pressBack()
    }




}