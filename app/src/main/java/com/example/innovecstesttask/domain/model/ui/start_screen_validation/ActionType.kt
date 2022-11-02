package com.example.innovecstesttask.domain.model.ui.start_screen_validation

sealed class ActionType{
    object None : ActionType()
    object Animation : ActionType()
    object ToastMessage : ActionType()
    object Call : ActionType()
    object Notification : ActionType()
}
