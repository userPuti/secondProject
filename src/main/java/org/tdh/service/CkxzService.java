package org.tdh.service;

import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkCkxz;
import org.tdh.dto.CkxzDto;

/**
 * @author Puti
 * @date 2022/5/24 17:34
 */
public interface CkxzService {
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
    public CkCkxz viewCkxzInfo(String bdhm);


    CkCkdx getAll();

    /**
     * 插入一条查控协执对象
     *
     * @param ckdx 查控协执对象
     * @return 是否插入成功
     */
    public boolean insertCkdx(CkCkdx ckdx);

    /**
     * 插入一条查控协执信息
     *
     * @param ckxz 查控协执对象
     * @return 插入成功返回true，失败返回false
     */
    boolean insertCkxz(CkCkxz ckxz);
}
