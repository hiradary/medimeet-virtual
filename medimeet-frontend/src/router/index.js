import {createRouter, createWebHistory} from 'vue-router'
import PatientInterface from '@/views/PatientInterface.vue'

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), 
    routes: [
        {
            path: '/home',
            name: '/home',
            component: PatientInterface
        },
        {
            path: '/',
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
            path: '/DoctorInterface',
            name: 'DoctorInterface',
            component: () => import('../views/DoctorInterface.vue')
        },
        {
            path: '/patient',
            name: 'patient',
            component: () => import('../components/UserInfo.vue')
        }
    ]
})

export default router