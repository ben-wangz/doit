<template>
  <el-table :data="allWord" style="width: 100%">
    <el-table-column prop="id" label="id"></el-table-column>
    <el-table-column prop="word" label="单词"></el-table-column>
    <el-table-column prop="description" label="描述"></el-table-column>
  </el-table>
</template>
<script>
import axios from "axios";

export default {
  name: "WordTable",
  data() {
    return {
      'allWord': []
    };
  },
  created() {
    const self = this
    axios.post('/api/word/all?limit=5', {
      limit: '-1',
    }).then(function (response) {
      self.allWord = response.data.data
    }).catch(function (error) {
      console.log(error);
    });
  }
}
</script>
<style>
.text_center {
  text-align: center;
}

.footer_column_size {
  height: 50px;
}
</style>
