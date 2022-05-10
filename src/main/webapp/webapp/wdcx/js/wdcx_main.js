let pageSize = 10;
let pageNum = 1;
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


function queryInfo() {
    let zt = $('#zt').val();
    let ah = $('#ah').val();
    let mc = $('#mc').val();
    let zjhm = $('#zjhm').val();

    console.info(zt);
    mygrid.loadXML(CONTEXT_PATH + "webapp/wdcx/listwdcx.do?zt="+zt+"&ah="+ah+"&mc="+mc+"&zjhm="+zjhm);
}
