package com.browngoo.nembers.global.exception

import com.browngoo.nembers.global.dto.CommonResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.CONFLICT)d
    fun handleDataIntegrityViolationException(ex : DataIntegrityViolationException) : CommonResponse<*>{
        return CommonResponse.except(ex.message?:"UnknownError!")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(ex : MethodArgumentNotValidException) : CommonResponse<*>{
        val message = ex.fieldErrors.joinToString {
            "${it.defaultMessage}"
        }
        return CommonResponse.except(message)
    }
}