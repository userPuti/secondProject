let countCkdx = 0;

$(
    function () {
        let func = $('#func').val();

        addCxdx();
        selectAllXzdw();

        if (func === 'view') {
            doView();
        } else if (func === 'edit') {
            doEdit();
        }
        if (func === 'add') {
            cxsqdjSave();
        }
    }
)

//查看信息
function doView() {
    let hSasf = $('#hSasf').val();
    let hZjlx = $('#hZjlx').val();
    let hGj = $('#hGj').val();
    setSelVal('#sasf', hSasf);
    setSelVal('#zjlx', hZjlx);
    setSelVal('#gj', hGj);

    inputDisable('#ah', true);
    inputDisable('#cbr', true);
    inputDisable('#sjy', true);
    inputDisable('#mc', true);
    selDisable('#sasf', true);
    selDisable('#zjlx', true);
    inputDisable('#zjhm', true);
    selDisable('#gj', true);
    inputDisable('#hjszd', true);
}


//编辑信息
function doEdit() {
    let hSasf = $('#hSasf').val();
    let hZjlx = $('#hZjlx').val();
    let hGj = $('#hGj').val();
    setSelVal('#sasf', hSasf);
    setSelVal('#zjlx', hZjlx);
    setSelVal('#gj', hGj);

    inputDisable('#ah', true);
}

//查控申请登记的保存
function cxsqdjSave() {
    $('#cxsqdjSave').click(function () {
        let valid = validateForm();
        if (valid === false) {
            layer.alert("请检查必填项！", {
                icon: 7,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            });
            return valid;
        }
    })
}

function addCxdx() {
    $.ajax({
        url: CONTEXT_PATH + "webapp/wdcx/getCxdxTab.do",
        type: "post",
        dataType: "html",
        success: function (data) {
            $('#cxdxTab').append(data);
            selectInit('#cxdxTab .inputSel');
            checkboxInit('#cxdxTab .inputCheck');
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
    let count = $('.tableInpChk:checked').size();

    if ((countCkdx - count) < 1) {
        layer.msg("至少要保留一个查控对象", {
            icon: 7,
            shade: 0.000001, //不展示遮罩，但是要有遮罩效果
            time: 2000
        });
        return false;
    } else {
        $('.tableInpChk:checked').each(function () {
            let tableID = $(this).val();
            $('#' + tableID).remove();
            countCkdx -= count;
            count = 0;
        })
    }

}

function validateForm() {
    var zt = true;
    $(".validate").each(function (m, n) {
        var tempVal = $(this).val();
        if (!tempVal) {
            $(this).parent().css("background", "red");
            zt = false;
        }
    })
    return zt;
}


//复选框全选和取消全选
function selectAllXzdw() {
    $('#selAll').on('change', function () {
            if (this.checked) {
                setCheckVal(".xzdwCheck", true);
            } else {
                setCheckVal(".xzdwCheck", false);
            }
        }
    );
}


