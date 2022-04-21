package com.browngoo.nembers.domain.account.service

import com.browngoo.nembers.domain.account.repository.AccountRepository
import com.browngoo.nembers.global.dto.AccountCreateModel
import com.browngoo.nembers.global.dto.AccountModel
import com.browngoo.nembers.global.dto.AccountSignModel
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository
) : AccountService {
    override fun createAccount(@Valid accountCreateModel: AccountCreateModel) {
        if (existsAccount(accountCreateModel.email)) {
            throw DataIntegrityViolationException("이미 존재하는 이메일입니다")
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

    override fun loginAccount(model: AccountSignModel) {
        val account = accountRepository.findByEmail(model.email)
            ?: throw NoSuchElementException("유저를 찾을 수 없습니다")

        model.isComparePassword(account.password)
    }
}