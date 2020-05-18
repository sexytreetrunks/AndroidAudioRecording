package com.treetrunks.audiorecording

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AudioRecordingServiceTest {
    @get:Rule
    val serviceRule = ServiceTestRule()

    /*@Test
    fun testServiceActionStartRecord() {
        val intent = getAudioServiceIntent(AudioActions.ACTION_START_RECORD)

        serviceRule.startService(intent)
        val binder = serviceRule.bindService(intent)

        Assert.assertEquals(true, isRecording(binder))

        serviceRule.unbindService()
    }*/

    @Test
    fun testServiceActionStopRecord() {
        var intent = getAudioServiceIntent(AudioActions.ACTION_START_RECORD)

        serviceRule.startService(intent)
        val binder = serviceRule.bindService(intent)

        Assert.assertEquals(true, isRecording(binder))

        Thread.sleep(2000)

        intent = getAudioServiceIntent(AudioActions.ACTION_STOP_RECORD)
        serviceRule.startService(intent)

        Assert.assertEquals(true, isRecording(binder))

        serviceRule.unbindService()
    }

    private fun getAudioServiceIntent(action: String): Intent {
        val serviceIntent = Intent(
            ApplicationProvider.getApplicationContext<Context>(),
            AudioRecordService::class.java
        ).apply {
            setAction(action)
        }
        return serviceIntent
    }

    private fun isRecording(binder: IBinder) : Boolean {
        val service = (binder as AudioRecordService.MyBinder).getService()
        return service.isRecording()
    }

}