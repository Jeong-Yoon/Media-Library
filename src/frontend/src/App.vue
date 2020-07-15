<template>
  <div id="app">
    <AppHeader />
    <router-view />
    <div v-if="token !== null" class="app-contents">
      <Navigation />
      <image-viewing />
      <Video-viewing />
    </div>
  </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader.vue";
import Navigation from "@/components/common/Navigation.vue";
import { mapState } from "vuex";
import ImageViewing from "@/components/ImageViewing.vue";
import VideoViewing from "@/components/VideoViewing.vue";

export default {
  components: {
    AppHeader,
    Navigation,
    ImageViewing,
    VideoViewing,
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
