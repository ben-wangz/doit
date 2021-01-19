<template>
  <div>
    <TaskTable></TaskTable>
  </div>
</template>

<script>
import TaskTable from "./task/TaskTable";

export default {
  name: 'HomePage',
  components: {TaskTable},
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  color: #42b983;
}
</style>
