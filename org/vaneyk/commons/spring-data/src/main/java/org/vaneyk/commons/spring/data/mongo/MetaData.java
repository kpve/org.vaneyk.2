package org.vaneyk.commons.spring.data.mongo;

public class MetaData
{
    public static final String UNDEFINED_ID_VALUE       = "-1";
    public static final String ROOT_ID_VALUE_EXPRESSION = "#root._id ?: " + MetaData.UNDEFINED_ID_VALUE;
}
