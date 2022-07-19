package org.tdh.enums;

/**
 * @author Puti
 * @date 2022/7/19 9:01
 */
public enum CkztEnum {
    DJ("10", "登记"), CP("20", "呈批"), SP("30", "审批"), GZ("40", "盖章"), FS("60", "发送"), JS("70", "接收"), XZTH("75", "协执退回"), FK("80", "反馈");

    private String code;
    private String mc;
    private Object aa;
    CkztEnum(String code, String mc) {
        this.code = code;
        this.mc = mc;
    }

    public String getCode() {
        return code;
    }

    public String getMc() {
        return mc;
    }


}
