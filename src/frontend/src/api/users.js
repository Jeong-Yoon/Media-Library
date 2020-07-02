// import {instance} from "@/api/index"
// import router from "@/router"

// const UNAUTHORIZED = 401;
// const onUnauthorized = () =>{
//     router.push("/login");
// };

// const request = (method, url, data) => {
//     return axios({
//         method,
//         url : DOMAIN + url,
//         data,
//     })
//     .then((res) => {
//         console.log(res)
//         return res.data})
//     .catch((result) => {
//         const { status } = result.response;
//         if(status === UNAUTHORIZED) return onUnauthorized();
//         throw Error(result);
//     });
// };

// function login(userData){
//     return instance.post('login', userData)
// }

// export const auth = {
//     login(email, password) {
//         return res.request("post", "/users/login", { email, password });
//     },
// };

// export {login};