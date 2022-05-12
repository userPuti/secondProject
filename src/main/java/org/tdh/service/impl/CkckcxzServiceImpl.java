package org.tdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.cache.Caches;
import org.tdh.domain.CkCkxz;
import org.tdh.domain.TsDm;
import org.tdh.dto.CkxzDto;
import org.tdh.mapper.CkCkxzMapper;
import org.tdh.service.CkCkxzService;

import java.util.List;

/**
 * @author Puti
 * @date 2022/5/6 16:07
 */
@Service
public class CkckcxzServiceImpl implements CkCkxzService {
    private Logger log = LoggerFactory.getLogger(CkckcxzServiceImpl.class);

    @Autowired
    private CkCkxzMapper ckxzMapper;

    /**
     * ckckxz表中的所有信息
     *
     * @return 所有信息拼接的xml的String
     */
    @Override
    public String CkckxzInfo(CkxzDto ckxzDto) {
        if (ckxzDto != null) {
            log.debug("根据ckxzDto来查询信息： {}", ckxzDto);
            PageHelper.offsetPage(ckxzDto.getStart() - 1, ckxzDto.getLimit());
            List<CkCkxz> ckCkxzs = ckxzMapper.selectByCkxzDto(ckxzDto);
            PageInfo<CkCkxz> usersPageInfo = new PageInfo<>(ckCkxzs);
            int total = (int) usersPageInfo.getTotal();
            log.debug("查询到的数据总数为：{}, ckxzDto查询的信息为: {}", total, ckCkxzs);
            return ckxzListXml(ckCkxzs, total);
        }
        log.info("ckxzDto为null");
        return null;
    }


    /**
     * 批量删除
     *
     * @param bdhms 表单号码
     * @return 删除的数量
     */
    @Override
    @Transactional
    public int batchDel(String bdhms) {
        if (bdhms != null && !"".equals(bdhms)) {
            log.warn("正在删除查控协执信息： 表单号码是： {}", bdhms);
            return ckxzMapper.batchDel(bdhms);
        }
        return 0;
    }


    /**
     * 根据表单号码查询协执信息
     *
     * @param bdhm 表单号码
     * @return 查控协执对象，找不到数据就返回null
     */
    @Override
    public CkCkxz viewCkxzInfo(String bdhm) {
        if (bdhm != null && !"".equals(bdhm)) {
            CkCkxz ckxz = ckxzMapper.selectByBdhm(bdhm);
            if (ckxz != null) {
                return ckxz;
            } else {
                return null;
            }
        }
        return null;
    }


    /**
     * 用户显示到主页的表格
     *
     * @param list  需要显示到表格的List集合
     * @param count 需要显示的数据的数量
     * @return String拼接的xml
     */
    private String ckxzListXml(List<CkCkxz> list, int count) {
        StringBuilder allCkxzXml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        if (list != null && count > 0) {
            log.debug("正在构建表格的xml的String！");
            allCkxzXml.append("<rows>");
            allCkxzXml.append("<userdata name='totalnumber'><![CDATA[").append(count).append("]]></userdata>");
            for (CkCkxz ckxz : list) {
                allCkxzXml.append("<row id=\"").append(ckxz.getBdhm()).append("\">");
                allCkxzXml.append("<userdata name='zt'><![CDATA[").append(ckxz.getZt()).append("]]></userdata>");
                allCkxzXml.append("<cell>0</cell>");

                List<TsDm> ckzts = Caches.CKZT_MAP.get("ckzts");
                for (TsDm dm : ckzts) {
                    if (ckxz.getZt().equals(dm.getCode())) {
                        allCkxzXml.append("<cell><![CDATA[").append(dm.getMc()).append("]]></cell>");
                        break;
                    }
                }

                if (10 < Integer.parseInt(ckxz.getZt())) {
                    allCkxzXml.append("<cell><![CDATA[").append("/sp/resources/static/v2/static/tdh/btn/images/blue/search.png^查看^javascript:view(\"")
                            .append(ckxz.getBdhm()).append("\")^_self").append("]]></cell>");
                } else {
                    allCkxzXml.append("<cell></cell>");
                }

                if ("10".equals(ckxz.getZt())) {
                    allCkxzXml.append("<cell><![CDATA[").append("/sp/resources/static/v2/static/tdh/btn/images/blue/edit.png^编辑^javascript:edit(\"")
                            .append(ckxz.getBdhm()).append("\")^_self").append("]]></cell>");
                } else {
                    allCkxzXml.append("<cell></cell>");
                }

                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getAh()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getMc()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getZjhm()).append("]]></cell>");

                String xzdwdm = ckxz.getXzdwdm();

                allCkxzXml.append("<cell><![CDATA[").append(Caches.CKXZDW_MAP.get(xzdwdm).getMc()).append("]]></cell>");

                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getCbr()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getSjy()).append("]]></cell>");

                allCkxzXml.append("</row>");
            }
            allCkxzXml.append("</rows>");
            log.debug("构建表格xml的String完成！");
        } else {
            //没数据
            log.debug("表格中没有信息！");
            allCkxzXml.append("<rows><userdata name='totalnumber'>0</userdata></rows>");
        }
        return allCkxzXml.toString();
    }
}
