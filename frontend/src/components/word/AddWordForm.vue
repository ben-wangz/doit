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

export default {
  name: "WordTable",
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
      console.log('submit!');
    }
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
