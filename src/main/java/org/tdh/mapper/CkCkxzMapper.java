package org.tdh.mapper;

import org.apache.ibatis.annotations.Param;
import org.tdh.domain.CkCkxz;
import org.tdh.dto.HomePageDto;

import java.util.List;

/**
 * @author puti
 * @description 针对表【ck_ckxz】的数据库操作Mapper
 * @createDate 2022-05-05 14:27:18
 * @Entity org.tdh.domain.CkCkxz
 */
public interface CkCkxzMapper {
    /**
     * 查询出所有的CKckxz
     *
     * @return Ckckxz表里面的所有信息
     */
    public List<CkCkxz> selectAll();

    /**
     * 根据ckxzDto查询协执信息
     *
     * @param homePageDto 查控协执入参信息
     * @return 查询到的查控协执信息的List集合
     */
    public List<CkCkxz> selectByCkxzDto(HomePageDto homePageDto);

    /**
     * 根据表单号码删除信息
     *
     * @param bdhms 表单号码
     * @return 删除的条数
     */
    public int batchDel(String[] bdhms);

    /**
     * 根据表单号码查询查控协执信息
     * @param bdhm 表单号码
     * @return 查控协执对象
     */
    public CkCkxz selectByBdhm(String bdhm);

    /**
     * 插入一条数据
     * @param ckxz 查控协执对象
     * @return 插入成功返回1，否则返回0
     */
    public int insertSelective(CkCkxz ckxz);


    /**
     * 根据登记批次查询查控协执对象
     * @param djpc 登记批次
     * @return 查询到的查控协执信息，如果没有则返回null
     */
    List<CkCkxz> selectAllByDjpc(String djpc);


    /**
     * 根据登记批次查询协执说明
     * @param djpc 登记批次
     * @return 查询到的协执说明信息
     */
    String selectXzsmByDjpc(String djpc);

    /**
     * 根据登记批次查询协执单位代码
     * @param djpc 登记批次
     * @return 查询到的协执单位代码，否则返回null
     */
    List<String> selectXzdwdmByDjpc(String djpc);

    /**
     * 根据登记批次查询信息
     * @param djpc 登记批次
     * @return 删除的条数
     */
    int deleteByDjpc(String djpc);
}




