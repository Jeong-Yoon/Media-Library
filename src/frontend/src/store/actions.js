import * as api from "@/api";

const actions = {
  LOGIN({ commit }, { email, password }) {
    return api.auth
      .login(email, password)
      .then(({ root_folder, accessToken }) =>
        commit("LOGIN", { root_folder, accessToken })
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
  UPLOAD_FILE(_, formData) {
    return api.uploadFile.uploadFile(formData);
  },
  DOWNLOAD_FILE(_, id) {
    return api.uploadFile.uploadFile(id);
  },
};

export default actions;