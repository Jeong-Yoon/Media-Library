import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'
// import user from './modules/user';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  // modules: {
  //   user : user,
  // },
  plugins:[
    createPersistedState()
  ]
})

const { token } = localStorage
if(token !== null){
  store.commit('LOGIN', token)
}

export default store
