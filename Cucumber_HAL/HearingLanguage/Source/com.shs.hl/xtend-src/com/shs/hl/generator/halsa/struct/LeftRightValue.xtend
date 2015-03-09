package com.shs.hl.generator.halsa.struct

class StorageObject
{
	String name
	boolean isGlobal

	new(String name, boolean isGlobal)
	{
		this.name = name
		this.isGlobal = isGlobal
	}

	def getName()
	{
		return name
	}

	def isLocal()
	{
		return !isGlobal
	}

	def isGlobal()
	{
		return isGlobal
	}
}
