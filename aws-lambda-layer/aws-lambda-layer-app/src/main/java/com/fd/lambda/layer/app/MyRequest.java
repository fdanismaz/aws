package com.fd.lambda.layer.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fdanismaz
 * date: 2/27/19 12:37 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyRequest {

	private String name;
	private String surname;

}
