package com.example.coworking_admin

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.coworking_admin.model.AccountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var userList = mutableStateListOf(AccountModel(1, "ONE"), AccountModel(2,"TWO"))

    fun getAccountById(id:Int) : AccountModel {
       return userList.find { it.id == id } ?: AccountModel(0)
    }
}