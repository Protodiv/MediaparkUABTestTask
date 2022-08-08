package com.example.mediaparkuabtesttask.ui.screens.screensutils

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mediaparkuabtesttask.R
import com.example.mediaparkuabtesttask.ui.theme.orange
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object FilterUtils {
    @Composable
    fun DateChooser(
        direction:String,
        dateFormat:String,
        context: Context,
        date: LocalDate?,
        onClickAction: (Int, Int, Int) -> Unit
    ){
        Text(
            text = direction,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    DatePickerDialog(
                        context,
                        {   _, i, i2, i3 ->
                            onClickAction.invoke(i, i2, i3)
                        },
                        date?.year ?: 2022,
                        date?.monthValue ?: 1,
                        date?.dayOfMonth ?: 1
                    ).show()
            }
        ){
            Text(
                text = date?.format(DateTimeFormatter.ofPattern(dateFormat)) ?: "yyyy/mm/dd",
                style = MaterialTheme.typography.body2.takeIf { date != null } ?: MaterialTheme.typography.subtitle2
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                tint = orange
            )
        }
        Spacer(Modifier.height(14.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(orange)
        )
    }
}