package com.example.chatbot.features.chat.data.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.utils.constants.Constants
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel(){

    val messageList by lazy {
        mutableStateListOf<MessageHistoryModel>()
    }

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = Constants.geminiApiKey
    )

    fun sendMessage(question: String){
        viewModelScope.launch {
            val chat = generativeModel.startChat()
            messageList.add(
                MessageHistoryModel(
                    input = question,
                    role = "user",
                    loading = false,
                )
            )
            messageList.add(
                MessageHistoryModel(
                    input = "",
                    role = "wack gemini bot noob",
                    loading = true,
                )
            )
            val response = chat.sendMessage(question)
            messageList.removeAt(messageList.size -1)
            messageList.add(
                MessageHistoryModel(
                    input = response.text.toString(),
                    role = "wack gemini bot noob",
                    loading = false,
                )
            )
        }
    }

}
