package org.tdh.mapper;

import org.tdh.domain.CkXzdw;

import java.util.List;

/**
* @author puti
* @description 针对表【ck_xzdw】的数据库操作Mapper
* @createDate 2022-05-05 14:28:37
* @Entity org.tdh.domain.CkXzdw
*/
public interface CkXzdwMapper {
    /**
     * 查询所有的协执单位
     * @return 协执单位List集合
     */
    List<CkXzdw> selectAll();
}




