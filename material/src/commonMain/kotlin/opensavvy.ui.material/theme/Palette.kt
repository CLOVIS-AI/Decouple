package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.ColorStrength
import opensavvy.ui.core.theme.RGB

@Suppress("MemberVisibilityCanBePrivate")
class Palette(
	val primary: TonalPalette,
	val secondary: TonalPalette,
	val tertiary: TonalPalette,
	val error: TonalPalette,
	val neutral: TonalPalette,
	val neutralVariant: TonalPalette,
) {

	//region Generate light and dark schemes

	@Suppress("DuplicatedCode")
	fun toLightTheme() = MaterialColorTheme(
		palette = this,
		primary = ColorStrength(
			accent = primary.i40,
			container = primary.i90,
		),
		secondary = ColorStrength(
			accent = secondary.i40,
			container = secondary.i90,
		),
		tertiary = ColorStrength(
			accent = tertiary.i40,
			container = tertiary.i90,
		),
		error = ColorStrength(
			accent = error.i40,
			container = error.i90,
		),
		background = neutral.i99,
		backgroundVariant = neutralVariant.i99,
		contrast = neutral.i20,
		outline = neutralVariant.i50,
		outlineVariant = neutralVariant.i80,
	)

	@Suppress("DuplicatedCode")
	fun toDarkTheme() = MaterialColorTheme(
		palette = this,
		primary = ColorStrength(
			accent = primary.i80,
			container = primary.i30,
		),
		secondary = ColorStrength(
			accent = secondary.i80,
			container = secondary.i30,
		),
		tertiary = ColorStrength(
			accent = tertiary.i80,
			container = tertiary.i30,
		),
		error = ColorStrength(
			accent = error.i80,
			container = error.i30,
		),
		background = neutral.i10,
		backgroundVariant = neutralVariant.i30,
		contrast = neutral.i90,
		outline = neutralVariant.i60,
		outlineVariant = neutralVariant.i30,
	)

	//endregion

	companion object {
		//region Default palette

		val default = Palette(
			primary = TonalPalette(
				white,
				RGB.fromHex("#FFFBFE"),
				RGB.fromHex("#FDF7FF"),
				RGB.fromHex("#F6EDFF"),
				RGB.fromHex("#EADDFF"),
				RGB.fromHex("#D0BCFF"),
				RGB.fromHex("#B69DF8"),
				RGB.fromHex("#9A82DB"),
				RGB.fromHex("#7F67BE"),
				RGB.fromHex("#6750A4"),
				RGB.fromHex("#5B4397"),
				RGB.fromHex("#4F378B"),
				RGB.fromHex("#432B7E"),
				RGB.fromHex("#381E72"),
				RGB.fromHex("#21005D"),
				black,
			),
			secondary = TonalPalette(
				white,
				RGB.fromHex("#FFFBFF"),
				RGB.fromHex("#FCF8FF"),
				RGB.fromHex("#F3EEFF"),
				RGB.fromHex("#E4DFFF"),
				RGB.fromHex("#C7BFFF"),
				RGB.fromHex("#AAA0FB"),
				RGB.fromHex("#8F86DE"),
				RGB.fromHex("#756CC2"),
				RGB.fromHex("#5C53A7"),
				RGB.fromHex("#50469A"),
				RGB.fromHex("#443A8E"),
				RGB.fromHex("#392E81"),
				RGB.fromHex("#2D2176"),
				RGB.fromHex("#180362"),
				black,
			),
			tertiary = TonalPalette(
				white,
				RGB.fromHex("#FFFBFF"),
				RGB.fromHex("#FFF8F8"),
				RGB.fromHex("#FFECF0"),
				RGB.fromHex("#FFD9E3"),
				RGB.fromHex("#EFB8C8"),
				RGB.fromHex("#D29DAD"),
				RGB.fromHex("#B58392"),
				RGB.fromHex("#996A79"),
				RGB.fromHex("#7E5260"),
				RGB.fromHex("#704654"),
				RGB.fromHex("#633B48"),
				RGB.fromHex("#56303D"),
				RGB.fromHex("#4A2532"),
				RGB.fromHex("#31101D"),
				black,
			),
			error = TonalPalette(
				white,
				RGB.fromHex("#FFFBFF"),
				RGB.fromHex("#FFF8F7"),
				RGB.fromHex("#FFEDEA"),
				RGB.fromHex("#FFDAD6"),
				RGB.fromHex("#FFB4AB"),
				RGB.fromHex("#FF897D"),
				RGB.fromHex("#FF5449"),
				RGB.fromHex("#DE3730"),
				RGB.fromHex("#BA1A1A"),
				RGB.fromHex("#A80710"),
				RGB.fromHex("#93000A"),
				RGB.fromHex("#7E0007"),
				RGB.fromHex("#690005"),
				RGB.fromHex("#410002"),
				black,
			),
			neutral = TonalPalette(
				white,
				RGB.fromHex("#FFFBFF"),
				RGB.fromHex("#FDF8FD"),
				RGB.fromHex("#F4EFF4"),
				RGB.fromHex("#E6E1E6"),
				RGB.fromHex("#CAC5CA"),
				RGB.fromHex("#AEAAAE"),
				RGB.fromHex("#938F94"),
				RGB.fromHex("#76767A"),
				RGB.fromHex("#605D62"),
				RGB.fromHex("#545156"),
				RGB.fromHex("#48464A"),
				RGB.fromHex("#3D3B3E"),
				RGB.fromHex("#313033"),
				RGB.fromHex("#1C1B1E"),
				black,
			),
			neutralVariant = TonalPalette(
				white,
				RGB.fromHex("#FFFBFF"),
				RGB.fromHex("#FDF7FF"),
				RGB.fromHex("#F5EEFA"),
				RGB.fromHex("#E7E0EB"),
				RGB.fromHex("#CAC4CF"),
				RGB.fromHex("#AFA9B4"),
				RGB.fromHex("#948799"),
				RGB.fromHex("#7A757F"),
				RGB.fromHex("#615D66"),
				RGB.fromHex("#54515A"),
				RGB.fromHex("#49454E"),
				RGB.fromHex("#3D3A43"),
				RGB.fromHex("#322F38"),
				RGB.fromHex("#1D1A22"),
				black,
			)
		)

		//endregion
	}
}
