package com.example.coworking_admin.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coworking_admin.model.AccountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : ViewModel() {

    var currentAccount by mutableStateOf(AccountModel(0))
        private set

    var detailState by mutableStateOf(DetailScreenState.OPTIONS)

    fun getSomeInfo() : String {
        return "Calculated info ${Calendar.getInstance().time}"
    }

    fun setAccount(accountModel: AccountModel) {
        currentAccount = accountModel
    }

    fun onChangeState(state: DetailScreenState) {
        detailState = state
    }

    fun onSaveClick(money: String) {
        var moni = 0
        try {
            moni = money.toInt()
        } catch (x : Exception) {
           // IGNORE
        }
        currentAccount = currentAccount.copy(debt = currentAccount.debt - moni)
    }


}

enum class DetailScreenState {
    OPTIONS, ORDER, PAYMENT,
}

