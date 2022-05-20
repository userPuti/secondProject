package org.tdh.service;

import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkCkxz;

/**
 * @author puti
 * @description 针对表【ck_ckdx】的数据库操作Service
 * @createDate 2022-05-05 14:26:12
 */
public interface CkCkdxService {
    CkCkdx getAll();

    /**
     * 插入一条查控协执对象
     *
     * @param ckdx 查控协执对象
     * @return 是否插入成功
     */
    boolean insertCkdx(CkCkdx ckdx);

    /**
     * 插入一条查控协执信息
     *
     * @param ckxz 查控协执对象
     * @return 插入成功返回true，失败返回false
     */
    boolean insertCkxz(CkCkxz ckxz);
}
