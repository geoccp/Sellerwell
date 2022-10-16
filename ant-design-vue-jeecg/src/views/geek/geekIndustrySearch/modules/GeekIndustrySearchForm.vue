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
            <a-form-model-item label="总评论数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commentSum">
              <a-input v-model="model.commentSum" placeholder="请输入总评论数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="hotSalesIndex" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hotSalesIndex">
              <a-input v-model="model.hotSalesIndex" placeholder="请输入hotSalesIndex"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="industryName">
              <a-input v-model="model.industryName" placeholder="请输入商品名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="韩文名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="industryNameKo">
              <a-input v-model="model.industryNameKo" placeholder="请输入韩文名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="行业动销率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="industrySaleRate">
              <a-input v-model="model.industrySaleRate" placeholder="请输入行业动销率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="level" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="level">
              <a-input v-model="model.level" placeholder="请输入level"  ></a-input>
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
            <a-form-model-item label="产品均价" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="priceAvg">
              <a-input v-model="model.priceAvg" placeholder="请输入产品均价"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="有销量产品数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productSales">
              <a-input v-model="model.productSales" placeholder="请输入有销量产品数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productTotal">
              <a-input v-model="model.productTotal" placeholder="请输入产品数量"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="店铺数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="shopTotal">
              <a-input v-model="model.shopTotal" placeholder="请输入店铺数量"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="state" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="state">
              <a-input v-model="model.state" placeholder="请输入state"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Top1000产品评论数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="topCommentTotal">
              <a-input v-model="model.topCommentTotal" placeholder="请输入Top1000产品评论数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <a-input v-model="model.url" placeholder="请输入url"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="近7日新增产品评论数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weeklyAddComment">
              <a-input v-model="model.weeklyAddComment" placeholder="请输入近7日新增产品评论数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="近7日新增产品数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weeklyAddProduct">
              <a-input v-model="model.weeklyAddProduct" placeholder="请输入近7日新增产品数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="近7日供需指数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weeklySupplyDemand">
              <a-input v-model="model.weeklySupplyDemand" placeholder="请输入近7日供需指数"  ></a-input>
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
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'GeekIndustrySearchForm',
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
          add: "/geekIndustrySearch/geekIndustrySearch/add",
          edit: "/geekIndustrySearch/geekIndustrySearch/edit",
          queryById: "/geekIndustrySearch/geekIndustrySearch/queryById"
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