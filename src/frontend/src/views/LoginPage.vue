<template>
  <!-- <div class="login-bg"></div> -->
  <div id="sign-in">
    <div class="group">
      <h1 class="login">
        <a>
          로그인
        </a>
      </h1>
      <form
        class="form"
        @submit.prevent="submitForm"
      >
        <div class="insert-area">
          <label for="email">
            <input
              id="email"
              v-model="email"
              type="text"
              placeholder="이메일"
            >
            <p class="validation-text">
              <span
                v-if="!isUseremailValid && email"
                class="warning"
              >
                Please enter an email address
              </span>
            </p>
          </label>
          <label
            for="password"
            class="password"
          >
            <input
              id="password"
              v-model="password"
              type="password"
              autocomplete="off"
              placeholder="비밀번호"
            >
          </label>
        </div>
        <div class="login-option">
          <div class="input-checkbox">
            <label class="label">
              <input
                id="checkbox"
                type="checkbox"
                class="checkbox"
                checked="checked"
              >
              <!-- <input type="checkbox" class="checkbox" id="save_email"> -->
              <span class="check-mark" />
              <span>이메일 저장</span>
            </label>
          </div>
        </div>
        <button
          :disabled="!isUseremailValid || !password"
          type="submit"
          class="btn btn-login"
        >
          로그인
        </button>
      </form>
    
      <hr class="hr1">
      
      <h5 class="signup">
        <!-- <a href="/views/SignupPage.vue"> -->
        <a> <router-link to="/signup">회원가입</router-link>
        </a>
      </h5>
      
      <h5 class="forget">
        <a href="#!">
          아이디 / 비밀번호 찾기
        </a>
      </h5>
    </div>
  </div>
</template>


<script>
import { validateEmail } from '@/utils/validation';
import { mapActions } from 'vuex'

// const user = 'user'

export default {
  // props: {
  //   source: String
  // },
  data(){
    return {
      email : '',
      password : '',
      returnPath: ''
    };
  },
  computed: {
    isUseremailValid(){
      return validateEmail(this.email);
    },
  },
  created(){
    this.returnPath = this.$route.query.rPath || '/';
  },
  methods: {
    ...mapActions(['LOGIN']),
    submitForm(){
      this.LOGIN({ email: this.email, password: this.password })
      .then(() => {
        this.parseJwt(this.$store.state.token);
        this.$router.push(this.returnPath);
      })
      .catch(error => {
        this.error = error.data.error;
        alert("아이디 혹은 비밀번호가 일치하지 않습니다.")
        this.initForm();
      });
    },
    parseJwt(token) {
      try {
        var base64Url = token.split(".")[1];
        var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
        var jsonPayload = decodeURIComponent(
          atob(base64)
            .split("")
            .map(function(c) {
              return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
            })
            .join("")
        );
        console.log(JSON.parse(jsonPayload));
        this.$store.commit("setUserInfo", JSON.parse(jsonPayload));
        console.log(this.$store.state.userInfo.username);

        return JSON.parse(jsonPayload);
      } catch (e) {
        console.log(e);
      }
    },
    initForm(){
      this.email = '',
      this.password = ''
    }
  }
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




#sign-in{
    text-align: center;
    padding: 80px 70px;
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    @include screen-sm {
        width: 98%;
        padding: 50px 30px;
        max-width: 470px;
    }
    h1.login a {
        font-size: 30px;
        color: #666666;
        display: block;
        margin-bottom: 50px;
        
    }
    form {
        .insert-area label {
            // display: block;
            width: 100%;
            // border: 1px;
            // margin-bottom: 10px;
            font-size: 0;
            color: #e2e2e2;
            input {
                border: none;
                padding: 13px 15px;
                background: #e3e2e1;
                width: 100%;
                font-size: 16px;
                // box-sizing: border-box;
                color: #333333;
                &:focus {
                outline: 0;
              }
            }
        }
      
        .login-option {
              margin-bottom: 25px;
        }
        
          .login-option .label{
            position: relative;
            display: block;

          }

          .login-option .check-mark{
           
            content: "";
            width: 20px;
            height: 20px;
            background-color: #e3e2e1;
            position: absolute;
            left : 160px;
            border-radius: 50%;
            transition: .1s;
            margin-bottom: 10px;
          }


      .login-option .label .checkbox:checked + .check-mark{
        
        background-color: #a49988;
        transition: .1s;
      }
      .login-option .label .checkbox:checked + .check-mark:after{
        
        content: "";
        position: absolute;
        width: 10px;
        transition: .1s;
        height: 5px;
        top:45%;
        left:50%;
        border-left: 2px solid #fff;
        border-bottom: 2px solid #fff;
        transform: translate(-50%, -50%) rotate(-45deg);  
      }

        
      .btn-login {
            display: nome;
            width: 100%;
            height: 50px;
            // line-height: 46px;
            background: #474346;
            color: #ffffff;
            font-weight: 400;
            margin-bottom: 30px;
            font-size: 17px;
        }
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



.hr1 {
    border: 0;
    height: 1px;
    background: #ccc;
    margin-bottom: 30px;
  }

.signup a {
       font-size : 15px;
       color : #666666;
       float: left;
       
}

.forget a {
       font-size : 15px;
       color : #666666;
       float: right;
}



</style>