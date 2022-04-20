package com.browngoo.nembers.domain.account.entities

import com.browngoo.nembers.global.validation.PasswordBCrypt
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
    @Convert(converter = PasswordConvert::class)
    val password : String,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
    val expiredAt: LocalDateTime? = null

){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
}

class PasswordConvert : AttributeConverter<String, String>{
    override fun convertToDatabaseColumn(attribute: String): String {
        return PasswordBCrypt.fire(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): String {
        return dbData ?: ""
    }
}