package com.user.app.Service

import com.user.app.Models.Common.userDetails
import org.springframework.security.core.Authentication

interface AuthenticationService {

    //Return [Authentication] extracted from jwt
    val authentication: Authentication?

    //Return [userDetails] extracted from jwt
    val user: userDetails

    //Return [true] if authentication passed
    fun isCustomerAuthentication(): Boolean

}