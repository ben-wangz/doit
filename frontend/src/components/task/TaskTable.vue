<template>
  <el-table :data="allTask" style="width: 100%">
    <el-table-column prop="id" label="id"></el-table-column>
    <el-table-column prop="title" label="标题"></el-table-column>
    <el-table-column prop="description" label="描述"></el-table-column>
    <el-table-column prop="finishTime" label="完成时间"></el-table-column>
    <el-table-column prop="unit" label="周期单位"></el-table-column>
    <el-table-column prop="nextPeriod" label="下一周期"></el-table-column>
  </el-table>
</template>
<script>
import axios from "axios";

export default {
  name: "TaskTable",
  data() {
    return {
      'allTask': []
    };
  },
  created() {
    const self = this
    axios.post('/api/task/all?limit=5', {
      limit: '5',
    }).then(function (response) {
      let taskList = response.data.data;
      taskList.forEach(task => {
        task.finishTime = new Date(task.timestampInMs).toLocaleString("en-US")
      })
      self.allTask = taskList
      console.log(self.allTask)
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
