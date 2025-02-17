package com.user.app.Service.impls

import com.user.app.Models.Common.userDetails
import com.user.app.Service.AuthenticationService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl: AuthenticationService{


    override val authentication: Authentication? get() = SecurityContextHolder.getContext().authentication

    override val user: userDetails get() = authentication?.principal as userDetails

    override fun isCustomerAuthentication(): Boolean {
       return if(authentication != null){
           !authentication!!.credentials.equals("")
       }else false
    }
}