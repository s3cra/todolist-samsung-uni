package com.example.module3todo.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.module3todo.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditorScreen(
    navController: NavHostController,
    todoId: Int?,
    viewModel: TodoViewModel,
) {
    val todo = viewModel.findTodo(todoId)
    var title by remember(todoId, todo?.title) { mutableStateOf(todo?.title.orEmpty()) }
    var description by remember(todoId, todo?.description) { mutableStateOf(todo?.description.orEmpty()) }
    var isCompleted by remember(todoId, todo?.isCompleted) { mutableStateOf(todo?.isCompleted ?: false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (todoId == null) "Новая задача" else "Редактировать задачу") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("Назад")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            viewModel.saveTodo(todoId, title, description, isCompleted)
                            navController.popBackStack()
                        },
                        enabled = title.isNotBlank(),
                    ) {
                        Text("Сохранить")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Название") },
                singleLine = true,
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Описание") },
            )
            Button(onClick = { isCompleted = !isCompleted }) {
                Text(if (isCompleted) "Выполнено" else "Не выполнено")
            }

            if (todoId != null) {
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        viewModel.deleteTodo(todoId)
                        navController.popBackStack()
                    }
                ) {
                    Text("Удалить")
                }
            }
        }
    }
}
