package org.tdh.mapper;

import org.tdh.domain.CkJz;

import java.util.List;

/**
* @author puti
* @description 针对表【ck_jz】的数据库操作Mapper
* @createDate 2022-05-05 14:28:37
* @Entity org.tdh.domain.CkJz
*/
public interface CkJzMapper {
    /**
     * 插入卷宗信息
     * @param ckJz 查控卷宗对象
     * @return 插入的条数信息
     */
    int insertSelective(CkJz ckJz);

    /**
     * 根据登记批次查询用户信息
     * @param djpc 登记批次
     * @return 查询到的查控卷宗对象集合
     */
    List<CkJz> selectAllByDjpc(String djpc);

    /**
     * 根据登记批次去查询当前批次的最大序号
     *
     * @param djpc 登记批次
     * @return 当前登记批次的最大值
     */
    int selectMaxXhByDjpc(String djpc);
}




