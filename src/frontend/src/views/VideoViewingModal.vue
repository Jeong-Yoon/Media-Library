<template>
  <div>

    <!-- 비디오 모달 -->
    <div class="overlay" id="video_nav" tabindex="1" role="dialog">
      <!-- header -->
      <div class="header">
        <!-- 나가기 -->
        <!-- <a class="v_btn_close" @click="closeVideoNav()" style="color:#fff;  font-size: 14px;">나가기</a> -->
          <a class="v_btn_close" @click="$emit('close')" style="color:#fff; font-size: 15px; z-index :10;">
             <img src="@/assets/image/view_ss_exit.png" height="14px" />
          </a>


        <div class="v_task">
    
          <!-- 삭제 -->
          <!-- <a 
            class="v_ta_trash"
            @click="deleteFile"
            title="휴지통"
          >
            <img src="@/assets/image/v_task_delete.png" height="15px" />
          </a> -->

          <!-- 공유 문서함 추가  -->
          <!-- <a class="v_ta_share_doc" title="공유문서함">
            <img src="@/assets/image/v_task_documentshare.png" height="16px"  style="margin-right:3px; top:1px;"/>
          </a> -->


              <!-- 더보기 -->
          <div class="dropdown">
            <!-- <button class="dropbtn">Dropdown</button> -->
            <a 
              class="dropbtn"
              title="더보기" 
              style="left:-3px; top:1px;"
              
            >
              <img src="@/assets/image/v_task_more.png" height="15px" />
            </a>
            <div class="dropdown-content" style="float:right;">
              <!-- <a
                class="slider_option"
                @click="deleteFile"
              >
                삭제
              </a> -->
             <a 
                @click="shareItem"
                class="publicfolder">
                공유 문서함 추가
              </a>
              <!-- <a class="publicalbum">공유 앨범 추가</a> -->
            </div>
          </div>
          <!-- 더보기 끝 -->

        </div>
        <!-- v_task 끝 -->

      </div>
      <!-- header 끝 -->

      <!-- content -->
      <div class="content">
        <div class="video-container" id="video-div">
          <video 
            id="video"
            controls autoplay muted loop :src="this.src" 
            type="video/mp4"
            style ="width :1040px; height : 600px;"
           >
          </video>
            <!-- <div class="footer-info"  style="margin-right : 20px;">
              <h4 class="media-heading"  style="margin-right : 20px;">{{content_id.content_origin_name}}</h4>
              <p class="by-upload"  style="margin-right : 20px; ">2020.05.01 20:17:56</p>
              <p class="by-upload">By 최지은</p>
            </div> -->

        </div>
        <!-- video-con -->

        <div class ="side-contanier">
            <a class="container-title">
                다음동영상
            </a>

            <div class ="video-thumb">
              <ul class="media-list main-list ypt_thumbs">
              
                <li 
                  class="media"
                  v-for="list in this.vList"
                  v-bind:key="list.content_id"
                  style="margin-bottom : 11px; height : 110px;"
                > 
                    <img
                      class="vvvvvv"
                      :src="roadImg(list.content)"
                      @click="getVideoId(list.content_id)"
                      style="                             
                              height:100px;
                              width : 180px;
                              max-width : 240px;
                              object-fit:contain;
                              background-color : #fff; 
                              float : left;
                              z-index : 500000;
                            "
                    />
                    <!-- {{ list.content_id }} -->
                    <div class="media-body" style="height : 110px; float : left;" > 
                    <!-- 파일명, 업로드일시, 올린사람 -->
                    <h4 class="media-heading">{{list.content_origin_name }}</h4>
                    <p class="by-author">{{list.content_reg_date}} </p>
                    <p class="by-author">{{list.content_reg_user}} </p>

                    

                  </div>

                </li>
              </ul>
            </div>

              <!-- video thumb 끝 -->
          </div>
          <!-- side-content 끝 -->
       </div> 
      <!-- content 끝 -->
    </div>
    <!-- 비디오 모달 끝 -->

   
  </div>
</template>


<script>
import { mapActions, mapState } from "vuex";
export default {
  components: {
  },
  
  props : [
    "idOfVideo",
    "videoList",
    "folderId"
  ],
   data() {
    return {
        video: {},
        // MediaTrackConstraints를 변수로 정의
        mediaContraint: {
            video: true
        },
        scr:"",
        vList:"",
        videoId :"",
        nextVideoId:"",
        videoModal: false,
        ids:[],
        fId : ""
    };
  },
  created() {
    this.videoId = this.idOfVideo;
    this.src = "/api/videos/video/" + this.videoId;
    this.getvideoList(this.videoId);
    this.fId = this.folderId;
    // this.vList = this.videoList;
    // this.vList = this.videoList;
    console.log(this.videoList)
  },
  computed: {
    ...mapState({
      parent: "root_folder",
    }),
  },
   methods: {
    ...mapActions([
      "GET_VIDEO",
      "DELETE_FILE",
      "GET_VIDEO_LIST",
      "SHARE_ITEMS",
    ]),
    
    openVideoNav() {
      document.getElementById("video_nav").style.display = "block";
      document.getElementById("image_nav").style.display = "block";
      // this.openshareModal = [];
      // event.target.reset();
    },
    close() {
      document.getElementById("video_nav").style.display = "none";
      document.getElementById("shareModal").style.display = "none";
      document.getElementById("infoModal").style.display = "none";
      document.getElementById("image_nav").style.display = "block";
      document.webkitExitFullscreen().style.display = "none";
    },
    roadImg(data) {
      const result = "data:image;base64," + data;
      return result;
    },

    // 삭제
      deleteFile() {
      console.log(this.idOfVideo);
      if(this.idOfVideo === this.videoList[this.videoList.length-1].content_id){
        this.nextVideoId = this.videoList[this.videoList.length-2].content_id;
      } else  {
        for(var i = 0; i < this.videoList.length-1; i++){
          console.log(this.videoList[i].content_id)
          console.log(this.idOfVideo)
          if(this.idOfVideo === this.videoList[i].content_id){
          console.log(this.idOfVideo === this.videoList[i].content_id)
            this.nextVideoId = this.videoList[i+1].content_id;
            console.log(this.nextVideoId + " : ----------------------------- : " + this.videoList[i+1].content_id)
          }
        }
      }
      this.DELETE_FILE([this.idOfVideo]).then((data) => {
        console.log(data);
        if (data == 1) {
          alert("파일 삭제에 성공하였습니다.");
          this.$emit("getVideo", this.nextVideoId)
          // this.videoList.content_id;
          console.log("===========next : " + this.nextVideoId)
                this.videoId = this.idOfVideo;
      console.log(this.nextVideoId + ' delete video method');
      this.src ="/api/videos/video/" + this.nextVideoId;
      console.log(this.src);
      var v1 = document.getElementById("video-div");
      // v1.parentNode.replaceChild(p);
      var p = '<video id="video"'
            + 'controls autoplay muted loop src="'+this.src+'"' 
            + ' type="video/mp4"'
            + 'style ="width :1040px; height : 600px;"'
           + '> </video>';
      v1.innerHTML = p;

        } 
      });
    },
    getvideoList(id){
      console.log("=================" + this.folderId)
      if(this.$route.params.album_id) {
        this.fId = this.$route.params.album_id;
        console.log(this.fId)
      } else if (this.$route.params.id) {
        this.fId = this.$route.params.id;
        console.log(this.fId)
      } else if(this.$route.path === '/ownDocumentBox'){
        this.fId = this.parent;
      } 
      console.log(this.fId)
      console.log(this.$route.params.id)
        this.GET_VIDEO_LIST({ folderId: this.fId, videoId: id }).then(
        (result) => {
          console.log(this.parent);
          console.log("video list result : " + result[0].content_id);
          this.vList = result;
        }
      );
    },
    // 다음동영상
    getVideoId(id){
      this.$emit("getVideo", id)
      // console.log('created' + this.videoList[0].content_id)
      // console.log('created' + this.vList[0].content_id)
      // console.log(id + ' get video method')
      // console.log(this.folderId)
      this.videoId = id;
      console.log(this.$route.params.id + ' get video method')
      this.src ="/api/videos/video/" + this.videoId;
      if (typeof this.$route.params.id === "undefined") {
        this.folderId = this.parent;
      } else {
        this.folderId = this.$route.params.id;
      }
      this.getvideoList(id)
      // this.GET_VIDEO_LIST({ folderId: this.folderId, videoId: id }).then(
      //   (result) => {
      //     console.log(this.parent);
      //     console.log("video list result : " + result[0].content_id);
      //     this.vList = result;
      //   }
      // );
      var v1 = document.getElementById("video-div");
      // v1.parentNode.replaceChild(p);
      var p = '<video id="video"'
            + 'controls autoplay muted loop src="'+this.src+'"' 
            + ' type="video/mp4"'
            + 'style ="width :1040px; height : 600px;"'
           + '> </video>';
      v1.innerHTML = p;
      // v1.  = p
    },
    // 공유
    shareItem() {
      console.log(this.idOfVideo + " : share item");
      this.ids.push(this.idOfVideo);
      this.SHARE_ITEMS(this.ids).then((data) => {
        if(data == 1){
          console.log(data)
          alert("공유에 성공했습니다.");
          this.ids = [];
        } else {
          alert("공유에 실패했습니다.");
          this.ids = [];
        }
      }
      )
    },
  }
};
</script>



<style scoped>
/* 큰 모달 */
.overlay {
  height: 100%;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgb(0, 0, 0);
  z-index: 100;
}

/* 비디오 */
.video-container {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  top: 180px;
  left : 100px;
}

.footer-info{
  position: absolute;
  display: flex;
  /* background-color: #e9ecef; */
  color: #ffffff;
  font-size: 25px;
  font-style: bold;
  justify-content: center;
  align-items: center;
  bottom: -70px;
  left : 10px;
}


/* 다음동영상 */
.container-title{
  position: absolute;
  display: flex;
  font-size: 18px;
  font-style: bold;
  justify-content: center;
  align-items: center;
  top: 120px;
  left : 1140px;
  height : 600px;
  overflow-y: scroll;
  display: inline-block;
  white-space:nowrap;
  margin-right : 150px;
  /* margin-left : -50px; */
}

.video-thumb {
  position: absolute;
  display: flex;
  font-size: 18px;
  font-style: bold;
  justify-content: center;
  align-items: center;
  top: 180px;
  left : 1150px;
  height : 600px;
  overflow-y: scroll;
  display: inline-block;
  /* white-space:nowrap; */
  /* margin-left : -50px; */
}


.by-upload {
  /* font-style: italic; */
  font-size: 15px;
   line-height: 1.5;
   /* color: #d3d3d3; */
  color : #fff;
}


.media-body {
  font-size : 14px;
  color : #d3d3d3;
  /* float : right; */
  margin-left : 10px;
  /* margin-right: ; */
}

.by-author {
  /* font-style: italic; */
  font-size: 14px;
   line-height: 1.5;
   color: #d3d3d3;
  /* color : #fff; */
  line-height: 1.8;
}


.footer-info {
  /* font-size : 14px; */
  font-size: 20px;
  color : #ffffff;
  /* top : 650px; */
  line-height: 1.5;
}

 .media {
   padding-bottom: 0.5em;
   overflow: hidden;
   /* border-bottom: 1px solid #e8e8e8; */
}


.overlay a {
  padding: 8px;
  text-decoration: none;
  font-size: 20px;
  color: #ffffff;
  display: inline;
  transition: 0.3s;
}

.v_btn_close {
  position: absolute;
  top: 7px;
  cursor: pointer;
  left: 10px;
}

.v_task {
  display: inline-block; /* 한줄로 */
  position: absolute;
  top: 15px;
  right: 5px;
  font-size: 18px;
  cursor: pointer;
}

.li, .ul {
  list-style: none;
  float: left;
  margin-bottom: 10px;
}

/* SCROLL */
::-webkit-scrollbar { width: 10px; height: 10px;}
::-webkit-scrollbar-track { background-color: #000; }
::-webkit-scrollbar-thumb { 
   border-radius: 20px;
    /* background: linear-gradient(to bottom, #a6c4c7, #a6c4c7);  */
    background: linear-gradient(to bottom, #000, #000); 
}
::-webkit-scrollbar-button { display: none; }

.dropdown {
  /* position: relative; */
  display: inline-block;
  border-radius: 5px;
  /* float: right; */
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 150px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
  border-radius: 5px;
  right: 0;
}

.dropdown-content a {
  font-size: 12px;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  border-radius: 5px;
}

.dropdown-content a:hover {
   overflow: hidden;
  background-color: #e9ecef;
  border-radius: 5px;
}

.dropdown:hover .dropdown-content {
   overflow: hidden;
  display: block;
}

.vvvvvv{
  z-index: 999999;
}


</style>