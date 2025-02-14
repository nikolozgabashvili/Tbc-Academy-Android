package com.example.tbcacademyhomework.data.users.repository

import androidx.room.withTransaction
import com.example.tbcacademyhomework.data.users.database.AppDatabase
import com.example.tbcacademyhomework.data.users.util.toDomain
import com.example.tbcacademyhomework.data.users.util.toUserEntity
import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import javax.inject.Inject

class UsersLocalRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : UsersLocalRepository {

    private val usersDao = appDatabase.userDao

    override suspend fun getUsers(page: Int): List<User> {
        val users = usersDao.getUsersBeforePage(page).map { it.toDomain() }
        return users
    }

    override suspend fun getLoadedPageCount(): Int {
        return usersDao.getLoadedPageNumber()
    }


    override suspend fun updateUsers(page: Int, users: List<User>): List<User> {
        return appDatabase.withTransaction {
            usersDao.deleteUsersByPage(page)
            usersDao.upsertUsers(users.map { it.toUserEntity(page) })
            usersDao.getUsersBeforePage(page).map { it.toDomain() }
        }
    }
}