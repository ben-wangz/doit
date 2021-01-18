import Vue from 'vue'
import VueRouter from 'vue-router'

import Hello from '../components/HelloWorld.vue'

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/',
            name: 'Hello',
            component: Hello
        }
    ]
})