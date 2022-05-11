<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/5/11
  Time: 15:52
--%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
</head>
<body style="padding: 0 10px;box-sizing: border-box; overflow-y: auto;">
<div>
    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="save" onclick="saveInfo()">
        <i class="tdh_icon icon_save"></i>保存
    </a>
    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="send" onclick="sendInfo()">
        <i class="tdh_icon icon_send"></i>发送
    </a>
</div>

<div class="tdh_form_title">查控登记表</div>
<table class="tdh_form">
    <colgroup>
        <col width="5%"/>
        <col width="5%"/>
        <col width="11%"/>
        <col width="22%"/>
        <col width="11%"/>
        <col width="22%"/>
        <col width="11%"/>
        <col width="22%"/>
    </colgroup>
    <form id="cxdx" action="#">
        <jsp:include page="commonTable.jsp"/>
    </form>

    <form id="xzdz" action="#">
        <tr>
            <td rowspan="4" align="center">协<br/>执<br/>单<br/>位<br/></td>
        </tr>
        <tr>
            <td colspan="7" class="tdCont_pd">
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>所有单位(选择下列单位发起财产查询通知, 至少选择一个协助执行单位)</label>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>银行</label>
            </td>
            <td colspan="6" class="tdCont_pd">
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>工商银行</label>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>建设银行</label>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>农业银行</label><br/>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>中国银行</label>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>华夏银行</label>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>互联网金融</label>
            </td>
            <td colspan="6" class="tdCont_pd">
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>支付宝</label>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>百度金融</label>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>京东钱包</label><br/>
                <label><input name="fxk1" class="inputCheck" type="checkbox"/>腾讯财付通</label>
            </td>
        </tr>
    </form>

    <form id="xxsm" action="#">
        <tr rowspan="4">
            <td align="center">协<br/>执<br/>说<br/>明<br/></td>
            <td class="tdCont_pd" colspan="7">
                <textarea class="inputArea"></textarea>
            </td>
        </tr>
    </form>

    <form id="wsxx">
        <tr rowspan="4">
            <td align="center">文<br/>书<br/>信<br/>息<br/></td>

            <td class="tdCont_pd" colspan="7">
                <textarea class="inputArea"></textarea>
            </td>
        </tr>

    </form>
</table>

</body>
</html>
