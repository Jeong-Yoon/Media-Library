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
          <button class="share" @click="shareItem">공유</button>
          <button class="album" @click="openAlbumModal">앨범</button>
          <button class="delete" @click="deleteFile">삭제</button>
          <ChooseAlbumModal @close="closeAlbumModal" v-if="albumModal">
            <p class="pp">
              {{ ids.length }}개의 파일 어떤 앨범에 추가하시겠습니까?
            </p>
            <div>
              <select v-model="albumId" class="select-box">
                <option disabled value>앨범을 선택해주세요</option>
                <option
                  v-for="album in this.albums"
                  :value="album.album_id"
                  :key="album.album_id"
                >
                  {{ album.album_name }}
                </option>
              </select>
            </div>
            <template slot="footer">
              <!--<button class="button1" @click="doNewAlbum">새앨범</button>-->
              <button class="buttonAdd" @click="addAlbum">추가</button>
            </template>
          </ChooseAlbumModal>
        </div>
        <div v-else>
          <button class="b1" @click="openModal" v-show="noShow">새폴더</button>
          <button class="b2" @click="all">전체</button>
          <button class="b3" @click="onlyFolder">폴더</button>
          <button class="b4" @click="onlyImage">사진</button>
          <button class="b5" @click="onlyVideo">동영상</button>
          <NewFolderModal @close="closeModal" v-if="modal">
            <!-- default 슬롯 콘텐츠 -->
            <p>폴더 이름을 입력해주세요.</p>
            <div>
              <input class="input" v-model="newFolderName" />
            </div>
            <!-- /default -->
            <!-- footer 슬롯 콘텐츠 -->
            <template slot="footer">
              <button class="button" @click="doNewFolder">생성</button>
            </template>
            <!-- /footer -->
          </NewFolderModal>
        </div>
        
        <!-- <form class="search"> -->
          <!-- <select id="key" name="keyword" class="key">
            <option value="file_origin_name">파일명</option> -->
            <!-- <option value="contents_atribute">확장자</option>
            <option value="contents_reg_date">등록일</option> -->
          <!-- </select> -->
          <!-- <input
            type="text"
            name="search"
            placeholder="Search.."
            v-model="keyword"
            class="value"
            
          />
          <button @click="search" class="submit">
            <img
              src="@/assets/image/search.png"
              alt="search logo"
              height="15px"
              class="search-icon"
            />
          </button>
        </form> -->
        
      </div>
      <hr class="top-hr" />

      <div class="bottom-content">
        <div>
          <ul class="table">
            <li
              v-for="folder in this.folderList"
              v-bind:key="folder.id"
              class="li"
            >
              <!-- 폴더 -->
              <div v-if="checkType(folder.id) == '2'" v-show="folders">
                <div class="agree2" >
                  <input
                    class="a2"
                    :id="'a2' + folder.id"
                    type="checkbox"
                    v-model="ids"
                    @click="select(folder.id)"
                    :value="folder.id"
                  />
                  <label :for="'a2' + folder.id"></label>
                </div>
                <router-link :to="`/ownDocumentBox/${folder.id}`">
                  <img
                    src="@/assets/image/folder.png"
                    width="130"
                    height="130"
                    style="opacity: 1; transition: opacity 0.2s ease 0s;"
                    @mouseover.right="select"
                    @dragover.prevent
                    @drop.prevent="onDrop(folder.id)"
                  />
                </router-link>
                <div class="info">
                  <span class="title">{{ folder.folder_name }}</span>
                </div>
              </div>


              <!-- 사진 -->
              <div
                v-if="folder.content_type == 'I' && checkType(folder.id) == '3'"
                v-show="images"
              >
                <div class="agree2">
                  <input
                    class="a2"
                    :id="'a2' + folder.id"
                    type="checkbox"
                    v-model="ids"
                    @click="select(folder.id)"
                    :value="folder.id"
                  />
                  <label :for="'a2' + folder.id"></label>
                </div>
                <img
                  :src="roadImg(folder.content)"
                  width="130px"
                  height="130px"
                  style="overflow:hidden; opacity: 1; transition: opacity 0.2s ease 0s;"
                  @click="getImg(folder.id)"
                  @dragstart="dragstart_handler(folder.id)"
                  @dragend="dragend_handler()"
                  draggable="true"
                />
                <div class="info">
                  <span class="title">{{ folder.content_name }}</span>
                </div>
              </div>
              <!-- 동영상 -->
               <div :id="'video-div'+folder.id"
                v-if="folder.content_type == 'V' && checkType(folder.id) == '3'"
                v-show="videos"
              >
                <div class="agree2">
                  <input
                    class="a2"
                    :id="'a2' + folder.id"
                    type="checkbox"
                    v-model="ids"
                    @click="select(folder.id)"
                    :value="folder.id"
                  />
                  <label :for="'a2' + folder.id"></label>
                </div>
                <video
                  :id="'video'+folder.id"
                  :poster="roadImg(folder.content)"
                  :src="'/api/videos/video/'+ folder.id"
                  width="130"
                  height="130"
                  style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  @click="getVideo(folder.id)"
                  @dragstart="dragstart_handler(folder.id)"
                  @dragend="dragend_handler()"
                  draggable="true"
                  @mouseover="preview(folder.id)"
                  @mouseout="preEnd(folder.id)"
                  muted
                />
                <!-- <img
                  :src="roadImg(folder.content)"
                  width="130px"
                  height="130px"
                  style="overflow:hidden; opacity: 1; transition: opacity 0.2s ease 0s;"
                  :id="'content'+folder.id"
                  @mouseover="preview(folder.id)"
                  @onmouseout="preEnd(folder.id)"
                  @click="getVideo(folder.id)"
                  @dragstart="dragstart_handler(folder.id)"
                  @dragend="dragend_handler()"
                /> -->
                <div class="info">
                  <span class="title">{{ folder.content_name }}</span>
                </div>
              </div>
            </li>
          </ul>
        </div>

        <ImageViewingModal
          :idOfImage="idOfImage"
          :imageList="imageList"
          v-if="imageModal"
          :idx="idx"
          @getImg="getImg"
          @close="closeImageModal"
        />
        <VideoViewingModal
          :idOfVideo="idOfVideo"
          :videoList="videoList"
          v-if="videoModal"
          @getVideo="getVideo"
          @close="closeVideoModal"
        />
        <!--<p>selected ids : {{ ids }}</p>-->
      </div>
    </div>
    <fab
      :position="position"
      :bg-color="bgColor"
      :actions="fabActions"
      :z-index="zIndex"
      @uploadFile="openFileModal"
    />
    <UploadFileModal @close="closeFileModal()" v-if="fileModal" />
    <!--
    <quick-menu
      :menu-count="count"
      :icon-class="icons"
      :menu-url-list="list"
      :position="position"
      :background-color="backgroundColor"
    />-->
  </content>
</template>

<script>
//import quickMenu from "vue-quick-menu";
import fab from "vue-fab";
import NewFolderModal from "./NewFolderModal";
import ImageViewingModal from "./ImageViewingModal";
import VideoViewingModal from "./VideoViewingModal";
import UploadFileModal from "./UploadFileModal";
import ChooseAlbumModal from "./ChooseAlbumModal.vue";
//import UploadFolderModal from "./UploadFolderModal";
import { mapActions, mapState } from "vuex";

export default {
  components: {
    //quickMenu,
    fab,
    NewFolderModal,
    ImageViewingModal,
    VideoViewingModal,
    UploadFileModal,
    ChooseAlbumModal,
    //UploadFolderModal,
  },
  props:[
    "selectFolderId"
  ],
  data() {
    return {
      // count: 2,
      // icons: ["fa fa-file", "fa fa-folder"],
      // list: [
      //   { isLink: true, url: "/uploadFile" },
      //   { isLink: true, url: "/uploadFolder" },
      // ],
      // position: "bottom-right",
      // backgroundColor: "#474346",
      bgColor: "#494346",
      position: "bottom-right",
      fabActions: [
        {
          name: "uploadFile",
          icon: "insert_drive_file",
        },
      ],
      zIndex: 50,
      modal: false,
      albumModal: false,
      imageModal: false,
      videoModal: false,
      fileModal: false,
      folderModal: false,
      newFolderName: "",
      folderList: [],
      targetList: [],
      allSelected: true,
      ids: [],
      idOfImage: "",
      idOfVideo: "",
      folders: true,
      images: true,
      videos: true,
      noShow: true,
      imageList: [],
      fileName: "",
      downloadId: "",
      intoParent: "",
      videoList: [],
      folderId: "",
      albumId: "",
      albums: [],
      idx:"",
      updatefolder:"",
      updatefile:"",
      keyword:""
      // selectedFolderId: "",
    };
  },
  created() {
    console.log("------create------");
    this.getFolders();
  },
  watch: {
    // 라우터 객체를 감시하고 있다가 fetchData() 함수를 호출한다
    $route: "getFolders",
  },
  computed: {
    ...mapState({
      userInfo: "userInfo",
      parent: "root_folder",
    }),
    filteredList() {
      return Object.values(this.folderList).filter((post) => {
          console.log(post.content_name);
          return post.folder_name.toLowerCase().includes(this.keyword.toLowerCase());
    })},
  },
  methods: {
    ...mapActions([
      "ADD_ALBUM",
      "GET_ALBUMS",
      "NEW_FOLDER",
      "GET_FOLDERS",
      "DOWNLOAD_FILE",
      "GET_ONLY",
      "GET_IMAGE",
      "GET_VIDEO",
      "GET_IMAGELIST",
      "DELETE_FILE",
      "GET_VIDEO_LIST",
      "SHARE_ITEMS",
      "MOVE_FILE",
      // "SEARCH_FILE"
    ]),
    // search(){
    //   this.SEARCH_FILE({ keyword: this.keyword }).then((list) => {
    //     this.folderList = list;
    //     console.log("albumList : ", this.folderList);
    //   });
    // },
    preview(id){  
      var cId = 'video' + id
      var v1 = document.getElementById(cId);
      v1.play();
      // var cId = 'content'+id
      // var v1 = document.getElementById(cId);
      // v1.hide();
      // var v = ' <video'
      //           +'src="/api/videos/video/'+id+'"'
      //           + 'width="130"'
      //           + 'height="130"'
      //           + 'style="opacity: 1; transition: opacity 0.2s ease 0s;"'
      //           + 'autoplay muted '
      //           + '@click="getVideo(folder.id)"'
      //           +'/>';
      // var vId = 'video-div'+id
      // var dv = document.getElementById(vId);
      // dv.innerHTML = v;
    },
    preEnd(id){
      var cId = 'video'+id
      var v1 = document.getElementById(cId);
      v1.pause();
      // v1.show();
      // var dv = document.getElementById('video-div'+id);
      // dv.innerHTML = ''
    },
    onDrop(folder_id){
      console.log("folerId:",folder_id)
      this.updatefolder = folder_id;
    },
    dragend_handler(){
      console.log("드래그 끝! 드래그 관련 변수를 비워주세용!")
      this.MOVE_FILE({ folder : this.updatefolder, file : this.updatefile}).then(()=>{
        this.getFolders();
      })
      this.updatefolder = "";
      this.updatefile = "";
    },
    dragstart_handler(img_id){
      console.log("dragstart! ", img_id)
      this.updatefile = img_id;
    },
    addAlbum() {
      console.log("----------------addAlbum start---------------");
      console.log("albumId : ", this.albumId);
      console.log("ids : ", this.ids);
      for(var i = 0; i < this.ids.length; i++){
        if(Math.floor(this.ids[i] / 10000) === 2){
          alert('폴더는 앨범에 추가할 수 없습니다.')
          return;
        }
      }
      this.ADD_ALBUM({ album_id: this.albumId, ids: this.ids }).then((data) => {
        console.log("data : ", data);
        if (data == this.ids.length) {
          alert("앨범에 파일이 추가되었습니다.");
          this.albumId = "";
          this.ids = [];
        } else {
          alert("파일 추가에 실패하였습니다.");
          this.albumId = "";
          this.ids = [];
        }
      });
      console.log("----------------addAlbum end---------------");
    },
    getAlbums() {
      console.log("-------------Get Albums Start------------");
      this.GET_ALBUMS({ email: this.userInfo.useremail }).then((list) => {
        this.albums = list;
        console.log("albumList : ", this.albums);
      });
      console.log("-------------Get Albums End------------");
    },
    resetImg() {
      this.idOfImage = "";
    },
    goToStart() {
      if (document.getElementById("video1").currentTime == 15) {
        document.getElementById("video1").currentTime = 0;
      }
    },
    getVideo(videoId) {
      console.log("getVideo : " + videoId);
      this.idOfVideo = videoId;
      console.log("get Video : " + this.idOfVideo);
      console.log(this.$route.params.id + "=---0-0-0-0")
      if (typeof this.$route.params.id === "undefined") {
        this.folderId = this.parent;
      } else {
        this.folderId = this.$route.params.id;
      }
      this.GET_VIDEO_LIST({ folderId: this.folderId, videoId: videoId }).then(
        (result) => {
          console.log(this.parent);
          console.log("video list result : " + result[0]);
          this.videoList = result;
        }
      );
      this.openVideoModal();
    },
    deleteFile() {
      console.log(this.ids);
      this.DELETE_FILE(this.ids).then((data) => {
        console.log(data);
        if (data == 1) {
          alert("삭제된 파일이 휴지통으로 이동하였습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        } else {
          alert("파일 삭제에 실패했습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        }
      });
    },
    deleteFolder() {
      console.log(this.ids);
      this.DELETE_FOLDERS(this.ids).then((data) => {
        console.log(data);
        if (data == 1) {
          alert("삭제된 폴더가 휴지통으로 이동하였습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        } else {
          alert("폴더 삭제에 실패했습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        }
      });
    },
    deleteItems() {
      console.log(this.ids);
      this.DELETE_ITEMS(this.ids).then((data) => {
        console.log(data);
        if (data == 1) {
          alert("삭제된 폴더 및 파일이 휴지통으로 이동하였습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        } else {
          alert("폴더 및 파일 삭제에 실패했습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        }
      });
    },
    shareItem() {
      console.log(this.ids + " : share item");
      console.log(this.ids.length)
      this.SHARE_ITEMS(this.ids).then((data) => {
        if (data === 1) {
          console.log(data);
          alert("공유에 성공했습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        } else {
          alert("공유에 실패했습니다.");
          this.ids = [];
          this.intoFolder(this.$route.params.id);
        }
      });
    },
    async download() {
      console.log("downloadFile실행..");
      for (var j = 0; j < this.ids.length; j++) {
        this.downloadId = this.ids[j];
        this.fileName = "";
        await this.DOWNLOAD_FILE({ id: this.ids[j] })
          .then((res) => {
            const url = window.URL.createObjectURL(new Blob([res.data]), {
              type: "*",
            }); // = window.URL.createObjectURL(new Blob([res.data], { type: 'application/zip' }));
            for (var i = 0; i < this.folderList.length; i++) {
              // console.log(i + ',' + this.folderList[i].id + ',' + this.folderList[i].content_name)
              if (this.downloadId === this.folderList[i].id) {
                this.fileName = this.folderList[i].content_name;
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
    getImg(imageId) {
      console.log("getImg...", imageId);
      this.GET_IMAGE({ image_id: imageId }).then((data) => {
        console.log(data);
        this.idOfImage = data;
      });
      if (typeof this.$route.params.id === "undefined") {
        this.folderId = this.parent;
      } else {
        this.folderId = this.$route.params.id;
      }
      console.log("folderId : " + this.folderId);
      this.GET_IMAGELIST({ folderId: this.folderId }).then((result) => {
        //console.log(this.parent);
        //console.log(result[0].content_id + " : image list");
        this.imageList = result;
      });
      for(var i = 0; i < this.imageList.length; i++){
        if(this.idOfImage.content_id === this.imageList[i].content_id){
          this.idx = i
        }
      }
      this.openImageModal();
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
    intoFolder(intoParent) {
      console.log("--------into the folder start--------");
      this.intoParent = intoParent;
      if(typeof this.intoParent === 'undefined'){
        this.intoParent = this.parent;
      }
      console.log(this.intoParent);
      this.GET_FOLDERS({ parent: this.intoParent }).then((list) => {
        //console.log("intoParent List : ", list);
        this.folderList = list;
      });
      console.log("--------into the folder end--------");
    },
    getOnly(target) {
      this.GET_ONLY({ parent: this.parent, target: target }).then((list) => {
        // this.targetList = list;
        // console.log("targetList : ", this.targetList);
        console.log("list", list);
        this.folderList = list;
      });
    },
    getFolders() {
      console.log(this.selectedFolderId)
      let id = this.parent;
      if (this.$route.params.id) {
        id = this.$route.params.id;
      }
      console.log("---------------------get folders start ----------------");
      console.log("id : ", id);
      
      this.GET_FOLDERS({ parent: id }).then((list) => {
        console.log("ownpage list : ", list);
        this.folderList = list;
        //민준
        //폴더의 첫 select focus를 지정해줍니다.
        // this.folderList.some((element)=>{
        //   if(element.content){
        //     this.selectedFolderId= element.id;
        //     return true;
        //   }
        // })
        // console.log('### selectedFolderId', this.selectedFolderId)
        // console.log("ownpage folderlist : ", this.folderList);
      });
      console.log("---------------------get folders start----------------");
    },
    selectAll: function() {
      this.ids = [];
      if (!this.allSelected) {
        for (this.folder in this.folderList) {
          this.ids.push(this.folderList[this.folder].id);
        }
      }
    },
    /*
    checkbox(folder_id) {
      console.log(folder_id);
      // this.ids.pudh(folder_id)
      // if (this.ids.indexOf(folder_id) === -1) {
      //   this.ids.push(folder_id);
      //   // this.ids.push(folder_id)
      // } else {
      //   this.ids.splice(this.ids.indexOf(folder_id), 1);
      // }
    },
    */
    select: function() {
      this.allSelected = false;
      this.ids.push(this.folderList[this.folder].id);
    },
    openAlbumModal() {
      this.albumModal = true;
      this.getAlbums();
    },
    closeAlbumModal() {
      this.albumModal = false;
      this.intoFolder(this.$route.params.id);
    },
    openImageModal() {
      this.imageModal = true;
    },
    openVideoModal() {
      this.videoModal = true;
    },
    openFileModal() {
      this.fileModal = true;
    },
    openFolderModal() {
      this.folderModal = true;
    },
    openModal() {
      this.modal = true;
    },
    closeModal() {
      this.modal = false;
      this.getFolders(this.intoParent);
    },
    closeFileModal() {
      this.fileModal = false;
      this.intoFolder(this.$route.params.id);
    },
    closeFolderModal() {
      this.folderModal = false;
      this.getFolders(this.intoParent);
    },
    closeImageModal() {
      this.imageModal = false;
      this.getFolders(this.intoParent);
    },
    closeVideoModal() {
      this.videoModal = false;
      this.getFolders(this.intoParent);
    },
    doNewFolder() {
      if (this.newFolderName.length < 0) {
        alert("폴더명을 입력해주세요.");
      } else {
        console.log(this.newFolderName + " : newFolderName");
        if(this.$route.params.id){
          this.intoParent = this.$route.params.id;
        } else {
          this.intoParent = this.parent
        }
        console.log(this.$route.params.id + " : intoParent")
        this.NEW_FOLDER({
          parent: this.intoParent,
          newFolderName: this.newFolderName,
          userEmail: this.userInfo.useremail,
        })
          .then(() => {
            alert("폴더가 생성되었습니다.");
            this.newFolderName = "";
            this.closeModal();
            this.getFolders(this.$route.params.id);
          })
          .catch((error) => {
            alert("폴더 생성에 실패했습니다.");
            this.error = error.data.error;
            this.newFolderName = "";
            this.closeModal();
            this.getFolders(this.$route.params.id);
          });
      }
    },
    roadImg(data) {
      const result = "data:image;base64," + data;
      return result;
    },
    selectedItem(id){
        console.log('selectedItem', id);
        this.selectedFolderId=id;
    }
  },
};
</script>

<style scoped>
option {
  color: #a49988;
}
.pp {
  padding-bottom: 10px;
}
.buttonAdd {
  font-family: inherit;
  font-size: 90%;
  margin: 0;
  cursor: pointer;
  border: none;
  background-color: #494346;
  color: white;
  border-radius: 4px;
  height: 30px;
  width: 100px;
  margin-left: 100px;
}

.agree3 {
  position: absolute;
  z-index: 5;
  opacity: 0.5;
}
.agree3:hover {
  opacity: 1;
}
.agree3 input[type="checkbox"] {
  display: none;
}
.agree3 input[type="checkbox"] + label {
  width: 20px;
  height: 20px;
  background: #d2cdc5;
  cursor: pointer;
  border-radius: 3px;
  float: left;
  margin-right: 10px;
}
.agree3 input[type="checkbox"] + label:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
  opacity: 1;
}
.agree3 input[type="checkbox"]:checked + label {
  background: url(../assets/image/check.png) #d2cdc5 no-repeat center/20px 20px;
  float: left;
  opacity: initial;
  background-color: #a6c4c7;
}
.agree3 input[type="checkbox"] + label span {
  position: absolute;
  left: 0px;
  display: block;
}

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
/* select:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
} */
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
/* option {
  background-color: #a49988;
} */
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
video {
  border: 2px solid #e3e2e1;
}
video:focus {
  border: none;
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
/* 민준 :: 선택된 항목의 css를 다르게
                 */
.selected-title {
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
  background-color: #D9F1F1;
}
</style>
