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
        <button class="b1" @click="openModal">새앨범</button>
        <div v-if="this.albumIds > 0">
          <button class="b2" @click="deleteAlbum()">삭제</button>
        </div>
        <NewFolderModal @close="closeModal" v-if="modal">
          <!-- default 슬롯 콘텐츠 -->
          <p>앨범 이름을 입력해주세요.</p>
          <div>
            <input class="input" v-model="newAlbumName" />
          </div>
          <!-- /default -->
          <!-- footer 슬롯 콘텐츠 -->
          <template slot="footer">
            <button class="button" @click="doNewAlbum">생성</button>
          </template>
          <!-- /footer -->
        </NewFolderModal>
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
        <ul class="table">
          <li
            v-for="album in this.albums"
            v-bind:key="album.album_id"
            class="li"
          >
            <div>
              <div class="agree2">
                <input
                  class="a2"
                  :id="'a2' + album.album_id"
                  type="checkbox"
                  v-model="ids"
                  @click="select(album.album_id)"
                  :value="album.album_id"
                />
                <label :for="'a2' + album.album_id"></label>
              </div>
              <router-link :to="`/albumDocumentBox/${album.album_id}`">
                <img
                  src="@/assets/image/album.png"
                  alt="folder.png"
                  width="150"
                  height="150"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                />
              </router-link>
              <div class="info">
                <span class="title">{{ album.album_name }}</span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </content>
</template>

<script>
import NewFolderModal from "./NewFolderModal";
import { mapActions, mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      userInfo: "userInfo",
    }),
  },
  components: { NewFolderModal },
  data() {
    return {
      newAlbumName: "",
      modal: false,
      albums: [],
      items: [],
      intoAlbumKey: "",
      ids: [],
      albumIds: [],
    };
  },
  created() {
    this.getAlbums();
  },
  watch: {
    $route: "getAlbums",
  },
  methods: {
    ...mapActions(["NEW_ALBUM", "GET_ALBUMS", "DELETE_ALBUM"]),
    getAlbums() {
      console.log("-------------Get Albums Start------------");
      this.GET_ALBUMS({ email: this.userInfo.useremail }).then((list) => {
        this.albums = list;
        console.log("albumList : ", this.albums);
      });
      console.log("-------------Get Albums End------------");
    },
    deleteAlbum() {
      console.log("-------------delete Album Start------------");
      console.log("삭제할 앨범 : ", this.albumIds);
      this.DELETE_ALBUM(this.albumIds).then((data) => {
        if (data == 1) {
          alert("앨범이 삭제되었습니다.");
          this.albumIds = [];
          this.getAlbums();
        } else {
          alert("앨범 삭제에 실패했습니다.");
          this.albumIds = [];
          this.getAlbums();
        }
      });
      console.log("-------------delete Album End------------");
    },
    doNewAlbum() {
      console.log("-------------New Album Start-------------");
      if (this.newAlbumName.length < 0) {
        alert("앨범명을 입력해주세요.");
      } else {
        console.log(this.newAlbumName + " : newAlbumName");
        this.NEW_ALBUM({
          newAlbumName: this.newAlbumName,
          userEmail: this.userInfo.useremail,
        })
          .then(() => {
            alert("앨범이 생성되었습니다.");
            this.newAlbumName = "";
            this.closeModal();
            this.getAlbums(this.$route.params.id);
          })
          .catch((error) => {
            alert("앨범 생성에 실패했습니다.");
            this.error = error.data.error;
            this.newAlbumName = "";
            this.closeModal();
            this.getAlbums(this.$route.params.id);
          });
      }
      console.log("-------------New Album End-------------");
    },
    openModal() {
      this.modal = true;
    },
    closeModal() {
      this.modal = false;
      this.getAlbums();
    },
    selectAll: function() {
      this.ids = [];
      if (!this.allSelected) {
        for (this.album in this.albums) {
          this.ids.push(this.albums[this.album].id);
        }
      }
    },
    select: function() {
      this.allSelected = false;
      this.ids.push(this.albums[this.album].id);
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
  display: block;
  font-size: 12px;
  line-height: 15px;
  background-color: white;
  width: 150px;
  text-align: center;
}
</style>
