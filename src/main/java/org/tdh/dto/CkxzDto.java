package org.tdh.dto;

import org.tdh.domain.CkCkdx;

import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/25 15:41
 */
public class CkxzDto {
    /**
     * key——查控流水号
     * value--对应的查控对象
     */
    private Map<String, CkCkdx> ckCkdxMap;
    //协执单位名称
    private String xzdwdm;
    //协执说明
    private String xzsm;

    public CkxzDto() {
    }

    public CkxzDto(Map<String, CkCkdx> ckCkdxMap, String xzdwdm, String xzsm) {
        this.ckCkdxMap = ckCkdxMap;
        this.xzdwdm = xzdwdm;
        this.xzsm = xzsm;
    }


    public String getXzdwdm() {
        return xzdwdm;
    }

    public void setXzdwdm(String xzdwdm) {
        this.xzdwdm = xzdwdm;
    }

    public Map<String, CkCkdx> getCkCkdxMap() {
        return ckCkdxMap;
    }

    public void setCkCkdxMap(Map<String, CkCkdx> ckCkdxMap) {
        this.ckCkdxMap = ckCkdxMap;
    }


    public String getXzsm() {
        return xzsm;
    }

    public void setXzsm(String xzsm) {
        this.xzsm = xzsm;
    }

    @Override
    public String toString() {
        return "CkxzDto{" +
                ", ckCkdxMap=" + ckCkdxMap +
                ", xzdwmc='" + xzdwdm + '\'' +
                ", xzsm='" + xzsm + '\'' +
                '}';
    }
}
