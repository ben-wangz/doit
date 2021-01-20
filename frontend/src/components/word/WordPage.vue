<template>
  <div>
    <WordTable></WordTable>
    <AddWordForm></AddWordForm>
  </div>
</template>

<script>
import WordTable from "./WordTable";
import AddWordForm from "./AddWordForm";

export default {
  name: 'WordPage',
  components: {WordTable, AddWordForm},
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  color: #42b983;
}
</style>
