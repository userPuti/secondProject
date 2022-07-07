package org.tdh.dto;

import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkJz;

import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/25 15:41
 */
public class CxsqDto {
    /**
     * key——查控流水号
     * value--对应的查控对象
     */
    private Map<String, CkCkdx> ckCkdxMap;
    //协执单位名称
    private String xzdwdm;
    //协执说明
    private String xzsm;
    //登记批次
    private String djpc;
    /**
     * key -- 文件的uuid
     * value -- 查控卷宗的临时对象
     */
//    private Map<String, CkJz> fileInfo;

    private List<CkJz> files;

    public List<CkJz> getFiles() {
        return files;
    }

    public void setFiles(List<CkJz> files) {
        this.files = files;
    }

    public String getDjpc() {
        return djpc;
    }

    public void setDjpc(String djpc) {
        this.djpc = djpc;
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
        return "CxsqDto{" +
                "ckCkdxMap=" + ckCkdxMap +
                ", xzdwdm='" + xzdwdm + '\'' +
                ", xzsm='" + xzsm + '\'' +
                ", djpc='" + djpc + '\'' +
                ", files=" + files +
                '}';
    }
}
