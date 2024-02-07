package com.example.learning_compose

data class User(val name: String, val profession: String, val dob: Long)
object Database {
    private val _users = mutableListOf<User>()

    fun createUser(name: String, profession: String, dob: Long?): Boolean {
        if (name.isEmpty() || profession.isEmpty() || dob == null)
            return false
        _users.add(User(name, profession, dob))
        return true
    }

    fun removeUser(index: Int): Boolean {
        _users.removeAt(index)
        return true
    }

    fun getAllUsers(): List<User> {
        return _users
    }
}