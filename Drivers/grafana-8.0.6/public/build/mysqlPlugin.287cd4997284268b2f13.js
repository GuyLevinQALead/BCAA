(window.webpackJsonp=window.webpackJsonp||[]).push([[77],{QjE0:function(e,t,a){"use strict";let r;a.d(t,"a",(function(){return r})),a.d(t,"c",(function(){return s})),a.d(t,"b",(function(){return i})),function(e){e.Password="password",e.BasicAuthPassword="basicAuthPassword"}(r||(r={}));const s=(e,t)=>a=>{a.preventDefault(),e.current[t]=void 0,e.current.secureJsonFields[t]=!1,e.current.secureJsonData=e.current.secureJsonData||{},e.current.secureJsonData[t]=""},i=(e,t)=>a=>{e.current.secureJsonData=e.current.secureJsonData||{},e.current.secureJsonData[t]=a.currentTarget.value}},"XXK+":function(e,t,a){"use strict";a.d(t,"b",(function(){return s})),a.d(t,"a",(function(){return i}));var r=a("LvDl");class s{constructor(e){this.type=void 0,this.style=void 0,this.label=void 0,this.params=void 0,this.defaultParams=void 0,this.wrapOpen=void 0,this.wrapClose=void 0,this.separator=void 0,this.type=e.type,e.label?this.label=e.label:this.label=this.type[0].toUpperCase()+this.type.substring(1)+":",this.style=e.style,"function"===this.style?(this.wrapOpen="(",this.wrapClose=")",this.separator=", "):(this.wrapOpen=" ",this.wrapClose=" ",this.separator=" "),this.params=e.params,this.defaultParams=e.defaultParams}}class i{constructor(e,t){if(this.part=void 0,this.def=void 0,this.params=void 0,this.label=void 0,this.name=void 0,this.datatype=void 0,this.part=e,this.def=t,!this.def)throw{message:"Could not find sql part "+e.type};this.datatype=e.datatype,e.name?(this.name=e.name,this.label=t.label+" "+e.name):(this.name="",this.label=t.label),e.params=e.params||Object(r.clone)(this.def.defaultParams),this.params=e.params}updateParam(e,t){""===e&&this.def.params[t].optional?this.params.splice(t,1):this.params[t]=e,this.part.params=this.params}}},"qSU+":function(e,t,a){"use strict";a.r(t);var r=a("LvDl"),s=a("F/XL"),i=a("67Y/"),n=a("aGNc"),l=a("9Z1F"),o=a("t8hP");class u{constructor(e,t,a){this.target=void 0,this.templateSrv=void 0,this.scopedVars=void 0,this.target=e,this.templateSrv=t,this.scopedVars=a,e.format=e.format||"time_series",e.timeColumn=e.timeColumn||"time",e.metricColumn=e.metricColumn||"none",e.group=e.group||[],e.where=e.where||[{type:"macro",name:"$__timeFilter",params:[]}],e.select=e.select||[[{type:"column",params:["value"]}]],"rawQuery"in this.target||(e.rawQuery="rawSql"in e),this.interpolateQueryStr=this.interpolateQueryStr.bind(this)}unquoteIdentifier(e){return'"'===e[0]&&'"'===e[e.length-1]?e.substring(1,e.length-1).replace(/""/g,'"'):e}quoteIdentifier(e){return'"'+e.replace(/"/g,'""')+'"'}quoteLiteral(e){return"'"+e.replace(/'/g,"''")+"'"}escapeLiteral(e){return String(e).replace(/'/g,"''")}hasTimeGroup(){return Object(r.find)(this.target.group,e=>"time"===e.type)}hasMetricColumn(){return"none"!==this.target.metricColumn}interpolateQueryStr(e,t,a){if(!t.multi&&!t.includeAll)return this.escapeLiteral(e);if("string"==typeof e)return this.quoteLiteral(e);return Object(r.map)(e,this.quoteLiteral).join(",")}render(e){const t=this.target;return this.target.rawQuery||"table"in this.target?(t.rawQuery||(t.rawSql=this.buildQuery()),e?this.templateSrv.replace(t.rawSql,this.scopedVars,this.interpolateQueryStr):t.rawSql):""}hasUnixEpochTimecolumn(){return["int","bigint","double"].indexOf(this.target.timeColumnType)>-1}buildTimeColumn(e=!0){const t=this.hasTimeGroup();let a,r="$__timeGroup";if(t){let s;s=t.params.length>1&&"none"!==t.params[1]?t.params.join(","):t.params[0],this.hasUnixEpochTimecolumn()&&(r="$__unixEpochGroup"),e&&(r+="Alias"),a=r+"("+this.target.timeColumn+","+s+")"}else a=this.target.timeColumn,e&&(a+=' AS "time"');return a}buildMetricColumn(){return this.hasMetricColumn()?this.target.metricColumn+" AS metric":""}buildValueColumns(){let e="";for(const t of this.target.select)e+=",\n  "+this.buildValueColumn(t);return e}buildValueColumn(e){let t="";t=Object(r.find)(e,e=>"column"===e.type).params[0];const a=Object(r.find)(e,e=>"aggregate"===e.type);if(a){t=a.params[0]+"("+t+")"}const s=Object(r.find)(e,e=>"alias"===e.type);return s&&(t+=" AS "+this.quoteIdentifier(s.params[0])),t}buildWhereClause(){let e="";const t=Object(r.map)(this.target.where,(e,t)=>{switch(e.type){case"macro":return e.name+"("+this.target.timeColumn+")";case"expression":return e.params.join(" ")}});return t.length>0&&(e="\nWHERE\n  "+t.join(" AND\n  ")),e}buildGroupClause(){let e="",t="";for(let e=0;e<this.target.group.length;e++){const a=this.target.group[e];e>0&&(t+=", "),"time"===a.type?t+="1":t+=a.params[0]}return t.length&&(e="\nGROUP BY "+t,this.hasMetricColumn()&&(e+=",2")),e}buildQuery(){let e="SELECT";return e+="\n  "+this.buildTimeColumn(),this.hasMetricColumn()&&(e+=",\n  "+this.buildMetricColumn()),e+=this.buildValueColumns(),e+="\nFROM "+this.target.table,e+=this.buildWhereClause(),e+=this.buildGroupClause(),e+="\nORDER BY "+this.buildTimeColumn(!1),e}}u.$inject=["target","templateSrv","scopedVars"];class m{transformMetricFindResponse(e){const t=Object(o.toDataQueryResponse)(e).data;if(!t||!t.length)return[];const a=t[0],r=[],s=a.fields.find(e=>"__text"===e.name),i=a.fields.find(e=>"__value"===e.name);if(s&&i)for(let e=0;e<s.values.length;e++)r.push({text:""+s.values.get(e),value:""+i.values.get(e)});else r.push(...a.fields.flatMap(e=>e.values.toArray()).map(e=>({text:e})));return Array.from(new Set(r.map(e=>e.text))).map(e=>{var t;return{text:e,value:null===(t=r.find(t=>t.text===e))||void 0===t?void 0:t.value}})}async transformAnnotationResponse(e,t){const a=Object(o.toDataQueryResponse)({data:t}).data;if(!a||!a.length)return[];const r=a[0],s=r.fields.find(e=>"time"===e.name||"time_sec"===e.name);if(!s)throw new Error("Missing mandatory time column (with time column alias) in annotation query");if(r.fields.find(e=>"title"===e.name))throw new Error("The title column for annotations is deprecated, now only a column named text is returned");const i=r.fields.find(e=>"timeend"===e.name),n=r.fields.find(e=>"text"===e.name),l=r.fields.find(e=>"tags"===e.name),u=[];for(let t=0;t<r.length;t++){const a=i&&i.values.get(t)?Math.floor(i.values.get(t)):void 0;u.push({annotation:e.annotation,time:Math.floor(s.values.get(t)),timeEnd:a,text:n&&n.values.get(t)?n.values.get(t):"",tags:l&&l.values.get(t)?l.values.get(t).trim().split(/\s*,\s*/):[]})}return u}}var h=a("5kRJ"),c=a("+JjO"),d=a("NPB1");function p(){return(p=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var r in a)Object.prototype.hasOwnProperty.call(a,r)&&(e[r]=a[r])}return e}).apply(this,arguments)}class g extends o.DataSourceWithBackend{constructor(e,t=Object(h.a)(),a=Object(d.a)()){super(e),this.id=void 0,this.name=void 0,this.responseParser=void 0,this.queryModel=void 0,this.interval=void 0,this.interpolateVariable=(e,t)=>{if("string"==typeof e){if(t.multi||t.includeAll){return this.queryModel.quoteLiteral(e)}return e}if("number"==typeof e)return e;return Object(r.map)(e,e=>this.queryModel.quoteLiteral(e)).join(",")},this.templateSrv=t,this.timeSrv=a,this.name=e.name,this.id=e.id,this.responseParser=new m,this.queryModel=new u({});const s=e.jsonData||{};this.interval=s.timeInterval||"1m"}interpolateVariablesInQueries(e,t){let a=e;return e&&e.length>0&&(a=e.map(e=>p({},e,{datasource:this.name,rawSql:this.templateSrv.replace(e.rawSql,t,this.interpolateVariable),rawQuery:!0}))),a}filterQuery(e){return!e.hide}applyTemplateVariables(e,t){const a=new u(e,this.templateSrv,t);return{refId:e.refId,datasourceId:this.id,rawSql:a.render(this.interpolateVariable),format:e.format}}async annotationQuery(e){if(!e.annotation.rawQuery)return Promise.reject({message:"Query missing in annotation definition"});const t={refId:e.annotation.name,datasourceId:this.id,rawSql:this.templateSrv.replace(e.annotation.rawQuery,e.scopedVars,this.interpolateVariable),format:"table"};return Object(o.getBackendSrv)().fetch({url:"/api/ds/query",method:"POST",data:{from:e.range.from.valueOf().toString(),to:e.range.to.valueOf().toString(),queries:[t]},requestId:e.annotation.name}).pipe(Object(i.a)(async t=>await this.responseParser.transformAnnotationResponse(e,t.data))).toPromise()}metricFindQuery(e,t){let a="tempvar";t&&t.variable&&t.variable.name&&(a=t.variable.name);const r=this.templateSrv.replace(e,Object(c.g)({query:e,wildcardChar:"%",options:t}),this.interpolateVariable),s={refId:a,datasourceId:this.id,rawSql:r,format:"table"},n=this.timeSrv.timeRange();return Object(o.getBackendSrv)().fetch({url:"/api/ds/query",method:"POST",data:{from:n.from.valueOf().toString(),to:n.to.valueOf().toString(),queries:[s]},requestId:a}).pipe(Object(i.a)(e=>this.responseParser.transformMetricFindResponse(e))).toPromise()}testDatasource(){return Object(o.getBackendSrv)().fetch({url:"/api/ds/query",method:"POST",data:{from:"5m",to:"now",queries:[{refId:"A",intervalMs:1,maxDataPoints:1,datasourceId:this.id,rawSql:"SELECT 1",format:"table"}]}}).pipe(Object(n.a)({status:"success",message:"Database Connection OK"}),Object(l.a)(e=>(console.error(e),e.data&&e.data.message?Object(s.a)({status:"error",message:e.data.message}):Object(s.a)({status:"error",message:e.status})))).toPromise()}targetContainsTemplate(e){let t="";if(e.rawQuery)t=e.rawSql;else{t=new u(e).buildQuery()}return t=t.replace("$__",""),this.templateSrv.variableExists(t)}}var y=a("Xmxp");class b{constructor(e,t){this.target=e,this.queryModel=t}getOperators(e){switch(e){case"double":case"float":return["=","!=","<","<=",">",">="];case"text":case"tinytext":case"mediumtext":case"longtext":case"varchar":case"char":return["=","!=","<","<=",">",">=","IN","NOT IN","LIKE","NOT LIKE"];default:return["=","!=","<","<=",">",">=","IN","NOT IN"]}}quoteIdentAsLiteral(e){return this.queryModel.quoteLiteral(this.queryModel.unquoteIdentifier(e))}findMetricTable(){return"\n  SELECT\n    table_name as table_name,\n    ( SELECT\n        column_name as column_name\n      FROM information_schema.columns c\n      WHERE\n        c.table_schema = t.table_schema AND\n        c.table_name = t.table_name AND\n        c.data_type IN ('timestamp', 'datetime')\n      ORDER BY ordinal_position LIMIT 1\n    ) AS time_column,\n    ( SELECT\n        column_name AS column_name\n      FROM information_schema.columns c\n      WHERE\n        c.table_schema = t.table_schema AND\n        c.table_name = t.table_name AND\n        c.data_type IN('float', 'int', 'bigint')\n      ORDER BY ordinal_position LIMIT 1\n    ) AS value_column\n  FROM information_schema.tables t\n  WHERE\n    t.table_schema = database() AND\n    EXISTS\n    ( SELECT 1\n      FROM information_schema.columns c\n      WHERE\n        c.table_schema = t.table_schema AND\n        c.table_name = t.table_name AND\n        c.data_type IN ('timestamp', 'datetime')\n    ) AND\n    EXISTS\n    ( SELECT 1\n      FROM information_schema.columns c\n      WHERE\n        c.table_schema = t.table_schema AND\n        c.table_name = t.table_name AND\n        c.data_type IN('float', 'int', 'bigint')\n    )\n  LIMIT 1\n;"}buildTableConstraint(e){let t="";if(e.includes(".")){const a=e.split(".");return t="table_schema = "+this.quoteIdentAsLiteral(a[0]),t+=" AND table_name = "+this.quoteIdentAsLiteral(a[1]),t}return t="table_schema = database() AND table_name = "+this.quoteIdentAsLiteral(e),t}buildTableQuery(){return"SELECT table_name FROM information_schema.tables WHERE table_schema = database() ORDER BY table_name"}buildColumnQuery(e){let t="SELECT column_name FROM information_schema.columns WHERE ";switch(t+=this.buildTableConstraint(this.target.table),e){case"time":t+=" AND data_type IN ('timestamp','datetime','bigint','int','double','float')";break;case"metric":t+=" AND data_type IN ('text','tinytext','mediumtext','longtext','varchar','char')";break;case"value":t+=" AND data_type IN ('bigint','int','smallint','mediumint','tinyint','double','decimal','float')",t+=" AND column_name <> "+this.quoteIdentAsLiteral(this.target.timeColumn);break;case"group":t+=" AND data_type IN ('text','tinytext','mediumtext','longtext','varchar','char')"}return t+=" ORDER BY column_name",t}buildValueQuery(e){let t="SELECT DISTINCT QUOTE("+e+")";return t+=" FROM "+this.target.table,t+=" WHERE $__timeFilter("+this.target.timeColumn+")",t+=" ORDER BY 1 LIMIT 100",t}buildDatatypeQuery(e){let t="\nSELECT data_type\nFROM information_schema.columns\nWHERE ";return t+=" table_name = "+this.quoteIdentAsLiteral(this.target.table),t+=" AND column_name = "+this.quoteIdentAsLiteral(e),t}}var f=a("LzXI"),v=a("XXK+");const S=[];function w(e){S[e.type]=new v.b(e)}w({type:"column",style:"label",params:[{type:"column",dynamicLookup:!0}],defaultParams:["value"]}),w({type:"expression",style:"expression",label:"Expr:",params:[{name:"left",type:"string",dynamicLookup:!0},{name:"op",type:"string",dynamicLookup:!0},{name:"right",type:"string",dynamicLookup:!0}],defaultParams:["value","=","value"]}),w({type:"macro",style:"label",label:"Macro:",params:[],defaultParams:[]}),w({type:"aggregate",style:"label",params:[{name:"name",type:"string",options:["avg","count","min","max","sum","stddev","variance"]}],defaultParams:["avg"]}),w({type:"alias",style:"label",params:[{name:"name",type:"string",quote:"double"}],defaultParams:["alias"]}),w({type:"time",style:"function",label:"time",params:[{name:"interval",type:"interval",options:["$__interval","1s","10s","1m","5m","10m","15m","1h"]},{name:"fill",type:"string",options:["none","NULL","previous","0"]}],defaultParams:["$__interval","none"]});var C={create:function(e){const t=S[e.type];return t?new v.a(e,t):null}},P=a("Obii"),_=a("xAj+");class E extends f.QueryCtrl{constructor(e,t,a,r){super(e,t),this.formats=void 0,this.lastQueryError=void 0,this.showHelp=void 0,this.queryModel=void 0,this.metaBuilder=void 0,this.lastQueryMeta=void 0,this.tableSegment=void 0,this.whereAdd=void 0,this.timeColumnSegment=void 0,this.metricColumnSegment=void 0,this.selectMenu=[],this.selectParts=[],this.groupParts=[],this.whereParts=[],this.groupAdd=void 0,this.templateSrv=a,this.uiSegmentSrv=r,this.target=this.target,this.queryModel=new u(this.target,a,this.panel.scopedVars),this.metaBuilder=new b(this.target,this.queryModel),this.updateProjection(),this.formats=[{text:"Time series",value:"time_series"},{text:"Table",value:"table"}],this.target.rawSql||("table"===this.panelCtrl.panel.type?(this.target.format="table",this.target.rawSql="SELECT 1",this.target.rawQuery=!0):(this.target.rawSql="SELECT\n  UNIX_TIMESTAMP(<time_column>) as time_sec,\n  <value column> as value,\n  <series name column> as metric\nFROM <table name>\nWHERE $__timeFilter(time_column)\nORDER BY <time_column> ASC\n",this.datasource.metricFindQuery(this.metaBuilder.findMetricTable()).then(e=>{if(e.length>0){this.target.table=e[0].text;let t=this.uiSegmentSrv.newSegment(this.target.table);this.tableSegment.html=t.html,this.tableSegment.value=t.value,this.target.timeColumn=e[1].text,t=this.uiSegmentSrv.newSegment(this.target.timeColumn),this.timeColumnSegment.html=t.html,this.timeColumnSegment.value=t.value,this.target.timeColumnType="timestamp",this.target.select=[[{type:"column",params:[e[2].text]}]],this.updateProjection(),this.updateRawSqlAndRefresh()}}))),this.target.table?this.tableSegment=r.newSegment(this.target.table):this.tableSegment=r.newSegment({value:"select table",fake:!0}),this.timeColumnSegment=r.newSegment(this.target.timeColumn),this.metricColumnSegment=r.newSegment(this.target.metricColumn),this.buildSelectMenu(),this.whereAdd=this.uiSegmentSrv.newPlusButton(),this.groupAdd=this.uiSegmentSrv.newPlusButton(),this.panelCtrl.events.on(P.PanelEvents.dataReceived,this.onDataReceived.bind(this),e),this.panelCtrl.events.on(P.PanelEvents.dataError,this.onDataError.bind(this),e)}updateRawSqlAndRefresh(){this.target.rawQuery||(this.target.rawSql=this.queryModel.buildQuery()),this.panelCtrl.refresh()}updateProjection(){this.selectParts=Object(r.map)(this.target.select,e=>Object(r.map)(e,C.create).filter(e=>e)),this.whereParts=Object(r.map)(this.target.where,C.create).filter(e=>e),this.groupParts=Object(r.map)(this.target.group,C.create).filter(e=>e)}updatePersistedParts(){this.target.select=Object(r.map)(this.selectParts,e=>Object(r.map)(e,e=>({type:e.def.type,datatype:e.datatype,params:e.params}))),this.target.where=Object(r.map)(this.whereParts,e=>({type:e.def.type,datatype:e.datatype,name:e.name,params:e.params})),this.target.group=Object(r.map)(this.groupParts,e=>({type:e.def.type,datatype:e.datatype,params:e.params}))}buildSelectMenu(){this.selectMenu.push({text:"Aggregate Functions",value:"aggregate",submenu:[{text:"Average",value:"avg"},{text:"Count",value:"count"},{text:"Maximum",value:"max"},{text:"Minimum",value:"min"},{text:"Sum",value:"sum"},{text:"Standard deviation",value:"stddev"},{text:"Variance",value:"variance"}]}),this.selectMenu.push({text:"Alias",value:"alias"}),this.selectMenu.push({text:"Column",value:"column"})}toggleEditorMode(){this.target.rawQuery?y.b.publish(new _.ShowConfirmModalEvent({title:"Warning",text2:"Switching to query builder may overwrite your raw SQL.",icon:"exclamation-triangle",yesText:"Switch",onConfirm:()=>{this.$scope.$evalAsync(()=>{this.target.rawQuery=!this.target.rawQuery})}})):this.$scope.$evalAsync(()=>{this.target.rawQuery=!this.target.rawQuery})}resetPlusButton(e){const t=this.uiSegmentSrv.newPlusButton();e.html=t.html,e.value=t.value}getTableSegments(){return this.datasource.metricFindQuery(this.metaBuilder.buildTableQuery()).then(this.transformToSegments({})).catch(this.handleQueryError.bind(this))}tableChanged(){this.target.table=this.tableSegment.value,this.target.where=[],this.target.group=[],this.updateProjection();const e=this.uiSegmentSrv.newSegment("none");this.metricColumnSegment.html=e.html,this.metricColumnSegment.value=e.value,this.target.metricColumn="none";const t=this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery("time")).then(e=>{if(e.length>0&&!Object(r.find)(e,e=>e.text===this.target.timeColumn)){const t=this.uiSegmentSrv.newSegment(e[0].text);this.timeColumnSegment.html=t.html,this.timeColumnSegment.value=t.value}return this.timeColumnChanged(!1)}),a=this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery("value")).then(e=>{e.length>0&&(this.target.select=[[{type:"column",params:[e[0].text]}]],this.updateProjection())});Promise.all([t,a]).then(()=>{this.updateRawSqlAndRefresh()})}getTimeColumnSegments(){return this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery("time")).then(this.transformToSegments({})).catch(this.handleQueryError.bind(this))}timeColumnChanged(e){return this.target.timeColumn=this.timeColumnSegment.value,this.datasource.metricFindQuery(this.metaBuilder.buildDatatypeQuery(this.target.timeColumn)).then(t=>{if(1===t.length){let e;this.target.timeColumnType!==t[0].text&&(this.target.timeColumnType=t[0].text),e=this.queryModel.hasUnixEpochTimecolumn()?C.create({type:"macro",name:"$__unixEpochFilter",params:[]}):C.create({type:"macro",name:"$__timeFilter",params:[]}),this.whereParts.length>=1&&"macro"===this.whereParts[0].def.type?this.whereParts[0]=e:this.whereParts.splice(0,0,e)}this.updatePersistedParts(),!1!==e&&this.updateRawSqlAndRefresh()})}getMetricColumnSegments(){return this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery("metric")).then(this.transformToSegments({addNone:!0})).catch(this.handleQueryError.bind(this))}metricColumnChanged(){this.target.metricColumn=this.metricColumnSegment.value,this.updateRawSqlAndRefresh()}onDataReceived(e){var t;this.lastQueryError=void 0,this.lastQueryMeta=null===(t=e[0])||void 0===t?void 0:t.meta}onDataError(e){if(e.data&&e.data.results){const t=e.data.results[this.target.refId];t&&(this.lastQueryError=t.error)}}transformToSegments(e){return t=>{const a=Object(r.map)(t,e=>this.uiSegmentSrv.newSegment({value:e.text,expandable:e.expandable}));if(e.addTemplateVars)for(const t of this.templateSrv.getVariables()){let r;r="$"+t.name,e.templateQuoter&&!1===t.multi&&(r=e.templateQuoter(r)),a.unshift(this.uiSegmentSrv.newSegment({type:"template",value:r,expandable:!0}))}return e.addNone&&a.unshift(this.uiSegmentSrv.newSegment({type:"template",value:"none",expandable:!0})),a}}findAggregateIndex(e){return Object(r.findIndex)(e,e=>"aggregate"===e.def.type||"percentile"===e.def.type)}findWindowIndex(e){return Object(r.findIndex)(e,e=>"window"===e.def.type||"moving_window"===e.def.type)}addSelectPart(e,t,a){let s=t.value;a&&a.type&&(s=a.type);let i=C.create({type:s});a&&(i.params[0]=a.value);let n=!1;switch(s){case"column":const t=Object(r.map)(e,e=>C.create({type:e.def.type,params:Object(r.clone)(e.params)}));this.selectParts.push(t);break;case"percentile":case"aggregate":0===this.target.group.length&&this.addGroup("time","$__interval");const a=this.findAggregateIndex(e);-1!==a?e[a]=i:e.splice(1,0,i),Object(r.find)(e,e=>"alias"===e.def.type)||(n=!0);break;case"moving_window":case"window":const s=this.findWindowIndex(e);if(-1!==s)e[s]=i;else{const t=this.findAggregateIndex(e);-1!==t?e.splice(t+1,0,i):e.splice(1,0,i)}Object(r.find)(e,e=>"alias"===e.def.type)||(n=!0);break;case"alias":n=!0}n&&(i=C.create({type:"alias",params:[e[0].params[0].replace(/"/g,"")]}),"alias"===e[e.length-1].def.type?e[e.length-1]=i:e.push(i)),this.updatePersistedParts(),this.updateRawSqlAndRefresh()}removeSelectPart(e,t){if("column"===t.def.type){if(this.selectParts.length>1){const t=Object(r.indexOf)(this.selectParts,e);this.selectParts.splice(t,1)}}else{const a=Object(r.indexOf)(e,t);e.splice(a,1)}this.updatePersistedParts()}handleSelectPartEvent(e,t,a){switch(a.name){case"get-param-options":switch(t.def.type){case"column":return this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery("value")).then(this.transformToSegments({})).catch(this.handleQueryError.bind(this))}case"part-param-changed":this.updatePersistedParts(),this.updateRawSqlAndRefresh();break;case"action":this.removeSelectPart(e,t),this.updateRawSqlAndRefresh();break;case"get-part-actions":return Promise.resolve([{text:"Remove",value:"remove-part"}])}}handleGroupPartEvent(e,t,a){switch(a.name){case"get-param-options":return this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery()).then(this.transformToSegments({})).catch(this.handleQueryError.bind(this));case"part-param-changed":this.updatePersistedParts(),this.updateRawSqlAndRefresh();break;case"action":this.removeGroup(e,t),this.updateRawSqlAndRefresh();break;case"get-part-actions":return Promise.resolve([{text:"Remove",value:"remove-part"}])}}addGroup(e,t){let a=[t];"time"===e&&(a=["$__interval","none"]);const r=C.create({type:e,params:a});"time"===e?this.groupParts.splice(0,0,r):this.groupParts.push(r);for(const e of this.selectParts)if(!e.some(e=>"aggregate"===e.def.type)){const t=C.create({type:"aggregate",params:["avg"]});if(e.splice(1,0,t),!e.some(e=>"alias"===e.def.type)){const t=C.create({type:"alias",params:[e[0].part.params[0]]});e.push(t)}}this.updatePersistedParts()}removeGroup(e,t){"time"===e.def.type&&(this.selectParts=Object(r.map)(this.selectParts,e=>Object(r.filter)(e,e=>"aggregate"!==e.def.type&&"percentile"!==e.def.type))),this.groupParts.splice(t,1),this.updatePersistedParts()}handleWherePartEvent(e,t,a,r){switch(a.name){case"get-param-options":switch(a.param.name){case"left":return this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery()).then(this.transformToSegments({})).catch(this.handleQueryError.bind(this));case"right":return["int","bigint","double","datetime"].indexOf(t.datatype)>-1?Promise.resolve([]):this.datasource.metricFindQuery(this.metaBuilder.buildValueQuery(t.params[0])).then(this.transformToSegments({addTemplateVars:!0,templateQuoter:e=>this.queryModel.quoteLiteral(e)})).catch(this.handleQueryError.bind(this));case"op":return Promise.resolve(this.uiSegmentSrv.newOperators(this.metaBuilder.getOperators(t.datatype)));default:return Promise.resolve([])}case"part-param-changed":this.updatePersistedParts(),this.datasource.metricFindQuery(this.metaBuilder.buildDatatypeQuery(t.params[0])).then(e=>{1===e.length&&(t.datatype=e[0].text)}),this.updateRawSqlAndRefresh();break;case"action":e.splice(r,1),this.updatePersistedParts(),this.updateRawSqlAndRefresh();break;case"get-part-actions":return Promise.resolve([{text:"Remove",value:"remove-part"}])}}getWhereOptions(){const e=[];return this.queryModel.hasUnixEpochTimecolumn()?e.push(this.uiSegmentSrv.newSegment({type:"macro",value:"$__unixEpochFilter"})):e.push(this.uiSegmentSrv.newSegment({type:"macro",value:"$__timeFilter"})),e.push(this.uiSegmentSrv.newSegment({type:"expression",value:"Expression"})),Promise.resolve(e)}addWhereAction(e,t){switch(this.whereAdd.type){case"macro":{const e=C.create({type:"macro",name:this.whereAdd.value,params:[]});this.whereParts.length>=1&&"macro"===this.whereParts[0].def.type?this.whereParts[0]=e:this.whereParts.splice(0,0,e);break}default:this.whereParts.push(C.create({type:"expression",params:["value","=","value"]}))}this.updatePersistedParts(),this.resetPlusButton(this.whereAdd),this.updateRawSqlAndRefresh()}getGroupOptions(){return this.datasource.metricFindQuery(this.metaBuilder.buildColumnQuery("group")).then(e=>{const t=[];this.queryModel.hasTimeGroup()||t.push(this.uiSegmentSrv.newSegment({type:"time",value:"time($__interval,none)"}));for(const a of e)t.push(this.uiSegmentSrv.newSegment({type:"column",value:a.text}));return t}).catch(this.handleQueryError.bind(this))}addGroupAction(){this.groupAdd.value,this.addGroup(this.groupAdd.type,this.groupAdd.value),this.resetPlusButton(this.groupAdd),this.updateRawSqlAndRefresh()}handleQueryError(e){return this.error=e.message||"Failed to issue metric query",[]}}E.$inject=["$scope","$injector","templateSrv","uiSegmentSrv"],E.templateUrl="partials/query.editor.html";var O=a("QjE0");a.d(t,"ConfigCtrl",(function(){return x})),a.d(t,"AnnotationsQueryCtrl",(function(){return Q})),a.d(t,"plugin",(function(){return R})),a.d(t,"MysqlDatasource",(function(){return g})),a.d(t,"Datasource",(function(){return g})),a.d(t,"QueryCtrl",(function(){return E}));class x{constructor(){this.current=void 0,this.onPasswordReset=void 0,this.onPasswordChange=void 0,this.onPasswordReset=Object(O.c)(this,O.a.Password),this.onPasswordChange=Object(O.b)(this,O.a.Password)}}x.templateUrl="partials/config.html";class Q{constructor(e){this.annotation=e.ctrl.annotation,this.annotation.rawQuery=this.annotation.rawQuery||"SELECT\n    UNIX_TIMESTAMP(<time_column>) as time_sec,\n    <text_column> as text,\n    <tags_column> as tags\n  FROM <table name>\n  WHERE $__timeFilter(time_column)\n  ORDER BY <time_column> ASC\n  LIMIT 100\n  "}}Q.$inject=["$scope"],Q.templateUrl="partials/annotations.editor.html";const R=new P.DataSourcePlugin(g).setQueryCtrl(E).setConfigCtrl(x).setAnnotationQueryCtrl(Q)}}]);
//# sourceMappingURL=mysqlPlugin.287cd4997284268b2f13.js.map