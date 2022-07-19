package org.tdh.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName ts_bzdm
 */
public class TsBzdm implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 种类
     */
    private String kind;
    /**
     * 代码
     */
    private String code;
    /**
     * 09种类
     */
    private String kin09;
    /**
     * 标题
     */
    private String bt;
    /**
     * 最后更新日期
     */
    private Date lastupdate;
    /**
     * 新代码
     */
    private String codenow;
    /**
     * 是否禁用
     */
    private String sfjy;
    /**
     * 版本
     */
    private String ver;
    /**
     * 名称
     */
    private String mc;
    /**
     * 父代码
     */
    private String fdm;
    /**
     * 排序号
     */
    private Integer pxh;

    /**
     * 种类
     */
    public String getKind() {
        return kind;
    }

    /**
     * 种类
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 09种类
     */
    public String getKin09() {
        return kin09;
    }

    /**
     * 09种类
     */
    public void setKin09(String kin09) {
        this.kin09 = kin09;
    }

    /**
     * 标题
     */
    public String getBt() {
        return bt;
    }

    /**
     * 标题
     */
    public void setBt(String bt) {
        this.bt = bt;
    }

    /**
     * 最后更新日期
     */
    public Date getLastupdate() {
        return lastupdate;
    }

    /**
     * 最后更新日期
     */
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    /**
     * 新代码
     */
    public String getCodenow() {
        return codenow;
    }

    /**
     * 新代码
     */
    public void setCodenow(String codenow) {
        this.codenow = codenow;
    }

    /**
     * 是否禁用
     */
    public String getSfjy() {
        return sfjy;
    }

    /**
     * 是否禁用
     */
    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    /**
     * 版本
     */
    public String getVer() {
        return ver;
    }

    /**
     * 版本
     */
    public void setVer(String ver) {
        this.ver = ver;
    }

    /**
     * 名称
     */
    public String getMc() {
        return mc;
    }

    /**
     * 名称
     */
    public void setMc(String mc) {
        this.mc = mc;
    }

    /**
     * 父代码
     */
    public String getFdm() {
        return fdm;
    }

    /**
     * 父代码
     */
    public void setFdm(String fdm) {
        this.fdm = fdm;
    }

    /**
     * 排序号
     */
    public Integer getPxh() {
        return pxh;
    }

    /**
     * 排序号
     */
    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TsBzdm other = (TsBzdm) that;
        return (this.getKind() == null ? other.getKind() == null : this.getKind().equals(other.getKind()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getKin09() == null ? other.getKin09() == null : this.getKin09().equals(other.getKin09()))
                && (this.getBt() == null ? other.getBt() == null : this.getBt().equals(other.getBt()))
                && (this.getLastupdate() == null ? other.getLastupdate() == null : this.getLastupdate().equals(other.getLastupdate()))
                && (this.getCodenow() == null ? other.getCodenow() == null : this.getCodenow().equals(other.getCodenow()))
                && (this.getSfjy() == null ? other.getSfjy() == null : this.getSfjy().equals(other.getSfjy()))
                && (this.getVer() == null ? other.getVer() == null : this.getVer().equals(other.getVer()))
                && (this.getMc() == null ? other.getMc() == null : this.getMc().equals(other.getMc()))
                && (this.getFdm() == null ? other.getFdm() == null : this.getFdm().equals(other.getFdm()))
                && (this.getPxh() == null ? other.getPxh() == null : this.getPxh().equals(other.getPxh()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getKind() == null) ? 0 : getKind().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getKin09() == null) ? 0 : getKin09().hashCode());
        result = prime * result + ((getBt() == null) ? 0 : getBt().hashCode());
        result = prime * result + ((getLastupdate() == null) ? 0 : getLastupdate().hashCode());
        result = prime * result + ((getCodenow() == null) ? 0 : getCodenow().hashCode());
        result = prime * result + ((getSfjy() == null) ? 0 : getSfjy().hashCode());
        result = prime * result + ((getVer() == null) ? 0 : getVer().hashCode());
        result = prime * result + ((getMc() == null) ? 0 : getMc().hashCode());
        result = prime * result + ((getFdm() == null) ? 0 : getFdm().hashCode());
        result = prime * result + ((getPxh() == null) ? 0 : getPxh().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", kind=").append(kind);
        sb.append(", code=").append(code);
        sb.append(", kin09=").append(kin09);
        sb.append(", bt=").append(bt);
        sb.append(", lastupdate=").append(lastupdate);
        sb.append(", codenow=").append(codenow);
        sb.append(", sfjy=").append(sfjy);
        sb.append(", ver=").append(ver);
        sb.append(", mc=").append(mc);
        sb.append(", fdm=").append(fdm);
        sb.append(", pxh=").append(pxh);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}