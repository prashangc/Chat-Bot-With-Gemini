package com.example.chatbot.features.chat.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.chatbot.features.chat.data.model.ChatViewModel
import com.example.chatbot.features.chat.presentation.compose.ChatAppBar
import com.example.chatbot.features.chat.presentation.compose.ChatBodyCard
import com.example.chatbot.features.chat.presentation.compose.ChatBottomBar

@Composable
fun ChatScreen(chatViewModel : ChatViewModel) {
    Scaffold (
        topBar = {
            ChatAppBar()
        },
        content = {
                innerPadding -> Column (
                    modifier = Modifier
                        .padding(innerPadding)
                ){
                    ChatBodyCard(chatViewModel = chatViewModel)
                }
            },
        bottomBar = {
                ChatBottomBar(chatViewModel = chatViewModel)
        }
    )
}