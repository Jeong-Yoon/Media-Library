<template>
  <div id="app">
    <AppHeader />
    <router-view />
    <div v-if="token !== null" class="app-contents">
      <Navigation />
    </div>
  </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader.vue";
import Navigation from "@/components/common/Navigation.vue";
import { mapState } from "vuex";

export default {
  components: {
    AppHeader,
    Navigation,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState({
      token: (state) => state.token,
    }),
  },
};
</script>

<style>
@import "./assets/css/reset.css";
@import "./assets/css/common.css";

template {
  background-color: white;
}
</style>
