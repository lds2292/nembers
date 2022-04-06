package com.browngoo.nembers.domain.account.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null

@Entity
class Account(
    @Column(length = 50)
    val name : String,

    @Column(length = 50)
    val nickName: String,

    val email : String? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
    val expiredAt: LocalDateTime? = null

){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
}