


/$Switch to enable adaptive level meter$/
hi enum d8PAgcAlmEn 4068
{
    off = 0,
    on = 1
};

/$Slope for transition between slow and fast attack time constant$/
hi double d8PAgcAlmSlopeAtt [4094,4095,4096,4097]
{-8.99609375,7.99609375};

/$Slope for transition region between fast and slow release time constants$/
hi double d8PAgcAlmSlopeRel [4090,4091,4092,4093]
{-8.99609375,7.99609375};

/$Fast attack time constant
Formula: (<value> + 1)*3/4
FormulaInv: 4/3*<value> - 1$/
hi int d8PAgcAlmTcAttFast [4078,4079,4080,4081]
{0,127};

/$Slow attack time constant
Formula: (<value> + 1)*3/4/128
FormulaInv: <value>*4/3*128 -1$/
hi int d8PAgcAlmTcAttSlow [4086,4087,4088,4089]
{0,31};

/$Switch to enable time constant control for this set of parameters$/
hi enum d8PAgcAlmTcCtrlEn [4069,4070,4071,4072]
{
    off = 0,
    on = 1
};

/$Switch to choose between slow and fast time constants for bands with disabled time constant control$/
hi enum d8PAgcAlmTcFixtype 4073
{
    fast = 0,
    slow = 1
};

/$Fast release time constant
Formula: (<value> + 1)*3/4
FormulaInv: 4/3*<value> - 1$/
hi int d8PAgcAlmTcRelFast [4074,4075,4076,4077]
{0,127};

/$Slow release time constant
Formula: (<value> + 1)*3/4/128
Formulainv: <value>*4/3*128 -1$/
hi int d8PAgcAlmTcRelSlow [4082,4083,4084,4085]
{0,31};

/$Slow attack time constants are used below this threshold for level differences$/
hi enum d8PAgcAlmThrLdAttSlow [4100,4101,4102,4103]
{
    _0dB = 0,
    P1dB = 1,
    P2dB = 2,
    P3dB = 3,
    P4dB = 4,
    P5dB = 5,
    P6dB = 6,
    P7dB = 7,
    P8dB = 8,
    P9dB = 9,
    P10dB = 10,
    P11dB = 11,
    P12dB = 12,
    P13dB = 13,
    P14dB = 14,
    P15dB = 15,
    P16dB = 16,
    P17dB = 17,
    P18dB = 18,
    P19dB = 19,
    P20dB = 20,
    P21dB = 21,
    P22dB = 22,
    P23dB = 23,
    P24dB = 24,
    P25dB = 25,
    P26dB = 26,
    P27dB = 27,
    P28dB = 28,
    P29dB = 29,
    P30dB = 30,
    P31dB = 31
};

/$Slow release time constants are used above this threshold for level differences$/
hi enum d8PAgcAlmThrLdRelSlow [4098,4099,5000,5001]
{
    _0dB = 0,
    M1dB = 1,
    M2dB = 2,
    M3dB = 3,
    M4dB = 4,
    M5dB = 5,
    M6dB = 6,
    M7dB = 7,
    M8dB = 8,
    M9dB = 9,
    M10dB = 10,
    M11dB = 11,
    M12dB = 12,
    M13dB = 13,
    M14dB = 14,
    M15dB = 15,
    M16dB = 16,
    M17dB = 17,
    M18dB = 18,
    M19dB = 19,
    M20dB = 20,
    M21dB = 21,
    M22dB = 22,
    M23dB = 23,
    M24dB = 24,
    M25dB = 25,
    M26dB = 26,
    M27dB = 27,
    M28dB = 28,
    M29dB = 29,
    M30dB = 30,
    M31dB = 31
};

/$Attack time constant for input broadband level meter$/
hi enum d8PAgcBblevelTcAtt 5006
{
    _2s0 = 0,
    _2sM1 = 1,
    _2sM2 = 2,
    _2sM3 = 3,
    _2sM4 = 4,
    _2sM5 = 5,
    _2sM6 = 6,
    _2sM7 = 7
};

/$Release time constant for input broadband level meter$/
hi enum d8PAgcBblevelTcRel 5007
{
    _2s0 = 0,
    _2sM1 = 1,
    _2sM2 = 2,
    _2sM3 = 3,
    _2sM4 = 4,
    _2sM5 = 5,
    _2sM6 = 6,
    _2sM7 = 7
};

/$Compression concept channel coupling scheme $/
hi enum d8PAgcCc 11
{
    symmetric_narrow = 0,
    symmetric = 1,
    asymmetric_narrow = 2,
    asymmetric = 3
};

/$Enable compression concept$/
hi enum d8PAgcCcEn 12
{
    off = 0,
    on = 1
};

/$Channel individual gain$/
hi ordered enum d8PAgcChannelGain [13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60]
{
    LogicalPosition_0 = 0,
    LogicalPosition_1 = 1,
    LogicalPosition_2 = 2,
    LogicalPosition_3 = 3,
    LogicalPosition_4 = 4,
    LogicalPosition_5 = 5,
    LogicalPosition_6 = 6,
    LogicalPosition_7 = 7,
    LogicalPosition_8 = 8,
    LogicalPosition_9 = 9,
    LogicalPosition_10 = 10,
    LogicalPosition_11 = 11,
    LogicalPosition_12 = 12,
    LogicalPosition_13 = 13,
    LogicalPosition_14 = 14,
    LogicalPosition_15 = 15,
    LogicalPosition_16 = 16,
    LogicalPosition_17 = 17,
    LogicalPosition_18 = 18,
    LogicalPosition_19 = 19,
    LogicalPosition_20 = 20,
    LogicalPosition_21 = 21,
    LogicalPosition_22 = 22,
    LogicalPosition_23 = 23,
    LogicalPosition_24 = 24,
    LogicalPosition_25 = 25,
    LogicalPosition_26 = 26,
    LogicalPosition_27 = 27,
    LogicalPosition_28 = 28,
    LogicalPosition_29 = 29,
    LogicalPosition_30 = 30,
    LogicalPosition_31 = 31,
    LogicalPosition_32 = 32,
    LogicalPosition_33 = 33,
    LogicalPosition_34 = 34,
    LogicalPosition_35 = 35,
    LogicalPosition_36 = 36,
    LogicalPosition_37 = 37,
    LogicalPosition_38 = 38,
    LogicalPosition_39 = 39,
    LogicalPosition_40 = 40,
    LogicalPosition_41 = 41,
    LogicalPosition_42 = 42,
    LogicalPosition_43 = 43,
    LogicalPosition_44 = 44,
    LogicalPosition_45 = 45,
    LogicalPosition_46 = 46,
    LogicalPosition_47 = 47,
    LogicalPosition_48 = 48,
    LogicalPosition_49 = 49,
    LogicalPosition_50 = 50,
    LogicalPosition_51 = 51,
    LogicalPosition_52 = 52,
    LogicalPosition_53 = 53,
    LogicalPosition_54 = 54,
    LogicalPosition_55 = 55,
    LogicalPosition_56 = 56,
    LogicalPosition_57 = 57,
    LogicalPosition_58 = 58,
    LogicalPosition_59 = 59,
    LogicalPosition_60 = 60,
    LogicalPosition_61 = 61,
    LogicalPosition_62 = 62,
    LogicalPosition_63 = 63,
    LogicalPosition_64 = 64,
    LogicalPosition_65 = 65,
    LogicalPosition_66 = 66,
    LogicalPosition_67 = 67,
    LogicalPosition_68 = 68,
    LogicalPosition_69 = 69,
    LogicalPosition_70 = 70,
    LogicalPosition_71 = 71,
    LogicalPosition_72 = 72,
    LogicalPosition_73 = 73,
    LogicalPosition_74 = 74,
    LogicalPosition_75 = 75,
    LogicalPosition_76 = 76,
    LogicalPosition_77 = 77,
    LogicalPosition_78 = 78,
    LogicalPosition_79 = 79,
    LogicalPosition_80 = 80,
    LogicalPosition_81 = 81,
    LogicalPosition_82 = 82,
    LogicalPosition_83 = 83,
    LogicalPosition_84 = 84,
    LogicalPosition_85 = 85,
    LogicalPosition_86 = 86,
    LogicalPosition_87 = 87,
    LogicalPosition_88 = 88,
    LogicalPosition_89 = 89,
    LogicalPosition_90 = 90,
    LogicalPosition_91 = 91,
    LogicalPosition_92 = 92,
    LogicalPosition_93 = 93,
    LogicalPosition_94 = 94,
    LogicalPosition_95 = 95,
    LogicalPosition_96 = 96,
    LogicalPosition_97 = 97,
    LogicalPosition_98 = 98,
    LogicalPosition_99 = 99,
    LogicalPosition_100 = 100,
    LogicalPosition_101 = 101,
    LogicalPosition_102 = 102,
    LogicalPosition_103 = 103,
    LogicalPosition_104 = 104,
    LogicalPosition_105 = 105,
    LogicalPosition_106 = 106,
    LogicalPosition_107 = 107,
    LogicalPosition_108 = 108,
    LogicalPosition_109 = 109,
    LogicalPosition_110 = 110,
    LogicalPosition_111 = 111,
    LogicalPosition_112 = 112,
    LogicalPosition_113 = 113,
    LogicalPosition_114 = 114,
    LogicalPosition_115 = 115,
    LogicalPosition_116 = 116,
    LogicalPosition_117 = 117,
    LogicalPosition_118 = 118,
    LogicalPosition_119 = 119,
    LogicalPosition_120 = 120,
    LogicalPosition_121 = 121,
    LogicalPosition_122 = 122,
    LogicalPosition_123 = 123,
    LogicalPosition_124 = 124,
    LogicalPosition_125 = 125,
    LogicalPosition_126 = 126,
    LogicalPosition_127 = 127
};

/$Switch to enable broadband control of channel dependent third knee point$/
const hi enum d8PAgcCk3BbEn 4104
{
    off = 0,
    on = 1
};

/$Enable CK3, ALM only$/
hi enum d8PAgcCk3En 5010
{
    off = 0,
    on = 1
};

/$Enable dirmic-dependent gain limitation for increased feedback stability$/
hi enum d8PAgcDirGainAttEn 123
{
    off = 0,
    on = 1
};

/$Switch to force slow smoothing for dual AGC-i$/
hi enum d8PAgcDualSlow 126
{
    fastdslow = 0,
    forced_slow = 1
};

/$Switch between dual and syllabic compression$/
hi enum d8PAgcType [372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391]
{
    syllabic = 0,
    dual = 1
};

/$Enable the hardware part of the classification system. When disabled class 0 (SiQ) is output.$/
hi enum d8PClaEn 496
{
    off = 0,
    on = 1
};

/$Enable firmware control input for FINE$/
hi enum d8PCtrlFineEnable 500
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for SWINE$/
hi enum d8PCtrlSwnEnable 524
{
    disabled = 0,
    enabled = 1
};

/$Enable FBC for both microphones or front-mic only$/
hi enum d8PFbcDualEn 765
{
    frontMmic = 0,
    both_microphones = 1
};

/$Enable feedback canceller or disable module except for split-band filter (See p_fbc_sbf_en)$/
hi enum d8PFbcEn 766
{
    off = 0,
    on = 1
};

/$Enable/disable split-band filter in FBC output submodule$/
hi enum d8PFbcFsSbfEn 789
{
    off = 0,
    on = 1
};

/$Enable FBC channel level processing$/
hi enum d8PFbcLevEnNra 861
{
    off = 0,
    on = 1
};

/$Enable/disable complete FBC output submodule$/
hi enum d8PFbcPshFsEn 883
{
    off = 0,
    on = 1
};

/$Enable FBC split-band filter$/
hi enum d8PFbcSbfEn 893
{
    off = 0,
    on = 1
};

/$Mode (combination of front/rear mic) for lowering the step-size for transient excitation$/
hi enum d8PFbcSsLimMode 905
{
    front_mic_only = 0,
    maxfrontrear = 1,
    minfrontrear = 2,
    meanfrontrear = 3
};

/$Enable frequency compression$/
hi enum d8PFcoSpcEn 927
{
    off = 0,
    on = 1
};

/$Factor for FINE delta gain computation$/
hi ordered enum d8PFinDeltaGain 939
{
    _1 = 0,
    _05 = 1,
    _025 = 2,
    _0125 = 3
};

/$Limit matching range$/
hi enum d8PMicFmmLimit 4065
{
    PdM12dB = 0,
    PdM9dB = 1,
    PdM6dB = 2,
    PdM3dB = 3
};

/$Band parameterization for modifying FANCY noise floor. This parameter provides an upper limit for the adaptive noise floor for SPASS/FANCY. Hence, an at least effect is adjusted by this parameter.  This parameter influences SPASS and also pure FANCY if p_mic_spass_mod_nf_for_fny_pure is set to on.$/
hi enum d8PMicSpassUlimAutoNf [1548,1549,1550,1551,1552,1553,1554,1555]
{
    _0_dB = 0,
    M45_dB = 1,
    M6_dB = 2,
    M75_dB = 3,
    M9_dB = 4,
    M105_dB = 5,
    M12_dB = 6,
    unlimited = 7
};

/$Additional slow down of release time constant by additional shifts. Slower AGC-o release times can help to improve feedback stability in certain situations. $/
hi ordered enum d8POutAgcoAddTcRel [1617,1618]
{
    none = 0,
    _1_shift = 1,
    _2_shifts = 2,
    _3_shifts = 3
};

/$Gain configuration for path[1:9].
Path[1:9] = [mic_F, mic_R, tcoil, dai, astr, ind_voice, ind_melody, tone, wls_in]
shall not be used for sensor offset compensation anymore$/
hi int d8PSmxGainCfg [1865,1866,1867,1868,1869,1870,1871,1872,1873]
{-128,127};

/$Enable switch for shifi in aux path$/
hi enum d8PSmxShifiAuxEn 1932
{
    off = 0,
    on = 1
};

/$Enable switch for shifi in mic path$/
hi enum d8PSmxShifiMicEn 1933
{
    off = 0,
    on = 1
};

/$Gain for synthetic signal part for aux path$/
hi ordered enum d8PSmxShifiStyleGainAux 1934
{
    _0_dB = 0,
    M3_dB = 1,
    M6_dB = 2,
    M9_dB = 3,
    M12_dB = 4,
    M15_dB = 5,
    M18_dB = 6,
    M21_dB = 7
};

/$Gain for synthetic signal part for mic path$/
hi ordered enum d8PSmxShifiStyleGainMic 1935
{
    _0_dB = 0,
    M3_dB = 1,
    M6_dB = 2,
    M9_dB = 3,
    M12_dB = 4,
    M15_dB = 5,
    M18_dB = 6,
    M21_dB = 7
};

/$Enable SingleMic Wind Noise Elimination (SWINE)$/
hi enum d8PSwnEn 2097
{
    off = 0,
    on = 1
};

/$Enable channel-based tinnitus masker$/
hi enum d8PTinEn 2262
{
    off = 0,
    on = 1
};

/$Channel gain for each of the 20 frequency bands. These gains are used to determine the relative PSD (i.e. frequency shaping) of the tinnitus noise signal.$/
hi ordered enum d8PTinGain [2263,2264,2265,2266,2267,2268,2269,2270,2271,2272,2273,2274,2275,2276,2277,2278,2279,2280,2281,2282]
{
    LogicalPosition_0 = 0,
    LogicalPosition_1 = 1,
    LogicalPosition_2 = 2,
    LogicalPosition_3 = 3,
    LogicalPosition_4 = 4,
    LogicalPosition_5 = 5,
    LogicalPosition_6 = 6,
    LogicalPosition_7 = 7,
    LogicalPosition_8 = 8,
    LogicalPosition_9 = 9,
    LogicalPosition_10 = 10,
    LogicalPosition_11 = 11,
    LogicalPosition_12 = 12,
    LogicalPosition_13 = 13,
    LogicalPosition_14 = 14,
    LogicalPosition_15 = 15,
    LogicalPosition_16 = 16,
    LogicalPosition_17 = 17,
    LogicalPosition_18 = 18,
    LogicalPosition_19 = 19,
    LogicalPosition_20 = 20,
    LogicalPosition_21 = 21,
    LogicalPosition_22 = 22,
    LogicalPosition_23 = 23,
    LogicalPosition_24 = 24,
    LogicalPosition_25 = 25,
    LogicalPosition_26 = 26,
    LogicalPosition_27 = 27,
    LogicalPosition_28 = 28,
    LogicalPosition_29 = 29,
    LogicalPosition_30 = 30,
    LogicalPosition_31 = 31
};

/$Switch between mixed signal and noise or pure noise output for channel-based tinnitus masker$/
hi enum d8PTinMode 2285
{
    mixed = 0,
    pure = 1
};

/$Release time constant for CEN effect smoothing used for determination of s_lrn_cen_effect$/
hi enum d9PCenLogTcRel 5015
{
    _0 = 0,
    _41s = 1,
    _82s = 2,
    _164s = 3
};

/$Enable the consonant enhancer algorithm (with parameter p_sys_mode_d9 activated)$/
hi enum d9PCenEn 5012
{
    disable = 0,
    enable = 1
};

/$Enable bypass for smoothing with p_cen_tc_ld_level before CEN LD level meter $/
hi enum d9PCenBypassLevelSmooth 5011
{
    disable = 0,
    enable = 1
};

/$Select which levels shall be used in the later signal processing (cc, compression, ...)$/
hi enum d9PCenLevelSel 5013
{
    use_unmodified_old_level_calculation = 0,
    calculate_level_on_the_CENMmodified_signal = 1
};

/$Attack time constant for CEN effect smoothing used for determination of s_lrn_cen_effect$/
hi enum d9PCenLogTcAtt 5014
{
    _0 = 0,
    _41s = 1,
    _82s = 2,
    _164s = 3
};

/$Enable adaptive microphone noise reduction (aMNR)$/
hi enum d9PMicAmnrEn 5112
{
    disable = 0,
    enable = 1
};

/$Threshold of magnitude ratio which needs to be exceeded for action in internally controlled part$/
hi int d9PCenMagnRatioThr 5016
{0,31};

/$Enable NELE$/
hi enum d9PNleEn 5321
{
    off = 0,
    on = 1
};

/$Broadband shift of the NELE channel gains$/
hi enum d9PNleGainShiftLd 5323
{
    _0_dB = 0,
    _12_dB = 1,
    _24_dB = 2,
    _36_dB = 3,
    _48_dB = 4,
    _60_dB = 5,
    _72_dB = 6,
    _84_dB = 7
};

/$Time constant setting for LD-Level smoothing; equivalent to nra/nru/ch_level/p_nru_tc_ld_level$/
hi enum d9PCenTcLdLevel 5018
{
    _2sM1 = 0,
    _2sM2 = 1
};

/$Enable adaptive inherent noise reduction in auxillary path (AUX INR)$/
hi enum d9PAuxInrEn 5074
{
    disable = 0,
    enable = 1
};

/$Gain of original signal which is one summand of the CEN-output-signal, Bit = 7 ==> "CEN only"-mode$/
hi enum d9PCenOrigGain 5017
{
    _0dB = 0,
    M6dB = 1,
    M12dB = 2,
    M18dB = 3,
    M24dB = 4,
    M30dB = 5,
    M36dB = 6,
    Minf = 7
};

/$Nele gain modification per channel$/
hi enum d9PNleSnrmodifierLd [5327,5328,5329,5330,5331,5332,5333,5334]
{
    M459_dB = 0,
    M436_dB = 1,
    M414_dB = 2,
    M391_dB = 3,
    M369_dB = 4,
    M346_dB = 5,
    M324_dB = 6,
    M3010_dB = 7,
    M278_dB = 8,
    M256_dB = 9,
    M233_dB = 10,
    M211_dB = 11,
    M188_dB = 12,
    M166_dB = 13,
    M143_dB = 14,
    M12_dB = 15,
    M978_dB = 16,
    M753_dB = 17,
    M527_dB = 18,
    M301_dB = 19,
    M0753_dB = 20,
    _151_dB = 21,
    _376_dB = 22,
    _602_dB = 23,
    _828_dB = 24,
    _105_dB = 25,
    _128_dB = 26,
    _1510_dB = 27,
    _173_dB = 28,
    _196_dB = 29,
    _218_dB = 30,
    _241_dB = 31
};

/$Lowest allowed value of denominator mean level (denominator) in magnitude-ratio-calculation, (note: adjust such that for silence (very low level, e.g. due to noise; or more aggressive: for non-speech)  the following quotient delivers around 0. This parameter needs to be adjusted for the hearing aid environment.)$/
hi enum d9PCenThrLevelMean 5019
{
    M18dB = 0,
    M12dB = 1,
    M6dB = 2,
    _0dB = 3,
    _6dB = 4,
    _12dB = 5,
    _18dB = 6,
    _24dB = 7,
    _30dB = 8,
    _36dB = 9,
    _42dB = 10,
    _48dB = 11,
    _54dB = 12,
    _60dB = 13,
    _66dB = 14,
    _72dB = 15
};

/$Set master or slave mode$/
hi enum d9PWltMaster 5410
{
    slave = 0,
    master = 1
};

/$Global enable of ARC$/
const hi enum d9PArcEn 5023
{
    Off = 0,
    On = 1
};

/$Attenuation for auxiliary signal. Applies additionally to p_smx_aux_gain_bands$/
hi int d9PSmxAuxGainBandsAtten [5367,5368,5369,5370,5371,5372,5373,5374,5375,5376,5377,5378,5379,5380,5381,5382]
{0,63};

/$Attenuation for microphone signal. Applies additionally to p_smx_mic_gain_bands$/
hi int d9PSmxMicGainBandsAtten [5388,5389,5390,5391,5392,5393,5394,5395,5396,5397,5398,5399,5400,5401,5402,5403]
{0,63};

/$Binaural wind noise canceller (BWNC) enable$/
hi enum d9PMicBwncEn 5174
{
    disabled = 0,
    enabled = 1
};
