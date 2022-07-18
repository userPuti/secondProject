package org.tdh.util.translate;

import org.tdh.cache.CkxzdwCache;
import org.tdh.domain.CkXzdw;

import java.util.List;

/**
 * @author Puti
 * @date 2022/7/18 14:23
 */
public class CkxzdwTranslate {
    // 根据协执单位的code来获取协执单位的信息
    public static List<CkXzdw> getCkxzdwByCode(String code) {
        return CkxzdwCache.XZDWFL_XZDW_MAP.get(code);
    }
}
