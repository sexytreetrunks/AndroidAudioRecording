package com.treetrunks.audiorecording

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

class AudioServiceConnection : ServiceConnection {
    private lateinit var boundService: AudioRecordService

    private var isBound = false

    override fun onServiceDisconnected(name: ComponentName?) {
        isBound = false
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as AudioRecordService.MyBinder

        boundService = binder.getService()

        isBound = true
    }
}