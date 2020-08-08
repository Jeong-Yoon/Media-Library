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
    //console.log("----------------------email-------", email);
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
  GET_VIDEO_LIST(_, { folderId, videoId }) {
    console.log("video list action : " + folderId + ", " + videoId);
    return api.getVideoList.getVideoList({
      folderId,
      videoId,
    });
  },
  UPLOAD_FILE(_, formData) {
    return api.uploadFile.uploadFile(formData);
    // .then(() => {
    //   const parent = state.root_folder;
    //   dispatch("GET_FOLDERS", { parent });
    // });
  },
  DOWNLOAD_FILE(_, { id }) {
    console.log("action " + { id });
    return api.download.download({ id });
  },
  DELETE_FILE(_, content_id) {
    console.log("DELETE_FILE / action : ", content_id);
    return api.deleteFile.deleteFile(content_id);
  },
  DELETE_FOLDERS(_, folder_ids) {
    console.log("DELETE_FOLDER / action : ", folder_ids);
    return api.deleteFolder.deleteFolder(folder_ids);
  },
  DELETE_ITEMS(_, ids) {
    console.log("DELETE_ITEMS / action : ", ids);
    return api.deleteItems.deleteItems(ids);
  },
  GET_ITEMS(_, { email }) {
    console.log("action / getItems : ", { email });
    return api.getItems.getItems({ email });
  },
  HARD_DELETE_ITEMS(_, { ids, email }) {
    console.log("action / deleteItems : ", { ids, email });
    return api.hardDeleteItems.hardDeleteItems({ ids, email });
  },
  RESTORE_ITEMS(_, ids) {
    console.log("action / restoreItems : ", ids);
    return api.restoreItems.restoreItems(ids);
  },
  GET_SHARE_ITEMS() {
    console.log("get share actions");
    return api.getShareItems.getShareItems();
  },
  SHARE_ITEMS(_, ids){
    console.log("share action : " + ids);
    return api.shareItems.shareItems(ids);
  },
  UN_SHARE_ITEMS(_, ids) {
    console.log("unshared action : " + ids)
    return api.unshareItems.unshareItems(ids);
  },
};

export default actions;
