package com.treetrunks.audiorecording

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtil(val context: Context){
    private val CODE_RECORD_AUDIO = 10000
    private val CODE_WRITE_EXTERNAL_STORAGE = 20000


    public fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    public fun grantWriteExternalStoragePerm() {
        grantPermission(WRITE_EXTERNAL_STORAGE, CODE_WRITE_EXTERNAL_STORAGE)
    }

    public fun grantRecordAudio() {
        grantPermission(RECORD_AUDIO, CODE_RECORD_AUDIO)
    }

    private fun grantPermission(permission: String, code: Int) {

        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), code)
    }
}