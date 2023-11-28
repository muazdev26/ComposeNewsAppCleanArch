package com.muazdev26.composenewsapp.util

import android.content.Context
import androidx.paging.LoadState
import com.muazdev26.composenewsapp.R
import java.net.ConnectException
import java.net.SocketTimeoutException

fun LoadState.Error?.parseErrorMessage(context: Context): String {
    return when (this?.error) {

        is SocketTimeoutException -> {
            context.getString(R.string.server_not_available)
        }

        is ConnectException -> {
            context.getString(R.string.internet_un_available)
        }

        else -> {
            context.getString(R.string.unknow_error)
        }
    }
}