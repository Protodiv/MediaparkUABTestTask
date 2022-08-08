package com.example.mediaparkuabtesttask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.models.Filter
import com.example.domain.models.SearchIn
import com.example.mediaparkuabtesttask.MainViewModel
import com.example.mediaparkuabtesttask.R
import com.example.mediaparkuabtesttask.ui.screens.screensutils.FilterUtils.DateChooser
import com.example.mediaparkuabtesttask.ui.screens.screensutils.ScreensUtils
import com.example.mediaparkuabtesttask.ui.theme.lightGray
import com.example.mediaparkuabtesttask.ui.theme.orange
import com.example.mediaparkuabtesttask.ui.theme.white
import java.time.LocalDate

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val FilterSaver = run {
        val dateFrom = "dateFrom"
        val dateTo = "dateTo"
        val searchIn = "searchIn"
        mapSaver(
            save = {
                mapOf(
                    dateFrom to it.dateFrom,
                    dateTo to it.dateTo,
                    searchIn to it.searchIn,
                )
            },
            restore = {
                Filter(
                    it[dateFrom] as LocalDate?,
                    it[dateTo] as LocalDate,
                    it[searchIn] as SearchIn,
                )
            }
        )
    }

    val filterFormat = remember {
        viewModel.getFilterFormat()
    }

    val filter = rememberSaveable(stateSaver = FilterSaver) {
        mutableStateOf(viewModel.filter)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
    ) {
        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(86.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = orange,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SearchScreen.route)
                    }
                )
                Button(
                    onClick = {
                        viewModel.clearFilter()
                        filter.value = Filter(null, LocalDate.now(),SearchIn.All)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = orange,
                        disabledBackgroundColor = Color.Transparent,
                        disabledContentColor = orange,
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("Clear")
                    Spacer(Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = null
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            ScreensUtils.HeaderText(text = "Filter")
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Date",
                style = MaterialTheme.typography.body2
            )
            Spacer(Modifier.height(20.dp))

            DateChooser(
                "From",
                filterFormat,
                context,
                filter.value.dateFrom
            ) { i, i2, i3 ->
                viewModel.setNewFilter(
                    Filter(LocalDate.of(i, i2 + 1, i3), filter.value.dateTo,filter.value.searchIn)
                )
                filter.value = Filter(LocalDate.of(i, i2 + 1, i3), filter.value.dateTo,filter.value.searchIn)
            }
            Spacer(Modifier.height(26.dp))
            DateChooser(
                "To",
                filterFormat,
                context,
                filter.value.dateTo
            ) { i, i2, i3 ->
                filter.value = Filter(filter.value.dateFrom, LocalDate.of(i, i2 + 1, i3),filter.value.searchIn)
                viewModel.setNewFilter(
                    Filter(filter.value.dateFrom, LocalDate.of(i, i2 + 1, i3),filter.value.searchIn)
                )
            }
            Spacer(Modifier.height(33.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.SearchInScreen.route)
                    }
            ) {
                Text(
                    text = "Search in",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = filter.value.searchIn.title,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(lightGray)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.SearchScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = orange,
                        contentColor = white,
                        disabledBackgroundColor = orange,
                        disabledContentColor = white,
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 1.dp,
                        disabledElevation = 0.dp
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                ) {
                    Text("Apply filter")
                }
            }
        }
    }
}