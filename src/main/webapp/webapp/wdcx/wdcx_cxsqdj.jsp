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
        <jsp:param name="UIS" value="FORM_LAYER_BTN_LAYOUT_JSON2"/>
    </jsp:include>
    <script src="${pageContext.request.contextPath}/webapp/wdcx/js/wdcx_cxsqdj.js"
            type="application/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.serialize.js"
            type="text/javascript"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/resources/static/js/plupload.full.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/resources/static/js/jquery.plupload.queue/jquery.plupload.queue.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webapp/wdcx/css/list.css"/>
</head>
<body style="padding: 0 10px;box-sizing: border-box; overflow-x: hidden;overflow-y: auto">

<div class="tdh_form_search">
    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="cxsqdjSave">
        <i class="tdh_icon icon_save"></i>保存
    </a>

    <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="cxsqdjSend" onclick="send()">
        <i class="tdh_icon icon_send"></i>发送
    </a>
</div>

<div class="tdh_form_title">司法查询登记表</div>
<div>
    <form id="cxsq" action="#">
        <div style="width: 100%">
            <table class="tdh_form">
                <colgroup>
                    <col width="5%"/>
                </colgroup>
                <tr>
                    <td align="center" class="tdhCont">查<br/>询<br/>对<br/>象<br/></td>
                    <td id="cxdxTab">
                    </td>
                </tr>
                <tr>
                    <td colspan="7" align="right">
                        <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="addCxdx">
                            <i class="tdh_icon icon_add"></i>新增
                        </a>
                        <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="delCxdx"
                           onclick="delCxdx()">
                            <i class="tdh_icon icon_del"></i>删除
                        </a>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height: 120px;width: 100%">
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
                        <label><input id="selAllChk" class="inputCheck" type="checkbox" onchange="selectAllXzdw()"/>所有单位(选择下列单位发起财产查询通知,
                            至少选择一个协助执行单位)</label>
                    </td>
                </tr>
                <c:forEach items="${ckxzdwMap}" var="xzdws" varStatus="idxStatus">
                    <tr>
                        <td class="tdCont">
                            <label><input class="inputCheck xzdw xz" type="checkbox" id="xzdw_${idxStatus.index}"
                                          onchange="chkXzdw(this,${idxStatus.index})"
                                          value="xzdw_${idxStatus.index}"/>${xzdws.key}</label>
                        </td>
                        <td class="tdCont">
                            <c:forEach items="${xzdws.value}" var="xzdw">
                                <div style="width: 100px;padding: 0px 5px 0px 5px; float: left">
                                    <label><input
                                            id="xzdwdm_${xzdw.xzdwdm}"
                                            class="inputCheck xzdw_${idxStatus.index} xzdwmc xz"
                                            type="checkbox" value="${xzdw.xzdwdm}"
                                            onchange="allXzdwchk(${idxStatus.index})"/>${xzdw.mc}</label>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div style="height: 120px;width: 100%">
            <table class="tdh_form">
                <colgroup>
                    <col width="5%"/>
                </colgroup>

                <tr rowspan="4">
                    <td align="center">协<br/>执<br/>说<br/>明<br/></td>
                    <td class="tdCont_pd">
                        <textarea id="xzsmText" class="inputArea" name="xzsm" maxlength="255" style="resize:none;" >${xzsm}</textarea>
                    </td>
                </tr>
            </table>
        </div>

        <div style="height: 300px;width: 100%">
            <table class="tdh_form" style="height: 300px;">
                <colgroup>
                    <col width="5%"/>
                    <col width="60%"/>
                </colgroup>

                <tr>
                    <td align="center">文<br/>书<br/>信<br/>息<br/></td>
                    <td class="tdCont_pd" style="border-right: 0px">
                        <div class="form_upload_list">
                            <ul id="fileList">
                            </ul>
                        </div>
                    </td>
                    <td class="tdCont_pd" style="border-left: 0px">
                        <div align="right">
                            <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" id="fileUpload">
                                <i class="tdh_icon icon_upload"></i>上传
                            </a>
                            <a class="tdh_btn tdh_btn_white" href="javascript:void(0);" onclick="fileDel()">
                                <i class="tdh_icon icon_del"></i>删除
                            </a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <input id="djpc" name="djpc" type="hidden" value="${djpc}"/>
    </form>
</div>
<input id="func" type="hidden" value="${func}"/>
<input id="xzdwdms" type="hidden" value="${xzdwdms}"/>
<input id="ckjzs" type="hidden" value="${ckjzs}"/>
</body>
</html>
