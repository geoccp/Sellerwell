<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="classification">
              <a-input v-model="model.classification" placeholder="请输入编码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="细分市场" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="industryName">
              <a-input v-model="model.industryName" placeholder="请输入细分市场"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="分类1" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path1">
              <a-input v-model="model.path1" placeholder="请输入分类1"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="分类2" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path2">
              <a-input v-model="model.path2" placeholder="请输入分类2"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="分类3" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path3">
              <a-input v-model="model.path3" placeholder="请输入分类3"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="分类4" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path4">
              <a-input v-model="model.path4" placeholder="请输入分类4"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="分类5" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path5">
              <a-input v-model="model.path5" placeholder="请输入分类5"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出单指数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="orderIndex">
              <a-input-number v-model="model.orderIndex" placeholder="请输入出单指数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="热销指数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hotSalesIndex">
              <a-input-number v-model="model.hotSalesIndex" placeholder="请输入热销指数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productTotal">
              <a-input-number v-model="model.productTotal" placeholder="请输入产品数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="有销售产品数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productSales">
              <a-input-number v-model="model.productSales" placeholder="请输入有销售产品数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="行业动销率(%)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="industrySaleRate">
              <a-input-number v-model="model.industrySaleRate" placeholder="请输入行业动销率(%)" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品均价" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="priceAvg">
              <a-input-number v-model="model.priceAvg" placeholder="请输入产品均价" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="top1000评论指数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="topCommentTotal">
              <a-input-number v-model="model.topCommentTotal" placeholder="请输入top1000评论指数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="韩文分类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path">
              <a-input v-model="model.path" placeholder="请输入韩文分类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="中文分类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pathZh">
              <a-input v-model="model.pathZh" placeholder="请输入中文分类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createTime">
              <j-date placeholder="请选择创建日期" v-model="model.createTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'ToolGeekseekerForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           topCommentTotal: [
              { required: true, message: '请输入top1000评论指数!'},
           ],
        },
        url: {
          add: "/tool/toolGeekseeker/add",
          edit: "/tool/toolGeekseeker/edit",
          queryById: "/tool/toolGeekseeker/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }

        })
      },
    }
  }
</script>