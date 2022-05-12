package org.tdh.service;

import org.tdh.domain.CkCkxz;
import org.tdh.dto.CkxzDto;

/**
 * @author Puti
 * @date 2022/5/6 16:01
 */
public interface CkCkxzService {
    /**
     * ckckxz表中的所有信息
     * @return 所有信息拼接的xml的String
     */
    String CkckxzInfo(CkxzDto ckxzDto);

    /**
     * 批量删除
     * @param bdhms 表单号码
     * @return 删除的数量
     */
    int batchDel(String bdhms);

    /**
     * 根据表单号码查询协执信息
     * @param bdhm 表单号码
     * @return 查控协执对象
     */
    CkCkxz viewCkxzInfo(String bdhm);
}
