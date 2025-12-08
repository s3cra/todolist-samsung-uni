package com.example.module3todo.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.navigation.Details
import com.example.module3todo.presentation.viewmodel.TodoViewModel

@Composable
fun TodoCard(todoItem: TodoItem, onCheck: () -> Unit, modifier: Modifier = Modifier){
    Card(modifier = Modifier.then(modifier)) {

        Row(modifier = Modifier.padding(16.dp).height(50.dp)) {
            Checkbox(checked = todoItem.isCompleted, onCheckedChange = {
                onCheck()
            }, modifier = Modifier.testTag("check"))

            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center){
                Text(todoItem.title, fontWeight = FontWeight.Bold)
                Text(todoItem.description)
            }
        }

    }
}

