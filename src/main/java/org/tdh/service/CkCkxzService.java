package org.tdh.service;

import org.tdh.dto.CkxzDto;

/**
 * @author Puti
 * @date 2022/5/6 16:01
 */
public interface CkCkxzService {
    /**
     * ckckxz表中的所有信息
     * @return 所有信息拼接的xml的String
     */
    String CkckxzInfo(CkxzDto ckxzDto);
}
