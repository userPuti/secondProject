package org.tdh.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.TsDm;
import org.tdh.mapper.TsDmMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/24 15:41
 */
@Component
public class TsDmCache {

    public static final Map<String, List<TsDm>> KIND_TSDM_MAP = new LinkedHashMap<>();

    @Autowired
    private TsDmMapper tsDmMapper;

    public void init() {
        List<TsDm> zjfl = tsDmMapper.selectAll();
        for (TsDm tsDm : zjfl) {
            String kind = tsDm.getKind();
            if (!KIND_TSDM_MAP.containsKey(kind)) {
                List<TsDm> tsDmList = new ArrayList<>();
                tsDmList.add(tsDm);
                KIND_TSDM_MAP.put(kind, tsDmList);
            } else {
                KIND_TSDM_MAP.get(kind).add(tsDm);
            }
        }
    }
}
