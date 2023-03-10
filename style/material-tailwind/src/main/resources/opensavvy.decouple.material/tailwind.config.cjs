module.exports = {
    content: ["./kotlin/**/*.{html,js}"],
    theme: {
        extend: {
            fontFamily: {
                'roboto': ['Roboto', 'sans-serif'],
            },
            colors: {
                materialColor1: 'rgb(var(--material-color-1) / <alpha-value>)',
                materialColor2: 'rgb(var(--material-color-2) / <alpha-value>)',
                materialColor3: 'rgb(var(--material-color-3) / <alpha-value>)',
                materialColor4: 'rgb(var(--material-color-4) / <alpha-value>)',
                materialColor5: 'rgb(var(--material-color-5) / <alpha-value>)',
                materialColor6: 'rgb(var(--material-color-6) / <alpha-value>)',
                materialColor7: 'rgb(var(--material-color-7) / <alpha-value>)',
            },
            opacity: {
                'normal': '0.05',
                'hover': '0.08',
                'focus': '0.12',
                'disabled': '0.38',
                'disabledBg': '0.12',
                'elevationShadow1': '0.15',
                'elevationShadow2': '0.30'
            },
            boxShadow: {
                'elevation1': '0px 1px 3px 1px rgb(0 0 0 / 0.15), 0px 1px 2px 0px rgb(0 0 0 / 0.3)',
                'elevation2': '0px 2px 6px 2px rgb(0 0 0 / 0.15), 0px 1px 2px 0px rgb(0 0 0 / 0.3)',
            }
        },
    },
    plugins: [],
}
