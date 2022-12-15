package opensavvy.decouple.demo

import androidx.compose.runtime.*
import opensavvy.decouple.core.basic.Button
import opensavvy.decouple.core.basic.Text
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.LazyColumn
import opensavvy.decouple.core.layout.Row

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
