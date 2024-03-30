import {createRouter, createWebHistory} from 'vue-router'
import PatientInterface from '@/views/PatientInterface.vue'

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), 
    routes: [
        {
            path: '/',
            name: '/home',
            component: PatientInterface
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/LoginPage.vue')
        },
        {
            path: '/registration',
            name: 'registration',
            component: () => import('../views/RegistrationPage.vue')
        },
        {
            path: '/edit/:id',
            name: 'edit',
            component: () => import('../views/UpdateUser.vue')
        },
        {
            path: '/about',
            name: 'about',
            component: () => import('../views/DoctorInterface.vue')
        }
    ]
})

export default router