webpackJsonp([4],{Q3V3:function(n,t,e){var r=e("tN+f");"string"==typeof r&&(r=[[n.i,r,""]]),r.locals&&(n.exports=r.locals);e("rjj0")("116f0d50",r,!0)},scgI:function(n,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=e("Xxa5"),s=e.n(r),a=e("exGp"),l=e.n(a),o={name:"index",data:function(){return{roleList:[]}},created:function(){this.listRoles()},watch:{$route:function(){this.$get(""),this.listRoles()}},methods:{listRoles:function(){var n=this;return l()(s.a.mark(function t(){return s.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,n.$get("/rbac/list-roles");case 2:n.roleList=t.sent;case 3:case"end":return t.stop()}},t,n)}))()}}},i={render:function(){var n=this.$createElement,t=this._self._c||n;return t("div",[t("el-table",{attrs:{data:this.roleList}},[t("el-table-column",{attrs:{type:"index",label:"序号"}}),this._v(" "),t("el-table-column",{attrs:{prop:"roleName",label:"角色"}}),this._v(" "),t("el-table-column",{attrs:{prop:"createTime",label:"创建时间"}}),this._v(" "),t("el-table-column",{attrs:{prop:"recordStatus",label:"状态"}})],1)],1)},staticRenderFns:[]};var c=e("VU/8")(o,i,!1,function(n){e("Q3V3")},"data-v-1e17b6df",null);t.default=c.exports},"tN+f":function(n,t,e){(n.exports=e("FZ+f")(!1)).push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])}});