import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],

  server: {
    proxy: {
      "/auth": {
        target: "http://api-gateway:8080",
        changeOrigin: true
      }
    }
  }
})


