package com.xagu.xxb.common.web.base;

import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.common.web.domain.ResuTree;

public class BaseController {

    /**
     * Describe: 返回数据表格数据
     * Param data
     * Return 表格数据
     * */
    protected  static ResuTable dataTable(Object data){
        ResuTable resuTable = new ResuTable();
        resuTable.setCode(0);
        resuTable.setMsg("...");
        resuTable.setCount(0L);
        resuTable.setData(data);
        return resuTable;
    }

    /**
     * Describe: 返回 Tree 数据
     * Param data
     * Return Tree数据
     * */
    protected  static ResuTree dataTree(Object data){
         ResuTree resuTree = new ResuTree();
         resuTree.setData(data);
         return resuTree;
    }

    /**
     * Describe: 返回数据表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResuTable pageTable(Object data,long count){
        ResuTable resuTable = new ResuTable();
        resuTable.setData(data);
        resuTable.setCount(count);
        resuTable.setCode(0);
        resuTable.setMsg("...");
        return resuTable;
    }

    /**
     * Describe: 返回树状表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResuTable treeTable(Object data){
        ResuTable resuTable = new ResuTable();
        resuTable.setData(data);
        resuTable.setCode(0);
        resuTable.setMsg("...");
        return resuTable;
    }

    /**
     * Describe: 返回 ajax 接收成功消息
     * Param msg
     * Return ResuBean
     * */
    public static ResuBean success(String msg){
        ResuBean resuBean = new ResuBean();
        resuBean.setCode(200);
        resuBean.setSuccess(true);
        resuBean.setMsg(msg);
        return resuBean;
    }

    /**
     * Describe: 返回 ajax 接收失败消息
     * Param msg
     * Return ResuBean
     * */
    public static ResuBean failure(String msg){
        ResuBean resuBean = new ResuBean();
        resuBean.setCode(500);
        resuBean.setSuccess(false);
        resuBean.setMsg(msg);
        return resuBean;
    }

    /**
     * Describe: 根据 Boolean 自主返回 Success Failure 封装
     * Param msg
     * Return ResuBean
     * */
    public static ResuBean  decide(Boolean result,String success,String failure){
        if(result){
            return success(success);
        }else{
            return failure(failure);
        }
    }

}
