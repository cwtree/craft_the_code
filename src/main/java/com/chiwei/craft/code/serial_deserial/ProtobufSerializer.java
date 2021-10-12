package com.chiwei.craft.code.serial_deserial;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chiwei
 * @Type ProtobufSerializer.java
 * @Desc
 * @date 2016年6月6日 下午2:38:34
 */
public class ProtobufSerializer {

	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

	private static Objenesis objenesis = new ObjenesisStd(true);

	static {
		cachedSchema.put(Boolean.class, RuntimeSchema.createFrom(Boolean.class));
		cachedSchema.put(Byte.class, RuntimeSchema.createFrom(Byte.class));
		cachedSchema.put(Short.class, RuntimeSchema.createFrom(Short.class));
		cachedSchema.put(Integer.class, RuntimeSchema.createFrom(Integer.class));
		cachedSchema.put(Long.class, RuntimeSchema.createFrom(Long.class));
		cachedSchema.put(Float.class, RuntimeSchema.createFrom(Float.class));
		cachedSchema.put(Double.class, RuntimeSchema.createFrom(Double.class));
		cachedSchema.put(Character.class, RuntimeSchema.createFrom(Character.class));
		cachedSchema.put(String.class, RuntimeSchema.createFrom(String.class));
		cachedSchema.put(BigInteger.class, RuntimeSchema.createFrom(BigInteger.class));
		cachedSchema.put(BigDecimal.class, RuntimeSchema.createFrom(BigDecimal.class));
	}

	@SuppressWarnings("unchecked")
	private static <T> Schema<T> getSchema(Class<T> cls) {
		Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
		if (schema == null) {
			schema = RuntimeSchema.createFrom(cls);
			if (schema != null) {
				cachedSchema.put(cls, schema);
			}
		}
		return schema;
	}

	/**
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> byte[] serialize(T obj) {
		Class<T> cls = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema = getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * @param bytes
	 * @return
	 */
	public static <T> T deserialize(byte[] bytes) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param bytes
	 * @param cls
	 * @return
	 */
	public static <T> T deserialize(byte[] bytes, Class<T> cls) {
		try {
			T message = objenesis.newInstance(cls);
			Schema<T> schema = getSchema(cls);
			ProtostuffIOUtil.mergeFrom(bytes, message, schema);
			return message;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年6月6日 chiwei create
 */
