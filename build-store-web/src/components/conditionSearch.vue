<template>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "conditionSearch",
    data() {
      return {
        searchForm: {}
      };
    },
    methods: {
      // 条件查询
      searchByCondition () {
        let url = 'http://localhost:8080/buildingBean/getAllBuildByPage'
        axios.post(url, this.searchForm).then(function (response) {
          if (response.data.retCode === '0000') {
            if (response.data.retData.records.length > 0) {
              return response.data.retData
            } else {
            alert(response.data.retMsg)
          }
        }
        }).catch(function (error) {
          console.log(error);
        });
      }
  },
    mounted: function () {
      this.searchByCondition()
    }
  }
</script>

<style scoped>

</style>
