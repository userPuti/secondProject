package org.tdh.mapper;

import org.apache.ibatis.annotations.Param;
import org.tdh.domain.TsBzdm;

import java.util.List;

/**
* @author puti
* @description 针对表【ts_bzdm】的数据库操作Mapper
* @createDate 2022-05-05 14:28:37
* @Entity org.tdh.domain.TsBzdm
*/
public interface TsBzdmMapper {

    /**
     * 根据kind来查询
     * @param kind
     * @return
     */
    List<TsBzdm> selectByKind(@Param("kind") String kind);


    /**
     * 根据kind和是否禁用来查询
     * @param kind
     * @param sfjy
     * @return
     */
    List<TsBzdm> selectByKindAndSfjy(@Param("kind") String kind, @Param("sfjy")String sfjy);

}




