package com.example.magicthegathering.domain.model.ui.start_screen_validation

data class StartScreenValidation (
    val nameError: NameErrors?,
    val surnameError: SurnameErrors?,
    val emailError: EmailErrors?,
    val dateError: DateErrors?,
)
