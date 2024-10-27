package com.user.app.Service

import com.user.app.Database.Models.userData
import com.user.app.Models.Request.loginRequest
import com.user.app.Models.Response.loginResponse

interface LoginService {

    fun userLogin(loginRequest: loginRequest): loginResponse

    fun getUser():userData
}