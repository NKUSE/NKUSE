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
]
export const asyncRoutes = [
  {
    path: '/',
    meta: {
      roles: ['stu']
    },
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/stu-dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
  },
  {
    path: '/',
    meta: {
      roles: ['tea']
    },
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/tea-dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
  },
  {
    path: '/',
    meta: {
      roles: ['adm']
    },
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/admin-dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
  },
  {
    path: '/stu_sign_up',
    meta: {
      roles: ['stu']
    },
    component: Layout,
    children: [
      {
        path: '/stu_sign_up',
        name: '在线报名',
        component: () => import('@/views/stu_sign_up/index'),
        meta: { title: '在线报名', icon: 'form' }
      },
      {
        path: '/stu_sign_up/details',
        name: 'details',
        hidden: true,
        component: () => import('@/views/stu_sign_up/details'),
        meta: { title: '详细信息' }
      },
      {
        path: '/stu_sign_up/detailsqq',
        name: 'detailsqq',
        hidden: true,
        component: () => import('@/views/stu_sign_up/detailsqq'),
        meta: { title: '资格信息查询' }
      },
      {
        path: '/stu_sign_up/detailsqc',
        name: 'detailsqc',
        hidden: true,
        component: () => import('@/views/stu_sign_up/detailsqc'),
        meta: { title: '资格信息' }
      },
    ]
  },
  {
    path: '/stu_search',
    meta: {
      roles: ['stu']
    },
    component: Layout,
    children: [
      {
        path: '/stu_search/index',
        name: 'search',
        component: () => import('@/views/stu_search/index'),
        meta: { title: '报考信息查询', icon: 'form' }
      },
      {
        path: '/stu_search/application_enquiry',
        name: 'search',
        hidden: true,
        component: () => import('@/views/stu_search/details'),
        meta: { title: '学生报考信息查询', icon: 'search' }
      }
    ]
  },
  {
    path: '/stu_online_test',
    meta: {
      roles: ['stu']
    },
    component: Layout,
    children: [
      {
        path: '/stu_online_test',
        name: 'index',
        component: () => import('@/views/stu_online_test/index'),
        meta: { title: '在线考试', icon: '考试' }
      },
      {
        path: '/stu_online_test/confirm',
        name: 'Confirm',
        hidden: true,
        component: () => import('@/views/stu_online_test/confirm'),
        meta: { title: '确认考试', icon: 'dashboard', meta: { requiresExamid: true }  }
      },
      {
        path: '/stu_online_test/Testing',
        name: 'Testing',
        hidden: true,
        component: () => import('@/views/stu_online_test/onlinetest'),
        meta: { title: '答题页面', icon: 'dashboard', meta: { requiresExamid: true }   }
      },
      {
        path: '/stu_online_test/success',
        name: 'Success',
        hidden: true,
        component: () => import('@/views/stu_online_test/success'),
        meta: { title: '提交成功', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/stu_score_search',
    meta: {
      roles: ['stu']
    },
    component: Layout,
    children: [
      {
        path: '/stu_score_search',
        name: 'index',
        component: () => import('@/views/stu_score_search/index'),
        meta: { title: '成绩查询', icon: 'marking' }
      },
      {
        path: '/stu_score_search/ScorePage',
        name: 'ScorePage',
        hidden: true,
        component: () => import('@/views/stu_score_search/ScorePage'),
        meta: { title: '成绩信息查询', icon: 'search' }
      }
    ]
  }, 
  {
    path: '/tea_marking',
    component: Layout,
    meta: {
      roles: ['tea'], 
    },
    children: [
      {
        path: '/tea_marking',
        name: 'index',
        component: () => import('@/views/tea_marking/index'),
        meta: { title: '教师阅卷', icon: 'marking' }
      },
      {
        path: '/tea_marking/marking',
        name: 'marking',
        component: () => import('@/views/tea_marking/marking'),
        meta: { title: '阅卷页面', icon: 'marking2' },
        hidden: true
      }
    ]
  },
  {
    path: '/admin-register',
    component: Layout,
    meta: {
      roles: ['adm']
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
      roles: ['adm']
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
      roles: ['adm']
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
      roles: ['adm']
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
      roles: ['adm']
    },
    children: [
      {
        path: 'index',
        name: 'admin-questioninfo',
        component: () => import('@/views/admin-questioninfo/index'),
        meta: { title: '管理员-考题信息管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/admin-paperinfo',
    component: Layout,
    meta: {
      roles: ['adm']
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
  {
    path: '/all-userinfo',
    component: Layout,
    meta: {
      roles: ['adm', 'tea', 'stu']
    },
    children: [
      {
        path: 'index',
        name: 'all-userinfo',
        component: () => import('@/views/all-userinfo/index'),
        meta: { title: '个人信息管理', icon: 'form' }
      }
    ]
  },
  {
    path: 'Github',
    component: Layout,
    meta: {
      roles: ['adm', 'tea', 'stu']
    },
    children: [
      {
        path: 'https://github.com/orgs/NKUSE/repositories',
        meta: { title: 'Github项目地址', icon: 'link' }
      }
    ]
  }
  ,
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
