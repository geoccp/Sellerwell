<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="分类1">
              <a-input placeholder="请输入分类1" v-model="queryParam.path1"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="分类2">
              <a-input placeholder="请输入分类2" v-model="queryParam.path2"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="分类3">
              <a-input placeholder="请输入分类3" v-model="queryParam.path3"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="出单指数">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.orderIndex_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.orderIndex_end"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="行业动销率">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.industrySaleRate_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.industrySaleRate_end"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="产品均价">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.priceAvg_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.priceAvg_end"></a-input>
            </a-form-item>
          </a-col>
<!--          <template v-if="toggleSearchStatus">-->
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="Top1000产品评论数">
                <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.topCommentTotal_begin"></a-input>
                <span class="query-group-split-cust"></span>
                <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.topCommentTotal_end"></a-input>
              </a-form-item>
            </a-col>

<!--          </template>-->
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
<!--              <a @click="handleToggleSearch" style="margin-left: 8px">-->
<!--                {{ toggleSearchStatus ? '收起' : '展开' }}-->
<!--                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>-->
<!--              </a>-->
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
<!--      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>-->
      <a-button type="primary" icon="download" @click="handleExportXls('geek_industry_search')">导出</a-button>
<!--      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">-->
<!--        <a-button type="primary" icon="import">导入</a-button>-->
<!--      </a-upload>-->
      <!-- 高级查询区域 -->
<!--      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>-->
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

<!--        <span slot="action" slot-scope="text, record">-->
<!--          <a @click="handleEdit(record)">编辑</a>-->

<!--          <a-divider type="vertical" />-->
<!--          <a-dropdown>-->
<!--            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>-->
<!--            <a-menu slot="overlay">-->
<!--              <a-menu-item>-->
<!--                <a @click="handleDetail(record)">详情</a>-->
<!--              </a-menu-item>-->
<!--              <a-menu-item>-->
<!--                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">-->
<!--                  <a>删除</a>-->
<!--                </a-popconfirm>-->
<!--              </a-menu-item>-->
<!--            </a-menu>-->
<!--          </a-dropdown>-->
<!--        </span>-->

      </a-table>
    </div>

    <geek-industry-search-modal ref="modalForm" @ok="modalFormOk"></geek-industry-search-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import GeekIndustrySearchModal from './modules/GeekIndustrySearchModal'

  export default {
    name: 'GeekIndustrySearchList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      GeekIndustrySearchModal
    },
    data () {
      return {
        description: 'geek_industry_search管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'分类编码',
            align:"center",
            dataIndex: 'classification'
          },

          {
            title:'出单指数',
            align:"center",
            dataIndex: 'orderIndex'
          },
          {
            title:'动销率',
            align:"center",
            dataIndex: 'industrySaleRate'
          },
          {
            title:'总评论数',
            align:"center",
            dataIndex: 'commentSum'
          },
          {
            title:'产品数量',
            align:"center",
            dataIndex: 'productTotal'
          },
          {
            title:'分类1',
            align:"center",
            dataIndex: 'path1'
          },
          {
            title:'分类2',
            align:"center",
            dataIndex: 'path2'
          },
          {
            title:'分类3',
            align:"center",
            dataIndex: 'path3'
          },

          // {
          //   title:'hotSalesIndex',
          //   align:"center",
          //   dataIndex: 'hotSalesIndex'
          // },
          {
            title:'商品名称',
            align:"center",
            dataIndex: 'industryName'
          },
          // {
          //   title:'韩文名称',
          //   align:"center",
          //   dataIndex: 'industryNameKo'
          // },

          // {
          //   title:'level',
          //   align:"center",
          //   dataIndex: 'level'
          // },
          // {
          //   title:'韩文分类',
          //   align:"center",
          //   dataIndex: 'path'
          // },
          {
            title:'中文分类',
            align:"center",
            dataIndex: 'pathZh'
          },
          {
            title:'产品均价',
            align:"center",
            dataIndex: 'priceAvg'
          },
          {
            title:'有销量产品数',
            align:"center",
            sorter: true,
            dataIndex: 'productSales'
          },

          {
            title:'店铺数量',
            align:"center",
            dataIndex: 'shopTotal'
          },
          // {
          //   title:'state',
          //   align:"center",
          //   dataIndex: 'state'
          // },
          {
            title:'Top1000产品评论数',
            align:"center",
            dataIndex: 'topCommentTotal'
          },
          // {
          //   title:'url',
          //   align:"center",
          //   dataIndex: 'url'
          // },
          {
            title:'近7日新增产品评论数',
            align:"center",
            dataIndex: 'weeklyAddComment'
          },
          {
            title:'近7日新增产品数',
            align:"center",
            dataIndex: 'weeklyAddProduct'
          },
          {
            title:'近7日供需指数',
            align:"center",
            dataIndex: 'weeklySupplyDemand'
          },

          // {
          //   title:'分类4',
          //   align:"center",
          //   dataIndex: 'path4'
          // },
          // {
          //   title:'分类5',
          //   align:"center",
          //   dataIndex: 'path5'
          // },
          // {
          //   title: '操作',
          //   dataIndex: 'action',
          //   align:"center",
          //   fixed:"right",
          //   width:147,
          //   scopedSlots: { customRender: 'action' }
          // }
        ],
        url: {
          list: "/geekIndustrySearch/geekIndustrySearch/list",
          delete: "/geekIndustrySearch/geekIndustrySearch/delete",
          deleteBatch: "/geekIndustrySearch/geekIndustrySearch/deleteBatch",
          exportXlsUrl: "/geekIndustrySearch/geekIndustrySearch/exportXls",
          importExcelUrl: "geekIndustrySearch/geekIndustrySearch/importExcel",

        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'classification',text:'classification',dictCode:''})
        fieldList.push({type:'string',value:'commentSum',text:'总评论数',dictCode:''})
        fieldList.push({type:'string',value:'orderIndex',text:'出单指数',dictCode:''})
        fieldList.push({type:'string',value:'hotSalesIndex',text:'hotSalesIndex',dictCode:''})
        fieldList.push({type:'string',value:'industryName',text:'商品名称',dictCode:''})
        fieldList.push({type:'string',value:'industryNameKo',text:'韩文名称',dictCode:''})
        fieldList.push({type:'string',value:'industrySaleRate',text:'行业动销率',dictCode:''})
        fieldList.push({type:'string',value:'level',text:'level',dictCode:''})
        fieldList.push({type:'string',value:'path',text:'韩文分类',dictCode:''})
        fieldList.push({type:'string',value:'pathZh',text:'中文分类',dictCode:''})
        fieldList.push({type:'string',value:'priceAvg',text:'产品均价',dictCode:''})
        fieldList.push({type:'string',value:'productSales',text:'有销量产品数',dictCode:''})
        fieldList.push({type:'string',value:'productTotal',text:'产品数量',dictCode:''})
        fieldList.push({type:'string',value:'shopTotal',text:'店铺数量',dictCode:''})
        fieldList.push({type:'string',value:'state',text:'state',dictCode:''})
        fieldList.push({type:'string',value:'topCommentTotal',text:'Top1000产品评论数',dictCode:''})
        fieldList.push({type:'string',value:'url',text:'url',dictCode:''})
        fieldList.push({type:'string',value:'weeklyAddComment',text:'近7日新增产品评论数',dictCode:''})
        fieldList.push({type:'string',value:'weeklyAddProduct',text:'近7日新增产品数',dictCode:''})
        fieldList.push({type:'string',value:'weeklySupplyDemand',text:'近7日供需指数',dictCode:''})
        fieldList.push({type:'string',value:'path1',text:'分类1',dictCode:''})
        fieldList.push({type:'string',value:'path2',text:'分类2',dictCode:''})
        fieldList.push({type:'string',value:'path3',text:'分类3',dictCode:''})
        fieldList.push({type:'string',value:'path4',text:'分类4',dictCode:''})
        fieldList.push({type:'string',value:'path5',text:'分类5',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>