package com.music.search.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.ref.WeakReference

object ImageLoader {
    fun loadImage(
        context: Context,
        imageUrl: String?,
        placeHolderResourceID: Int,
        imageView: ImageView
    ) {
        val weakReference = WeakReference(context)
        Glide.with(weakReference.get()).load(imageUrl).asBitmap().placeholder(placeHolderResourceID)
            .into(imageView)
    }

    // load image with callbacks
    fun loadImage(
        context: Context,
        imageUrl: String?,
        placeHolderResourceID: Int,
        imageView: ImageView?,
        callbacks: ImageLoaderCallbacks?
    ) {
        val weakReference = WeakReference(context)
        Glide.with(weakReference.get()).load(imageUrl).asBitmap().placeholder(placeHolderResourceID)
            .listener(object : RequestListener<String?, Bitmap?> {
                override fun onException(
                    e: Exception,
                    model: String?,
                    target: Target<Bitmap?>,
                    isFirstResource: Boolean
                ): Boolean {
                    callbacks?.onFail(e)
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: String?,
                    target: Target<Bitmap?>,
                    isFromMemoryCache: Boolean,
                    isFirstResource: Boolean
                ): Boolean {
                    callbacks?.onSuccess()
                    return false
                }
            })
            .into(imageView)
    }

    interface ImageLoaderCallbacks {
        fun onSuccess()
        fun onFail(e: Exception?)
    }
}