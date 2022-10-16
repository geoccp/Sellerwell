<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="分类编码">
              <a-input placeholder="请输入分类编码" v-model="queryParam.classification"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="父目录编码">
              <a-input placeholder="请输入父目录编码" v-model="queryParam.parentCategoryId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="产品编码">
              <a-input placeholder="请输入产品编码" v-model="queryParam.productId"></a-input>
            </a-form-item>
          </a-col>
<!--          <template v-if="toggleSearchStatus">-->
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="分类名称">
                <a-input placeholder="请输入分类名称" v-model="queryParam.name"></a-input>
              </a-form-item>
            </a-col>
<!--            <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--              <a-form-item label="分类韩文名称">-->
<!--                <a-input placeholder="请输入分类韩文名称" v-model="queryParam.nameTko"></a-input>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
<!--            <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--              <a-form-item label="7天平均排名">-->
<!--                <a-input placeholder="请输入7天平均排名" v-model="queryParam.avgRank"></a-input>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="7天平均排名">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.avgRank_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.avgRank_end"></a-input>
            </a-form-item>
          </a-col>

          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="当前排名">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.rank_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.rank_end"></a-input>
            </a-form-item>
          </a-col>

          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="排名涨跌">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.changeRank_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.changeRank_end"></a-input>
            </a-form-item>
          </a-col>


          <!--          </template>-->
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
<!--      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>-->
      <a-button type="primary" icon="download" @click="handleExportXls('view_geek_hot_index_new')">导出</a-button>
<!--      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">-->
<!--        <a-button type="primary" icon="import">导入</a-button>-->
<!--      </a-upload>-->
      <!-- 高级查询区域 -->
<!--      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>-->
<!--      <a-dropdown v-if="selectedRowKeys.length > 0">-->
<!--        <a-menu slot="overlay">-->
<!--          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>-->
<!--        </a-menu>-->
<!--        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>-->
<!--      </a-dropdown>-->
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

    <view-geek-hot-index-new-modal ref="modalForm" @ok="modalFormOk"></view-geek-hot-index-new-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ViewGeekHotIndexNewModal from './modules/ViewGeekHotIndexNewModal'

  export default {
    name: 'ViewGeekHotIndexNewList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ViewGeekHotIndexNewModal
    },
    data () {
      return {
        description: 'view_geek_hot_index_new管理页面',
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
            sorter: true,
            dataIndex: 'classification'
          },
          {
            title:'分类级别',
            align:"center",
            sorter: true,
            dataIndex: 'level'
          },
          {
            title:'分类父编码',
            align:"center",
            sorter: true,
            dataIndex: 'parentCategoryId'
          },
          {
            title:'产品编码',
            align:"center",
            sorter: true,
            dataIndex: 'productId'
          },
          {
            title:'分类名称',
            align:"center",
            sorter: true,
            dataIndex: 'name'
          },
          {
            title:'分类韩文名称',
            align:"center",
            sorter: true,
            dataIndex: 'nameTko'
          },
          {
            title:'7天平均排名',
            align:"center",
            sorter: true,
            dataIndex: 'avgRank'
          },
          {
            title:'当前排名',
            align:"center",
            sorter: true,
            dataIndex: 'rank'
          },
          {
            title:'排名涨跌',
            align:"center",
            sorter: true,
            dataIndex: 'changeRank'
          },

          // {
          //   title:'标题',
          //   align:"center",
          //   sorter: true,
          //   dataIndex: 'title'
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
          list: "/viewGeekHotIndexNew/viewGeekHotIndexNew/list",
          delete: "/viewGeekHotIndexNew/viewGeekHotIndexNew/delete",
          deleteBatch: "/viewGeekHotIndexNew/viewGeekHotIndexNew/deleteBatch",
          exportXlsUrl: "/viewGeekHotIndexNew/viewGeekHotIndexNew/exportXls",
          importExcelUrl: "viewGeekHotIndexNew/viewGeekHotIndexNew/importExcel",

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
        fieldList.push({type:'string',value:'classification',text:'分类编码',dictCode:''})
        fieldList.push({type:'string',value:'productId',text:'产品编码',dictCode:''})
        fieldList.push({type:'string',value:'name',text:'分类名称',dictCode:''})
        fieldList.push({type:'string',value:'nameTko',text:'分类韩文名称',dictCode:''})
        fieldList.push({type:'double',value:'avgRank',text:'7天平均排名',dictCode:''})
        fieldList.push({type:'string',value:'rank',text:'当前排名',dictCode:''})
        fieldList.push({type:'string',value:'title',text:'标题',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>