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
          <button class="download" @click="download" v-show="noShow">
            받기
          </button>
          <button class="unShare" @click="openUnShareModal">해제</button>
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
                <router-link :to="`/shareDocumentBox/${item.id}`">
                <img
                  src="@/assets/image/folder.png"
                  width="130"
                  height="130"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                />
                </router-link>
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
                <!-- <video
                  :src="item.content"
                  width="130"
                  height="130"
                  preload="metadata"
                  id="video1"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  @click="getVideo(item.id)"
                /> -->
                <img
                  :src="roadImg(item.content)"
                  width="130px"
                  height="130px"
                  style="overflow:hidden; opacity: 1; transition: opacity 0.2s ease 0s;"
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
    <!-- <p>selected ids : {{ ids }}</p> -->
    <un-share-modal @close="closeUnShareModal" v-if="unShareModal">
      <p class="p1">{{ ids.length }}개의 항목의 공유를 해제하시겠습니까?</p>
      <p class="p2">해제된 파일은 기존 개인문서함에서 확인이 가능합니다.</p>

      <!-- footer 슬롯 콘텐츠 -->
      <template slot="footer">
        <button class="button1" @click="unshareItems">해제</button>
        <button class="button2" @click="closeUnShareModal">취소</button>
      </template>
    </un-share-modal>

      <ImageViewingModal
          :idOfImage="idOfImage"
          :imageList="imageList"
          v-if="imageModal"
          @getImg="getImg"
          @close="closeImageModal"
        />
        <VideoViewingModal
          :idOfVideo="idOfVideo"
          :videoList="videoList"
          :folderId="0"
          v-if="videoModal"
          @getVideo="getVideo"
          @close="closeVideoModal"
        />


  </content>
</template>

<script>
import UnShareModal from "@/views/UnShareModal.vue";
import { mapActions } from "vuex";
import ImageViewingModal from "./ImageViewingModal";
import VideoViewingModal from "./VideoViewingModal";

export default {
  components: {
    UnShareModal,
    ImageViewingModal,
    VideoViewingModal,
  },
  data() {
    return {
      ids: [],
      noShow: true,
      unShareModal: false,
      folders: true,
      images: true,
      videos: true,
      items: [],
      allSelected: true,
      downloadId: "",
      videoModal: false,
      imageModal : false,
      videoList: [],
      imageList: [],
    };
  },
  created() {
    this.getItems();
    // this.unShared();
  },
  watch: {
    // 라우터 객체를 감시하고 있다가 fetchData() 함수를 호출한다
    $route: "getItems",
  },
  methods: {
    ...mapActions([
      "GET_SHARE_ITEMS",
      "UN_SHARE_ITEMS",
      "DOWNLOAD_FILE",
      "GET_IMAGE",
      "GET_VIDEO",
      "GET_IMAGELIST",
      "GET_VIDEO_LIST",
      "GET_FOLDERS"
    ]),
    getItems() {
      console.log(this.$route.params.id)
      if(this.$route.params.id){
        this.GET_FOLDERS({ parent: this.$route.params.id}).then((list) => {
          this.items = list;
        })
      } else {
        this.GET_SHARE_ITEMS().then((list) => {
          console.log("share page list : " + list[0]);
        this.items = list;
        console.log("share page folderlist : " + this.items);
      });
      }
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
    unshareItems(){
      this.UN_SHARE_ITEMS(this.ids).then((data)=>{
        console.log(data)
        if (data == 1) {
          alert("해제가 완료되었습니다.");
          this.ids = [];
          this.closeUnShareModal();
          this.getItems();
        } else {
          alert("해제에 실패했습니다.");
          this.ids = [];
          this.closeUnShareModal();
          this.getItems();
        }
      })
    },
   getImg(imageId) {
      console.log("getImg...", imageId);
      this.GET_IMAGE({ image_id: imageId }).then((data) => {
        console.log(data);
        this.idOfImage = data;
      });
      if(typeof(this.$route.params.id) === 'undefined'){
        this.folderId = this.parent;
      } else {
        this.folderId = this.$route.params.id
      }
      console.log("folderId : " + this.folderId)
      this.GET_IMAGELIST({ folderId: this.folderId }).then((result) => {
        //console.log(this.parent);
        //console.log(result[0].content_id + " : image list");
        this.imageList = result;
      });
      this.openImageModal();
    },

    getVideo(videoId) {
      console.log("getVideo : " + videoId);
      this.idOfVideo = videoId;
      console.log("get Video : " + this.idOfVideo)
      this.GET_VIDEO_LIST({ folderId: this.parent, videoId: videoId }).then(
        (result) => {
          console.log(this.parent);
          console.log("video list result : " + result[0]);
          this.videoList = result;
        });
      this.openVideoModal();
    },

    openImageModal() {
      this.imageModal = true;
    },
    closeImageModal() {
      this.imageModal = false;
    },
    openVideoModal() {
      this.videoModal = true;
    },
     closeVideoModal() {
      this.videoModal = false;
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
    openUnShareModal() {
      this.unShareModal = true;
    },
    closeUnShareModal() {
      this.unShareModal = false;
      this.getItems();
    },
    async download() {
      console.log("downloadFile실행..");
      console.log(this.ids)
      console.log(this.items)
      console.log(this.ids.length)
      console.log(this.items.length)
      for (var j = 0; j < this.ids.length; j++) {
        this.downloadId = this.ids[j];
        this.fileName = "";

        await this.DOWNLOAD_FILE({ id: this.ids[j] })
          .then((res) => {
            const url = window.URL.createObjectURL(new Blob([res.data]), {
              type: "*",
            }); // = window.URL.createObjectURL(new Blob([res.data], { type: 'application/zip' }));
            for (var i = 0; i < this.items.length; i++) {
              // console.log(i + ',' + this.folderList[i].id + ',' + this.folderList[i].content_name)
              if (this.downloadId === this.items[i].id) {
                this.fileName = this.items[i].content_name;
              }
            }
            const link = document.createElement("a");
            link.href = url;
            link.setAttribute("download", this.fileName);
            document.body.appendChild(link);
            link.click();
            // this.ids = [];
            // return res;
          })
          .catch((err) => {
            console.log("err~!");
            console.error(err);
          });
      }
      alert("다운로드가 완료되었습니다.");
      this.ids = [];
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
.download {
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
.unShare {
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
