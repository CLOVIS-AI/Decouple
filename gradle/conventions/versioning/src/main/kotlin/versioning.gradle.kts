package opensavvy

plugins {
	base
}

val appVersion: String? by project

group = "opensavvy.decouple"
version = appVersion ?: "0.1.0-DEV"
