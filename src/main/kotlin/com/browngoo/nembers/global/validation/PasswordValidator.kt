package com.browngoo.nembers.global.validation

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class PasswordValid(
    val message: String = "비밀번호는 영문,숫자,특수문자 포함 8~25자리 이어야 합니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PasswordValidator : ConstraintValidator<PasswordValid, String>{
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return if (value != null)
            PasswordBCrypt.isValid(value)
        else
            false
    }
}