<template>
  <div>
    <el-row>
      <span style="font-size: 50px;align:center;color: aliceblue">X导航</span>
    </el-row>
    <el-row>
      <div style="margin-top: 15px;">
        <el-input placeholder="输入搜索内容..." v-model="searchText" class="input-with-select" type="text" autofocus="true">
          <el-select v-model="searchEngines" slot="prepend" placeholder="请选择" style="width: 100px">
            <el-option label="百度" value="www.baidu.com"></el-option>
            <el-option label="谷歌" value="www.google.com"></el-option>
            <el-option label="知乎" value="www.zhihu.com"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
        </el-input>
      </div>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "SearchBox",
      created() {
        //搜索引擎初始化
        this.searchEngines = 'www.baidu.com';
      },
      data(){
        return {
          searchEngines:'',
          searchText:'',
        }
      },
      methods:{
        search(){
          if(Object.isNotNull(this.searchText)){
            if(this.searchEngines.indexOf("baidu") != -1){
              window.open("https://"+this.searchEngines+"/s?ie=utf-8&f=8&rsv_bp=1&ch=&tn=baiduerr&bar=&wd="+this.searchText);
            }
          }
        }
      }
    }
</script>

<style scoped>
  .input-with-select{
    width: 60%;
    align:center;
  }

  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
