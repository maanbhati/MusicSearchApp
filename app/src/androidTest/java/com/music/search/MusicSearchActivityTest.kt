package com.music.search

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.music.search.AssetsFileReader.readFileAsString
import com.music.search.ui.MusicSearchActivity
import com.music.search.utils.Constants
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MusicSearchActivityTest {
    @Rule
    var categoryDetailActivityActivityTestRule = ActivityTestRule(MusicSearchActivity::class.java)

    @BeforeClass
    @Throws(IOException::class)
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        Constants.BASE_URL = mockWebServer.url("test/").toString()
        mockWebServer.setDispatcher(object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(recordedRequest: RecordedRequest): MockResponse? {
                if (recordedRequest.path.equals(TOP_ARTISTS_REQUEST_URL, ignoreCase = true)) {
                    try {
                        return MockResponse().setResponseCode(200)
                            .setBody(readFileAsString(TOP_ARTISTS_JSON_FILE))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else if (recordedRequest.path.equals(TOP_TRACKS_REQUEST_URL, ignoreCase = true)) {
                    try {
                        return MockResponse().setResponseCode(200)
                            .setBody(readFileAsString(TOP_TRACKS_JSON_FILE))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else if (recordedRequest.path.equals(TOP_ALBUMS_REQUEST_URL, ignoreCase = true)) {
                    try {
                        return MockResponse().setResponseCode(200)
                            .setBody(readFileAsString(TOP_ALBUMS_JSON_FILE))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                return null
            }
        })
    }

    companion object {
        const val TOTAL_ITEMS_COUNT = 5
        const val TOP_ARTISTS_JSON_FILE = "top_artists.json"
        const val TOP_TRACKS_JSON_FILE = "top_tracks.json"
        const val TOP_ALBUMS_JSON_FILE = "top_albums.json"
        const val TOP_ARTISTS_REQUEST_URL =
            "/test/?method=user.gettopartists&format=json&user=drrobbins&limit=5&api_key="
        const val TOP_ALBUMS_REQUEST_URL =
            "/test/?method=user.gettopalbums&format=json&user=drrobbins&limit=5&api_key="
        const val TOP_TRACKS_REQUEST_URL =
            "/test/?method=user.gettoptracks&format=json&user=drrobbins&limit=5&api_key="
        const val LAST_EXPECTED_ARTIST_NAME = "X-Dream"
        const val LAST_EXPECTED_ARTIST_PLAY_COUNT = "30"
        const val LAST_EXPECTED_ALBUM_NAME = "Iowa"
        const val LAST_EXPECTED_ALBUM__PLAY_COUNT = "1163"
        const val LAST_EXPECTED_ALBUM_ARTIST = "Slipknot"
        const val LAST_EXPECTED_TRACK_NAME = "Fences"
        const val LAST_EXPECTED_TRACK_ARTIST = "Paramore"
        const val LAST_EXPECTED_TRACK_PLAY_COUNT = "200"
        const val LAST_EXPECTED_TRACK_DURATION = "3:18"
        private lateinit var mockWebServer: MockWebServer
    }

    @Test
    fun testDataDisplay() {
        try {
            Thread.sleep(5000)

            // cehck number of displayed artists
            Espresso.onView(ViewMatchers.withId(R.id.rclr_artists))
                .check(RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT))
            // scroll to the last item
            Espresso.onView(ViewMatchers.withId(R.id.rclr_artists)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(TOTAL_ITEMS_COUNT - 1)
            )
            // check last item data is displayed correctly
            checkArtistItem(
                LAST_EXPECTED_ARTIST_NAME,
                LAST_EXPECTED_ARTIST_PLAY_COUNT,
                TOTAL_ITEMS_COUNT - 1
            )
            // go to top albums
            Espresso.onView(ViewMatchers.withId(R.id.vp_main))
                .perform(ViewPagerSwipeToPositionAction(1))
            // check number of displayed albums
            Espresso.onView(ViewMatchers.withId(R.id.list_albums))
                .check(RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT))
            //scroll to the last album
            Espresso.onView(ViewMatchers.withId(R.id.list_albums)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(TOTAL_ITEMS_COUNT - 1)
            )
            // check last album data displayed correctly
            checkAlbumItem(
                LAST_EXPECTED_ALBUM_NAME,
                LAST_EXPECTED_ALBUM__PLAY_COUNT,
                LAST_EXPECTED_ALBUM_ARTIST,
                TOTAL_ITEMS_COUNT - 1
            )
            // go to top albums
            Espresso.onView(ViewMatchers.withId(R.id.vp_main))
                .perform(ViewPagerSwipeToPositionAction(2))
            // check number of displayed tracks
            Espresso.onView(ViewMatchers.withId(R.id.list_tracks))
                .check(RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT))
            //scroll to the last album
            Espresso.onView(ViewMatchers.withId(R.id.list_tracks)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(TOTAL_ITEMS_COUNT - 1)
            )
            // check last album data displayed correctly
            checkTrackItem(
                LAST_EXPECTED_TRACK_NAME,
                LAST_EXPECTED_TRACK_ARTIST,
                LAST_EXPECTED_TRACK_PLAY_COUNT,
                LAST_EXPECTED_TRACK_DURATION,
                TOTAL_ITEMS_COUNT - 1
            )
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun checkArtistItem(
        expectedArtistName: String,
        expectedArtistPlayCount: String,
        itemPosition: Int
    ) {
        // match artist name
        Espresso.onView(ViewMatchers.withId(R.id.rclr_artists)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_artist_name,
                expectedArtistName,
                itemPosition
            )
        )
        // match artist playcount
        Espresso.onView(ViewMatchers.withId(R.id.rclr_artists)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedArtistPlayCount,
                itemPosition
            )
        )
    }

    private fun checkAlbumItem(
        expectedAlbumName: String,
        expectedAlbumPlayCount: String,
        expectedAlbumArtist: String,
        itemPosition: Int
    ) {
        // check album name
        Espresso.onView(ViewMatchers.withId(R.id.list_albums)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_album_name,
                expectedAlbumName,
                itemPosition
            )
        )
        // check album artist
        Espresso.onView(ViewMatchers.withId(R.id.list_albums)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_album_artist,
                expectedAlbumArtist,
                itemPosition
            )
        )

        // match album playcount
        Espresso.onView(ViewMatchers.withId(R.id.list_albums)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedAlbumPlayCount,
                itemPosition
            )
        )
    }

    private fun checkTrackItem(
        expectedTrackName: String,
        expectedTrackArtistName: String,
        expectedTrackPlayCount: String,
        expectedTrackDuration: String,
        itemPosition: Int
    ) {
        // match track name
        Espresso.onView(ViewMatchers.withId(R.id.list_tracks)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_track_name,
                expectedTrackName,
                itemPosition
            )
        )
        // match track artist
        Espresso.onView(ViewMatchers.withId(R.id.list_tracks)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_track_artist,
                expectedTrackArtistName,
                itemPosition
            )
        )
        // match track play count
        Espresso.onView(ViewMatchers.withId(R.id.list_tracks)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedTrackPlayCount,
                itemPosition
            )
        )
        // match track duration
        Espresso.onView(ViewMatchers.withId(R.id.list_tracks)).check(
            RecyclerViewItemStringDataAssertion(
                R.id.txt_duration,
                expectedTrackDuration,
                itemPosition
            )
        )
    }
}