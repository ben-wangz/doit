<template>
  <div class="tool-list-wrapper">
    <el-collapse v-model="activeCategorys">
    <el-collapse-item v-for="tool in list" :key="tool.category" :name="tool.category">
      <template slot="title">
        <div class="category">{{tool.category}}</div>
      </template>
      <el-row :gutter="12">
        <el-col v-for="topic in tool.topics" :key="topic.topic" :span="6" style="margin-bottom: 12px">
          <a :href="topic.targetUrl" class="topic" target="_blank">
            <el-card shadow="hover">
                <strong>{{topic.topic}}</strong>
                <p>{{topic.summary}}</p>
            </el-card>
          </a>
        </el-col>
      </el-row>
    </el-collapse-item>
  </el-collapse>
  </div>
</template>
<script>
/**
data format:
 * [{
 *  category: {
 *    name: string;
 *    topics: [{
 *      topic: string; // 名称
 *      summary: string;  // 简介
 *      desc?: string; // 详情
 *      poster?: url;  // icon图片链接
 *      status?: online | disabled | offline; // 上线、置灰、下线
 *      targetUrl?: url; 跳转链接
 *    }]
 *  }
 * }]
 */
const mockTools = [
  {
    category: 'Java',
    topics: [
      {
        topic: 'springboot1',
        summary: '关于springboot的一些理解',
        status: 'online',
        targetUrl: 'https://github.com/ben-wangz/doit'
      }
    ]
  },
  {
    category: '大数据',
    topics: [
      {
        topic: 'kafka',
        summary: '老司机开车很快，请坐稳',
        status: 'disabled',
        targetUrl: 'https://github.com/ben-wangz/interview/blob/main/kafka'
      }
    ]
  }
]
export default {
  name: "ToolList",
  data() {
    return {
     list: [],
     activeCategorys: []
    }
  },
  mounted() {
    this.loadToolList();
  },
  methods: {
    loadToolList() {
      // 这里可以模拟接口获取，支持配置化
      this.list = mockTools;
      this.activeCategorys = mockTools.map(c => c.category);
    }
  }
}
</script>
<style>
.tool-list-wrapper .el-collapse {
  border: none;
}
.tool-list-wrapper .el-collapse-item {
  background: #f9f9f9;
  margin-bottom: 30px;
  border: 1px solid #f2f2f2;
}
.tool-list-wrapper .el-collapse-item__wrap {
  border-bottom: none;
}
.tool-list-wrapper .el-collapse-item__content {
  padding: 10px;

}
.tool-list-wrapper .el-collapse-item__header {
  background: #e2ece7;
  padding-left: 10px;
}
.tool-list-wrapper .category {
  font-weight: 500;
  font-size: 18px;
}
.tool-list-wrapper .topic {
  cursor: pointer;
  text-decoration: none;
}
</style>
