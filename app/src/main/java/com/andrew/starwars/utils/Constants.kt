package com.andrew.starwars.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.andrew.starwars.R

class Constants {
    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
        const val THEME_ID = "theme_id"
        const val PREFERENCES_NAME = "theme"

    }
    object LoadingScreen {
        private var dialog: Dialog? = null //obj
        fun displayLoadingWithText(
            context: Context?,
            cancelable: Boolean
        ) { // function -- context(parent (reference))
            dialog = Dialog(context!!)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.layout_loading_screen)
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCancelable(cancelable)
            try {
                dialog!!.show()
            } catch (_: Exception) {
            }
        }

        fun hideLoading() {
            try {
                if (dialog != null) {
                    dialog!!.dismiss()
                }
            } catch (_: Exception) {
            }
        }
    }

}