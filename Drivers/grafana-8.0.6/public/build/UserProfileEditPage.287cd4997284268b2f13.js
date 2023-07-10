(window.webpackJsonp=window.webpackJsonp||[]).push([[54],{"3QIV":function(e,t,s){"use strict";var n=s("t8hP"),r=s("Gqk0");const i={changePassword:async function(e){try{await Object(n.getBackendSrv)().put("/api/user/password",e)}catch(e){console.error(e)}},revokeUserSession:async function(e){await Object(n.getBackendSrv)().post("/api/user/revoke-auth-token",{authTokenId:e})},loadUser:function(){return Object(n.getBackendSrv)().get("/api/user")},loadSessions:function(){return Object(n.getBackendSrv)().get("/api/user/auth-tokens")},loadOrgs:function(){return Object(n.getBackendSrv)().get("/api/user/orgs")},loadTeams:function(){return Object(n.getBackendSrv)().get("/api/user/teams")},setUserOrg:async function(e){await Object(n.getBackendSrv)().post("/api/user/using/"+e.orgId,{})},updateUserProfile:async function(e){try{await Object(n.getBackendSrv)().put("/api/user",e)}catch(e){console.error(e)}}};function a(e){return async function(t){t(Object(r.g)({updating:!0})),await i.changePassword(e),t(Object(r.g)({updating:!1}))}}function o(){return async function(e){await e(c()),e((async function(e){e(Object(r.d)());const t=await i.loadTeams();e(Object(r.h)({teams:t}))})),e((async function(e){e(Object(r.b)());const t=await i.loadOrgs();e(Object(r.e)({orgs:t}))})),e((async function(e){e(Object(r.c)());const t=await i.loadSessions();e(Object(r.f)({sessions:t}))}))}}function c(){return async function(e){const t=await i.loadUser();e(Object(r.j)({user:t}))}}function d(e){return async function(t){t(Object(r.g)({updating:!0})),await i.revokeUserSession(e),t(Object(r.k)({tokenId:e}))}}function l(e){return async function(t){t(Object(r.g)({updating:!0})),await i.setUserOrg(e),window.location.href=n.config.appSubUrl+"/profile"}}function j(e){return async function(t){t(Object(r.g)({updating:!0})),await i.updateUserProfile(e),await t(c()),t(Object(r.g)({updating:!1}))}}s.d(t,"a",(function(){return a})),s.d(t,"c",(function(){return o})),s.d(t,"d",(function(){return c})),s.d(t,"e",(function(){return d})),s.d(t,"b",(function(){return l})),s.d(t,"f",(function(){return j}))},"53+f":function(e,t,s){"use strict";var n,r,i,a,o=s("q1tI"),c=s("kDLi"),d=s("nKUr");class l extends o.PureComponent{render(){const{isLoading:e,orgs:t,user:s}=this.props;return e?n||(n=Object(d.jsx)(c.LoadingPlaceholder,{text:"Loading organizations..."})):0===t.length?null:Object(d.jsxs)("div",{children:[r||(r=Object(d.jsx)("h3",{className:"page-sub-heading",children:"Organizations"})),Object(d.jsx)("div",{className:"gf-form-group",children:Object(d.jsxs)("table",{className:"filter-table form-inline","aria-label":"User organizations table",children:[i||(i=Object(d.jsx)("thead",{children:Object(d.jsxs)("tr",{children:[Object(d.jsx)("th",{children:"Name"}),Object(d.jsx)("th",{children:"Role"}),Object(d.jsx)("th",{})]})})),Object(d.jsx)("tbody",{children:t.map((e,t)=>Object(d.jsxs)("tr",{children:[Object(d.jsx)("td",{children:e.name}),Object(d.jsx)("td",{children:e.role}),Object(d.jsx)("td",{className:"text-right",children:e.orgId===(null==s?void 0:s.orgId)?a||(a=Object(d.jsx)(c.Button,{variant:"secondary",size:"sm",disabled:!0,children:"Current"})):Object(d.jsx)(c.Button,{variant:"secondary",size:"sm",onClick:()=>{this.props.setUserOrg(e)},children:"Select"})})]},t))})]})})]})}}t.a=l},Fceo:function(e,t,s){"use strict";s("q1tI");var n,r,i,a,o=s("kDLi"),c=s("ZFWI"),d=s("nKUr");function l(){return(l=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var s=arguments[t];for(var n in s)Object.prototype.hasOwnProperty.call(s,n)&&(e[n]=s[n])}return e}).apply(this,arguments)}const{disableLoginForm:j}=c.b;t.a=({user:e,isSavingUser:t,updateProfile:s})=>{var a;return Object(d.jsx)(o.Form,{onSubmit:e=>{s(e)},validateOn:"onBlur",children:({register:s,errors:c})=>{var u,h,O;return Object(d.jsxs)(o.FieldSet,{label:"Edit profile",children:[Object(d.jsx)(o.Field,{label:"Name",invalid:!!c.name,error:"Name is required",disabled:j,children:Object(d.jsx)(o.Input,l({},s("name",{required:!0}),{id:"edit-user-profile-name",placeholder:"Name",defaultValue:null!==(u=null==e?void 0:e.name)&&void 0!==u?u:"",suffix:n||(n=Object(d.jsx)(b,{}))}))}),Object(d.jsx)(o.Field,{label:"Email",invalid:!!c.email,error:"Email is required",disabled:j,children:Object(d.jsx)(o.Input,l({},s("email",{required:!0}),{id:"edit-user-profile-email",placeholder:"Email",defaultValue:null!==(h=null==e?void 0:e.email)&&void 0!==h?h:"",suffix:r||(r=Object(d.jsx)(b,{}))}))}),Object(d.jsx)(o.Field,{label:"Username",disabled:j,children:Object(d.jsx)(o.Input,l({},s("login"),{id:"edit-user-profile-username",defaultValue:null!==(O=null==e?void 0:e.login)&&void 0!==O?O:"",placeholder:"Username",suffix:i||(i=Object(d.jsx)(b,{}))}))}),a||(a=Object(d.jsx)("div",{className:"gf-form-button-row",children:Object(d.jsx)(o.Button,{variant:"primary",disabled:t,"aria-label":"Edit user profile save button",children:"Save"})}))]})}})};const b=()=>j?a||(a=Object(d.jsx)(o.Tooltip,{content:"Login details locked because they are managed in another system.",children:Object(d.jsx)(o.Icon,{name:"lock"})})):null},"bkZ+":function(e,t,s){"use strict";s.d(t,"a",(function(){return d}));var n,r,i,a=s("q1tI"),o=s("kDLi"),c=s("nKUr");class d extends a.PureComponent{render(){const{isLoading:e,teams:t}=this.props;return e?n||(n=Object(c.jsx)(o.LoadingPlaceholder,{text:"Loading teams..."})):0===t.length?null:Object(c.jsxs)("div",{children:[r||(r=Object(c.jsx)("h3",{className:"page-sub-heading",children:"Teams"})),Object(c.jsx)("div",{className:"gf-form-group",children:Object(c.jsxs)("table",{className:"filter-table form-inline","aria-label":"User teams table",children:[i||(i=Object(c.jsx)("thead",{children:Object(c.jsxs)("tr",{children:[Object(c.jsx)("th",{}),Object(c.jsx)("th",{children:"Name"}),Object(c.jsx)("th",{children:"Email"}),Object(c.jsx)("th",{children:"Members"})]})})),Object(c.jsx)("tbody",{children:t.map((e,t)=>Object(c.jsxs)("tr",{children:[Object(c.jsx)("td",{className:"width-4 text-center",children:Object(c.jsx)("img",{className:"filter-table__avatar",src:e.avatarUrl})}),Object(c.jsx)("td",{children:e.name}),Object(c.jsx)("td",{children:e.email}),Object(c.jsx)("td",{children:e.memberCount})]},t))})]})})]})}}},mHLn:function(e,t,s){"use strict";s.d(t,"a",(function(){return u}));var n,r,i=s("q1tI"),a=s("vF1F"),o=s("kDLi"),c=s("Csm0"),d=s("NXk7"),l=s("oeIY"),j=s("nKUr");const b=[{value:"",label:"Default"},{value:"dark",label:"Dark"},{value:"light",label:"Light"}];class u extends i.PureComponent{constructor(e){super(e),this.service=void 0,this.onSubmitForm=async()=>{const{homeDashboardId:e,theme:t,timezone:s}=this.state;await this.service.update({homeDashboardId:e,theme:t,timezone:s}),window.location.reload()},this.onThemeChanged=e=>{this.setState({theme:e})},this.onTimeZoneChanged=e=>{e&&this.setState({timezone:e})},this.onHomeDashboardChanged=e=>{this.setState({homeDashboardId:e})},this.getFullDashName=e=>void 0===e.folderTitle||""===e.folderTitle?e.title:e.folderTitle+" / "+e.title,this.service=new l.a(e.resourceUri),this.state={homeDashboardId:0,theme:"",timezone:"",dashboards:[]}}async componentDidMount(){const e=await this.service.load(),t=await d.b.search({starred:!0});if(e.homeDashboardId>0&&!t.find(t=>t.id===e.homeDashboardId)){const s=await d.b.search({dashboardIds:[e.homeDashboardId]});s&&s.length>0&&t.push(s[0])}this.setState({homeDashboardId:e.homeDashboardId,theme:e.theme,timezone:e.timezone,dashboards:[{id:0,title:"Default",tags:[],type:"",uid:"",uri:"",url:"",folderId:0,folderTitle:"",folderUid:"",folderUrl:"",isStarred:!1,slug:"",items:[]},...t]})}render(){const{theme:e,timezone:t,homeDashboardId:s,dashboards:i}=this.state,a=h();return Object(j.jsx)(o.Form,{onSubmit:this.onSubmitForm,children:()=>{var d;return Object(j.jsxs)(o.FieldSet,{label:"Preferences",children:[Object(j.jsx)(o.Field,{label:"UI Theme",children:Object(j.jsx)(o.RadioButtonGroup,{options:b,value:null===(d=b.find(t=>t.value===e))||void 0===d?void 0:d.value,onChange:this.onThemeChanged})}),Object(j.jsx)(o.Field,{label:Object(j.jsxs)(o.Label,{children:[Object(j.jsx)("span",{className:a.labelText,children:"Home Dashboard"}),n||(n=Object(j.jsx)(o.Tooltip,{content:"Not finding dashboard you want? Star it first, then it should appear in this select box.",children:Object(j.jsx)(o.Icon,{name:"info-circle"})}))]}),"aria-label":"User preferences home dashboard drop down",children:Object(j.jsx)(o.Select,{value:i.find(e=>e.id===s),getOptionValue:e=>e.id,getOptionLabel:this.getFullDashName,onChange:e=>this.onHomeDashboardChanged(e.id),options:i,placeholder:"Choose default dashboard"})}),Object(j.jsx)(o.Field,{label:"Timezone","aria-label":c.selectors.components.TimeZonePicker.container,children:Object(j.jsx)(o.TimeZonePicker,{includeInternal:!0,value:t,onChange:this.onTimeZoneChanged})}),r||(r=Object(j.jsx)("div",{className:"gf-form-button-row",children:Object(j.jsx)(o.Button,{variant:"primary","aria-label":"User preferences save button",children:"Save"})}))]})}})}}t.b=u;const h=Object(o.stylesFactory)(()=>({labelText:a.css`
      margin-right: 6px;
    `}))},ubFk:function(e,t,s){"use strict";var n=s("Zcyb");t.a=function(e){Object(n.a)((function(){e()}))}},xfwI:function(e,t,s){"use strict";var n,r,i,a,o,c=s("q1tI"),d=s("kDLi"),l=s("nKUr");class j extends c.PureComponent{render(){const{isLoading:e,sessions:t,revokeUserSession:s}=this.props;return e?n||(n=Object(l.jsx)(d.LoadingPlaceholder,{text:"Loading sessions..."})):Object(l.jsx)("div",{children:t.length>0&&Object(l.jsxs)(l.Fragment,{children:[r||(r=Object(l.jsx)("h3",{className:"page-sub-heading",children:"Sessions"})),Object(l.jsx)("div",{className:"gf-form-group",children:Object(l.jsxs)("table",{className:"filter-table form-inline","aria-label":"User sessions table",children:[i||(i=Object(l.jsx)("thead",{children:Object(l.jsxs)("tr",{children:[Object(l.jsx)("th",{children:"Last seen"}),Object(l.jsx)("th",{children:"Logged on"}),Object(l.jsx)("th",{children:"IP address"}),Object(l.jsx)("th",{children:"Browser & OS"}),Object(l.jsx)("th",{})]})})),Object(l.jsx)("tbody",{children:t.map((e,t)=>Object(l.jsxs)("tr",{children:[e.isActive?a||(a=Object(l.jsx)("td",{children:"Now"})):Object(l.jsx)("td",{children:e.seenAt}),Object(l.jsx)("td",{children:e.createdAt}),Object(l.jsx)("td",{children:e.clientIp}),Object(l.jsxs)("td",{children:[e.browser," on ",e.os," ",e.osVersion]}),Object(l.jsx)("td",{children:Object(l.jsx)(d.Button,{size:"sm",variant:"destructive",onClick:()=>s(e.id),children:o||(o=Object(l.jsx)(d.Icon,{name:"power"}))})})]},t))})]})})]})})}}t.a=j},z6Vd:function(e,t,s){"use strict";s.r(t),function(e){s.d(t,"UserProfileEditPage",(function(){return x}));s("q1tI");var n,r=s("/MKj"),i=s("ubFk"),a=s("0cfB"),o=s("kDLi"),c=s("lzJ5"),d=s("ZGyg"),l=s("3QIV"),j=s("Fceo"),b=s("mHLn"),u=s("bkZ+"),h=s("53+f"),O=s("xfwI"),g=s("nKUr");const m={initUserProfilePage:l.c,revokeUserSession:l.e,changeUserOrg:l.b,updateUserProfile:l.f},f=Object(r.connect)((function(e){const t=e.user,{user:s,teams:n,orgs:r,sessions:i,teamsAreLoading:a,orgsAreLoading:o,sessionsAreLoading:d,isUpdating:l}=t;return{navModel:Object(c.a)(e.navIndex,"profile-settings"),orgsAreLoading:o,sessionsAreLoading:d,teamsAreLoading:a,orgs:r,sessions:i,teams:n,isUpdating:l,user:s}}),m);function x({navModel:e,orgsAreLoading:t,sessionsAreLoading:s,teamsAreLoading:r,initUserProfilePage:a,orgs:c,sessions:l,teams:m,isUpdating:f,user:x,revokeUserSession:p,changeUserOrg:v,updateUserProfile:U}){return Object(i.a)(()=>a()),Object(g.jsx)(d.a,{navModel:e,children:Object(g.jsx)(d.a.Contents,{isLoading:!x,children:Object(g.jsxs)(o.VerticalGroup,{spacing:"md",children:[Object(g.jsx)(j.a,{updateProfile:U,isSavingUser:f,user:x}),n||(n=Object(g.jsx)(b.b,{resourceUri:"user"})),Object(g.jsx)(u.a,{isLoading:r,teams:m}),Object(g.jsx)(h.a,{isLoading:t,setUserOrg:v,orgs:c,user:x}),Object(g.jsx)(O.a,{isLoading:s,revokeUserSession:p,sessions:l})]})})})}t.default=Object(a.hot)(e)(f(x))}.call(this,s("3UD+")(e))}}]);
//# sourceMappingURL=UserProfileEditPage.287cd4997284268b2f13.js.map