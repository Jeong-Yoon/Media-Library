<template>
  <div id="app">
    <AppHeader />
    <router-view :selectFolderId="selectFolderId"/>
    <div
      v-if="token !== null"
      class="app-contents"
    >
      <Navigation v-on:getFolderId="getFolderId"/>
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
    return {
      selectFolderId:""
    };
  },
  computed: {
    ...mapState({
      token: (state) => state.token,
    }),
  },
  methods:{
    getFolderId(id){
      this.selectFolderId = id;
      console.log(this.selectFolderId , " : app.vue")
      console.log("app vue : ", id)
    }
  }
};
</script>

<style>
@import "./assets/css/reset.css";
@import "./assets/css/common.css";

template {
  background-color: white;
}
</style>