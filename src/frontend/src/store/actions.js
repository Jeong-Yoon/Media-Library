import * as api from "@/api";

const actions = {
  LOGIN({ commit }, { email, password }) {
    return api.auth
      .login(email, password)
      .then(({ root_folder, accessToken, total_capacity, use_capacity }) =>
        commit("LOGIN", {
          root_folder,
          accessToken,
          total_capacity,
          use_capacity,
        })
      );
  },
  CHECK_EMAIL(_, { email }) {
    return api.checkEmail.check({ email });
  },
  SIGNUP(
    _,
    { email, password, password2, name, phone, company, department, job }
  ) {
    return api.signup.signup({
      email,
      password,
      password2,
      name,
      phone,
      company,
      department,
      job,
    });
  },
  GET_CAPACITY(_, { email }) {
    return api.getCapacity.getCapacity({ email });
  },
  NEW_FOLDER(_, { parent, newFolderName, userEmail }) {
    console.log(newFolderName + " : actions");
    return api.newFolder.newFolder({
      parent,
      newFolderName,
      userEmail,
    });
  },
  GET_FOLDERS(_, { parent }) {
    console.log(parent + " : actions / getFolders");
    return api.getFolders.getFolders({
      parent,
    });
  },
  GET_ONLY(_, { parent, target }) {
    console.log("actions / GET_ONLY : parent", parent);
    console.log("actions / GET_ONLY : target", target);
    return api.getOnly.getOnly({
      parent,
      target,
    });
  },
  GET_IMAGE(_, { image_id }) {
    console.log(image_id + " : actions / getImages");
    return api.getImage.getImage({
      image_id,
    });
  },
  GET_IMAGELIST(_, { folderId }) {
    console.log();
    return api.getImageList.getImageList({
      folderId,
    });
  },
  GET_VIDEO(_, { videoId }) {
    console.log("actions : " + videoId);
    return api.getVideo.getVideo({
      videoId,
    });
  },
  UPLOAD_FILE(_, formData) {
    return api.uploadFile.uploadFile(formData);
  },
  DOWNLOAD_FILE(_, id) {
    return api.download.download(id);
  },
  DELETE_FILE(_, content_id) {
    console.log("DELETE_FILE / action : ", content_id);
    return api.deleteFile.deleteFile(content_id);
  },
};

export default actions;
