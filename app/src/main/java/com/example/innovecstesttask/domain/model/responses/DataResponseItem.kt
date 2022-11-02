package com.example.innovecstesttask.domain.model.responses

data class DataResponseItem(
    val cool_down: Int,
    val enabled: Boolean,
    val priority: Int,
    val type: String,
    val valid_days: List<Int>
)