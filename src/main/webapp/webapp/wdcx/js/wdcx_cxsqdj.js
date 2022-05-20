$(
    function () {
        let func = $('#func').val();

        addCxdx();
        loadCheckBox();
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

            let sasfOption = $('#sasfOption').val();
            sasfOption = JSON.parse(sasfOption);
            $.each(sasfOption, function (index, item) {
                $('.sasf').append(new Option(item.mc, item.code));
            });

            let zjlxOption = $('#zjlxOption').val();
            zjlxOption = JSON.parse(zjlxOption);
            $.each(zjlxOption, function (index, item) {
                $('.zjlx').append(new Option(item.mc, item.code));
            });

            let gjOption = $('#gjOption').val();
            gjOption = JSON.parse(gjOption);
            $.each(gjOption, function (index, item) {
                $('.gj').append(new Option(item.mc, item.code));
            });

            selectInit('.inputSel');
            checkboxInit(':checkbox');
        },
        error: function () {
            layer.msg("请求出现错误，请联系管理员！", {
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

function loadCheckBox() {
    let ckxzdwMap = $('#ckxzdwMap').val();
    ckxzdwMap = JSON.parse(ckxzdwMap);
    let ckxzdwHtml = '';

    $.each(ckxzdwMap, function (key, value) {
        ckxzdwHtml += '<tr><td><label><input type="checkbox" class="inputCheck">' + key + '</input></label></td><td>'
        $.each(value, function (index, ckxzdw) {
            ckxzdwHtml += '<label><input type="checkbox" class="inputCheck">' + ckxzdw.mc + '</input></label>'
        })
        ckxzdwHtml += '</td></tr>';
    });
    $('#ckxzdwMap').replaceWith(ckxzdwHtml);

    checkboxInit($('.inputCheck'));
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


