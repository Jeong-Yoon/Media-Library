import axios from "axios";
import router from "@/router";

const DOMAIN = "http://localhost:8080";
const UNAUTHORIZED = 401;
const onUnauthorized = () => {
    router.push(`/login?rPath=${encodeURIComponent(location.pathname)}`)
}

const request = (method, url, data) => {
    return axios({
        method,
        url : DOMAIN + url,
        data,
    })
    .then((res) => {
        console.log(res)
        return res.data})
    .catch((result) => {
        const { status } = result.response;
        if(status === UNAUTHORIZED) return onUnauthorized();
        throw result.response;
    });
};

export const setAuthInHeader = token => {
    axios.defaults.headers.common['Authorization'] = token ? `Bearer ${token}` : null;
}

export const auth = {
    login(email, password) {
      return request('post', '/api/users/login', {
        email,
        password
      })
    }
  }

export const checkEmail={
    check(email){
        return request('post','/api/users/emailCheck',
            email
        )
    }
}

export const signup = {
    signup(email, password, password2, name, phone, company, department, job){
        return request('post','/api/users/signup',
            email, password, password2, name, phone, company, department, job
        )
    }
}

function createInstance() {
    return axios.create({
        baseURL: DOMAIN,
        changeOrigin: true,
    });
}

export const instance = createInstance();