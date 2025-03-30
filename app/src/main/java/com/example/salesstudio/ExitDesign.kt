package com.example.salesstudio.com.example.salesstudio

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ExitDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    val pin = remember { mutableStateOf("") }
    val correctPin = "1234"
    val context = LocalContext.current

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Enter PIN to Exit", style = MaterialTheme.typography.headlineSmall)

                OutlinedTextField(
                    value = pin.value,
                    onValueChange = { pin.value = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { onDismiss() }) {
                        Text("Cancel")
                    }

                    Button(onClick = {
                        if (pin.value == correctPin) {
                            onConfirm()
                        } else {
                            Toast.makeText(context, "Incorrect PIN!", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}
