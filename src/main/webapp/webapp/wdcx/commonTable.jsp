<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/5/11
  Time: 16:52
--%>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<c:forEach var="ckdx" items="${ckCkdxList}">
    <table style="margin-top:5px;margin-bottom:5px;border: 1px solid #e7e7e7;border-bottom:0;border-left: 0;border-top: 0;border-right: 0;width:100%">
        <colgroup>
            <col width="10%"/>
            <col width="15%"/>
            <col width="20%"/>
            <col width="15%"/>
            <col width="20%"/>
            <col width="15%"/>
            <col width="20%"/>
        </colgroup>

        <tr>
            <td rowspan="4" class="tdTitle">
                <label><input name="fxk1" class="inputCheck" type="checkbox"/></label>
            </td>
            <td class="tdTitle">案号<i class="required">*</i></td>
            <td class="tdCont"><input name="ckCkdxMap['${ckdx.cklsh}'].ah" class="inputText validate" type="text"
                                      value="${ckxz.ah}"/></td>
            <td class="tdTitle">承办人<i class="required">*</i></td>
            <td class="tdCont"><input id="cbr" name="cbr" class="inputText validate" type="text" value="${ckxz.cbr}"/>
            </td>
            <td class="tdTitle">书记员<i class="required">*</i></td>
            <td class="tdCont"><input id="sjy" name="sjy" class="inputText validate" type="text" value="${ckxz.sjy}"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">查询对象<i class="required">*</i></td>
            <td class="tdCont"><input id="mc" name="mc" class="inputText validate" type="text" value="${ckxz.mc}"/></td>
            <td class="tdTitle">涉案身份</td>
            <td class="tdCont">
                <input id="hSasf" type="hidden" value="${ckxz.ssdw}"/>
                <input id="sasfOption" type="hidden" value="${sasf}"/>
                <select class="inputSel sasf" name="ssdw">
                    <option value=""></option>
                </select>
            </td>
            <td class="tdTitle">类型<i class="required">*</i></td>
            <td class="tdCont"><input class="inputText validate" type="text" value="自然人" disabled="disabled"/></td>
        </tr>
        <tr>
            <td class="tdTitle">证件类型<i class="required">*</i></td>
            <td class="tdCont">
                <input id="hZjlx" type="hidden" value="${ckxz.zjlx}"/>
                <input id="zjlxOption" type="hidden" value="${zjfl}"/>
                <select class="inputSel validate zjlx" name="zjlx">
                    <option value=""></option>
                </select>
            </td>
            <td class="tdTitle">证件号码<i class="required">*</i></td>
            <td class="tdCont" colspan="3"><input id="zjhm" name="zjhm" class="inputText validate" type="text"
                                                  value="${ckxz.zjhm}"/></td>
        </tr>
        <tr>
            <td class="tdTitle">国家或地区</td>
            <td class="tdCont">
                <input id="hGj" type="hidden" value="${ckxz.gj}"/>
                <input id="gjOption" type="hidden" value="${gj}"/>
                <select class="inputSel gj" name="gj">
                    <option value=""></option>
                </select>
            </td>
            <td class="tdTitle">户籍地<i class="required">*</i></td>
            <td class="tdCont" colspan="3"><input id="hjszd" name="hjszd" class="inputText validate" type="text"
                                                  value="${ckxz.hjszd}"/></td>
        </tr>
        <tr>
            <td colspan="7" align="right">
                <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="addCxdx" onclick="addCxdx()">
                    <i class="tdh_icon icon_add"></i>新增
                </a>
                <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="delCxdx" onclick="delCxdx()">
                    <i class="tdh_icon icon_del"></i>删除
                </a>
            </td>
        </tr>
    </table>
</c:forEach>


