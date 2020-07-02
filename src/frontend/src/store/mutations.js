import { setAuthInHeader } from '@/api'

const mutations ={
    LOGIN(state, token){
        if(!token) return
        state.token = token;
        localStorage.setItem('token', token);
        setAuthInHeader(token)
    },
    setUserInfo(state, payload){
        state.userInfo = payload;
    },
    LOGOUT(state){
        state.token = null;
        state.userInfo = '';
        delete localStorage.token;
        delete localStorage.vuex;
    }
}

export default mutations;