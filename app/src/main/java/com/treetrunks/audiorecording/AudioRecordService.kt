package com.treetrunks.audiorecording

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.io.File

class AudioRecordService : Service() {

    private val binder = MyBinder()

    private lateinit var audioRecording: AudioRecorder

    override fun onCreate() {
        super.onCreate()

        audioRecording = AudioRecorder(getSavePath())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action) {
            AudioActions.ACTION_START_RECORD -> {
                //build notification & startForeground
                Log.d(javaClass.simpleName, "action start")
                audioRecording.startRecording()
            }
            AudioActions.ACTION_STOP_RECORD ->{
                Log.d(javaClass.simpleName, "action stop")
                audioRecording.stopRecording()
                stopSelf()
            }
        }
        return START_NOT_STICKY // let system not restart service
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isRecording())
            audioRecording.stopRecording()

        audioRecording.release()
    }

    public fun isRecording() : Boolean {
        return audioRecording.isRecording()
    }

    inner class MyBinder : Binder() {
        fun getService() = this@AudioRecordService
    }

    private fun getSavePath(): String {
        var externalDir = File(getExternalFilesDir(null), "record_")
        return externalDir.absolutePath
    }
}