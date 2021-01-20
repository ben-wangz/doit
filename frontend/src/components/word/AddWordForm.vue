<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="单词">
      <el-input v-model="form.word"></el-input>
    </el-form-item>
    <el-form-item label="描述">
      <el-input type="textarea" v-model="form.description"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
    </el-form-item>
  </el-form>
</template>
<script>

import axios from "axios";

export default {
  name: "AddWordForm",
  data() {
    return {
      form: {
        word: '',
        description: '',
      }
    }
  },
  methods: {
    onSubmit() {
      const self = this
      axios.post('/api/word/add', null, {
        params: {
          'word': self.form.word,
          'description': self.form.description,
        },
      }).then(function (response) {
        let responseBackend = response.data
        if (!responseBackend.success) {
          console.error(response)
          return
        }
        self.$emit('refreshed')
      }).catch(function (error) {
        console.error(error);
      });
    },
  }
}
</script>
<style>
</style>
