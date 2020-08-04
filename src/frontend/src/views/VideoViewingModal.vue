<template>
  <div>

    <!-- 비디오 모달 -->
    <div class="overlay" id="video_nav" tabindex="1" role="dialog">
      <!-- header -->
      <div class="header">
        <!-- 나가기 -->
        <!-- <a class="v_btn_close" @click="closeVideoNav()" style="color:#fff;  font-size: 14px;">나가기</a> -->
          <a class="v_btn_close" @click="$emit('close')" style="color:#fff; font-size: 15px; z-index :10;">
            나가기
          </a>


        <div class="v_task">
    
          <!-- 삭제 -->
          <a 
            class="v_ta_trash"
            @click="deleteFile" 
            title="휴지통"
          >
            <img src="@/assets/image/v_task_delete.png" height="15px" />
          </a>

          <!-- 공유 문서함 추가  -->
          <a class="v_ta_share_doc" title="공유문서함">
            <img src="@/assets/image/v_task_documentshare.png" height="16px"  style="margin-right:3px; top:1px;"/>
          </a>

        </div>
        <!-- v_task 끝 -->
      </div>
      <!-- header 끝 -->

      <!-- content -->
      <div class="content">
        <div class="video-container">
          <video 
            controls autoplay muted loop :src="this.src" 
            type="video/mp4"
            style ="width :1040px; height : 600px;"
           >
          </video>
            <!-- <div class="footer-info"  style="margin-right : 20px;">
              <h4 class="media-heading"  style="margin-right : 20px;">{{this.idOfVideo}}</h4>
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
                  v-for="list in videoList"
                  v-bind:key="list.content_id"
                >
                    <img
                      :src="roadImg(list.content)"
                      @click="getVideoId(list.content_id)"
                      height="100px"
                      
                    />
                  
                    <div class="media-body" style="margin-right : 10px;"> 
                    <!-- 파일명, 업로드일시, 올린사람 -->
                    <h4 class="media-heading">{{idOfVideo.content_origin_name }}</h4>
                    <p class="by-author">{{videoList.content_reg_date}}</p>
                    <p class="by-author">{{videoList.content_reg_user}}</p>
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
export default {
  props : [
    "idOfVideo",
    "videoList",
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
        videoId :""
    };
  },
  created() {
    this.videoId = this.idOfVideo;
    this.src = "/api/videos/video/" + this.videoId;
    this.vList = this.videoList;
    console.log(this.videoList)
  },
  methods: {
    //모달
    getVideoId(id){
      this.videoId = id;
      this.src = "/api/videos/video/" + this.videoId;
    },
    openVideoNav() {
      console.log("동영상모달열어!");
      document.getElementById("video_nav").style.display = "block";
      // this.openshareModal = [];
      // event.target.reset();
    },
    close() {
      console.log("동영상모달닫아!");
      document.getElementById("video_nav").style.display = "none";
      document.getElementById("shareModal").style.display = "none";
      document.getElementById("infoModal").style.display = "none";
      document.webkitExitFullscreen().style.display = "none";
    },
    roadImg(data) {
      const result = "data:image;base64," + data;
      return result;
    },



    // 삭제
  
  
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
  left : 150px;
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
  /* background-color: #e9ecef; */
  color: #ffffff;
  font-size: 16px;
  /* font-style: bold; */
  justify-content: center;
  align-items: center;
  top: 120px;
  right : 550px;
}

.video-thumb {
  position: absolute;
  display: flex;
  font-size: 18px;
  font-style: bold;
  justify-content: center;
  align-items: center;
  top: 175px;
  left : 1250px;
  height : 600px;
  overflow-y: scroll;
  display: inline-block;
  white-space:nowrap;
  margin-right : 150px;
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
  float : right;
}

.by-author {
  /* font-style: italic; */
  font-size: 14px;
   line-height: 1.5;
   color: #d3d3d3;
  /* color : #fff; */
  line-height: 1.5;
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
  top: 10px;
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

.ul, li {
  list-style: none;
  float: left;
  margin-bottom: 10px;
}

/* SCROLL */
::-webkit-scrollbar { width: 10px; height: 10px;}
::-webkit-scrollbar-track { background-color: #000; }
::-webkit-scrollbar-thumb { 
   border-radius: 20px;
    background: linear-gradient(to bottom, #a6c4c7, #a6c4c7); 
}
::-webkit-scrollbar-button { display: none; }



</style>