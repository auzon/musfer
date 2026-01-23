import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "./router"
import 'bootswatch/dist/flatly/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

createApp(App)
    .use(router)
    .mount('#app')
