package com.user.app.Models.Request

import java.io.Serializable

open class signUpRequest (
        var email: String = "",
        var password: String =""
    ):Serializable