<template>
  <div>
    <div>
      <div class="background">
        <img :src="imgSrc" width="100%" height="90%" alt="" />
      </div>
      <!--导航栏-->
      <nav-bar></nav-bar>
      <!--搜索栏-->
      <search-box></search-box>
    </div>
    <div>
      <!--导航地址卡片-->
      <div class="box" v-for="item in boxList">
        <web-box :title="item.title" :links="item.links"></web-box>
      </div>
    </div>
    <!--网站协议-->
    <div style="margin-top: 200px">
      <span style="font-size: 50px">网站作者：ffw</span>
    </div>
  </div>

</template>

<script>
  import 'bootstrap/dist/css/bootstrap.min.css'
  import 'bootstrap/dist/js/bootstrap.min.js'
  import 'jquery/dist/jquery'
  import navBar from './NavigationBar'
  import searchBox from './SearchBox'
  //导航网站
  import WebBox from './box/WebBox'
  export default {
    name: 'Home',
    created() {
      this.init();
    },
    components:{
      navBar,
      searchBox,
      WebBox
    },
    data () {
      return {
        imgSrc:require('../assets/background2.png'),
        boxList: []
      }
    },
    methods:{
      init(){
        this.boxList = [
          {"title":"购物",links:[{"text":"京东"},{"text":"淘宝"},{"text":"闲鱼"},{"text":"苏宁易购"}]},
          {"title":"学习论坛",links:[{"text":"知乎","link":"https://www.zhihu.com"},{"text":"V2ex","link":"https://www.v2ex.com/"},{"text":"牛客网"},{"text":"谷歌"}]},
          ];
      },
      handleSelect(){

      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .background {
    width:100%;
    height:100%;  /**宽高100%是为了图片铺满屏幕 **/
    z-index:-1;
    position: absolute;
  }
  .box{
    display:inline-block;
    margin-top: 31%;
    width: 40%;
  }

</style>
