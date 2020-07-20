import { setAuthInHeader } from "@/api";

const mutations = {
  LOGIN(state, data) {
    if (!data) return;
    if (!data.accessToken) return;
    state.token = data.accessToken;
    state.root_folder = data.root_folder;
    console.log("mutation root_folder :" + data.root_folder);
    localStorage.setItem("token", data.accessToken);
    setAuthInHeader(data.accessToken);
  },
  setUserInfo(state, payload) {
    state.userInfo = payload;
  },
  LOGOUT(state) {
    state.token = null;
    state.userInfo = "";
    state.root_folder = "";
    delete localStorage.token;
    delete localStorage.vuex;
  },
};

export default mutations;