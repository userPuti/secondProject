package org.tdh.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.TsBzdm;
import org.tdh.mapper.TsBzdmMapper;

import java.util.*;

/**
 * @author Puti
 * @date 2022/5/23 10:47
 */
@Component
public class TsBzdmCache {

    /**
     * key
     * value
     */
    public static final Map<String, List<TsBzdm>> KIND_TSBZDM_MAP = new LinkedHashMap<>();

    /**
     * key ---> KIND-CODE
     * value ---> TsBzdm
     */
    public static final Map<String, TsBzdm> KINDCODE_TSBZDM_MAP = new HashMap<>();

    @Autowired
    private TsBzdmMapper tsBzdmMapper;

    public void init() {
        List<TsBzdm> bzdms = tsBzdmMapper.selectAll();
        for (TsBzdm tsBzdm : bzdms) {
            String kind = tsBzdm.getKind();
            if (!KIND_TSBZDM_MAP.containsKey(kind)) {
                List<TsBzdm> tsDmList = new ArrayList<>();
                tsDmList.add(tsBzdm);
                KIND_TSBZDM_MAP.put(kind, tsDmList);
            } else {
                KIND_TSBZDM_MAP.get(kind).add(tsBzdm);
            }
        }
    }
}
