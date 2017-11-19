package com.sodyu.learn.test.protobuffer;


import com.google.protobuf.InvalidProtocolBufferException;
import com.sodyu.learn.protobufferdemo.protomodels.AddressModel;
import com.sodyu.learn.protobufferdemo.protomodels.PersonModel;
import org.junit.Test;

/**
 * Created by sodyu on 2017/11/12.
 */
public class protobufTest {


    @Test
    public void protobufone() throws InvalidProtocolBufferException {
        AddressModel.Address address= AddressModel.Address.newBuilder().setCountry("中国").setCity("上海").build();
        PersonModel.Person person= PersonModel.Person.newBuilder().setName("sodyu")
                .setGender("man").setAge(26).setAddress(address).build();
        byte[] results = person.toByteArray();

        PersonModel.Person p=PersonModel.Person.parseFrom(results);
        System.out.println(p.getName()+"-年龄:"+p.getAge());

    }
}
