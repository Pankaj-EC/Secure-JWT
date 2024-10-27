package com.user.app.Models.Response

data class loginResponse(
    var userId: String = "",
    var token: String ="",
    var lastLogin: String="",
)
