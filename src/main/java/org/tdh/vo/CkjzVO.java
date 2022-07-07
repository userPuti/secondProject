package org.tdh.vo;

import java.util.Date;

/**
 * @author Puti
 * @date 2022/7/6 9:45
 */
public class CkjzVO {
    /**
     * 登记批次UUID
     */
    private String djpc;

    /**
     * 序号
     */
    private Integer xh;

    /**
     * 文件名称
     */
    private String wjmc;

    /**
     * 文件类型
     */
    private String wjlx;

    /**
     * 文件文件地址
     */
    private String path;

    /**
     * 临时文件标识
     */
    private String tmepUuid;

    public String getDjpc() {
        return djpc;
    }

    public void setDjpc(String djpc) {
        this.djpc = djpc;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    public String getWjlx() {
        return wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTmepUuid() {
        return tmepUuid;
    }

    public void setTmepUuid(String tmepUuid) {
        this.tmepUuid = tmepUuid;
    }
}
