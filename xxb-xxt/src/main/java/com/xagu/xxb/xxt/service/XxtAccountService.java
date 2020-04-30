package com.xagu.xxb.xxt.service;

import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.xxt.domain.XxtAccount;

import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/25
 * Email:xagu_qc@foxmail.com
 * Describe: 学习通账户
 */
public interface XxtAccountService {
    /**
     * 绑定学习通账户
     * @param account
     * @return
     */
    public boolean bindXxtAccount(XxtAccount account);

    /**
     * 查询当前用户绑定的账户
     * @return
     */
    public List<XxtAccount> selectAll(XxtAccount xxtAccount);

    /**
     * 分页查询所有绑定的账户
     * @param xxtAccount
     * @param pageDomain
     * @return
     */
    public PageInfo<XxtAccount> pageSelect(XxtAccount xxtAccount, PageDomain pageDomain);


    /**
     * 获取账户通过Id
     * @param id
     * @return
     */
    XxtAccount getById(String id);

    /**
     * Describe: 根据 id 修改绑定数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean update(XxtAccount xxtAccount);


    /**
     * 删除
     * @param ids
     * @return
     */
    boolean batchRemove(String[] ids);
}
