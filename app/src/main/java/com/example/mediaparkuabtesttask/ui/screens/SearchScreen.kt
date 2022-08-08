package com.example.mediaparkuabtesttask.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mediaparkuabtesttask.MainViewModel
import com.example.mediaparkuabtesttask.R
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils.HeaderAndContent
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils.HeaderWithLogo
import com.example.mediaparkuabtesttask.ui.screens.screensutils.SearchScreenUtils.PopUpWindow
import com.example.mediaparkuabtesttask.ui.screens.screensutils.SearchScreenUtils.SearchHistoryItem
import com.example.mediaparkuabtesttask.ui.screens.screensutils.SearchScreenUtils.SearchScreenBadgeBox
import com.example.mediaparkuabtesttask.ui.screens.screensutils.SearchScreenUtils.heightOfSearchPanel
import com.example.mediaparkuabtesttask.ui.theme.gray

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val filterBadgedState = viewModel.filtersCount
    val sortBadgedState = viewModel.sortCount

    var showSortMenu by remember { mutableStateOf(false) }

    val selectedValue = viewModel.sortedType
    val onChangeState: (String) -> Unit = { viewModel.changeSelectedValue(it) }
    val ratioElements = listOf(
        "Upload date",
        "Relevance"
    )
    val isSelectedItem: (String) -> Boolean = { selectedValue == it }

    val searchHistory = viewModel.searchHistory
    val news = viewModel.news

    var textFieldText by remember {
        mutableStateOf("")
    }

    val stateScroll = rememberScrollState()

    var isSearched by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(gray)
            .fillMaxSize(),
    ) {
        HeaderWithLogo() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, bottom = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextField(
                    value = textFieldText,
                    onValueChange = {
                        textFieldText = it
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            viewModel.search(textFieldText)
                            isSearched = true
                        }
                    ),
                    shape = CircleShape,
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = gray,
                        cursorColor = Color.DarkGray
                    ),
                    textStyle = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .width(220.dp)
                        .height(heightOfSearchPanel)
                )

                SearchScreenBadgeBox(this, filterBadgedState, R.drawable.ic_filter) {
                    navController.navigate(Screen.FilterScreen.route)
                }
                SearchScreenBadgeBox(this, sortBadgedState, R.drawable.ic_sort) {
                    showSortMenu = true
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .zIndex(0f)
                .verticalScroll(stateScroll)
        ) {
            if (isSearched) {
                HeaderAndContent("${news.totalArticles} news") {
                    news.articles.forEach { article ->
                        ScreensUtils.NewsBox(article)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            } else {
                HeaderAndContent("Search History") {
                    searchHistory.forEach { historyString ->
                        historyString.historyItem?.let { value ->
                            SearchHistoryItem(value) {
                                viewModel.search(it)
                                textFieldText = it
                                isSearched = true
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(75.dp))
        }

        if (showSortMenu) {
            PopUpWindow(
                ratioElements,
                { item -> isSelectedItem(item) },
                { item -> onChangeState(item) },
                { showSortMenu = false }
            )
        }

    }
}