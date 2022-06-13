package com.example.magicthegathering.domain.model.ui.main_screen

import androidx.annotation.StringRes
import com.example.magicthegathering.R

enum class ErrorTypes(@StringRes val error : Int) {
    SMTH_WENT_WRONG(R.string.smth_went_wrong),
    EMPTY_DATA(R.string.empty_body),
}