package com.fraga.APIRest.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}

	public static <O, D> List<D> parseList(List<O> origin, Class<D> destination) {
		return origin.stream().map(o -> mapper.map(o, destination)).collect(Collectors.toList());
	}
	
	
}
