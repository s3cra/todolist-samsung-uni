package com.example.module3todo.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.module3todo.navigation.EditTodo
import com.example.module3todo.navigation.NewTodo
import com.example.module3todo.presentation.ui.component.TodoCard
import com.example.module3todo.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavHostController,
    viewModel: TodoViewModel
) {
    val todos by viewModel.items.collectAsState()
    val completedColorEnabled by viewModel.completedColorEnabled.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo List") },
                actions = {
                    Row(
                        modifier = Modifier.padding(end = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text("Цвет завершенных")
                        Switch(
                            checked = completedColorEnabled,
                            onCheckedChange = viewModel::setCompletedColorEnabled,
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(NewTodo) }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(todos, key = { it.id }) { item ->
                val cardModifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(EditTodo(item.id)) }
                TodoCard(
                    todoItem = item,
                    highlightCompleted = completedColorEnabled,
                    onCheck = { viewModel.toggleTodo(item.id) },
                    modifier = cardModifier,
                )
            }
        }
    }
}
