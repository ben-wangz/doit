<template>
  <div>
    <el-row>
      <el-table :data="allWord" style="width: 100%">
        <el-table-column prop="id" label="id"></el-table-column>
        <el-table-column prop="word" label="单词"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="deleteWord(scope.row.id)" type="text" size="small">删除</el-button>
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row>
      <el-button type="primary" round @click="refresh">刷新</el-button>
    </el-row>
  </div>
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
    self.refresh()
  },
  methods: {
    refresh() {
      const self = this
      axios.post('/api/word/all', null, {
        params: {
          'limit': -1,
        },
      }).then(function (response) {
        let responseBackend = response.data
        if (!responseBackend.success) {
          console.error(response)
          return
        }
        self.allWord = responseBackend.data
      }).catch(function (error) {
        console.log(error);
      });
    },
    deleteWord(id) {
      const self = this
      axios.post('/api/word/delete', null, {
        params: {
          'wordId': id,
        },
      }).then(function (response) {
        let responseBackend = response.data
        if (!responseBackend.success) {
          console.error(response)
        }
        self.refresh()
      }).catch(function (error) {
        console.log(error);
      });
    }
  }
}
</script>
<style>
</style>
