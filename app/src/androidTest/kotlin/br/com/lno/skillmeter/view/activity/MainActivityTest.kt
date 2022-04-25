package br.com.lno.skillmeter.view.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.lno.skillmeter.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testViewsVisibility() {

        // Fragment container
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.nav_host_fragment),
                ViewMatchers.isDisplayed()
            )
        )

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
}