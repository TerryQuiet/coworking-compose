package com.example.coworking_admin.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coworking_admin.ui.components.*

@Composable
fun AccountDetailScreen(
    viewModel: DetailViewModel = viewModel()
) {
   // viewModel.setAccount()
    val someInfo = remember { viewModel.getSomeInfo() }
    Column {
        TopAccountInfo(
            photoSlot = {
                AccountPicture(
                    pictureUrl = viewModel.currentAccount.pictureUri,
                    name = viewModel.currentAccount.name
                )
            },
            infoSlot = {
                InfoSlot(
                    debt = viewModel.currentAccount.debt.toString(),
                    someInfo = someInfo
                )
            }
        )

        Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(8.dp))
        when (viewModel.detailState) {
            DetailScreenState.PAYMENT -> Payment(onSaveClick = viewModel::onSaveClick)
            DetailScreenState.ORDER -> Options(onOptionClick = viewModel::onChangeState)
            DetailScreenState.OPTIONS -> Options(onOptionClick = viewModel::onChangeState)
        }
    }

}