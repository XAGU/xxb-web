package com.xagu.xxb.xxt.mapper;
import org.apache.ibatis.annotations.Param;

import com.xagu.xxb.xxt.domain.XxtAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xagu
 */
@Mapper
public interface XxtAccountMapper {


    /**
     * 查询用户的所有学习通账户
     * @param userId
     * @return
     */
    List<XxtAccount> selectAccountByUserId(String userId,XxtAccount xxtAccount);


    /**
     * 通过accountId删除
     * @param accountId
     * @return
     */
    int deleteByAccountId(String accountId);

    /**
     * 插入或更新学习通账户
     * @param record
     * @return
     */
    int insertOrUpdate(XxtAccount record);

    /**
     * 插入或更新学习通账户，只插入有的字段
     * @param record
     * @return
     */
    int insertOrUpdateSelective(XxtAccount record);

    /**
     * 通过账户Id查询账户信息
     * @param accountId
     * @return
     */
    XxtAccount selectByAccountId(String accountId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByAccountIdSelective(XxtAccount record);

    /**
     * 更新，通过accountId
     * @param record
     * @return
     */
    int updateByAccountId(XxtAccount record);

    int deleteByAccountIds(List<String> accountIds);

    int batchRemove(String[] ids);
}