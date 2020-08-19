<template>
  <div class="body" style="align:center">
    <el-row>
      <el-col :span="3">
        <img :src="imgSrc" width="120px" height="60px" alt="" />
      </el-col>
      <el-col :span="21">
        <el-menu
          :default-active="1"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#272727"
          text-color="#f0f8ff"
          active-text-color="#ffd04b">
          <el-menu-item index="1">
            <template slot="title">
              <i class="el-icon-s-home"></i>
              <span>首页</span>
            </template>
          </el-menu-item>
          <el-menu-item index="2">
            <template slot="title">
              <i class="el-icon-price-tag"></i>
              <span>网址导航</span>
            </template>
          </el-menu-item>
          <el-menu-item index="3">
            <template slot="title">
              <i class="el-icon-takeaway-box"></i>
              <span>工具箱</span>
            </template>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "NavigationBar",
      data(){
        return {
          imgSrc:require('../assets/logo.png')
        }
      },
      methods:{
        handleSelect(){

        }
      }
    }
</script>

<style scoped>
  .body{
    width: 80%;
    height:100%;
    margin:0 auto;
    z-index:1;
  }
  .el-menu{
    opacity:0.8
  }
  .el-menu-item{
    font-size: 25px;
  }
</style>
