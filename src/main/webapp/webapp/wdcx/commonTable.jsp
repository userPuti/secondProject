<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/5/11
  Time: 16:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<tr>
    <td rowspan="6" align="center">查<br/>询<br/>对<br/>象<br/></td>
</tr>

<tr>
    <td rowspan="4" class="tdTitle">
        <label><input name="fxk1" class="inputCheck" type="checkbox"/></label>
    </td>
    <td class="tdTitle">案号<i class="required">*</i></td>
    <td class="tdCont"><input class="inputText" type="text" value="${ckxz.ah}"/></td>
    <td class="tdTitle">承办人<i class="required">*</i></td>
    <td class="tdCont"><input class="inputText" type="text" value="${ckxz.cbr}"/></td>
    <td class="tdTitle">书记员<i class="required">*</i></td>
    <td class="tdCont"><input class="inputText" type="text" value="${ckxz.sjy}"/></td>
</tr>
<tr>
    <td class="tdTitle">查询对象<i class="required">*</i></td>
    <td class="tdCont"><input class="inputText" type="text" value="${ckxz.mc}"/></td>
    <td class="tdTitle">涉案身份</td>
    <td class="tdCont">
        <input id="sasf" type="hidden" value="${ckxz.ssdw}"/>
        <select class="inputSel">
            <option value="" selected="selected"></option>
            ${sasfOption}
        </select>
    </td>
    <td class="tdTitle">类型<i class="required">*</i></td>
    <td class="tdCont"><input class="inputText" type="text" value="自然人" disabled="disabled"/></td>
</tr>
<tr>
    <td class="tdTitle">证件类型<i class="required">*</i></td>
    <td class="tdCont">
        <select class="inputSel">
            <option value="" selected="selected"></option>
            ${zjflOption}
        </select>
    </td>
    <td class="tdTitle">证件号码<i class="required">*</i></td>
    <td class="tdCont" colspan="3"><input class="inputText" type="text"/></td>
</tr>
<tr>
    <td class="tdTitle">国家或地区</td>
    <td class="tdCont">
        <select class="inputSel">
            <option value="" selected="selected"></option>
            ${gjOption}
        </select>
    </td>
    <td class="tdTitle">户籍地<i class="required">*</i></td>
    <td class="tdCont" colspan="3"><input class="inputText" type="text"/></td>
</tr>
<tr>
    <td colspan="7" align="right">
        <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="addInfo" onclick="addInfo()">
            <i class="tdh_icon icon_add"></i>新增
        </a>
        <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="delInfo" onclick="delInfo()">
            <i class="tdh_icon icon_deal"></i>删除
        </a>
    </td>
</tr>
</body>
</html>
