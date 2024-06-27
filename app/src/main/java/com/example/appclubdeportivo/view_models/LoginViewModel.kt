package com.example.appclubdeportivo.view_models

import androidx.lifecycle.ViewModel
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val userDao: UserDao = appDatabase.userDao()

    fun validateCredentials(username: String, password: String): Flow<Boolean> = flow {
        emit(validateCredentialsDB(username, password))
    }.flowOn(Dispatchers.IO)

    private suspend fun validateCredentialsDB(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user?.password == password && user.isActive
    }
}

