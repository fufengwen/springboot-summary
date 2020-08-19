<template>
    <div>
      <el-row>
        <el-col :span="12">
          <div class="head">
            <span>{{title}}</span>
          </div>
          <div class="body" v-for="item in links">
            <el-button round @click="toLink(item.link)" style="outline:none">{{item.text}}</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "WebBox",
        created(){

        },
        props:{
          title:"",
          links:[]
        },
        data(){
          return{

          }
        },
        methods:{
          toLink(link){
            if(link == undefined || link == ''){

            }else{
              window.open(link);
            }

          }
        }
    }
</script>

<style scoped>
  .head{
    background: aliceblue;
  }
  .body{
    background: white;
    display:inline-block;
    margin-top: 10px;
    width: 88px;
    height: 24px;
  }
</style>
