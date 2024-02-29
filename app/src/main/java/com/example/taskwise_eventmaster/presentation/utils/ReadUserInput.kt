package com.example.taskwise_eventmaster.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object InputTypeLambdaHandling {
    val intHandling: (String) -> Int =
        { str ->
            str.toIntOrNull() ?: -1
        }

    val stringHandling: (String) -> (String) =
        { str ->
            str
        }
}

@Composable
fun <T> readValueField(
    numberOfLines: Int,
    fieldName: String,
    valueConverter: (String) -> T
): T {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Enter $fieldName:") },
        maxLines = numberOfLines,
        modifier = Modifier.padding(20.dp)
    )

    return valueConverter(value)
}
