package com.user.app.Service.impls

import com.user.app.Database.Models.userData
import com.user.app.Database.Repository.userRepo
import com.user.app.Exceptions.AppException
import com.user.app.Exceptions.AppStatusCodes
import com.user.app.Models.Common.userDetails
import com.user.app.Models.Request.loginRequest
import com.user.app.Models.Response.loginResponse
import com.user.app.Security.JwtUtils
import com.user.app.Service.AuthenticationService
import com.user.app.Service.LoginService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class LoginServiceImpl(
        private var jwtUtils: JwtUtils,
        private val userRepo: userRepo,
        private val authenticationService: AuthenticationService
):LoginService {

    fun generateJwtAndAddSession(userDetails: userDetails): loginResponse {
        return loginResponse(
            userDetails.userId,
            jwtUtils.generateJwtToken(userDetails),
            LocalDateTime.now().toString()
        )
    }


    override fun userLogin(loginRequest: loginRequest): loginResponse {
        var user= userData()

        try {user =userRepo.findByUserId(loginRequest.userId)
        }catch (_:Exception){throw AppException(AppStatusCodes.USER_NOT_EXIST)}

        // If user is found, check password
        if (loginRequest.password != user.password) {
            throw AppException(AppStatusCodes.WRONG_PASSWORD)
        }

        // Generate JWT and session if login is successful
        return generateJwtAndAddSession(
                userDetails = userDetails(
                        userId = user.userId,
                        channelId = "W",
                        timeStemp = LocalDateTime.now(),
                        UUID.randomUUID()
                )
        )
    }

    override fun getUser(): userData {
       try {
           return userRepo.findByUserId(authenticationService.user.userId)
       }catch (_:Exception){
           throw AppException(AppStatusCodes.USER_NOT_EXIST)
       }
    }

}

