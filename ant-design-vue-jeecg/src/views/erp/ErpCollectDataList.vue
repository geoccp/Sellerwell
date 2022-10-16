<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="目录名称">
              <a-input placeholder="请输入目录名称" v-model="queryParam.categoryName"></a-input>
            </a-form-item>
          </a-col>
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
      <a-button type="primary" icon="download" @click="handleExportXls('erp_collect_data')">导出</a-button>
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
        bordered
        rowKey="id"
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
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
            @click="uploadFile(text)">
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
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <erpCollectData-modal ref="modalForm" @ok="modalFormOk"></erpCollectData-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ErpCollectDataModal from './modules/ErpCollectDataModal'
  import '@/assets/less/TableExpand.less'

  export default {
    name: "ErpCollectDataList",
    mixins:[JeecgListMixin],
    components: {
      ErpCollectDataModal
    },
    data () {
      return {
        description: 'erp_collect_data管理页面',
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
            title:'目录ID',
            align:"center",
            dataIndex: 'categoryId'
          },
          {
            title:'目录名称',
            align:"center",
            dataIndex: 'categoryName'
          },
          {
            title:'重量',
            align:"center",
            dataIndex: 'weight'
          },
          {
            title:'当前价格',
            align:"center",
            dataIndex: 'price'
          },
          {
            title:'货币单位',
            align:"center",
            dataIndex: 'currency'
          },
          {
            title:'主商品图片',
            align:"center",
            dataIndex: 'mainImageUrl'
          },
          {
            title:'是否当前',
            align:"center",
            dataIndex: 'isCurrenct'
          },
          {
            title:'链接平台',
            align:"center",
            dataIndex: 'srcPlatform'
          },
          {
            title:'商品平台',
            align:"center",
            dataIndex: 'purchasePlatform'
          },
          {
            title:'商品链接',
            align:"center",
            dataIndex: 'srcUrl'
          },
          {
            title:'仓库',
            align:"center",
            dataIndex: 'warehouseSpu'
          },
          {
            title:'商品长度',
            align:"center",
            dataIndex: 'productLength'
          },
          {
            title:'商品宽',
            align:"center",
            dataIndex: 'productWidth'
          },
          {
            title:'商品高',
            align:"center",
            dataIndex: 'productHigh'
          },
          {
            title:'商品id',
            align:"center",
            dataIndex: 'itemId'
          },
          {
            title:'库存',
            align:"center",
            dataIndex: 'stock'
          },
          {
            title:'价格范围',
            align:"center",
            dataIndex: 'sourcePrice'
          },
          {
            title:'创建人',
            align:"center",
            dataIndex: 'createBy_dictText'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/erp/erpCollectData/list",
          delete: "/erp/erpCollectData/delete",
          deleteBatch: "/erp/erpCollectData/deleteBatch",
          exportXlsUrl: "/erp/erpCollectData/exportXls",
          importExcelUrl: "erp/erpCollectData/importExcel",
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
      }
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'int',value:'categoryId',text:'目录ID',dictCode:''})
        fieldList.push({type:'string',value:'categoryName',text:'目录名称',dictCode:''})
        fieldList.push({type:'double',value:'weight',text:'重量',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'price',text:'当前价格',dictCode:''})
        fieldList.push({type:'string',value:'currency',text:'货币单位',dictCode:''})
        fieldList.push({type:'string',value:'mainImageUrl',text:'主商品图片',dictCode:''})
        fieldList.push({type:'string',value:'isCurrenct',text:'是否当前',dictCode:''})
        fieldList.push({type:'string',value:'srcPlatform',text:'链接平台',dictCode:''})
        fieldList.push({type:'string',value:'purchasePlatform',text:'商品平台',dictCode:''})
        fieldList.push({type:'string',value:'srcUrl',text:'商品链接',dictCode:''})
        fieldList.push({type:'string',value:'warehouseSpu',text:'仓库',dictCode:''})
        fieldList.push({type:'string',value:'productLength',text:'商品长度',dictCode:''})
        fieldList.push({type:'string',value:'productWidth',text:'商品宽',dictCode:''})
        fieldList.push({type:'string',value:'productHigh',text:'商品高',dictCode:''})
        fieldList.push({type:'string',value:'itemId',text:'商品id',dictCode:''})
        fieldList.push({type:'string',value:'stock',text:'库存',dictCode:''})
        fieldList.push({type:'string',value:'sourcePrice',text:'价格范围',dictCode:''})
        fieldList.push({type:'sel_user',value:'createBy',text:'创建人'})
        this.superFieldList = fieldList
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>