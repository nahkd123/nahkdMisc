package me.nahkd.misc.core.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface BinaryStorageType<T> {
	
	/**
	 * Write to stream. The stream already included headers
	 * @param stream
	 * @throws IOException
	 */
	public void write(OutputStream stream, T val) throws IOException;
	
	/**
	 * You should better using {@link BinaryStorageType#write(OutputStream, Object)}, rather than
	 * this method.
	 * @param stream
	 * @param val
	 * @throws IOException
	 */
	default void unsafeWrite(OutputStream stream, Object val) throws IOException {
		write(stream, (T) val);
	}
	
	/**
	 * Read from stream. The stream already included headers
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	public T read(InputStream stream) throws IOException;
	
	/**
	 * The ID to store to storage. IDs can be the same, but if so then it would break
	 * the plugin.
	 * @return
	 */
	public int getId();
	
	default void register() {
		BinaryStorage.getTypes().put(getId(), this);
	}
	
}
