package org.tdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.cache.CkxzdwCache;
import org.tdh.cache.TsDmCache;
import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkCkxz;
import org.tdh.domain.CkJz;
import org.tdh.domain.TsDm;
import org.tdh.dto.CxsqDto;
import org.tdh.dto.HomePageDto;
import org.tdh.mapper.CkCkdxMapper;
import org.tdh.mapper.CkCkxzMapper;
import org.tdh.mapper.CkJzMapper;
import org.tdh.service.CkxzService;

import java.util.*;

/**
 * @author Puti
 * @date 2022/5/24 17:34
 */
@Service
public class CkxzServiceImpl implements CkxzService {
    private Logger log = LoggerFactory.getLogger(CkxzServiceImpl.class);

    @Autowired
    private CkCkdxMapper ckdxMapper;

    @Autowired
    private CkCkxzMapper ckxzMapper;

    @Autowired
    private CkJzMapper ckJzMapper;


    /**
     * 插入一条查控协执信息,在插入查控协执单位的同时，插入查控对象
     *
     * @param cxsqDto
     * @return 插入成功返回true，失败返回false
     */
    @Override
    @Transactional
    public boolean insertCksq(CxsqDto cxsqDto) {
        if (cxsqDto != null) {
            Map<String, CkCkdx> ckdxMap = cxsqDto.getCkCkdxMap();
            List<CkCkdx> ckdxes = new ArrayList<>();
            for (Map.Entry<String, CkCkdx> ckdxEntry : ckdxMap.entrySet()) {
                ckdxes.add(ckdxEntry.getValue());
            }

            String djpc = cxsqDto.getDjpc();
            String[] xzdwdms = cxsqDto.getXzdwdm().split(",");
            String xzsm = cxsqDto.getXzsm();
            List<CkJz> files = cxsqDto.getFiles();

            if (!insertCkdx(ckdxes, djpc) || !insertCkxz(djpc,ckdxes,xzdwdms,xzsm) || !insertCkjz(files,djpc)) {
                return false;
            }

            return true;
        }
        return false;
    }


    /**
     * @param djpc
     * @param ckdxes
     * @param xzdwdms
     * @param xzsm
     * @return
     */
    private boolean insertCkxz(String djpc, List<CkCkdx> ckdxes, String[] xzdwdms, String xzsm) {

        for (CkCkdx ckdx : ckdxes) {
            CkCkxz ckxz = new CkCkxz();
            ckxz.setCklsh(ckdx.getCklsh());
            ckxz.setZt("10");
            ckxz.setXzlb("1");
            ckxz.setAh(ckdx.getAh());
            ckxz.setZjhm(ckdx.getZjhm());
            ckxz.setZjlx(ckdx.getZjlx());
            ckxz.setMc(ckdx.getMc());
            ckxz.setGj(ckdx.getGj());
            ckxz.setHjszd(ckdx.getHjszd());
            ckxz.setCbr(ckdx.getCbr());
            ckxz.setSjy(ckdx.getSjy());
            ckxz.setXzsm(xzsm);
            ckxz.setFydm("");
            ckxz.setSsdw(ckdx.getSsdw());
            ckxz.setDjpc(djpc);
            ckxz.setLastupdate(new Date());

            for (String xzdwdm : xzdwdms) {
                String bdhm = getUUID();
                ckxz.setBdhm(bdhm);
                ckxz.setXzdwdm(xzdwdm);
                ckxz.setXzdwfl(CkxzdwCache.XZDWDM_XZDW_MAP.get(xzdwdm).getXzdwfl());
                ckxz.setDjpc(djpc);
                int insertCount = ckxzMapper.insertSelective(ckxz);
                if (1 != insertCount) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * 插入卷宗信息
     *
     * @param ckjzs 查控卷宗集合
     * @param djpc  登记批次
     * @return 插入是否成功
     */
    private boolean insertCkjz(List<CkJz> ckjzs, String djpc) {
        if (ckjzs != null) {
            int xh = 1;
            for (CkJz ckjz : ckjzs) {
                ckjz.setXh(xh++);
                ckjz.setLastupdate(new Date());
                if (1 != ckJzMapper.insertSelective(ckjz)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ckckxz表中的所有信息
     *
     * @return 所有信息拼接的xml的String
     */
    @Override
    public String CkckxzInfo(HomePageDto homePageDto) {
        if (homePageDto != null) {
            log.debug("根据ckxzDto来查询信息： {}", homePageDto);
            PageHelper.offsetPage(homePageDto.getStart() - 1, homePageDto.getLimit());
            List<CkCkxz> ckCkxzs = ckxzMapper.selectByCkxzDto(homePageDto);
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
    public int batchDel(String[] bdhms) {
        if (bdhms != null && !"".equals(bdhms)) {
            log.warn("正在删除查控协执信息： 表单号码是： {}", bdhms);
            return ckxzMapper.batchDel(bdhms);
        }
        return 0;
    }


    /**
     * 插入一条查控协执对象
     *
     * @param ckdxes 查控协执对象
     * @return 是否插入成功
     */
    private boolean insertCkdx(List<CkCkdx> ckdxes, String djpc) {
        if (ckdxes != null) {
            for (CkCkdx ckdx : ckdxes) {
                log.debug("正在插入一条查控对象数据！：{}", ckdx);
                ckdx.setLastupdate(new Date());
                ckdx.setZt("10");
                ckdx.setDjpc(djpc);
                int count = ckdxMapper.insertSelective(ckdx);
                if (1 == count) {
                    log.debug("插入一条查控对象数据成功!");
                    return true;
                } else {
                    log.info("插入查控对象数据出现了问题！");
                    return false;
                }
            }
        }
        log.info("需要插入的查控对象为空，不能插入！");
        return false;
    }


    /**
     * 根据登记批次查询查控对象信息
     *
     * @param djpc 登记批次
     * @return 返回查询到的同一批次的查控对象
     */
    @Override
    public List<CkCkdx> viewCkdxInfo(String djpc) {
        if (djpc != null && !"".equals(djpc)) {
            List<CkCkdx> ckCkdxes = ckdxMapper.selectAllByDjpc(djpc);
            if (ckCkdxes != null) {
                return ckCkdxes;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 根据登记批次获取协执单位代码信息
     *
     * @param djpc 登记批次
     * @return 协执单位代码信息，没有返回null
     */
    @Override
    public List<String> getXzdwdm(String djpc) {
        if (djpc != null && !"".equals(djpc)) {
            List<String> xzdwdms = ckxzMapper.selectXzdwdmByDjpc(djpc);

            if (xzdwdms != null) {
                return xzdwdms;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 根据登记批次获取协执说明信息
     *
     * @param djpc 登记批次
     * @return 获取协执说明，没有则返回null
     */
    @Override
    public String getXzsm(String djpc) {
        if (djpc != null && !"".equals(djpc)) {
            String xzsm = ckxzMapper.selectXzsmByDjpc(djpc);
            if (xzsm != null) {
                return xzsm;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 根据登记批次查询查控卷宗信息
     *
     * @param djpc 登记批次
     * @return 根据登记批次查询到的卷宗对象信息，没有返回null
     */
    @Override
    @Transactional
    public List<CkJz> getCkJz(String djpc) {
        if (djpc != null && !"".equals(djpc)) {
            List<CkJz> ckJzs = ckJzMapper.selectAllByDjpc(djpc);

            if (ckJzs != null) {
                return ckJzs;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新查询申请信息
     *
     * @param cxsqDto 查控协执参数
     * @return 更新成功返回true，否则返回false
     */
    @Override
    public boolean updateSqInfo(CxsqDto cxsqDto) {
        if (cxsqDto != null) {
            String djpc = cxsqDto.getDjpc();
            if (deleteCkxzInfo(djpc)) {
                List<CkCkdx> ckdxes = (List<CkCkdx>) cxsqDto.getCkCkdxMap().values();

                for (CkCkdx ckdx : ckdxes) {
                    if (1 != ckdxMapper.updateCkdxByCklsh(ckdx)) {
                        return false;
                    }
                }

                String[] xzdwdms = cxsqDto.getXzdwdm().split(",");

                CkCkxz ckxz = new CkCkxz();
                ckxz.setDjpc(djpc);


                for (String xzdwdm : xzdwdms) {
                    String bdhm = getUUID();
                    ckxz.setBdhm(bdhm);
                    ckxz.setXzdwdm(xzdwdm);
                    ckxz.setXzdwfl(CkxzdwCache.XZDWDM_XZDW_MAP.get(xzdwdm).getXzdwfl());
                    ckxz.setDjpc(djpc);
                    int insertCount = ckxzMapper.insertSelective(ckxz);
                    if (1 != insertCount) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 根据登记批次删除所有此登记批次的信息
     *
     * @param djpc 登记批次
     * @return 是否删除成功
     */
    public boolean deleteCkxzInfo(String djpc) {
        if (djpc != null && !"".equals(djpc)) {
            int xzdel = ckxzMapper.deleteByDjpc(djpc);

            if (0 != xzdel) {
                return true;
            } else {
                return false;
            }
        }
        return false;
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

                List<TsDm> ckzts = TsDmCache.KIND_TSDM_MAP.get("CKZT");
                for (TsDm dm : ckzts) {
                    if (ckxz.getZt().equals(dm.getCode())) {
                        allCkxzXml.append("<cell><![CDATA[").append(dm.getMc()).append("]]></cell>");
                        break;
                    }
                }

                if (10 < Integer.parseInt(ckxz.getZt())) {
                    allCkxzXml.append("<cell><![CDATA[").append("/sp/resources/static/v2/static/tdh/btn/images/blue/search.png^查看^javascript:view(\"")
                            .append(ckxz.getDjpc()).append("\")^_self").append("]]></cell>");
                } else {
                    allCkxzXml.append("<cell></cell>");
                }

                if ("10".equals(ckxz.getZt())) {
                    allCkxzXml.append("<cell><![CDATA[").append("/sp/resources/static/v2/static/tdh/btn/images/blue/edit.png^编辑^javascript:edit(\"")
                            .append(ckxz.getDjpc()).append("\")^_self").append("]]></cell>");
                } else {
                    allCkxzXml.append("<cell></cell>");
                }

                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getAh()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getMc()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckxz.getZjhm()).append("]]></cell>");

                String xzdwdm = ckxz.getXzdwdm();

                allCkxzXml.append("<cell><![CDATA[").append(CkxzdwCache.XZDWDM_XZDW_MAP.get(xzdwdm).getMc()).append("]]></cell>");

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


    /**
     * 获取一个32位的uuid
     *
     * @return 32位的uuid
     */
    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
