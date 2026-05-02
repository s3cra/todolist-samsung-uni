package com.example.module3todo.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.module3todo.domain.model.TodoItem

@Composable
fun TodoCard(
    todoItem: TodoItem,
    highlightCompleted: Boolean,
    onCheck: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (todoItem.isCompleted && highlightCompleted) {
                Color(0xFF097500)
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Checkbox(
                checked = todoItem.isCompleted,
                onCheckedChange = { onCheck() },
            )
            Column {
                Text(
                    text = todoItem.title,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (todoItem.isCompleted) TextDecoration.LineThrough else null,
                )
                Text(
                    text = todoItem.description,
                    textDecoration = if (todoItem.isCompleted) TextDecoration.LineThrough else null,
                )
            }
        }
    }
}
