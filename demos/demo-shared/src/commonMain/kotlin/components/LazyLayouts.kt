package opensavvy.decouple.demo.components

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.LazyColumn
import opensavvy.decouple.core.layout.Screen

@Composable
fun LazyLayouts() = Screen("Lazy layouts") {

	Text("Lazy layouts permit displaying lists that are too large to be loaded at once, including infinite lists.")

	LazyColumn {
		item("test1") {
			Text("This is a single item. It will always be displayed first.")
		}

		items(5) {
			Text("Here are five items, which are numbered: $it")
		}

		items(listOf("hello", "world", "other")) {
			Text("Here are three items, which have specific values: $it")
		}
	}

}
