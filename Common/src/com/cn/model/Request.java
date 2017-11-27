package com.cn.model;
/**
 * 
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头	| 模块号  | 命令号 |  长度  |   数据  |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 * 数据4字节
 */
public class Request {
	/**
	 * 模块号
	 */
	private short module;
	/**
	 * 命令号
	 */
	private short cmd;
	/**
	 * 数据
	 */
	private byte[] data;
	public short getModule() {
		return module;
	}
	public void setModule(short module) {
		this.module = module;
	}
	public short getCmd() {
		return cmd;
	}
	public void setCmd(short cmd) {
		this.cmd = cmd;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	/**
	 * 获取数据长度
	 */
	public int getDataLength(){
		if(this.data==null){
			return 0;
		}
		return this.data.length;
	}
}

































