package com.example.magicthegathering.domain.model.ui.start_screen_validation

import androidx.annotation.StringRes
import com.example.magicthegathering.R

enum class EmailErrors(@StringRes val error : Int) {
    EMPTY(R.string.email_empty_validation),
    INVALID_EMAIL(R.string.email_invalid_form_validation)
}