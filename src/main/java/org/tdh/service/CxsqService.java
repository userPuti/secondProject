package org.tdh.service;

import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkJz;
import org.tdh.dto.CxsqDto;
import org.tdh.dto.HomePageDto;

import java.util.List;

/**
 * @author Puti
 * @date 2022/5/24 17:34
 */
public interface CxsqService {
    /**
     * ckckxz表中的所有信息
     *
     * @return 所有信息拼接的xml的String
     */
    String showCksqInfo(HomePageDto homePageDto);

    /**
     * 批量删除
     *
     * @param cklsh 表单号码
     * @return 删除的数量
     */
    int batchDel(String[] cklsh);

    /**
     * 插入一条查控协执信息
     *
     * @param cxsqDto
     * @return 插入成功返回true，失败返回false
     */
    boolean insertCksq(CxsqDto cxsqDto);


    /**
     * 根据登记批次查询查控对象信息
     *
     * @param djpc 登记批次
     * @return 返回查询到的同一批次的查控对象
     */
    List<CkCkdx> viewCkdxInfo(String djpc);


    /**
     * 根据登记批次获取协执单位代码信息
     *
     * @param djpc 登记批次
     * @return 协执单位代码信息，没有返回null
     */
    List<String> getXzdwdm(String djpc);

    /**
     * 根据登记批次获取协执说明信息
     *
     * @param djpc 登记批次
     * @return 获取协执说明，没有则返回null
     */
    String getXzsm(String djpc);

    /**
     * 根据登记批次查询查控卷宗信息
     *
     * @param djpc 登记批次
     * @return 根据登记批次查询到的卷宗对象信息，没有返回null
     */
    List<CkJz> getCkJz(String djpc);

    /**
     * 更新查询申请信息
     *
     * @param cxsqDto 查控协执参数
     * @param xhs     数据库需要删除的文件的序号
     * @return 更新成功返回true，否则返回false
     */
    boolean updateSqInfo(CxsqDto cxsqDto, List<Integer> xhs);

    /**
     * 根据登记批次查询当前批次最大的序号
     *
     * @param djpc 登记批次
     * @return 当前批次的最大序号值
     */
    int getMaxXh(String djpc);

    /**
     * 发送查询申请
     *
     * @param djpc 登记批次
     * @return 成功返回true，失败返回false
     */
    boolean sendCxsq(String djpc);

}
