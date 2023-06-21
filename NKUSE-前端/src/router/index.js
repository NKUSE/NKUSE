import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
  },
  {
    path: '/online_exam',
    component: Layout,
    children: [
      {
        path: 'online_exam',
        name: 'Online_exam',
        component: () => import('@/views/stu_online_test/index'),
        meta: { title: '在线考试', icon: 'dashboard'}
      }
    ]
  },
  {
    path: 'Github',
    component: Layout,
    children: [
      {
        path: 'https://github.com/orgs/NKUSE/repositories',
        meta: { title: 'Github项目地址', icon: 'link' }
      }
    ]
  }
]
export const asyncRoutes = [
  {
    path: '/admin-register',
    component: Layout,
    meta: {
      roles: ['admin']
    },
    children: [
      {
        path: 'index',
        name: 'admin-register',
        component: () => import('@/views/admin-register/index'),
        meta: { title: '管理员-报名信息管理', icon: 'form' }
      }
    ]
  },

  {
    path: '/admin-userinfo',
    component: Layout, 
    meta: {
      roles: ['admin']
    },
    children: [
      {
        path: 'index',
        name: 'admin-userinfo',
        component: () => import('@/views/admin-userinfo/index'),
        meta: { title: '管理员-用户信息管理', icon: 'form' }
      }
    ]
  },

  {
    path: '/admin-examroominfo',
    component: Layout, 
    meta: {
      roles: ['admin']
    },
    children: [
      {
        path: 'index',
        name: 'admin-examroominfo',
        component: () => import('@/views/admin-examroominfo/index'),
        meta: { title: '管理员-考场信息管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/admin-examinfo',
    component: Layout, 
    meta: {
      roles: ['admin']
    },
    children: [
      {
        path: 'index',
        name: 'admin-examinfoo',
        component: () => import('@/views/admin-examinfo/index'),
        meta: { title: '管理员-考试信息管理', icon: 'form' }
      }
    ]
  },

  {
    path: '/admin-questioninfo',
    component: Layout, 
    meta: {
      roles: ['admin']
    },
    children: [
      {
        path: 'index',
        name: 'admin-questioninfo',
        component: () => import('@/views/admin-questioninfo/index'),
        meta: { title: '管理员-考题信息管理', icon: 'form'}
      }
    ]
  },
  {
    path: '/admin-paperinfo',
    component: Layout, 
    meta: {
      roles: ['admin']
    },
    children: [
      {
        path: 'index',
        name: 'admin-paperinfo',
        component: () => import('@/views/admin-paperinfo/index'),
        meta: { title: '管理员-试卷管理', icon: 'form' }
      }
    ]
  },  

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
