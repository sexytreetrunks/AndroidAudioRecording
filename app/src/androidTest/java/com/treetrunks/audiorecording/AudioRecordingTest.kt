package com.treetrunks.audiorecording

import android.content.Context
import android.os.Environment
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

    @Before
    fun init() {
        val dir_file = File(getContext().getExternalFilesDir(null), "child")
        if (!dir_file.exists()) dir_file.mkdir()
        audioRecoder = AudioRecorder(dir_file.absolutePath)
    }

    @After
    fun after() {
        audioRecoder.release()
    }

    @Test
    fun testRecording() {
        Assert.assertEquals(false, audioRecoder.isRecording())
        audioRecoder.startRecording()
        Assert.assertEquals(true, audioRecoder.isRecording())
        audioRecoder.stopRecording()
        Assert.assertEquals(false, audioRecoder.isRecording())
    }

    private fun getContext(): Context {
        return InstrumentationRegistry.getInstrumentation().targetContext
    }
}