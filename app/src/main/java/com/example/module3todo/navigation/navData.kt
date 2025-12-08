package com.example.module3todo.navigation

import com.example.module3todo.domain.model.TodoItem
import kotlinx.serialization.Serializable

@Serializable
object TodoList

@Serializable
data class Details(val todoID: Int)