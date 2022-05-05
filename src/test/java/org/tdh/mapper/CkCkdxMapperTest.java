package org.tdh.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tdh.domain.CkCkdx;


@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/application.xml","file:src/main/webapp/WEB-INF/config/jdbc.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CkCkdxMapperTest {

    @Autowired
    private CkCkdxMapper ckCkdxMapper;

    @Test
    public void getAll() {
        CkCkdx all = ckCkdxMapper.getAll();
        System.out.println(all);
    }

}