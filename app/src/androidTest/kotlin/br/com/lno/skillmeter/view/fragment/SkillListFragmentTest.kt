package br.com.lno.skillmeter.view.fragment

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.com.lno.skillmeter.R
import br.com.lno.skillmeter.di.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SkillListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun skillListTest() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<SkillListFragment> {
            navController.setGraph(R.navigation.navigation_graph)
            navController.setCurrentDestination(R.id.menu_list)
            Navigation.setViewNavController(requireView(), navController)
        }

        Assert.assertEquals(navController.currentDestination?.id, R.id.menu_list)

        Espresso.onView(ViewMatchers.withId(R.id.rv_skills))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}