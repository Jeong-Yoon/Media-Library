<template>
  <content>
    <div class="content">
      <div class="top-content">
        <div class="agree" v-show="noShow">
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
          <button class="b0" @click="openDeleteModal">삭제</button>
          <button class="b1" @click="openRestoreModal">복원</button>
        </div>
        <div v-else>
          <button class="b2" @click="all">전체</button>
          <button class="b3" @click="onlyFolder">폴더</button>
          <button class="b4" @click="onlyImage">사진</button>
          <button class="b5" @click="onlyVideo">동영상</button>
        </div>
      </div>
      <hr class="top-hr" />

      <div class="bottom-content">
        <div>
          <ul class="table">
            <li
              v-for="item in this.items"
              v-bind:key="item.id"
              @change="checkbox(item.id)"
              class="li"
            >
              <!-- 폴더 -->
              <div v-if="checkType(item.id) == '2'" v-show="folders">
                <div class="agree2">
                  <input
                    class="a2"
                    :id="'a2' + item.id"
                    type="checkbox"
                    v-model="ids"
                    @click="select(item.id)"
                    :value="item.id"
                  />
                  <label :for="'a2' + item.id"></label>
                </div>
                <img
                  src="@/assets/image/folder.png"
                  width="130"
                  height="130"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                />
                <div class="info">
                  <span class="title">{{ item.folder_name }}</span>
                </div>
              </div>

              <!-- 사진 -->
              <div
                v-if="item.content_type == 'I' && checkType(item.id) == '3'"
                v-show="images"
              >
                <div class="agree2">
                  <input
                    class="a2"
                    :id="'a2' + item.id"
                    type="checkbox"
                    v-model="ids"
                    @click="select(item.id)"
                    :value="item.id"
                  />
                  <label :for="'a2' + item.id"></label>
                </div>
                <img
                  :src="roadImg(item.content)"
                  width="130"
                  height="130"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  @click="getImg(item.id)"
                />
                <div class="info">
                  <span class="title">{{ item.content_name }}</span>
                </div>
              </div>
              <!-- 동영상 -->
              <div
                v-if="item.content_type == 'V' && checkType(item.id) == '3'"
                v-show="videos"
              >
                <div class="agree2">
                  <input
                    class="a2"
                    :id="'a2' + item.id"
                    type="checkbox"
                    v-model="ids"
                    @click="select(item.id)"
                    :value="item.id"
                  />
                  <label :for="'a2' + item.id"></label>
                </div>
                <video
                  :src="item.content"
                  width="130"
                  height="130"
                  preload="metadata"
                  id="video1"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  @click="getVideo(item.id)"
                />
                <div class="info">
                  <span class="title">{{ item.content_name }}</span>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <p>selected ids : {{ ids }}</p>
    <delete-modal @close="closeDeleteModal" v-if="deleteModal">
      <p class="p1">{{ ids.length }}개의 항목을 삭제하시겠습니까?</p>
      <p class="p2">휴지통에서 삭제하신 항목을 복구할 수 없습니다.</p>

      <!-- footer 슬롯 콘텐츠 -->
      <template slot="footer">
        <button class="button1" @click="deleteItems">삭제</button>
        <button class="button2" @click="closeDeleteModal">취소</button>
      </template>
    </delete-modal>
    <restore-modal @close="closeRestoreModal" v-if="restoreModal">
      <div class="input">
        <p class="p1">{{ ids.length }}개의 항목을 복원하시겠습니까?</p>
        <p class="p2">복원된 항목은 기존 위치로 돌아갑니다.</p>
      </div>
      <!-- footer 슬롯 콘텐츠 -->
      <template slot="footer">
        <button class="button1" @click="restoreItems">복구</button>
        <button class="button2" @click="closeRestoreModal">취소</button>
      </template>
    </restore-modal>
  </content>
</template>

<script>
import { mapActions, mapState } from "vuex";
import DeleteModal from "@/views/DeleteModal.vue";
import RestoreModal from "@/views/RestoreModal.vue";

export default {
  components: {
    DeleteModal,
    RestoreModal,
  },
  data() {
    return {
      ids: [],
      noShow: true,
      folders: true,
      images: true,
      videos: true,
      items: [],
      deleteModal: false,
      restoreModal: false,
      allSelected: true,
    };
  },
  created() {
    this.getItems();
  },
  computed: {
    ...mapState({
      userInfo: "userInfo",
    }),
  },
  methods: {
    ...mapActions(["GET_ITEMS", "DELETE_ITEMS", "RESTORE_ITEMS"]),
    deleteItems() {
      this.DELETE_ITEMS(this.ids).then((data) => {
        console.log(data);
        if (data == 1) {
          alert("삭제가 완료되었습니다.");
          this.ids = [];
          this.closeDeleteModal();
          this.getItems();
        } else {
          alert("삭제에 실패했습니다.");
          this.ids = [];
          this.closeDeleteModal();
          this.getItems();
        }
      });
    },
    restoreItems() {
      console.log("--------------restore Start--------------");
      console.log("보낼 ids 리스트 : ", this.ids);
      this.RESTORE_ITEMS(this.ids).then((data) => {
        console.log("받은 결과값 : ", data);
        if (data == 1) {
          alert("복원이 완료되었습니다.");
          this.ids = [];
          this.closeRestoreModal();
          this.getItems();
        } else {
          alert("복원이 실패했습니다.");
          this.ids = [];
          this.closeRestoreModal();
          this.getItems();
        }
      });
      console.log("--------------restore End--------------");
    },
    openDeleteModal() {
      this.deleteModal = true;
    },
    closeDeleteModal() {
      this.deleteModal = false;
      this.getItems();
    },
    openRestoreModal() {
      this.restoreModal = true;
    },
    closeRestoreModal() {
      this.restoreModal = false;
      this.getItems();
    },
    getItems() {
      console.log("----------getItems start------------");
      console.log(this.userInfo.useremail);
      this.GET_ITEMS({ email: this.userInfo.useremail }).then((list) => {
        this.items = list;
        console.log("trashPage Items : ", this.items);
      });
      console.log("----------getItems end------------");
    },
    selectAll: function() {
      this.ids = [];
      if (!this.allSelected) {
        for (this.item in this.items) {
          this.ids.push(this.items[this.item].id);
        }
      }
    },
    select: function() {
      this.allSelected = false;
      this.ids.push(this.items[this.item].id);
    },
    roadImg(data) {
      const result = "data:image;base64," + data;
      return result;
    },
    checkType(id) {
      // var temp = id.subStr(0, 1);
      var temp = String(id);
      temp = temp.substring(0, 1);
      return temp;
    },
    all() {
      this.folders = true;
      this.images = true;
      this.videos = true;
      this.noShow = true;
    },
    onlyFolder() {
      this.folders = true;
      this.images = false;
      this.videos = false;
      this.noShow = false;
    },
    onlyImage() {
      this.folders = false;
      this.images = true;
      this.videos = false;
      this.noShow = false;
    },
    onlyVideo() {
      this.folders = false;
      this.images = false;
      this.videos = true;
      this.noShow = false;
    },
  },
};
</script>

<style scoped>
.input {
  border-bottom: 1px solid #a49988;
  width: 320px;
}
.p1 {
  font-size: 20px;
  padding-bottom: 5px;
  padding-top: 20px;
}
.p2 {
  padding-bottom: 35px;
  font-size: 12px;
  color: #a49988;
}
.button1 {
  font-family: inherit;
  font-size: 90%;
  margin: 0;
  cursor: pointer;
  border: none;
  background-color: #494346;
  color: white;
  width: 100px;
  border-radius: 4px;
  height: 30px;
  margin-right: 50px;
  margin-left: 25px;
}
.button2 {
  font-family: inherit;
  font-size: 90%;
  margin: 0;
  cursor: pointer;
  border: none;
  background-color: #d2cdc5;
  color: white;
  width: 100px;
  border-radius: 4px;
  height: 30px;
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
  opacity: initial;
  background-color: #a6c4c7;
}
.agree2 input[type="checkbox"] + label span {
  position: absolute;
  left: 0px;
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
.b0 {
  background-color: #dee8eb;
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
.b1 {
  background-color: #a6c4c7;
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
.b2 {
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
.b3 {
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
.b4 {
  background-color: #a49988;
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
.b5 {
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
  float: right;
  padding-right: 15px;
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
  -webkit-appearance: none;
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
  /*
  display: block;
  font-size: 12px;
  line-height: 15px;
  background-color: white;
  width: 150px;
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  */
  text-overflow: ellipsis;
  white-space: nowrap;
  display: block;
  font-size: 12px;
  font-weight: 500;
  line-height: 15px;
  word-break: break-all;
  word-wrap: break-word;
  color: #222;
  cursor: default;
  text-decoration: none;
  text-align: center;
  overflow: hidden;
  width: 130px;
  padding-bottom: 5px;
}
</style>
