<template>
<div>

<span @click="openImageNav" style="cursor: pointer;">이미지</span>


<!-- 이미지 모달 -->
<div class="overlay" id="image_nav" tabindex="1" role="dialog" >

<!-- header -->
<div class="header">

    <!-- 나가기 -->
    <a class="v_btn_close" @click="closeImageNav()" style="color:#fff;  font-size: 14px;">나가기</a>
    
    <div class="v_task">

      <!-- 확대/축소 -->
      <!-- <div class="zoom_wrap">       -->
        <a class="zoom-in" id="in" title ="확대" @click="zoomIn()" style="margin-right:3px"><img src="@/assets/image/icon_pl.png" height="15.5px"></a>
        <a class="zoom-out"  id="out" title ="축소" @click="zoomOut()"  style="margin-right:3px"><img src="@/assets/image/icon_mi.png" height="15.5px"></a>
      <!-- </div> -->

        <!-- 회전 -->
        <a class="v_ta_rotate" @click="rotate()" title ="회전" style="margin-right:3px; top : 0.5px;"><img src="@/assets/image/icon2.png" height="16.7px"></a>

        <!-- 돋보기 -->
        <a class="v_ta_magnifying"  title="돋보기" style="top : 1.5px"> <img src="@/assets/image/icon3.png" height="15px"></a>

        <!-- 공유 -->
        <a class="share_modal" id="shareBtn" @click="openShareModal()" title="공유" style="margin-right:3px"><img src="@/assets/image/icon4.png" height="15px"></a>

        <!-- 정보 -->
        <a class="info_modal" id="infoBtn" @click="openInfoModal()" title="정보" style="margin-right:3px; top : 0.5px"><img src="@/assets/image/icon5.png" height="15.5px"></a>
      
        <!-- 삭제 -->
        <a class="v_ta_trash" title="휴지통"><img src="@/assets/image/icon6.png" height="15px"></a>
      
        <!-- 더보기 -->
          <div class="dropdown">
            <!-- <button class="dropbtn">Dropdown</button> -->
            <a class="dropbtn" title="더보기" style="left:-3px"><img src="@/assets/image/icon7.png" height="15px"></a>
            <div class="dropdown-content" style="float : right;">
              <a class="slider_option" onclick="var el = document.getElementById('element'); el.webkitRequestFullscreen();">슬라이드쇼</a>
              <a class="publicfolder">공유 문서함 추가</a>
              <a class="publicalbum">공유 앨범 추가</a>
            </div>
          </div> <!-- 더보기 끝 -->
  </div><!-- v_task 끝 -->
</div><!-- header 끝 -->

<!-- content -->
<div class="content">
  <!-- <div class ="image-container">  -->
  <div  class ="image-container" id="imgContainer"> 
    <img class="image" id="img" src="@/assets/image/zo.png">
  </div>

  <div class="slideshow">
      <a class="v_btn_prev" @click="plusDivs(-1)" title="이전사진"><img src="@/assets/image/btn_prev.png" height="30px"></a>
      <a class="v_btn_next" @click="plusDivs(1)" title="다음사진"><img src="@/assets/image/btn_next.png" height="30px"></a>
  </div>
</div><!-- contrent 끝 -->

<!-- footer -->
  <div class ="footer">
    <input type="checkbox" name="toggle" id="toggle" />
    <label for="toggle"></label>

    <div class="toggle-container"><!-- thumb list -->
        <div class="v_photo_list" >
          <ul class="image_list">
            <li class="image_item">
              <a>
                <img src="https://www.w3schools.com/images/w3schools_green.jpg">
                <span class="mask_sel" />
              </a>
            </li>
            
            <li class="image_item">
              <a>
                <img src="https://www.w3schools.com/images/w3schools_green.jpg">
              <span class="mask_sel" />
            </a>
          </li>
          
          <li class="image_item">
            <a>
              <img src="https://www.w3schools.com/images/w3schools_green.jpg">
              <span class="mask_sel" />
            </a>
          </li>
        </ul>
     </div>
  </div><!-- thumb list-->
</div> <!--footer 끝-->
</div> <!-- 큰 모달 끝 --> 


<!-- 공유 모달 -->
<div id="shareModal" class="sharemodal">
<div class="share-modal-content">
    <div class ="share-modal-header">
      <a style="font-size : 16px">공유 URL 생성</a>
      <span class="closesharemodal" @click="closeShareModal()">&times;</span>
    </div>

    <div class =" share-modal-body">
      <button type="button" class="btn_copy" data-dismiss="modal" >복사</button>
      <p> URL 영역 </P>
    </div>

    <div class="share-modal-footer">
    </div>
</div>
</div><!-- 공유 모달 끝 -->

<!-- 정보 모달 -->
<div id="infoModal" class="infomodal">
  <div class="info-modal-content">
    <div class ="info-modal-header">
      <a style="font-size : 16px">정보</a>
      <span class="closeinfomodal" @click="closeInfoModal()">&times;</span>
    </div>

  <div class =" info-modal-body">
    <p style="margin-bottom : 5px;  color : #353535;"> 파일 정보 </p>
      <div class =" info" style="font-size:13px; margin-left : 5px">
      <span> 해상도 </span> <a style="font-color : #000">3024 X 3024</a> <br>
      <span> 파일크기 </span> <br>
      <span style="margin-bottom : 5px"> 업로드일시 </span> 
      </div>
  </div>

  <div class="info-modal-footer">
    <p style="margin-bottom : 5px;  color : #353535;"> 폴더 경로 </p>
     <div class =" info" style="font-size:13px; margin-left : 5px">
      <a> 개인문서함/.../... </a>
     </div>
     </div>
  </div>
</div><!-- 정보 모달 끝 -->




<!-- f11 -->
<div class="container" style="overflow: hidden;">
  <div class="element" id="element" style="overflow: hidden;">    
    <div id="wrapper" style="overflow: hidden;">
    <div id="container" style="overflow: hidden;">
      
        <div  class ="image-container" id="imgContainer" style="overflow : hidden"> 
          <img class="image" id="img" src="@/assets/image/zozo.png">
       </div>


        <!-- -->
         <div class="slide_in">
            
             <a class="slide_next" @click="plusDivsSlide(-1)" title="이전" style="margin-left : 7px"><img src="@/assets/image/icon_1_1.png" height="15px"></a>

             <!-- <a class="slide_play_stop" @click="aaa" title="재생"><img src="@/assets/image/icon_1_2.png" height="15px"></a> -->
             <a class="slide_play_stop" @click="aaa" title="일시정지"><img src="@/assets/image/icon_1_2_1.png" height="15px"></a>

             <a class="slide_next" @click="plusDivsSlide(1)" title="다음"><img src="@/assets/image/icon_1_3.png" height="15px"></a>
            
             <a class="slide_play_stop" @click="closeFullscreen()" title="슬라이드쇼 마침"><img src="@/assets/image/icon_1_4.png" height="15px"></a>

        </div><!-- -->


      </div>
    </div>
</div>
</div> <!-- f11 끝 -->



</div>
</template>



<script>
// import Cropper from '../utils/cropper.js'
export default {
    data(){
    return {
      slideIndex : 1,
      // zoomIn:undefined,
      // zoomOut:undefined
      }
},
    created(){
      // let options = {
      //   holderNode: '.js-cropper-holder',
      //   imageNode: '.js-cropper-image-box',
      //   thumbNode: '.js-cropper-image-thumb',
      //   spinner: '.js-cropper-image-spinner',
      //   zoomIn: '.js-cropper-image-zoom-in',
      //   zoomOut: '.js-cropper-image-zoom-out',
        // imgSrc: 'https://camo.mybb.com/e01de90be6012adc1b1701dba899491a9348ae79/687474703a2f2f7777772e6a71756572797363726970742e6e65742f696d616765732f53696d706c6573742d526573706f6e736976652d6a51756572792d496d6167652d4c69676874626f782d506c7567696e2d73696d706c652d6c69676874626f782e6a7067'
    //  }

    //   const cropper = new Cropper(options);
    //   cropper.show();


        //  this.zoomIn = document.getElementById("in")
        //  this.zoomOut = document.getElementById("out")


    },
   
   methods: {

        //모달 
        openImageNav() {
            console.log("이미지모달열어!")
            document.getElementById("image_nav").style.display = "block";
            // this.openshareModal = [];
            // event.target.reset();
        },
        closeImageNav() {
            console.log("이미지모달닫아!")
            document.getElementById("image_nav").style.display = "none";
            document.getElementById("video_nav").style.display = "none";
            document.getElementById("shareModal").style.display = "none";
        },
        openShareModal(){
            console.log("공유 모달")
            document.getElementById("shareModal").style.display = "block";
        },
        closeShareModal() {
            console.log("공유 모달 닫아")
            document.getElementById("shareModal").style.display = "none";
        },
        openInfoModal(){
            console.log("정보 모달")
            document.getElementById("infoModal").style.display = "block";
        },
        closeInfoModal() {
            console.log("정보모달 닫아")
            document.getElementById("infoModal").style.display = "none";
        },

        // 회전
        rotate(){
          console.log("회전")
           const container = document.querySelector('.image-container');
          // Probably select current image here:
          const image = container.querySelector('.image');
          const width = image.offsetWidth;
          const height = image.offsetHeight;
          console.log("image", image);
          console.log("container",container)
          // this should be in data attributes or extracted from deg
          const currentRotateCycle =
            parseInt(
              getComputedStyle(image).getPropertyValue('--current-rotate-cycle')
            );
          
          if (currentRotateCycle % 2 === 0) {
            image.style.setProperty('--current-scale', 'scale(' + (height / width) + ')');
          } else {
            image.style.setProperty('--current-scale', 'scale(1)');  
          }
          
          if (currentRotateCycle === 3) {
            image.style.setProperty('--current-rotate-cycle', 0);
            image.style.setProperty('--current-rotate', 'rotate(0deg)');
          } else {
            const newRotateCycle = currentRotateCycle + 1;
            image.style.setProperty('--current-rotate-cycle', newRotateCycle);
            image.style.setProperty('--current-rotate', 'rotate(' + newRotateCycle * 90 + 'deg)');
          }

        },  

          // 슬라이드쇼
          plusDivs(n) {
            console.log("slide_next")
            this.showDivs(this.slideIndex += n);
          },
          showDivs(n) {
            console.log("slide_prev")
            var i;
            var x = document.getElementsByClassName("image");
            if (n > x.length) {this.slideIndex = 1}
            if (n < 1) {this.slideIndex = x.length}
            for (i = 0; i < x.length; i++) {
              x[i].style.display = "none";  
            }
            x[this.slideIndex-1].style.display = "block";  
            },

            //  f11 슬라이드쇼

            // autoSlide() {
            //   console("f11");
            //    document.getElementById("image_nav").style.display = "block";
            //   document.documentElement.webkitRequestFullscreen();

            // },


          // zoom 
          //   zoomin(elm){
          //     console.log("확대")
          //     console.dir(elm);
          //   if(elm.parentElement.parentElement.firstElementChild.style.transform === "" || elm.parentElement.firstElementChild.style.transform === 'translate("-50%, -50%")'){
          //     elm.parentElement.parentElement.firstElementChild.style.transform = 'translate(-50%, -50%) scale(1.1)';
          //   }else{
          //     var cur = elm.parentElement.parentElement.firstElementChild.style.transform.split('scale(')[1].split(')')[0];
          //     if(cur*1 >= 2) return;
          //     elm.parentElement.parentElement.firstElementChild.style.transform = 'translate(-50%, -50%) scale('+(eval(cur*1)+0.1)+')';
          //   }
          // },


          // zoomout(elm){
          //   console.log("축소")
          //   if(elm.parentElement.parentElement.firstElementChild.style.transform === "" || elm.parentElement.firstElementChild.style.transform === 'translate("-50%, -50%")'){
          //     elm.parentElement.parentElement.firstElementChild.style.transform = 'translate(-50%, -50%) scale(0.9)';
          //   }else{
          //     var cur = elm.parentElement.parentElement.firstElementChild.style.transform.split('scale(')[1].split(')')[0];
          //     if(cur*1 <= 0.1) return;
          //     elm.parentElement.parentElement.firstElementChild.style.transform = 'translate(-50%, -50%) scale('+(eval(cur*1)-0.1)+')';
          //   }
          // },

          zoomIn(){
            console.log("zoomIn")
            let myImg = document.getElementById("img")
            let imgCurrWidth = myImg.clientWidth;
              // let maxWidth = this.imgBox.clientWidth;
            if(imgCurrWidth<1000){
              myImg.style.width = `${imgCurrWidth+100}px`
            }else{
              return false
            }    

          },
          zoomOut(){
            console.log("zoomOut")
            let myImg = document.getElementById("img")
            let imgCurrWidth = myImg.clientWidth;
            // let maxWidth = this.imgBox.clientWidth;
            if(imgCurrWidth>100){
             myImg.style.width = `${imgCurrWidth-100}px`
            }else{
              return false
            }

          },
          

          // f11 

          closeFullscreen() {
             if (document.exitFullscreen) {
              document.exitFullscreen();
            } else if (document.webkitExitFullscreen) { /* Chrome, Safari and Opera */
              document.webkitExitFullscreen();
            } 
          },

           plusDivsSlide(n) {
            console.log("슬라이드 1")
            this.showDivs(this.slideIndex += n);
          },
          showDivsSlide(n) {
            console.log("슬라이드 2")
            var i;
            var x = document.getElementsByClassName("image");
            if (n > x.length) {this.slideIndex = 1}
            if (n < 1) {this.slideIndex = x.length}
            for (i = 0; i < x.length; i++) {
              x[i].style.display = "none";  
            }
            x[this.slideIndex-1].style.display = "block";  
            },
         

        }
      }
</script>


<style>

/* 큰 모달 */
.overlay {
  height: 100%;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgb(0,0,0);
  z-index: 100;
}

/* 이미지 */
.image-container {  
  display:flex;
  justify-content:center;
  align-items: center;
}

.image-container:before {
  content: '';
  display: block;
}

.image{
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
  left : 10px;
}

.v_task{
  display: inline-block; /* 한줄로 */
  position: absolute;
  top: 15px;
  right : 5px;
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
background:#000; /*  */
color:#FFF;
position: fixed;
bottom: -200px; /* 접어두기 동작 수행*/
left: 0;
width: 100%;
height: 150px;
/* padding: 20px; */
/* transition: top 300ms cubic-bezier(0.17, 0.04, 0.03, 0.94); */
overflow: hidden;
box-sizing: border-box;
  
}

#toggle {
  position:absolute;
  cursor:pointer;
  left:-100%;
}

#toggle + label {
  font-style: italic;
  z-index: 600;
  position:absolute;
  bottom : 15px;
  right : 15px;
  padding:10px;
  /* background: rgba(0, 0, 0, 0.6); */
  background: rgba(0, 0, 0, 0.6);
  width: 100px;
/*   border-radius: 3px; */
  padding: 8px 10px;
  color: #FFF;
/*   line-height:20px; */
  font-size:16px;
  text-align:center;
/*   -webkit-font-smoothing: antialiased; */
  cursor: pointer;
  /* transition:all 500ms ease; */
}

#toggle + label:after {
  content:"펼쳐보기" 
}

.container {
transition: margin 300ms cubic-bezier(0.17, 0.04, 0.03, 0.94);
  padding:5em 3em;
}

#toggle:checked ~ .toggle-container {
  bottom: 0;
}

#toggle:checked ~ .container {
  margin-bottom: 200px;
}

#toggle:checked + label {
    background: rgba(0, 0, 0, 0.3);
}

#toggle:checked + label:after {
  font-style: italic;
  content:"접어두기"
}

/* thumb */
.v_photo_list .thumb_list{
  /* position: fixed;? */
  height: 100px;
  display: inline-block;
  /* padding: 5px;  */
  display: inline-block;
  outline-color: green; 
}

.ul , li{
  list-style: none;
  float:left;
  margin-bottom : 10px;


}

/* 공유 모달 */
.p {
    font-size: 13px;
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
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

.sharemodal:target:before {
    display: none;
}
.sharemodal:before {
    content:"";
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
    border-radius: 10px;
    color: #fff;
    display: inline-block;
    font-size: 14px;
    padding: 8px 15px;
    text-decoration: none;
    text-align: center;
    min-width: 60px;
    position: relative;
    /* transition: color .1s ease; */
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
.share-modal-header{
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
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

.infomodal:target:before {
    display: none;
}
.infomodal:before {
    content:"";
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
    transition: color .1s ease;
    float: right;
    bottom : 5px;
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
.info-modal-header{
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
  top : 3.px;
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
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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

.dropdown-content a:hover {background-color: #e9ecef;  border-radius: 5px;}


.dropdown:hover .dropdown-content {
  display: block;
}

/* 슬라이드 */
.v_btn_prev {
  position: absolute;
  top: 450px;
  left : 45px;
  cursor: pointer;
}

.v_btn_next{
  position: absolute;
  top: 450px;
  right : 45px;
  cursor: pointer;
}


/* f11 */
.wrapper {
 overflow: hidden;
}

.container {
  overflow: hidden;
  margin: 40px auto;
  max-width: 700px;
  position: fixed;
  bottom: -2400px; /* 접어두기 동작 수행*/
}

.element {
  padding: 20px;
  height: 300px;
  width: 100%;
   overflow: hidden;
  color : #fff;

}

.element p {
  visibility: hidden;
  text-align: center;
  color: white;
  font-size: 3em;

}

.element:-webkit-full-screen {
  background-color: #000;
  width: 100vw;
  height: 100vh;

}

.element:-webkit-full-screen p {
  visibility: visible;
  display: none;

}

.slide_in {
  background-color: #333;
  position: relative; 
  color: white;
  padding: 10px;
  top : 3px;
  bottom : 1px;
  border-radius: 3px;
  color: #fff;
  display: inline-block;
  /* padding: 8px 15px; */
  text-decoration: none;
  text-align: center;
  min-width: 150px;
}

.slide_in a {
  margin-right: 15px;
}



</style>