package com.example.module3todo.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import com.example.module3todo.navigation.Details
import com.example.module3todo.presentation.ui.component.TodoCard
import com.example.module3todo.presentation.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(
    navController: NavHostController,
    viewModel: TodoViewModel
) {

    val todos = viewModel.items

    Scaffold {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(todos.value){ item ->
                TodoCard(item, {viewModel.toggleTodo(item.id)}, Modifier.fillMaxWidth().clickable{
                    navController.navigate(Details(item.id))
                }.testTag("todoCard"))
            }
        }
    }
}