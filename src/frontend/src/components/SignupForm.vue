<template>
  <div id="join-in">
    <div class="group">
      <h1 class="signup">
        <a>
          회원가입
        </a>
      </h1>
      <form action="">
        <div class="insert-area">
          <label
            for="email"
            class=""
          >
            <input
              id="email"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="이메일"
            >
          </label>
          <label
            for="password"
            class=""
          >
            <input
              id="password"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="비밀번호"
            >
          </label>
          <label
            for="password"
            class=""
          >
            <input
              id="password2"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="비밀번호확인"
            >
          </label>
          <label
            for="name"
            class=""
          >
            <input
              id="name"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="이름"
            >
          </label>
          <label
            for="phone"
            class="phone"
          >
            <input
              type="tel"
              name="phone"
              placeholder="휴대전화"
            >
          </label>
          <label
            for="text"
            class=""
          >
            <input
              id="company"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="회사명"
            >
          </label>
          <label
            for="text"
            class=""
          >
            <input
              id="dept"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="부서명"
            >
          </label>
          <label
            for="texts"
            class=""
          >
            <input
              id="postion"
              type="text"
              class=""
              title=""
              autocomplete="off"
              placeholder="직위"
            >
          </label>
        </div>
        <div class="join-option" />
        <input
          type="submit"
          class="btn btn-join"
          value="회원가입"
        >
      </form>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>


// Media queries
@mixin screen-lg {
  @media only screen and (max-width: 1200px) {
    @content;
  }
}
@mixin screen-md {
  @media only screen and (max-width: 992px) {
    @content;
  }
}
@mixin screen-sm {
  @media only screen and (max-width: 768px) {
    @content;
  }
}
@mixin screen-xs {
  @media only screen and (max-width: 542px) {
    @content;
  }
}

.input-checkbox {
  input[type="checkbox"] {
    visibility: hidden;
    position: absolute;
    left: 0;
    top: 0;
  }
  label {
    cursor: pointer;
    display: inline-block;
    i {
      display: block;
      float: left;
      font-size: 23px;
      
    }
   }

  input + label {
    i.is-checked {
      display: none;
    }
  }
  input:checked + label {
    i.is-outline {
      display: none;
    }
    i.is-checked {
      display: block;
      color: #00b8be;
    }
  }

  .condition-detail {
    font-size: 14px;
    color: #666;
    font-weight: 400;
    margin-left: 30px;
     &:hover {
      text-decoration: underline;
    
    }
  }
}
.signup-bg {
 
    height: 100vh;
    -webkit-filter: blur(15px);
    -moz-filter: blur(15px);
    -o-filter: blur(15px);
    -ms-filter: blur(15px);
    filter: blur(15px);
}
#join-in{
    text-align: center;
    padding: 80px 70px;
    width: 470px;
    // background-color: rgba(233, 233, 233,0);
    position: fixed;
    left: 50%;
    top: 50%;
    -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;
    -webkit-transform: translate(-50%,-50%);
    -moz-transform: translate(-50%,-50%);
    -ms-transform: translate(-50%,-50%);
    -o-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%);
    @include screen-sm {
        width: 98%;
        padding: 50px 30px;
        max-width: 470px;
    }
    h1.signup a {
        font-size: 30px;
        color: #666666;
        display: block;
        margin-bottom: 50px;
        
    }
    form {
        .insert-area label {
            display: block;
            width: 100%;
            border: 1px;
            margin-bottom: 10px;
            font-size: 0;
            color: #e2e2e2;
            input {
                border: none;
                padding: 13px 15px;
                background: #eeeeee;
                width: 100%;
                font-size: 16px;
                box-sizing: border-box;
                color: #333333;
                &:focus {
                outline: 0;
              }
            }
        }
        .join-option {
             margin-bottom: 30px;
          
            .input-checkbox {
                margin-left: -3px;
                text-align: center;
            }
        }
        .btn-join {
            display: block;
            width: 100%;
            height: 50px;
            line-height: 46px;
            background: #b3d929;
            color: #fff;
            // font-weight: 400;
            // margin-bottom: 3px;
            font-size: 17px;
        }
    }
    .input-checkbox label span{
        color: #666666;
    }
    .input-checkbox label i {
        color: #444444;
    }
    .input-checkbox input:checked + label i.is-checked {
        color: #666666;
    }
}


* {
  list-style: none;
  text-decoration: none;
  box-sizing: border-box;
}

.btn {
    display: inline-block;
    font-size: 13px;
    font-weight: 300;
    // background: transparent;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    border: 1px solid transparent;
  
    -webkit-transition: all 0.3s; -moz-transition: all 0.3s; -ms-transition: all 0.3s; -o-transition: all 0.3s; transition: all 0.3s;
}
.btn:hover {
    text-decoration: none;
}



</style>