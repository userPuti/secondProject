let countCkdx = 0;

$(
    function () {
        let func = $("#func").val();
        addCxdx();
        selectAllXzdw();

        if (func === "view") {
            doView();
        } else if (func === "edit") {
            doEdit();
            uploadFile();
        }
        if (func === "add") {
            cxsqdjSave();
            uploadFile();
        }
    }
)

//查看信息
function doView() {
    let hSasf = $("#hSasf").val();
    let hZjlx = $("#hZjlx").val();
    let hGj = $("#hGj").val();
    setSelVal("#sasf", hSasf);
    setSelVal("#zjlx", hZjlx);
    setSelVal("#gj", hGj);

    inputDisable("#ah", true);
    inputDisable("#cbr", true);
    inputDisable("#sjy", true);
    inputDisable("#mc", true);
    selDisable("#sasf", true);
    selDisable("#zjlx", true);
    inputDisable("#zjhm", true);
    selDisable("#gj", true);
    inputDisable("#hjszd", true);
}


//编辑信息
function doEdit() {
    let hSasf = $("#hSasf").val();
    let hZjlx = $("#hZjlx").val();
    let hGj = $("#hGj").val();
    setSelVal("#sasf", hSasf);
    setSelVal("#zjlx", hZjlx);
    setSelVal("#gj", hGj);
    inputDisable("#ah", true);
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

        let params = serialize("#ckxz");
        console.info(params);


        let chkStr = "";
        $(".xzdwmc:checked").each(function () {
            chkStr += $(this).val() + ",";
        })

        console.info("chkStr", chkStr);

        $.ajax({
            url: CONTEXT_PATH + "webapp/wdcx/saveCkxz.do",
            type: "post",
            dataType: "json",
            data: params + "&xzdwdm=" + chkStr,
            success: function (data) {
                console.info("data", data);
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

function addCxdx() {
    $.ajax({
        url: CONTEXT_PATH + "webapp/wdcx/getCxdxTab.do",
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


function chkXzdw(obj,index){
    console.info(index);
    if (obj.checked) {
        setCheckVal(".xzdw_"+index, true);
    } else {
        setCheckVal(".xzdw_"+index, false);
    }
}

function allXzdwchk(index) {
    var chknum = $(".xzdw_"+index).size();//选项总个数
    console.info("chknum",chknum);
    var chk = 0;
    $(".xzdw_"+index).each(function () {
        if ($(this).attr("checked")) {
            chk++;
        }
    });
    if (chknum == chk) {//全选
        setCheckVal("#xzdw_"+index, true);
    } else {//不全选
        setCheckVal("#xzdw_"+index, false);
    }
}


//上传文件
function uploadFile() {
    let uploader = new plupload.Uploader({
        browse_button: "fileUpload",
        url: CONTEXT_PATH + "upload.do",
        //最大文件限制
        max_file_size: "10mb",
        //一次上传数据大小
        chunk_size: "10mb",
        unique_names: false,    //是否自动生成唯一名称
        flash_swf_url: CONTEXT_PATH + "resources/static/js/Moxie.swf",
        silverlight_xap_url: CONTEXT_PATH + "resources/static/js/Moxie.xap",
        filters: [                //文件类型限制
            {title: "上传文件", extensions: "pdf,doc,docx"},
        ],

        init: {
            PostInit: function () {
                $('#fileList').html('');

                $('#fileUpload').onclick = function () {
                    uploader.start();
                    return false;
                };
            },

            FilesAdded: function (up, files) {
                let fileInfo = '';
                plupload.each(files, function (file) {
                    fileInfo += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
                    $('#fileList').append(fileInfo);
                });
            },

            UploadProgress: function (up, file) {
                $('#' + file.id).find('b')[0].html('<span>' + file.percent + "%</span>");
            },

            Error: function (up, err) {
                layer.msg("\nError #" + err.code + ": " + err.message, {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                });
            }
        }
    });

    uploader.init();
}



