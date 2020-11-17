package com.newspaperapp.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.startActivity

const val TAG = "CustomTabsBrowser"

object CustomTabsBrowser {

    fun openCustomTab(url: String?, ctx: Context) {
        if (url.isNullOrBlank()) {
            return
        }
        var link = url
        val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()
        try {
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            /**
             * Url addresses must be preceded by http://
             */
            if (url.startsWith("www")) {
                link = "http://$url"
            }
            customTabsIntent.launchUrl(ctx, Uri.parse(link))
        } catch (e: ActivityNotFoundException) {
            openBrowser(link, ctx)
        }
    }

    private fun openBrowser(link: String, ctx: Context) {
        try {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(ctx, browserIntent, null)
        } catch (ex: ActivityNotFoundException) {
            Log.e(TAG, " No Activity found to handle Intent")
        }
    }
}