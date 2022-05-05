package org.tdh.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName ts_dm
 */
public class TsDm implements Serializable {
    /**
     * 代码      
     */
    private String code;

    /**
     * 种类      
     */
    private String kind;

    /**
     * 标志      
     */
    private String bz;

    /**
     * 最后更新日期      
     */
    private Date lastupdate;

    /**
     * 标题      
     */
    private String bt;

    /**
     * 名称      
     */
    private String mc;

    /**
     * 是否禁用      
     */
    private String sfjy;

    /**
     * 排序号      
     */
    private Integer pxh;

    private static final long serialVersionUID = 1L;

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
     * 标志      
     */
    public String getBz() {
        return bz;
    }

    /**
     * 标志      
     */
    public void setBz(String bz) {
        this.bz = bz;
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
        TsDm other = (TsDm) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getKind() == null ? other.getKind() == null : this.getKind().equals(other.getKind()))
            && (this.getBz() == null ? other.getBz() == null : this.getBz().equals(other.getBz()))
            && (this.getLastupdate() == null ? other.getLastupdate() == null : this.getLastupdate().equals(other.getLastupdate()))
            && (this.getBt() == null ? other.getBt() == null : this.getBt().equals(other.getBt()))
            && (this.getMc() == null ? other.getMc() == null : this.getMc().equals(other.getMc()))
            && (this.getSfjy() == null ? other.getSfjy() == null : this.getSfjy().equals(other.getSfjy()))
            && (this.getPxh() == null ? other.getPxh() == null : this.getPxh().equals(other.getPxh()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getKind() == null) ? 0 : getKind().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        result = prime * result + ((getLastupdate() == null) ? 0 : getLastupdate().hashCode());
        result = prime * result + ((getBt() == null) ? 0 : getBt().hashCode());
        result = prime * result + ((getMc() == null) ? 0 : getMc().hashCode());
        result = prime * result + ((getSfjy() == null) ? 0 : getSfjy().hashCode());
        result = prime * result + ((getPxh() == null) ? 0 : getPxh().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", kind=").append(kind);
        sb.append(", bz=").append(bz);
        sb.append(", lastupdate=").append(lastupdate);
        sb.append(", bt=").append(bt);
        sb.append(", mc=").append(mc);
        sb.append(", sfjy=").append(sfjy);
        sb.append(", pxh=").append(pxh);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}