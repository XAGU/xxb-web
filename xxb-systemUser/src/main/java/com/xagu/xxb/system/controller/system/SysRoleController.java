package com.xagu.xxb.system.controller.system;

import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.constant.MessageConstants;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.common.web.domain.ResuTree;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.SysRole;
import com.xagu.xxb.system.domain.SysRolePower;
import com.xagu.xxb.system.service.SysRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

/**
 * @author xagu
 */
@RestController
@RequestMapping("system/role")
@Validated
public class SysRoleController extends BaseController {

    /**
     * Describe: 基础路径
     * */
    private static String MODULE_PATH = "system/role/";

    /**
     * Describe: 角色模块服务
     * */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * Describe: 获取角色列表视图
     * Param ModelAndView
     * Return 用户列表视图
     * */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/role/select','sys:role:select')")
    public ModelAndView main(ModelAndView modelAndView){
        modelAndView.setViewName(MODULE_PATH + "main");
        return modelAndView;
    }

    /**
     * Describe: 获取角色列表数据
     * Param SysRole PageDomain
     * Return 角色列表数据
     * */
    @GetMapping
    @PreAuthorize("hasPermission('/system/role/select','sys:role:select')")
    public ResuTable data(PageDomain pageDomain,SysRole sysRole){
       PageInfo<SysRole> pageInfo = sysRoleService.pageSelect(sysRole,pageDomain);
       return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * Describe: 获取角色新增视图
     * Param ModelAndView
     * Return 角色新增视图
     * */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/role/insert','sys:role:insert')")
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName(MODULE_PATH + "add");
        return modelAndView;
    }

    /**
     * Describe: 保存角色信息
     * Param SysRole
     * Return 执行结果
     * */
    @PostMapping
    @PreAuthorize("hasPermission('/system/role/insert','sys:role:insert')")
    public ResuBean save(@RequestBody @Validated(value = SysRole.Insert.class) SysRole sysRole){
        sysRole.setRoleId("" + new SnowFlake().nextId());
        boolean result = sysRoleService.save(sysRole);
        return decide(
                result,                           // 响应结果
                MessageConstants.SAVE_SUCCESS,     // 成功消息
                MessageConstants.SAVE_FAILURE      // 失败消息
        );
    }

    /**
     * Describe: 获取角色修改视图
     * Param ModelAndView
     * Return 角色修改视图
     * */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/role/update','sys:role:update')")
    public ModelAndView edit(ModelAndView modelAndView,@Validated @NotEmpty(message = "角色Id不能为空") String roleId){
        modelAndView.addObject("sysRole",sysRoleService.getById(roleId));
        modelAndView.setViewName(MODULE_PATH + "edit");
        return modelAndView;
    }

    /**
     * Describe: 修改角色信息
     * Param SysRole
     * Return 执行结果
     * */
    @PutMapping
    @PreAuthorize("hasPermission('/system/role/update','sys:role:update')")
    public ResuBean update(@RequestBody @Validated(value = SysRole.Update.class) SysRole sysRole){

       boolean result = sysRoleService.update(sysRole);
        return decide(
                result,                           // 响应结果
                MessageConstants.UPDATE_SUCCESS,   // 成功消息
                MessageConstants.UPDATE_FAILURE    // 失败消息
        );
    }

    /**
     * Describe: 获取角色授权视图
     * Param ModelAndView
     * Return ModelAndView
     * */
    @GetMapping("power")
    @PreAuthorize("hasPermission('/system/role/update','sys:role:update')")
    public ModelAndView power(ModelAndView modelAndView,@NotEmpty(message = "角色Id不能为空") String roleId){
        modelAndView.setViewName(MODULE_PATH + "power");
        modelAndView.addObject("roleId",roleId);
        return modelAndView;
    }

    /**
     * Describe: 保存角色权限
     * Param RoleId PowerIds
     * Return ResuBean
     * */
    @PutMapping("rolePower")
    @PreAuthorize("hasPermission('/system/role/update','sys:role:update')")
    public ResuBean saveRolePower(@RequestBody SysRolePower sysRolePower){
        boolean result = sysRoleService.saveRolePower(sysRolePower.getRoleId(), Arrays.asList(sysRolePower.getPowerId().split(",")));
        return decide(
                result,                           // 响应结果
                MessageConstants.SAVE_SUCCESS,     // 成功消息
                MessageConstants.SAVE_FAILURE      // 失败消息
        );
    }

    /**
     * Describe: 获取角色权限
     * Param RoleId
     * Return ResuTree
     * */
    @GetMapping("rolePower/{roleId}")
    @PreAuthorize("hasPermission('/system/role/select','sys:role:select')")
    public ResuTree getRolePower(@PathVariable("roleId") @NotEmpty(message = "角色Id不能为空") String roleId){
        return dataTree(sysRoleService.getRolePower(roleId));
    }

    /**
     * Describe: 用户删除接口
     * Param: id
     * Return: ResuBean
     * */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasPermission('/system/role/delete','sys:role:delete')")
    public ResuBean batchRemove(@PathVariable @NotEmpty(message = "角色Id不能为空") String ids){
        boolean result  = sysRoleService.batchRemove(ids.split(","));
        return decide(
                result,
                MessageConstants.REMOVE_SUCCESS,
                MessageConstants.REMOVE_FAILURE
        );
    }


}
