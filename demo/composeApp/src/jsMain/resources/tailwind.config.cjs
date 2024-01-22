// noinspection JSFileReferences IDEA doesn't know the folder exists
const material = require('./imported/tailwind-material3.config.cjs')

module.exports = {
	darkMode: 'class',
	content: [
		"./**/*.{html,js}",
	],
	theme: {
		extend: {
			...material.theme.extend,
		},
	},
	variants: {
		extend: {},
	},
	plugins: [],
}
