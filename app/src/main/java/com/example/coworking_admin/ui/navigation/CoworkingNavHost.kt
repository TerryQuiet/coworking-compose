package com.example.coworking_admin.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.coworking_admin.MainViewModel
import com.example.coworking_admin.ui.MainDestinations.DETAILS_ROUTE
import com.example.coworking_admin.ui.navigation.EScreen
import com.example.coworking_admin.ui.screens.details.AccountDetailScreen
import com.example.coworking_admin.ui.screens.details.DetailViewModel
import com.example.coworking_admin.ui.screens.overview.OverviewScreen
import com.example.coworking_admin.ui.screens.overview.OverviewViewModel

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details"
}

@Composable
fun CoworkingNavHost(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = EScreen.OverviewScreen.route,
        modifier = modifier
    ) {
        composable(EScreen.OverviewScreen.route) {
            val overviewViewModel = hiltViewModel<OverviewViewModel>()
            overviewViewModel.userList = mainViewModel.userList
            OverviewScreen(
                viewModel = overviewViewModel,
                onAccountClick = actions.navigateToDetails
            )
        }
        composable(
            route = "${EScreen.AccountDetailScreen.route}/{$DETAILS_ROUTE}",
            arguments = listOf(
                navArgument(DETAILS_ROUTE) { type = NavType.IntType }
            )
        ) {
            val exampleViewModel = hiltViewModel<DetailViewModel>()
            if (exampleViewModel.currentAccount.id == 0) {
                val id = it.arguments?.getInt(DETAILS_ROUTE)
                id?.let { id ->
                    val account = mainViewModel.getAccountById(id)
                    exampleViewModel.setAccount(account)
                }
            }
            AccountDetailScreen(exampleViewModel)
        }
    }
}


class MainActions(navController: NavHostController) {
    val navigateToDetails: (Int) -> Unit = { id ->
        navController.navigate("${EScreen.AccountDetailScreen.route}/$id")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}