package com.music.search

import com.music.search.utils.DurationConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class DurationConverterTest {
    @Test
    @Throws(Exception::class)
    fun test_duration_in_minutes() {
        assertEquals(
            DurationConverter.getDurationInMinutesText(FIRST_SECONDS),
            FIRST_EXPECTED_RESULT
        )
        assertEquals(
            DurationConverter.getDurationInMinutesText(SECOND_SECONDS),
            SECOND_EXPECTED_RESULT
        )
        assertEquals(
            DurationConverter.getDurationInMinutesText(THIRD_SECONDS),
            THIRD_EXPECTED_RESULT
        )
        assertEquals(
            DurationConverter.getDurationInMinutesText(FOURTH_SECONDS),
            FOURTH_EXPECTED_RESULT
        )
        assertEquals(
            DurationConverter.getDurationInMinutesText(FIFTH_SECONDS),
            FIFTH_EXPECTED_RESULT
        )
        assertEquals(
            DurationConverter.getDurationInMinutesText(SIXTH_SECONDS),
            SIXTH_EXPECTED_RESULT
        )
    }

    companion object {
        const val FIRST_SECONDS = 360L
        const val FIRST_EXPECTED_RESULT = "6:0"
        const val SECOND_SECONDS = 244L
        const val SECOND_EXPECTED_RESULT = "4:4"
        const val THIRD_SECONDS = 650L
        const val THIRD_EXPECTED_RESULT = "10:50"
        const val FOURTH_SECONDS = 1560L
        const val FOURTH_EXPECTED_RESULT = "26:0"
        const val FIFTH_SECONDS = -10L
        const val FIFTH_EXPECTED_RESULT = "0:0"
        const val SIXTH_SECONDS: Long = 0
        const val SIXTH_EXPECTED_RESULT = "0:0"
    }
}