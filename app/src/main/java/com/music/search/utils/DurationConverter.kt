package com.music.search.utils

object DurationConverter {
    private const val SEPARATOR = ":"
    private const val DEFAULT_VALUE = "0:0"
    fun getDurationInMinutesText(durationInSeconds: Long): String {
        if (durationInSeconds <= 0) {
            return DEFAULT_VALUE
        }
        val minutes = durationInSeconds / 60
        val seconds = durationInSeconds % 60
        return minutes.toString() + SEPARATOR + seconds.toString()
    }
}