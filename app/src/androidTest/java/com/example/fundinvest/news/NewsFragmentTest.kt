package com.example.fundinvest.news

import android.widget.LinearLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fundinvest.MainActivity
import com.example.fundinvest.R
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun newsWasFoundAndAdded() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_notifications)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editTextSearch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso.onView(ViewMatchers.withId(R.id.editTextSearch))
            .perform(
                ViewActions.typeText("Tesla"),
                ViewActions.pressImeActionButton()
            )
        Thread.sleep(14000)
        val scenario = activityScenarioRule.scenario
        scenario.onActivity { activity ->
            val cardsLayout = activity.findViewById<LinearLayout>(R.id.cardsLayout)
            MatcherAssert.assertThat(cardsLayout.childCount, Matchers.greaterThan(0))
        }
    }
}