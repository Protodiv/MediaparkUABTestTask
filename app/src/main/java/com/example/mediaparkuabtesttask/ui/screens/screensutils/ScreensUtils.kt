package com.example.mediaparkuabtesttask.ui.screens.screensutils

//import coil.compose.AsyncImage
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Article
import com.example.mediaparkuabtesttask.R
import com.example.mediaparkuabtesttask.ui.theme.orange
import com.example.mediaparkuabtesttask.ui.theme.sortLineSaperate
import com.example.mediaparkuabtesttask.ui.theme.white

object ScreensUtils {

    val roundCornerShape = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp)

    @Composable
    fun HeaderWithLogo(
        block: @Composable (() -> Unit)? = null
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, roundCornerShape)
                .zIndex(1f)
                .clip(roundCornerShape)
                .background(white)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(86.dp)
                )
            }
            block?.invoke()
        }
    }

    @Composable
    fun HeaderText(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.h1,
            modifier = modifier,
        )
    }

    @Composable
    fun NewsBox(article: Article, modifier: Modifier = Modifier,onClickAction: (String)->Unit) {
        Box(
            Modifier
                .height(108.dp)
                .fillMaxWidth()
                .padding(start = 14.dp, end = 18.dp)
                .background(white)
                .clickable {
                    onClickAction(article.url)
                }
                .then(modifier)
        ) {
            Row {
                SubcomposeAsyncImage(
                    model = article.image,
                    loading = {
                        CircularProgressIndicator(
                            color = orange,
                            modifier = Modifier.align(Alignment.Center).padding(30.dp)
                        )
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .width(124.dp)
                        .height(108.dp),
                    alignment = Alignment.TopStart,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(start = 19.dp, top = 15.dp, end = 20.dp)
                ) {
                    Text(text = article.title, maxLines = 1, style = MaterialTheme.typography.h2)
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = article.content,
                        maxLines = 2,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }

    @Composable
    fun HeaderAndContent(headerText: String, content: @Composable () -> Unit) {
        Spacer(modifier = Modifier.height(17.dp))
        HeaderText(headerText, Modifier.padding(start = 16.dp))
        Spacer(modifier = Modifier.height(29.dp))
        content.invoke()
    }

    @Composable
    fun ErrorWindow(text:String, onClickAction: () -> Unit){
        Popup(
            alignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                    .clickable {
                        onClickAction()
                    }
            ) {
                Column(
                    modifier = Modifier
                        .background(white)
                        .padding(start = 20.dp)
                ) {
                    HeaderText(
                        text = text,
                    )
                }
            }
        }
    }
}