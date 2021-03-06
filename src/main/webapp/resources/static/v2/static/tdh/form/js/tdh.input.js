/*
 * setSelVal('#sel', json.ajly);	//下拉赋值
 * setRadioVal('#radio', true);		//单选赋值
 * setCheckVal('#check', true);		//多选赋值
 * selDisable('#saly',true);		//下拉禁用
 * checkDisable("#check",true);		//多选禁用
 * radioDisable("#radio",true);		//单选禁用
 * inputDisable("#text",true); 		//输入框禁用
 * 注：以上方法 第一个参数是jQuery对象或选择器，并非一定是id。
 *
 * 下拉表单:
 * 例：<select class="inputSel" multiple="multiple"> 多选下拉
 * 例：<select class="inputSel" data-edit="true"> 可编辑下拉
 */
var jq = jQuery;
var ir = 'inputRadio',
    ic = 'inputCheck',
    is = 'inputSel';
var r = 'radio',
    rc = 'radio_checked',
    rd = 'radio_disable',
    rcd = 'radio_checked_disable',
    c = 'checkbox',
    cc = 'checkbox_checked',
    cd = 'checkbox_disable',
    ccd = 'checkbox_checked_disable',
    w = 'select_box',
    d = 'select_showbox',
    l = 'select_option';

jq(function () {
    radioInit(jq('.inputRadio'));//单选框
    checkboxInit(jq('.inputCheck'));//复选框
    selectInit(jq('.inputSel'));//下拉框
    jq('body').click(function (e) {
        if (!jq(e.target).parents('.select_box').length) {
            jq('.select_box').removeClass('active');
            jq('.select_option').hide();
        }
    });
    //禁止删除键后退
    jq('input[readOnly]').keydown(function (e) {
        return forbidBackSpace(e);
    })
});

//处理键盘事件 禁止后退键（Backspace）
function forbidBackSpace(e) {
    var ev = e || window.event; //获取event对象
    var obj = ev.target || ev.srcElement; //获取事件源
    var t = obj.type || obj.getAttribute('type'); //获取事件源类型
    //获取作为判断条件的事件类型
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    //处理undefined值情况
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    //当敲Backspace键时
    //并且readOnly属性为true或disabled属性为true的，则退格键失效
    if (ev.keyCode == 8 && (vReadOnly == true || vDisabled == true)) {
        return false;
    }
}

function initForm(formId) {
    checkboxInit(jQuery('#' + formId + ' .inputCheck'));//复选框
    radioInit(jQuery('#' + formId + ' .inputRadio'));//单选框
    selectInit(jQuery('#' + formId + ' .inputSel'));
}

function initFormObj(obj) {
    checkboxInit(obj);//复选框
    radioInit(obj);//单选框
    selectInit(obj);
}

//单选框
function radioInit(obj) {
    var radioBoxs = ifdom(obj);
    radioBoxs.each(function () {
        var o = jq(this), name = o.attr('name'), cAttr = o.attr('checked'), dAttr = o.attr('disabled'), checked,
            disabled;
        if (cAttr) {
            checked = rc;
            jq('.' + ir + '[name="' + name + '"]').siblings('.' + r).removeClass(rc);
            jq('.' + ir + '[name="' + name + '"]').siblings('.' + ir + '[name="' + name + '"]').removeAttr('checked');
            if (dAttr) {
                disabled = rcd;
            } else {
                disabled = '';
            }
        } else {
            checked = '';
            if (dAttr) {
                disabled = rd;
            } else {
                disabled = '';
            }
        }
        var spanBox = jq('<span class="' + r + ' ' + checked + ' ' + disabled + '" name="' + name + '"></span>');
        o.next('.' + r).remove();
        o.after(spanBox);
        radioEvent(o.parent());
    });
}

function radioEvent(obj) {
    obj.click(function () {
        var spanBox = jq(this).find('.' + r), radioBox = jq(this).find('.' + ir), name = radioBox.attr('name');
        if (radioBox.attr('disabled')) {
            return;
        } else {
            if (spanBox.hasClass(rc)) {
                return;
            } else {
                jq('.' + ir + '[name="' + name + '"]').removeAttr('checked').next('.' + r).removeClass(rc);
                spanBox.addClass(rc);
                radioBox.attr('checked', 'checked');   // 单选按钮的选中  会让其他同name的checkbox取消选中。
                jq('.inputCheck[name="' + name + '"]').next('.checkbox_checked').removeClass('checkbox_checked');
            }
        }
        if (radioBox.attr("onchange")) {
            radioBox.trigger('change');
        } else {
            radioBox.trigger('click');
        }
    });
}

//复选框
function checkboxInit(obj) {
    var checkBoxs = ifdom(obj);
    checkBoxs.each(function () {
        var o = jq(this), name = jq(this).attr('name'), cAttr = o.attr('checked'), dAttr = o.attr('disabled'), checked,
            disabled;
        if (cAttr) {
            checked = cc;
            if (dAttr) {
                disabled = ccd;
            } else {
                disabled = '';
            }
        } else {
            checked = '';
            if (dAttr) {
                disabled = cd;
            } else {
                disabled = '';
            }
        }
        var spanBox = jq('<span class="' + c + ' ' + checked + ' ' + disabled + '" name="' + name + '"></span>');
        o.next('.' + c).remove();
        o.after(spanBox);
        checkBoxEvent(o.parent());
    });
}

function checkBoxEvent(obj) {
    var td = obj.closest("td");
    var datatype = td.attr("datatype");
    obj.click(function (e) {
        var that = jq(this);
        var spanBox = that.find('.' + c);
        var checkBox = that.find('.' + ic);
        if (checkBox.attr('disabled')) {
            return;
        } else {
            if (spanBox.hasClass(cc)) {
                spanBox.removeClass(cc);
                checkBox.removeAttr('checked');
                if (datatype) {
                    var i = 0;
                    jq('input[name="' + checkBox.attr('name') + '"]:checked').each(function () {
                        i++;
                    });
                    if (!i) {
                        that.closest('td').addClass("Validform_error");
                    }
                }
            } else {
                spanBox.addClass(cc);
                checkBox.attr('checked', 'checked');
                datatype && that.closest('td').removeClass("Validform_error");
            }
        }
        checkBox.trigger('change');
        return false;
    });
}

//下拉框
function selectInit(obj) {
    var selects = ifdom(obj);
    selects.each(function () {
        var $sel = jq(this),
            $selPrev = $sel.prev('.' + w),
            disabled = $sel.attr('disabled') == 'disabled' ? 'sel_disabled' : '',
            readonly = $sel.attr('disabled') == 'disabled' ? 'readonly' : '',
            edit = $sel.attr('data-edit'),
            multiple = $sel.attr('multiple') == 'multiple' ? 'sel_multiple' : '',
            $wrap = jq('<div class="' + w + ' ' + disabled + ' ' + multiple + '">' +
                '<div class="' + d + '"></div>' +
                '<ul class="' + l + '"></ul>' +
                '</div>');
        $selPrev.remove(), $sel.before($wrap);
        if ($sel[0].style.width) {
            $wrap.css('width', $sel[0].style.width);
        }
        if ($sel.attr('p-height')) {
            $wrap.find('.' + l).attr('p-height', $sel.attr('p-height'));
        }
        if (edit) {
            $wrap.find('.' + d).html('<input class="select_input" type="text" >');
        }
        var options = $sel.find('option'),
            selected_option = options.filter(':selected'),
            val = [], txt = [];
        for (var i = 0; i < selected_option.length; i++) {
            val.push(selected_option.eq(i).attr('value'));
            txt.push(selected_option.eq(i).text());
        }
        if (val.join(',') == '') {
            if (edit) {
                $wrap.find('.' + d).children().addClass('sel_tip').val('');
                if (txt.join(',') != '') {
                    $wrap.find('.' + d).children().attr('placeholder', txt.join(','));
                }
            } else {
                $wrap.find('.' + d).addClass('sel_tip').text(txt.join(','));
            }
        } else {
            if (edit) {
                $wrap.find('.' + d).children().val(txt.join(','));
            } else {
                $wrap.find('.' + d).text(txt.join(','));
            }
        }
        if ($sel.hasClass("noborder")) {
            jq('.' + w).css('border', '0');
            jq('.' + d).css('border', '0');
        }
        selectEvent($sel);
    });
}

function selectEvent($sel) {
    var $wrap = $sel.prev(),
        $div = $wrap.find('.' + d),
        $input = $div.find('input'),
        $list = $wrap.find('.' + l),
        mul = $sel.attr('multiple');
    $div.click(function () {
        if ($sel.attr('disabled')) {
            return;
        } else {
            if ($wrap.hasClass('active')) {
                hideList($wrap, $list);
                //$input.blur();
            } else {
                if (!$list.children().length) {
                    createOptions($sel);
                    $list = $sel.prev().find('.' + l);
                }
                $list.children().removeClass('sel_hide');
                showList($wrap, $div, $list);
            }
        }
    });
    $div.children().on('focus', function () {
        jq(this).removeClass('sel_tip');
    }).on('keyup', function (e) {
        if (jq(this).attr('readonly')) {
            return false;
        }
        if (e.keyCode) {
            var hiddens = 0,
                inputVal = jq(this).val(),
                search = inputVal.toLowerCase();
            search = jq.trim(search);
            if (mul) {
                var checkedVal = '';
                if (search.indexOf(',') > 0) {
                    searchVal = inputVal.substring(inputVal.lastIndexOf(',') + 1);
                    search = searchVal.toLowerCase();
                    checkedVal = inputVal.substring(0, inputVal.lastIndexOf(','));
                }
                search = jq.trim(search);
                if (!search) {
                    if (!$sel.find('option[value=""]').length) {
                        $sel.append(jq('<option value=""></option>'));
                    }
                    if (checkedVal != '') {
                        var vals = checkedVal.split(','), trueVals = [], selectedIndex;
                        for (var len = 0; len < vals.length; len++) {
                            if (vals[len]) {
                                $list.find('li').each(function () {
                                    if (jq(this).text() == vals[len] && jq(this).hasClass('selected')) {
                                        trueVals.push(vals[len]);
                                    }
                                });
                            }
                        }
                        jq(this).val(trueVals.join(',') + ',');
                    }
                }
                $list.find('li.sel_visible').removeClass('sel_hide');
                var addli = jq('<li class="sel_none">无匹配项</li>');
                hiddens = $list.find('li.sel_visible').filter(function (i, li) {
                    return jQuery(li).text().toLowerCase().indexOf(search) < 0;
                }).addClass('sel_hide').length;
                if ($list.find('li.sel_visible').length == hiddens) {
                    if (!$list.find('.sel_none').length) {
                        $list.append(addli);
                    }
                } else {
                    $list.show();
                    jq('.sel_none').remove();
                }
                setListHeight($div, $list);
            } else {
                search = jq.trim(search);
                if (!search) {
                    if (!$sel.find('option[value=""]').length) {
                        $sel.append(jq('<option value=""></option>'));
                    }
                }
                $list.find('li.sel_visible').removeClass('sel_hide');
                var addli = jq('<li class="sel_none">无匹配项</li>');
                hiddens = $list.find('li.sel_visible').filter(function (i, li) {
                    return jQuery(li).text().toLowerCase().indexOf(search) < 0;
                }).addClass('sel_hide').length;
                if ($list.find('li.sel_visible').length == hiddens) {
                    if (!$list.find('.sel_none').length) {
                        if ($sel.attr('data-diy') != 'true') {
                            $list.append(addli);
                        } else {
                            $list.find('li').removeClass('selected');
                            $list.hide();
                        }
                    }
                } else {
                    $list.show();
                    jq('.sel_none').remove();
                }
                setListHeight($div, $list);
            }
        }
    }).on('blur', function () {
        if (jq(this).attr('readonly')) {
            return false;
        }
        if ($sel.attr('data-diy') != 'true') {
            if (!$sel.attr('multiple')) {
                $list.hide();
                $list.find('li.sel_visible').removeClass('sel_hide');
                jq('.sel_none').remove();
            }
            var vals = [], txts = [];
            $sel.find('option:selected').each(function () {
                vals.push(jq(this).attr('value'));
                txts.push(jq(this).text());
            });
            if (vals == '') {
                jq(this).addClass('sel_tip').val('').attr('placeholder', txts.join(','));
            } else {
                jq(this).removeClass('sel_tip').val(txts.join(','));
            }
        } else {
            var inputVal = jq(this).val();
            var l = 0;
            $sel.find('option').each(function () {
                if (jq(this).text() == inputVal) {
                    l += 1;
                }
            });
            if (l > 0) {
                $sel.find('.addOption').remove();
                val = jq(this).attr('value');
                $sel.val(val);
                $list.find('li[data-value="' + val + '"]').addClass('selected');
            } else {
                if (!$sel.find('.addOption').length) {
                    $sel.append(jq('<option class="addOption"></option>'));
                }
                $sel.find('.addOption').attr('value', inputVal).text(inputVal);
                $sel.val(inputVal);
            }
        }
        $sel.trigger('change');
    });
}

function createOptions($sel) {
    var $wrap = $sel.prev(),
        options = $sel.find('option').not('.addOption'),
        multiple = $sel.attr('multiple') == 'multiple' ? 'sel_multiple' : '',
        $list = $sel.prev().find('.' + l);
    var liHtml = '';
    for (var n = 0; n < options.length; n++) {
        var selected = options.eq(n).is(':selected') ? 'selected' : '',
            visible = options.eq(n).attr('disabled') ? '' : 'sel_visible',
            tip = options.eq(n).attr('value') == '' ? 'sel_tip' : '';
        liHtml += '<li class="' + visible + ' ' + tip + ' ' + selected + '" data-value="' + options.eq(n).attr("value") + '">' + options.eq(n).text() + '</li>';
    }
    $list.html(liHtml);
    var $li = $list.children();
    $li.on('mousedown', function (event) {
        event.preventDefault();
        if (jq(this).hasClass('sel_visible')) {
            if ($sel.attr('multiple')) {
                jq(this).toggleClass('selected');
                var val = [],
                    lied = $list.find('.selected');
                for (var i = 0; i < lied.length; i++) {
                    val.push(lied.eq(i).attr('data-value'));
                }
                setSelVal($sel, val.join(','));
            } else {
                var val = jq(this).attr('data-value');
                setSelVal($sel, val);
                hideList($wrap, $list);
            }
        }
    });
    $li.on('mouseover', function (e) {
        jq(this).addClass('hover');
        var txtW = jq(this).text().length * parseInt(jq(this).css('font-size'));
        if (txtW > jq(this).width()) {
            var xx = e.pageX + 20;
            var yy = e.pageY + 5;
            jq('body').append('<span class="tips" style="font-size:12px;padding:0 5px; border:1px solid #dee5ef; background-color: #f7faff; position:absolute; top:' + yy + 'px; left:' + xx + 'px; z-index:99999999;">' + jq(this).text() + '</span>');
        }
    }).on('mouseout', function () {
        jq(this).removeClass('hover');
        jq('.tips').remove();
    });
}

function showList($wrap, $div, $list) {
    jq('.select_box').css('z-index', '1').removeClass('active');
    jq('.select_box').parent().css('z-index', '1');
    jq('.select_option').hide();
    if ($list.find('li.sel_visible').length > 0) {
        $wrap.css('z-index', '999').addClass('active');
        $wrap.parent().css('z-index', '999');
        $list.show();
        setListHeight($div, $list);
    }
}

function hideList($wrap, $list) {
    $wrap.css('z-index', '1').removeClass('active');
    $list.hide();
}

function setListHeight($div, $list) {
    var optionHeight = $list.find('li.sel_visible').height(),
        optionLength = $list.find('li.sel_visible').not('.sel_hide').length,
        listHeight = optionHeight * optionLength,
        maxSize = $div.parent().siblings('select').attr('data-size');
    var maxHeight;
    if (!maxSize) {
        maxHeight = 200;
    } else {
        maxHeight = maxSize * optionHeight;
    }
    if (listHeight > maxHeight) {
        $list.css('height', maxHeight + 'px');
    } else {
        $list.css('height', 'auto');
    }
    var eventType = 'mousewheel';
    if (document.mozHidden !== undefined) {
        eventType = 'DOMMouseScroll';
    }
    $list.on(eventType, function (event) {
        var scrollTop = this.scrollTop,
            scrollHeight = this.scrollHeight,
            height = this.clientHeight;

        var delta = (event.originalEvent.wheelDelta) ? event.originalEvent.wheelDelta : -(event.originalEvent.detail || 0);

        if ((delta > 0 && scrollTop <= delta) || (delta < 0 && scrollHeight - height - scrollTop <= -1 * delta)) {
            this.scrollTop = delta > 0 ? 0 : scrollHeight;
            event.preventDefault();
            return false;
        }
    });
    var parentHeight = $list.attr('p-height');
    if (!parentHeight) {
        parentHeight = jq(window).height();
        if (!parentHeight) {
            parentHeight = jq(document).height();
        }
    }
    var topHeight = $div.offset().top - jq(window).scrollTop() + $list.height() + $div.outerHeight();   //计算出 list 的底部离window顶部高度
    var botHeight = ($div.offset().top - jq(window).scrollTop()) - $list.height();          //计算下拉框顶部离window顶部距离 与 list之差
    var pDiv = $list.parent().next().attr('data-p');
    if (pDiv) {
        topHeight = $div.offset().top - jq(pDiv).offset().top + $list.height() + $div.outerHeight();
        botHeight = ($div.offset().top - jq(pDiv).offset().top) - $list.height();
    }
    if (topHeight > parentHeight) {    //高度超出父元素总高度（window） 则显示在上方
        if (botHeight > 0) {
            $list.css('top', -$list.height());
            //$list.css('top',-$list.height()-11);  // 此行替换上行 可调整下拉选择框的位置是紧挨文字or紧挨边框
        } else {
            //上方高度不够 需要重新计算list高度
            if ((parentHeight - topHeight) >= botHeight) {   //下方高度比上方大 则显示在下方
                $list.css('height', $list.height() + (parentHeight - topHeight));   //计算差值 调低list高度
                $list.css('top', $div.outerHeight());
                //$list.css('top',$div.outerHeight()+9);
            } else {  //上方高度比下方大
                $list.css('height', $list.height() + botHeight);    //计算差值 调低list高度
                $list.css('top', -$list.height());
                //$list.css('top',-$list.height()-11);
            }
        }
    } else {
        $list.css('top', $div.outerHeight());
    }
}

//判断dom是元素还是对象
function ifdom(obj) {
    var domObj;
    if (typeof (obj) != "object") {
        domObj = jq(obj);
    } else {
        domObj = obj;
    }
    return domObj;
}

//动态设置单选框选中
function setRadioVal(obj, value) {
    var radioBox = ifdom(obj),
        spanBox = radioBox.next(),
        name = radioBox.attr('name'),
        otherRadio = jq('input[type="radio"][name="' + name + '"]').not(radioBox),
        otherSpan = otherRadio.next();
    if (value == true) {
        spanBox.addClass(rc);
        otherRadio.attr('checked', false);
        otherSpan.removeClass(rc);
    } else {
        spanBox.removeClass(rc);
    }
    radioBox.attr('checked', value);
}

//单选框不可用
function radioDisable(obj, value) {
    var radioBox = ifdom(obj),
        spanBox = radioBox.next(),
        cAttr = radioBox.attr('checked');
    radioBox.attr('disabled', value);
    if (value == true) {
        if (cAttr) {
            spanBox.addClass(rcd);
        } else {
            spanBox.addClass(rd);
        }
    } else {
        if (cAttr) {
            spanBox.removeClass(rcd);
        } else {
            spanBox.removeClass(rd);
        }
    }
}

//动态设置复选框选中
function setCheckVal(obj, value) {
    var checkBox = ifdom(obj),
        spanBox = checkBox.next();
    checkBox.attr('checked', value);
    if (value == true) {
        spanBox.addClass(cc);
    } else {
        spanBox.removeClass(cc);
    }
}

//复选框不可用
function checkDisable(obj, value) {
    var checkBox = ifdom(obj),
        spanBox = checkBox.next(),
        cAttr = checkBox.attr('checked');
    checkBox.attr('disabled', value);
    if (value == true) {
        if (cAttr) {
            spanBox.addClass(ccd);
        } else {
            spanBox.addClass(cd);
        }
    } else {
        if (cAttr) {
            spanBox.removeClass(ccd);
        } else {
            spanBox.removeClass(cd);
        }
    }
}

//动态设置选中下拉框值
function setSelVal(obj, value, triggerChange) {
    var selObj = ifdom(obj);
    if (selObj.attr("datatype")) {
        if (value) {
            selObj.closest('td').removeClass("Validform_error");
        } else {
            selObj.closest('td').addClass("Validform_error");
        }
    }
    if (selObj.attr('multiple')) {
        var selDiv = selObj.prev();
        if (!value) {
            selObj.find('option').attr('selected', false);
            selDiv.find('li').removeClass('selected');
            selObj.val('');
            if (selObj.attr('data-edit')) {
                selDiv.find('.select_input').addClass('sel_tip').val('');
            } else {
                selDiv.find('.select_showbox').addClass('sel_tip').text('');
            }
        } else {
            var vals = value.split(',');
            var strs = [], selectedIndex;
            selObj.find('option').attr('selected', false);
            selDiv.find('li').removeClass('selected');
            for (var len = 0; len < vals.length; len++) {
                if (vals[len]) {
                    selObj.find('option').each(function () {
                        if (jq(this).attr('value') == vals[len]) {
                            selectedIndex = jq(this).index();
                            strs.push(jq(this).text());
                        }
                    });
                    selObj.find('option').eq(selectedIndex).attr('selected', true);
                    selDiv.find('li').eq(selectedIndex).addClass('selected');
                }
            }
            if (selObj.attr('data-edit')) {
                selDiv.find('.select_input').removeClass('sel_tip').val(strs.join(','));
            } else {
                selDiv.find('.select_showbox').removeClass('sel_tip').text(strs.join(','));
            }
        }
    } else {
        selObj.val(value);
        var selDiv = selObj.prev(),
            val = selObj.find('option:selected').val()
        txt = selObj.find('option:selected').text(),
            selectedIndex = selObj.find('option:selected').index();
        if (selObj.attr('data-edit')) {
            selDiv.find('.select_input').val(txt);
            if (!value) {
                selDiv.find('.select_input').addClass('sel_tip').val('');
                if (txt != '') {
                    selDiv.find('.select_input').attr('placeholder', txt);
                }
            } else {
                selDiv.find('.select_input').removeClass('sel_tip');
            }
        } else {
            selDiv.find('.select_showbox').text(txt);
            if (!value) {
                selDiv.find('.select_showbox').addClass('sel_tip');
            } else {
                selDiv.find('.select_showbox').removeClass('sel_tip');
            }
        }
        selDiv.find('li').removeClass('selected');
        selDiv.find('li').eq(selectedIndex).addClass('selected');
        if (selObj.attr('data-diy') && val != value) {
            selObj.find('option').attr('selected', false);
            selDiv.find('li').removeClass('selected');
            if (!selObj.find('.addOption').length) {
                selObj.append(jq('<option class="addOption"></option>'));
            }
            selObj.find('.addOption').attr('value', value).text(value);
            selObj.val(value);
            selDiv.find('.select_input').val(value);
        }
    }
    if (triggerChange != false) {
        selObj.trigger('change');
    }
}

// 设置输入框禁用(text) 需要把td加背景色
function inputDisable(obj, value) {
    var selObj = ifdom(obj);
    selObj.attr('disabled', value);
    var selDiv = selObj.parents('td:eq(0)');
    if (value == true) {
        selDiv.addClass('disablecolor');
        selObj.addClass('disablecolor');
    } else {
        selDiv.removeClass('disablecolor');
        selObj.removeClass('disablecolor');
    }
}

//下拉框不可用
function selDisable(obj, value) {
    var selObj = ifdom(obj);
    selObj.attr('disabled', value);
    var selDiv = selObj.prev();
    if (value == true) {
        selDiv.addClass('sel_disabled');
        selObj.parent().addClass('disablecolor');
        if (selObj.attr('data-edit')) {
            selDiv.find('.select_input').attr('readonly', 'readonly');
        }
    } else {
        selDiv.removeClass('sel_disabled');
        selObj.parent().removeClass('disablecolor');
        if (selObj.attr('data-edit')) {
            selDiv.find('.select_input').removeAttr('readonly');
        }
    }
}

// bzdm的多选下拉用, 返回值例：15_0000-1,2,3
//2017-08-31 废弃此方法 jq.serialize.js 中增加特殊处理
function getMulSelVal(obj) {
    var selVal = '';
    var kind = '';
    if (typeof (obj) != "object") {
        obj = jq(obj);
    }
    obj.find('option:selected').each(function () {
        if (kind) {
            kind = jq(this).val().split("-")[0] + "-";
        }
        selVal += ',' + jq(this).val();
    });
    if (selVal.length > 0) {
        selVal = selVal.substring(1);
    }
    selVal = kind + selVal.replace(new RegExp(kind, "gm"), '');
    return selVal;
}