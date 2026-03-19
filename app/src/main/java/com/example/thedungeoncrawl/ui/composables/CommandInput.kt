package com.example.thedungeoncrawl.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.DungeonBlack
import com.example.thedungeoncrawl.ui.theme.DungeonDarkGrey
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

@Composable
fun CommandInput(
    onCommandSent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            placeholder = { Text("Enter command...") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (input.isNotEmpty()) {
                        onCommandSent(input)
                        input = ""
                    }
                }
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = DungeonDarkGrey,
                disabledContainerColor = DungeonBlack
            ),
            border = null,
            onClick = {
                if (input.isNotEmpty()) {
                    onCommandSent(input)
                    input = ""
                }
            },
            modifier = Modifier
                .height(48.dp)
                .wrapContentWidth()
                .retroBorder()
        ) {
            Text("Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommandInputPreview() {
    TheDungeonCrawlTheme {
        CommandInput(onCommandSent = {})
    }
}