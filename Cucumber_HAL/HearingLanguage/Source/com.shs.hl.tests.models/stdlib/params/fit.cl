/$current experience level, ClComment is used for generate fitting enum, name for HAL
experienced     Experienced
unexperienced   New$/
fit enum ExpLevel 23000 {
    experienced       = 0,    //Experienced
    unexperienced     = 1    //New
};

/$current acclimatisation level$/
fit enum AccLevel 23001 {
    None              = 0,
    Acc1              = 1,
    Acc2              = 2,
    Acc3              = 3,
    Acc4              = 4,
    AccSPF            = 5,
    AccSPLF           = 6
};

/$original fitting formula as selected by Connexx-user
NalNl1X         NAL Non-Linear 1
Gain3           Third Gain
NalNl2Orig      original NAL Non-Linear 2
DSLmioOrig      original DSLmio
ConnexxFit      Connexx Fit$/
const fit enum SelectableFormula 23002 {
    NalNl1X           = 1,    //NAL Non-Linear 1
    Gain3             = 4,    //Third Gain
    NalNl2Orig        = 12,    //original NAL Non-Linear 2
    DSLmioOrig        = 13,    //original DSLmio
    ConnexxFit        = 16    //Connexx Fit
};

/$fitting formula as calculated by Target-macros
Berger          Berger
NalNl1X         NAL Non-Linear 1
NalRp           NAL FORMULA
Pogo2           POGO2
Gain3           Third Gain
Gain2           Half Gain
Fig6            Fig 6
Open            HIFIT FORMULA for instant fit
NalBall         NAL BALL (moderate level is equal to NAL-RP)
NalNl2Orig      original NAL Non-Linear 2
DSLmioOrig      original DSLmio$/
fit enum CalculationFormula 23003 {
    Berger            = 0,    //Berger
    NalNl1X           = 1,    //NAL Non-Linear 1
    NalRp             = 2,    //NAL FORMULA
    Pogo2             = 3,    //POGO2
    Gain3             = 4,    //Third Gain
    Gain2             = 5,    //Half Gain
    Fig6              = 6,    //Fig 6
    Open              = 8,    //HIFIT FORMULA for instant fit
    NalBall           = 9,    //NAL BALL (moderate level is equal to NAL-RP)
    NalNl2Orig        = 12,    //original NAL Non-Linear 2
    DSLmioOrig        = 13    //original DSLmio
};

/$stimulus for HI simulation
Sine            Sine
WhiteNoise      WhiteNoise
PinkNoise       PinkNoise
IECNoise        IECNoise
AurinsNoise     AurinsNoise
Ltass           Ltass
ShortTimeSpeech ShortTimeSpeech$/
fit enum Stimulus 23004 {
    Sine              = 0,    //Sine
    WhiteNoise        = 1,    //WhiteNoise
    PinkNoise         = 2,    //PinkNoise
    IECNoise          = 3,    //IECNoise
    AurinsNoise       = 4,    //AurinsNoise
    Ltass             = 7,    //Ltass
    ShortTimeSpeech   = 8    //ShortTimeSpeech
};

/$gain spl mode for HI simulation
GainMode_Gain   GainMode_Gain
GainMode_OutPut GainMode_OutPut$/
fit enum GainSplMode 23005 {
    GainMode_Gain     = 0,    //GainMode_Gain
    GainMode_OutPut   = 1    //GainMode_OutPut
};

/$measurement condition for HI simulation
REIG            REIG
REAG            REAG
EarSimulator    EarSimulator
Coupler2cc      Coupler2cc
REAR            REAR$/
fit enum MeasCond 23006 {
    REIG              = 0,    //REIG
    REAG              = 1,    //REAG
    EarSimulator      = 2,    //EarSimulator
    Coupler2cc        = 3,    //Coupler2cc
    REAR              = 4    //REAR
};

/$flag indicating, if ShortTimeSpeech stimulus shall be used for fitting$/
fit enum ShortTimeSpeech 23008 {
    disabled          = 0,
    enabled           = 1
};

/$recd type names$/
fit enum CouplerType 23009 {
    HA1               = 0,
    HA2               = 1,
    Hybrid            = 2
};

/$recd type names$/
fit enum TipMold 23010 {
    manualRECD_tip    = 0,
    manualRECD_mould  = 1,
    manualRECD_Other  = 2
};

/$current tonal language$/
fit enum TonalLanguage 23012 {
    NonTonal          = 0,
    Tonal             = 1
};

/$current TransducerType$/
fit enum TransducerType 23014 {
    InsertTip         = 1,
    InsertMold        = 2,
    TDH               = 3,
    Loudspeaker0Deg   = 4,
    Loudspeaker45Deg  = 5,
    Loudspeaker90Deg  = 6,
    RealearSpl        = 7
};

/$current AudUnitSrc$/
fit enum AudUnitSrc 23015 {
    dBHL              = 1,
    SPL               = 2,
    eHL               = 3,
    nHL               = 4
};

/$current RECDMeasMethod$/
fit enum RECDMeasMethod 23016 {
    HA1_tip           = 0,
    HA1_mould         = 1,
    HA2_tip           = 2,
    HA2_mould         = 3
};

/$current REUGMeasMethod$/
fit enum REUGMeasMethod 23017 {
    SF0               = 0,
    SF45              = 1,
    SF90              = 2
};

/$current shell$/
fit enum Shell 23013 {
    BTE               = 0,
    FullShell         = 1,
    HalfShell         = 2,
    Canal             = 3,
    MiniCanal         = 4,
    CIC               = 5,
    Module            = 6,
    Implant           = 7
};

