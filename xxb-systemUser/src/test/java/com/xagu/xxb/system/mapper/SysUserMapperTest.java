package com.xagu.xxb.system.mapper;

import com.xagu.xxb.system.domain.SysUserAndPowers;
import com.xagu.xxb.system.domain.SysUserJoinSysRole;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/18
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public class SysUserMapperTest {
    private static SysUserMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(SysUserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/SysUserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(SysUserMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectUserAndRoles() throws FileNotFoundException {
        List<SysUserJoinSysRole> sysUserJoinSysRoles = mapper.selectAllUserAndRoles();
        for (SysUserJoinSysRole sysUserJoinSysRole : sysUserJoinSysRoles) {
            System.out.println(sysUserJoinSysRole + "/n");
        }
    }

    @Test
    public void testSelectUserPowersByUsername() throws FileNotFoundException {
        SysUserAndPowers sysUserAndPowers = mapper.selectUserPowersByUsername("admin");
        System.out.println(sysUserAndPowers);
    }
}
