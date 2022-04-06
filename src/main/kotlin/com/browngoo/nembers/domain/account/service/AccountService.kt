package com.browngoo.nembers.domain.account.service

import com.browngoo.nembers.global.dto.AccountCreateModel
import com.browngoo.nembers.global.dto.AccountModel

interface AccountService {
    fun createAccount(accountCreateModel: AccountCreateModel)
    fun getAccount(id: Long) : AccountModel
}