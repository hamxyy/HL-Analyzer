grammar csharppostprocessor;



@header{
package com.shs.hl.postprocessing.grammar;
 
}

@lexer::header{
package com.shs.hl.postprocessing.grammar;
import java.util.HashMap;
import java.util.Map;
}

@lexer::members{
Map<String, StringBuilder> hashs = new HashMap<String, StringBuilder>();
List<String> hashList = new ArrayList<String>();
int line =0;
String fileName ="";
private void addHash(String str, int hlStart, int csharpStart,String name){
   if(hashs.get(str)!=null){
    System.err.println("This hash exists: " + str);
    }
   else{
     StringBuilder bd = new StringBuilder();
     hashList.add(str);
     bd.append(str + " | "+name+ "|" + hlStart + "|"+ csharpStart  );
     hashs.put(str,bd);
   }
 }
private void setFileName(String str){
     this.fileName = str;
}
 
public List<String> getHashList(){
  return hashList;
} 
 
public Map<String, StringBuilder> getMap(){
  return hashs;
}
public String getFileName (){
   return this.fileName; 
}

private void addEnd(String hash, String hlEnd, String cStop){
    if(hashs.get(hash)==null){
    System.err.println("This hash doesnt have start : " + hash);
    }
   else{
    hashs.get(hash).append('|' + hlEnd +  '|' + cStop);
   }
 } 

}

@members{

}


input : line;

line :STARTELEMENT |ENDELEMENT | HFILENAME|COMMENT| OTHER; 

// "/*#START#1037477562#19#SHS.SAT.FittingHIDBInterfaces.HearingLanguageRuntime.shs_sat.BasicTuning_2SoftSoundsDownBu::IsEnabled#*/"
STARTELEMENT :START has=ID (child=CHILD)? '#' lnNr =ID '#'elementName = '2SHS.SAT.FittingHIDBInterfaces.HearingLanguageRuntime.shs_sat.BasicTuning_SoftSoundsDownBu::IsEnabled' '#*/'{System.out.println($elementName.text);addHash($has.text+($child.text!=null?$child.text :"" ),Integer.valueOf($lnNr.text),getLine(),$elementName.text);};
ENDELEMENT : END has=ID (child=CHILD)?'#'lnr = ID'#*/'{addEnd($has.text+($child.text!=null?$child.text :"" ),$lnr.text, String.valueOf(getLine()));} ;

HFILENAME : '//#HL-SourceFile:' name=FILENAME {setFileName($name.text +".hl");};

ID  :  '-'?Digit+;
CHILD : '$'Letter+;  

fragment
Digit :
  '0'..'9';

IDENTIFIER :
   (Letter | Digit | '$'| '.'|'_'| ':')*;

fragment
Letter :
  'a'..'z' |
  'A'..'Z';

START : '/*' (('#START#')=>'#START#'{$type=STARTTAG;});
END : '/*' (('#END')=>'#END#'{$type=ENDTAG;});

fragment STARTTAG :;
fragment ENDTAG :;

//:curve_Lib/CL.Curve_Base_LIB.hl 
FILENAME :( Letter|'_'| '/'| '.'|Digit)+;

COMMENT: '/*' OTHER  '*/';


OTHER :  ('$'|'!'|' '|','| 'a'..'z' |'A'..'Z' |'}'|'{'|'\r'| '\n' | '\t'| '=' | '.'| '(' | ')' | '"' | '['|']'| '+'| ';' | '_'| '-' | '>' | ':'| '&' | '|' | '@'| '<')*;






