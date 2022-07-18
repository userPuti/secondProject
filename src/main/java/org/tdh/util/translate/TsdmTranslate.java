package org.tdh.util.translate;

import org.tdh.cache.TsDmCache;
import org.tdh.domain.TsDm;

import java.util.List;

/**
 * @author Puti
 * @date 2022/7/18 14:16
 */
public class TsdmTranslate {
    //根据kind获取对应的TSDM的集合
    public static List<TsDm> getTsDmByKind(String kind) {
        kind = kind.toUpperCase();
        return TsDmCache.KIND_TSDM_MAP.get(kind);
    }
}
