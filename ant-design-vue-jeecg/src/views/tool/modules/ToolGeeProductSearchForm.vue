<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="问答数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="askAndQ">
              <a-input v-model="model.askAndQ" placeholder="请输入问答数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="advertisementProduct" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="advertisementProduct">
              <a-input v-model="model.advertisementProduct" placeholder="请输入advertisementProduct"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="坏评率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="badCommentRate">
              <a-input v-model="model.badCommentRate" placeholder="请输入坏评率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="classification" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="classification">
              <a-input v-model="model.classification" placeholder="请输入classification"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前产品评价数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="comment">
              <a-input v-model="model.comment" placeholder="请输入当前产品评价数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="促销折扣" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="discount">
              <a-input v-model="model.discount" placeholder="请输入促销折扣"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="配送" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distribution">
              <a-input v-model="model.distribution" placeholder="请输入配送"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="好评率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="goodCommentRate">
              <a-input v-model="model.goodCommentRate" placeholder="请输入好评率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商品浏览图" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="imgIndex">
              <a-input v-model="model.imgIndex" placeholder="请输入商品浏览图"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="itemid" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemid">
              <a-input v-model="model.itemid" placeholder="请输入itemid"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="类目路径韩文" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path">
              <a-input v-model="model.path" placeholder="请输入类目路径韩文"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="类目路径 中文" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pathTzh">
              <a-input v-model="model.pathTzh" placeholder="请输入类目路径 中文"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品价格" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="price">
              <a-input v-model="model.price" placeholder="请输入产品价格"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productId">
              <a-input v-model="model.productId" placeholder="请输入产品编码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="productStatus" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productStatus">
              <a-input v-model="model.productStatus" placeholder="请输入productStatus"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前BSR排名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rank">
              <a-input v-model="model.rank" placeholder="请输入当前BSR排名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="榜单类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rankType">
              <a-input v-model="model.rankType" placeholder="请输入榜单类型"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上架时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="shelfTime">
              <a-input v-model="model.shelfTime" placeholder="请输入上架时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="卖家数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sellerCount">
              <a-input v-model="model.sellerCount" placeholder="请输入卖家数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品评分" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="star">
              <a-input v-model="model.star" placeholder="请输入产品评分"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="state" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="state">
              <a-input v-model="model.state" placeholder="请输入state"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品标题" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入产品标题"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <a-input v-model="model.url" placeholder="请输入url"  ></a-input>
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
    name: 'ToolGeeProductSearchForm',
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
          add: "/tool/toolGeeProductSearch/add",
          edit: "/tool/toolGeeProductSearch/edit",
          queryById: "/tool/toolGeeProductSearch/queryById"
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