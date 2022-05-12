package org.tdh.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName ck_ckdx
 */
public class CkCkdx implements Serializable {
    /**
     * 查控流水号UUID  
     */
    private String cklsh;

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
     * 承办人  中文姓名
     */
    private String cbr;

    /**
     * 书记员  中文姓名
     */
    private String sjy;

    /**
     * 户籍所在地  
     */
    private String hjszd;

    /**
     * 国籍  代码值TS_BZDM.KIND=00004
     */
    private String gj;

    /**
     * 状态  代码值TS_DM.KIND=CKZT
     */
    private String zt;

    /**
     * 案号  
     */
    private String ah;

    /**
     * 法院代码  
     */
    private String fydm;

    /**
     * 登记批次UUID  
     */
    private String djpc;

    /**
     * 诉讼地位  
     */
    private String ssdw;

    /**
     * 该对象涉及到的查控范围（行业），以逗号隔开，数据来源TS_DM.KIND=CKLB
     */
    private String ckfw;

    /**
     * 更新日期  
     */
    private Date lastupdate;

    private static final long serialVersionUID = 1L;

    /**
     * 查控流水号UUID  
     */
    public String getCklsh() {
        return cklsh;
    }

    /**
     * 查控流水号UUID  
     */
    public void setCklsh(String cklsh) {
        this.cklsh = cklsh;
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
     * 国籍  代码值TS_BZDM.KIND=00004
     */
    public String getGj() {
        return gj;
    }

    /**
     * 国籍  代码值TS_BZDM.KIND=00004
     */
    public void setGj(String gj) {
        this.gj = gj;
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
     * 登记批次UUID  
     */
    public String getDjpc() {
        return djpc;
    }

    /**
     * 登记批次UUID  
     */
    public void setDjpc(String djpc) {
        this.djpc = djpc;
    }

    /**
     * 诉讼地位  
     */
    public String getSsdw() {
        return ssdw;
    }

    /**
     * 诉讼地位  
     */
    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }

    /**
     * 该对象涉及到的查控范围（行业），以逗号隔开，数据来源TS_DM.KIND=CKLB
     */
    public String getCkfw() {
        return ckfw;
    }

    /**
     * 该对象涉及到的查控范围（行业），以逗号隔开，数据来源TS_DM.KIND=CKLB
     */
    public void setCkfw(String ckfw) {
        this.ckfw = ckfw;
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
        CkCkdx other = (CkCkdx) that;
        return (this.getCklsh() == null ? other.getCklsh() == null : this.getCklsh().equals(other.getCklsh()))
            && (this.getZjlx() == null ? other.getZjlx() == null : this.getZjlx().equals(other.getZjlx()))
            && (this.getZjhm() == null ? other.getZjhm() == null : this.getZjhm().equals(other.getZjhm()))
            && (this.getMc() == null ? other.getMc() == null : this.getMc().equals(other.getMc()))
            && (this.getCbr() == null ? other.getCbr() == null : this.getCbr().equals(other.getCbr()))
            && (this.getSjy() == null ? other.getSjy() == null : this.getSjy().equals(other.getSjy()))
            && (this.getHjszd() == null ? other.getHjszd() == null : this.getHjszd().equals(other.getHjszd()))
            && (this.getGj() == null ? other.getGj() == null : this.getGj().equals(other.getGj()))
            && (this.getZt() == null ? other.getZt() == null : this.getZt().equals(other.getZt()))
            && (this.getAh() == null ? other.getAh() == null : this.getAh().equals(other.getAh()))
            && (this.getFydm() == null ? other.getFydm() == null : this.getFydm().equals(other.getFydm()))
            && (this.getDjpc() == null ? other.getDjpc() == null : this.getDjpc().equals(other.getDjpc()))
            && (this.getSsdw() == null ? other.getSsdw() == null : this.getSsdw().equals(other.getSsdw()))
            && (this.getCkfw() == null ? other.getCkfw() == null : this.getCkfw().equals(other.getCkfw()))
            && (this.getLastupdate() == null ? other.getLastupdate() == null : this.getLastupdate().equals(other.getLastupdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCklsh() == null) ? 0 : getCklsh().hashCode());
        result = prime * result + ((getZjlx() == null) ? 0 : getZjlx().hashCode());
        result = prime * result + ((getZjhm() == null) ? 0 : getZjhm().hashCode());
        result = prime * result + ((getMc() == null) ? 0 : getMc().hashCode());
        result = prime * result + ((getCbr() == null) ? 0 : getCbr().hashCode());
        result = prime * result + ((getSjy() == null) ? 0 : getSjy().hashCode());
        result = prime * result + ((getHjszd() == null) ? 0 : getHjszd().hashCode());
        result = prime * result + ((getGj() == null) ? 0 : getGj().hashCode());
        result = prime * result + ((getZt() == null) ? 0 : getZt().hashCode());
        result = prime * result + ((getAh() == null) ? 0 : getAh().hashCode());
        result = prime * result + ((getFydm() == null) ? 0 : getFydm().hashCode());
        result = prime * result + ((getDjpc() == null) ? 0 : getDjpc().hashCode());
        result = prime * result + ((getSsdw() == null) ? 0 : getSsdw().hashCode());
        result = prime * result + ((getCkfw() == null) ? 0 : getCkfw().hashCode());
        result = prime * result + ((getLastupdate() == null) ? 0 : getLastupdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cklsh=").append(cklsh);
        sb.append(", zjlx=").append(zjlx);
        sb.append(", zjhm=").append(zjhm);
        sb.append(", mc=").append(mc);
        sb.append(", cbr=").append(cbr);
        sb.append(", sjy=").append(sjy);
        sb.append(", hjszd=").append(hjszd);
        sb.append(", gj=").append(gj);
        sb.append(", zt=").append(zt);
        sb.append(", ah=").append(ah);
        sb.append(", fydm=").append(fydm);
        sb.append(", djpc=").append(djpc);
        sb.append(", ssdw=").append(ssdw);
        sb.append(", ckfw=").append(ckfw);
        sb.append(", lastupdate=").append(lastupdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}