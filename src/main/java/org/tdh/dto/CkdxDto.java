package org.tdh.dto;

import org.tdh.domain.CkCkdx;

import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/25 15:41
 */
public class CkdxDto {
    /**
     * key——查控流水号
     * value--对应的查控对象
     */
    private Map<String, CkCkdx> ckCkdxMap;

    public CkdxDto() {
    }

    public CkdxDto(Map<String, CkCkdx> ckCkdxMap) {
        this.ckCkdxMap = ckCkdxMap;
    }

    public Map<String, CkCkdx> getCkCkdxMap() {
        return ckCkdxMap;
    }

    public void setCkCkdxMap(Map<String, CkCkdx> ckCkdxMap) {
        this.ckCkdxMap = ckCkdxMap;
    }

    @Override
    public String toString() {
        return "CkdxDto{" +
                "ckCkdxMap=" + ckCkdxMap +
                '}';
    }
}
