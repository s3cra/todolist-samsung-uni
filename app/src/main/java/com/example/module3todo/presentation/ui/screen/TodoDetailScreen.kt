package com.example.module3todo.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.presentation.viewmodel.TodoViewModel

@Composable
fun TodoDetailScreen(
    navController: NavHostController,
    todoID: Int,
    viewModel: TodoViewModel
) {

    val todos = viewModel.items.collectAsState()
    val todo = todos.value.find { it.id == todoID } ?: return

    Scaffold {
        Column {
            IconButton(onClick = { navController.popBackStack() }, Modifier.padding(it)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(todo.title)
            Text(todo.description)
            when(todo.isCompleted){
                true ->
                    Text("Выполнено", color = Color.Green)
                false ->
                    Text("Не выполнено", color = Color.Red)
            }

        }
    }
}