<template>
  <div class="sidebar">
    <div class="sidebar-inner">
      <div class="list">
        <ul class="menu-root">
          <li id="usage">
            <p class="p1">
              {{ use_capacity }}GB /
            </p>
            <p class="p2">
              {{ total_capacity }}GB
            </p>
            <div id="progress">
              <k-progress
                :percent="80"
                active
                :color="['#E3E2E1', '#DEE8EB', '#A6C4C7', '#A49988']"
                :show-text="false"
                :line-height="7"
              />
            </div>
            <p class="p3">
              여유{{ spare_capacity }}GB
            </p>
          </li>
          <hr class="top">
          <div class="document">
            <perfect-scrollbar>
              <li>
                <h3>개인 문서함</h3>
              </li>
              <hr class="middle1">
              <li>
                <h3>공유 문서함</h3>
              </li>
              <hr class="middle2">
              <li>
                <h3>앨범</h3>
              </li>
            </perfect-scrollbar>
          </div>
          <hr class="bottom">
          <li class="trash">
            <router-link to="/">
              <img
                src="@/assets/image/trash.png"
                alt="trash logo"
                height="30px"
              >
              <p class="p4">
                휴지통
              </p>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // el: "progress",
  data() {
    return {
      use_capacity: "409",
      total_capacity: "512",
      percentage: 80,
      spare_capacity: "102"
    };
  }
};
</script>

<style scoped>
li {
  padding-left: 25px;
  padding-right: 15px;
}
a {
  text-decoration: none;
}
img:hover {
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
}
h3:hover {
  color: #a6c4c7;
  text-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
}
#main.fix-sidebar .sidebar {
  position: fixed;
}
.sidebar {
  position: absolute;
  z-index: 10;
  top: 50px;
  left: 0;
  bottom: 0;
  overflow-x: hidden;
  overflow-y: auto;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  background-color: white;
  -webkit-overflow-scrolling: touch;
  -ms-overflow-style: none;
}
user agent stylesheet div {
  display: block;
}
#usage {
  padding-left: 25px;
  margin-right: -50px;
  padding-top: 15px;
  padding-bottom: 10px;
}
.p1 {
  font-size: 15px;
  font-weight: bold;
  float: left;
  color: #474346;
}
.p2 {
  font-size: 15px;
  color: #a49988;
  float: left;
  margin-left: 7px;
}
.p3 {
  font-size: 10px;
  color: #e3e2e1;
  padding-left: 115px;
}
.p4 {
  font-size: 10px;
  color: #a49988;
}
#progress {
  width: 225px;
  clear: both;
  padding-top: 5px;
}
.trash {
  padding-left: 90px;
  position: fixed;
  width: 150px;
  bottom: 10px;
  padding-bottom: 15px;
}
.top {
  position: fixed;
  width: 215px;
  margin-bottom: 30px;
}
.middle1 {
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 25px;
  margin-right: 15px;
  width: 170px;
  border: 1px solid #e3e2e1;
}
.middle2 {
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 25px;
  margin-right: 15px;
  width: 170px;
  border: 1px solid #e3e2e1;
}
.bottom {
  position: fixed;
  width: 215px;
  bottom: 85px;
}
.document {
  margin-top: 25px;
  margin-bottom: 10px;
}
</style>
