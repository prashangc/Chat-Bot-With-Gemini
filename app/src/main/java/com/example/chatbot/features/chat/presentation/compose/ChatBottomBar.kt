package com.example.chatbot.features.chat.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chatbot.features.chat.data.model.ChatViewModel
import com.example.chatbot.utils.textformfield.CustomTextFormField

@Composable
fun ChatBottomBar(chatViewModel : ChatViewModel) {
    var textInput by remember {
        mutableStateOf(value = "")
    }
    BottomAppBar(
        content = {
            Row (
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                CustomTextFormField(
                    value = textInput,
                    hintText = "Type your message...",
                    onValueChange = {
                        textInput = it
                    },
                    modifier = Modifier
                        .weight(2f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                SendButton(
                    onTap = {
                        chatViewModel.sendMessage(question = textInput)
                        textInput = ""
                    }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary,
    )
}

@Composable
fun SendButton(
    onTap: () -> Unit
) {
    Card (
        modifier = Modifier
            .clip(CircleShape),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onTap
    ){
        Icon(
            imageVector =  Icons.AutoMirrored.Filled.Send,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(12.dp)
        )
    }
}