package com.example.chatbot.features.chat.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.chatbot.features.chat.data.model.ChatViewModel
import com.example.chatbot.features.chat.data.model.MessageHistoryModel
import com.razzaghi.compose_loading_dots.LoadingFady
import com.razzaghi.compose_loading_dots.core.rememberDotsLoadingController

@Composable
fun ChatBodyCard(chatViewModel : ChatViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        if(chatViewModel.messageList.isEmpty()){
            EmptyChatCard()
        }else{
            LazyColumn(
                content = {
                    items(
                        chatViewModel.messageList.count(),
                        itemContent = {
                            index ->
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 8.dp, horizontal = 16.dp)
                                ){
                                    MessageList(
                                        message = chatViewModel.messageList[index],
                                        isWackGeminiBotNoob = chatViewModel.messageList[index].role == "wack gemini bot noob"
                                    )
                                }
                        }
                    )
                }
            )

        }
    }
}

@Composable
fun MessageList(
    message : MessageHistoryModel,
    isWackGeminiBotNoob: Boolean
) {
    if(isWackGeminiBotNoob){
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth(),
        ){
            ProfileCard(isWackGeminiBotNoob = true)
            Spacer(modifier = Modifier.width(5.dp))
            TextValueCard(message = message, isWackGeminiBotNoob = true)
        }
    }else{
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TextValueCard(message = message, isWackGeminiBotNoob = false)
            Spacer(modifier = Modifier.width(5.dp))
            ProfileCard(isWackGeminiBotNoob = false)
        }
    }
}

@Composable
fun TextValueCard(
    message : MessageHistoryModel,
    isWackGeminiBotNoob: Boolean
) {

    var topStart: Dp = 16.dp
    var topEnd: Dp = 16.dp
    var bgColor: Color = MaterialTheme.colorScheme.primary
    var textColor: Color = Color.White

    if(isWackGeminiBotNoob) {
        topStart = 0.dp
        bgColor = Color.LightGray
        textColor = Color.Black
    }else{
        topEnd = 0.dp
    }
    Box (

        modifier = Modifier
            .background(
                color = bgColor,
                shape = RoundedCornerShape(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = 16.dp,
                    bottomStart = 16.dp
                ),
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ){
        if(message.loading){
            val rememberDotsLoadingFadyController = rememberDotsLoadingController()
            LoadingFady(
                controller = rememberDotsLoadingFadyController,
                dotsColor = MaterialTheme.colorScheme.primary,
                dotsSize = 12.dp,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 12.dp),
            )
        }else{
            Text(
                text = message.input,
                color = textColor
            )
        }
    }
}

@Composable
fun ProfileCard(isWackGeminiBotNoob: Boolean) {

    var textValue: String = "U"
    var color: Color = MaterialTheme.colorScheme.primary
    var textColor: Color = Color.White

    if(isWackGeminiBotNoob) {
        textValue = "B"
        color = Color.LightGray
        textColor = Color.Black
    }

    Card (
        modifier = Modifier
            .clip(CircleShape)
    ){
        Text(
            text = textValue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(color)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            color = textColor
        )
    }
}


@Composable
fun EmptyChatCard() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
        ){
        Icon(
            imageVector = Icons.Default.Face,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(50.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Ask me anything you noob !!!")
    }
}