package dev.rodni.ru.cache.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * provides random values for
 * @see ProjectDataFactory
 * and
 * @see ConfigDataFactory
 */
object DataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }
}