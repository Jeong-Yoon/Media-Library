<template>
  <!-- <div class="login-bg"></div> -->
  <div id="sign-in">
    <div class="group">
      <h1 class="login">
        <a>
          로그인
        </a>
      </h1>
      <form class="form" @submit.prevent="submitForm">
        <div class="insert-area">
          <label for="email">
            <input
              id="email"
              v-model="email"
              type="text"
              class=""
              placeholder="이메일"
            />
            <p class="validation-text">
              <span v-if="!isUseremailValid && username" class="warning">
                Please enter an email address
              </span>
            </p>
          </label>
          <label for="password" class="password">
            <input
              id="password"
              v-model="password"
              type="password"
              class=""
              title=""
              autocomplete="off"
              placeholder="비밀번호"
            />
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
              />
              <!-- <input type="checkbox" class="checkbox" id="save_email"> -->
              <span class="check-mark" />
              <span>이메일 저장</span>
            </label>
          </div>
        </div>
        <input
          :disabled="!isUseremailValid || !password"
          type="submit"
          class="btn btn-login"
          value="로그인"
        />
      </form>

      <hr class="hr1" />

      <h5 class="signup">
        <!-- <a href="/views/SignupPage.vue"> -->
        <a> <router-link to="/signup">회원가입</router-link> </a>
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
import { validateEmail } from "@/utils/validation";

export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  computed: {
    isUseremailValid() {
      return validateEmail(this.email);
    },
  },
  methods: {
    async submitForm() {
      try {
        const userData = {
          email: this.email,
          password: this.password,
        };
        await this.$store.dispatch("LOGIN", userData);
        console.log(userData);
        console.log(this.email + ":" + this.password);
        this.$router.push("/main");
      } catch (error) {
        console.log(error.response.data);
      } finally {
        console.log(this.email + ":" + this.password);
        this.initForm();
      }
    },
    initForm() {
      (this.email = ""), (this.password = "");
    },
  },
};
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

button:focus {
  border: none;
}

#sign-in {
  text-align: center;
  padding: 80px 70px;
  width: 470px;
  position: fixed;
  left: 50%;
  top: 50%;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
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
      display: block;
      width: 100%;
      border: 1px;
      margin-bottom: 10px;
      font-size: 0;
      color: #e2e2e2;
      input {
        border: none;
        padding: 13px 15px;
        background: #e3e2e1;
        width: 100%;
        font-size: 16px;
        box-sizing: border-box;
        color: #333333;
        &:focus {
          outline: 0;
        }
      }
    }

    .login-option {
      margin-top: 15px;
      margin-bottom: 15px;
    }

    .login-option .label {
      position: relative;
      display: block;
    }

    .login-option .check-mark {
      content: "";
      width: 20px;
      height: 20px;
      background-color: #d3d3d3;
      position: absolute;
      left: 0;
      border-radius: 50%;
      transition: 0.1s;
    }

    .login-option .label .checkbox:checked + .check-mark {
      background-color: #ffbf00;
      transition: 0.1s;
    }
    .login-option .label .checkbox:checked + .check-mark:after {
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

    .btn-login {
      display: nome;
      width: 100%;
      height: 50px;
      line-height: 46px;
      background: #a49988;
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

  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
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
  font-size: 15px;
  color: #666666;
  float: left;
}

.forget a {
  font-size: 15px;
  color: #666666;
  float: right;
}
</style>
