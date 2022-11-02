package com.example.innovecstesttask.utils

import android.content.Context
import android.widget.Toast

object ViewUtils {

    fun makeToast(context : Context, text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}