<template>
  <content>
    <div class="content">
      <div class="top-content">
        <div class="agree">
          <div class="all_agree">
            <input type="checkbox" id="a1" name="전체동의" />
            <label for="a1"></label>
          </div>
        </div>
        <button class="b1">휴지통 비우기</button>
        <button class="b2">폴더</button>
        <button class="b3">사진</button>
        <button class="b4">동영상</button>
        <form class="search">
          <select id="key" name="keyword" class="key">
            <option value="file_origin_name">파일명</option>
            <option value="contents_atribute">확장자</option>
            <option value="contents_reg_date">등록일</option>
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
              class="search-icon"
            />
          </button>
        </form>
      </div>
      <hr class="top-hr" />

      <div class="bottom-content">
        <ul class="list_thumb">
          <li class="li" title="2020">
            <label for>
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
            <label for>
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
  </content>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      count: 2,
      icons: ["fa fa-folder", "fa fa-file"],
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
  margin-bottom: 30px;
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
.agree {
  padding-right: 10px;
}
.agree input[type="checkbox"] {
  display: none;
}
.agree input[type="checkbox"] + label {
  width: 30px;
  height: 30px;
  background: #d2cdc5;
  cursor: pointer;
  border-radius: 3px;
  float: left;
  margin-right: 10px;
}
.agree input[type="checkbox"] + label:hover {
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
}
.agree input[type="checkbox"]:checked + label {
  background: url(../assets/image/check.png) #d2cdc5 no-repeat center/20px 20px;
  float: left;
}
.agree input[type="checkbox"] + label span {
  position: absolute;
  left: 0px;
  display: block;
}
.b1 {
  background-color: white;
  color: #474346;
  border: 1px solid #d2cdc5;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
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
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
  border: none;
}
.b3 {
  background-color: #a49988;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  width: 60px;
  height: 30px;
  font-size: 12px;
  text-align: center;
  outline: none;
  border: none;
}
.b4 {
  background-color: #474346;
  color: white;
  margin-right: 10px;
  border-radius: 4px;
  text-align: center;
  width: 60px;
  height: 30px;
  font-size: 12px;
  border: none;
  outline: none;
  border: none;
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
  background-color: #a49988;
}
option:before {
  content: ">";
  font-size: 20px;
  display: none;
  padding-right: 10px;
  padding-left: 5px;
  color: #fff;
}
option:hover:before {
  display: inline;
}
input {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}
.value {
  border: 1px solid #a49988;
  width: 200px;
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
  border: none;
}
.search-icon {
  border: none;
}
/* bottom div */
img {
  border: 2px solid #e3e2e1;
}
.li {
  float: left;
  margin-bottom: 10px;
  margin-right: 15px;
}
.thumb {
  width: 150px;
  height: 150px;
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
