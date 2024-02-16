package com.example.taskwise_eventmaster.presentation.sign_in

data class SignInResult(
    val data: UserData?, //my class
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)