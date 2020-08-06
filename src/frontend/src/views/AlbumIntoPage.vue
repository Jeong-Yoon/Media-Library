<template>
  <content>
    <div class="content">
      <div class="top-content">
        <div class="agree">
          <div class="all_agree">
            <input type="checkbox" id="a1" name="전체동의" />
            <label for="a1"></label>
          </div>
        </div>
        <div v-if="this.ids > 0">
          <button class="b1" @click="deleteItem()">삭제</button>
        </div>
        <div v-else>
          <button class="b2" @click="all">전체</button>
          <button class="b3" @click="onlyFolder">폴더</button>
          <button class="b4" @click="onlyImage">사진</button>
          <button class="b5" @click="onlyVideo">동영상</button>
        </div>
        <!--
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
        -->
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
                <img
                  :src="roadImg(item.content)"
                  width="130"
                  height="130"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
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
  </content>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      userInfo: "userInfo",
    }),
  },
  components: {},
  data() {
    return {
      newAlbumName: "",
      modal: false,
      albums: [],
      items: [],
      intoAlbumKey: "",
      ids: [],
      albumId: "",
    };
  },
  created() {},
  watch: {
    $route: "getAlbums",
  },
  methods: {
    ...mapActions(["INTO_ALBUM", "DELETE_ITEM"]),
    intoAlbum(intoAlbumKey) {
      console.log("-------------Into Albums Start------------");
      this.intoAlbumKey = intoAlbumKey;
      this.albumId = "";
      console.log("intoAlbum : ", this.intoAlbum);
      this.INTO_ALBUM({ intoAlbumKey: intoAlbumKey }).then((list) => {
        this.items = list;
      });
      console.log("-------------Into Albums End------------");
    },
    deleteItem() {
      console.log("-------------delete Item Start------------");
      console.log("삭제할 아이템목록 : ", this.ids);
      this.DELETE_ITEM(this.ids).then((data) => {
        console.log("결과값 : ", data);
        if (data == 1) {
          alert("파일이 앨범에서 삭제되었습니다.");
          this.ids = [];
          this.intoAlbum(this.$route.params.id);
        } else {
          alert("파일 삭제에 실패했습니다.");
          this.ids = [];
          this.intoAlbum(this.$route.params.id);
        }
      });
      console.log("-------------delete Item End------------");
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
    selectAll: function() {
      this.ids = [];
      if (!this.allSelected) {
        for (this.folder in this.folderList) {
          this.ids.push(this.folderList[this.folder].id);
        }
      }
    },
    select: function() {
      this.allSelected = false;
      this.ids.push(this.folderList[this.folder].id);
    },
    roadImg(data) {
      const result = "data:image;base64," + data;
      return result;
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
