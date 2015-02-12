package com.shs.hl.generator.utils

import com.google.inject.Singleton
import org.eclipse.emf.ecore.EObject

import static org.eclipse.xtext.nodemodel.util.NodeModelUtils.*



@Singleton
class LineGene {

StateSpaceHashCodeHelper hashHelper = new StateSpaceHashCodeHelper(true,false,false) 


  def getHash(EObject obj){
  	  hashHelper.hashCode(obj)
  }
     
  def calculateHash(EObject obj){ 
     obj.hashCode 	
  }
 

  def getStartRegion(EObject obj, String info,String fqName)'''
   /*#START#«obj.hash»«info»#«findActualNodeFor(obj).startLine»#«fqName»#*/
  '''
  
  def getEndRegion(EObject obj,String info) '''
  /*#END#«obj.hash»«info»#«findActualNodeFor(obj).getEndLine»#*/
  '''
  
}