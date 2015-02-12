package com.shs.hl.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.shs.hl.tests.arrays.ArrayTest1;
import com.shs.hl.tests.bitops.BitOpsTest;
import com.shs.hl.tests.bitops.Bitmasks;
import com.shs.hl.tests.curves.Curves;
import com.shs.hl.tests.curves.LevelCurves;
import com.shs.hl.tests.domainfunctions.DomainFunctions;
import com.shs.hl.tests.functionattrs.FunctionAttrs;
import com.shs.hl.tests.namespaces.Namespaces;
//import com.shs.hl.tests.nativeCode.NativeCode;
import com.shs.hl.tests.packag.Packag;
import com.shs.hl.tests.parameteraccess.EnumAccess1;
import com.shs.hl.tests.parameteraccess.Opposite;
import com.shs.hl.tests.parameteraccess.Sit;
import com.shs.hl.tests.scopes.TestScopes1;
import com.shs.hl.tests.text.Text;
import com.shs.hl.tests.typesystem.Const;
import com.shs.hl.tests.typesystem.Foreach;
import com.shs.hl.tests.typesystem.HexAndBin;
import com.shs.hl.tests.typesystem.Interval;
import com.shs.hl.tests.typesystem.NegativeNumbers;
import com.shs.hl.tests.typesystem.Nullable;
import com.shs.hl.tests.typesystem.Operators;
import com.shs.hl.tests.typesystem.Precedence;
import com.shs.hl.tests.typesystem.StringCoercion;
import com.shs.hl.tests.typesystem.Subtyping;
import com.shs.hl.tests.typesystem.Switch;
import com.shs.hl.tests.typesystem.Test1;
import com.shs.hl.tests.typesystem.TestAssignment;
import com.shs.hl.tests.typesystem.TestIf;
import com.shs.hl.tests.typesystem.While;
import com.shs.hl.tests.unitstuff.UnitStuff;
import com.shs.hl.tests.unitstuff.UnitsAndParameterArrays;
import com.shs.hl.tests.weightingAndMax.WeightingAndMax;


@RunWith(Suite.class)
@Suite.SuiteClasses({
  // arrays
  ArrayTest1.class,

  // bitops
  Bitmasks.class,
  BitOpsTest.class,
  

  // curves
  Curves.class,
  LevelCurves.class,
  
  // domainfunctions
  DomainFunctions.class,

  // functionattrs
  FunctionAttrs.class,
  
  // namespaces
  Namespaces.class,
  
  // nativeCode
//  NativeCode.class,

  // packag
  Packag.class,

  // parameteraccess
  EnumAccess1.class,
  Opposite.class,
  Sit.class,

  // query
  
  // scopes
  TestScopes1.class,

  // text
  Text.class,

  // typesystem
  Const.class,
  Foreach.class,
  HexAndBin.class,
  Interval.class,
  NegativeNumbers.class,
  Nullable.class,
  Operators.class,
  Precedence.class,
  StringCoercion.class,
  Subtyping.class,
  Switch.class,
  Test1.class,
  TestAssignment.class,
  TestIf.class,
  While.class,
  
  // Unitstuff
  UnitsAndParameterArrays.class,
  UnitStuff.class,
  
  // weightingAndMax
  WeightingAndMax.class 
})
public class AllTests {

}
   

