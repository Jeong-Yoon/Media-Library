<template>
  <content>
    <div class="content">
      <div class="top-content">
        <div class="agree">
          <input
            type="checkbox"
            id="a1"
            name="전체동의"
            @click="selectAll"
            v-model="allSelected"
          />
          <label for="a1"></label>
        </div>
        <div v-if="this.ids.length > 0">
          <button class="download" @click="download">받기</button>
          <button class="share">공유</button>
          <button class="album">앨범</button>
          <button class="delete">삭제</button>
        </div>
        <div v-else>
          <button class="b1" @click="openModal">새폴더</button>
          <button class="b2">폴더</button>
          <button class="b3">사진</button>
          <button class="b4">동영상</button>
          <NewFolderModal @close="closeModal" v-if="modal">
            <!-- default 슬롯 콘텐츠 -->
            <p>폴더 이름을 입력해주세요.</p>
            <div><input class="input" v-model="newFolderName" /></div>
            <!-- /default -->
            <!-- footer 슬롯 콘텐츠 -->
            <template slot="footer">
              <button class="button" @click="doNewFolder">생성</button>
            </template>
            <!-- /footer -->
          </NewFolderModal>
        </div>
        <form class="search">
          <select id="key" name="keyword" class="key">
            <option value="file_origin_name">파일명</option>
            <option value="contents_atribute">확장자</option>
            <option value="contents_reg_date">등록일</option>
          </select>
          <input
            type="text"
            name="search"
            placeholder="Search.."
            class="value"
          />
          <button type="submit" class="submit">
            <img
              src="@/assets/image/search.png"
              alt="search logo"
              height="15px"
              class="search-icon"
            />
          </button>
        </form>
      </div>
      <hr class="top-hr" />

      <div class="bottom-content">
        <div>
          <ul class="table">
            <li
              v-for="folder in this.folderList"
              v-bind:key="folder.folder_id"
              @change="checkbox(folder.folder_id)"
              class="li"
            >
              <div class="agree2">
                <input
                  id="a2"
                  type="checkbox"
                  v-model="ids"
                  @click="select(folder.folder_id)"
                  :value="folder.folder_id"
                />
                <label for="a2"></label>
              </div>
              <img
                src="@/assets/image/folder.png"
                width="130"
                height="130"
                style="opacity: 1; transition: opacity 0.2s ease 0s;"
              />
              <div class="info">
                <span class="title">{{ folder.folder_name }}</span>
              </div>
            </li>
          </ul>
        </div>
        <p>selected ids : {{ ids }}</p>
        <!-- <ul class="list_thumb">
          <li class="li" title="2020">
            <label for>
              <div class="thumb">
                <span class="folder">
                  <img
                    src="@/assets/image/folder.png"
                    width="150"
                    height="150"
                    style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  />
                </span>
              </div>
              <div class="info">
                <span class="title">2020</span>
              </div>
            </label>
          </li>
        </ul> -->
      </div>
    </div>

    <quick-menu
      :menu-count="count"
      :icon-class="icons"
      :menu-url-list="list"
      :position="position"
      :background-color="backgroundColor"
    />
  </content>
</template>

<script>
import quickMenu from "vue-quick-menu";
import NewFolderModal from "./NewFolderModal";
import { mapActions, mapState } from "vuex";

export default {
  components: {
    quickMenu,
    NewFolderModal,
  },
  data() {
    return {
      count: 2,
      icons: ["fa fa-file", "fa fa-folder"],
      list: [
        { isLink: true, url: "/uploadFile" },
        { isLink: true, url: "/uploadFolder" },
      ],
      position: "bottom-right",
      backgroundColor: "#474346",
      modal: false,
      newFolderName: "",
      folderList: [],
      allSelected: true,
      ids: [],
    };
  },
  created() {
    this.getFolders();
  },
  computed: {
    ...mapState({
      userInfo: "userInfo",
      parent: "root_folder",
    }),
  },
  methods: {
    ...mapActions(["NEW_FOLDER", "GET_FOLDERS"]),
    async download() {},
    getFolders() {
      this.GET_FOLDERS({ parent: this.parent }).then((list) => {
        console.log("ownpage list : " + list[0]);
        this.folderList = list;
        console.log("ownpage folderlist : " + this.folderList);
      });
    },
    selectAll: function() {
      this.ids = [];
      if (!this.allSelected) {
        for (this.folder in this.folderList) {
          this.ids.push(this.folderList[this.folder].folder_id);
        }
      }
    },
    checkbox(folder_id) {
      if (this.ids.indexOf(folder_id) == -1) {
        this.ids.push(folder_id);
      } else {
        this.ids.splice(this.ids.indexOf(folder_id), 1);
      }
    },
    select: function(folder_id) {
      this.allSelected = false;
      this.ids.push(folder_id);
    },
    openModal() {
      this.modal = true;
    },
    closeModal() {
      this.modal = false;
    },
    doNewFolder() {
      if (this.newFolderName.length < 0) {
        alert("폴더명을 입력해주세요.");
      } else {
        console.log(this.newFolderName + " : newFolderName");
        this.NEW_FOLDER({
          parent: this.parent,
          newFolderName: this.newFolderName,
          userEmail: this.userInfo.useremail,
        })
          .then(() => {
            alert("폴더가 생성되었습니다.");
            this.newFolderName = "";
            this.closeModal();
            this.getFolders();
          })
          .catch((error) => {
            alert("폴더 생성에 실패했습니다.");
            this.error = error.data.error;
            this.newFolderName = "";
            this.closeModal();
            this.getFolders();
          });
      }
    },
  },
};
</script>

<style scoped>
.input {
  margin-top: 30px;
  border-bottom: 1px solid #a49988;
  width: 200px;
}
.button {
  font-family: inherit;
  font-size: 90%;
  margin: 0;
  cursor: pointer;
  border: none;
  background-color: #494346;
  color: white;
  width: 210px;
  border-radius: 4px;
  height: 30px;
}

img {
  position: relative;
}
.agree2 {
  position: absolute;
  z-index: 5;
  opacity: 0.5;
}
.agree2:hover {
  opacity: 1;
}
.agree2 input[type="checkbox"] {
  display: none;
}
.agree2 input[type="checkbox"] + label {
  width: 30px;
  height: 30px;
  background: #d2cdc5;
  cursor: pointer;
  border-radius: 3px;
  float: left;
  margin-right: 10px;
}
.agree2 input[type="checkbox"] + label:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
  opacity: 1;
}
.agree2 input[type="checkbox"]:checked + label {
  background: url(../assets/image/check.png) #d2cdc5 no-repeat center/20px 20px;
  float: left;
  opacity: 1;
}
.agree2 input[type="checkbox"] + label span {
  position: absolute;
  left: 0px;
  display: block;
}

hr {
  margin-bottom: 30px;
}
.content {
  position: relative;
  margin: 0 auto;
  padding-top: 80px;
  background-color: white;
  width: 100%;
  height: 100%;
  font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}
user agent stylesheet div {
  display: block;
}

/* top div */
.agree {
  padding-right: 10px;
}
.agree input[type="checkbox"] {
  display: none;
}
.agree input[type="checkbox"] + label {
  width: 30px;
  height: 30px;
  background: #d2cdc5;
  cursor: pointer;
  border-radius: 3px;
  float: left;
  margin-right: 10px;
}
.agree input[type="checkbox"] + label:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
.agree input[type="checkbox"]:checked + label {
  background: url(../assets/image/check.png) #d2cdc5 no-repeat center/20px 20px;
  float: left;
}
.agree input[type="checkbox"] + label span {
  position: absolute;
  left: 0px;
  display: block;
}
.download {
  background-color: white;
  color: #474346;
  border: 1px solid #d2cdc5;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
}
.share {
  background-color: #d2cdc5;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
  border: none;
}
.album {
  background-color: #a49988;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
  border: none;
}
.delete {
  background-color: #474346;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  text-align: center;
  width: 60px;
  height: 30px;
  font-size: 12px;
  border: none;
  outline: none;
  border: none;
}
.b1 {
  background-color: white;
  color: #474346;
  border: 1px solid #d2cdc5;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
}
.b2 {
  background-color: #d2cdc5;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
  border: none;
}
.b3 {
  background-color: #a49988;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
  border: none;
}
.b4 {
  background-color: #474346;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  text-align: center;
  width: 60px;
  height: 30px;
  font-size: 12px;
  border: none;
  outline: none;
  border: none;
}
button:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
select:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
input:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
.top-content {
  padding-bottom: 18px;
  padding-left: 235px;
}
.bottom-content {
  padding-left: 235px;
}

.search {
  padding-right: 15px;
  position: absolute;
  right: 0px;
  top: 80px;
}
.key {
  background-color: #a49988;
  color: white;
  width: 60px;
  height: 30px;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  text-align-last: center;
  font-size: 12px;
  outline: none;
  vertical-align: middle;
}
option {
  background-color: #a49988;
}
option:before {
  content: ">";
  font-size: 20px;
  display: none;
  padding-right: 10px;
  padding-left: 5px;
  color: #fff;
}
option:hover:before {
  display: inline;
}
input {
  /* -webkit-appearance: none; */
  -moz-appearance: none;
  appearance: none;
}
.value {
  border: 1px solid #a49988;
  width: 200px;
  height: 30px;
  text-align: center;
  font-size: 12px;
  outline: none;
  vertical-align: middle;
}
.submit {
  background-color: #a49988;
  width: 30px;
  height: 30px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  text-align: center;
  outline: none;
  vertical-align: middle;
  border: none;
}
.search-icon {
  border: none;
}
/* bottom div */
img {
  border: 2px solid #e3e2e1;
}
.li {
  float: left;
  margin-bottom: 10px;
  margin-right: 15px;
}
.thumb {
  width: 150px;
  height: 150px;
}
.info {
  margin-top: 11px;
  background-color: white;
  width: 150px;
}
.title {
  display: block;
  font-size: 12px;
  line-height: 15px;
  background-color: white;
  width: 150px;
  text-align: center;
}
</style>
