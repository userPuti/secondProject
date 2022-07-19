package org.tdh.mapper;

import org.apache.ibatis.annotations.Param;
import org.tdh.domain.TsDm;

import java.util.List;

/**
 * @author puti
 * @description 针对表【ts_dm】的数据库操作Mapper
 * @createDate 2022-05-05 14:28:37
 * @Entity org.tdh.domain.TsDm
 */
public interface TsDmMapper {

    /**
     * 根据kind和是否禁用查询
     *
     * @param kind 类型
     * @param sfjy 是否禁用
     * @return TsDm对象
     */
    List<TsDm> selectAllByKindAndSfjy(@Param("kind") String kind, @Param("sfjy") String sfjy);

    /**
     * 查询所有的代码
     *
     * @return
     */
    List<TsDm> selectAll();
}




