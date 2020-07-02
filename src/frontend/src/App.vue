<template>
  <div id="app">
    <AppHeader />
    <div class="app-contents">
      <Navigation />
      <router-view />
      <own-document-box />
    </div>
  </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader.vue";
import Navigation from "@/components/common/Navigation.vue";
import OwnDocumentBox from "@/components/OwnDocumentBox.vue";

export default {
  components: {
    AppHeader,
    Navigation,
    OwnDocumentBox,
  },
  data() {
    return {};
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
