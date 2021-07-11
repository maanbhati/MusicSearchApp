package com.music.search

import androidx.test.InstrumentationRegistry
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object AssetsFileReader {
    @JvmStatic
    @Throws(IOException::class)
    fun readFileAsString(fileName: String?): String {
        val inputStream = InstrumentationRegistry.getTargetContext().assets.open(fileName!!)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val builder = StringBuilder()
        var line: String? = null
        while (reader.readLine().also { line = it } != null) {
            builder.append(line)
        }
        reader.close()
        return builder.toString()
    }
}