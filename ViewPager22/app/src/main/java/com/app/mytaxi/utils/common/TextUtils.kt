package com.app.mytaxi.utils.common

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView
import java.lang.reflect.Type

/**
 * Created by Ajay Deepak on 28-11-2019, 13:17
 */

 fun TextView.textDisplay(text: String)
    = SpannableString(text)
        .setSpan(StyleSpan(Typeface.BOLD), 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

