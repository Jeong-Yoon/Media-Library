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
            <div>
              <span class="attach-file" @click="onClickFileS">
                <!--<input v-model="attachFiles" type="file"/>-->
                <i class="fa fa-paperclip"></i>
              </span>
            </div>
            <div>
              <button class="upload-button" @click="onClickUpload">
                Upload
              </button>
            </div>
          </div>
          <input
            type="file"
            class="file-input"
            accept="image/*"
            ref="fileInput"
            @change="onFileChange"
          />
        </div>
        <div v-show="imageSrc" class="upload-image">
          <img :src="imageSrc" />
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      filename: "",
      imageSrc: "",
      attachFiles: [],
    };
  },
  methods: {
    onDrop(event) {
      this.inputImageFile(event.dataTransfer.files);
    },
    onClickFile() {
      this.$refs.fileInput.click();
    },
    onFileChangee(event) {
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
