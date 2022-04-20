package com.browngoo.nembers.global.exception

import com.browngoo.nembers.global.Log
import com.browngoo.nembers.global.dto.CommonResponse
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.apache.tomcat.util.file.Matcher
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.regex.Pattern

@RestControllerAdvice
class ExceptionAdvice {
    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
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

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException) : CommonResponse<*>{
        val cause = ex.cause as MismatchedInputException
        val pathMessage = cause.path.toString()
        log.warn("HttpMessageNotReadableException -> {}", pathMessage)
        return CommonResponse.except("요청 메세지를 처리 할 수 없습니다")
    }

    @ExceptionHandler(IllegalStateException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalStateException(ex : IllegalStateException) : CommonResponse<*>{
        return CommonResponse.except(ex.message?:"")
    }

    companion object : Log
}