package com.browngoo.nembers.global.dto

import java.time.LocalDateTime

class CommonResponse<T> (
    val payload : T? = null,
    val message : String? = null,
    val timestamp : LocalDateTime = LocalDateTime.now()
){
    companion object{
        fun except(errorMessage : String): CommonResponse<Unit>{
            return CommonResponse(payload = null, message = errorMessage)
        }
    }
}