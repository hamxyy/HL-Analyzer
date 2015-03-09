package com.shs.hl.generator.halsa.struct

import java.util.ArrayList
import java.util.List

class HLTControlFlowTree
{
	HLTControlFlowTreeNode root

	def static HLTControlFlowTree create()
	{
		return new HLTControlFlowTree(new HLTRootNode)
	}

	new(HLTControlFlowTreeNode root)
	{
		this.root = root
	}

	def getRoot()
	{
		return root
	}

	override toString()
	{
		return "ControlTree: " + root.toString
	}

	def String format()
	{
		var sb = new StringBuilder
		printNode(root, 0, sb);
		return sb.toString
	}

	private def void printNode(HLTControlFlowTreeNode node, int indentation, StringBuilder sb)
	{

		// Print the value to the console/file/whatever
		// This prefixes the value with the necessary amount of indentation
		print(node.toString, indentation, sb);

		// Recursively call the child nodes.
		for (childNode : node.children)
		{
			printNode(childNode, indentation + 1, sb); // Increment the indentation counter.
		}
	}

	private def void print(String s, int indent, StringBuilder sb)
	{
		var spaces = ""
		for (var i = 0; i < indent; i++)
		{
			spaces += "  "
		}
		sb.append(spaces + s + "\n")
	}
}

class HLTControlFlowTreeNode
{
	public HLTControlFlowTreeNode parent
	List<HLTControlFlowTreeNode> children = new ArrayList

	def getChildren()
	{
		return children
	}

	def append(HLTControlFlowTreeNode node)
	{
		node.parent = this
		children.add(node)
	}

	override toString()
	{
		var result = "["
		for (n : children)
		{
			result += n.toString + ", "
		}
		result += "]"
		return result
	}

	def HLTBoolExpression conditionsAlongPath()
	{
		var HLTBoolExpression curExpr = HLTConstantExpression.alwaysTrue
		var HLTControlFlowTreeNode curNode
		if (this instanceof HLTConditionNode)
		{
			curNode = this
		}
		else
		{
			curNode = this.parent
		}

		while (curNode != null)
		{
			if (curNode instanceof HLTRootNode)
			{
				return curExpr
			}
			else if (curNode instanceof HLTConditionNode)
			{
				if (curExpr == null)
				{
					curExpr = (curNode as HLTConditionNode).condition
				}
				else
				{
					curExpr = curExpr.and((curNode as HLTConditionNode).condition)
				}
				curNode = curNode.parent
			}
			else
			{
				throw new Exception("Unexpected tree structure.")
			}
		}
		return curExpr
	}
}

class HLTRootNode extends HLTControlFlowTreeNode
{
	override toString()
	{
		return "root"
	}
}

class HLTConditionNode extends HLTControlFlowTreeNode
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

	override toString()
	{
		return "+ " + condition.toString
	}

}

class HLTExecutionNode extends HLTControlFlowTreeNode
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

	override toString()
	{
		return "- " + id + "=" + value
	}
}

class HLTReturnNode extends HLTControlFlowTreeNode
{
	String value

	new(String value)
	{
		this.value = value
	}

	def getValue()
	{
		return value
	}

	override toString()
	{
		return "- return " + value
	}

}
