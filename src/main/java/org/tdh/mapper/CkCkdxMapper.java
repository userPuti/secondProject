package org.tdh.mapper;

import org.tdh.domain.CkCkdx;
import org.tdh.dto.HomePageDto;

import java.util.List;

/**
 * @author puti
 * @description 针对表【ck_ckdx】的数据库操作Mapper
 * @createDate 2022-05-05 14:26:12
 * @Entity org.tdh.domain.CkCkdx
 */
public interface CkCkdxMapper {

    /**
     * @param homePageDto
     * @return
     */
    List<CkCkdx> selectAll(HomePageDto homePageDto);


    /**
     * 插入一条完整的数据
     *
     * @param ckdx 查控协执对象
     * @return 插入的条数
     */
    int insert(CkCkdx ckdx);

    /**
     * 插入一条数据，可以不是完整的
     *
     * @param ckdx 查控协执对象
     * @return 插入的条数
     */
    int insertSelective(CkCkdx ckdx);

    /**
     * 根据登记批次查询同一批次的查控对象
     *
     * @param djpc
     * @return 同一登记批次的查控对象
     */
    List<CkCkdx> selectAllByDjpc(String djpc);

    /**
     * 根据登记批次删除信息
     *
     * @param djpc 登记批次
     * @return 删除的条数
     */
    int deleteByDjpc(String djpc);

    /**
     * 更新查控对象
     *
     * @param ckdx 查控对象
     * @return 更新条数
     */
    int updateCkdxByCklsh(CkCkdx ckdx);

    /**
     * 根据查控流水号删除信息
     *
     * @param cklsh 查控流水号
     * @return 删除的条数
     */
    int deleteByCklsh(String[] cklsh);

    /**
     * 根据查控流水号判断数据是否在数据库中
     *
     * @param cklsh 查控流水号
     * @return 数据库中存在数据返回1，否则返回0
     */
    boolean selectByCklsh(String cklsh);


    /**
     * 根据登记批次查询当前批次的所有查控流水号信息
     *
     * @param djpc 登记批次
     * @return 查控流水号
     */
    List<String> selectCklshByDjpc(String djpc);

    /**
     * 发送时将状态设置为60
     *
     * @param djpc 登记批次
     * @return 更新的数据条数
     */
    int updateZtByDjpc(String djpc);

    /**
     * 查询同一批次的查控对象的数量
     *
     * @param djpc 登记批次
     * @return 一个批次的查控对象的数量
     */
    int countByDjpc(String djpc);
}
