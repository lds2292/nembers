package com.browngoo.nembers.global.dto

import com.browngoo.nembers.domain.account.entities.Account
import com.browngoo.nembers.global.validation.PasswordBCrypt
import com.browngoo.nembers.global.validation.PasswordValid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class AccountCreateModel(
    @field:NotBlank(message = "이름은 필수 입니다")
    val name : String,
    @field:NotBlank(message = "별명은 필수 입니다")
    val nickName : String,
    @field:Email(message = "이메일 형식을 확인하세요")
    val email : String,
    @field:PasswordValid
    val password: String,
){
    fun toAccountEntity() = Account(name, nickName, email, password)
}

data class AccountSignModel(
    @field:Email(message = "이메일 형식을 확인하세요")
    val email : String,
    @field:PasswordValid
    val password: String
){
    fun isComparePassword(hashedText : String){
        check(PasswordBCrypt.compare(password, hashedText)){
            "이메일 또는 비밀번호를 확인하세요"
        }
    }
}

class AccountModel(
    val id: Long,
    val name: String,
    val nickName: String,
    val email: String? = null
){
    companion object{
        fun from(account: Account) =
            AccountModel(account.id!!, account.name, account.nickName, account.email)
    }
}