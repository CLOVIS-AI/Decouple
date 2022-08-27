import {viteCommonjs} from '@originjs/vite-plugin-commonjs'
import commonjs from '@rollup/plugin-commonjs'

export default {
  plugins: [
    viteCommonjs(),
    commonjs()
  ],
  root: "./kotlin",
  base: "./",
}
