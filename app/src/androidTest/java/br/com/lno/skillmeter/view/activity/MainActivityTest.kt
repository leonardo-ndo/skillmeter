package br.com.lno.skillmeter.view.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.lno.skillmeter.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testViewsVisibility() {

        // Fragment container
        Espresso.onView(ViewMatchers.withId(R.id.fragment_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Bottom bar
        Espresso.onView(ViewMatchers.withId(R.id.bottom_bar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Bottom navigation view and items
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.menu_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.menu_chart))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Float action button
        Espresso.onView(ViewMatchers.withId(R.id.fab))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testIntents() {

        Intents.init()

        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_view))
            .perform(ViewActions.click())

        Intents.intended(IntentMatchers.hasComponent(InputSkillActivity::class.qualifiedName))

        Intents.release()

    }
}