import Vue from 'vue'
import VueRouter from 'vue-router'

import HomePage from '../components/HomePage.vue'
import WordPage from '../components/word/WordPage'

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/',
            name: 'HomePage',
            component: HomePage
        },
        {
            path: '/word',
            name: 'WordPage',
            component: WordPage
        }
    ]
})