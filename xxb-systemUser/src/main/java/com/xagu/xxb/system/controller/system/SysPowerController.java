package com.xagu.xxb.system.controller.system;

import com.xagu.xxb.common.constant.MessageConstants;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.common.web.domain.ResuTree;
import com.xagu.xxb.system.domain.SysPower;
import com.xagu.xxb.system.service.SysPowerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("system/power")
public class SysPowerController extends BaseController {

    /**
     * Describe: 基础路径
     * */
    private static String MODULE_PATH = "system/power/";

    /**
     * Describe: 权限模块服务
     * */
    @Resource
    private SysPowerService sysPowerService;

    /**
     * Describe: 获取权限列表视图
     * Param ModelAndView
     * Return 权限列表视图
     * */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/power/select','sys:power:select')")
    public ModelAndView main(ModelAndView modelAndView){
        modelAndView.setViewName(MODULE_PATH + "main");
        return modelAndView;
    }

    /**
     * Describe: 获取权限列表数据
     * Param ModelAndView
     * Return 权限列表数据
     * */
    @GetMapping
    @PreAuthorize("hasPermission('/system/power/select','sys:power:select')")
    public ResuTable data(){
        return treeTable(sysPowerService.list());
    }

    /**
     * Describe: 获取权限新增视图
     * Param ModelAndView
     * Return 权限新增视图
     * */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/power/insert','sys:power:insert')")
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName(MODULE_PATH + "add");
        return modelAndView;
    }

    /**
     * Describe: 获取权限修改视图
     * Param ModelAndView
     * Return 权限修改视图
     * */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/power/update','sys:power:update')")
    public ModelAndView edit(ModelAndView modelAndView,@NotEmpty(message = "权限Id不能为空") String powerId){
        modelAndView.addObject("sysPower",sysPowerService.getById(powerId));
        modelAndView.setViewName(MODULE_PATH + "edit");
        return modelAndView;
    }

    /**
     * Describe: 保存权限信息
     * Param: SysPower
     * Return: ResuBean
     * */
    @PostMapping
    @PreAuthorize("hasPermission('/system/power/insert','sys:power:insert')")
    public ResuBean save(@RequestBody @Validated(value = SysPower.Insert.class) SysPower sysPower){
        sysPower.setPowerId(""+new SnowFlake().nextId());
        boolean result = sysPowerService.save(sysPower);
        return decide(
                result,
                MessageConstants.SAVE_SUCCESS,
                MessageConstants.SAVE_FAILURE
        );
    }

    /**
     * Describe: 修改权限信息
     * Param SysPower
     * Return 执行结果
     * */
    @PutMapping
    @PreAuthorize("hasPermission('/system/power/update','sys:power:update')")
    public ResuBean update(@RequestBody @Validated(value = SysPower.Update.class) SysPower sysPower){
        boolean result = sysPowerService.update(sysPower);
        return decide(
                result,                           // 响应结果
                MessageConstants.UPDATE_SUCCESS,   // 成功消息
                MessageConstants.UPDATE_FAILURE    // 失败消息
        );
    }

    /**
     * Describe: 根据 id 进行删除
     * Param id
     * Return ResuTree
     * */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasPermission('/system/power/delete','sys:power:delete')")
    public ResuBean remove(@PathVariable @NotNull(message = "删除得用户不能为空") String ids){
        boolean result = sysPowerService.batchRemove(ids.split(","));
        return decide(
                result,                           // 响应结果
                MessageConstants.REMOVE_SUCCESS,   // 成功消息
                MessageConstants.REMOVE_FAILURE    // 失败消息
        );
    }

    /**
     * Describe: 获取父级权限选择数据
     * Param sysPower
     * Return ResuTree
     * */
    @GetMapping("/parent")
    @PreAuthorize("hasPermission('/system/power/select','sys:power:select')")
    public ResuTree selectParent(){
        List<SysPower> list = sysPowerService.list();
        SysPower basePower = new SysPower();
        basePower.setPowerName("顶级权限");
        basePower.setPowerId("0");
        basePower.setParentId("-1");
        list.add(basePower);
        return dataTree(list);
    }

}
