package com.treetrunks.audiorecording

import android.media.MediaRecorder
import android.util.Log
import java.io.IOException
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

class AudioRecorder(val directoryPath: String) : IAudioRecord{
    private lateinit var mRecorder : MediaRecorder
    private var isRecording = false

    override fun initRecorder() {
        mRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
            setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        }
    }

    override fun isRecording(): Boolean {
        return isRecording
    }

    override fun startRecording() {
        try {
            mRecorder.setOutputFile(getSaveFilePath())
            mRecorder.prepare()
            mRecorder.start()
            isRecording = true
        } catch (ioEx: IOException) {
            Log.e(javaClass.simpleName, "Prepare Failed..")
            ioEx.printStackTrace()
            isRecording = false
        } catch (illegalEx: IllegalStateException) {
            Log.e(javaClass.simpleName, "Something wrong initiating..")
            illegalEx.printStackTrace()
            isRecording = false
        }
    }

    override fun stopRecording() {
        mRecorder.stop()
        isRecording = false
    }

    override fun release() {
        mRecorder.release()
    }

    private fun getSaveFilePath() : String {
        val sdf = SimpleDateFormat("yyyy-MM-dd_HH:mm")
        val timestamp = sdf.format(Date(System.currentTimeMillis()))
        return directoryPath + timestamp
    }
}