package com.browngoo.nembers.domain.account.controller

import com.browngoo.nembers.domain.account.repository.AccountRepository
import com.browngoo.nembers.domain.account.service.AccountService
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(AccountController::class)
internal class AccountControllerTest{

    @Autowired
    private lateinit var mockMvc : MockMvc

    @MockBean
    private lateinit var accountService: AccountService

    @MockBean
    private lateinit var accountRepository: AccountRepository

    @Nested
    inner class 고객{
        @Test
        fun 등록을_성공한다(){
            //given
            val accountString = """
                {"name":"이름", "nickName":"별명"}
                """.trimIndent()

            //when
            val perform = mockMvc.perform(
                MockMvcRequestBuilders.post(ACCOUNTS_PATH)
                    .content(accountString)
                    .contentType(MediaType.APPLICATION_JSON)
            )

            //then
            perform.andExpect(MockMvcResultMatchers.status().isOk)
        }

        @Test
        fun 등록을_닉네임이_없어서_실패한다(){
            val accountString = """
                {"name":"이름", "nickName":""}
                """.trimIndent()

            mockMvc.perform(
                MockMvcRequestBuilders.post(ACCOUNTS_PATH)
                    .content(accountString)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }

    companion object{
        const val ACCOUNTS_PATH : String = "/accounts"
    }
}