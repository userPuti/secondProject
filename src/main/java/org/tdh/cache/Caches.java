package org.tdh.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.TsBzdm;

import javax.annotation.PostConstruct;

/**
 * @author Puti
 * @date 2022/5/6 15:23
 */
@Component
public class Caches {
    private Logger log = LoggerFactory.getLogger(Caches.class);

    @Autowired
    private TsBzdmCache tsBzdmCache;

    @Autowired
    private TsDmCache tsDmCache;

    @Autowired
    private CkxzdwCache ckxzdwCache;

    public static String convertTsBzdmMc(String kind, String code) {
        String mc = "";
        TsBzdm tsBzdm = TsBzdmCache.KINDCODE_TSBZDM_MAP.get(kind + "-" + code);
        if (tsBzdm != null) {
            mc = tsBzdm.getMc();
        }
        return mc;
    }


    /**
     * 加载缓存
     */
    @PostConstruct
    private void initCxsq() {
        tsDmCache.init();
        tsBzdmCache.init();
        ckxzdwCache.init();
    }
}
