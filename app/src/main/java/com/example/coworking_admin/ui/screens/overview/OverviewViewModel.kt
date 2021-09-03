package com.example.coworking_admin.ui.screens.overview

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.coworking_admin.model.AccountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
) : ViewModel() {

    var userList = mutableStateListOf(AccountModel(0))


 }