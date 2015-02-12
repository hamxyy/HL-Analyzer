// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g 2012-09-26 17:07:09

package com.shs.hl.postprocessing.grammar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
public class csharppostprocessorLexer extends Lexer {
    public static final int CHILD=11;
    public static final int STARTTAG=17;
    public static final int ENDTAG=18;
    public static final int ENDELEMENT=5;
    public static final int OTHER=8;
    public static final int FILENAME=13;
    public static final int IDENTIFIER=16;
    public static final int STARTELEMENT=4;
    public static final int START=9;
    public static final int HFILENAME=6;
    public static final int COMMENT=7;
    public static final int ID=10;
    public static final int END=12;
    public static final int Digit=14;
    public static final int EOF=-1;
    public static final int Letter=15;

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



    // delegates
    // delegators

    public csharppostprocessorLexer() {;} 
    public csharppostprocessorLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public csharppostprocessorLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g"; }

    // $ANTLR start "STARTELEMENT"
    public final void mSTARTELEMENT() throws RecognitionException {
        try {
            int _type = STARTELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken has=null;
            CommonToken child=null;
            CommonToken lnNr=null;
            CommonToken elementName=null;

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:68:14: ( START has= ID (child= CHILD )? '#' lnNr= ID '#' elementName= '2SHS.SAT.FittingHIDBInterfaces.HearingLanguageRuntime.shs_sat.BasicTuning_SoftSoundsDownBu::IsEnabled' '#*/' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:68:15: START has= ID (child= CHILD )? '#' lnNr= ID '#' elementName= '2SHS.SAT.FittingHIDBInterfaces.HearingLanguageRuntime.shs_sat.BasicTuning_SoftSoundsDownBu::IsEnabled' '#*/'
            {
            mSTART(); if (state.failed) return ;
            int hasStart27 = getCharIndex();
            mID(); if (state.failed) return ;
            has = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, hasStart27, getCharIndex()-1);
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:68:28: (child= CHILD )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='$') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:68:29: child= CHILD
                    {
                    int childStart32 = getCharIndex();
                    mCHILD(); if (state.failed) return ;
                    child = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, childStart32, getCharIndex()-1);

                    }
                    break;

            }

            match('#'); if (state.failed) return ;
            int lnNrStart41 = getCharIndex();
            mID(); if (state.failed) return ;
            lnNr = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, lnNrStart41, getCharIndex()-1);
            match('#'); if (state.failed) return ;
            int elementNameStart = getCharIndex();
            match("2SHS.SAT.FittingHIDBInterfaces.HearingLanguageRuntime.shs_sat.BasicTuning_SoftSoundsDownBu::IsEnabled"); if (state.failed) return ;
            elementName = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, elementNameStart, getCharIndex()-1);
            match("#*/"); if (state.failed) return ;

            if ( state.backtracking==0 ) {
              System.out.println((elementName!=null?elementName.getText():null));addHash((has!=null?has.getText():null)+((child!=null?child.getText():null)!=null?(child!=null?child.getText():null) :"" ),Integer.valueOf((lnNr!=null?lnNr.getText():null)),getLine(),(elementName!=null?elementName.getText():null));
            }

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STARTELEMENT"

    // $ANTLR start "ENDELEMENT"
    public final void mENDELEMENT() throws RecognitionException {
        try {
            int _type = ENDELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken has=null;
            CommonToken child=null;
            CommonToken lnr=null;

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:69:12: ( END has= ID (child= CHILD )? '#' lnr= ID '#*/' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:69:14: END has= ID (child= CHILD )? '#' lnr= ID '#*/'
            {
            mEND(); if (state.failed) return ;
            int hasStart63 = getCharIndex();
            mID(); if (state.failed) return ;
            has = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, hasStart63, getCharIndex()-1);
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:69:25: (child= CHILD )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='$') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:69:26: child= CHILD
                    {
                    int childStart68 = getCharIndex();
                    mCHILD(); if (state.failed) return ;
                    child = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, childStart68, getCharIndex()-1);

                    }
                    break;

            }

            match('#'); if (state.failed) return ;
            int lnrStart76 = getCharIndex();
            mID(); if (state.failed) return ;
            lnr = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, lnrStart76, getCharIndex()-1);
            match("#*/"); if (state.failed) return ;

            if ( state.backtracking==0 ) {
              addEnd((has!=null?has.getText():null)+((child!=null?child.getText():null)!=null?(child!=null?child.getText():null) :"" ),(lnr!=null?lnr.getText():null), String.valueOf(getLine()));
            }

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENDELEMENT"

    // $ANTLR start "HFILENAME"
    public final void mHFILENAME() throws RecognitionException {
        try {
            int _type = HFILENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken name=null;

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:71:11: ( '//#HL-SourceFile:' name= FILENAME )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:71:13: '//#HL-SourceFile:' name= FILENAME
            {
            match("//#HL-SourceFile:"); if (state.failed) return ;

            int nameStart92 = getCharIndex();
            mFILENAME(); if (state.failed) return ;
            name = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nameStart92, getCharIndex()-1);
            if ( state.backtracking==0 ) {
              setFileName((name!=null?name.getText():null) +".hl");
            }

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HFILENAME"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:73:5: ( ( '-' )? ( Digit )+ )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:73:8: ( '-' )? ( Digit )+
            {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:73:8: ( '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:73:8: '-'
                    {
                    match('-'); if (state.failed) return ;

                    }
                    break;

            }

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:73:12: ( Digit )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:73:12: Digit
            	    {
            	    mDigit(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "CHILD"
    public final void mCHILD() throws RecognitionException {
        try {
            int _type = CHILD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:74:7: ( '$' ( Letter )+ )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:74:9: '$' ( Letter )+
            {
            match('$'); if (state.failed) return ;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:74:12: ( Letter )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='A' && LA5_0<='Z')||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:74:12: Letter
            	    {
            	    mLetter(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHILD"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:77:7: ( '0' .. '9' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:78:3: '0' .. '9'
            {
            matchRange('0','9'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:80:12: ( ( Letter | Digit | '$' | '.' | '_' | ':' )* )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:81:4: ( Letter | Digit | '$' | '.' | '_' | ':' )*
            {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:81:4: ( Letter | Digit | '$' | '.' | '_' | ':' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='$'||LA6_0=='.'||(LA6_0>='0' && LA6_0<=':')||(LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:
            	    {
            	    if ( input.LA(1)=='$'||input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:84:8: ( 'a' .. 'z' | 'A' .. 'Z' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "START"
    public final void mSTART() throws RecognitionException {
        try {
            int _type = START;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:88:7: ( '/*' ( ( '#START#' )=> '#START#' ) )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:88:9: '/*' ( ( '#START#' )=> '#START#' )
            {
            match("/*"); if (state.failed) return ;

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:88:14: ( ( '#START#' )=> '#START#' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:88:15: ( '#START#' )=> '#START#'
            {
            match("#START#"); if (state.failed) return ;

            if ( state.backtracking==0 ) {
              _type=STARTTAG;
            }

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:89:5: ( '/*' ( ( '#END' )=> '#END#' ) )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:89:7: '/*' ( ( '#END' )=> '#END#' )
            {
            match("/*"); if (state.failed) return ;

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:89:12: ( ( '#END' )=> '#END#' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:89:13: ( '#END' )=> '#END#'
            {
            match("#END#"); if (state.failed) return ;

            if ( state.backtracking==0 ) {
              _type=ENDTAG;
            }

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "STARTTAG"
    public final void mSTARTTAG() throws RecognitionException {
        try {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:91:19: ()
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:91:20: 
            {
            }

        }
        finally {
        }
    }
    // $ANTLR end "STARTTAG"

    // $ANTLR start "ENDTAG"
    public final void mENDTAG() throws RecognitionException {
        try {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:92:17: ()
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:92:18: 
            {
            }

        }
        finally {
        }
    }
    // $ANTLR end "ENDTAG"

    // $ANTLR start "FILENAME"
    public final void mFILENAME() throws RecognitionException {
        try {
            int _type = FILENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:95:10: ( ( Letter | '_' | '/' | '.' | Digit )+ )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:95:11: ( Letter | '_' | '/' | '.' | Digit )+
            {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:95:11: ( Letter | '_' | '/' | '.' | Digit )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='.' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:
            	    {
            	    if ( (input.LA(1)>='.' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FILENAME"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:97:8: ( '/*' OTHER '*/' )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:97:10: '/*' OTHER '*/'
            {
            match("/*"); if (state.failed) return ;

            mOTHER(); if (state.failed) return ;
            match("*/"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "OTHER"
    public final void mOTHER() throws RecognitionException {
        try {
            int _type = OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:100:7: ( ( '$' | '!' | ' ' | ',' | 'a' .. 'z' | 'A' .. 'Z' | '}' | '{' | '\\r' | '\\n' | '\\t' | '=' | '.' | '(' | ')' | '\"' | '[' | ']' | '+' | ';' | '_' | '-' | '>' | ':' | '&' | '|' | '@' | '<' )* )
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:100:10: ( '$' | '!' | ' ' | ',' | 'a' .. 'z' | 'A' .. 'Z' | '}' | '{' | '\\r' | '\\n' | '\\t' | '=' | '.' | '(' | ')' | '\"' | '[' | ']' | '+' | ';' | '_' | '-' | '>' | ':' | '&' | '|' | '@' | '<' )*
            {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:100:10: ( '$' | '!' | ' ' | ',' | 'a' .. 'z' | 'A' .. 'Z' | '}' | '{' | '\\r' | '\\n' | '\\t' | '=' | '.' | '(' | ')' | '\"' | '[' | ']' | '+' | ';' | '_' | '-' | '>' | ':' | '&' | '|' | '@' | '<' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||(LA8_0>=' ' && LA8_0<='\"')||LA8_0=='$'||LA8_0=='&'||(LA8_0>='(' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='.')||(LA8_0>=':' && LA8_0<='>')||(LA8_0>='@' && LA8_0<='[')||LA8_0==']'||LA8_0=='_'||(LA8_0>='a' && LA8_0<='}')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='\"')||input.LA(1)=='$'||input.LA(1)=='&'||(input.LA(1)>='(' && input.LA(1)<=')')||(input.LA(1)>='+' && input.LA(1)<='.')||(input.LA(1)>=':' && input.LA(1)<='>')||(input.LA(1)>='@' && input.LA(1)<='[')||input.LA(1)==']'||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='}') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OTHER"

    public void mTokens() throws RecognitionException {
        // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:8: ( STARTELEMENT | ENDELEMENT | HFILENAME | ID | CHILD | IDENTIFIER | START | END | FILENAME | COMMENT | OTHER )
        int alt9=11;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:10: STARTELEMENT
                {
                mSTARTELEMENT(); if (state.failed) return ;

                }
                break;
            case 2 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:23: ENDELEMENT
                {
                mENDELEMENT(); if (state.failed) return ;

                }
                break;
            case 3 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:34: HFILENAME
                {
                mHFILENAME(); if (state.failed) return ;

                }
                break;
            case 4 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:44: ID
                {
                mID(); if (state.failed) return ;

                }
                break;
            case 5 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:47: CHILD
                {
                mCHILD(); if (state.failed) return ;

                }
                break;
            case 6 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:53: IDENTIFIER
                {
                mIDENTIFIER(); if (state.failed) return ;

                }
                break;
            case 7 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:64: START
                {
                mSTART(); if (state.failed) return ;

                }
                break;
            case 8 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:70: END
                {
                mEND(); if (state.failed) return ;

                }
                break;
            case 9 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:74: FILENAME
                {
                mFILENAME(); if (state.failed) return ;

                }
                break;
            case 10 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:83: COMMENT
                {
                mCOMMENT(); if (state.failed) return ;

                }
                break;
            case 11 :
                // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:1:91: OTHER
                {
                mOTHER(); if (state.failed) return ;

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\6\1\13\1\10\1\14\2\6\1\uffff\1\6\2\uffff\1\13\2\uffff\1\6\1"+
        "\22\12\uffff\1\33\4\uffff\1\37\2\uffff";
    static final String DFA9_eofS =
        "\41\uffff";
    static final String DFA9_minS =
        "\1\11\1\52\1\60\1\44\2\11\1\uffff\1\11\1\uffff\1\11\1\43\2\uffff"+
        "\1\56\1\11\1\105\3\uffff\1\116\1\124\1\104\1\101\1\43\1\122\1\55"+
        "\1\124\2\uffff\1\43\1\55\2\uffff";
    static final String DFA9_maxS =
        "\1\175\1\57\1\71\1\172\2\175\1\uffff\1\175\1\uffff\1\175\1\43\2"+
        "\uffff\1\172\1\175\1\123\3\uffff\1\116\1\124\1\104\1\101\1\43\1"+
        "\122\1\71\1\124\2\uffff\1\43\1\71\2\uffff";
    static final String DFA9_acceptS =
        "\6\uffff\1\6\1\uffff\1\13\2\uffff\1\11\1\4\3\uffff\1\12\1\3\1\5"+
        "\10\uffff\1\10\1\2\2\uffff\1\7\1\1";
    static final String DFA9_specialS =
        "\41\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\10\2\uffff\1\10\22\uffff\3\10\1\uffff\1\4\1\uffff\1\10\1"+
            "\uffff\2\10\1\uffff\2\10\1\2\1\5\1\1\12\3\1\7\4\10\1\uffff\1"+
            "\10\32\5\1\10\1\uffff\1\10\1\uffff\1\5\1\uffff\32\5\3\10",
            "\1\11\4\uffff\1\12",
            "\12\14",
            "\1\6\11\uffff\1\15\1\13\12\3\1\6\6\uffff\32\15\4\uffff\1\15"+
            "\1\uffff\32\15",
            "\2\10\2\uffff\1\10\22\uffff\3\10\1\uffff\1\7\1\uffff\1\10"+
            "\1\uffff\2\10\1\uffff\3\10\1\7\13\uffff\1\7\4\10\1\uffff\1\10"+
            "\32\16\1\10\1\uffff\1\10\1\uffff\1\7\1\uffff\32\16\3\10",
            "\2\10\2\uffff\1\10\22\uffff\3\10\1\uffff\1\7\1\uffff\1\10"+
            "\1\uffff\2\10\1\uffff\3\10\1\5\1\13\12\15\1\7\4\10\1\uffff\1"+
            "\10\32\5\1\10\1\uffff\1\10\1\uffff\1\5\1\uffff\32\5\3\10",
            "",
            "\2\10\2\uffff\1\10\22\uffff\3\10\1\uffff\1\7\1\uffff\1\10"+
            "\1\uffff\2\10\1\uffff\3\10\1\7\13\uffff\1\7\4\10\1\uffff\1\10"+
            "\32\7\1\10\1\uffff\1\10\1\uffff\1\7\1\uffff\32\7\3\10",
            "",
            "\2\20\2\uffff\1\20\22\uffff\3\20\1\17\1\20\1\uffff\1\20\1"+
            "\uffff\7\20\13\uffff\5\20\1\uffff\34\20\1\uffff\1\20\1\uffff"+
            "\1\20\1\uffff\35\20",
            "\1\21",
            "",
            "",
            "\1\15\1\13\12\15\7\uffff\32\15\4\uffff\1\15\1\uffff\32\15",
            "\2\10\2\uffff\1\10\22\uffff\3\10\1\uffff\1\7\1\uffff\1\10"+
            "\1\uffff\2\10\1\uffff\3\10\1\7\1\uffff\12\6\1\7\4\10\1\uffff"+
            "\1\10\32\16\1\10\1\uffff\1\10\1\uffff\1\7\1\uffff\32\16\3\10",
            "\1\23\15\uffff\1\24",
            "",
            "",
            "",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\34\2\uffff\12\34",
            "\1\35",
            "",
            "",
            "\1\36",
            "\1\40\2\uffff\12\40",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( STARTELEMENT | ENDELEMENT | HFILENAME | ID | CHILD | IDENTIFIER | START | END | FILENAME | COMMENT | OTHER );";
        }
    }
 

}