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
      //console.log(res);
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

export const getCapacity = {
  getCapacity({ email }) {
    //console.log({ email } + " : getcapacity api");
    return request("post", "/api/users/capacity", { email });
  },
};

export const getchild = {
  getchild({parent}){
    return request("get", `/api/folders/getchild/${parent}`);
  }
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
    // return request("post", "/api/folders/getfolders", { parent });
    return request("get", `/api/folders/getfolders/${parent}`);
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

export const getImageList = {
  getImageList({ folderId }) {
    console.log(folderId + " : api/getimagelist");
    return request("post", "/api/images/getimagelist", { folderId });
  },
};

export const uploadFile = {
  uploadFile(formData) {
    console.log(formData);
    return request("post", "/api/contents/upload", formData);
  },
};

export const search = {
  search(keyword){
    console.log(keyword);
    return request("post", "/api/folders/search", keyword);
  }
}

export const download = {
  download({ id }) {
    console.log("api " + id);
    // return request("post", "/api/contents/download", id , {responseType:"blob"})
    return axios.post(
      DOMAIN + "/api/contents/download",
      { id },
      {
        responseType: "blob",
      }
    );
  },
};

// export const getVideo = function() {
//   axios.get('/api/videos/video', {
//     params: {
//       vide
//     }
//   });
// }

// export const getVideo = {
//   getVideo({ videoId }) {
//     return axios.get('/api/videos/video', {params : { videoId : videoId}})
//   }
// }

export const getVideo = {
  getVideo({ videoId }) {
    console.log("api : " + videoId);
    return request("get", `/api/videos/video/${videoId}`);
  },
};

export const getVideoList = {
  getVideoList({ folderId, videoId }) {
    console.log("get video list api : " + folderId + ", " + videoId);
    return request(
      "post",
      "/api/videos/videolist",
      { folderId, videoId }
      // {
      //   params : {
      //     "folderId":folderId,
      //     "videoId" : videoId
      //   }
      // }
    );
  },
};

export const deleteFile = {
  deleteFile(content_id) {
    console.log("api / deleteFile", content_id);
    return request("delete", "/api/contents", content_id);
  },
};

export const deleteFolder = {
  deleteFolder(folder_ids) {
    console.log("api / deleteFolder", folder_ids);
    return request("delete", "/api/folders", folder_ids);
  },
};

export const deleteItems = {
  deleteItems(ids) {
    console.log("api / deleteItems", ids);
    return request("delete", "/api/folders", ids);
  },
};

export const moveFile ={
  moveFile({ folder, file }){
    console.log("api move file : ", folder, file);
    return request("put", "/api/contents/movefolder", { folder, file });
  }
};

export const getItems = {
  getItems({ email }) {
    console.log("api / getItems", { email });
    return request("post", "/api/garbages/garbageList/", { email });
  },
};

export const hardDeleteItems = {
  hardDeleteItems( { ids, email }) {
    console.log("api / delete : " +  ids + " , "  + email );
    return request("post", "/api/garbages/harddelete", { ids, email });
  },
};

export const restoreItems = {
  restoreItems(ids) {
    console.log("api / restore : ", ids);
    return request("post", "/api/garbages/restore", ids);
  },
};

export const getShareItems = {
  getShareItems() {
    console.log("api / share");
    return request("get", "/api/shares/getshareitems");
  },
};

export const shareItems = {
  shareItems(ids){
    console.log("api / share : ", ids);
    return request("put", "/api/shares/share", ids);
  }
};

export const unshareItems = {
  unshareItems(ids){
    console.log("api / unshare : " + ids);
    return request("put", "/api/shares/unshare", ids);
  }
};

// export const getVideo = {
//   getVideo({videoId}){
//     return request("post", "/api/videos/full", {content_id : videoId});
//   }
// }

export const getAlbums = {
  getAlbums({ email }) {
    console.log("api : ", email);
    return request("get", `/api/albums/${email}`);
  },
};

export const doNewAlbum = {
  doNewAlbum(data) {
    const newAlbumName = data.newAlbumName;
    const userEmail = data.userEmail;
    return request("post", "/api/albums/add", { newAlbumName, userEmail });
  },
};

export const deleteAlbum = {
  deleteAlbum(ids) {
    console.log("api : ", ids);
    return request("delete", "/api/albums", ids);
  },
};

export const intoAlbum = {
  intoAlbum({ album_id }) {
    console.log("api : ", album_id);
    return request("get", `/api/albums/intoAlbum/${album_id}`);
  },
};

export const deleteAlbumItems = {
  deleteAlbumItems({ album_id, ids }) {
    console.log("api : ", album_id);
    return request("delete", "/api/albums/deleteitem", { album_id, ids });
  },
};

export const addAlbum = {
  addAlbum({ album_id, ids }) {
    console.log("api : ", album_id);
    console.log("api : ", ids);
    return request("post", "/api/albums/additem", { album_id, ids });
  },
};

function createInstance() {
  return axios.create({
    baseURL: DOMAIN,
    changeOrigin: true,
  });
}

export const instance = createInstance();
