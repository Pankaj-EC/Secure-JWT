package com.user.app.Exceptions

import com.user.app.Utils.ResponseAdvice.StatusCode
import java.text.MessageFormat

class AppException: BaseException {

    constructor(err: StatusCode): super(err)

    constructor(err: StatusCode, param: String): super(err, param){
        statuscode = AppStatusCodes.createResponseCode(err.statusCode, MessageFormat.format(err.statusMessage, param))
    }
}