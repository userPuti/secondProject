package org.tdh.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName ck_xzdw
 */
public class CkXzdw implements Serializable {
    /**
     * 协助单位代码  
     */
    private String xzdwdm;

    /**
     * 协执单位分类  代码值TS_DM.KIND=CKLB
     */
    private String xzdwfl;

    /**
     * 单位名称  
     */
    private String mc;

    /**
     * 更新日期  
     */
    private Date lastupdate;

    private static final long serialVersionUID = 1L;

    /**
     * 协助单位代码  
     */
    public String getXzdwdm() {
        return xzdwdm;
    }

    /**
     * 协助单位代码  
     */
    public void setXzdwdm(String xzdwdm) {
        this.xzdwdm = xzdwdm;
    }

    /**
     * 协执单位分类  代码值TS_DM.KIND=CKLB
     */
    public String getXzdwfl() {
        return xzdwfl;
    }

    /**
     * 协执单位分类  代码值TS_DM.KIND=CKLB
     */
    public void setXzdwfl(String xzdwfl) {
        this.xzdwfl = xzdwfl;
    }

    /**
     * 单位名称  
     */
    public String getMc() {
        return mc;
    }

    /**
     * 单位名称  
     */
    public void setMc(String mc) {
        this.mc = mc;
    }

    /**
     * 更新日期  
     */
    public Date getLastupdate() {
        return lastupdate;
    }

    /**
     * 更新日期  
     */
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
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
        CkXzdw other = (CkXzdw) that;
        return (this.getXzdwdm() == null ? other.getXzdwdm() == null : this.getXzdwdm().equals(other.getXzdwdm()))
            && (this.getXzdwfl() == null ? other.getXzdwfl() == null : this.getXzdwfl().equals(other.getXzdwfl()))
            && (this.getMc() == null ? other.getMc() == null : this.getMc().equals(other.getMc()))
            && (this.getLastupdate() == null ? other.getLastupdate() == null : this.getLastupdate().equals(other.getLastupdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getXzdwdm() == null) ? 0 : getXzdwdm().hashCode());
        result = prime * result + ((getXzdwfl() == null) ? 0 : getXzdwfl().hashCode());
        result = prime * result + ((getMc() == null) ? 0 : getMc().hashCode());
        result = prime * result + ((getLastupdate() == null) ? 0 : getLastupdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", xzdwdm=").append(xzdwdm);
        sb.append(", xzdwfl=").append(xzdwfl);
        sb.append(", mc=").append(mc);
        sb.append(", lastupdate=").append(lastupdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}