package com.easyz.util;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>Title:SystemMessage</p>
 * @Company zhuzher
 * @Description   service  方法的返回参数
 * @author z
 */
public class ResponseMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	//请求返回状态码
	private Integer code;
	
	private String message;
	
	private Map<String, Object> data;


	public ResponseMessage pushData(String key, Object obj){
		if(this.data == null){
			data = new HashMap<String, Object>();
		}
		data.put(key, obj);
		return this;
	}
	public ResponseMessage pushData(Object object){
		if(null == data){
			data = new HashMap<String, Object>();
		}
		if(object == null){
			return this;
		}
		String name = object.getClass().getSimpleName();
		data.put(name.substring(0, 1).toLowerCase() + name.substring(1), object);
		return this;
	}
	
	public static ResponseMessage success(){
		return new ResponseMessage(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage());
	}
	
	public static ResponseMessage success(String messgae){
		return new ResponseMessage(ErrorCode.SUCCESS.getCode(), messgae);
	}
	
	public static ResponseMessage success(String key, Object obj){
		return new ResponseMessage(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage()).pushData(key, obj);
	}

	public static ResponseMessage failture(ErrorCode systemHttpInfo){
		return new ResponseMessage(systemHttpInfo.getCode(),systemHttpInfo.getMessage());
	}
	public static ResponseMessage failture(){
		return new ResponseMessage(ErrorCode.FAIL.getCode(),ErrorCode.FAIL.getMessage());
	}

	public static ResponseMessage newErrorsMessage(String message){
        return new ResponseMessage(ErrorCode.FAIL.getCode(), message);
    }
	
	public ResponseMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return toJson();
	}
	
	public String toJson(){
		return new Gson().toJson(this);
	}
}
