import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: () => import ('../views/Manage'),
        redirect :"/home",//这里斜杠被重定向到home
        children:[
            {
                path: 'home', name: '主页', component: () => import ('../views/Home')
            },
            {
                path: 'user', name: '用户管理', component: () => import ('../views/User')
            },
            {
                path: 'person', name: '个人信息', component: () => import ('../views/Person')
            }
        ]
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path:'/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "about" */ '../views/Login')
    },
    {
        path:'/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "about" */ '../views/Register')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to,from,next)=>{
    localStorage.setItem("currentPathName",to.name)//设置当期并存储路由
    store.commit("setPath")
    next()//放行路由
})


export default router
