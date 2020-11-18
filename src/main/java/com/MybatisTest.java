package com;

import com.pojo.Dic;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sqlSessionFactory.openSession();

        Dic addDic = new Dic();
        addDic.setDicId(UUID.randomUUID().toString());
        addDic.setDicCode("D.002");
        addDic.setDicName("性别");
        addDic.setDescription("指男女两性");
        session.insert("addDic", addDic);

        session.commit();

        List<Dic> dics = session.selectList("dicList");
        for (Dic dic : dics) {
            System.out.println(dic.getDicCode() + "(" + dic.getDicName() + ")：" + dic.getDescription());
        }

        session.close();
    }
}
