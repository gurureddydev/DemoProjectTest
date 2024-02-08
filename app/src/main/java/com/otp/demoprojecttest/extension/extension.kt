package com.otp.demoprojecttest.extension

import android.content.Context
import android.widget.Toast

object Extension {
    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}