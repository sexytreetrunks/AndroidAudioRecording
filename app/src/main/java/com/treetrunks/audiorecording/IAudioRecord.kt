package com.treetrunks.audiorecording

interface IAudioRecord {

    public fun initRecorder()

    public fun isRecording():Boolean

    public fun startRecording()

    public fun stopRecording()

    public fun release()

}