package com.example.chatbot.utils.textformfield

import android.webkit.ValueCallback
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import com.example.chatbot.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFormField(
    value: String,
    hintText: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        enabled = true,colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(32.dp),
        modifier = modifier,
        label = {
            Text(text = hintText)
        }
    )
}