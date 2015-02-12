package de.itemis.xtext.typesystem.dsl.generator

import de.itemis.xtext.typesystem.dsl.tsDsl.TypesystemSpec
import de.itemis.xtext.typesystem.dsl.tsDsl.TypingRule
import de.itemis.xtext.typesystem.dsl.tsDsl.MetaclassSpec
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import de.itemis.xtext.typesystem.dsl.tsDsl.TypeConstraint
import java.util.LinkedList

class Utils { 
	
	def className( TypesystemSpec tss ) {
		val l = tss.name.lastIndexOf('.');
		tss.name.substring(l+1)+"Generated"
	}
	
	def pack( TypesystemSpec tss ) {
		val l = tss.name.lastIndexOf('.');
		tss.name.substring(0,l)
	}
	
	def parentMetaclass( TypingRule tr ) {
		(tr.eContainer as MetaclassSpec).clazz
	}
	
	def parentMetaclass( TypeConstraint tc ) {
		(tc.eContainer as MetaclassSpec).clazz
	}
	
	def getter( EClass metaclass ) {
		"p.get"+metaclass.name+"()"
	}
	
	def getter( EStructuralFeature f ) {
		"p.get"+(f.eContainer as EClass).name+"_"+f.name.toFirstUpper+"()"
	}
	
	def skipLastSegment( String qualName ) {
		qualName.substring(0,qualName.lastIndexOf('.'));
	}
	
	def ts(MetaclassSpec spec) {
		(spec.eContainer.eContainer) as TypesystemSpec
	}
	
	def subtypes( EClass  cls ) {
		cls.EPackage.EClassifiers.filter(typeof(EClass)).filter(c | c.EAllSuperTypes.contains(cls)  )
	}
	
	def all( TypesystemSpec spec ) {
		spec.sections.map(s|s.metaclassSpecs).flatten
	}
	
	def allSubtypeSpec( TypesystemSpec spec ) {
		spec.sections.map(s|s.subtypeSpec).flatten
	}
	
	def hasSpecFor( TypesystemSpec spec, EClass cls ) {
		spec.all.exists(mm|mm.responsibleFor(cls)  )
	}
	
	def hasDirectSpecFor( TypesystemSpec spec, EClass cls ) {
		spec.all.exists(mm|mm.clazz == cls )
	}
	
	def responsibleFor( MetaclassSpec spec, EClass cls ) {
		spec.clazz == cls || (spec.includeSubtypes && spec.clazz.subtypes.exists(c|c==cls) ) 
	}	
	
}