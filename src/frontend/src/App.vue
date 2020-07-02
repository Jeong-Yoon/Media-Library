<template>
  <!-- <v-app> -->
  <!-- <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer />

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar> -->

  <!-- <v-content>
    <HelloWorld />
  </v-content>
  </v-app> -->
  <div id="app">
    <AppHeader />
    <router-link to="/login">
      ssdfsd
    </router-link>
    <router-view />
  </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader.vue";
// import HelloWorld from './components/HelloWorld';
// import request from "request";

export default {
  // name: 'App',

  components: {
    AppHeader
    // HelloWorld,
  },

  data: () => ({
    //
  }),
  mounted(){
    // request('http://localhost:8081/api/test', function(error, response, body){
    //   window.console.log('error:', error);
    //   window.console.log('statusCode:', response && response.statusCode);
    //   window.console.log('body:', body);
    // });
  }
};
</script>

<style>
@import "./assets/css/reset.css";
@import "./assets/css/common.css";
</style>
