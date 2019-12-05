package com.app.mytaxi.utils.display

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.app.mytaxi.R
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Ajay Deepak on 26-11-2019, 11:49
 */
object Display {

    fun snackShow(context: Context, view: View, text: CharSequence){

        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).apply {
            setBackgroundTint(ContextCompat.getColor(context,R.color.red))
            setText(R.string.connection_error).setTextColor(ContextCompat.getColor(context,R.color.white))
            show()
        }
    }
}