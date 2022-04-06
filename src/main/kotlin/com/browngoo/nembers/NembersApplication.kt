package com.browngoo.nembers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NembersApplication

fun main(args: Array<String>) {
    runApplication<NembersApplication>(*args)
}
