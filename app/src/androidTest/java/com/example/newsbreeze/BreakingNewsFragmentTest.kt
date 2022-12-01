package com.example.newsbreeze

import android.os.SystemClock
import android.support.test.espresso.intent.Intents
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsbreeze.testutils.AssetReaderUtil
import com.example.newsbreeze.testutils.OkHttpIdlingResourceRule
import com.example.newsbreeze.ui.NewsBreezeActivity
import com.example.newsbreeze.util.Constants
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BreakingNewsFragmentTest {
    @get:Rule
    var rule = OkHttpIdlingResourceRule()

    private val mockWebServerRule = MockWebServer()

    @Before
    fun setup() {
        Constants.BASE_URL = "https://127.0.0.1:8080"
        mockWebServerRule.start(8080)
    }

    @After
    fun after() {
        mockWebServerRule.shutdown()
    }

    @Test
    fun launchActivity(){
        SystemClock.sleep(1000)
        mockWebServerRule.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path!!.contains("v2/top-headlines")) {
                    return MockResponse().setResponseCode(200).setBody(
                        AssetReaderUtil.asset(
                            ApplicationProvider.getApplicationContext(),
                            "breaking_news_api_response"
                        )
                    )
                }
                return MockResponse().setResponseCode(404)
            }
        }
        val scenario = launchActivity<NewsBreezeActivity>()
        SystemClock.sleep(1000)
        scenario.close()
        SystemClock.sleep(1000)
    }
}