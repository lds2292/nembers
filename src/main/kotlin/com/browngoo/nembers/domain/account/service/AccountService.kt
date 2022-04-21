package com.browngoo.nembers.domain.account.service

import com.browngoo.nembers.global.dto.AccountCreateModel
import com.browngoo.nembers.global.dto.AccountModel
import com.browngoo.nembers.global.dto.AccountSignModel
import org.springframework.validation.annotation.Validated

interface AccountService {
    fun createAccount(accountCreateModel: AccountCreateModel)
    fun getAccount(id: Long) : AccountModel
    fun existsAccount(email: String) : Boolean
    fun loginAccount(model: AccountSignModel)
}