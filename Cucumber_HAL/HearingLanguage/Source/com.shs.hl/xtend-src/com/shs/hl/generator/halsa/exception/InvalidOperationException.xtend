package com.shs.hl.generator.halsa.exception

import java.lang.Exception

class InvalidOperationException extends Exception
{
	new(String string)
	{
		super(string)
	}
}

class AbondonPathException extends Exception
{
	new(String string)
	{
		super(string)
	}
}

class PathNoEffectException extends AbondonPathException
{
	new(String string)
	{
		super(string)
	}
}

class UnreachablePathException extends AbondonPathException
{
	new(String string)
	{
		super(string)
	}
}

/**
 * This exception is not supposed to happen.
 */
class OnlyHappenWhenBuggyException extends Exception
{
	new(String string)
	{
		super(string)
	}
}
