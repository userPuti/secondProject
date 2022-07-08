let mygrid;

$(function () {
    //构建dgrid表格
    initGrid();
});

//初始化主页表格信息
function initGrid() {
    mygrid = new dhtmlXGridObject('grid');
    mygrid.setImagePath(CONTEXT_PATH + "/resources/static/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/imgs/");
    mygrid.setHeader("#master_checkbox,状态,查看,编辑,案号,被执行人,证件号码,协执单位,承办人,书记员");
    mygrid.setInitWidths("40,60,60,60,150,150,150,80,80,80");
    mygrid.setColTypes("ch,ro,img,img,ro,ro,ro,ro,ro,ro");
    mygrid.setColAlign("center,center,center,center,center,center,center,center,center,center");
    mygrid.setColSorting("na,na,na,na,str,str,str,str,str,str");
    mygrid.setSkin("tdh_default");
    mygrid.pagingLayout("first,prev,page,next,last,limit,refresh,skip,count");
    mygrid.pagingLimits("10,20,50");
    mygrid.enablePagingon(true, 20);
    mygrid.init();
    mygrid.loadXML(CONTEXT_PATH + "webapp/wdcx/listwdcx.do");
}


//查询
function queryInfo() {
    let zt = $('#zt').val();
    if (zt === null) {
        zt = '';
    }
    let ah = $('#ah').val();
    let mc = $('#mc').val();
    let zjhm = $('#zjhm').val();

    mygrid.loadXML(CONTEXT_PATH + "webapp/wdcx/listwdcx.do?zt=" + zt + "&ah=" + ah + "&mc=" + mc + "&zjhm=" + zjhm);
}

//删除
function batchDel() {
    let gridlist = mygrid.getCheckedRows(0);

    if (gridlist === null || gridlist === '') {
        layer.msg('未选择登记状态的记录，无法删除', {
            icon: 7,
            shade: 0.000001, //不展示遮罩，但是要有遮罩效果
            time: 2000
        })
    }

    let bdhms = gridlist.split(",");
    let delZt = [];
    let count = 0;

    for (let bdhm of bdhms) {
        let ztTemp = mygrid.getUserData(bdhm, "zt");

        if ('10' === ztTemp) {
            delZt.push(bdhm);
            count++;
        }
    }

    delZt = delZt.join(',');

    if (0 === count) {
        layer.msg('未选择登记状态的记录，无法删除', {
            icon: 7,
            shade: 0.000001, //不展示遮罩，但是要有遮罩效果
            time: 2000
        })
    } else if (bdhms.length !== count) {
        layer.confirm('存在非登记状态的记录，只会删除登记状态的记录，是否继续!', {
            title: '删除提示',
            btn: ['是', '否'],
            btn1: function () {
                deleted(delZt);
            },
            btn2: function () {
                layerClose(true);
            }
        });
    } else if (bdhms.length === count) {
        layer.confirm('是否删除选中的记录!', {
            title: '删除提示',
            btn: ['是', '否'],
            btn1: function () {
                deleted(delZt);
            },
            btn2: function () {
                layerClose(true);
            }
        });
    }
}

//删除
function deleted(bdhms) {
    let index = layer.msg('正在删除中...请稍等', {icon: 16, shade: 0.4, time: false});
    $.ajax({
        url: CONTEXT_PATH + "webapp/wdcx/batchDel.do",
        data: {bdhms: bdhms},
        dataType: "JSON",
        success: function (data) {
            layer.close(index);
            if (data.code === 0) {
                layer.msg(data.data, {
                    icon: 1,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    mygrid.loadPage(true);
                })
            } else if (data.code === 1) {
                layer.msg("删除失败，请联系管理员！", {
                    icon: 2,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                })
            }
        },
        error: function () {
            layer.close(index);
            layer.msg("请求出现了错误，请联系管理员解决！", {
                icon: 2,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            })
        }
    })
}

//新增
function addInfo() {
    openLayerModal(CONTEXT_PATH + "webapp/wdcx/cxsfdj.do?func=add", "查询申请登记", 700, 600, "addInfoCallback");
}

//查询申请登记的回调函数，用于刷新
function addInfoCallback(rtn) {
    console.info("查询");
    if (rtn === "success") {
        return mygrid.loadXML(CONTEXT_PATH + "webapp/wdcx/listwdcx.do");
    }
}

//查看
function view(djpc) {
    openLayerModal(CONTEXT_PATH + "webapp/wdcx/viewSqInfo.do?djpc="+djpc+"&func=view", "查看协执信息", 700, 600);
}

//编辑
function edit(djpc) {
    openLayerModal(CONTEXT_PATH + "webapp/wdcx/viewSqInfo.do?djpc="+djpc+"&func=edit", "编辑协执信息", 700, 600);
}