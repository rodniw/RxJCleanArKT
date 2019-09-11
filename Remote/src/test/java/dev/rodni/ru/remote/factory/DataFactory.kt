package dev.rodni.ru.remote.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * this class will help to inflate models in tests
 */
object DataFactory {

        fun randomUuid(): String {
            return UUID.randomUUID().toString()
        }

        fun randomInt(): Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomBoolean(): Boolean {
            return Math.random() < 0.5
        }

}