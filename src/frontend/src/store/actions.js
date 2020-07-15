import * as api from "@/api";

const actions = {
    LOGIN({commit}, {email, password}){
        return api.auth
        .login(email, password)
        .then(({ root_folder, accessToken }) => commit('LOGIN', {root_folder, accessToken}));
    },
    CHECK_EMAIL(_,{email}){
        return api.checkEmail.check({email})
    },
    SIGNUP(_,{email, password, password2, name, phone, company, department, job}){
        return api.signup
        .signup({email, password, password2, name, phone, company, department, job});
    }
}

export default actions;