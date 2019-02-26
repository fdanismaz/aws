package com.fd.lambda.layer.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fd.lambda.layer.lib.MyService;


/**
 * @author fdanismaz
 * date: 2/27/19 12:36 AM
 */
public class MySampleHandler implements RequestHandler<MyRequest, MyResponse> {

	private MyService myService;

	public MySampleHandler() {
		this.myService = new MyService();
	}

	/**
	 * Entry point
	 * @param myRequest
	 * @param context
	 * @return
	 */
	@Override
	public MyResponse handleRequest(MyRequest myRequest, Context context) {
		String message = this.myService.execute(myRequest.getName(), myRequest.getSurname());
		return new MyResponse(message);
	}
}
