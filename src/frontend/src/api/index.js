import axios from "axios";
import router from "@/router";

const DOMAIN = "http://14.32.18.239:8080";
const UNAUTHORIZED = 401;
const onUnauthorized = () => {
  router.push(`/login?rPath=${encodeURIComponent(location.pathname)}`);
};

const request = (method, url, data) => {
  return axios({
    method,
    url: DOMAIN + url,
    data,
  })
    .then((res) => {
      console.log(res);
      return res.data;
    })
    .catch((result) => {
      const { status } = result.response;
      if (status === UNAUTHORIZED) return onUnauthorized();
      throw result.response;
    });
};

export const setAuthInHeader = (token) => {
  axios.defaults.headers.common["Authorization"] = token
    ? `Bearer ${token}`
    : null;
};

export const auth = {
  login(email, password) {
    return request("post", "/api/users/login", {
      email,
      password,
    });
  },
};

export const checkEmail = {
  check(email) {
    return request("post", "/api/users/emailCheck", email);
  },
};

export const signup = {
  signup(email, password, password2, name, phone, company, department, job) {
    return request(
      "post",
      "/api/users/signup",
      email,
      password,
      password2,
      name,
      phone,
      company,
      department,
      job
    );
  },
};

export const newFolder = {
  newFolder(data) {
    // console.log(parent);
    // console.log(newFolderName);
    // console.log(userEmail);
    console.log(data.userEmail + ": api");
    console.log(data.parent + ": api");
    console.log(data.newFolderName + ": api");
    const parent = data.parent;
    const newFolderName = data.newFolderName;
    const userEmail = data.userEmail;
    return request("post", "/api/folders/add", {
      parent,
      newFolderName,
      userEmail,
    });
  },
};

export const getFolders = {
  getFolders({ parent }) {
    console.log(parent + ": api / getfolders");
    return request("post", "/api/folders/getfolders", { parent });
  },
};

export const getOnly = {
  getOnly(parent, target) {
    console.log(parent + ": api / getOnly : parent");
    console.log(target + ": api / getOnly : target");
    return request("post", "/api/folders/bytarget", parent, target);
  },
};

export const getImage = {
  getImage({ image_id }) {
    console.log(image_id + " : api / getimages");
    return request("post", "/api/images/getimages", { image_id });
  },
};

export const uploadFile = {
  uploadFile(formData) {
    console.log(formData);
    return request("post", "/api/contents/upload", formData);
  },
};

export const download = {
  download(id) {
    console.log(id);
    return request("post", "/api/contents/download", id);
  },
};

function createInstance() {
  return axios.create({
    baseURL: DOMAIN,
    changeOrigin: true,
  });
}

export const instance = createInstance();