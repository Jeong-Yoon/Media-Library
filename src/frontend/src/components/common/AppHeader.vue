<template>
  <header>
    <div>
      <router-link to="/">
        <img src="@/assets/image/logo.png" alt="main logo" height="20px" />
      </router-link>
    </div>
    <div class="user">
      <div v-if="username !== undefined">
        <p>{{ username }}님 환영합니다.</p>
        <!-- <li @click="logout"> -->
        <span @click="logout">
          <router-link to="/"> 로그아웃</router-link></span
        >
        <!-- </li> -->
      </div>
      <div v-else>
        <router-link to="/login">
          Login
        </router-link>
        <router-link to="/signup">
          Signup
        </router-link>
      </div>
    </div>
  </header>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  // el: "#user",
  computed: {
    ...mapState({
      username: (state) => state.userInfo.username,
    }),
    // userInfo : function(){
    //   return this.$store.state.userInfo
    // }
  },
  methods: {
    ...mapMutations(["LOGOUT"]),
    mouseover: function() {
      this.class.add("animate__animated animate__pulse");
    },
    mouseleave: function() {
      this.class.remove;
    },
    logout() {
      this.LOGOUT();
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
header {
  position: fixed;
  top: 0;
  width: 100%;
  height: 50px;
  padding: 1rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #a6c4c7;
  z-index: 6;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.25);
  transition: background-color 0.3s ease-in-out;
  font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}
.user a {
  margin-left: 10px;
}
.fixed {
  position: fixed;
  top: 0;
  width: 100%;
}
/*--- LINK ---*/
a {
  color: white;
  text-decoration: none;
  display: inline-block;
}
a:hover {
  color: #a49988;
  -webkit-animation: zoom 0.3s;
  animation: zoom 0.3s;
}
@-webkit-keyframes zoom {
  50% {
    -webkit-transform: scale(1.05);
  }
}
@keyframes zoom {
  50% {
    transform: scale(1.05);
  }
}
a.router-link-exact-active {
  color: #474346;
  font-weight: bold;
}
.user {
  font-weight: bold;
  letter-spacing: 1px;
  text-shadow: 1px 1px 1px gray;
  text-transform: uppercase;
  color: #ffffff;
  text-shadow: 2px 8px 6px rgba(0, 0, 0, 0.2),
    0px -3px 20px rgba(255, 255, 255, 0.4);
}
p {
  display: inline-block;
}
</style>
