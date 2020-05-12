// import '../../libs/node_modules/vue/types/index'



new Vue({
  el: '#signupPageId',
  data: {
    message: 'Hello Vue!',
    validationErrorTimer: null
  },
  methods: {
    clearValidationErrors: function(){
      if(this.$refs.errorMsgEmail){
        this.$refs.errorMsgEmail.remove()
        this.$refs.emailFormInput.classList.remove('is-invalid')
      }      
      if(this.$refs.errorMsgPassword){
        this.$refs.errorMsgPassword.remove()
        this.$refs.passwordFormInput.classList.remove('is-invalid')
      }
    },
    fetchCaptcha: function (){
      this.$refs.captchaLoad.classList.remove('d-none')
      this.$refs.captchaImg.classList.add('d-none')

      fetch('/api/captcha')
        .then(res => res.json()
          .then(data => this.showCaptchaImage(data.captcha))
          .catch(e=> console.error(e))
        ).catch(e=> console.error(e))
    
    },
    showCaptchaImage: function(byteArray){
      const TYPED_ARRAY = new Uint8Array(byteArray)
      const str = TYPED_ARRAY.reduce((acc, cur) => acc + String.fromCharCode(cur), '')
      const image = 'data:image/jpg;base64,' + btoa(str)
      this.$refs.captchaImg.classList.remove('d-none')
      this.$refs.captchaImg.src = image
      this.$refs.captchaLoad.classList.add('d-none')
    }
  },
  mounted(){
    this.validationErrorTimer = setTimeout(this.clearValidationErrors, 4000)
    this.fetchCaptcha()
  }
})