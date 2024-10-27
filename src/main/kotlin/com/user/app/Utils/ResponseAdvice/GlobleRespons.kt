package com.user.app.Utils.ResponseAdvice

data class GlobleRespons<T>(
    var data: T,
    var status: StatusResponse
)
