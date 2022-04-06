package com.browngoo.nembers.domain.account.service

import com.browngoo.nembers.domain.account.repository.AccountRepository
import com.browngoo.nembers.global.dto.AccountCreateModel
import com.browngoo.nembers.global.dto.AccountModel
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository
) : AccountService {
    override fun createAccount(accountCreateModel: AccountCreateModel) {
        accountRepository.save(accountCreateModel.toAccountEntity())
    }

    override fun getAccount(id: Long): AccountModel {
        val account = accountRepository.findById(id).orElseThrow()
        return AccountModel.from(account)
    }
}