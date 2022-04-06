package com.browngoo.nembers.domain.account.controller

import com.browngoo.nembers.domain.account.service.AccountService
import com.browngoo.nembers.global.Log
import com.browngoo.nembers.global.dto.AccountCreateModel
import com.browngoo.nembers.global.dto.AccountModel
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/accounts")
class AccountController(
    val accountService: AccountService
) {
    @GetMapping("{id}")
    fun getAccount(@PathVariable id : Long) : AccountModel {
        return accountService.getAccount(id)
    }

    @PostMapping
    fun createAccount(@Valid @RequestBody model : AccountCreateModel){
        accountService.createAccount(model)
    }

    companion object : Log
}