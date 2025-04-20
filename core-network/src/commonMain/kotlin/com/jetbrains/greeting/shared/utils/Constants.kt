package com.jetbrains.greeting.shared.utils

const val API_FAILED_WITH_SUCCESS_CODE = "API FAILED WITH SUCCESS CODE"
const val SUCCESS = "success"
const val ERROR_MESSAGE = "errorMessage"


object NetworkErrorMessages {
    const val SOME_ERROR_OCCURRED = "Some error occurred"
    const val ACCESS_TOKEN_EXPIRED = ""
    const val PLEASE_LOGIN_AGAIN = "Please login again"
    const val UNUSUAL_ACTIVITY_DETECTED = "Unusual activity detected"
    const val APP_UNDER_MAINTENANCE = "App under maintenance"
    const val PLEASE_CHECK_YOUR_INTERNET_CONNECTION = "Please check your internet connection"
    const val DATA_SERIALIZATION_ERROR = "Data serialization error"
    const val API_FAILED_WITH_SUCCESS_CODE = "API FAILED WITH SUCCESS CODE"
}

object NetworkErrorCodes {
    const val ACCESS_TOKEN_EXPIRED = 401
    const val REFRESH_TOKEN_EXPIRED = 403
    const val UNUSUAL_ACTIVITY_DETECTED = 417
    const val INTERNET_NOT_WORKING = 712
    const val UNKNOWN_ERROR_OCCURRED = 713
    const val NETWORK_CALL_CANCELLED = 714
    const val DATA_SERIALIZATION_ERROR = 715
}
object ResponseHeader {
    const val X_TRACE_ID = "X-Trace-Id"
}