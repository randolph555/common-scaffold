package v.show.backend.utils;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

/**
 * 使用Orika实现深度的BeanOfClasssA<->BeanOfClassB复制
 * <p>
 * 不要使用Apache Common BeanUtils进行类复制，每次进行反射查询对象的属性列表, 非常缓慢.
 */
public class BeanMapper {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private static MapperFacade mapper;

    static {
        // 支持Jasckson的JSON对象拷贝
        mapperFactory.registerObjectFactory((source, mappingContext) -> JsonUtil.convertObj(source), TypeFactory.valueOf(ObjectNode.class));

        mapperFactory.registerObjectFactory((source, mappingContext) -> JsonUtil.convertObj(source), TypeFactory.valueOf(ArrayNode.class));

        mapper = mapperFactory.getMapperFacade();
    }

    /**
     * 简单的复制出新类型对象.
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 简单的复制出新对象ArrayList.
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, destinationClass);
    }

    /**
     * 简单的复制出新对象Set.
     */
    public static <S, D> Set<D> mapSet(Iterable<S> sourceList, Class<D> destinationClass) {
        return mapper.mapAsSet(sourceList, destinationClass);
    }

    /**
     * 简单复制出新对象数组.
     */
    public static <S, D> D[] mapArray(final S[] sourceArray, final Class<D> destinationClass) {
        D[] destinationArray = (D[]) Array.newInstance(destinationClass, sourceArray.length);
        return mapper.mapAsArray(destinationArray, sourceArray, destinationClass);
    }

}
