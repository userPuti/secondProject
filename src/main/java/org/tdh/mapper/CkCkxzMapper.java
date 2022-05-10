package org.tdh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
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

    public List<CkCkxz> selectByCkxzDto(CkxzDto ckxzDto);
}




