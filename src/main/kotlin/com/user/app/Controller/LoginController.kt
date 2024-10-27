package com.user.app.Controller

import com.user.app.Database.Models.userData
import com.user.app.Models.Request.*
import com.user.app.Models.Response.loginResponse
import com.user.app.Service.LoginService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api")
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: loginRequest):loginResponse{
        return loginService.userLogin(loginRequest)
    }

    @GetMapping("/getUser")
    fun getUser():userData{
        return loginService.getUser()
    }
}