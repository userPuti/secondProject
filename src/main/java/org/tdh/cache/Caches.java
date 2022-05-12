package org.tdh.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsBzdm;
import org.tdh.domain.TsDm;
import org.tdh.mapper.CkXzdwMapper;
import org.tdh.mapper.TsBzdmMapper;
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

    //查控状态的缓存
    public static final Map<String, List<TsDm>> CKZT_MAP = new HashMap<>();
    //查控协执单位缓存
    public static final Map<String, CkXzdw> CKXZDW_MAP = new HashMap<>();
    //证件类型的缓存
    public static final Map<String, List<TsDm>> ZJFL_MAP = new HashMap<>();
    //涉案身份的缓存
    public static final Map<String, List<TsBzdm>> SASF_MAP = new HashMap<>();
    //国籍的缓存
    public static final Map<String, List<TsBzdm>> GJ_MAP = new HashMap<>();
    //查控类别缓存
    public static final Map<String, List<TsDm>> CKLB_MAP = new HashMap<>();
    //查控协执单位分类缓存
    public static final Map<String, List<CkXzdw>> CKXZDWFL_MAP = new HashMap<>();

    @Autowired
    private TsDmMapper tsDmMapper;

    @Autowired
    private TsBzdmMapper tsBzdmMapper;

    @Autowired
    private CkXzdwMapper ckXzdwMapper;


    /**
     * 加载查控状态map
     */
    @PostConstruct
    private void initCkzt() {
        log.debug("正在加载查控状态map!");
        List<TsDm> ckzts = tsDmMapper.selectAllByKindAndSfjy("CKZT", "0");
        CKZT_MAP.put("ckzts", ckzts);
        log.debug("查控状态缓存加载完毕：{}", ckzts);
    }

    /**
     * 加载证件类型map
     * 加载涉案身份缓存
     */
    @PostConstruct
    private void initCxsq() {
        log.debug("正在加载证件类型map！");
        List<TsDm> zjfl = tsDmMapper.selectAllByKindAndSfjy("ZJFL", "0");
        ZJFL_MAP.put("zjfl", zjfl);
        log.debug("证件类型的缓存加载完成：{}", zjfl);

        log.debug("正在加载涉案身份的map!");
        List<TsBzdm> sasf = tsBzdmMapper.selectByKind("05036");
        SASF_MAP.put("sasf", sasf);
        log.debug("涉案身份缓存加载完成：{}", sasf);

        log.debug("正在加载国籍的map！");
        List<TsBzdm> gj = tsBzdmMapper.selectByKind("00004");
        GJ_MAP.put("gj", gj);
        log.debug("国籍缓存加载完成：{}", gj);

        log.debug("正在加载查控类别map！");
        List<TsDm> cklb = tsDmMapper.selectAllByKindAndSfjy("CKLB", "0");
        CKLB_MAP.put("cklb", cklb);
        log.debug("查控类别缓存加载完成：{}", cklb);

        log.debug("正在加载查控协执单位map!");
        for (TsDm ck : Caches.CKLB_MAP.get("cklb")) {
            List<CkXzdw> ckXzdws = ckXzdwMapper.selectByXzdwfl(ck.getCode());
            CKXZDWFL_MAP.put(ck.getCode(), ckXzdws);
            log.debug("{},的加载查控协执单位缓存加载完成：{}", ck.getCode(),ckXzdws);
        }
    }

    /**
     * 加载查控协执单位map
     */
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
