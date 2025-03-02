package com.example.jettip

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettip.components.InputField
import com.example.jettip.components.RoundedIconButton
import com.example.jettip.ui.theme.JetTipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTipTheme {
                MyApp {
                    Column {
                        TopHeader()
                        MainContent()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TopHeader(perPerson: Double = 0.0) {
    val perPersonFormatted = "%.2f".format(perPerson)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "Total Per Person",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                "$$perPersonFormatted",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Preview
@Composable
fun MainContent() {
    BillForm {
        Log.d("MainActivity", "Bill value: $it")
    }
}

@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    val totalBill = remember { mutableStateOf("") }
    val validState = remember(totalBill.value) {
        totalBill.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            InputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = totalBill,
                label = "Enter Bill",
                leadingIcon = { Text("$") },
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValueChange(totalBill.value)
                    keyboardController?.hide()
                },
            )
            if (!validState) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        "Split",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .wrapContentSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RoundedIconButton(
                            imageVector = Icons.Default.Edit,
                            onClick = { /*TODO*/ },
                        )
                        Text(
                            "0",
                            modifier = Modifier.padding(horizontal = 9.dp),
                        )
                        RoundedIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = { /*TODO*/ },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    JetTipTheme {
        MyApp {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                TopHeader()
                MainContent()
            }
        }
    }
}
