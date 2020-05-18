package com.treetrunks.audiorecording

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var audioRecordService: AudioRecordService

    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionUtil = PermissionUtil(this)
        if (!permissionUtil.hasPermission(RECORD_AUDIO)) {
            permissionUtil.grantRecordAudio()
        }
        if (!permissionUtil.hasPermission(WRITE_EXTERNAL_STORAGE)) {
            permissionUtil.grantWriteExternalStoragePerm()
        }

        this.record_start.setOnClickListener(this)
        this.record_stop.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.record_start -> {
                //TODO(" 퍼미션 추가되어있는지 확인후 아래 코드 진행")

                if (isRecording) {
                    Toast.makeText(
                        this,
                        "audio is recording",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    startRecord()
                    isRecording = true
                }

            }
            R.id.record_stop -> {
                if (isRecording) {
                    stopRecord()
                    isRecording = false
                }
            }
        }
    }

    private fun startRecord() {
        val intent = Intent(this, AudioRecordService::class.java)
        intent.action = AudioActions.ACTION_START_RECORD

        startService(intent)
    }

    private fun stopRecord() {
        val intent = Intent(this, AudioRecordService::class.java)
        intent.action = AudioActions.ACTION_STOP_RECORD

        startService(intent)
    }
}
