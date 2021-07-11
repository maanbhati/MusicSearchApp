package com.music.search.ui

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun searchUserName(userName: String)
}