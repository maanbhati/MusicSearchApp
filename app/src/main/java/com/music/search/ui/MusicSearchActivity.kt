package com.music.search.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.music.search.R
import com.music.search.adapters.MusicSearchPagerAdapter
import com.music.search.models.Album
import com.music.search.models.Artist
import com.music.search.models.Track
import com.music.search.ui.album.AlbumsFragment
import com.music.search.ui.artists.ArtistsFragment
import com.music.search.ui.tracks.TracksFragment
import kotlinx.android.synthetic.main.activity_music_search.*

class MusicSearchActivity : AppCompatActivity(), ArtistsFragment.OnFragmentInteractionListener,
    AlbumsFragment.OnFragmentInteractionListener,
    TracksFragment.OnFragmentInteractionListener {

    var mAdapter: MusicSearchPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_search)
        looseSearchEditTextFocus()
        edt_search?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty()) searchUser("po")
            }
        })
        initializeFragments()

        edt_search.setOnEditorActionListener { textView, actionId, event ->
            onEditorAction(textView, actionId, event)
        }
        search?.setOnClickListener {
            searchClick()
        }
    }

    private fun initializeFragments() {
        mAdapter = MusicSearchPagerAdapter(supportFragmentManager, this)

        vp_main?.adapter = mAdapter
        vp_main?.offscreenPageLimit = 3
        tl_main?.setupWithViewPager(vp_main)
    }

    fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (isValidSearch(v.text.toString())) {
                searchUser(v.text.toString())
            } else {
                showEnterValidUserNameToast()
            }
            looseSearchEditTextFocus()
            return true
        }
        return false
    }

    fun searchClick() {
        if (isValidSearch(edt_search?.text.toString())) {
            searchUser(edt_search?.text.toString())
        } else {
            showEnterValidUserNameToast()
        }
        looseSearchEditTextFocus()
    }

    private fun showEnterValidUserNameToast() {
        Toast.makeText(this, R.string.request_user_name, Toast.LENGTH_SHORT).show()
    }

    private fun isValidSearch(search: String?): Boolean {
        return !TextUtils.isEmpty(search)
    }

    // loops the base fragments and notify them to search with the given userName
    private fun searchUser(userName: String) {
        for (fr in mAdapter?.getFragments()!!) {
            if (fr is BaseFragment) {
                fr.searchUserName(userName)
            }
        }
    }

    override fun onArtistClicked(artist: Artist) {
        // open artist url
        openUrl(artist.url)
    }

    override fun onAlbumClicked(album: Album) {
        // open album url
        openUrl(album.url)
    }

    override fun onTrackClicked(track: Track) {
        // open track url
        openUrl(track.url)
    }

    fun openUrl(url: String?) {
        if (!TextUtils.isEmpty(url)) {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(url)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    // hide keyboard after search
    private fun looseSearchEditTextFocus() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        edt_search?.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}