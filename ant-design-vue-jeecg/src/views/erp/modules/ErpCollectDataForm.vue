<template>
<a-spin :spinning="confirmLoading">

   <a-tabs v-model="activeKey" @change="handleChangeTabs">
   <!--主表区域 -->
    <a-tab-pane tab="erp_collect_data" :key="refKeys[0]" :forceRender="true">
         <a-form-model ref="form" :model="model" :rules="validatorRules">
           <a-row>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="目录ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="categoryId">
                      <a-input-number v-model="model.categoryId" placeholder="请输入目录ID" style="width: 100%" />
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="目录名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="categoryName">
                      <a-input v-model="model.categoryName" placeholder="请输入目录名称" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="重量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weight">
                      <a-input-number v-model="model.weight" placeholder="请输入重量" style="width: 100%" />
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="当前价格" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="price">
                      <a-input-number v-model="model.price" placeholder="请输入当前价格" style="width: 100%" />
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="货币单位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="currency">
                      <a-input v-model="model.currency" placeholder="请输入货币单位" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="主商品图片" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mainImageUrl">
                      <a-input v-model="model.mainImageUrl" placeholder="请输入主商品图片" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="是否当前" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isCurrenct">
                      <a-input v-model="model.isCurrenct" placeholder="请输入是否当前" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="链接平台" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="srcPlatform">
                      <a-input v-model="model.srcPlatform" placeholder="请输入链接平台" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="商品平台" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="purchasePlatform">
                      <a-input v-model="model.purchasePlatform" placeholder="请输入商品平台" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="商品链接" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="srcUrl">
                      <a-input v-model="model.srcUrl" placeholder="请输入商品链接" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="仓库" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="warehouseSpu">
                      <a-input v-model="model.warehouseSpu" placeholder="请输入仓库" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="商品长度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productLength">
                      <a-input v-model="model.productLength" placeholder="请输入商品长度" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="商品宽" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productWidth">
                      <a-input v-model="model.productWidth" placeholder="请输入商品宽" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="商品高" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productHigh">
                      <a-input v-model="model.productHigh" placeholder="请输入商品高" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="商品id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemId">
                      <a-input v-model="model.itemId" placeholder="请输入商品id" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="库存" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stock">
                      <a-input v-model="model.stock" placeholder="请输入库存" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="价格范围" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sourcePrice">
                      <a-input v-model="model.sourcePrice" placeholder="请输入价格范围" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createBy">
                      <j-select-user-by-dep v-model="model.createBy" />
                    </a-form-model-item>
                  </a-col>

                </a-row>
              </a-form-model>

     </a-tab-pane>
   <!--子表单区域 -->
     <a-tab-pane tab="erp_collect_language" :key="refKeys[1]" :forceRender="true">
       <j-editable-table
         :ref="refKeys[1]"
         :loading="erpCollectLanguageTable.loading"
         :columns="erpCollectLanguageTable.columns"
         :dataSource="erpCollectLanguageTable.dataSource"
         :maxHeight="300"
         :rowNumber="true"
         :rowSelection="true"
         :actionButton="true"/>
     </a-tab-pane>

     <a-tab-pane tab="erp_collect_image" :key="refKeys[2]" :forceRender="true">
       <j-editable-table
         :ref="refKeys[2]"
         :loading="erpCollectImageTable.loading"
         :columns="erpCollectImageTable.columns"
         :dataSource="erpCollectImageTable.dataSource"
         :maxHeight="300"
         :rowNumber="true"
         :rowSelection="true"
         :actionButton="true"/>
     </a-tab-pane>

   </a-tabs>

 </a-spin>
</j-modal>
</template>

<script>

import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
import { validateDuplicateValue } from '@/utils/util'
import { VALIDATE_NO_PASSED, validateFormModelAndTables } from '@/utils/JEditableTableUtil'

export default {
 name: 'ErpCollectDataForml',
 mixins: [JEditableTableModelMixin],
 components: {
 },
 data() {
   return {
     labelCol: {
       xs: { span: 24 },
       sm: { span: 6 },
     },
     wrapperCol: {
       xs: { span: 24 },
       sm: { span: 16 },
     },
     labelCol2: {
       xs: { span: 24 },
       sm: { span: 3 },
     },
     wrapperCol2: {
       xs: { span: 24 },
       sm: { span: 20 },
     },
     // 新增时子表默认添加几行空数据
     addDefaultRowNum: 1,
     model:{
     },
        validatorRules: {
        },
     refKeys: ['erpCollectData','erpCollectLanguage', 'erpCollectImage', ],
     tableKeys:['erpCollectLanguage', 'erpCollectImage', ],
     activeKey: 'erpCollectData',
     // erp_collect_language
     erpCollectLanguageTable: {
       loading: false,
       dataSource: [],
       columns: [
         {
           title: '集合外键',
           key: 'collectId',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '当前语言',
           key: 'currenctLanguage',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: 'title',
           key: 'title',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: 'description',
           key: 'description',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: 'optionJson',
           key: 'optionJson',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
       ]
     },
     // erp_collect_image
     erpCollectImageTable: {
       loading: false,
       dataSource: [],
       columns: [
         {
           title: '集合外键',
           key: 'collectId',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '序号',
           key: 'sn',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '图片路径',
           key: 'imageUrl',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: 'windowImage 、otherImage',
           key: 'imageType',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
       ]
     },
     url: {
       add: "/erp/erpCollectData/add",
       edit: "/erp/erpCollectData/edit",
        erpCollectData: {
         list: '/erp/erpCollectData/queryById'
        },
       erpCollectLanguage: {
         list: '/erp/erpCollectData/queryErpCollectLanguageByMainId'
       },
       erpCollectImage: {
         list: '/erp/erpCollectData/queryErpCollectImageByMainId'
       },
     }
   }
 },
 methods: {
   getAllTable() {
     let values = this.tableKeys.map(key => getRefPromise(this, key))
     return Promise.all(values)
   },
   /** 调用完edit()方法之后会自动调用此方法 */
   editAfter() {
     this.$nextTick(() => {
     })
     // 加载子表数据
     if (this.model.id) {
       let params = { id: this.model.id }
       this.requestSubTableData(this.url.erpCollectLanguage.list, params, this.erpCollectLanguageTable)
       this.requestSubTableData(this.url.erpCollectImage.list, params, this.erpCollectImageTable)
     }
   },
   //校验所有一对一子表表单
   validateSubForm(allValues){
     return new Promise((resolve,reject)=>{
       Promise.all([
       ]).then(() => {
         resolve(allValues)
       }).catch(e => {
         reject(e)
       })
     })
   },
   /** 整理成formData */
   classifyIntoFormData(allValues) {
     let main = Object.assign(this.model, allValues.formValue)
     return {
       ...main, // 展开
       erpCollectLanguageList: allValues.tablesValue[0].values,
       erpCollectImageList: allValues.tablesValue[1].values,
     }
   },
    /** 确定按钮点击事件 */
     handleOk() {
       /** 触发表单验证 */
       this.getAllTable().then(tables => {
          return validateFormModelAndTables(this.$refs.form,this.model, tables)
       }).then(allValues => {
         /** 一次性验证一对一的所有子表 */
         return this.validateSubForm(allValues)
       }).then(allValues => {
         if (typeof this.classifyIntoFormData !== 'function') {
           throw this.throwNotFunction('classifyIntoFormData')
         }
         console.log("this.classifyIntoFormData",typeof this.classifyIntoFormData)
         let formData = this.classifyIntoFormData(allValues)

         // 发起请求
         return this.request(formData)
       }).catch(e => {
         if (e.error === VALIDATE_NO_PASSED) {
           // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
           this.activeKey = e.index == null ? this.refKeys[0] : this.refKeys[e.index+1]
         } else {
           console.error(e)
         }
       })
     },
   validateError(msg){
     this.$message.error(msg)
   },
 close() {
   this.visible = false
   this.$emit('close')
   this.$refs.form.clearValidate();
 },

 }
}
</script>

<style scoped>
</style>