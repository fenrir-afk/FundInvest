package com.example.fundinvest.statements

import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fundinvest.MainActivity
import com.example.fundinvest.R
import com.example.fundinvest.ui.statements.RecyclerViewAdapter
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatementsAppearTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun statementsWasFound() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_dashboard)).perform(ViewActions.click())
        Thread.sleep(6000)
        Espresso.onView(ViewMatchers.withId(R.id.statementSearch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso.onView(ViewMatchers.withId(R.id.statementSearch))
            .perform(
                ViewActions.typeText("TSLA"),
                ViewActions.pressImeActionButton()
            )
        Thread.sleep(5000)
        val scenario = activityScenarioRule.scenario
        scenario.onActivity { activity ->
            val cardsLayout = activity.findViewById<RecyclerView>(R.id.statement_list)
            MatcherAssert.assertThat(cardsLayout.childCount, Matchers.greaterThan(0))
        }
    }
}