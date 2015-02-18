package com.shs.hl.generator.halsa.controltree

import java.util.ArrayList
import java.util.List

class HLTControlFlowTree
{
	HLTControlTreeNode root

	def static HLTControlFlowTree create()
	{
		return new HLTControlFlowTree(new HLTControlTreeNode)
	}

	new(HLTControlTreeNode root)
	{
		this.root = root
	}

	def getRoot()
	{
		return root
	}
}

class HLTControlTreeNode
{
	public HLTControlTreeNode parent
	List<HLTControlTreeNode> children = new ArrayList

	def getChildren()
	{
		return children
	}

	def append(HLTControlTreeNode node)
	{
		node.parent = this
		children.add(node)
	}

}

class HLTConditionNode extends HLTControlTreeNode
{
	HLTBoolExpression condition

	new(HLTBoolExpression cond)
	{
		this.condition = cond
	}

	def getCondition()
	{
		return condition
	}
}

class HLTExecutionNode extends HLTControlTreeNode
{
	String id
	String value

	new(String id, String value)
	{
		this.id = id
		this.value = value
	}

	def getId()
	{
		return id
	}

	def getValue()
	{
		return value
	}
}
