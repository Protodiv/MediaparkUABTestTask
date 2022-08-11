package com.example.mediaparkuabtesttask.ui.screens.screensutils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.mediaparkuabtesttask.ui.theme.*

object SearchScreenUtils {
    val heightOfSearchPanel = 56.dp

    @ExperimentalMaterialApi
    @Composable
    fun SearchScreenBadgeBox(
        row: RowScope,
        badgedState: Int,
        icon: Int,
        onClickAction: () -> Unit
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val colorBg = if (isPressed) orange else gray
        val tintColor = if (isPressed) white else black
        row.apply {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(heightOfSearchPanel)
            ) {
                Box(
                    Modifier
                        .size(heightOfSearchPanel)
                        .clip(CircleShape)
                        .background(
                            colorBg
                        )
                )
                Box(
                    Modifier
                        .align(Alignment.Center)
                ) {
                    if(badgedState > 0){
                        BadgeBox(
                            backgroundColor = lightRed,
                            contentColor = white,
                            badgeContent = {
                                Text(text = badgedState.toString())
                            },
                        ) {
                            Box(
                                Modifier
                                    .align(Alignment.Center)
                                    .size(heightOfSearchPanel - 16.dp)
                            ) {
                                IconButton(
                                    interactionSource = interactionSource,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .width(20.dp)
                                        .height(21.dp),
                                    onClick = {onClickAction.invoke()}
                                ){
                                    Icon(
                                        painter = painterResource(id = icon),
                                        contentDescription = null,
                                        tint = tintColor
                                    )
                                }
                            }
                        }
                    }
                    else{
                        Box(
                            Modifier
                                .align(Alignment.Center)
                                .size(heightOfSearchPanel - 16.dp)
                        ) {
                            IconButton(
                                interactionSource = interactionSource,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .width(20.dp)
                                    .height(21.dp),
                                onClick = {onClickAction.invoke()}
                            ){
                                Icon(
                                    painter = painterResource(id = icon),
                                    contentDescription = null,
                                    tint = tintColor
                                )
                            }
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun SearchHistoryItem(historyItem: String, onClickAction: (String) -> Unit) {
        Column(
            Modifier
                .padding(start = 16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        onClickAction.invoke(historyItem)
                    }
                ),
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = historyItem,
                style = MaterialTheme.typography.body2
            )
            Spacer(Modifier.height(15.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(white)
            )
        }
    }

    @Composable
    fun line(
        color: Color,
        height:Int = 2,
        modifier:Modifier = Modifier,
    ){
        Box(
            Modifier
                .background(color)
                .fillMaxWidth()
                .height(height.dp)
                .then(modifier)
        )
    }

    @Composable
    fun RatioElement(
        text:String,
        selected:Boolean,
        onClickAction: () -> Unit
    ){
        val radialButtonsColors: RadioButtonColors = RadioButtonDefaults.colors(
            selectedColor = orange,
            unselectedColor = sortLineSaperate,
            disabledColor = white
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text, style = MaterialTheme.typography.body2)
            RadioButton(
                selected = selected,
                onClick = { onClickAction.invoke() },
                colors = radialButtonsColors
            )
        }
        line(sortLineSaperate,1, Modifier.padding(start = 16.dp))
    }

    @Composable
    fun PopUpWindow(ratioElements: List<String>, param: (String) -> Boolean, param1: (String) -> Unit, onDismiss:()->Unit) {
        Popup(
            alignment = Alignment.BottomCenter,
            properties = PopupProperties(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            ) {
                Column(
                    modifier = Modifier
                        .background(white)
                        .padding(start = 16.dp)
                ) {
                    ScreensUtils.HeaderText(
                        text = "Sort by",
                        modifier = Modifier.padding(top = 19.dp, bottom = 19.dp)
                    )
                    line(sortLineSaperate,1, Modifier.padding(start = 16.dp))
                    ratioElements.forEach { item ->
                        RatioElement(
                            item,
                            param(item)
                        ){
                            param1(item)
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}