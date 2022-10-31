package opensavvy.ui.demo

import androidx.compose.runtime.*
import opensavvy.ui.core.basic.Button
import opensavvy.ui.core.basic.Text
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.LazyColumn
import opensavvy.ui.core.layout.Row

@Composable
fun User(name: String) = Column {
	var selected by remember { mutableStateOf(false) }

	Row {
		Text(name)
		Text("my.email@opensavvy.dev")
	}

	Row {
		Button({ selected = !selected }) {
			Text(if (selected) "Désélectionner" else "Sélectionner")
		}
	}
}

@Composable
fun UserList() = LazyColumn {
	items(200) {
		User("User #$it")
	}
}
