package com.jetbrains.greeting.shared.utils

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
open class ApiResponseWrapper<T>(
    @SerialName("data")
    val `data`: T,

    @SerialName("success")
    val success: Boolean?,

)