let countCkdx = 0;
let fileIndex = 0;
let delFileXh = "";


$(
    function () {
        let func = $("#func").val();

        if (func === "view") {
            doView("view");
        } else if (func === "edit") {
            doEdit();
            uploadFile();
        } else if (func === "add") {
            getCkdxTab("");

            $("#addCxdx").click(function () {
                getCkdxTab("");
            });

            uploadFile();
            cxsqdjSave();
        }
    }
)

//查看信息
function doView(type) {
    let djpc = $("#djpc").val();
    getCkdxTab(djpc);

    let xzdwdms = $("#xzdwdms").val();
    xzdwdms = xzdwdms.substr(1, xzdwdms.length - 2).split(", ");

    for (let i in xzdwdms) {
        setCheckVal("#xzdwdm_" + xzdwdms[i], true);
    }

    let ckjzs = $("#ckjzs").val();
    ckjzs = JSON.parse(ckjzs);

    for (let index in ckjzs) {
        let path = ckjzs[index].path;
        let fileName = ckjzs[index].wjmc + "." + ckjzs[index].wjlx;
        let viewFileName = '';

        if (fileName.length > 30) {
            viewFileName = fileName.substr(0, 30) + "...";
        } else {
            viewFileName = fileName;
        }

        let fileInfo = '';
        //用卷宗的序号去当id
        let jzId = "xh_" + ckjzs[index].xh;
        fileInfo += '<li id="' + jzId + '">' +
            '<label title="' + fileName + '"><input class="filechkbox inputCheck viewchkbox" type="checkbox" value="' + jzId + '">' + viewFileName + '</label>' +
            '<a class="tdh_icon icon_download form_upload_close" onclick="downloadFile(\'' + path + '\')"></a>' +
            '</li>';
        $('#fileList').append(fileInfo);
        checkboxInit("#fileList .filechkbox");
    }

    isChanged();

    if (type === "view") {
        editDisable();
    }
}

//设置页面不可编辑
function editDisable() {
    inputDisable("input", true);
    selDisable("select", true);
    inputDisable("textarea", true);
    checkDisable("type=['checkbox']", true);
    $(".tdh_btn").hide();
}


//编辑信息
function doEdit() {
    doView();

    $("#addCxdx").click(function () {
        getCkdxTab("");
    })
    cxsqdjUpdate();
}


//编辑操作
function cxsqdjUpdate() {
    $("#cxsqdjSave").click(function () {
        let valid = validateForm();
        if (valid === false) {
            layer.alert("请检查必填项！", {
                icon: 7,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            });
            return valid;
        }

        let params = serialize("#cxsq");

        let chkStr = "";
        $(".xzdwmc:checked").each(function () {
            chkStr += $(this).val() + ",";
        })


        $.ajax({
            url: CONTEXT_PATH + "webapp/wdcx/updateCxsqdj.do",
            type: "post",
            dataType: "json",
            data: params + "&xzdwdm=" + chkStr + "&delFileXh=" + delFileXh,
            success: function (data) {
                if (data.code === 0) {
                    layer.msg("编辑成功！", {
                        icon: 1,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerReturn("success");
                    });
                } else {
                    layer.msg("编辑失败！", {
                        icon: 0,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerClose(true);
                    });
                }
            },
            error: function () {
                layer.msg("新增对象页面请求出现错误，请联系管理员！", {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    layerClose(true);
                });
            }
        })
    });
}


//查控申请登记的保存
function cxsqdjSave() {
    $("#cxsqdjSave").click(function () {
        let valid = validateForm();
        if (!valid) {
            layer.alert("请检查必填项！", {
                icon: 7,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            });
            return valid;
        }

        let params = serialize("#cxsq");
        let chkStr = "";
        $(".xzdwmc:checked").each(function () {
            chkStr += $(this).val() + ",";
        })

        $.ajax({
            url: CONTEXT_PATH + "webapp/wdcx/saveCksq.do",
            type: "post",
            dataType: "json",
            data: params + "&xzdwdm=" + chkStr,
            success: function (data) {
                if (data.code === 0) {
                    layer.msg("保存成功！", {
                        icon: 1,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerReturn("success");
                    });
                } else {
                    layer.msg("保存失败！", {
                        icon: 0,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerClose(true);
                    });
                }
            },
            error: function () {
                layer.msg("新增对象页面请求出现错误，请联系管理员！", {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    layerClose(true);
                });
            }
        })
    });
}


//获取查控对象表格
function getCkdxTab(djpc) {
    $.ajax({
        url: CONTEXT_PATH + "webapp/wdcx/getCxdxTab.do?djpc=" + djpc,
        type: "post",
        async: false,
        dataType: "html",
        success: function (data) {
            $("#cxdxTab").append(data);
            selectInit("#cxdxTab .inputSel");
            checkboxInit("#cxdxTab .inputCheck");
            countCkdx += $("#countCkdx").val();
        },
        error: function () {
            layer.msg("新增对象页面请求出现错误，请联系管理员！", {
                icon: 0,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            }, function () {
                layerClose(true);
            });
        }
    });
}


//删除查控对象表格
function delCxdx() {
    let count = $(".tableInpChk:checked").size();

    if ((countCkdx - count) < 1) {
        layer.msg("至少要保留一个查控对象", {
            icon: 7,
            shade: 0.000001, //不展示遮罩，但是要有遮罩效果
            time: 2000
        });
        return false;
    } else {
        $(".tableInpChk:checked").each(function () {
            let tableID = $(this).val();
            $("#" + tableID).remove();
            countCkdx -= count;
            count = 0;
        })
    }
}


//验证表单必填项
function validateForm() {
    var zt = true;
    $(".validate").each(function (m, n) {
        var tempVal = $(this).val();
        if (!tempVal) {
            $(this).parent().css("background", "gray");
            zt = false;
        }
    })
    return zt;
}


//复选框全选和取消全选
function selectAllXzdw() {
    if ($("#selAllChk").prop("checked")) {
        setCheckVal(".xzdw", true);
        setCheckVal(".xzdwmc", true);
    } else {
        setCheckVal(".xzdw", false);
        setCheckVal(".xzdwmc", false);
    }
}


//如果协执单位复选框全部被手动选中，所有单位的复选框会自动勾选
function allchk() {
    let allChecked = true;

    //遍历所有的复选框，如果没有选中的，则直接设置false
    $(".xz").each(function () {
        if (!$(this).prop("checked")) {
            allChecked = false;
            return false;
        }
    })

    if (allChecked) {
        setCheckVal("#selAllChk", true);
    } else {
        setCheckVal("#selAllChk", false);
    }
}


//协执单位分类时的全选
function chkXzdw(obj, index) {
    if (obj.checked) {
        setCheckVal(".xzdw_" + index, true);
    } else {
        setCheckVal(".xzdw_" + index, false);
    }
    allchk();
}


//分类中的协执单位在全选了之后，小类别的那个复选框会自动勾选
function allXzdwchk(index) {
    var chknum = $(".xzdw_" + index).size();//选项总个数
    var chk = 0;
    $(".xzdw_" + index).each(function () {
        if ($(this).attr("checked")) {
            chk++;
        }
    });
    if (chknum == chk) {//全选
        setCheckVal("#xzdw_" + index, true);
    } else {//不全选
        setCheckVal("#xzdw_" + index, false);
    }
    allchk();
}


//在编辑和查看的时候需要模拟出发一次change事件
function isChanged() {
    $(".xzdwmc").each(function () {
        if ($(this).prop("checked")) {
            $(this).trigger("change");
        }
    });
}


//上传文件
function uploadFile() {
    let i = 0;
    let uploader = new plupload.Uploader({
        browse_button: "fileUpload",
        url: CONTEXT_PATH + "webapp/wdcx/upload.do",
        //最大文件限制
        max_file_size: "10mb",
        //一次上传数据大小
        chunk_size: "10mb",
        unique_names: true,    //是否自动生成唯一名称
        flash_swf_url: CONTEXT_PATH + "resources/static/js/Moxie.swf",
        silverlight_xap_url: CONTEXT_PATH + "resources/static/js/Moxie.xap",
        filters: [                //文件类型限制
            {title: "上传文件", extensions: "pdf,doc,docx"},
        ],

        init: {
            FilesAdded: function (up, files) {
                uploader.start();
            },

            Error: function (up, err) {
                layer.msg("\nError #" + err.code + ": " + err.message, {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                });
            },

            FileUploaded: function (uploader, file, result) {
                let res = JSON.parse(result.response);
                let fileInfo = JSON.parse(res.data)[0];

                let fileName = file.name;
                if (fileName.length > 30) {
                    fileName = fileName.substr(0, 30) + "...";
                }

                let jzID = fileInfo.tempUuid;

                let fileData = '';
                fileData += '<li id="' + jzID + '">' +
                    '<label title="' + fileName + '"><input class="filechkbox inputCheck" type="checkbox" value="' + jzID + '" >' + fileName + '</label>' +
                    '<input type="hidden" name="files[' + fileIndex + '].wjmc" value="' + fileInfo.wjmc + '"/>' +
                    '<input type="hidden" name="files[' + fileIndex + '].djpc" value="-1"/>' +
                    '<input type="hidden" name="files[' + fileIndex + '].wjlx" value="' + fileInfo.wjlx + '"/>' +
                    '<input type="hidden" name="files[' + fileIndex + '].path" value="' + fileInfo.path + '"/>' +
                    '<input type="hidden" name="files[' + fileIndex + '].xh" value="-1"/>' +
                    '</li>';
                fileIndex++;
                $('#fileList').append(fileData);
                checkboxInit("#fileList .filechkbox");
            }
        }
    });

    uploader.init();
}

//删除文件，但是没有删除上传到临时文件中的文件信息
function fileDel() {
    let jzId = "";
    $('.fileChkbox:checked').each(function () {
        jzId = $(this).val();

        let pattern = /^xh_[0-9]{1,}$/;

        if (jzId.match(pattern)) {
            delFileXh += jzId + ",";
        }

        $("#" + jzId).remove();
    });
}

//下载文件
function downloadFile(path) {
    window.location.href = CONTEXT_PATH + "webapp/wdcx/downloadFile.do?path=" + encodeURIComponent(path);
}

//发送
function send() {
    let djpc = $("#djpc").val();

    $.ajax({
        url: CONTEXT_PATH + "webapp/wdcx/send.do",
        type: "post",
        dataType: "json",
        data: {djpc: djpc},
        success: function (data) {
            if (data.code === 0) {
                layer.msg("发送成功！", {
                    icon: 1,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    layerReturn("success");
                });
            } else {
                layer.msg("发送失败！", {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    layerClose(true);
                });
            }
        },
        error: function () {
            layer.msg("新增对象页面请求出现错误，请联系管理员！", {
                icon: 0,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            }, function () {
                layerClose(true);
            });
        }
    })

    editDisable();
}