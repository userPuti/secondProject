let countCkdx = 0;

$(
    function () {
        let func = $("#func").val();
        selectAllXzdw();

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

            cxsqdjSave();
            uploadFile();
        }
    }
)

//查看信息
function doView(type) {
    let djpc = $("#djpc").val();
    console.info("djpc", djpc);
    getCkdxTab(djpc);

    let xzdwdms = $("#xzdwdms").val();
    console.info(xzdwdms);
    xzdwdms = xzdwdms.substr(1, xzdwdms.length - 2).split(", ");
    console.info("xzdwdms", xzdwdms);

    for (let i in xzdwdms) {
        console.info(xzdwdms[i]);
        setCheckVal("#xzdwdm_" + xzdwdms[i], true);
    }

    let xzsm = $("#xzsm").val();
    console.info("xzsm", xzsm);
    $("#xzsmText").val(xzsm);

    let ckjzs = $("#ckjzs").val();
    console.info("ckjzs", ckjzs);
    ckjzs = JSON.parse(ckjzs);
    console.info(ckjzs);


    //todo 这里会丢失临时文件的id，保存的时候需要根据临时文件的id复制文件，所以会在更新时报错
    for (let index in ckjzs) {
        console.info(ckjzs[index].wjmc + "." + ckjzs[index].wjlx);
        let fileInfo = '';
        let jzId = ckjzs[index].djpc + "_" + ckjzs[index].xh;
        fileInfo += '<li id="' + jzId + '">' +
            '<label><input class="filechkbox inputCheck" type="checkbox" value="' + jzId + '">' + ckjzs[index].wjmc + "." + ckjzs[index].wjlx + '</label>' +
            '<a class="tdh_icon icon_download form_upload_close" onclick=""></a>' +
            '<input type="hidden" name="fileInfo[' + jzId + '].wjmc" value="' + ckjzs[index].wjmc + '"/>' +
            '<input type="hidden" name="fileInfo[' + jzId + '].wjlx" value="' + ckjzs[index].wjlx + '"/>' +
            '<input type="hidden" name="fileInfo[' + jzId + '].path" value="' + ckjzs[index].path + '"/>' +
            '</li>';
        $('#fileList').append(fileInfo);
    }
    checkboxInit("#fileList .filechkbox");

    if (type === "view") {
        editDisable();
    }
}

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
    cxsqdjUpdate();
}

//todo 编辑信息
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
            data: params + "&xzdwdm=" + chkStr,
            success: function (data) {
                if (data.code === 0) {
                    layer.msg("保存成功！", {
                        icon: 1,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerClose(true);
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


//查控申请登记的保存
function cxsqdjSave() {
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

        console.info(params);

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
                        layerClose(true);
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
        dataType: "html",
        success: function (data) {
            $("#cxdxTab").append(data);
            selectInit("#cxdxTab .inputSel");
            checkboxInit("#cxdxTab .inputCheck");
            countCkdx++;
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
    $("#selAll").on("change", function () {
            if (this.checked) {
                setCheckVal(".xzdw", true);
            } else {
                setCheckVal(".xzdw", false);
            }
        }
    );

    $(".xzdw").on("change", function () {
        allchk();
    })
}

//如果协执单位复选框全部被手动选中，所有单位的复选框会自动勾选
function allchk() {
    var chknum = $(".xzdw").size();//选项总个数
    var chk = 0;
    $(".xzdw").each(function () {
        if ($(this).attr("checked")) {
            chk++;
        }
    });
    if (chknum == chk) {//全选
        setCheckVal("#selAll", true);
    } else {//不全选
        setCheckVal("#selAll", false);
    }
}


function chkXzdw(obj, index) {
    console.info(index);
    if (obj.checked) {
        setCheckVal(".xzdw_" + index, true);
    } else {
        setCheckVal(".xzdw_" + index, false);
    }
}

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
}

let i = 0;

//上传文件
function uploadFile() {
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
                console.info(result.response);
                let res = JSON.parse(result.response);
                let fileInfo = JSON.parse(res.data)[0];
                console.info(fileInfo);
                console.info(fileInfo.wjlx);

                let fileData = '';

                let fileName = file.name;
                if (fileName.length > 10) {
                    fileName = fileName.substr(0, 10) + "...";
                }

                let jzID = fileInfo.tempUuid;
                ;

                fileData += '<li id="' + jzID + '">' +
                    '<label><input class="filechkbox inputCheck" type="checkbox" value="' + jzID + ' title="' + fileName + '">' + fileName + '</label>' +
                    '<input type="hidden" name="files[' + i + '].wjmc" value="' + fileInfo.wjmc + '"/>' +
                    '<input type="hidden" name="files[' + i + '].wjlx" value="' + fileInfo.wjlx + '"/>' +
                    '<input type="hidden" name="files[' + i + '].path" value="' + fileInfo.path + '"/>' +
                    '</li>';
                i++;
                $('#fileList').append(fileData);
                checkboxInit("#fileList .filechkbox");
            }
        }
    });

    uploader.init();
}

//删除文件，但是没有删除上传到临时文件中的文件信息
function fileDel() {
    let chkStr = "";
    $('.fileChkbox:checked').each(function () {
        chkStr += $(this).val();
        $('#' + chkStr).remove();
    })
}