package org.tdh.util.translate;

import org.tdh.cache.TsBzdmCache;
import org.tdh.domain.TsBzdm;

import java.util.List;

/**
 * @author Puti
 * @date 2022/7/18 14:20
 */
public class TsBzdmTranslate {
    //根据kind获取对应的标准代码集合
    public static List<TsBzdm> getTsbzdmByKind(String kind) {
        return TsBzdmCache.KIND_TSBZDM_MAP.get(kind);
    }
}
