package me.nahkd.misc.core.storage;

public class BinaryStorageObject {
	
	Object obj;
	BinaryStorageType<?> type;
	
	public BinaryStorageObject(BinaryStorageType<?> type, Object obj) {
		this.obj = obj;
		this.type = type;
	}
	
}
