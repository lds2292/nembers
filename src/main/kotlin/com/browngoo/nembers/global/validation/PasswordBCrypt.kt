package com.browngoo.nembers.global.validation

import org.mindrot.jbcrypt.BCrypt
import java.util.regex.Pattern

object PasswordBCrypt : Password {
    private const val PASSWORD_RULES = """^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,25}${'$'}"""

    override fun fire(plainText: String) : String{
        return BCrypt.hashpw(plainText, BCrypt.gensalt())
    }

    override fun compare(plainText: String, hashedText: String) : Boolean {
        return BCrypt.checkpw(plainText, hashedText)
    }

    override fun isValid(plainText: String) : Boolean {
        return Pattern.compile(PASSWORD_RULES).matcher(plainText).matches()
    }
}