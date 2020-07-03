import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "@babel/polyfill";
import PerfectScrollbar from "vue2-perfect-scrollbar";
import "vue2-perfect-scrollbar/dist/vue2-perfect-scrollbar.css";
import KProgress from "k-progress";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/js/all.js";
import VueCookie from "vue-cookie";

Vue.config.productionTip = false;
Vue.use(PerfectScrollbar);
Vue.use(VueCookie);
Vue.component("k-progress", KProgress);

new Vue({
  router,
  store,
  vuetify,
  PerfectScrollbar,
  KProgress,
  VueCookie,
  render: (h) => h(App),
}).$mount("#app");