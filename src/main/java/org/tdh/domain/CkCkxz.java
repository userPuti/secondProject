package org.tdh.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName ck_ckxz
 */
public class CkCkxz implements Serializable {
    /**
     * 表单号码UUID  
     */
    private String bdhm;

    /**
     * 查控流水号  
     */
    private String cklsh;

    /**
     * 状态  代码值TS_DM.KIND=CKZT
     */
    private String zt;

    /**
     * 协执类别    1-查询 2-控制
     */
    private String xzlb;

    /**
     * 协执单位代码  
     */
    private String xzdwdm;

    /**
     * 协执单位分类  代码值TS_DM.KIND=CKLB
     */
    private String xzdwfl;

    /**
     * 案号  
     */
    private String ah;

    /**
     * 证件类型  代码值TS_DM.KIND=ZJFL
     */
    private String zjlx;

    /**
     * 证件号码  
     */
    private String zjhm;

    /**
     * 名称  
     */
    private String mc;

    /**
     * 国籍
     */
    private String gj;

    /**
     * 户籍所在地  
     */
    private String hjszd;

    /**
     * 承办人  中文姓名
     */
    private String cbr;

    /**
     * 书记员  中文姓名
     */
    private String sjy;

    /**
     * 协助说明  
     */
    private String xzsm;

    /**
     * 法院代码  
     */
    private String fydm;

    /**
     * 更新日期  
     */
    private Date lastupdate;

    private static final long serialVersionUID = 1L;

    /**
     * 表单号码UUID  
     */
    public String getBdhm() {
        return bdhm;
    }

    /**
     * 表单号码UUID  
     */
    public void setBdhm(String bdhm) {
        this.bdhm = bdhm;
    }

    /**
     * 查控流水号  
     */
    public String getCklsh() {
        return cklsh;
    }

    /**
     * 查控流水号  
     */
    public void setCklsh(String cklsh) {
        this.cklsh = cklsh;
    }

    /**
     * 状态  代码值TS_DM.KIND=CKZT
     */
    public String getZt() {
        return zt;
    }

    /**
     * 状态  代码值TS_DM.KIND=CKZT
     */
    public void setZt(String zt) {
        this.zt = zt;
    }

    /**
     * 协执类别    1-查询 2-控制
     */
    public String getXzlb() {
        return xzlb;
    }

    /**
     * 协执类别    1-查询 2-控制
     */
    public void setXzlb(String xzlb) {
        this.xzlb = xzlb;
    }

    /**
     * 协执单位代码  
     */
    public String getXzdwdm() {
        return xzdwdm;
    }

    /**
     * 协执单位代码  
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
     * 案号  
     */
    public String getAh() {
        return ah;
    }

    /**
     * 案号  
     */
    public void setAh(String ah) {
        this.ah = ah;
    }

    /**
     * 证件类型  代码值TS_DM.KIND=ZJFL
     */
    public String getZjlx() {
        return zjlx;
    }

    /**
     * 证件类型  代码值TS_DM.KIND=ZJFL
     */
    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    /**
     * 证件号码  
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * 证件号码  
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
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
     * 国籍
     */
    public String getGj() {
        return gj;
    }

    /**
     * 国籍
     */
    public void setGj(String gj) {
        this.gj = gj;
    }

    /**
     * 户籍所在地  
     */
    public String getHjszd() {
        return hjszd;
    }

    /**
     * 户籍所在地  
     */
    public void setHjszd(String hjszd) {
        this.hjszd = hjszd;
    }

    /**
     * 承办人  中文姓名
     */
    public String getCbr() {
        return cbr;
    }

    /**
     * 承办人  中文姓名
     */
    public void setCbr(String cbr) {
        this.cbr = cbr;
    }

    /**
     * 书记员  中文姓名
     */
    public String getSjy() {
        return sjy;
    }

    /**
     * 书记员  中文姓名
     */
    public void setSjy(String sjy) {
        this.sjy = sjy;
    }

    /**
     * 协助说明  
     */
    public String getXzsm() {
        return xzsm;
    }

    /**
     * 协助说明  
     */
    public void setXzsm(String xzsm) {
        this.xzsm = xzsm;
    }

    /**
     * 法院代码  
     */
    public String getFydm() {
        return fydm;
    }

    /**
     * 法院代码  
     */
    public void setFydm(String fydm) {
        this.fydm = fydm;
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
        CkCkxz other = (CkCkxz) that;
        return (this.getBdhm() == null ? other.getBdhm() == null : this.getBdhm().equals(other.getBdhm()))
            && (this.getCklsh() == null ? other.getCklsh() == null : this.getCklsh().equals(other.getCklsh()))
            && (this.getZt() == null ? other.getZt() == null : this.getZt().equals(other.getZt()))
            && (this.getXzlb() == null ? other.getXzlb() == null : this.getXzlb().equals(other.getXzlb()))
            && (this.getXzdwdm() == null ? other.getXzdwdm() == null : this.getXzdwdm().equals(other.getXzdwdm()))
            && (this.getXzdwfl() == null ? other.getXzdwfl() == null : this.getXzdwfl().equals(other.getXzdwfl()))
            && (this.getAh() == null ? other.getAh() == null : this.getAh().equals(other.getAh()))
            && (this.getZjlx() == null ? other.getZjlx() == null : this.getZjlx().equals(other.getZjlx()))
            && (this.getZjhm() == null ? other.getZjhm() == null : this.getZjhm().equals(other.getZjhm()))
            && (this.getMc() == null ? other.getMc() == null : this.getMc().equals(other.getMc()))
            && (this.getGj() == null ? other.getGj() == null : this.getGj().equals(other.getGj()))
            && (this.getHjszd() == null ? other.getHjszd() == null : this.getHjszd().equals(other.getHjszd()))
            && (this.getCbr() == null ? other.getCbr() == null : this.getCbr().equals(other.getCbr()))
            && (this.getSjy() == null ? other.getSjy() == null : this.getSjy().equals(other.getSjy()))
            && (this.getXzsm() == null ? other.getXzsm() == null : this.getXzsm().equals(other.getXzsm()))
            && (this.getFydm() == null ? other.getFydm() == null : this.getFydm().equals(other.getFydm()))
            && (this.getLastupdate() == null ? other.getLastupdate() == null : this.getLastupdate().equals(other.getLastupdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBdhm() == null) ? 0 : getBdhm().hashCode());
        result = prime * result + ((getCklsh() == null) ? 0 : getCklsh().hashCode());
        result = prime * result + ((getZt() == null) ? 0 : getZt().hashCode());
        result = prime * result + ((getXzlb() == null) ? 0 : getXzlb().hashCode());
        result = prime * result + ((getXzdwdm() == null) ? 0 : getXzdwdm().hashCode());
        result = prime * result + ((getXzdwfl() == null) ? 0 : getXzdwfl().hashCode());
        result = prime * result + ((getAh() == null) ? 0 : getAh().hashCode());
        result = prime * result + ((getZjlx() == null) ? 0 : getZjlx().hashCode());
        result = prime * result + ((getZjhm() == null) ? 0 : getZjhm().hashCode());
        result = prime * result + ((getMc() == null) ? 0 : getMc().hashCode());
        result = prime * result + ((getGj() == null) ? 0 : getGj().hashCode());
        result = prime * result + ((getHjszd() == null) ? 0 : getHjszd().hashCode());
        result = prime * result + ((getCbr() == null) ? 0 : getCbr().hashCode());
        result = prime * result + ((getSjy() == null) ? 0 : getSjy().hashCode());
        result = prime * result + ((getXzsm() == null) ? 0 : getXzsm().hashCode());
        result = prime * result + ((getFydm() == null) ? 0 : getFydm().hashCode());
        result = prime * result + ((getLastupdate() == null) ? 0 : getLastupdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bdhm=").append(bdhm);
        sb.append(", cklsh=").append(cklsh);
        sb.append(", zt=").append(zt);
        sb.append(", xzlb=").append(xzlb);
        sb.append(", xzdwdm=").append(xzdwdm);
        sb.append(", xzdwfl=").append(xzdwfl);
        sb.append(", ah=").append(ah);
        sb.append(", zjlx=").append(zjlx);
        sb.append(", zjhm=").append(zjhm);
        sb.append(", mc=").append(mc);
        sb.append(", gj=").append(gj);
        sb.append(", hjszd=").append(hjszd);
        sb.append(", cbr=").append(cbr);
        sb.append(", sjy=").append(sjy);
        sb.append(", xzsm=").append(xzsm);
        sb.append(", fydm=").append(fydm);
        sb.append(", lastupdate=").append(lastupdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}