package com.shs.hl.generator.utils;

import com.google.inject.ImplementedBy;


@ImplementedBy(com.shs.hl.generator.utils.GenCode.class)
public interface IGenCode {
	CharSequence genCode(Object val);

}
