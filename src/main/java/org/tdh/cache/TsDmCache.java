package org.tdh.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.TsDm;
import org.tdh.mapper.TsDmMapper;

import java.util.*;

/**
 * @author Puti
 * @date 2022/5/24 15:41
 */
@Component
public class TsDmCache {

    /**
     * key: kind
     * value: kind 对应的  tsdm
     */
    public static final Map<String, List<TsDm>> KIND_TSDM_MAP = new LinkedHashMap<>();

    /**
     * key: 查控类别的code
     * value: 查控类别的bz
     */
    public static final Map<String, String> CODE_BZ_MAP = new HashMap<>();

    @Autowired
    private TsDmMapper tsDmMapper;

    public void init() {
        List<TsDm> dms = tsDmMapper.selectAll();
        for (TsDm tsDm : dms) {
            String kind = tsDm.getKind();
            if (!KIND_TSDM_MAP.containsKey(kind)) {
                List<TsDm> tsDmList = new ArrayList<>();
                tsDmList.add(tsDm);
                KIND_TSDM_MAP.put(kind, tsDmList);
            } else {
                KIND_TSDM_MAP.get(kind).add(tsDm);
            }
        }

        for (TsDm cklb : KIND_TSDM_MAP.get("CKLB")) {
            CODE_BZ_MAP.put(cklb.getCode(),cklb.getBz());
        }
    }
}
