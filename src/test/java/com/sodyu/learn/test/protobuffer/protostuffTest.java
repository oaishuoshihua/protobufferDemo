package com.sodyu.learn.test.protobuffer;

import com.alibaba.fastjson.JSON;
import com.sodyu.learn.protobufferdemo.model.Address;
import com.sodyu.learn.protobufferdemo.model.Person;
import com.sodyu.learn.protobufferdemo.utils.SerializatioinUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by sodyu on 2017/11/5.
 */

public class protostuffTest {

    @Test
    public void protostuff(){
        Person person=new Person();
        Address address=new Address();
        address.setCity("上海");
        address.setContry("中国");
        person.setName("sodyu");
        person.setAge(26);
        person.setAddress(address);
        person.setGender("man");

        byte[] bytes= SerializatioinUtil.serialize(person);

        System.out.println(Arrays.toString(bytes));

        Person p=SerializatioinUtil.deserialize(bytes,Person.class);
        System.out.println(JSON.toJSONString(p));

    }
}
