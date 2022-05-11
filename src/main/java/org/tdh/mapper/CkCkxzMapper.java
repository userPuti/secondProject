package org.tdh.mapper;

import org.tdh.domain.CkCkxz;
import org.tdh.dto.CkxzDto;

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
     * @param ckxzDto 查控协执入参信息
     * @return 查询到的查控协执信息的List集合
     */
    public List<CkCkxz> selectByCkxzDto(CkxzDto ckxzDto);

    /**
     * 根据表单号码删除信息
     *
     * @param bdhms 表单号码
     * @return 删除的条数
     */
    public int batchDel(String bdhms);
}




