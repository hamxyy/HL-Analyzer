// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g 2012-09-26 17:07:09

package com.shs.hl.postprocessing.grammar;
 


import java.io.IOException;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugEventListener;
import org.antlr.runtime.debug.DebugEventSocketProxy;
import org.antlr.runtime.debug.DebugParser;
public class csharppostprocessorParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STARTELEMENT", "ENDELEMENT", "HFILENAME", "COMMENT", "OTHER", "START", "ID", "CHILD", "END", "FILENAME", "Digit", "Letter", "IDENTIFIER", "STARTTAG", "ENDTAG"
    };
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
    public static final int Digit=14;
    public static final int END=12;
    public static final int ID=10;
    public static final int COMMENT=7;
    public static final int EOF=-1;
    public static final int Letter=15;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "line", "input"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public csharppostprocessorParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public csharppostprocessorParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this, port, null);
            setDebugListener(proxy);
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
        }
    public csharppostprocessorParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg, new RecognizerSharedState());

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }


    public String[] getTokenNames() { return csharppostprocessorParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g"; }






    // $ANTLR start "input"
    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:63:1: input : line ;
    public final void input() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "input");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(63, 1);

        try {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:63:7: ( line )
            dbg.enterAlt(1);

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:63:9: line
            {
            dbg.location(63,9);
            pushFollow(FOLLOW_line_in_input39);
            line();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(63, 13);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "input");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "input"


    // $ANTLR start "line"
    // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:65:1: line : ( STARTELEMENT | ENDELEMENT | HFILENAME | COMMENT | OTHER );
    public final void line() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "line");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(65, 1);

        try {
            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:65:6: ( STARTELEMENT | ENDELEMENT | HFILENAME | COMMENT | OTHER )
            dbg.enterAlt(1);

            // D:\\EclipseWS\\HLWHearingLanguage7.1.1\\com.shs.hl.postprocessing\\src\\com\\shs\\hl\\postprocessing\\grammar\\csharppostprocessor.g:
            {
            dbg.location(65,6);
            if ( (input.LA(1)>=STARTELEMENT && input.LA(1)<=OTHER) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(65, 58);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "line");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "line"

    // Delegated rules


 

    public static final BitSet FOLLOW_line_in_input39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_line0 = new BitSet(new long[]{0x0000000000000002L});

}