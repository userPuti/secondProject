package org.tdh.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkCkxz;
import org.tdh.mapper.CkCkdxMapper;
import org.tdh.mapper.CkCkxzMapper;
import org.tdh.service.CkCkdxService;

/**
 * @author puti
 * @description 针对表【ck_ckdx】的数据库操作Service实现
 * @createDate 2022-05-05 14:26:12
 */
@Service
public class CkCkdxServiceImpl implements CkCkdxService {
    private Logger log = LoggerFactory.getLogger(CkckcxzServiceImpl.class);

    @Autowired
    private CkCkdxMapper ckdxMapper;

    @Autowired
    private CkCkxzMapper ckxzMapper;

    @Override
    public CkCkdx getAll() {
        return ckdxMapper.getAll();
    }

    /**
     * 插入一条查控协执对象
     *
     * @param ckdx 查控协执对象
     * @return 是否插入成功
     */
    @Override
    @Transactional
    public boolean insertCkdx(CkCkdx ckdx) {
        if (ckdx != null) {
            log.debug("正在插入一条查控对象数据！：{}", ckdx);
            int count = ckdxMapper.insertSelective(ckdx);
            if (0 != count) {
                log.debug("插入一条查控对象数据成功!");
                return true;
            } else {
                log.info("插入查控对象数据出现了问题！");
                return false;
            }
        }
        log.info("需要插入的查控对象为空，不能插入！");
        return false;
    }

    /**
     * 插入一条查控协执信息
     *
     * @param ckxz 查控协执对象
     * @return 插入成功返回true，失败返回false
     */
    @Override
    @Transactional
    public boolean insertCkxz(CkCkxz ckxz) {
        if (ckxz != null) {
            log.debug("正在插入一条查控协执数据： {}", ckxz);
            int count = ckxzMapper.insertSelective(ckxz);
            if (0 != count) {
                log.debug("插入一条查控协执数据成功!");
                return true;
            } else {
                log.info("插入查控协执数据出现了问题！");
                return false;
            }
        }
        log.info("需要插入的查控协执对象为空，不能插入！");
        return false;
    }
}
