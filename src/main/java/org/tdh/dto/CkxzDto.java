package org.tdh.dto;

import org.tdh.domain.CkCkdx;

import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/7 10:48
 */
public class CkxzDto {
    private String zt;
    private String ah;
    private String mc;
    private String zjhm;
    private int start;
    private int limit;

    public CkxzDto() {
    }

    public CkxzDto(String zt, String ah, String mc, String zjhm, int start, int limit) {
        this.zt = zt;
        this.ah = ah;
        this.mc = mc;
        this.zjhm = zjhm;
        this.start = start;
        this.limit = limit;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CkxzDto{" +
                "zt='" + zt + '\'' +
                ", ah='" + ah + '\'' +
                ", mc='" + mc + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}
