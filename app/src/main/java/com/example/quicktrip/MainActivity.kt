package com.example.quicktrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicktrip.ui.theme.QuickTripTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var billAmount by remember { mutableStateOf("") }

            QuickTripTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    BillAmountInput(billAmount = billAmount, onValueChange = { billAmount = it })
                }
            }
        }
    }
}

@Composable
fun BillAmountInput(billAmount: String, onValueChange: (String) -> Unit) {
    TextField(
        value = billAmount,
        onValueChange = onValueChange,
        label = { Text("Enter bill amount:") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun BillAmountInputPreview() {
    QuickTripTheme {
        BillAmountInput(billAmount = ""){ }
    }
}