package com.xagu.xxb.system.controller.system;

import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.constant.MessageConstants;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuMenu;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.system.service.SysPowerService;
import com.xagu.xxb.system.service.SysRoleService;
import com.xagu.xxb.system.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Describe: 用户控制器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@RestController
@RequestMapping("system/user")
@Validated
public class SysUserController extends BaseController {

    /**
     * Describe: 基础路径
     * */
    private static String MODULE_PATH = "system/user/";

    /**
     * Describe: 用户模块服务
     * */
    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    /**
     * Describe: 获取用户列表视图
     * Param ModelAndView
     * Return 用户列表视图
     * */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/user/select','sys:user:select')")
    public ModelAndView main(ModelAndView modelAndView){
        modelAndView.setViewName(MODULE_PATH + "main");
        return modelAndView;
    }

    /**
     * Describe: 获取用户列表数据
     * Param ModelAndView
     * Return 用户列表数据
     * */
    @GetMapping
    @PreAuthorize("hasPermission('/system/user/select','sys:user:select')")
    public ResuTable data(PageDomain pageDomain){

        PageInfo<SysUser> pageInfo = sysUserService.pageSelect(pageDomain);

        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * Describe: 用户新增视图
     * Param ModelAndView
     * Return 返回用户新增视图
     * */
    @GetMapping("/add")
    @PreAuthorize("hasPermission('/system/user/insert','sys:user:insert')")
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.addObject("sysRoles",sysRoleService.selectAll(null));
        modelAndView.setViewName(MODULE_PATH+"add");
        return modelAndView;
    }

    /**
     * Describe: 用户新增接口
     * Param ModelAndView
     * Return 操作结果
     * */
    @PostMapping
    @PreAuthorize("hasPermission('/system/user/insert','sys:user:insert')")
    public ResuBean save(@RequestBody @Validated(value = SysUser.Insert.class) SysUser sysUser){
        sysUser.setLogin("0");
        sysUser.setUserId("" + new SnowFlake().nextId());
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUserService.save(sysUser);
        Boolean result = sysUserService.saveUserRole(sysUser.getUserId(), Arrays.asList(sysUser.getRoleIds().split(",")));
        return decide(
                result,                            // 响应结果
                MessageConstants.SAVE_SUCCESS,     // 成功消息
                MessageConstants.SAVE_FAILURE      // 失败消息
        );
    }

    /**
     * Describe: 用户修改视图
     * Param ModelAndView
     * Return 返回用户修改视图
     * */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/user/update','sys:user:update')")
    public  ModelAndView edit(ModelAndView modelAndView,@NotEmpty(message = "用户Id不能为空") String userId){
        modelAndView.addObject("sysRoles",sysUserService.getUserRole(userId));
        modelAndView.addObject("sysUser",sysUserService.getById(userId));
        modelAndView.setViewName(MODULE_PATH + "edit");
        return modelAndView;
    }

    /**
     * Describe: 用户修改接口
     * Param ModelAndView
     * Return 返回用户修改接口
     * */
    @PutMapping
    @PreAuthorize("hasPermission('/system/user/update','sys:user:update')")
    public ResuBean update(@RequestBody @Validated(value = SysUser.Update.class) SysUser sysUser){
        String roleIds = sysUser.getRoleIds();
        if (roleIds != null) {
            sysUserService.saveUserRole(sysUser.getUserId(), Arrays.asList(roleIds.split(",")));
        }
        boolean result = sysUserService.update(sysUser);
        return decide(
                result,                            // 响应结果
                MessageConstants.UPDATE_SUCCESS,    // 成功消息
                MessageConstants.UPDATE_FAILURE     // 失败消息
        );
    }

    /**
     * Describe: 用户批量删除接口
     * Param: ids
     * Return: ResuBean
     * */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasPermission('/system/user/delete','sys:user:delete')")
    public ResuBean batchRemove(@PathVariable @NotEmpty(message = "删除的Id不能为空") String ids){
        boolean result = sysUserService.batchRemove(ids.split(","));
        return decide(
                result,                            // 响应结果
                MessageConstants.REMOVE_SUCCESS,    // 成功消息
                MessageConstants.REMOVE_FAILURE     // 失败消息
        );
    }

    /**
     * Describe: 根据 username 获取菜单数据
     * Param SysRole
     * Return 执行结果
     * */
    /**
     * 获取当前登录用户所拥有的目录权限
     * @return
     */
    @GetMapping("/menus")
    public List<ResuMenu> getLoginUserMenus(){
        return sysUserService.selectLoginUserMenus();
    }

}
