package com.example.module3todo

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.module3todo.data.local.TodoJsonDataSource
import com.example.module3todo.data.repository.TodoRepositoryImpl
import com.example.module3todo.domain.usecase.GetTodosUseCase
import com.example.module3todo.navigation.Details
import com.example.module3todo.navigation.TodoList
import com.example.module3todo.presentation.ui.screen.TodoDetailScreen
import com.example.module3todo.presentation.ui.screen.TodoListScreen
import com.example.module3todo.presentation.viewmodel.TodoViewModel
import com.example.module3todo.presentation.viewmodel.TodoViewModelFactory
import com.example.module3todo.ui.theme.Module3TodoTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun shows3Tasks(){
        val vm = TodoViewModel(
            GetTodosUseCase(
                TodoRepositoryImpl(
                    TodoJsonDataSource(InstrumentationRegistry.getInstrumentation().context)
                )
            )
        )

        composeTestRule.setContent {
            TodoListScreen(rememberNavController(),
                vm
            )
        }

        composeTestRule.onAllNodesWithTag("todoCard").assertCountEquals(3)
    }

    @Test
    fun checkBoxWorks(){
        val vm = TodoViewModel(
            GetTodosUseCase(
                TodoRepositoryImpl(
                    TodoJsonDataSource(InstrumentationRegistry.getInstrumentation().context)
                )
            )
        )

        composeTestRule.setContent {
            TodoListScreen(rememberNavController(),
                vm
            )
        }

        composeTestRule.onAllNodesWithTag("check")[0].performClick()
        composeTestRule.onAllNodesWithTag("check")[0].assertIsOn()
    }

    @Test
    fun navigationWorks(){
        val vm = TodoViewModel(
            GetTodosUseCase(
                TodoRepositoryImpl(
                    TodoJsonDataSource(InstrumentationRegistry.getInstrumentation().context)
                )
            )
        )

        composeTestRule.setContent {
            Module3TodoTheme {
                val controller = rememberNavController()
                val viewModel: TodoViewModel = vm

                NavHost(navController = controller, startDestination = TodoList){
                    composable<TodoList>{
                        TodoListScreen(controller, viewModel)
                    }
                    composable<Details>{
                        TodoDetailScreen(controller, it.arguments?.getInt("todoID") ?: 0, viewModel)
                    }

                }
            }
        }

        composeTestRule.onAllNodesWithTag("todoCard")[0].performClick()
        composeTestRule.onAllNodesWithTag("back")[0].performClick()
        composeTestRule.onAllNodesWithTag("todoCard").assertCountEquals(3)
    }

}