package com.example.newsbreeze

import android.os.SystemClock
import android.support.test.espresso.intent.Intents
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsbreeze.testutils.AssetReaderUtil
import com.example.newsbreeze.testutils.MockedAPITest
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
                        ApplicationProvider.getApplicationContext(),
                        "breaking_news_api_response"
                    )
                )
        )
        SystemClock.sleep(1000)
        val scenario = launchActivity<NewsBreezeActivity>()
        SystemClock.sleep(1000)
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
                        ApplicationProvider.getApplicationContext(),
                        "token-invalid"
                    )
                )
        )
        SystemClock.sleep(1000)
        val scenario = launchActivity<NewsBreezeActivity>()
        SystemClock.sleep(1000)
        scenario.close()
        SystemClock.sleep(1000)
    }
}