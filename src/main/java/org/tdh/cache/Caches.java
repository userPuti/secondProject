package org.tdh.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsDm;
import org.tdh.mapper.CkXzdwMapper;
import org.tdh.mapper.TsDmMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/6 15:23
 */
@Component
public class Caches {
    private Logger log = LoggerFactory.getLogger(Caches.class);

    public static final Map<String, List<TsDm>> CKZT_MAP = new HashMap<>();
    public static final Map<String, CkXzdw> CKXZDW_MAP = new HashMap<>();

    @Autowired
    private TsDmMapper tsDmMapper;

    @PostConstruct
    private void initTsDmMap() {
        log.debug("正在加载查控状态map!");
        List<TsDm> ckzts = tsDmMapper.selectAllByKindAndSfjy("CKZT", "0");
        CKZT_MAP.put("ckzts", ckzts);
        log.debug("查控状态map加载完毕：{}", ckzts);
    }

    @Autowired
    private CkXzdwMapper ckXzdwMapper;

    @PostConstruct
    private void initCkxzdw() {
        log.debug("正在加载查控协执单位的map!");
        List<CkXzdw> ckXzdws = ckXzdwMapper.selectAll();
        for (CkXzdw ckXzdw : ckXzdws) {
            CKXZDW_MAP.put(ckXzdw.getXzdwdm(), ckXzdw);
        }
        log.debug("查控协执单位的map加载完毕: {}", ckXzdws);
    }
}
