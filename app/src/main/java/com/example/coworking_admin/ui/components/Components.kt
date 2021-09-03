package com.example.coworking_admin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.coworking_admin.ui.screens.details.DetailScreenState

@Composable
fun AccountPicture(
    modifier: Modifier = Modifier,
    pictureUrl: String,
    name: String,
    size: Int = 128,
) {
    Card(
        modifier.size(size.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 8.dp
    ) {
        Box {
            Image(
                painter = rememberImagePainter(
                    data = pictureUrl,
                ),
                contentDescription = null,
                Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = size.toFloat()
                        )
                    )
            )
            Text(
                text = name,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                fontSize = 16.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
fun TopAccountInfo(
    photoSlot: @Composable () -> Unit,
    infoSlot: @Composable () -> Unit,
) {
    Row(Modifier.padding(8.dp)) {
        photoSlot()
        infoSlot()
    }
}

@Composable
fun InfoSlot(
    debt: String,
    someInfo: String
) {
    Column {
        Text(text = "dept is: $debt")
        Text(text = someInfo)
    }
}

@Composable
fun Options(
    onOptionClick: (DetailScreenState) -> Unit,
) {
    Column {
        Text(text = "Choose option")
        TextButton(onClick = { onOptionClick(DetailScreenState.ORDER) }) {
            Text(text = "Order")
        }
        TextButton(onClick = { onOptionClick(DetailScreenState.PAYMENT) }) {
            Text(text = "Payment")
        }
    }
}

@Composable
fun Payment(
    onSaveClick: (String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    Column {
        TextField(
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { text = it },
            label = { Text("Label") },
            singleLine = true

        )
        val radioOptions = listOf("cash", "card", "beer")
        val (selectedOption, onOptionSelected)
                = remember { mutableStateOf("") }
        Column(Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        val checkedState = remember { mutableStateOf(false) }
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
        Button(onClick = {
            onSaveClick(text)
        }) {
            Text("Save")
        }
    }
}


@Preview
@Composable
fun Op() {
    Options(onOptionClick = {})
}


@Preview
@Composable
fun PicPrev() {
    AccountPicture(pictureUrl = "https://i.pravatar.cc/150?u=pic", name = "влад доценко")
}