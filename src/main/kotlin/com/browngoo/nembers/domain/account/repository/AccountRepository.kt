package com.browngoo.nembers.domain.account.repository

import com.browngoo.nembers.domain.account.entities.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>