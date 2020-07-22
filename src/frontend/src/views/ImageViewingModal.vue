<template>
  <div>
    <!-- <span @click="openImageNav" style="cursor: pointer;">이미지</span> -->

    <!-- 이미지 모달 -->
    <div class="overlay" id="image_nav" tabindex="1" role="dialog">
      <!-- header -->
      <div class="header">
        <!-- 나가기 -->
          <a class="v_btn_close" id="reset" @click="closeImageNav()" style="color:#fff; font-size: 15px; z-index :10; ">
            나가기
          </a>

        <div class="v_task">
        
          <!-- 확대/축소 -->
          <!-- <div class="zoom_wrap">       -->
          <a class="zoom-in" id="in" title="확대" @click="zoomIn()" style="margin-right:3px; cursor: zoom-in">
            <img src="@/assets/image/v_task_zoomin.png" height="15.5px" />
          </a>
          <a class="zoom-out" id="out" title="축소" @click="zoomOut()" style="margin-right:3px; cursor: zoom-out">
            <img src="@/assets/image/v_task_zoomout.png" height="15.5px" />
          </a>
          <!-- </div> -->

          <!-- 회전 -->
          <a
            class="v_ta_rotate"
            @click="rotate()"
            title="회전"
            style="margin-right:3px; top : 0.5px;"
          >
            <img src="@/assets/image/v_task_rotate.png" height="16.7px"  style="top : 1px;" />
          </a>

          <!-- 돋보기 -->
          <a 
            class="v_ta_magnifying" 
            @clisk="magnify()"
            title="돋보기" 
            style="top : 1.5px"
          >
            <img src="@/assets/image/v_task_magnify.png" height="15px" />
          </a>

          <!-- 공유 -->
          <a
            class="share_modal"
            id="shareBtn"
            @click="openShareModal()"
            title="공유"
            style="margin-right:3px"
          >
            <img src="@/assets/image/v_task_share.png" height="15px" style="top : 1px;"/>
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
          <a class="v_ta_trash" title="휴지통">
            <img src="@/assets/image/v_task_delete.png" height="15px" />
          </a>

          <!-- 더보기 -->
          <div class="dropdown">
            <!-- <button class="dropbtn">Dropdown</button> -->
            <a class="dropbtn" title="더보기" style="left:-3px; top:1px;">
              <img src="@/assets/image/v_task_more.png" height="15px" />
            </a>
            <div class="dropdown-content" style="float:right;">
              <a
                class="slider_option"
                onclick="var el = document.getElementById('element'); el.webkitRequestFullscreen();">
                슬라이드쇼
              </a>
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
      <div class="content" id="page"> 
         <div class="image-container" id="imgContainer">
          <div v-ifelse>
                <img
                  :src="roadImg(this.idOfImage.content)"
                  class="image" 
                  id="img"
                >
          </div>
        </div>

        <div class="slideshow">
          <a class="v_btn_prev" @click="showDivs()"  title="이전사진">
            <img src="@/assets/image/view_prev.png" height="30px" />
          </a>
          <a class="v_btn_next" @click="plusDivs()"  title="다음사진">
            <img src="@/assets/image/view_next.png" height="30px" />
          </a>
        </div>
        <!-- slideshow 끝-->
      </div>
      <!-- contrent 끝 -->

      <!-- footer -->
      <div class="footer">
        <input type="checkbox" name="toggle" id="toggle" />
        <label for="toggle">
             <!-- <span style="margin-left : 15px;"><strong>{{currentIndex + 1}} / {{bannerList.length}}</strong></span> -->
        </label>

        <div class="toggle-container">
          <!-- thumb list -->
          <div class="v_photo_list">
            <ul class="image_list">
              <!-- <li class="image_item">
                <a>
                  <img src="https://www.w3schools.com/images/w3schools_green.jpg" />
                  <span class="mask_sel" />
                </a>
              </li> -->
              <!-- <li class="image_item">
               <img
                  :src="roadImg(this.idOfImage.content)"
                  class="image" 
                  id="img"
                  height="80px"
                > -->
              <li 
                style="display : inline-block; padding :5px"
                v-for="list in this.imageList"
                v-bind:key="list.content_id"
                class="li"
                @click="imaginate(list.content_id)"
              >

                  <img
                    :src="roadImg(list.content)"
                    height="80px"
                   
                  />

              </li>

            </ul>
          </div>
        </div>
        <!-- thumb list-->
      </div>
      <!--footer 끝-->
    </div>
    <!-- 이미지 모달 끝 -->

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

    <!-- 더보기 slide -->
    <div class="slide_container" style="overflow:hidden">
      <div class="element" id="element" >
          <div class = "slidein" id="wrap" >
             <!-- <div id="wrapp" > -->
                
                  <!-- <ul style="  list-style-type: none;" z-index:10>
                    <li v-for="(banner, index) in bannerList" :key="index" :class="{ on: index === currentIndex }">
                      <h2>test</h2>
                    </li>
                  </ul> -->
              
                    <a @click="roll('prev')" title="이전" style="margin-right : 15px">
                       <img src="@/assets/image/view_slideshow_prev.png" height="15px" />
                    </a>

                    <a v-if="!playing" @click="play" title="재생" style="margin-right : 15px">
                       <img src="@/assets/image/view_slideshow_play.png" height="16px" />
                     </a>

                    <a v-if="playing" @click="pause" title="일시정지" style="margin-right : 15px"> 
                       <img src="@/assets/image/view_slideshow_pause.png" height="17px" />  
                    </a>

                    <a @click="roll('next')" title="다음" style="margin-right : 15px">
                       <img src="@/assets/image/view_slideshow_next.png" height="15px" />
                    </a>

                    <a class="slide_playstop" @click="closeFullscreen()" title="슬라이드쇼 마침">
                    <img src="@/assets/image/view_slideshow_back.png" height="15px" />
                  </a>
             </div>
            
            <!-- 테스트 후 삭제할 코드 -->
             <!-- <span style="margin-left : 15px;"><strong>{{currentIndex + 1}} / {{bannerList.length}}</strong></span> -->

            <!-- 이미지 -->
            <div class ="slideContentImage">
            <ul style="  list-style-type: none;" z-index:10 > 
              <li v-for="(list, index) in imageList" :key="index" :class="{ on: index === currentIndex }">
                <img :src="roadImg(list.content)" :alt="index"/>
              </li>
            </ul>
            </div>


          </div>
      </div>
    <!-- 더보기 slide 끝 -->
  </div>
</template>


<script>
// import VueMagnifier from './test.vue' 
// import Cropper from '../utils/cropper.js'
import { mapActions } from "vuex";

export default {
  el: "#wrap",
  
  components : {
    // VueMagnifier
  },
  props : [
    "idOfImage",
    "imageList",
  ],
  data() {
    return {
      slideIndex: 1,
      // rotate : false,
      // zoomIn:undefined,
      // zoomOut:undefined

      src:'../assets/image/sample.jpg',
      srcLarge:'../assets/image/sample.jpg',
     
     // 더보기 슬라이드 이미지
        playing: false,
        bannerList: [
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
          {imgsrc : this.roadImg(this.idOfImage.content) },       
      ],
      currentIndex: 0,
      timer : null,
      imageData:"",
      imgList : [],
      // content_id : ""
    };
  },
  computed:{
    computedImageData : {
      get(){
        console.log(this.idOfImage + " : computed");
        return this.idOfImage;
      },
      set(imageId){
        console.log("imageId" , imageId)
        this.$emit("getImg", imageId);
      }
    },
    computedImagelist :{ 
      get(){
        return this.imageList;
      }
    },
    isPrevDisabled(){
      return this.idOfImage.content_id === this.imageList[0].content_id;
    },
    isNextDisabled(){
      return this.idOfImage.content_id === this.imageList[this.imageList.length-1].content_id;
    }
  },
  created() {
    // let options = {
    //   holderNode: '.js-cropper-holder',
    //   imageNode: '.js-cropper-image-box',
    //   thumbNode: '.js-cropper-image-thumb',
    //   spinner: '.js-cropper-image-spinner',
    //   zoomIn: '.js-cropper-image-zoom-in',
    //   zoomOut: '.js-cropper-image-zoom-out',
    //   imgSrc: 'https://camo.mybb.com/e01de90be6012adc1b1701dba899491a9348ae79/687474703a2f2f7777772e6a71756572797363726970742e6e65742f696d616765732f53696d706c6573742d526573706f6e736976652d6a51756572792d496d6167652d4c69676874626f782d506c7567696e2d73696d706c652d6c69676874626f782e6a7067'
    //  }
    //   const cropper = new Cropper(options);
    //   cropper.show();
    //  this.zoomIn = document.getElementById("in")
    //  this.zoomOut = document.getElementById("out")
  },

  methods: {
    ...mapActions([
      "GET_IMAGE",
    ]),
    imaginate(imaginate){
      console.log("imaginate-----------")
      console.log(imaginate)
      this.computedImageData=this.computedImageData.content_id= imaginate;
    },
    //모달
    openImageNav() {
      console.log("이미지모달");
      console.log(this.idOfImage);
      document.getElementById("image_nav").style.display = "block";
      // this.openshareModal = [];
      // event.target.reset();
    },
    closeImageNav() {
      console.log("이미지모달 닫아");
      document.getElementById("image_nav").style.display = "none";
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
      document.getElementById("infoModal").style.display = "none";
      document.getElementById("shareModal").style.display = "none";
    },

    // 회전
    rotate() {
      console.log("회전");
      const container = document.querySelector(".image-container");
      // Probably select current image here:
      const image = container.querySelector(".image");
      const width = image.offsetWidth;
      const height = image.offsetHeight;
      console.log("image", image);
      console.log("container", container);
      // this should be in data attributes or extracted from deg
      const currentRotateCycle = parseInt(
        getComputedStyle(image).getPropertyValue("--current-rotate-cycle")
      );

      if (currentRotateCycle % 2 === 0) {
        image.style.setProperty(
          "--current-scale",
          "scale(" + height / width + ")"
        );
      } else {
        image.style.setProperty("--current-scale", "scale(1)");
      }

      if (currentRotateCycle === 3) {
        image.style.setProperty("--current-rotate-cycle", 0);
        image.style.setProperty("--current-rotate", "rotate(0deg)");
      } else {
        const newRotateCycle = currentRotateCycle + 1;
        image.style.setProperty("--current-rotate-cycle", newRotateCycle);
        image.style.setProperty(
          "--current-rotate",
          "rotate(" + newRotateCycle * 90 + "deg)"
        );
      }
      return false;
    },

    // 확대/ 축소
    zoomIn() {
      console.log("zoomIn");
      let myImg = document.getElementById("img");
      let imgCurrWidth = myImg.clientWidth;
      // let maxWidth = this.imgBox.clientWidth;
      if (imgCurrWidth < 1000) {
        myImg.style.width = `${imgCurrWidth + 100}px`;
      } else {
        return false;
      }
    },
    zoomOut() {
      console.log("zoomOut");
      let myImg = document.getElementById("img");
      let imgCurrWidth = myImg.clientWidth;
      // let maxWidth = this.imgBox.clientWidth;
      if (imgCurrWidth > 100) {
        myImg.style.width = `${imgCurrWidth - 100}px`;
      } else {
        return false;
      }
    },

    // 돋보기 
    // magnify() {
    //   console.log("magnifying...")

    // },


    // view main 슬라이드쇼
    plusDivs() {
      // console.log("slide_next");
      // this.showDivs((this.slideIndex += n));
      if(this.idOfImage.content_id !== this.imageList[this.imageList.length-1].content_id){
        for(var i = 0; i < this.imageList.length-1; i++){
          if(this.imageList[i].content_id === this.idOfImage.content_id){
            this.computedImageData = this.imageList[i+1].content_id;
            break;
          }
        }
        // this.computedImageData=this.computedImageData.content_id + 1;
      } 
      // this.GET_IMAGE({ image_id: this.idOfImage.content_id + 1 }).then((data) => {
      //   console.log(data);
      //   this.idOfImage = data;
      // });
    },
    showDivs() {
      console.log(this.computedImageData.content_id - " : minus");
      if(this.idOfImage.content_id !== this.imageList[0].content_id){
        for(var i = this.imageList.length-1; i > 0; i--){
          if(this.imageList[i].content_id === this.idOfImage.content_id){
            this.computedImageData = this.imageList[i-1].content_id;
            break;
          }
        }
      }
      // var i;
      // var x = document.getElementsByClassName("image");
      // if (n > x.length) {
      //   this.slideIndex = 1;
      // }
      // if (n < 1) {
      //   this.slideIndex = x.length;
      // }
      // for (i = 0; i < x.length; i++) {
      //   x[i].style.display = "none";
      // }
      // x[this.slideIndex - 1].style.display = "block";
    },


    //  더보기 슬라이드쇼
    closeFullscreen() {
      if(document.exitFullscreen) {
        document.exitFullscreen();
      }else if (document.webkitExitFullscreen) {
        document.webkitExitFullscreen();
      }
    },

    setAutoRoll() {
      let vueSelf = this;
      this.timer = setInterval(function() {
        vueSelf.addIndex();
      }, 2000);
    },
    addIndex() {
      let newIndex = this.currentIndex + 1;
      this.currentIndex = newIndex === this.bannerList.length ? 0 : newIndex;
    },
    roll(direction) {
      let diff = direction === "prev" ? -1 : 1;
      this.currentIndex = this.getTargetIndex(diff);
      if(this.playing) {
        clearInterval(this.timer);
        this.setAutoRoll();
      }
    },
    play() {
      this.setAutoRoll();
      this.playing = true;
    },
    pause() {
      clearInterval(this.timer);
      this.playing = false;
    },
    getTargetIndex(diff) {
      let length = this.bannerList.length;
      let index = this.currentIndex + diff;
      if (index === -1) {
        return length - 1;
      }
      if (index === length) {
        return 0;
      }
      return index;
    },

    // closeFullscreen() {
    //   if (document.exitFullscreen) {
    //     document.exitFullscreen();
    //   } else if (document.webkitExitFullscreen) {
    //     /* Chrome, Safari and Opera */
    //     document.webkitExitFullscreen();
    //   }
    // },

    // plusDivsSlide(n) {
    //   console.log("더보기 슬라이드 ");
    //   this.showDivs((this.slideIndex += n));
    // },
    // showDivsSlide(n) {
    //   console.log("더보기 슬라이드 ");
    //   var i;
    //   var x = document.getElementsByClassName("image");
    //   if (n > x.length) {
    //     this.slideIndex = 1;
    //   }
    //   if (n < 1) {
    //     this.slideIndex = x.length;
    //   }
    //   for (i = 0; i < x.length; i++) {
    //     x[i].style.display = "none";
    //   }
    //   x[this.slideIndex - 1].style.display = "block";
    // }

  // base 64
    roadImg(data) {
      console.log("idOfImage", this.idOfImage.content)
      const result = "data:image;base64," + data;
      return result;
    },
    
  }
};
</script>


<style scoped>
/* @import "./assets/css/reset.css"; */

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

/* 이미지 */
.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-container:before {
  content: "";
  display: block;
}

.image {
  --current-rotate-cycle: 0;
  --current-rotate: rotate(0deg);
  --current-scale: scale(1);
  transform: var(--current-rotate) var(--current-scale) translate(-50%, -50%);
  transform-origin: 0 0;
  position: absolute;
  /* align: center; */
  left: 50%;
  /* width: 400px; */
  /* transform: translate(0, 0); */
  top: 50%;
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
  z-index: 1000;
}

/* .overlay .momodal {
  position: absolute;
  top: 40px;
  right : 245px;
  font-size: 18px;
  cursor: pointer;
} */

/* footer */
.toggle-container {
  background: rgba(0, 0, 0, 0.8); /*  */
  color: #fff;
  position: fixed;
  bottom: -200px; /* 접어두기 동작 수행*/
  left: 0;
  width: 100%;
  height: 100px;
  /* padding: 20px; */
  /* transition: top 300ms cubic-bezier(0.17, 0.04, 0.03, 0.94); */
  overflow: hidden;
  display: inline-block;
  /* box-sizing: border-box; */
}

#toggle {
  position: absolute;
  cursor: pointer;
  left: -100%;
  color : white;
}

#toggle + label {
  font-style: italic;
  z-index: 600;
  position: absolute;
  bottom: 15px;
  right: 15px;
  padding: 10px;
  /* background: rgba(0, 0, 0, 0.6); */
  background: rgba(0, 0, 0, 0.6);
  width: 100px;
  /*   border-radius: 3px; */
  padding: 8px 10px;
  color: #fff;
  /*   line-height:20px; */
  font-size: 18px;
  text-align: center;
  /*   -webkit-font-smoothing: antialiased; */
  cursor: pointer;
  /* transition:all 500ms ease; */
}

#toggle + label:after {
  content: "on";
  background:url('../assets/image/view_next.png');
}

.container {
  transition: margin 300ms cubic-bezier(0.17, 0.04, 0.03, 0.94);
  padding: 3em 3em;
}

#toggle:checked ~ .toggle-container {
  bottom: 0px;
}

#toggle:checked ~ .container {
  margin-bottom: 150px;
}

#toggle:checked + label {
  background: rgba(0, 0, 0, 0.3);
}

#toggle:checked + label:after {
  /* font-style: italic; */
  content: "off";
  background-image:  "@/assets/image/view_slideshow_back.png";
}

#toggle:checked ~ .imgContainer {
  margin-top: 150px;
}

/* thumb */
.v_photo_list .thumb_list {
  /* position: fixed;? */
  height: 100px;
  display: inline-block;
  /* padding: 5px;  */
  outline-color: green;
}


/* 공유 모달 */
.p {
  margin-bottom: 20px;
}

.sharemodal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 500; /* Sit on top */
  left: 50%;
  /* width: 400px; */
  /* transform: translate(0, 0); */
  top: 50%;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  background-color: rgba(0, 0, 0, 0.4); 
}

.sharemodal:target:before {
  display: none;
}
.sharemodal:before {
  content: "";
  display: block;
  background: rgba(0, 0, 0, 0.6);
  position: fixed;
  z-index: 10;
   left: 50%;
  top: 50%;
  bottom: 0;
}

.share-modal-content {
  background: #fefefe;
  /* border: #333333 solid 1px; */
  /* border-radius: 5px; */
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
  float : right;
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
  background-color: rgba(0, 0, 0, 0.4); 
}

.infomodal:target:before {
  display: none;
}
.infomodal:before {
  content: "";
  display: block;
  background: rgba(0, 0, 0, 0.6);
  position: fixed;
  left: 50%;
  top: 50%;
  bottom: 0;
  z-index: 10;
}

.info-modal-content {
  background: #fefefe;
  /* border: #333333 solid 1px; */
  /* border-radius: 5px; */
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

.infomodal:target .info-modal-content {
  top: -100%;
  -webkit-transform: translate(0, -500%);
  -ms-transform: translate(0, -500%);
  transform: translate(0, -500%);
}

.closeinfomodal {
  color: #353535;
  font-size: 25px;
  text-decoration: none;
  position: absolute;
  right: 10px;
  top: 0;
  cursor: pointer;
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
  top: 3px;
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
   overflow: hidden;
  background-color: #e9ecef;
  border-radius: 5px;
}

.dropdown:hover .dropdown-content {
   overflow: hidden;
  display: block;
}

/* 슬라이드 */
.v_btn_prev {
  overflow: hidden;
  position: absolute;
  top: 50%;
  left: 45px;
  cursor: pointer;
}

.v_btn_next {
  overflow: hidden;
  position: absolute;
  top: 50%;
  right: 45px;
  cursor: pointer;
}

/* 더보기 슬라이드 */
.slide_container{
  /* left : 12400px; */
  
}


.wrapper {
  overflow: hidden;
  background-color: #333333;
  width: 120px;
  height: 30px;
  margin: 4px;
  border: none;
}

.element li {
  overflow: hidden;
  display: none;
}
.element li.on {
  overflow: hidden;
  display: block;
}

#warpp{
  overflow: hidden;
  background-color: #333333;
  width: 120px;
  height: 30px;
  margin: 4px;
  border: none;
}

/* .slideContainer {
  overflow: hidden;
  position: absolute;
  left: 50%;
  top: 50%;
} */

.slideContentImage {
  /* overflow: hidden; */
  position: fixed;
  /* top: 25%; */
  /* left : 25% */
  float : center;
  /* height : 400px; */

}

.slide_image {
  overflow: hidden;
  overflow: hidden;
  margin: 40px auto;
  max-width: 700px;
  position: fixed;
}

.element {
  overflow: hidden;
  padding: 20px;
  height: 300px;
  width: 100%;
  color: #fff;
  width: 120px;
  height: 30px;
  margin: 4px;
  border: none;
}

.element p {
  overflow: hidden;
  text-align: center;
  color: white;
  font-size: 3em;
  width: 120px;
  height: 30px;
  margin: 4px;
  border: none;
}

.element:-webkit-full-screen {
  overflow: hidden;
  background-color: #000;
  width: 100vw;
  height: 100vh;
}

.element:-webkit-full-screen p {
  overflow: hidden;
  visibility: visible;
  display: none;
}

.slidein {
  overflow: hidden;
  background-color: #333;
  position: relative;
  color: white;
  padding: 13px;
  top: 3px;
  bottom: 1px;
  border-radius: 3px;
  color: #fff;
  display: inline-block;
  /* min-width: 150px; */

}

</style>