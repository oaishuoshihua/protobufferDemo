package com.sodyu.learn.protobufferdemo.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sodyu on 2017/11/5.
 */
public class SerializatioinUtil {

    private static Map<Class<?>, Schema<?>> schemaMap = new ConcurrentHashMap<Class<?>, Schema<?>>();

    public static <T> byte[] serialize(T data) {
        Class<T> cls = (Class<T>) data.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            Schema<T> schema = getSchema(cls);
            T obj = cls.newInstance();
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }catch (InstantiationException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    private static <T> Schema getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) schemaMap.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(cls);
            schemaMap.put(cls, schema);
        }
        return schema;
    }
}
