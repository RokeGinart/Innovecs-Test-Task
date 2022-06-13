package com.example.magicthegathering.data.utils

import android.content.Context
import android.widget.Toast

object CustomToast {

    fun makeToast(context : Context, text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}