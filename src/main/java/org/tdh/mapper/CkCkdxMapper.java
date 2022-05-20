package org.tdh.mapper;

import org.tdh.domain.CkCkdx;

/**
* @author puti
* @description 针对表【ck_ckdx】的数据库操作Mapper
* @createDate 2022-05-05 14:26:12
* @Entity org.tdh.domain.CkCkdx
*/
public interface CkCkdxMapper  {
    /**
     *
     * @return
     */
    public CkCkdx getAll();

    /**
     * 插入一条完整的数据
     * @param ckdx 查控协执对象
     * @return 插入的条数
     */
    public int insert(CkCkdx ckdx);

    /**
     * 插入一条数据，可以不是完整的
     * @param ckdx 查控协执对象
     * @return 插入的条数
     */
    public int insertSelective(CkCkdx ckdx);
}
