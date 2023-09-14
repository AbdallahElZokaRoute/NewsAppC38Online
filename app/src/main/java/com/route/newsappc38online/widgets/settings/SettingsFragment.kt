package com.example.newsapp.widgets.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
const val SETTINGS_ROUTE = "settings"
@Composable
fun SettingsContent() {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Language",
            style = TextStyle(
                color = Color(0xFF303030),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        ExposedDropDownMenu()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ExposedDropDownMenu() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val languages = arrayOf("Arabic", "English")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(languages[0]) }
    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF39A552),
                    unfocusedTrailingIconColor = Color(0xFF39A552),
                    unfocusedIndicatorColor = Color(0xFF39A552),
                    focusedTrailingIconColor = Color(0xFF39A552),
                    focusedIndicatorColor = Color(0xFF39A552)
                ), modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(text = item)
                        },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
            keyboardController?.hide()
        }
    }
}