package org.tdh.mapper;

import org.apache.ibatis.annotations.Param;
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
     *
     * @param ckJz 查控卷宗对象
     * @return 插入的条数信息
     */
    int insertSelective(CkJz ckJz);

    /**
     * 根据登记批次查询用户信息
     *
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


    /**
     * 查询同一批次的所有卷宗的序号
     *
     * @param djpc 登记批次
     * @return 当前批次所有的序号
     */
    List<Integer> selectXhByDjpc(String djpc);


    /**
     * 根据登记批次和序号去删除一个文件
     *
     * @param djpc 登记批次
     * @param xhs  序号信息
     * @return 删除的条数
     */
    int deleteByXhAndDjpc(@Param("djpc") String djpc, @Param("xhs") List<Integer> xhs);
}




