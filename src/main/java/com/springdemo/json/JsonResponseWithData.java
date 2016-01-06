package com.springdemo.json;

public class JsonResponseWithData<T> extends JsonResponse {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
