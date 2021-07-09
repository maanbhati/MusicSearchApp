package com.music.search.domain.executer

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UiThread internal constructor() : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}