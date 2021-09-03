package com.example.coworking_admin.ui.screens.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coworking_admin.ui.components.AccountPicture



@Composable
fun OverviewScreen(
    onAccountClick: (Int) -> Unit = {},
    viewModel: OverviewViewModel = viewModel()
) {
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            items(viewModel.userList.size) {
                AccountPicture(
                    modifier = Modifier.padding(8.dp).clickable { onAccountClick(viewModel.userList[it].id) },
                    name = viewModel.userList[it].name,
                    pictureUrl = viewModel.userList[it].pictureUri
                )
            }
        }

}