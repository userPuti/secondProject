<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/5/11
  Time: 15:52
--%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>查询申请登记</title>
    <jsp:include page="/resources/static/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_PAGE_LAYOUT"/>
    </jsp:include>
    <script src="${pageContext.request.contextPath}/webapp/wdcx/js/wdcx_cxsqdj.js"
            type="application/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.serialize.js"
            type="text/javascript"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/resources/static/js/plupload.full.min.js"></script>
</head>
<body style="padding: 0 10px;box-sizing: border-box; overflow-y: auto;">
<div style="padding-top: 10px">
    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="cxsqdjSave">
        <i class="tdh_icon icon_save"></i>保存
    </a>
    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="cxsqdjSend">
        <i class="tdh_icon icon_send"></i>发送
    </a>
</div>

<div class="tdh_form_title">司法查询登记表</div>
<div>
    <form id="ckxz" action="#">
        <table class="tdh_form">
            <colgroup>
                <col width="5%"/>
            </colgroup>
            <tr>
                <td align="center" class="tdhCont">查<br/>询<br/>对<br/>象<br/></td>
                <td id="cxdxTab">
                </td>
            </tr>
        </table>
        <table class="tdh_form" id="xzdwTable">
            <colgroup>
                <col width="5%"/>
                <col width="20%"/>
                <col width="75%"/>
            </colgroup>
            <tr>
                <td rowspan="4" align="center">协<br/>执<br/>单<br/>位<br/></td>
            </tr>
            <tr>
                <td class="tdCont" colspan="2">
                    <label><input id="selAll" class="inputCheck" type="checkbox"/>所有单位(选择下列单位发起财产查询通知,
                        至少选择一个协助执行单位)</label>
                </td>
            </tr>
            <input id="ckxzdwMap" type="hidden" value="${ckxzdwMap}"/>
        </table>
    </form>
</div>
<div>
    <table class="tdh_form">
        <colgroup>
            <col width="5%"/>
        </colgroup>

        <tr rowspan="4">
            <td align="center">协<br/>执<br/>说<br/>明<br/></td>
            <td class="tdCont_pd">
                <textarea class="inputArea"></textarea>
            </td>
        </tr>
    </table>
</div>
<div style="padding: 0px 0px 10px 0px">
    <table class="tdh_form">
        <colgroup>
            <col width="5%"/>
        </colgroup>

        <tr rowspan="4">
            <td align="center">文<br/>书<br/>信<br/>息<br/></td>
            <td class="tdCont_pd">
                <div align="right">
                    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="fileUpload"
                       onclick="saveInfo()">
                        <i class="tdh_icon icon_upload"></i>上传
                    </a>
                    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="filDel" onclick="sendInfo()">
                        <i class="tdh_icon icon_del"></i>删除
                    </a>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
