<template>
  <transition name="modal" appear>
    <div id="upload" @click.self="$emit('close')">
      <div class="uploadFile">
        <h4>Upload Image File</h4>
        <p>
          Input Image URL and Click the button or Drag and Drop or Attach an
          Image File
        </p>
        <div class="input-group">
          <input
            v-model="filename"
            type="text"
            placeholder="Input Image URL or  Drag & Drop or Select"
            class="form-control"
            @dragover.prevent
            @dragenter.prevent
            @drop.prevent="onDrop"
          />
          <div class="input-group-append">
            <div class="filebox">
              <label for="ex_file">파일선택</label>
              <input
                type="file"
                @change="selectFile()"
                ref="fileTag"
                id="ex_file"
              />
              <!--
              <span class="attach-file" @click="onClickFileS">
                <i class="fa fa-paperclip"></i>
              </span>
              -->
            </div>
            <div>
              <button class="upload-button" @click="onSubmit">
                Upload
              </button>
            </div>
          </div>
          <!--
          <input
            type="file"
            class="file-input"
            accept="image/*"
            ref="fileInput"
            @change="onFileChange"
          />
          -->
        </div>
        <!--
        <div v-show="imageSrc" class="upload-image">
          <img :src="imageSrc" />
        </div>
        -->
      </div>
    </div>
  </transition>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  props: ["intoParent"],
  components: {},
  data() {
    return {
      selectedFile: null,
      filename: "",
      imageSrc: "",
      attachFiles: [],
      fileModal: false,
    };
  },
  computed: {
    ...mapState({
      regUser: "userInfo",
      folder: "root_folder",
    }),
  },
  methods: {
    ...mapActions(["UPLOAD_FILE"]),
    selectFile() {
      //console.log("select File 함수");
      this.attachFiles = this.$refs.fileTag.files;
      //console.log("selectFile << file >> : ", this.$refs.fileTag.files);
    },
    onSubmit() {
      console.log("--------------------upload start------------------");
      if (this.attachFiles.length == 0) {
        alert("파일을 업로드 해주세요.");
      } else {
        let formData = new FormData();
        formData.append("regUser", this.regUser.useremail);
        formData.append("folder", this.$route.params.id);
        if (this.attachFiles.length !== 0) {
          for (var i = 0, file; (file = this.attachFiles[i]); i++) {
            console.log(this.attachFiles[i]);
            formData.append("attachFiles", file);
          }
        }
        this.UPLOAD_FILE(formData)
          .then(() => {
            alert("파일이 업로드 되었습니다.");
            this.$emit("close");
            //this.$router.push("/").catch(() => {});
            //this.$router.replace("/");
            //this.$route.fullPath("/");
          })
          .catch((res) => {
            this.error = res.data;
            alert("파일 업로드에 실패하셨습니다.");
            this.$emit("close");
            //this.$router.push("/").catch(() => {});
          });
      }
      console.log("--------------------upload end------------------");
    },
    closeFileModal() {
      this.fileModal = false;
    },
    onDrop() {
      this.inputImageFile(event.dataTransfer.files);
    },
    onClickFile() {
      this.$refs.fileInput.click();
    },
    onFileChangee() {
      this.inputImageFile(event.target.files);
    },
    inputImageFile(files) {
      if (files.length) {
        let file = files[0];
        if (!/^image\//.test(file.type)) {
          alert("이미지 파일만 등록이 가능합니다");
          return false;
        }
        this.filename = file.name;
        this.preview(file);
      }
    },
    onClickUpload() {
      this.preview(this.filename);
    },
    preview(file) {
      if (typeof file === "string") {
        this.imageSrc = file;
      } else {
        let vm = this;
        let reader = new FileReader();
        reader.onload = () => {
          vm.imageSrc = reader.result;
        };
        reader.readAsDataURL(file);
      }
    },
  },
};
</script>

<style scoped>
#upload {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  z-index: 15;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}
.uploadFile {
  background: white;
  border-radius: 4px;
  overflow: hidden;
}
.form-control {
  width: 600px;
  height: 300px;
  border: 1px solid #d2cdc5;
  text-align: center;
  margin-bottom: 5px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.attach-file {
  background-color: #a49988;
  padding-left: 293px;
  padding-right: 293px;
  padding-top: 5px;
  padding-bottom: 5px;
}
.upload-button {
  width: 600px;
  height: 35px;
  margin-top: 5px;
  border: none;
  background-color: #474346;
  color: white;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}
.file-input {
  display: none;
}
.upload-image {
  padding-top: 1rem;
  width: 200px;
  height: 200px;
}

.filebox label {
  display: inline-block;
  padding: 0.5em 0.75em;
  color: #fff;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #a49988;
  cursor: pointer;
  border: none;
  border-radius: 0.25em;
  transition: background-color 0.2s;
  margin-top: -6px;
  margin-bottom: -5px;
  width: 600px;
  text-align: center;
  font-size: 15px;
}
.filebox label:hover {
  background-color: #d2cdc5;
}
.filebox label:active {
  background-color: #d2cdc5;
}
.filebox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

h4 {
  padding-left: 225px;
  margin-top: 15px;
  margin-bottom: 15px;
}
p {
  padding-bottom: 15px;
  padding-left: 4px;
}
img {
  width: 100%;
  height: 100%;
}
input:focus {
  outline: none;
}
button:focus {
  outline: none;
}
</style>
