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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
            var tipPercent by remember { mutableStateOf(15f) }

            QuickTripTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    BillAmountInput(billAmount = billAmount, onValueChange = { billAmount = it })

                    TipPercentageSlider(
                        tipPercent = tipPercent,
                        onValueChange = { tipPercent = it }
                    )

                    TipAndTotalDisplay(tipPercent = tipPercent, totalAmount = billAmount)
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

@Composable
fun TipPercentageSlider(tipPercent: Float, onValueChange: (Float) -> Unit) {
    Column {
        Text(text = "Tip: ${tipPercent.toInt()}%", style = MaterialTheme.typography.bodySmall)
        Slider(
            value = tipPercent,
            onValueChange = onValueChange,
            valueRange = 0f..30f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TipAndTotalDisplay(tipPercent: Float, totalAmount: String) {
    val billAmount = totalAmount.toFloatOrNull() ?: 0f
    val tipAmount = billAmount * (tipPercent / 100)
    val totalAfterTip = billAmount + tipAmount

    Column {
        Text(text = "Tip Amount: $${"%.2f".format(tipAmount)}", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Total with Tip: $${"%.2f".format(totalAfterTip)}", style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun BillAmountInputPreview() {
    QuickTripTheme {
        BillAmountInput(billAmount = ""){ }
    }
}