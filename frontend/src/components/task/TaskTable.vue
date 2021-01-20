<template>
  <div>
    <el-row>
      <el-table :data="allTask" style="width: 100%">
        <el-table-column prop="id" label="id"></el-table-column>
        <el-table-column prop="title" label="标题"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="finishTime" label="完成时间"></el-table-column>
        <el-table-column prop="unit" label="周期单位"></el-table-column>
        <el-table-column prop="nextPeriod" label="下一周期"></el-table-column>
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
  name: "TaskTable",
  data() {
    return {
      'allTask': []
    };
  },
  created() {
    const self = this
    self.refresh()
  },
  methods: {
    refresh() {
      axios.post('/api/task/all', null, {
        params: {
          'limit': -1,
        },
      }).then(function (response) {
        let responseBackend = response.data
        if (!responseBackend.success) {
          console.error(response)
          return
        }
        let taskList = responseBackend.data;
        taskList.forEach(task => {
          task.finishTime = new Date(task.timestampInMs).toLocaleString("en-US")
        })
        self.allTask = taskList
      }).catch(function (error) {
        console.log(error);
      });
    }
  }
}
</script>
<style>
</style>
