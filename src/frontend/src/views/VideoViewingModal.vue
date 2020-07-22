<template>
  <div>

    <!-- 비디오 모달 -->
    <div class="overlay" id="video_nav" tabindex="1" role="dialog">
      <!-- header -->
      <div class="header">
        <!-- 나가기 -->
        <!-- <a class="v_btn_close" @click="closeVideoNav()" style="color:#fff;  font-size: 14px;">나가기</a> -->
          <a class="v_btn_close" id="reset" @click="closeVideoNav()" style="color:#fff; font-size: 15px; z-index :10; ">
            나가기
          </a>

        <div class="v_task">
          <!-- 공유 -->
          <a
            class="share_modal"
            id="shareBtn"
            @click="openShareModal()"
            title="공유"
            style="margin-right:3px"
          >
            <img src="@/assets/image/v_task_share.png" height="15px" />
          </a>

          <!-- 정보 -->
          <a
            class="info_modal"
            id="infoBtn"
            @click="openInfoModal()"
            title="정보"
            style="margin-right:3px; top : 0.5px"
          >
            <img src="@/assets/image/v_task_info.png" height="15.5px" />
          </a>

          <!-- 삭제 -->
          <a class="v_ta_trash" @click="aaa" title="휴지통">
            <img src="@/assets/image/v_task_delete.png" height="15px" />
          </a>

          <!-- 더보기 -->
          <div class="dropdown">
            <!-- <button class="dropbtn">Dropdown</button> -->
            <a class="dropbtn" title="더보기" style="left:-3px; top:1px;">
              <img src="@/assets/image/v_task_more.png" height="15px" />
            </a>
            <div class="dropdown-content" style="float : right;">
              <a class="publicfolder">공유 문서함 추가</a>
              <a class="publicalbum">공유 앨범 추가</a>
            </div>
          </div>
          <!-- 더보기 끝 -->
        </div>
        <!-- v_task 끝 -->
      </div>
      <!-- header 끝 -->

      <!-- content -->
      <div class="content">
        <div class="video-container">
          <!-- <video controls autoplay muted loop :src="this.idOfVideo" type="video/mp4"></video> -->
          <video width="320" height="240" autoplay="autoplay" controls>
            <source :src="this.idOfVideo" type="video/mp4">
          </video>

          <!-- <video id="video" controls muted loop autoplay preload="none" style ="height: 640px"> -->
            <!-- <source :src="this.idOfVideo" type="video/mp4"/> -->
            <!-- <source id="mp4" :src="this.idOfVideo" type="video/mp4" />
            <source
              id="webm"
              :src="this.idOfVideo"
              type="video/webm"
            />
            <source id="ogv" :src="this.idOfVideo" type="video/ogg" /> -->
          <!-- </video> -->
        </div>

          <div class ="side-contanier">
              <a class="container-title">
                  <b>다음동영상</b>
              </a>

              <div class ="video-thumb">
                <ul class="media-list main-list ypt_thumbs">
                  <li class="media">
                    <a class="pull-left" href="#">
                      <img src="@/assets/image/sample.jpg" height="100px" />
                    </a>
                    <div class="media-body">
                      <!-- 파일명, 업로드일시, 올린사람 -->
                      <h4 class="media-heading">Lorem ipsum dolor asit ame.mp4</h4>
                      <p class="by-author">2020.05.01 20:17:56</p>
                      <p class="by-author">By 최지은</p>
                    </div>
                  </li>

                  <li class="media">
                    <a class="pull-left" href="#">
                      <img src="@/assets/image/sample.jpg" height="100px" />
                    </a>
                    <div class="media-body">
                      <h4 class="media-heading">Lorem ipsum dolor asit ame.mp4</h4>
                      <p class="by-author">2020.05.01 20:17:56</p>
                      <p class="by-author">By 최지은</p>
                    </div>
                  </li>

                  <li class="media">
                    <a class="pull-left" href="#">
                      <img src="@/assets/image/sample.jpg" height="100px" />
                    </a>
                    <div class="media-body">
                      <h4 class="media-heading">Lorem ipsum dolor asit ame.mp4</h4>
                      <p class="by-author">2020.05.01 20:17:56</p>
                      <p class="by-author">By 최지은</p>
                    </div>
                  </li>

                  <li class="media">
                    <a class="pull-left" href="#">
                      <img src="@/assets/image/sample.jpg" height="100px" />
                    </a>
                    <div class="media-body">
                      <h4 class="media-heading">Lorem ipsum dolor asit ame.mp4</h4>
                      <p class="by-author">2020.05.01 20:17:56</p>
                      <p class="by-author">By 최지은</p>
                    </div>
                  </li>

                  <li class="media">
                    <a class="pull-left" href="#">
                      <img src="@/assets/image/sample.jpg" height="100px" />
                    </a>
                    <div class="media-body">
                      <h4 class="media-heading">Lorem ipsum dolor asit ame.mp4</h4>
                      <p class="by-author">2020.05.01 20:17:56</p>
                      <p class="by-author">By 최지은</p>
                    </div>
                  </li>
                </ul>
              </div>

              <!-- thumb-list 끝 -->
          </div>
          <!-- side-content 끝 -->
       </div> 
      <!-- contrent 끝 -->

      <!-- footer -->
      <!-- <div class="footer">
        <input type="checkbox" name="toggle" id="toggle" />
        <label for="toggle"></label>

        <div class="toggle-container"> -->
          <!-- thumb list -->
          <!-- <div class="v_photo_list">
            <ul class="image_list">
              <li class="image_item">
                <a>
                  <img src="https://www.w3schools.com/images/w3schools_green.jpg" />
                  <span class="mask_sel" />
                </a>
              </li>

              <li class="image_item">
                <a>
                  <img src="https://www.w3schools.com/images/w3schools_green.jpg" />
                  <span class="mask_sel" />
                </a>
              </li>

              <li class="image_item">
                <a>
                  <img src="https://www.w3schools.com/images/w3schools_green.jpg" />
                  <span class="mask_sel" />
                </a>
              </li>
            </ul>
          </div>
        </div> -->
        <!-- thumb list-->
      <!-- </div> -->
      <!--footer 끝-->
    </div>
    <!-- 큰 모달 끝 -->

    <!-- 공유 모달 -->
    <div id="shareModal" class="sharemodal">
      <div class="share-modal-content">
        <div class="share-modal-header">
          <a style="font-size : 16px">공유 URL 생성</a>
          <span class="closesharemodal" @click="closeShareModal()">&times;</span>
        </div>

        <div class="share-modal-body">
          <button type="button" class="btn_copy" data-dismiss="modal">복사</button>
          <p>URL 영역</p>
        </div>

        <div class="share-modal-footer"></div>
      </div>
    </div>
    <!-- 공유 모달 끝 -->

     <!-- 정보 모달 -->
    <div id="infoModal" class="infomodal">
      <div class="info-modal-content">
        <div class="info-modal-header">
          <a style="font-size : 16px">정보</a>
          <span class="closeinfomodal" @click="closeInfoModal()">&times;</span>
        </div>

        <div class="info-modal-body">
          <p style="margin-bottom : 5px;  color : #353535;">파일 정보</p>
          <div class="info" style="font-size:13px; margin-left : 5px">
            <span>해상도</span>
            <a style="font-color : #000">3024 X 3024</a>
            <br />
            <span>파일크기</span>
            <br />
            <span style="margin-bottom : 5px">업로드일시</span>
          </div>
        </div>

        <div class="info-modal-footer">
          <p style="margin-bottom : 5px;  color : #353535;">폴더 경로</p>
          <div class="info" style="font-size:13px; margin-left : 5px">
            <a>개인문서함 /.../...</a>
          </div>
        </div>
      </div>
    </div>
    <!-- 정보 모달 끝 -->
  </div>
</template>


<script>
export default {
  props : [
    "idOfVideo",
  ],
  data() {
    return {

    };
  },

  created() {
      console.log(this.idOfVideo)
  },

  methods: {
    //모달
    openVideoNav() {
      console.log("동영상모달열어!");
      document.getElementById("video_nav").style.display = "block";
      // this.openshareModal = [];
      // event.target.reset();
    },
    closeVideoNav() {
      console.log("동영상모달닫아!");
      document.getElementById("video_nav").style.display = "none";
      document.getElementById("shareModal").style.display = "none";
      document.getElementById("infoModal").style.display = "none";
      document.webkitExitFullscreen().style.display = "none";
    },
    openShareModal() {
      console.log("공유모달");
      document.getElementById("shareModal").style.display = "block";
    },
    closeShareModal() {
      console.log("공유모달 닫아");
      document.getElementById("shareModal").style.display = "none";
      document.getElementById("infoModal").style.display = "none";
    },
    openInfoModal() {
      console.log("정보모달");
      document.getElementById("infoModal").style.display = "block";
    },
    closeInfoModal() {
      console.log("정보모달 닫아");
      document.getElementById("shareModal").style.display = "none";
      document.getElementById("infoModal").style.display = "none";
      document.getElementById("shareModal").style.display = "none";
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
  left : 150px;
}

/* 다음동영상 */
.container-title{
  position: absolute;
  display: flex;
  /* background-color: #e9ecef; */
  color: #ffffff;
  font-size: 18px;
  font-style: bold;
  justify-content: center;
  align-items: center;
  top: 120px;
  right : 440px;
}

.video-thumb {
  position: absolute;
  display: flex;
  font-size: 18px;
  font-style: bold;
  justify-content: center;
  align-items: center;
  top: 185px;
  left : 1300px;
}

.by-author {
   font-style: italic;
  font-size: 14px;
	line-height: 1.5;
	color: #aab6aa;
}

.media-body {
  font-size : 14px;
  color : #ffffff;
  float : right;
}

 .media {
	padding-bottom: 0.7em;
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

/* 공유 모달 */
.p {
  margin-bottom: 20px;
}

.sharemodal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 500; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  /* background-color: rgba(0, 0, 0, 0.4);  */
}

.sharemodal:target:before {
  display: none;
}
.sharemodal:before {
  content: "";
  display: block;
  background: rgba(0, 0, 0, 0.6);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 10;
}

.share-modal-content {
  background: #fefefe;
  border: #333333 solid 1px;
  border-radius: 5px;
  margin-left: -200px;
  position: fixed;
  left: 50%;
  z-index: 11;
  width: 360px;
  -webkit-transform: translate(0, 0);
  -ms-transform: translate(0, 0);
  transform: translate(0, 0);
  -webkit-transition: -webkit-transform 0.3s ease-out;
  -moz-transition: -moz-transform 0.3s ease-out;
  -o-transition: -o-transform 0.3s ease-out;
  transition: transform 0.3s ease-out;
  top: 20%;
}

.sharemodal:target .share-modal-content {
  top: -100%;
  -webkit-transform: translate(0, -500%);
  -ms-transform: translate(0, -500%);
  transform: translate(0, -500%);
}

.closesharemodal {
  color: #353535;
  font-size: 25px;
  text-decoration: none;
  position: absolute;
  right: 10px;
  top: 0;
  cursor: pointer;
}

.btn_copy {
  background: #474346;
  border: solid 1px;
  border-radius: 3px;
  color: #fff;
  display: inline-block;
  font-size: 14px;
  padding: 8px 15px;
  text-decoration: none;
  text-align: center;
  min-width: 60px;
  position: relative;
  transition: color 0.1s ease;
}

.share-modal-header {
  text-align: center;
  float: center;
  font-size: 20px;
  padding: 10px;
  border-bottom: #eeeeee solid 1px;
}

.share-modal-body {
  padding: 20px;
}
.share-modal-header {
  padding: 10px 20px;
}

.share-modal-footer {
  text-align: right;
}

/* 정보 모달 */
.p {
  margin-bottom: 20px;
}
.infomodal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 500; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
}

.infomodal:target:before {
  display: none;
}
.infomodal:before {
  content: "";
  display: block;
  background: rgba(0, 0, 0, 0.6);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 10;
}

.info-modal-content {
  color: #353535;
  font-size: 25px;
  text-decoration: none;
  position: absolute;
  right: 10px;
  top: 0;
  cursor: pointer;
}

.infomodal:target .info-modal-content {
  top: -100%;
  -webkit-transform: translate(0, -500%);
  -ms-transform: translate(0, -500%);
  transform: translate(0, -500%);
}

.closeinfomodal {
  color: #aaaaaa;
  font-size: 30px;
  text-decoration: none;
  position: absolute;
  right: 5px;
  top: 0;
  cursor: pointer;
}

.btn_copy {
  background: #474346;
  border: solid 1px;
  border-radius: 3px;
  color: #fff;
  display: inline-block;
  font-size: 14px;
  padding: 8px 15px;
  text-decoration: none;
  text-align: center;
  min-width: 60px;
  position: relative;
  transition: color 0.1s ease;
  float: right;
  bottom: 5px;
}

.info-modal-header {
  text-align: center;
  float: center;
  font-size: 20px;
  padding: 10px;
  border-bottom: #eeeeee solid 1px;
}

.info-modal-body {
  padding: 20px;
  padding: 10px 20px;
  border-bottom: #eeeeee solid 1px;
}
.info-modal-header {
  padding: 10px 20px;
}

.info-modal-footer {
  border-top: #eeeeee solid 1px;
  padding: 20px;
  padding: 10px 20px;
}

/* 드롭다운 */
.dropbtn {
  /* background-color: #4CAF50; */
  position: relative;
  color: white;
  padding: 40px;
  border: none;
  cursor: pointer;
  top: 0px;
}

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
  background-color: #e9ecef;
  border-radius: 5px;
}

.dropdown:hover .dropdown-content {
  display: block;
}


</style>