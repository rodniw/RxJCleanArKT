package dev.rodni.ru.domain.test

import dev.rodni.ru.domain.model.Project
import java.util.*

//factory which will send fake project object to my tests
object ProjectDataFactory {

    //generate random string
    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    //generate random boolean
    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    //this will give me a fake project object
    fun makeProject(): Project {
        return Project(randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomBoolean())
    }

    //this will give ma a list of fake projects
    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}