package org.tdh.util;

import org.tdh.cache.CkxzdwCache;
import org.tdh.cache.TsBzdmCache;
import org.tdh.cache.TsDmCache;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsBzdm;
import org.tdh.domain.TsDm;

import java.util.List;

/**
 * @author Puti
 * @date 2022/7/19 11:28
 */
public class TranslateUtils {
    // 根据协执单位的code来获取协执单位的信息
    public static List<CkXzdw> getCkxzdwByCode(String code) {
        return CkxzdwCache.XZDWFL_XZDW_MAP.get(code);
    }

    //根据kind获取对应的TSDM的集合
    public static List<TsDm> getTsDmByKind(String kind) {
        kind = kind.toUpperCase();
        return TsDmCache.KIND_TSDM_MAP.get(kind);
    }

    //根据kind获取对应的标准代码集合
    public static List<TsBzdm> getTsbzdmByKind(String kind) {
        return TsBzdmCache.KIND_TSBZDM_MAP.get(kind);
    }

    //根据协执单位代码查询协执单位
    public static CkXzdw getCkxzdwByXzdwdm(String xzdwdm) {
        return CkxzdwCache.XZDWDM_XZDW_MAP.get(xzdwdm);
    }
}
