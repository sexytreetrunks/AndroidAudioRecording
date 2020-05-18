package com.treetrunks.audiorecording

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class AudioRecordingTest {

    lateinit var audioRecoder: AudioRecorder

    //@Before
    fun init() {
        Log.d("********", "INIT!")
        val dir_file = File(getContext().getExternalFilesDir(null), "child")
        if (!dir_file.exists()) dir_file.mkdir()
        audioRecoder = AudioRecorder(dir_file.absolutePath)
        audioRecoder.initRecorder()
    }

    //@After
    fun after() {
        //tlqkf 왜 자꾸 init안됬다고 뜸??
        Log.d("********", "AFTER!")
        audioRecoder.release()
    }

    @Test
    fun testStartRecording() {
        init()
        Log.d("********", "TEST!")
        Assert.assertEquals(false, audioRecoder.isRecording())
        audioRecoder.startRecording()
        Assert.assertEquals(true, audioRecoder.isRecording())
        audioRecoder.stopRecording()
        Assert.assertEquals(false, audioRecoder.isRecording())
        after()
    }

   /* @Test
    fun testStopRecording() {
        audioRecoder.stopRecording()
    }*/

    private fun getContext(): Context {
        return InstrumentationRegistry.getInstrumentation().targetContext
    }
}