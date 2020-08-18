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
                v-for="item in menus"
                @click="choose(item)"
                :class="{ active: item == selected }"
                :key="item.id"
              >
              <router-link :to="`${item.link}`" class="a">
                  <h3>{{ item.title }}</h3>
                </router-link>
                <hr class="middle2" />
              </li>
              <div>
              <!-- <div 
              v-for="folder in this.folders" 
              v-bind:key="folder.folder_id">
              <router-link :to="`/ownDocumentBox/${folder.folder_id}`">
                <p @click="getchild(folder.folder_id)">{{ folder.folder_name }}</p>
              </router-link>
              </div> -->
              <div>
                <div v-for="folder in this.folders" v-bind:key="folder.folder_id">
                  <router-link :to="`/ownDocumentBox/${folder.folder_id}`">
                    <div style="margin-left: 20px;">
                      <p v-if="folder.folder_parent" @click="getchild(folder.folder_id)" style="margin-left : 10px;">{{ folder.folder_name }}</p>
                    </div>
 
                  </router-link>
                </div>
                <hr />
              </div>
                <!-- <tree-item v-for="child in this.children" v-bind:key="child.folder_id">
                  <router-link :to="`/ownDocumentBox/${child.folder_id}`">
                  <p @click="getchild(child.folder_id)">{{ child.folder_name }}</p>
                 </router-link>              
                </tree-item> -->
              </div>

              <div class="mx-0">
          <!-- <v-subheader class="indigo lighten-5" style="margin-top:8px"
            >사내 조직도</v-subheader
          >
          <v-treeview
            v-model="selection"
            :items="items"
            selectable
            selection-type="all"
            open-all
            open-on-click
            return-object
            @input="selectDept"
          ></v-treeview> -->
        </div>

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
      menus: [
        { id: 1, title: "개인 문서함", link: "/ownDocumentBox" },
        { id: 2, title: "공유 문서함", link: "/shareDocumentBox" },
        { id: 3, title: "앨범", link: "/albumDocumentBox" },
      ],
      selected: null,
      folders:[],
      children:[]
    };
  },
  created() {
    this.getcapacity();
    this.getparent()
    this.getchild(this.parent);
    // this.use_capacity;
    // this.total_capacity;
  },
  computed: {
    ...mapState({
      userInfo: "userInfo",
      parent : "root_folder"
    }),
  },
  methods: {
    ...mapActions(["GET_CAPACITY", "GET_CHILD"]),
    // facthData() {
    //   this.getparent();

    //   list.forEach((item) => {
    //       for (let i = 0; i < list.length; i++) {
    //         if (item.folder_id === list[i].folder_id) {
    //           item.folder_id = list[i].folder_id;
    //           break;
    //         }
    //       }
    //     });
    //     console.log("foreach list", list);

    //     let myTree = null;
    //     list.forEach((item) => {
    //       if (myTree === null) {
    //         myTree = { id: item.id, name: item.deptName };
    //         console.log(";", myTree);
    //       } else {
    //         this.Recursive(item, myTree);
    //       }
    //     });

    //     let treeArr = [];
    //     treeArr.push(myTree);

    //     this.items = treeArr;
    //     this.myTree = myTree;
    // },
    // Recursive(item, tree) {
    //   //tree Id 와 item parentId가 같다면
    //   if (tree.id === item.parentId) {
    //     if (typeof tree.children === "undefined") {
    //       // tree에 children이 없다면
    //       tree.children = [];
    //       tree.children.push({ id: item.id, name: item.deptName });
    //     } else {
    //       // tree에 children이 있다.
    //       tree.children.push({ id: item.id, name: item.deptName });
    //     }
    //   } else {
    //     //tree Id와 item parentId가 다름
    //     if (typeof tree.children === "undefined") {
    //       // tree에 children이 없다면
    //       return;
    //     } else {
    //       // tree에 children이 있다면
    //       tree.children.forEach((child) => {
    //         this.Recursive(item, child);
    //       });
    //     }
    //   }
    // },
    goTrash() {
      this.selected = null;
      if (this.$route.path == "/trashDocumentBox") {
        // route.path가 트래쉬 페이지면은 액티브 클래스 없애기
        // var ss = document.querySelector.getElementsByClassName('active').remove();
        console.log;
      }
    },
    choose: function(index) {
      this.selected = index;
    },
    getparent(){
      this.GET_CHILD({ parent : this.parent }).then((list) => {
        this.folders = list;
      })
    },
    getchild(id){
      this.GET_CHILD({ parent : id }).then((list) => {
        this.children = list;
      })
      this.$emit('get child : ' + id)
    },
    getcapacity() {
      //console.log("method", this.userInfo.useremail);
      this.GET_CAPACITY({ email: this.userInfo.useremail }).then((list) => {
        //console.log(list);
        this.total = Math.floor(list.total_capacity / 1000000000);
        this.use = Math.floor(list.use_capacity / 1000000000);
        // this.use = Math.floor(list.use_capacity / 5000000);
        this.percent = Math.floor((this.use / this.total) * 100);
        this.spare_capacity = Math.floor(this.total - this.use -1);
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

.intoP1 {
  margin-left: 10px;
}

.intoP1 {
  margin-left: 10px;
}
</style>