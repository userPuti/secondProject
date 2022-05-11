<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/5/6
  Time: 10:21
--%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的查询</title>
    <jsp:include page="/resources/static/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_PAGE_LAYOUT"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webapp/wdcx/css/list.css"/>
    <script src="${pageContext.request.contextPath}/webapp/wdcx/js/listCommon.js"
            type="application/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/webapp/wdcx/js/wdcx_main.js"
            type="application/javascript" charset="utf-8"></script>
</head>
<body>

<div class="content">
    <div class="listwrapper">
        <div class="searchbar">
            <div class="searchbar_row">
                <em class="searchbar_name">状态：</em>
                <select class="inputSel" id="zt" name="zt" multiple="multiple" data-checkall="true"
                        style="width: 190px">
                    ${ztOption}
                </select>
            </div>
            <div class="searchbar_row">
                <em class="searchbar_name">案号：</em>
                <input id="ah" name="ah" class="inputText" type="text" placeholder="请输入" style="width: 190px;"/>
            </div>
            <div class="searchbar_row">
                <em class="searchbar_name">被执行人：</em>
                <input id="mc" name="mc" class="inputText" type="text" placeholder="请输入" style="width: 190px;"/>
            </div>
            <div class="searchbar_row">
                <em class="searchbar_name">证件号码：</em>
                <input id="zjhm" name="zjhm" class="inputText" type="text" placeholder="请输入" style="width: 190px;"/>
            </div>

            <div class="searchbar_row searchbar_row_fr">
                <a class="tdh_btn mgr8" href="javascript:void(0);" id="query" onclick="queryInfo()">
                    <i class="tdh_icon icon_search"></i>查询
                </a>

                <a class="tdh_btn mgr8" href="javascript:void(0);" id="addInfo" onclick="addInfo()">
                    <i class="tdh_icon icon_add"></i>新增
                </a>

                <a class="tdh_btn mgr8" href="javascript:void(0);" id="delUsers" onclick="batchDel()">
                    <i class="tdh_icon icon_del"></i>删除
                </a>
            </div>
        </div>
        <div class="list">
            <div id="grid" style="width: 100%; height: 100%;"></div>
        </div>
    </div>
</div>
</body>
</html>
