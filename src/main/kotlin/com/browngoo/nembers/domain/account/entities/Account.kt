package com.browngoo.nembers.domain.account.entities

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Entity
class Account(
    @Column(length = 50)
    @NotBlank(message = "이름은 필수 입니다")
    val name : String,
    @Email
    val email : String,

){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
}