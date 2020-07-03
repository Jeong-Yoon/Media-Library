<template>
  <div id="join-in">
    <div class="group">
      <h1 class="signup">
        <a>
          회원가입
        </a>
      </h1>
      
      <form
        class="form"
        @submit.prevent="submitForm"
      >
        <div class="insert-area">
          <input
            id="email"
            v-model="email"
            type="email"
            class="button"
            title=""
            autocomplete="off"
            placeholder="이메일"
          >
          <input 
            id="submit" 
            type="submit"
            class="button" 
            value="중복확인"  
            :disabled="!isUseremailValid && email" 
            @click="checkEmail"
          >
          <p class="validation-text">
            <span
              class="warning"
            >
              {{ checkMessage }}
            </span>
          </p>
        </div>
    
     
        <div>
          <input 
            id="password"
            v-model="password"
            type="password"
            class="button"
            title=""
            autocomplete="off"
            placeholder="비밀번호"
          >
        </div>
    
     
        <div>
          <input 
            id="password2"
            v-model="password2"
            type="password"
            class="button"
            title=""
            autocomplete="off"
            placeholder="비밀번호확인"
          >
      
          <p class="validation-text">
            <span
              v-if="!isPasswordSame && password2"
              class="warning"
            >
              비밀번호가 일치하지 않습니다.
            </span>
          </p>
        </div>
    
        <div>
          <input  
            id="name"
            v-model="name"
            type="text"
            class="button" 
            title=""
            autocomplete="off"
            placeholder="이름"
          >
        </div>
    
        <div>
          <input 
            id="phone"
            v-model="phone"
            type="tel"
            class="button"
            name="phone"
            placeholder="휴대전화"
          >
        </div>
    
        <div>
          <input 
            id="company"
            v-model="company"
            type="text"
            class="button"
            title=""
            autocomplete="off"
            placeholder="회사명"
          >
        </div>
    
        <div>
          <input 
            id="department"
            v-model="department"
            type="text"
            class="button"
            title=""
            autocomplete="off"
            placeholder="부서명"
          >
        </div>
    
        <div>
          <input
            id="job"
            v-model="job"
            type="text"
            class="button"
            title=""
            autocomplete="off"
            placeholder="직책"
          >
        </div>
    
        
    
        <div class="join-option">
          <button
            :disabled="!isPasswordSame || !isUseremailValid || !email || !password || !password2 || !name || 
              !phone || !company || !department || !job"
            type="submit"
            class="btn btn-join"
          >
            회원가입
          </button>
        </div>
      </form>
    </div>
  </div>
</template>




  <script>
    import { validateEmail } from '@/utils/validation';
    import { mapActions } from 'vuex';


  export default {
    data(){
      return {
        email: '',
        password: '',
        password2:'',
        name:'',
        phone:'',
        company:'',
        department:'',
        job:'',
        returnPath:'',
        check:'',
        checkMessage:'',
        emailchk:''
      };
    },
    computed: {
      isUseremailValid(){
        return validateEmail(this.email);
      },
      isPasswordSame(){
        return this.password === this.password2 ? true : false;
      },
      isEmailSame(){
        return (this.check && this.email === this.emailchk) ? true : false;
      },
    },
    create(){
      this.returnPath = this.$route.query.rPath || '/login';
    },
    methods:{
      ...mapActions(['CHECK_EMAIL','SIGNUP']),
      checkEmail(){
        this.CHECK_EMAIL({email:this.email}).then((result)=>{
          this.checkMessage =  result === 0 ? '사용 가능한 메일입니다.' : '이미 사용중인 메일입니다.';
          this.check = result === 0 ? true : false;
          this.emailchk = this.email;
        }) 
      },
      submitForm(){
        if(!this.isEmailSame || this.emailchk===''){
          this.checkMessage = '이메일 중복확인이 필요합니다.'
        }
        else{
          this.SIGNUP({
            email : this.email,
            password : this.password,
            password2: this.password2,
            name:this.name,
            phone:this.phone,
            company:this.company,
            department:this.department,
            job:this.job,
          })
          .then(() => {
            alert('회원 가입을 완료했습니다.')
            this.$router.push('/login');
          })
          .catch(error => {
            alert('회원가입을 실패했습니다.')
            this.error = error.data.error;
            this.initForm();
          });
        }
      },
      initForm(){
        this.email='',
        this.password= '',
        this.password2='',
        this.name='',
        this.phone='',
        this.company='',
        this.department='',
        this.job='',
        this.returnPath='',
        this.check='',
        this.checkMessage=''
      },
    }
  }
  </script>

 <style lang="scss" scoped>

#join-in{
   text-align: center;
    padding: 80px 70px;
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    // transform: translate(-50%,-50%);
    // text-align: center;
    // position: relative;
    // padding: 80px 70px;
    // width: 470px;
    // // position: fixed;
    // left: 30%;
    // // top: 50%;

  a {
    font-size: 30px;
    color: #666666;
    display: block;
    margin-bottom: 50px;

}

form {
  width: 450px;
  margin: auto;
}

form div{
  margin-bottom : 10px;
}
}



.insert-area {
  display: flex;
  align-items: center;
}

.button {
  height: 44px;
  border: none;
}
  
#email {
   border: none;
    padding: 13px 15px;
    background: #e3e2e1;
    width: 100%;
    font-size: 16px;
    box-sizing: border-box;
    color: #333333;
    display: block;
    width: 100%;
    border: 1px;
    &:focus {
                outline: 0;
              }
  }

#password {
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

#password2 {
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

#name {
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

#phone {
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

#company {
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

#department {
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

#job {
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


#submit {
  width: 25%;
  height: 46px;
  background-color: #a49988;
  color: #fff;
/*   font-weight: bold; */
/*   color: inherit; */
  letter-spacing: 1px;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  transition: background .3s ease-in-out;
}

  
.btn-join {
  display: nome;
  width: 100%;
  height: 50px;
  // / line-height: 46px;
  background: #474346;
  color: #ffffff;
  font-weight: 400;
  margin-bottom: 30px;
  font-size: 17px;
}
</style>