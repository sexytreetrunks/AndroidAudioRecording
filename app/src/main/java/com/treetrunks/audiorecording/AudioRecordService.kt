package com.treetrunks.audiorecording

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class AudioRecordService : Service() {

    private val binder = MyBinder()

    private var audioRecording: AudioRecorder? = null

    override fun onCreate() {
        super.onCreate()

        audioRecording = AudioRecorder("")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action) {
            AudioActions.ACTION_START_RECORD -> {
                //build notification & startForeground
                TODO("build notification & startForeground")

                audioRecording?.startRecording()
            }
            AudioActions.ACTION_STOP_RECORD ->{
                audioRecording?.stopRecording()
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

        audioRecording = null
    }

    inner class MyBinder : Binder() {
        fun getService() = this@AudioRecordService
    }
}