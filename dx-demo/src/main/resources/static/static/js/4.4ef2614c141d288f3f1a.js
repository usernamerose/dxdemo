webpackJsonp([4],{IGGa:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=r("IcnI"),s={data:function(){var e=this,t=t;return{loginRole:"用户登录",ruleForm:{admin:"",pass:""},rules:{admin:[{validator:function(e,r,i){""===r?i(new Error("用户名不能为空")):(""!==t.ruleForm.checkPass&&t.$refs.ruleForm.validateField("checkPass"),i())},trigger:"blur"}],pass:[{validator:function(t,r,i){var s=e;""===r?i(new Error("请输入密码")):(""!==s.ruleForm.checkPass&&s.$refs.ruleForm.validateField("checkPass"),i())},trigger:"blur"}]}}},methods:{submitForm:function(){"admin"===this.ruleForm.admin?(i.a.commit("login",{account:"admin"}),this.$router.push("./adminCenter")):this.$message("用户名或密码不正确")},userLogin:function(){this.$router.push("./login")},goRegistered:function(){this.$router.push("./register")}}},o={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("div",{staticStyle:{width:"1200px",margin:"0 auto","margin-top":"100px",padding:"20px 0"},attrs:{clas:"login-wrap"}},[r("div",{staticStyle:{display:"flex","justify-content":"space-between",padding:"0 100px"}},[r("div",{staticClass:"left"}),e._v(" "),r("div",{staticClass:"login-box"},[e._m(0),e._v(" "),r("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,"status-icon":"",rules:e.rules,"label-width":"100px"}},[r("el-form-item",{attrs:{label:"管理员"}},[r("el-input",{model:{value:e.ruleForm.admin,callback:function(t){e.$set(e.ruleForm,"admin",t)},expression:"ruleForm.admin"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"密码",prop:"pass"}},[r("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.ruleForm.pass,callback:function(t){e.$set(e.ruleForm,"pass",t)},expression:"ruleForm.pass"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm()}}},[e._v("登录")])],1)],1),e._v(" "),r("div",{staticStyle:{position:"absolute",bottom:"0",right:"0","font-size":"14px",color:"#E6A23C",cursor:"pointer","font-family":"'Helvetica Neue'"}},[r("span",{on:{click:function(t){return e.goRegistered()}}},[e._v("去注册")]),e._v(" "),r("span",[e._v(" ")]),e._v(" "),r("span",{on:{click:function(t){return e.userLogin()}}},[e._v(e._s(e.loginRole))])])],1)])])])},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"login-title"},[t("h1",[this._v("民工工资自主查询平台")])])}]};var n=r("VU/8")(s,o,!1,function(e){r("KHQh")},null,null);t.default=n.exports},KHQh:function(e,t){}});
//# sourceMappingURL=4.4ef2614c141d288f3f1a.js.map