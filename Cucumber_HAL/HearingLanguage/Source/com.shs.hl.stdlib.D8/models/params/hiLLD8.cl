


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

/$Fast attack time constant$/
hi int d8PAgcAlmTcAttFast [4078,4079,4080,4081]
{0,127};

/$Slow attack time constant$/
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

/$Fast release time constant$/
hi int d8PAgcAlmTcRelFast [4074,4075,4076,4077]
{0,127};

/$Slow release time constant$/
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

/$Bypass agc level meter to have an instantaneous level$/
hi enum d8PAgcBypassLevelSmooth 5008
{
    off = 0,
    on_bypass_mode = 1
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

/$Select input signal for frequency-domain classifier features. Note that time-domain features are computed seperately, use SMX to select signal composition.$/
hi enum d8PClaInSelect 497
{
    mic_f = 0,
    auxPmic_f = 1,
    aux = 2,
    invalid = 3
};

/$Enable firmware control input for FINE$/
hi enum d8PCtrlFineEnable 500
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for FANCY$/
hi enum d8PCtrlFnyEnable 502
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for mic$/
hi enum d8PCtrlMicEnable 508
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for SelDir$/
hi enum d8PCtrlSelEnable 512
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for SPASS$/
hi enum d8PCtrlSpassEnable 518
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

/$Enable firmware control input for VAD$/
hi enum d8PCtrlVadEnable 527
{
    disabled = 0,
    enabled = 1
};

/$This amplitude threshold has to be exceeded by the increasing amp gain before a trigger is activated ("bathtub feature")$/
hi ordered enum d8PFbcAmpGainThr 634
{
    off = 0,
    _15_dB = 1,
    _3_dB = 2,
    _45_dB = 3
};

/$Threshold for sine detection via bandpass modulation$/
hi ordered enum d8PFbcDetSineThr 764
{
    disabled = 0,
    high_sensitive = 1,
    med_sensitive = 2,
    low_sensitive = 3
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

/$Mode of internal feedback detection$/
hi enum d8PFbcFbdMode 767
{
    off = 0,
    reset_only = 1,
    stepMsize_only = 2,
    stepMsize_and_reset = 3
};

/$Threshold for internal feedback detection, influencing sensitivity.$/
hi enum d8PFbcFbdThr 768
{
    low_sensitivity = 0,
    medium = 1,
    high_sensitivity = 2,
    very_high = 3
};

/$Forced reset due to internal feedback with reset of the coefficients independet of fbc_fbd_mode (except disable)$/
hi ordered enum d8PFbcFbdThrReset 769
{
    very_low_sensitivity = 0,
    low_sensitivity = 1,
    medium_sensitivity = 2,
    high_sensitivity = 3
};

/$Waiting time before re-triggering reset in case of persisting internal feedback$/
hi ordered enum d8PFbcFbdWait 770
{
    disabled = 0,
    _07_s = 1,
    _14_s = 2,
    _27_s = 3
};

/$Enable step-size control via FINE$/
hi enum d8PFbcFineEn 771
{
    off = 0,
    on = 1
};

/$Enable frequency shift decision logic$/
hi enum d8PFbcFsDetEn 776
{
    off = 0,
    on = 1
};

/$Enable frequency shifter$/
hi enum d8PFbcFsEn 777
{
    off = 0,
    on = 1
};

/$Enable/disable boost mode for frequency shifter$/
hi enum d8PFbcFsEnInc 778
{
    off = 0,
    on = 1
};

/$orce frequency shift and disable logic. Frequency shift will be unconditionally applied, depending only on p_fbc_fs_en$/
hi enum d8PFbcFsForce 779
{
    off = 0,
    on = 1
};

/$Amount of frequency shifting applied in Hz$/
hi enum d8PFbcFsFreq 780
{
    _1099_Hz = 0,
    _1465_Hz = 1,
    _2051_Hz = 2,
    _2490_Hz = 3
};

/$Consider and scale impulse detection for FS activation$/
hi ordered enum d8PFbcFsImpScale 781
{
    disabled = 0,
    low = 1,
    medium = 2,
    high = 3
};

/$Enable/disable split-band filter in FBC output submodule$/
hi enum d8PFbcFsSbfEn 789
{
    off = 0,
    on = 1
};

/$Enable use of the lower frequency SBF pair by default / when no decision can be taken$/
hi enum d8PFbcFsSbfLowPairEn 846
{
    higher = 0,
    lower = 1
};

/$Selection of split-band filter pair for dynamic switching$/
hi enum d8PFbcFsSbfPair 847
{
    _750_Hz_d_900_Hz = 0,
    _900_Hz_d_1100_Hz = 1,
    _1100_Hz_d_1400_Hz = 2,
    invalid = 3
};

/$Switch to enable a power complementary split-band filter for use with the frequency shift off mode$/
hi enum d8PFbcFsSbfPcEn 848
{
    off = 0,
    on = 1
};

/$Hold upper step-size in case of FS activation$/
hi ordered enum d8PFbcFsSsHold 850
{
    off = 0,
    _025_x = 1,
    _05_x = 2,
    _1_x = 3
};

/$Smooth step-size in case of FS deactivate$/
hi ordered enum d8PFbcFsSsOff 851
{
    off = 0,
    _50_ms_d_ss = 1,
    _100_ms_d_ss = 2,
    _200ms_d_ss = 3
};

/$Wait with step-size increase in case of FS on-set$/
hi ordered enum d8PFbcFsSsOn 852
{
    off = 0,
    _10_ms = 1,
    _50_ms = 2,
    _100ms = 3
};

/$Enable step-size control via impulse detector$/
hi enum d8PFbcImpEn 854
{
    off = 0,
    on = 1
};

/$Use signal versions without split-band filtering as input for impulse detector$/
hi enum d8PFbcImpPure 855
{
    with_SBF = 0,
    without_SBF = 1
};

/$Sensibility of impulse detection, threshold of detection onset$/
hi ordered enum d8PFbcImpThr 856
{
    highly_sensitive = 0,
    sensitive = 1,
    normal = 2,
    low_sensitive = 3
};

/$Sensibility of impulse detection, i.e. (relative) threshold of detection offset$/
hi ordered enum d8PFbcImpThrOff 857
{
    hysteresis_off = 0,
    low = 1,
    normal = 2,
    sensitive = 3
};

/$Threshold for non-transient sinusiodals to take effect$/
hi ordered enum d8PFbcLevAbsPrb 860
{
    high_sensitive = 0,
    med_sensitive = 1,
    low_sensitive = 2,
    disabled = 3
};

/$Enable FBC channel level processing$/
hi enum d8PFbcLevEnNra 861
{
    off = 0,
    on = 1
};

/$Threshold for transient sinusiodals to take effect$/
hi ordered enum d8PFbcLevImpPrb 864
{
    high_sensitive = 0,
    med_sensitive = 1,
    low_sensitive = 2,
    disabled = 3
};

/$Complete channel levels to detect low-level sinusoidals$/
hi ordered enum d8PFbcLevLowsineComplNra 865
{
    off = 0,
    _10 = 1,
    _15 = 2,
    _20 = 3
};

/$Weighting of low-level sinusoidals$/
hi ordered enum d8PFbcLevLowsineWeightNra 866
{
    off = 0,
    M3_dB = 1,
    M6_dB = 2,
    M12_dB = 3
};

/$Activate FS when maximum > frequency$/
hi ordered enum d8PFbcLevMaxBand 867
{
    SBF = 0,
    SBF_P_250Hz = 1,
    SBF_P_500Hz = 2,
    SBF_P_750Hz = 3
};

/$Threshold for maximum detection to take effect$/
hi ordered enum d8PFbcLevMaxPrb 868
{
    _60_dB_SPL = 0,
    _72_dB_SPL = 1,
    _84_dB_SPL = 2,
    disabled = 3
};

/$Evaluate maximum with smoothed levels$/
hi enum d8PFbcLevSoftMaxNra 869
{
    actual_levels = 0,
    smoothed_levels = 1
};

/$Band weighting before evaluation of maximum channel level$/
hi ordered enum d8PFbcLevWeightsNra 870
{
    disabled = 0,
    set1 = 1,
    set2 = 2,
    set3 = 3
};

/$Threshold for FBC-freeze for low-level input signals (identical to MNR but in 4dB steps)$/
hi ordered enum d8PFbcLowlevThr 871
{
    M118_dBFS_RMS = 0,
    M114_dBFS_RMS = 1,
    M110_dBFS_RMS = 2,
    M106_dBFS_RMS = 3,
    M102_dBFS_RMS = 4,
    M98_dBFS_RMS = 5,
    M94_dBFS_RMS = 6,
    M90_dBFS_RMS = 7,
    M86_dBFS_RMS = 8,
    M82_dBFS_RMS = 9,
    M78_dBFS_RMS = 10,
    M74_dBFS_RMS = 11,
    M70_dBFS_RMS = 12,
    M66_dBFS_RMS = 13,
    M62_dBFS_RMS = 14,
    M58_dBFS_RMS = 15
};

/$Signal to be observed for FBC-freeze for low-level input signals$/
hi enum d8PFbcLowlevMic 872
{
    noneddisabled = 0,
    front_mic = 1,
    rear_mic = 2,
    receiver = 3
};

/$Configuration of pre-whitening highpass filter L(z) to slow down adaptation speed for low-frequencies$/
hi ordered enum d8PFbcLzMode 873
{
    disabled = 0,
    _0dB_at_1500Hz = 1,
    _0dB_at_1800Hz = 2,
    _0dB_at_2500Hz = 3
};

/$Threshold for phase shaker detection$/
hi ordered enum d8PFbcPshDecisionBound 879
{
    _6 = 0,
    _7 = 1,
    _8 = 2,
    _9 = 3,
    _10 = 4,
    _11 = 5,
    _12 = 6,
    _13 = 7
};

/$Enable phase shaker detector$/
hi enum d8PFbcPshDetEn 880
{
    off = 0,
    on = 1
};

/$Enable/disable phase shaker$/
hi enum d8PFbcPshEn 881
{
    off = 0,
    on = 1
};

/$Enable phase shaker detection filter bank$/
hi enum d8PFbcPshFbEn 882
{
    off = 0,
    on = 1
};

/$Enable/disable complete fbc output submodule$/
hi enum d8PFbcPshFsEn 883
{
    off = 0,
    on = 1
};

/$Shaking frequency of phase shaker$/
hi ordered enum d8PFbcPshFshake 884
{
    _14_Hz = 0,
    _17_Hz = 1,
    _23_Hz = 2,
    _30_Hz = 3
};

/$Use microphone signal or error signal for phase shaking detector$/
hi enum d8PFbcPshMic 885
{
    micMsignal = 0,
    errorMsignal = 1
};

/$Use front or rear mic for phase shaking detector$/
hi enum d8PFbcPshSide 886
{
    front = 0,
    rear = 1
};

/$Time constant of time-amplitude converter mean detector$/
hi ordered enum d8PFbcPshTaConvGl 888
{
    _2sM10 = 0,
    _2sM9 = 1,
    _2sM8 = 2,
    _2sM7 = 3
};

/$Smoothed mapping of feedback detection to step-size$/
hi ordered enum d8PFbcPshTremoloGl 889
{
    disabled = 0,
    _05 = 1,
    _0375 = 2,
    _025 = 3
};

/$Smoothed mapping value is raised$/
hi ordered enum d8PFbcPshTremoloRaise 890
{
    disabled = 0,
    _1125 = 1,
    _125 = 2,
    _15 = 3
};

/$Phase-shaker detection threshold: Number of phase detection events before the step-size is raised$/
hi ordered enum d8PFbcPshTriggerSchw 891
{
    _1_detection = 0,
    _3_detections = 1,
    _5_detections = 2,
    _7_detections = 3
};

/$Enable the delay raise of the step-size$/
hi enum d8PFbcPshTriggerSchwEn 892
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

/$Scaling factor: Scale output of FIR-filter to better exploit its dynamic range$/
hi ordered enum d8PFbcScalFir 896
{
    _2s0 = 0,
    _2s2 = 1,
    _2sM2 = 2,
    _2sM4 = 3
};

/$Add lowpass component of receiver signal$/
hi ordered enum d8PFbcScalTp 897
{
    off = 0,
    _2sM8 = 1,
    _2sM7 = 2,
    _2sM5 = 3
};

/$Time constant for soft-reset of FBC filter coefficients.$/
hi ordered enum d8PFbcSoftresetTc 898
{
    off = 0,
    _2sM7 = 1,
    _2sM8 = 2,
    _2sM9 = 3
};

/$Enable step-size control via firmware (experimental)$/
hi enum d8PFbcSsFwEn 903
{
    off = 0,
    on = 1
};

/$Enable transient-derived limitation of step-size: Increase robustness for transient excitation$/
hi enum d8PFbcSsLimEn 904
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

/$Sensitivity for lowering the step-size for transient excitation$/
hi ordered enum d8PFbcSsLimThr 907
{
    very_low = 0,
    low = 1,
    medium = 2,
    sensitive = 3,
    more_sensitive = 4,
    high = 5,
    very_high = 6,
    extremely_sensitive = 7
};

/$Freeze FBC coefficient adaptation when no feedback is detected$/
hi enum d8PFbcSsLowFreeze 908
{
    off = 0,
    freeze_enabled = 1
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

/$Enable of FANCY unit. Must also be on for SPASS funccionality. For pure FANCY attenuation, p_mic_spass_mode_select must further be set to "FANCY"$/
hi enum d8PNruFnyEn 1569
{
    off = 0,
    on = 1
};

/$Enable of level dependent noise reduction (LDNR) unit$/
hi enum d8PNruLdnrEn 1589
{
    off = 0,
    on = 1
};

/$Enable switch of MNR unit$/
hi enum d8PNruMnrEn 1592
{
    off = 0,
    on = 1
};

/$Select MNR expansion ratio$/
hi enum d8PNruMnrRatio 1593
{
    _12 = 0,
    _13 = 1
};

/$Enable of VAD unit$/
hi enum d8PNruVadEn 1606
{
    off = 0,
    on = 1
};

/$Additional slow down of release time constant by additional shifts. Slower AGC-o release times can help to improve feedback stability in certain situations. $/
hi ordered enum d8POutAgcoAddTcRel [1617,1618]
{
    none = 0,
    _1_shift = 1,
    _2_shifts = 2,
    _3_shifts = 3
};

/$Depending on p_sel_force_omni_en the decision is forced to omni when noise floor level is below threshold, meaning that the noise level is too low to achieve a significant benefit from a directional microphone.$/
hi enum d8PSelOmniThr 1707
{
    _50_dBspl = 0,
    _56_dBspl = 1,
    _38_dBspl = 2,
    _44_dBspl = 3
};

/$Tthe omni-directional microphone characteristic can be replaced by cardioid directionality. This functionality is controlled by the parameter sel_ replaceomnibycard. If p_sel_ replaceomnibycard is set to true, the anti-cardioid feature is scaled by the factor p_sel_card_anti.$/
hi enum d8PSelReplaceomnibycard 1708
{
    use_omni = 0,
    use_card = 1
};

/$The binaurally synchronized decision provided by the firmware, p_sel_wls_newidx , can be ignored by setting the parameter sel_wls_bypass to true. Then, the local index decision is used$/
hi enum d8PSelWlsBypass 1714
{
    force_omni_from_firmware = 0,
    local_force_omni_decision = 1
};

/$Gain configuration for path[1:8].  
Path[1:8] = [mic_F, mic_R, tcoil, dai, astr, ind_voice, ind_melody, tone]
shall not be used for sensor offset compensation anymore$/
hi int d8PSmxGainCfg [1865,1866,1867,1868,1869,1870,1871,1872]
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

/$Enable twin-mic wind noise canceller (WNC)$/
hi enum d8PWncEn 2459
{
    off = 0,
    on = 1
};
