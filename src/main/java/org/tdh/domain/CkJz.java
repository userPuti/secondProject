package org.tdh.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @TableName ck_jz
 */
public class CkJz implements Serializable {
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
     * 更新日期   
     */
    private Date lastupdate;

    /**
     * 文书内容   
     */
    private byte[] wsnr;

    private static final long serialVersionUID = 1L;

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
     * 序号   
     */
    public Integer getXh() {
        return xh;
    }

    /**
     * 序号   
     */
    public void setXh(Integer xh) {
        this.xh = xh;
    }

    /**
     * 文件名称   
     */
    public String getWjmc() {
        return wjmc;
    }

    /**
     * 文件名称   
     */
    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    /**
     * 文件类型   
     */
    public String getWjlx() {
        return wjlx;
    }

    /**
     * 文件类型   
     */
    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
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

    /**
     * 文书内容   
     */
    public byte[] getWsnr() {
        return wsnr;
    }

    /**
     * 文书内容   
     */
    public void setWsnr(byte[] wsnr) {
        this.wsnr = wsnr;
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
        CkJz other = (CkJz) that;
        return (this.getDjpc() == null ? other.getDjpc() == null : this.getDjpc().equals(other.getDjpc()))
            && (this.getXh() == null ? other.getXh() == null : this.getXh().equals(other.getXh()))
            && (this.getWjmc() == null ? other.getWjmc() == null : this.getWjmc().equals(other.getWjmc()))
            && (this.getWjlx() == null ? other.getWjlx() == null : this.getWjlx().equals(other.getWjlx()))
            && (this.getLastupdate() == null ? other.getLastupdate() == null : this.getLastupdate().equals(other.getLastupdate()))
            && (Arrays.equals(this.getWsnr(), other.getWsnr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDjpc() == null) ? 0 : getDjpc().hashCode());
        result = prime * result + ((getXh() == null) ? 0 : getXh().hashCode());
        result = prime * result + ((getWjmc() == null) ? 0 : getWjmc().hashCode());
        result = prime * result + ((getWjlx() == null) ? 0 : getWjlx().hashCode());
        result = prime * result + ((getLastupdate() == null) ? 0 : getLastupdate().hashCode());
        result = prime * result + (Arrays.hashCode(getWsnr()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", djpc=").append(djpc);
        sb.append(", xh=").append(xh);
        sb.append(", wjmc=").append(wjmc);
        sb.append(", wjlx=").append(wjlx);
        sb.append(", lastupdate=").append(lastupdate);
        sb.append(", wsnr=").append(wsnr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}