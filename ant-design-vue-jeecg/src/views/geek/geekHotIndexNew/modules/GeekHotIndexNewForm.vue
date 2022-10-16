<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="classification" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="classification">
              <a-input v-model="model.classification" placeholder="请输入classification"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="comment" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="comment">
              <a-input v-model="model.comment" placeholder="请输入comment"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="images" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="images">
              <a-input v-model="model.images" placeholder="请输入images"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="price" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="price">
              <a-input-number v-model="model.price" placeholder="请输入price" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="productId" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productId">
              <a-input-number v-model="model.productId" placeholder="请输入productId" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="rank" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rank">
              <a-input v-model="model.rank" placeholder="请输入rank"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="rankType" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rankType">
              <a-input v-model="model.rankType" placeholder="请输入rankType"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="star" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="star">
              <a-input v-model="model.star" placeholder="请输入star"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="state" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="state">
              <a-input v-model="model.state" placeholder="请输入state"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="title" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入title"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <a-input v-model="model.url" placeholder="请输入url"  ></a-input>
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
    name: 'GeekHotIndexNewForm',
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
        },
        url: {
          add: "/geekHotIndexNew/geekHotIndexNew/add",
          edit: "/geekHotIndexNew/geekHotIndexNew/edit",
          queryById: "/geekHotIndexNew/geekHotIndexNew/queryById"
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