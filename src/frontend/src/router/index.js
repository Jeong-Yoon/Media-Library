import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

Vue.use(VueRouter);

const requireAuth = () => (to, from, next) => {
  const loginPath = `/login?rPath=${encodeURIComponent(to.path)}`;
  if (store.state.token === "") {
    return next("/login");
  }
  //console.log(store.state.token);
  store.getters.isAuth ? next() : next(loginPath);
};

const routes = [
  {
    path: "/main",
    redirect: "/",
    beforeEnter: requireAuth(),
  },
  {
    path: "/",
    redirect: "/ownDocumentBox",
    // component: () => import("@/views/OwnPage.vue"),
    beforeEnter: requireAuth(),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/LoginPage.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/views/SignupPage.vue"),
  },
  {
    path: "/ownDocumentBox",
    name: "ownDocumentBox",
    component: () => import("@/views/OwnPage.vue"),
  },
  {
    path: "/ownDocumentBox/:id",
    name: "ownDocumentBox",
    component: () => import("@/views/OwnPage.vue"),
  },
  {
    path: "/shareDocumentBox",
    name: "shareDocumentBox",
    component: () => import("@/views/SharePage.vue"),
  },
  {
    path: "/albumDocumentBox",
    name: "albumDocumentBox",
    component: () => import("@/views/AlbumPage.vue"),
  },
  {
    path: "/albumDocumentBox/:album_id",
    name: "intoAlbumDocumentBox",
    component: () => import("@/views/AlbumIntoPage.vue"),
  },
  {
    path: "/trashDocumentBox",
    name: "trashDocumentBox",
    component: () => import("@/views/TrashPage.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
