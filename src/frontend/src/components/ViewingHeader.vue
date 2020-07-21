<template>
  <div class="v_header">
    <a class="v_btn_close">나가기</a>
    <div class="v_task">
      <ul class="v_task_lst">
        <li class="v_ta_zoom">
          <a title="확대/축소"><span>확대/축소</span></a>
        </li>
        <li class="v_ta_rotation">
          <a title="회전"><span>회전</span></a>
        </li>
        <li class="v_ta_magnifying">
          <a title="돋보기"><span>돋보기</span></a>
        </li>
        <li class="v_ta_share">
          <a title="공유"><span>공유</span></a>
        </li>
        <li class="v_ta_info">
          <a title="속성정보"><span>속성정보</span></a>
        </li>
        <li class="v_ta_trash">
          <a title="휴지통"><span>휴지통</span></a>
        </li>
        <li class="v_ta_more">
          <a title="더보기"><span>더보기</span></a>
          <div class="v_layers_s">
            <a class="slide">슬라이드쇼</a>
            <a class="publicfolder">공유 문서함 추가</a>
            <a class="publicalbum">공유 앨범 추가</a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style lang="scss" scoped>
*{
  background-color:gray;
}

.v_header {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 65px;
    z-index: 30;
    -webkit-transition: top 0.2s ease-in;
    transition: top 0.2s ease-in;
}

.v_task {
    position: absolute;
    top: 0;
    right: 9px;
}

.v_task_lst {
    width: auto;
    font-size: 10;
    line-height: 0;
    text-align: right;
    padding-top: 3px;
}
.v_task_lst li {
    display: inline-block; /* 한줄로 */
    position: relative;
    vertical-align: top;
}
.v_task_lst li > a {
    display: inline-block;
    width: 48px;
    height: 47px;
    text-align: center;
}
.v_task_lst li.v_ta_zoom > a:before {
    // background-image: url(https://ssl.pstatic.net/static/pwe/cloud/img/sprite/sp_viewer_e702cfca.png);
    // background-image: url(C:/Users/bit-user/Desktop/Media-Library/frontend/src/assets/logo.png);
    // background-image: -webkit-gradient(linear, left top, left bottom, from(transparent), to(transparent)), url(C:/Users/bit-user/Desktop/Media-Library/frontend/src/assets/logo.png);
    // background-image: linear-gradient(transparent, transparent), url(C:/Users/bit-user/Desktop/Media-Library/frontend/src/assets/logo.png);
    // background-size: 216px 174px;
    background-size:50px 50px;
    background-position: -40px -41px;
    width: 28px;
    height: 28px;
}

.v_task_lst li > a:before {
    content: '';
    display: inline-block;
    margin-top: 10px;
    vertical-align: top;
}
</style>