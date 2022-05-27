package org.tdh.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.domain.CkXzdw;
import org.tdh.mapper.CkXzdwMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/5/24 15:55
 */
@Component
public class CkxzdwCache {

    public static final Map<String, List<CkXzdw>> XZDWFL_XZDW_MAP = new HashMap<>();
    public static final Map<String, CkXzdw> XZDWDM_XZDW_MAP = new HashMap<>();

    @Autowired
    private CkXzdwMapper ckXzdwMapper;

    public void init() {
        List<CkXzdw> ckXzdwList = ckXzdwMapper.selectAll();
        for (CkXzdw ckXzdw : ckXzdwList) {
            XZDWDM_XZDW_MAP.put(ckXzdw.getXzdwdm(), ckXzdw);
            String xzdwfl = ckXzdw.getXzdwfl();
            if (!XZDWFL_XZDW_MAP.containsKey(xzdwfl)) {
                List<CkXzdw> tempList = new ArrayList<>();
                tempList.add(ckXzdw);
                XZDWFL_XZDW_MAP.put(xzdwfl, tempList);
            } else {
                XZDWFL_XZDW_MAP.get(xzdwfl).add(ckXzdw);
            }
        }
    }
}
