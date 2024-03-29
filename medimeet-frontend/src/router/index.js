import {createRouter, createWebHistory} from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import NavBar from '../components/NavBar.vue'

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), 
    routes: [
        {
            path: '/',
            name: 'home',
            component: LoginPage
        },
        // {
        //     path: '/about',
        //     name: 'about',
        //     // route level code-splitting
        //     // this generates a separate chunk (About.[hash].js) for this route
        //     // which is lazy-loaded when the route is visited.
        //     component: () => import('../components/NavBar.vue')
        // },
        {
            path: '/about',
            name: 'about',
            component: NavBar   
        }
    ]
})

export default router