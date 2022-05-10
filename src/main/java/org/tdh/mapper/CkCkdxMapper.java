package org.tdh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
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
}
