package com.example.module3todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module3todo.navigation.EditTodo
import com.example.module3todo.navigation.NewTodo
import com.example.module3todo.navigation.TodoList
import com.example.module3todo.presentation.ui.screen.TodoEditorScreen
import com.example.module3todo.presentation.ui.screen.TodoListScreen
import com.example.module3todo.presentation.viewmodel.TodoViewModel
import com.example.module3todo.presentation.viewmodel.TodoViewModelFactory
import com.example.module3todo.ui.theme.Module3TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Module3TodoTheme {
                val controller = rememberNavController()
                val viewModel: TodoViewModel = viewModel(
                    factory = TodoViewModelFactory(applicationContext)
                )

                NavHost(navController = controller, startDestination = TodoList){
                    composable<TodoList>{
                        TodoListScreen(controller, viewModel)
                    }
                    composable<NewTodo> {
                        TodoEditorScreen(controller, null, viewModel)
                    }
                    composable<EditTodo>{
                        TodoEditorScreen(controller, it.arguments?.getInt("todoId"), viewModel)
                    }
                }
            }
        }
    }
}
