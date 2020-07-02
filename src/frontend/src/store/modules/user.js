import * as api from "@/api";
import { setAuthInHeader } from '@/api';
// const user = {
//     state : {
//         token: null,
//         userInfo:'',
//     },
    
//     getters : {
//         isAuth(state) {
//             return !!state.token
//         }
//     },
//     actions : {
//         LOGIN({commit}, {email, password}){
//             return api.auth
//             .login(email, password)
//             .then(({ accessToken }) => commit('LOGIN',accessToken));
//         },
//         CHECK_EMAIL(_,{email}){
//             return api.checkEmail.check({email})
//         },
//         SIGNUP(_,{email, password, password2, name, phone, company, department, job}){
//             return api.signup
//             .signup({email, password, password2, name, phone, company, department, job});
//         }
//     },
//     mutations : {
//         LOGIN(state, token){
//             if(!token) return
//             state.token = token;
//             localStorage.setItem('token', token);
//             setAuthInHeader(token)
//         },
//         setUserInfo(state, payload){
//             state.userInfo = payload;
//         }
//     },
// }

// const parseJWT = function(token){
//     try{
//         var base64Url = token.split(".")[1];
//         var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
//         var jsonPayload = decodeURIComponent(
//             atob(base64)
//             .split("")
//             .map(function(c) {
//               return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
//             })
//             .join("")
//         );
//         console.log(JSON.parse(jsonPayload));
//         this.$store.commit("user/setUserInfo", JSON.parse(jsonPayload));
//         console.log(this.$store.state.userinfo.userInfo.name);

//         return JSON.parse(jsonPayload);
//     } catch (err){
//         console.log(err);
//     }
// }

const state = {
    token: null,
    userInfo:"",
}

const getters = {
    isAuth(state) {
        return !!state.token
    }
}

const actions = {
    LOGIN({commit}, {email, password}){
        return api.auth
        .login(email, password)
        .then(({ token }) => commit('LOGIN',token));
    },
    CHECK_EMAIL(_,{email}){
        return api.checkEmail.check({email})
    },
    SIGNUP(_,{email, password, password2, name, phone, company, department, job}){
        return api.signup
        .signup({email, password, password2, name, phone, company, department, job});
    }
}

const mutations = {
    LOGIN(state, token){
        if(!token) return
        state.token = token;
        localStorage.setItem('token', token);
        setAuthInHeader(token)
    },
    setUserInfo(state, payload){
        state.userInfo = payload;
    }
}

// const parseJWT = function(token){
//     try{
//         var base64Url = token.split(".")[1];
//         var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
//         var jsonPayload = decodeURIComponent(
//             atob(base64)
//             .split("")
//             .map(function(c) {
//               return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
//             })
//             .join("")
//         );
//         console.log(JSON.parse(jsonPayload));
//         this.$store.commit("user/setUserInfo", JSON.parse(jsonPayload));
//         console.log(this.$store.state.userinfo.userInfo.name);

//         return JSON.parse(jsonPayload);
//     } catch (err){
//         console.log(err);
//     }
// }

// const { token } = localStorage
// store.commit('LOGIN', token)


// export default user;
export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}