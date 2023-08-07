package com.programmingtechie.orderservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	Environment env;

	@Autowired
	public FeignErrorDecoder(Environment env) {
		this.env = env;
	}

	//Not working
	@Override
	public Exception decode(String methodKey, Response response) {
		System.out.println();
		switch (response.status()) {
		case 400:
			break;

		case 404: {

			if (methodKey.contains("isInStock")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()),
						env.getProperty("inventory.404.error"));

			}
			break;
		}
		case 500: {

			if (methodKey.contains("isInStock")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()),
						env.getProperty("inventory.404.error"));

			}
			break;
		}
		default:
			return new Exception(response.reason());

		}
		return new Exception(response.reason());

	}
}