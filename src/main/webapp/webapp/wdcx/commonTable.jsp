<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/5/11
  Time: 16:52
--%>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:forEach var="ckdx" items="${ckCkdxList}">
    <table id="${ckdx.cklsh}" class="tdh_table">
        <colgroup>
            <col width="7%"/>
            <col width="17%"/>
            <col width="18%"/>
            <col width="17%"/>
            <col width="18%"/>
            <col width="17%"/>
            <col width="18%"/>
        </colgroup>

        <tr>
            <td rowspan="4" class="tdTitle">
                <input type="hidden" name="ckCkdxMap['${ckdx.cklsh}'].cklsh" value="${ckdx.cklsh}"/>
                <input value="${ckdx.cklsh}" class="inputCheck tableInpChk" type="checkbox"/>
            </td>
            <td class="tdTitle">案号<i class="required">*</i></td>
            <td class="tdCont"><input id="ah" name="ckCkdxMap['${ckdx.cklsh}'].ah" class="inputText validate" type="text"
                                      value="${ckdx.ah}"/></td>
            <td class="tdTitle">承办人<i class="required">*</i></td>
            <td class="tdCont"><input id="cbr" name="ckCkdxMap['${ckdx.cklsh}'].cbr" class="inputText validate"
                                      type="text" value="${ckdx.cbr}"/>
            </td>
            <td class="tdTitle">书记员<i class="required">*</i></td>
            <td class="tdCont"><input id="sjy" name="ckCkdxMap['${ckdx.cklsh}'].sjy" class="inputText validate"
                                      type="text" value="${ckdx.sjy}"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">查询对象<i class="required">*</i></td>
            <td class="tdCont"><input id="mc" name="ckCkdxMap['${ckdx.cklsh}'].mc" class="inputText validate"
                                      type="text" value="${ckdx.mc}"/></td>
            <td class="tdTitle">涉案身份</td>
            <td class="tdCont">
                <select class="inputSel sasf" name="ckCkdxMap['${ckdx.cklsh}'].ssdw">
                    <option value=""></option>
                    <c:forEach items="${sasf}" var="ssdw">
                        <option value="${ssdw.code}" ${ssdw.code==ckdx.ssdw?'selected':''}>${ssdw.mc}</option>
                    </c:forEach>
                </select>
            </td>
            <td class="tdTitle">类型<i class="required">*</i></td>
            <td class="tdCont"><input class="inputText validate" type="text" value="自然人" disabled="disabled"/></td>
        </tr>
        <tr>
            <td class="tdTitle">证件类型<i class="required">*</i></td>
            <td class="tdCont">
                <select class="inputSel validate zjlx" name="ckCkdxMap['${ckdx.cklsh}'].zjlx">
                    <option value=""></option>
                    <c:forEach items="${zjfl}" var="zjlx">
                        <%--使用el表达式和三目运算符根据下拉框的值选中Option ${zjlx.code==ckCkdx.zjlx?'selected':''}--%>
                        <option value="${zjlx.code}" ${zjlx.code==ckdx.zjlx?'selected':''}>${zjlx.mc}</option>
                    </c:forEach>
                </select>
            </td>
            <td class="tdTitle">证件号码<i class="required">*</i></td>
            <td class="tdCont" colspan="3"><input id="zjhm" name="ckCkdxMap['${ckdx.cklsh}'].zjhm"
                                                  class="inputText validate" type="text"
                                                  value="${ckdx.zjhm}"/></td>
        </tr>
        <tr>
            <td class="tdTitle">国家或地区</td>
            <td class="tdCont">
                <select class="inputSel gj" name="ckCkdxMap['${ckdx.cklsh}'].gj">
                    <option value=""></option>
                    <c:forEach items="${gj}" var="gjdq">
                        <option value="${gjdq.code}" ${gjdq.code==ckdx.gj?'selected':''}>${gjdq.mc}</option>
                    </c:forEach>
                </select>
            </td>
            <td class="tdTitle">户籍地<i class="required">*</i></td>
            <td class="tdCont" colspan="3"><input id="hjszd" name="ckCkdxMap['${ckdx.cklsh}'].hjszd"
                                                  class="inputText validate" type="text"
                                                  value="${ckdx.hjszd}"/></td>
        </tr>
    </table>
</c:forEach>


