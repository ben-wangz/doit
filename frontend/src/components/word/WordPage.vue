<template>
  <div>
    <WordTable ref="wordTable"></WordTable>
    <AddWordForm v-on:refreshed="refresh"></AddWordForm>
  </div>
</template>

<script>
import WordTable from "./WordTable";
import AddWordForm from "./AddWordForm";

export default {
  name: 'WordPage',
  components: {WordTable, AddWordForm},
  methods: {
    refresh() {
      this.$refs.wordTable.refresh()
    }
  }
}
</script>

<style scoped>
</style>
