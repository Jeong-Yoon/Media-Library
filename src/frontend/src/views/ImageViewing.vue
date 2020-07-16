<template>
  <div id="wrap">
    <div id="content_wrap">
      <div class="viewer_wrap open_list">
        <div class="viewer bb-photoviewer">
          <div class="v_content">
            <Viewing-Header />
            <div class="v_photo_img">
              <img
                src="https://www.w3schools.com/images/w3schools_green.jpg"
                alt="W3Schools.com"
                style="width: auto; height: auto; opacity: 1; transform: translate3d(0px, 0px, 0px);"
              >
            </div>
            <div
              class="
                v_photo_img"
              style="opacity:0;"
            >
              <img
                src="https://www.w3schools.com/images/w3schools_green.jpg"
                alt="W3Schools.com"
              >
            </div>
            <a class="v_btn_prev"><span class="blind">이전사진</span></a>
            <a class="v_btn_next"><span class="blind">다음사진</span></a>
            <!-- <div class="v_photo_list">
              <ul><li>썸네일</li></ul>
            </div> -->
            <Photo-List />
            <div class="v_paginate">
              <a>
                <em class="ic_toggle" />
                7/13526
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ViewingHeader from '../components/ViewingHeader.vue'
import PhotoList from '../components/PhotoList.vue'

export default {
  components:{
    ViewingHeader,
    PhotoList
  }
}
</script>

<style lang="scss" scoped>
*{
  background-color: black;
}
body, p, h1, h2, h3, h4, h5, h6, menu, ul, ol, li, dl, dt, dd, table, th, td, form, fieldset, legend, input, textarea, button, select {
    margin: 0;
    padding: 0;
}

.v_btn_next {
    position: absolute;
    right: 0;
    top: 0;
    bottom: 0;
    width: 50%;
    background: url(https://ssl.pstatic.net/static/pwe/photo/blank.gif);
    outline: none;
}

.v_btn_next span {
    display: none;
    overflow: hidden;
    text-indent: -200px;
    position: absolute;
    right: 62px;
    top: 50%;
    width: 23px;
    height: 41px;
    margin: -20px 0 0 0;
    background-position: -75px 0;
    font-size: 0;
    content: '';
}

.v_btn_prev {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    width: 50%;
    background: url(https://ssl.pstatic.net/static/pwe/photo/blank.gif);
    outline: none;
}

.v_btn_prev span {
    display: none;
    overflow: hidden;
    text-indent: -200px;
    position: absolute;
    left: 62px;
    top: 50%;
    width: 23px;
    height: 41px;
    margin: -20px 0 0 0;
    background-position: -50px 0;
    font-size: 0;
    content: '';
}

.v_paginate a .ic_toggle, .v_comm_list li .btn_del_comm, .btn_v_cls, .v_btn_prev span, .v_btn_next span, .v_btn_close:after, .slide_in .time_area button:after, .slide_in .btn_play:after, .slide_in .btn_close_s:after, .v_task_lst li.v_ta_fcls > a, .input_memo .del, .tip_layer_v2 .cls, .tip_layer_v2 p a:after, .ic_video_s, .ic_audio_s {
    background-image: url(https://ssl.pstatic.net/static/pwe/cloud/img/sprite/sp_viewer_1702.png);
    background-repeat: no-repeat;
}


.open_list .v_photo_img, .open_list .v_original {
    bottom: 86px;
}

.v_photo_img {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    white-space: nowrap;
    text-align: center;
    font-size: 0;
    line-height: 0;
    -webkit-transition: all 0.2s ease-in;
    transition: all 0.2s ease-in;
}

.v_photo_img:after {
    display: inline-block;
    overflow: hidden;
    width: 1px;
    height: 100%;
    vertical-align: middle;
    content: '';
}

.v_photo_list {
    display: none;
    overflow: hidden;
    overflow-x: auto;
    position: absolute;
    left: 0;
    bottom: -86px;
    right: 0;
    height: 97px;
    background-color: #000;
    z-index: 50;
    -webkit-transition: bottom 0.2s ease-in;
    transition: bottom 0.2s ease-in;
}
.open_list .v_photo_list {
    bottom: 0;
    display: block;
}
.v_photo_list::-webkit-scrollbar {
    height: 10px;
}
.v_photo_list ul {
    padding: 13px 10px 24px;
    font-size: 0;
    line-height: 0;
    white-space: nowrap;
}
.v_photo_list ul li {
    display: inline-block;
    padding-right: 5px;
}

.v_photo_list ul li a {
    position: relative;
    display: inline-block;
}
// img, fieldset {
//     border: 0;
//     vertical-align: top;
// }
.v_paginate {
    position: absolute;
    right: 20px;
    bottom: 13px;
    width: auto;
    height: 31px;
    border: 1px solid #000;
    border-radius: 3px;
    z-index: 60;
}
.open_list .v_paginate a .ic_toggle {
    background-position: -27px -40px;
}
.v_paginate a {
    position: relative;
    display: block;
    height: 31px;
    padding: 0 9px 0 27px;
    line-height: 31px;
    color: #fff;
    white-space: nowrap;
    text-decoration: none;
    z-index: 5;
    font-size: 11px;
}

}
.v_photo_list ul {
    padding: 13px 10px 24px;
    font-size: 0;
    line-height: 0;
    white-space: nowrap;
}

</style>