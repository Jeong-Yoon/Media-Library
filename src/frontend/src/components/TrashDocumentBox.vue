<template>
  <content>
    <div class="content">
      <div class="top-content">
        <span class="all">
          <input id="check" type="checkbox" class="checkbox" />
          <label for="check" />
        </span>
        <button class="b1">
          새폴더
        </button>
        <button class="b2">
          폴더
        </button>
        <button class="b3">
          사진
        </button>
        <button class="b4">
          동영상
        </button>
        <form class="search">
          <select id="key" name="keyword" class="key">
            <option value="file_origin_name">
              파일명
            </option>
            <option value="contents_atribute">
              확장자
            </option>
            <option value="contents_reg_date">
              등록일
            </option>
          </select>
          <input
            type="text"
            name="search"
            placeholder="Search.."
            class="value"
          />
          <button type="submit" class="submit">
            <img
              src="@/assets/image/search.png"
              alt="search logo"
              height="15px"
            />
          </button>
        </form>
      </div>
      <hr />

      <div class="bottom-content">
        <ul class="list_thumb">
          <li class="li" title="2020">
            <label for="">
              <div class="thumb">
                <span class="folder">
                  <img
                    src="@/assets/image/folder.png"
                    alt="folder.png"
                    width="150"
                    height="150"
                    style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  />
                </span>
              </div>
              <div class="info">
                <span class="title">2020</span>
              </div>
            </label>
          </li>

          <li class="li" title="june.jpg">
            <label for="">
              <div class="thumb">
                <span class="file">
                  <img
                    src="@/assets/image/june.jpg"
                    alt="june.jpg"
                    width="150"
                    height="150"
                    style="opacity: 1; transition: opacity 0.2s ease 0s;"
                  />
                </span>
              </div>
              <div class="info">
                <span class="title">쭈니.jpg</span>
              </div>
            </label>
          </li>
        </ul>
      </div>
    </div>
    <quick-menu
      :menu-count="count"
      :icon-class="icons"
      :menu-url-list="list"
      :position="position"
      :background-color="backgroundColor"
    />
  </content>
</template>

<script>
import quickMenu from "vue-quick-menu";

export default {
  components: {
    quickMenu,
  },
  data() {
    return {
      count: 3,
      icons: ["fa fa-github", "fa fa-comment", "fa fa-code"],
      list: [
        { isLink: true, url: "/" },
        { isLink: true, url: "/" },
        { isLink: true, url: "/" },
      ],
      position: "bottom-right",
      backgroundColor: "#474346",
    };
  },
};
</script>

<style scoped>
hr {
  border: solid 1px #e3e2e1;
  margin-top: 10px;
  margin-bottom: 10px;
}
.content {
  position: relative;
  margin: 0 auto;
  padding-top: 80px;
  background-color: white;
  width: 100%;
  height: 100%;
  font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}
user agent stylesheet div {
  display: block;
}

/* top div */
input[id="check"] + label {
  display: inline-block;
  width: 30px;
  height: 30px;
  border: 1px solid #e3e2e1;
  cursor: pointer;
  margin-right: 10px;
  vertical-align: top;
  text-align: center;
  border-radius: 4px;
}
input[id="check"] {
  display: none;
}
input[id="check"]:checked + label {
  background-color: #e3e2e1;
}
input[id="check"]:checked + .check-mark:after {
  content: "";
  position: absolute;
  width: 10px;
  transition: 0.1s;
  height: 5px;
  top: 45%;
  left: 50%;
  border-left: 2px solid #fff;
  border-bottom: 2px solid #fff;
  transform: translate(-50%, -50%) rotate(-45deg);
}

.b1 {
  background-color: white;
  color: #474346;
  border: 1px solid #d2cdc5;
  margin-right: 10px;
  border-radius: 4px;
  width: 100px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
}
.b2 {
  background-color: #d2cdc5;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 100px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
}
.b3 {
  background-color: #a49988;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 100px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
}
.b4 {
  background-color: #474346;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  text-align: center;
  width: 100px;
  height: 30px;
  font-size: 12px;
  border: none;
  outline: none;
}
button:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
select:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
input:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
.top-content {
  padding-bottom: 18px;
  padding-left: 235px;
}
.bottom-content {
  padding-left: 235px;
}

.search {
  float: right;
  padding-right: 15px;
}
.key {
  background-color: #a49988;
  color: white;
  width: 60px;
  height: 30px;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  text-align-last: center;
  font-size: 12px;
  outline: none;
  vertical-align: middle;
}
option {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  text-align: center;
}
option:hover {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  text-align-last: center;
  font-size: 12px;
  outline: none;
}
.value {
  border: 1px solid #a49988;
  width: 300px;
  height: 30px;
  text-align: center;
  font-size: 12px;
  outline: none;
  vertical-align: middle;
}
.submit {
  background-color: #a49988;
  width: 30px;
  height: 30px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  text-align: center;
  outline: none;
  vertical-align: middle;
}

/* bottom div */

img {
  border: 2px solid #e3e2e1;
}
.li {
  float: left;
  margin-bottom: 10px;
}
.thumb {
  width: 150px;
  height: 150px;
  border: 2px solid #e3e2e1;
}
.info {
  margin-top: 11px;
  background-color: white;
  width: 150px;
}
.title {
  display: block;
  font-size: 12px;
  line-height: 15px;
  background-color: white;
  width: 150px;
  text-align: center;
}
</style>
