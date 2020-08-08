<template>
  <div class="sidebar">
    <div class="sidebar-inner">
      <div class="list">
        <ul class="menu-root">
          <li id="usage">
            <p class="p1">{{ use }}GB /</p>
            <p class="p2">{{ total }}GB</p>
            <div id="progress">
              <k-progress
                :percent="this.percent"
                active
                :color="['#E3E2E1', '#DEE8EB', '#A6C4C7', '#A49988']"
                :show-text="false"
                :line-height="7"
              />
            </div>
            <p class="p3">여유{{ spare_capacity }}GB</p>
          </li>
          <hr class="top" />
          <div class="document">
            <perfect-scrollbar>
              <li
                v-for="item in items"
                @click="choose(item)"
                :class="{ active: item == selected }"
                :key="item.id"
              >
                <router-link :to="`${item.link}`" class="a">
                  <h3>{{ item.title }}</h3>
                </router-link>
                <hr class="middle2" />
              </li>
              <!--
              <li  :class="{ active: own == selected }">
                <router-link to="/ownDocumentBox" class="a">
                  <h3>개인 문서함</h3>
                </router-link>
              </li>
              <hr class="middle1" />
              <li  :class="{ active: share == selected }">
                <router-link to="/shareDocumentBox" class="a">
                  <h3>공유 문서함</h3>
                </router-link>
              </li>
              <hr class="middle2" />
              <li @click="choose(album)" :class="{ active: album == selected }">
                <router-link to="/albumDocumentBox" class="a">
                  <h3>앨범</h3>
                </router-link>
              </li>
              -->
            </perfect-scrollbar>
          </div>
          <hr class="bottom" />
          <li class="trash">
            <router-link to="/trashDocumentBox" class="a">
              <img
                src="@/assets/image/trash.png"
                alt="trash logo"
                height="30px"
                @click="goTrash()"
              />
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
import { mapActions, mapState } from "vuex";

export default {
  // el: "progress",
  data() {
    return {
      use: "",
      total: "",
      percent: 0,
      spare_capacity: "",
      items: [
        { id: 1, title: "개인 문서함", link: "/ownDocumentBox" },
        { id: 2, title: "공유 문서함", link: "/shareDocumentBox" },
        { id: 3, title: "앨범", link: "/albumDocumentBox" },
      ],
      selected: null,
    };
  },
  created() {
    this.getcapacity();
    // this.use_capacity;
    // this.total_capacity;
  },
  computed: {
    ...mapState({
      userInfo: "userInfo",
    }),
  },
  methods: {
    ...mapActions(["GET_CAPACITY"]),
    goTrash() {
      if (this.$route.path == "/trashDocumentBox") {
        // route.path가 트래쉬 페이지면은 액티브 클래스 없애기
        
        console.log;
      }
    },
    choose: function(index) {
      this.selected = index;
    },
    getcapacity() {
      //console.log("method", this.userInfo.useremail);
      this.GET_CAPACITY({ email: this.userInfo.useremail }).then((list) => {
        //console.log(list);
        this.total = Math.floor(list.total_capacity / 1000000000);
        this.use = Math.floor(list.use_capacity / 10000000);
        this.percent = Math.floor((this.use / this.total) * 100);
        this.spare_capacity = this.total - this.use;
        /*console.log(
          "total : ",
          this.total,
          " / user : ",
          this.use,
          " / percent : ",
          this.percent,
          " / spare : ",
          this.spare_capacity
        );
        */
      });
    },
  },
};
</script>

<style scoped>
.active h3 {
  color: #a6c4c7;
  text-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
}
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
  color: #dee8eb;
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
  position: fixed;
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
.a:link {
  text-decoration: none;
  color: #474346;
}
.a:visited {
  text-decoration: none;
  color: #474346;
}
.a:active {
  text-decoration: none;
  color: #474346;
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
