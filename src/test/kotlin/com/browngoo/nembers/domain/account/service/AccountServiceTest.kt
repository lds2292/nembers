package com.browngoo.nembers.domain.account.service

import com.browngoo.nembers.global.dto.AccountCreateModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
internal class AccountServiceTest constructor(
    @Autowired
    private val accountService: AccountService
){

    private fun getExpectedAccount() = AccountCreateModel(
        "lyon", "lyonking", "lycon@kakao.com"
    )

    @Test
    @DisplayName("회원 가입")
    fun createAccount (){
        val expectedAccount = getExpectedAccount()
        accountService.createAccount(expectedAccount)
    }
}