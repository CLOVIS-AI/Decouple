package opensavvy.decouple.material3.html.components.layouts

import opensavvy.decouple.components.layout.Alignment
import opensavvy.decouple.components.layout.Arrangement

internal val Arrangement.css: String
	get() = when (this) {
		Arrangement.Stretch -> "justify-stretch"
		Arrangement.Start -> "justify-start"
		Arrangement.Center -> "justify-center"
		Arrangement.End -> "justify-end"
		Arrangement.SpaceBetween -> "justify-between"
		Arrangement.SpaceAround -> "justify-around"
		Arrangement.SpaceEvenly -> "justify-evenly"
	}

internal val Alignment.css: String
	get() = when (this) {
		Alignment.Stretch -> "items-stretch"
		Alignment.Start -> "items-start"
		Alignment.Center -> "items-center"
		Alignment.End -> "items-end"
	}
