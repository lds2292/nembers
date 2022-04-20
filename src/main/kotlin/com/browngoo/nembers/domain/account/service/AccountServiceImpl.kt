package com.browngoo.nembers.domain.account.service

import com.browngoo.nembers.domain.account.repository.AccountRepository
import com.browngoo.nembers.global.dto.AccountCreateModel
import com.browngoo.nembers.global.dto.AccountModel
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository
) : AccountService {
    override fun createAccount(accountCreateModel: AccountCreateModel) {
        if (existsAccount(accountCreateModel.email)) {
            throw DataIntegrityViolationException("already account : ${accountCreateModel.email}")
        }
        accountRepository.save(accountCreateModel.toAccountEntity())
    }

    override fun getAccount(id: Long): AccountModel {
        val account = accountRepository.findById(id).orElseThrow()
        return AccountModel.from(account)
    }

    override fun existsAccount(email: String): Boolean {
        return accountRepository.existsByEmail(email)
    }
}