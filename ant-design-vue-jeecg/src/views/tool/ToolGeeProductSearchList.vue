<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('tool_gee_product_search')">导出</a-button>
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

    <tool-gee-product-search-modal ref="modalForm" @ok="modalFormOk"></tool-gee-product-search-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ToolGeeProductSearchModal from './modules/ToolGeeProductSearchModal'

  export default {
    name: 'ToolGeeProductSearchList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ToolGeeProductSearchModal
    },
    data () {
      return {
        description: 'tool_gee_product_search管理页面',
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
            title:'问答数',
            align:"center",
            dataIndex: 'askAndQ'
          },
          {
            title:'advertisementProduct',
            align:"center",
            dataIndex: 'advertisementProduct'
          },
          {
            title:'坏评率',
            align:"center",
            dataIndex: 'badCommentRate'
          },
          {
            title:'classification',
            align:"center",
            dataIndex: 'classification'
          },
          {
            title:'当前产品评价数',
            align:"center",
            dataIndex: 'comment'
          },
          {
            title:'促销折扣',
            align:"center",
            dataIndex: 'discount'
          },
          {
            title:'配送',
            align:"center",
            dataIndex: 'distribution'
          },
          {
            title:'好评率',
            align:"center",
            dataIndex: 'goodCommentRate'
          },
          {
            title:'商品浏览图',
            align:"center",
            dataIndex: 'imgIndex'
          },
          {
            title:'itemid',
            align:"center",
            dataIndex: 'itemid'
          },
          {
            title:'类目路径韩文',
            align:"center",
            dataIndex: 'path'
          },
          {
            title:'类目路径 中文',
            align:"center",
            dataIndex: 'pathTzh'
          },
          {
            title:'产品价格',
            align:"center",
            dataIndex: 'price'
          },
          {
            title:'产品编码',
            align:"center",
            dataIndex: 'productId'
          },
          {
            title:'productStatus',
            align:"center",
            dataIndex: 'productStatus'
          },
          {
            title:'当前BSR排名',
            align:"center",
            dataIndex: 'rank'
          },
          {
            title:'榜单类型',
            align:"center",
            dataIndex: 'rankType'
          },
          {
            title:'配送方式',
            align:"center",
            dataIndex: 'rocket_dictText'
          },

          {
            title:'上架时间',
            align:"center",
            dataIndex: 'shelfTime'
          },
          {
            title:'卖家数',
            align:"center",
            dataIndex: 'sellerCount'
          },
          {
            title:'产品评分',
            align:"center",
            dataIndex: 'star'
          },
          {
            title:'state',
            align:"center",
            dataIndex: 'state'
          },
          {
            title:'产品标题',
            align:"center",
            dataIndex: 'title'
          },
          {
            title:'url',
            align:"center",
            dataIndex: 'url'
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
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/tool/toolGeeProductSearch/list",
          delete: "/tool/toolGeeProductSearch/delete",
          deleteBatch: "/tool/toolGeeProductSearch/deleteBatch",
          exportXlsUrl: "/tool/toolGeeProductSearch/exportXls",
          importExcelUrl: "tool/toolGeeProductSearch/importExcel",

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
        fieldList.push({type:'string',value:'askAndQ',text:'问答数'})
        fieldList.push({type:'string',value:'advertisementProduct',text:'advertisementProduct'})
        fieldList.push({type:'string',value:'badCommentRate',text:'坏评率'})
        fieldList.push({type:'string',value:'classification',text:'classification'})
        fieldList.push({type:'string',value:'comment',text:'当前产品评价数'})
        fieldList.push({type:'string',value:'discount',text:'促销折扣'})
        fieldList.push({type:'string',value:'distribution',text:'配送'})
        fieldList.push({type:'string',value:'goodCommentRate',text:'好评率'})
        fieldList.push({type:'string',value:'imgIndex',text:'商品浏览图'})
        fieldList.push({type:'string',value:'itemid',text:'itemid'})
        fieldList.push({type:'string',value:'path',text:'类目路径韩文'})
        fieldList.push({type:'string',value:'pathTzh',text:'类目路径 中文'})
        fieldList.push({type:'string',value:'price',text:'产品价格'})
        fieldList.push({type:'string',value:'productId',text:'产品编码'})
        fieldList.push({type:'string',value:'productStatus',text:'productStatus'})
        fieldList.push({type:'string',value:'rank',text:'当前BSR排名'})
        fieldList.push({type:'string',value:'rankType',text:'榜单类型'})
        fieldList.push({type:'string',value:'shelfTime',text:'上架时间'})
        fieldList.push({type:'string',value:'sellerCount',text:'卖家数'})
        fieldList.push({type:'string',value:'star',text:'产品评分'})
        fieldList.push({type:'string',value:'state',text:'state'})
        fieldList.push({type:'string',value:'title',text:'产品标题'})
        fieldList.push({type:'string',value:'url',text:'url'})
        fieldList.push({type:'string',value:'path1',text:'分类1'})
        fieldList.push({type:'string',value:'path2',text:'分类2'})
        fieldList.push({type:'string',value:'path3',text:'分类3'})
        fieldList.push({type:'string',value:'path4',text:'分类4'})
        fieldList.push({type:'string',value:'path5',text:'分类5'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>