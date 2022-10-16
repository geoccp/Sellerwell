<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="问答数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="aAndQ">
              <a-input v-model="model.aAndQ" placeholder="请输入问答数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="广告产品" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="adFlag">
              <a-input v-model="model.adFlag" placeholder="请输入广告产品"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="差评率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="badCommentRate">
              <a-input v-model="model.badCommentRate" placeholder="请输入差评率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="品牌" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="brandName">
              <a-input v-model="model.brandName" placeholder="请输入品牌"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="classification" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="classification">
              <a-input v-model="model.classification" placeholder="请输入classification"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="评价数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="comment">
              <a-input v-model="model.comment" placeholder="请输入评价数量"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="促销折扣" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="discount">
              <a-input v-model="model.discount" placeholder="请输入促销折扣"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="配送方式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distribution">
              <a-input v-model="model.distribution" placeholder="请输入配送方式"  ></a-input>
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
            <a-form-model-item label="韩文目录" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path">
              <a-input v-model="model.path" placeholder="请输入韩文目录"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="中文目录" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pathTzh">
              <a-input v-model="model.pathTzh" placeholder="请输入中文目录"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="价格" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="price">
              <a-input v-model="model.price" placeholder="请输入价格"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productId">
              <a-input v-model="model.productId" placeholder="请输入编码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="产品状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productStatus">
              <a-input v-model="model.productStatus" placeholder="请输入产品状态"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前BSR排名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="strRank">
              <a-input v-model="model.strRank" placeholder="请输入当前BSR排名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="入围榜单" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rankType">
              <a-input v-model="model.rankType" placeholder="请输入入围榜单"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="配送方式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rocket">
              <a-input v-model="model.rocket" placeholder="请输入配送方式"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="跟卖数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sellerCount">
              <a-input v-model="model.sellerCount" placeholder="请输入跟卖数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上架时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="shelfTime">
              <j-date placeholder="请选择上架时间" v-model="model.shelfTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="评分" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="star">
              <a-input v-model="model.star" placeholder="请输入评分"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="state" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="state">
              <a-input v-model="model.state" placeholder="请输入state"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="标题" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入标题"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <a-input v-model="model.url" placeholder="请输入url"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="变体数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="variableCount">
              <a-input v-model="model.variableCount" placeholder="请输入变体数量"  ></a-input>
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
    name: 'GeekAllProductSearchForm',
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
          add: "/geekAllProductSearch/geekAllProductSearch/add",
          edit: "/geekAllProductSearch/geekAllProductSearch/edit",
          queryById: "/geekAllProductSearch/geekAllProductSearch/queryById"
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