import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "@babel/polyfill";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import PerfectScrollbar from "vue2-perfect-scrollbar";
import "vue2-perfect-scrollbar/dist/vue2-perfect-scrollbar.css";
import KProgress from "k-progress";
import vueButtonEffect from "vue-button-effect";

library.add(fas);
library.add(far);

Vue.component("font-awesome-icon", FontAwesomeIcon);
Vue.config.productionTip = false;
Vue.use(PerfectScrollbar);
Vue.component("k-progress", KProgress);
Vue.use(vueButtonEffect);

new Vue({
  router,
  store,
  vuetify,
  FontAwesomeIcon,
  PerfectScrollbar,
  KProgress,
  vueButtonEffect,
  render: (h) => h(App),
}).$mount("#app");
