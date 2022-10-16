<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="编码">
              <a-input placeholder="请输入编码" v-model="queryParam.classification"></a-input>
            </a-form-item>
          </a-col>
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
          <template v-if="toggleSearchStatus">
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
              <a-form-item label="热销指数">
                <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.hotSalesIndex_begin"></a-input>
                <span class="query-group-split-cust"></span>
                <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.hotSalesIndex_end"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="产品数量">
                <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.productTotal_begin"></a-input>
                <span class="query-group-split-cust"></span>
                <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.productTotal_end"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="有销售产品数">
                <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.productSales_begin"></a-input>
                <span class="query-group-split-cust"></span>
                <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.productSales_end"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="行业动销率(%)">
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
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="top1000评论指数">
                <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.topCommentTotal_begin"></a-input>
                <span class="query-group-split-cust"></span>
                <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.topCommentTotal_end"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('tool_geekseeker')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
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

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <tool-geekseeker-modal ref="modalForm" @ok="modalFormOk"></tool-geekseeker-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ToolGeekseekerModal from './modules/ToolGeekseekerModal'

  export default {
    name: 'ToolGeekseekerList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ToolGeekseekerModal
    },
    data () {
      return {
        description: 'tool_geekseeker管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:20,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'编码',
            align:"center",
            sorter: true,
            dataIndex: 'classification'
          },
          {
            title:'中文分类',
            align:"center",
            width:160,
            sorter: true,
            dataIndex: 'pathZh'
          },
          {
            title:'出单指数',
            align:"center",
            sorter: true,
            dataIndex: 'orderIndex'
          },
          {
            title:'热销指数',
            align:"center",
            sorter: true,
            dataIndex: 'hotSalesIndex'
          },
          {
            title:'产品数量',
            align:"center",
            sorter: true,
            dataIndex: 'productTotal'
          },
          {
            title:'有销售产品数',
            align:"center",
            sorter: true,
            dataIndex: 'productSales'
          },
          {
            title:'行业动销率(%)',
            align:"center",
            sorter: true,
            dataIndex: 'industrySaleRate'
          },
          {
            title:'产品均价',
            align:"center",
            sorter: true,
            dataIndex: 'priceAvg'
          },
          {
            title:'top1000评论指数',
            align:"center",
            sorter: true,
            dataIndex: 'topCommentTotal'
          },
          {
            title:'细分市场',
            align:"center",
            dataIndex: 'industryName'
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
          {
            title:'分类4',
            align:"center",
            dataIndex: 'path4'
          },
          {
            title:'分类5',
            align:"center",
            dataIndex: 'path5'
          },
          {
            title:'韩文分类',
            align:"center",
            dataIndex: 'path'
          },

          {
            title:'创建日期',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/tool/toolGeekseeker/list",
          delete: "/tool/toolGeekseeker/delete",
          deleteBatch: "/tool/toolGeekseeker/deleteBatch",
          exportXlsUrl: "/tool/toolGeekseeker/exportXls",
          importExcelUrl: "tool/toolGeekseeker/importExcel",

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
        fieldList.push({type:'string',value:'classification',text:'编码',dictCode:''})
        fieldList.push({type:'string',value:'industryName',text:'细分市场',dictCode:''})
        fieldList.push({type:'string',value:'path1',text:'分类1',dictCode:''})
        fieldList.push({type:'string',value:'path2',text:'分类2',dictCode:''})
        fieldList.push({type:'string',value:'path3',text:'分类3',dictCode:''})
        fieldList.push({type:'string',value:'path4',text:'分类4',dictCode:''})
        fieldList.push({type:'string',value:'path5',text:'分类5',dictCode:''})
        fieldList.push({type:'double',value:'orderIndex',text:'出单指数',dictCode:''})
        fieldList.push({type:'double',value:'hotSalesIndex',text:'热销指数',dictCode:''})
        fieldList.push({type:'double',value:'productTotal',text:'产品数量',dictCode:''})
        fieldList.push({type:'double',value:'productSales',text:'有销售产品数',dictCode:''})
        fieldList.push({type:'double',value:'industrySaleRate',text:'行业动销率(%)',dictCode:''})
        fieldList.push({type:'double',value:'priceAvg',text:'产品均价',dictCode:''})
        fieldList.push({type:'double',value:'topCommentTotal',text:'top1000评论指数',dictCode:''})
        fieldList.push({type:'string',value:'path',text:'韩文分类',dictCode:''})
        fieldList.push({type:'string',value:'pathZh',text:'中文分类',dictCode:''})
        fieldList.push({type:'date',value:'createTime',text:'创建日期'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>