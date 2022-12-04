package com.example.newsbreeze

import android.os.SystemClock
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsbreeze.testutils.AssetReaderUtil
import com.example.newsbreeze.testutils.MockedAPITest
import com.example.newsbreeze.testutils.OkHttpIdlingResourceRule
import com.example.newsbreeze.ui.NewsBreezeActivity
import com.example.newsbreeze.util.Constants
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BreakingNewsFragmentTest : MockedAPITest(){

    @Before
    fun setup() {

    }

    @After
    fun after() {

    }

    @Test
    fun launchActivity(){
        SystemClock.sleep(1000)
        mockWebServerRule.server.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    AssetReaderUtil.asset(
                        "breaking_news_api_response"
                    )
                )
        )
        SystemClock.sleep(1000)
        val scenario = launchActivity<NewsBreezeActivity>()
        SystemClock.sleep(1000)
        onView(recyclerViewSizeMatcher(20)).check(matches(isDisplayed()))
        scenario.close()
        SystemClock.sleep(1000)
    }

    @Test
    fun failureApiResponseTest(){
        SystemClock.sleep(1000)
        mockWebServerRule.server.enqueue(
            MockResponse().setResponseCode(400)
                .setBody(
                    AssetReaderUtil.asset(
                        "token-invalid"
                    )
                )
        )
        SystemClock.sleep(1000)
        val scenario = launchActivity<NewsBreezeActivity>()
        SystemClock.sleep(1000)
        onView(recyclerViewSizeMatcher(0)).check(matches(isDisplayed()))
        scenario.close()
        SystemClock.sleep(1000)
    }


    fun recyclerViewSizeMatcher(matcherSize: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with list size: $matcherSize")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return matcherSize == recyclerView.adapter!!.itemCount
            }
        }
    }
}