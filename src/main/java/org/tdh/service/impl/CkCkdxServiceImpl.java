package org.tdh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tdh.domain.CkCkdx;
import org.tdh.service.CkCkdxService;
import org.tdh.mapper.CkCkdxMapper;
import org.springframework.stereotype.Service;

/**
* @author puti
* @description 针对表【ck_ckdx】的数据库操作Service实现
* @createDate 2022-05-05 14:26:12
*/
@Service
public class CkCkdxServiceImpl implements CkCkdxService{
    @Autowired
    private CkCkdxMapper ckCkdxMapper;

    @Override
    public CkCkdx getAll() {
        return ckCkdxMapper.getAll();
    }
}
