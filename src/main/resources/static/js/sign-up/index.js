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
    }
  },
  mounted(){
    this.validationErrorTimer = setTimeout(this.clearValidationErrors, 3000)
  }
})