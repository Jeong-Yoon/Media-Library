<template>
  <div id="wrap">
    <div id="content_wrap">
      <div class="viewer_wrap open_list">
        <div class="viewer bb-slideshow">
          <div class="vv_content">
            <div class="v_photo_box">
              <div class="v_photo_img">
                <img>
              </div>
              <div class="v_photo_img">
                <img>
              </div>
              <div class="v_photo_img">
                <img>
              </div>
            </div>
          </div>
          <div class="slide_in">
            <div class="time_area">
              <button class="btn_prev_s">
                <span class="blind">이전</span>
              </button>
              <span class="time">3초</span>
              <button class="btn_next_s">
                <span class="blind">다음</span>
              </button>
            </div>
            <!-- <button class="btn_play stop"> -->
            <button class="btn_play">
              <span class="blind">일시정지</span>
            </button>
            <button class="btn_close_s">
              슬라이드쇼 마침
            </button>
          </div>
        </div>
        <div
          class="bg_ground"
          style="position: fixed;"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
    
}
</script>
<style lang="scss" scoped>
.blind, legend {
	position: absolute;
	overflow: hidden;
	clip: rect(0 0 0 0);
	margin: -1px;
	width: 1px;
	height: 1px;
}
// .v_paginate a .ic_toggle,
// .v_comm_list li .btn_del_comm,
// .btn_v_cls,
// .v_btn_prev span,
// .v_btn_next span,
// .v_btn_close:after,
// .slide_in .time_area button:after,
// .slide_in .btn_play:after,
// .slide_in .btn_close_s:after,
// .v_task_lst li.v_ta_fcls > a,
// .input_memo .del,
// .tip_layer_v2 .cls,
// .tip_layer_v2 p a:after,
// .ic_video_s,
// .ic_audio_s {
// 	background-image: url(https://ssl.pstatic.net/static/pwe/cloud/img/sprite/sp_viewer_1702.png);
// 	background-repeat: no-repeat;
// }
.audio_type_none.toge_audio .audio_play_box .btn_play {
	opacity: .45;
	filter: alpha(opacity=45);
	cursor: default;
}
.toge_audio .audio_play_box .btn_play {
	position: absolute;
	overflow: hidden;
	left: 33px;
	top: 25px;
	width: 22px;
	height: 27px;
	background-position: -50px -618px;
}
.audio_type_play.toge_audio .audio_play_box .btn_play {
	background-position: -101px -618px;
}

.scr_wrap,
#bb-cluster-view,
#bb-photowall-view,
.viewer_wrap .v_content {
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.viewer_wrap .js-svg-loading {
	position: absolute;
	top: 50%;
	left: 50%;
}

.v_photo_box {
	position: relative;
	height: 100%;
}

.viewer_wrap.open_original .v_photo_box,
.viewer_wrap.open_original .v_photo_list,
.viewer_wrap.open_original .v_paginate,
.viewer_wrap.open_original .bb-sidebar {
	display: none;
}

/*한장뷰 슬라이드쇼*/
.open_list .bb-slideshow .v_photo_img {
	bottom: 0;
}

.slide_in.js-video-mode {
	width: 115px;
	margin-left: -58px;
}
.viewer {
	position: relative;
	height: 100%;
	z-index: 100;
}

.viewer_wrap {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: 900;
	overflow: hidden;
}

.viewer_wrap .bg_ground {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #000;
}

.viewer_wrap .no_view {
	display: none !important;
}

.viewer_wrap.open_original .v_photo_box,
.viewer_wrap.open_original .v_photo_list,
.viewer_wrap.open_original .v_paginate,
.viewer_wrap.open_original .bb-sidebar {
	display: none;
}

.v_photo_box {
	position: relative;
	height: 100%;
}
/* 슬라이드 쇼*/
.vv_content {
	position: relative;
	height: 100%;
	z-index: 20;
}

.slide_in {
	position: absolute;
	left: 50%;
	bottom: 20px;
	width: 250px;
	height: 31px;
	margin-left: -123px;
	border: 1px solid #000;
	border-radius: 3px;
	z-index: 4000;
	font-size: 0;
	white-space: nowrap;
}

.slide_in:after {
	position: absolute;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
	background-color: #000;
	opacity: .8;
	content: '';
	z-index: 1;
}

.slide_in button {
	position: relative;
	height: 31px;
	background-color: transparent;
	z-index: 10;
	border: 0 none;
	font-size: 12px;
	padding: 0;
}

.slide_in .btn_play {
	width: 45px;
	text-align: center;
	border-right: 1px solid #000;
	border-left: 1px solid #000;
}

.slide_in .btn_play:after {
	display: inline-block;
	margin-top: 4px;
	width: 24px;
	height: 23px;
	background-position: -78px -50px;
	content: '';
}

.slide_in .btn_play.stop:after {
	background-position: -50px -50px;
}

.slide_in .btn_close_s {
	position: relative;
	display: inline-block;
	width: 115px;
	height: 31px;
	line-height: 29px;
	color: #fff;
	text-align: left;
	text-indent: 8px;
}

.slide_in .btn_close_s:after {
	position: absolute;
	right: 10px;
	top: 12px;
	width: 8px;
	height: 7px;
	background-position: -29px -54px;
	content: '';
}

.slide_in .time_area {
	display: inline-block;
	position: relative;
	padding: 0 3px 0 3px;
	z-index: 10;
	font-size: 0;
	line-height: 0;
}

.slide_in .time_area .time {
	display: inline-block;
	width: 37px;
	color: #fff;
	font-size: 12px;
	text-align: center;
	line-height: 31px;
}

.slide_in .time_area button {
	position: relative;
	display: inline-block;
	width: 22px;
}

.slide_in .time_area button:after {
	position: absolute;
	left: 12px;
	top: 12px;
	width: 4px;
	height: 7px;
	content: '';
}

.slide_in .time_area button.btn_prev_s:after {
	background-position: -50px -77px;
}

.slide_in .time_area button.btn_next_s:after {
	left: 6px;
	background-position: -62px -77px;
}


</style>