<template>
  <j-modal
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel">
     <erp-collect-data-form ref="realForm" @ok="submitCallback" :disabled="disableSubmit"/>
 </j-modal>
</template>

<script>

  import ErpCollectDataForm from './ErpCollectDataForm'

  export default {
    name: 'ErpCollectDataModal',
    components: {
      ErpCollectDataForm
    },
    data() {
      return {
        title:'',
        visible: false,
        disableSubmit: false
      }
    },
    methods:{
      add () {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.add();
        })
      },
      edit (record) {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.edit(record);
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        this.$refs.realForm.handleOk();
      },
      submitCallback(){
        this.$emit('ok');
        this.visible = false;
      },
      handleCancel () {
        this.close()
      }
    }
  }
</script>

<style scoped>
</style>