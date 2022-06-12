package com.example.magicthegathering.domain.callback

import com.example.magicthegathering.domain.model.ui.DateModel


interface OnDatePickerListener {
    fun dateSelected(date : DateModel)
}