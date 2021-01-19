import Vue from 'vue'
import VueRouter from 'vue-router'

import HomePage from '../components/HomePage.vue'

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/',
            name: 'HomePage',
            component: HomePage
        }
    ]
})