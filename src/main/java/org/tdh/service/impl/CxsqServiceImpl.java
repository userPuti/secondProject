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
import org.tdh.service.CxsqService;
import org.tdh.util.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Puti
 * @date 2022/5/24 17:34
 */
@Service
public class CxsqServiceImpl implements CxsqService {
    private Logger log = LoggerFactory.getLogger(CxsqServiceImpl.class);

    //查控对象对应的mapper
    @Autowired
    private CkCkdxMapper ckdxMapper;

    //查控协执对应的mapper
    @Autowired
    private CkCkxzMapper ckxzMapper;

    //查控卷宗对应的mappper
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

            if (!insertCkdx(ckdxes, xzdwdms, djpc) || !insertCkxz(djpc, ckdxes, xzdwdms, xzsm) || !insertCkjz(files, djpc)) {
                throw new RuntimeException("插入查控申请失败！");
            }

            return true;
        }
        return false;
    }


    /**
     * ckckxz表中的所有信息
     *
     * @return 所有信息拼接的xml的String
     */
    @Override
    public String showCksqInfo(HomePageDto homePageDto) {
        if (homePageDto != null) {
            log.debug("根据ckxzDto来查询信息： {}", homePageDto);
            PageHelper.offsetPage(homePageDto.getStart() - 1, homePageDto.getLimit());
            List<CkCkdx> ckdxes = ckdxMapper.selectAll(homePageDto);
            PageInfo<CkCkdx> usersPageInfo = new PageInfo<>(ckdxes);
            int total = (int) usersPageInfo.getTotal();
            log.debug("查询到的数据总数为：{}, ckxzDto查询的信息为: {}", total, ckdxes);

            return ckxzListXml(ckdxes, total);
        }
        log.info("ckxzDto为空");
        return "";
    }

    /**
     * 批量删除
     *
     * @param cklsh 表单号码
     * @return 删除的数量
     */
    @Override
    @Transactional
    public int batchDel(String[] cklsh) {
        if (cklsh != null && !"".equals(cklsh)) {
            log.warn("正在删除查控协执信息： 表单号码是： {}", cklsh);
            int batchDel = ckdxMapper.deleteByCklsh(cklsh);

            if (cklsh.length == batchDel) {
                return batchDel;
            } else {
                throw new RuntimeException("批量删除出现异常！");
            }
        }
        return 0;
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
    public List<CkJz> getCkJz(String djpc) {
        if (djpc != null && !"".equals(djpc)) {
            List<CkJz> ckJzs = ckJzMapper.selectAllByDjpc(djpc);

            for (CkJz ckJz : ckJzs) {
                try {
                    ckJz.setPath(URLEncoder.encode(ckJz.getPath(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

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
     * @param xhs     数据库需要删除的文件的序号
     * @return 更新成功返回true，否则返回false
     */
    @Override
    @Transactional
    public boolean updateSqInfo(CxsqDto cxsqDto, List<Integer> xhs) {
        if (cxsqDto != null) {
            String djpc = cxsqDto.getDjpc();
            String[] xzdwdms = cxsqDto.getXzdwdm().split(",");
            String xzsm = cxsqDto.getXzsm();
            List<CkJz> files = cxsqDto.getFiles();

            List<CkCkdx> ckdxes = new ArrayList<>();
            for (Map.Entry<String, CkCkdx> ckdxEntry : cxsqDto.getCkCkdxMap().entrySet()) {
                ckdxes.add(ckdxEntry.getValue());
            }

            //先提前查询出在数据库中的查控对象的查控流水号信息，给后面判断查控对象是否在数据库中
            List<String> cklshsInDB = ckdxMapper.selectCklshByDjpc(djpc);
            List<CkCkdx> insertedCkdx = new ArrayList<>();

            for (CkCkdx ckdx : ckdxes) {
                String tempCklsh = ckdx.getCklsh();
                if (cklshsInDB.contains(tempCklsh)) {
                    //这条数据在数据库中存在，那么直接更新这条数据的信息，然后将他从list中删除，留下来的是需要从数据库中删除的
                    if (1 != ckdxMapper.updateCkdxByCklsh(ckdx)) {
                        throw new RuntimeException("更新查控对象出现问题！");
                    }

                    Iterator<String> cklshIt = cklshsInDB.iterator();
                    while (cklshIt.hasNext()) {
                        String s = cklshIt.next();
                        if (s.equals(tempCklsh)) {
                            cklshIt.remove();
                        }
                    }
                } else {
                    //这条数据不在数据库中，需要插入一条新的数据
                    insertedCkdx.add(ckdx);
                }
            }

            //插入新添加的对象
            //todo 需要测试一下这里是否会出现一个插入出错，但是事务不会回滚的情况
            if (!insertCkdx(insertedCkdx, xzdwdms, djpc) || !insertCkxz(djpc, insertedCkdx, xzdwdms, xzsm) || !insertCkjz(files, djpc)) {
                throw new RuntimeException("插入查控对象的时候出现错误");
            }

            //如果查控流水号里面还有多余的值，那么表示这个是已经被删除掉的查控对象
            if (0 != cklshsInDB.size()) {
                int length = cklshsInDB.size();
                String[] cklshs = cklshsInDB.toArray(new String[length]);
                if (length != ckdxMapper.deleteByCklsh(cklshs)) {
                    throw new RuntimeException("删除多余的查控对象时出现了错误！");
                }
            }

            //先删除之前的查控协执信息，在重新插入新的查控协执信息
            if (deleteCkxzInfo(djpc)) {
                //插入新的查控协执信息
                if (!insertCkxz(djpc, ckdxes, xzdwdms, cxsqDto.getXzsm())) {
                    throw new RuntimeException("更新时插入查控协执出现问题！");
                }
            } else {
                throw new RuntimeException("删除查控协执信息出现问题！");
            }

            //如果被删除的需要不为空，则删除对应序号的文件
            if (!(0 == xhs.size())) {
                if (!deleteFile(djpc, xhs)) {
                    throw new RuntimeException("删除文件出现问题！");
                }
            }

            //插入新上传的文件信息
            if (!insertCkjz(files, djpc)) {
                throw new RuntimeException("插入查控卷宗出现问题！");
            }

        }
        return true;
    }

    /**
     * 根据登记批次查询当前批次最大的序号
     *
     * @param djpc 登记批次
     * @return 当前批次的最大序号值
     */
    @Override
    public int getMaxXh(String djpc) {
        if (null != djpc && !"".equals(djpc)) {
            Integer xh = ckJzMapper.selectMaxXhByDjpc(djpc);
            if (xh != null) {
                return xh;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * 发送查询申请
     *
     * @param djpc 登记批次
     * @return 成功返回true，失败返回false
     */
    @Override
    @Transactional
    public boolean sendCxsq(String djpc) {
        log.info("发送查询申请！");
        if (null != djpc && !"".equals(djpc)) {
            log.debug("发送的登记批次为：{}", djpc);

            int countCkxz = ckxzMapper.countByDjpc(djpc);

            //将查控协执中的状态设置为60
            if (countCkxz != ckxzMapper.updateZtByDjpc(djpc)) {
                log.error("查控协执更新状态失败！");
                throw new RuntimeException("发送失败！");
            }

            int countCkdx = ckdxMapper.countByDjpc(djpc);
            //将查控对象中的状态设置为60
            if (countCkdx != ckdxMapper.updateZtByDjpc(djpc)) {
                log.error("查控对象更新状态失败！");
                throw new RuntimeException("发送失败！");
            }
            return true;
        }
        log.info("发送申请的登记批次信息为空！");
        return false;
    }

    /**
     * 根据登记批次和序号删除文件信息
     *
     * @param djpc       登记批次
     * @param delFileXhs 需要删除的卷宗序号
     * @return 删除成功返回true，否则返回false
     */
    private boolean deleteFile(String djpc, List<Integer> delFileXhs) {
        if (null != djpc && !"".equals(djpc)) {
            //没有需要删除的文件，直接返回
            if (0 == delFileXhs.size()) {
                return true;
            }

            if (0 == ckJzMapper.deleteByXhAndDjpc(djpc, delFileXhs)) {
                throw new RuntimeException();
            }
        }
        return true;
    }


    /**
     * 根据登记批次删除所有此登记批次的信息
     *
     * @param djpc 登记批次
     * @return 是否删除成功
     */
    private boolean deleteCkxzInfo(String djpc) {
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
     * 插入一条查控协执信息
     *
     * @param djpc    登记批次
     * @param ckdxes  查控对象
     * @param xzdwdms 协执单位
     * @param xzsm    协执说明
     * @return 插入成功返回true，否则返回false
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
                String bdhm = Utils.getUUID();
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
            for (CkJz ckjz : ckjzs) {
                ckjz.setLastupdate(new Date());

                if (1 != ckJzMapper.insertSelective(ckjz)) {
                    return false;
                }

            }
        }
        return true;
    }


    /**
     * 插入一条查控协执对象
     *
     * @param ckdxes 查控协执对象
     * @return 是否插入成功
     */
    private boolean insertCkdx(List<CkCkdx> ckdxes, String[] xzdwdms, String djpc) {
        if (ckdxes != null) {
            for (CkCkdx ckdx : ckdxes) {
                log.debug("正在插入一条查控对象数据！：{}", ckdx);
                ckdx.setLastupdate(new Date());
                ckdx.setZt("10");
                ckdx.setDjpc(djpc);

                List<String> xzdwflList = new ArrayList<>();
                if (xzdwflList != null && xzdwdms.length > 0) {
                    for (String xzdwdm : xzdwdms) {
                        xzdwflList.add(CkxzdwCache.XZDWDM_XZDW_MAP.get(xzdwdm).getXzdwfl());
                    }
                    xzdwflList = removeDuplicationByHashSet(xzdwflList);
                    String ckfw = String.join(",", xzdwflList);
                    ckdx.setCkfw(ckfw);
                } else {
                    xzdwflList = null;
                }

                int count = ckdxMapper.insertSelective(ckdx);
                if (0 == count) {
                    log.info("插入查控对象数据出现了问题！");
                    return false;
                }
            }
            return true;
        } else {
            log.info("需要插入的查控对象为空，不能插入！");
            return false;
        }
    }


    /**
     * 用户显示到主页的表格
     *
     * @param ckdxes 需要显示到表格的List集合
     * @param count  需要显示的数据的数量
     * @return String拼接的xml
     */
    private String ckxzListXml(List<CkCkdx> ckdxes, int count) {
        StringBuilder allCkxzXml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        if (ckdxes != null && count > 0) {
            log.debug("正在构建表格的！");
            allCkxzXml.append("<rows>");
            allCkxzXml.append("<userdata name='totalnumber'><![CDATA[").append(count).append("]]></userdata>");

            for (CkCkdx ckdx : ckdxes) {
                allCkxzXml.append("<row id=\"").append(ckdx.getCklsh()).append("\">");
                allCkxzXml.append("<userdata name='zt'><![CDATA[").append(ckdx.getZt()).append("]]></userdata>");
                allCkxzXml.append("<cell>0</cell>");

                List<TsDm> ckzts = TsDmCache.KIND_TSDM_MAP.get("CKZT");
                for (TsDm dm : ckzts) {
                    if (ckdx.getZt().equals(dm.getCode())) {
                        allCkxzXml.append("<cell><![CDATA[").append(dm.getMc()).append("]]></cell>");
                        break;
                    }
                }

                if (10 < Integer.parseInt(ckdx.getZt())) {
                    allCkxzXml.append("<cell><![CDATA[").append("/sp/resources/static/v2/static/tdh/btn/images/blue/search.png^查看^javascript:view(\"")
                            .append(ckdx.getDjpc()).append("\")^_self").append("]]></cell>");
                } else {
                    allCkxzXml.append("<cell></cell>");
                }

                if ("10".equals(ckdx.getZt())) {
                    allCkxzXml.append("<cell><![CDATA[").append("/sp/resources/static/v2/static/tdh/btn/images/blue/edit.png^编辑^javascript:edit(\"")
                            .append(ckdx.getDjpc()).append("\")^_self").append("]]></cell>");
                } else {
                    allCkxzXml.append("<cell></cell>");
                }

                allCkxzXml.append("<cell><![CDATA[").append(ckdx.getAh()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckdx.getMc()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckdx.getZjhm()).append("]]></cell>");


                String tempCkfw = ckdx.getCkfw();
                String ckfwmc = "";

                if (tempCkfw != null && tempCkfw.length() > 0) {
                    String[] ckfws = tempCkfw.split(",");
                    if (ckfws.length > 0) {
                        for (String ckfw : ckfws) {
                            ckfwmc += TsDmCache.CODE_BZ_MAP.get(ckfw) + ",";
                        }
                    }
                    ckfwmc = ckfwmc.substring(0, ckfwmc.length() - 1);
                }

                allCkxzXml.append("<cell><![CDATA[").append(ckfwmc).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckdx.getCbr()).append("]]></cell>");
                allCkxzXml.append("<cell><![CDATA[").append(ckdx.getSjy()).append("]]></cell>");
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
     * 使用HashSet实现List去重(无序)
     *
     * @param list
     */
    private List removeDuplicationByHashSet(List<String> list) {
        HashSet set = new HashSet(list);
        //把List集合所有元素清空
        list.clear();
        //把HashSet对象添加至List集合
        list.addAll(set);
        return list;
    }
}
