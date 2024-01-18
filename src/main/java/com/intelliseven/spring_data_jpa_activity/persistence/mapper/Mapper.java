package com.intelliseven.spring_data_jpa_activity.persistence.mapper;

public interface Mapper<A, B> {

  B mapTo(A a);

  A mapFrom(B b);

}
