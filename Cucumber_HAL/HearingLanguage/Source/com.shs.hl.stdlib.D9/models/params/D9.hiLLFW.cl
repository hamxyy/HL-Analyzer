


/$acclimatization ck offset in 20 bands$/
hi ordered enum d8FAacCk1Offset [6002,6003,6004,6005,6006,6007,6008,6009,6010,6011,6012,6013,6014,6015,6016,6017,6018,6019,6020,6021]
{
    _0_dB = 0,
    M1dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    _1_dB = 16,
    _2_dB = 17,
    _3_dB = 18,
    _4_dB = 19,
    _5_dB = 20,
    _6_dB = 21,
    _7_dB = 22,
    _8_dB = 23,
    _9_dB = 24,
    _10_dB = 25,
    _11_dB = 26,
    _12_dB = 27,
    _13_dB = 28,
    _14_dB = 29,
    _15_dB = 30,
    _16_dB = 31
};

/$acclimatization cr offset in 20 bands$/
hi ordered enum d8FAacCr1Offset [6022,6023,6024,6025,6026,6027,6028,6029,6030,6031,6032,6033,6034,6035,6036,6037,6038,6039,6040,6041]
{
    _0d16_dB = 0,
    M1d16_dB = 1,
    M2d16_dB = 2,
    M3d16_dB = 3,
    M4d16_dB = 4,
    M5d16_dB = 5,
    M6d16_dB = 6,
    M7d16_dB = 7,
    M8d16_dB = 8,
    M9d16_dB = 9,
    M10d16_dB = 10,
    M11d16_dB = 11,
    M12d16_dB = 12,
    M13d16_dB = 13,
    M14d16_dB = 14,
    M15d16_dB = 15,
    M16d16_dB = 16,
    _1d16_dB = 17,
    _2d16_dB = 18,
    _3d16_dB = 19,
    _4d16_dB = 20,
    _5d16_dB = 21,
    _6d16_dB = 22,
    _7d16_dB = 23,
    _8d16_dB = 24,
    _9d16_dB = 25,
    _10d16_dB = 26,
    _11d16_dB = 27,
    _12d16_dB = 28,
    _13d16_dB = 29,
    _14d16_dB = 30,
    _15d16_dB = 31
};

/$enable of automatic acclimatization$/
hi enum d8FAacEn 6042
{
    off = 0,
    enable = 1
};

/$acclimatization gain offset in 20 bands$/
const hi ordered enum d8FAacGainOffset [6043,6044,6045,6046,6047,6048,6049,6050,6051,6052,6053,6054,6055,6056,6057,6058,6059,6060,6061,6062]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    M16_dB = 16,
    M15_dB = 17,
    M14_dB = 18,
    M13_dB = 19,
    M12_dB = 20,
    M11_dB = 21,
    M10_dB = 22,
    M9_dB = 23,
    M8_dB = 24,
    M7_dB = 25,
    M6_dB = 26,
    M5_dB = 27,
    M4_dB = 28,
    M3_dB = 29,
    M2_dB = 30,
    M1_dB = 31
};

/$The intelligent acclimatization observes the volume control changes of the user and adapts the acclimatization steps accordingly.$/
hi enum d8FAacIaacEn 6063
{
    off = 0,
    enable = 1
};

/$First kneepoint$/
hi ordered enum d8FAgcCk1 [8805,8806,8807,8808,8809,8810,8811,8812,8813,8814,8815,8816,8817,8818,8819,8820,8821,8822,8823,8824]
{
    _120_dBspl = 0,
    _119_dBspl = 1,
    _118_dBspl = 2,
    _117_dBspl = 3,
    _116_dBspl = 4,
    _115_dBspl = 5,
    _114_dBspl = 6,
    _113_dBspl = 7,
    _112_dBspl = 8,
    _111_dBspl = 9,
    _110_dBspl = 10,
    _109_dBspl = 11,
    _108_dBspl = 12,
    _107_dBspl = 13,
    _106_dBspl = 14,
    _105_dBspl = 15,
    _104_dBspl = 16,
    _103_dBspl = 17,
    _102_dBspl = 18,
    _101_dBspl = 19,
    _100_dBspl = 20,
    _99_dBspl = 21,
    _98_dBspl = 22,
    _97_dBspl = 23,
    _96_dBspl = 24,
    _95_dBspl = 25,
    _94_dBspl = 26,
    _93_dBspl = 27,
    _92_dBspl = 28,
    _91_dBspl = 29,
    _90_dBspl = 30,
    _89_dBspl = 31,
    _88_dBspl = 32,
    _87_dBspl = 33,
    _86_dBspl = 34,
    _85_dBspl = 35,
    _84_dBspl = 36,
    _83_dBspl = 37,
    _82_dBspl = 38,
    _81_dBspl = 39,
    _80_dBspl = 40,
    _79_dBspl = 41,
    _78_dBspl = 42,
    _77_dBspl = 43,
    _76_dBspl = 44,
    _75_dBspl = 45,
    _74_dBspl = 46,
    _73_dBspl = 47,
    _72_dBspl = 48,
    _71_dBspl = 49,
    _70_dBspl = 50,
    _69_dBspl = 51,
    _68_dBspl = 52,
    _67_dBspl = 53,
    _66_dBspl = 54,
    _65_dBspl = 55,
    _64_dBspl = 56,
    _63_dBspl = 57,
    _62_dBspl = 58,
    _61_dBspl = 59,
    _60_dBspl = 60,
    _59_dBspl = 61,
    _58_dBspl = 62,
    _57_dBspl = 63,
    _56_dBspl = 64,
    _55_dBspl = 65,
    _54_dBspl = 66,
    _53_dBspl = 67,
    _52_dBspl = 68,
    _51_dBspl = 69,
    _50_dBspl = 70,
    _49_dBspl = 71,
    _48_dBspl = 72,
    _47_dBspl = 73,
    _46_dBspl = 74,
    _45_dBspl = 75,
    _44_dBspl = 76,
    _43_dBspl = 77,
    _42_dBspl = 78,
    _41_dBspl = 79,
    _40_dBspl = 80,
    _39_dBspl = 81,
    _38_dBspl = 82,
    _37_dBspl = 83,
    _36_dBspl = 84,
    _35_dBspl = 85,
    _34_dBspl = 86,
    _33_dBspl = 87,
    _32_dBspl = 88,
    _31_dBspl = 89,
    _30_dBspl = 90,
    _29_dBspl = 91,
    _28_dBspl = 92,
    _27_dBspl = 93,
    _26_dBspl = 94,
    _25_dBspl = 95,
    _24_dBspl = 96,
    _23_dBspl = 97,
    _22_dBspl = 98,
    _21_dBspl = 99,
    _20_dBspl = 100,
    _19_dBspl = 101,
    _18_dBspl = 102,
    _17_dBspl = 103,
    _16_dBspl = 104,
    _15_dBspl = 105,
    _14_dBspl = 106,
    _13_dBspl = 107,
    _12_dBspl = 108,
    _11_dBspl = 109,
    _10_dBspl = 110,
    _9_dBspl = 111,
    _8_dBspl = 112,
    _7_dBspl = 113,
    _6_dBspl = 114,
    _5_dBspl = 115,
    _4_dBspl = 116,
    _3_dBspl = 117,
    _2_dBspl = 118,
    _1_dBspl = 119,
    _0_dBspl = 120,
    M1_dBspl = 121,
    M2_dBspl = 122,
    M3_dBspl = 123,
    M4_dBspl = 124,
    M5_dBspl = 125,
    M6_dBspl = 126,
    M7_dBspl = 127
};

/$Second kneepoint$/
hi ordered enum d8FAgcCk2 [8825,8826,8827,8828,8829,8830,8831,8832,8833,8834,8835,8836,8837,8838,8839,8840,8841,8842,8843,8844]
{
    _120_dBspl = 0,
    _119_dBspl = 1,
    _118_dBspl = 2,
    _117_dBspl = 3,
    _116_dBspl = 4,
    _115_dBspl = 5,
    _114_dBspl = 6,
    _113_dBspl = 7,
    _112_dBspl = 8,
    _111_dBspl = 9,
    _110_dBspl = 10,
    _109_dBspl = 11,
    _108_dBspl = 12,
    _107_dBspl = 13,
    _106_dBspl = 14,
    _105_dBspl = 15,
    _104_dBspl = 16,
    _103_dBspl = 17,
    _102_dBspl = 18,
    _101_dBspl = 19,
    _100_dBspl = 20,
    _99_dBspl = 21,
    _98_dBspl = 22,
    _97_dBspl = 23,
    _96_dBspl = 24,
    _95_dBspl = 25,
    _94_dBspl = 26,
    _93_dBspl = 27,
    _92_dBspl = 28,
    _91_dBspl = 29,
    _90_dBspl = 30,
    _89_dBspl = 31,
    _88_dBspl = 32,
    _87_dBspl = 33,
    _86_dBspl = 34,
    _85_dBspl = 35,
    _84_dBspl = 36,
    _83_dBspl = 37,
    _82_dBspl = 38,
    _81_dBspl = 39,
    _80_dBspl = 40,
    _79_dBspl = 41,
    _78_dBspl = 42,
    _77_dBspl = 43,
    _76_dBspl = 44,
    _75_dBspl = 45,
    _74_dBspl = 46,
    _73_dBspl = 47,
    _72_dBspl = 48,
    _71_dBspl = 49,
    _70_dBspl = 50,
    _69_dBspl = 51,
    _68_dBspl = 52,
    _67_dBspl = 53,
    _66_dBspl = 54,
    _65_dBspl = 55,
    _64_dBspl = 56,
    _63_dBspl = 57,
    _62_dBspl = 58,
    _61_dBspl = 59,
    _60_dBspl = 60,
    _59_dBspl = 61,
    _58_dBspl = 62,
    _57_dBspl = 63,
    _56_dBspl = 64,
    _55_dBspl = 65,
    _54_dBspl = 66,
    _53_dBspl = 67,
    _52_dBspl = 68,
    _51_dBspl = 69,
    _50_dBspl = 70,
    _49_dBspl = 71,
    _48_dBspl = 72,
    _47_dBspl = 73,
    _46_dBspl = 74,
    _45_dBspl = 75,
    _44_dBspl = 76,
    _43_dBspl = 77,
    _42_dBspl = 78,
    _41_dBspl = 79,
    _40_dBspl = 80,
    _39_dBspl = 81,
    _38_dBspl = 82,
    _37_dBspl = 83,
    _36_dBspl = 84,
    _35_dBspl = 85,
    _34_dBspl = 86,
    _33_dBspl = 87,
    _32_dBspl = 88,
    _31_dBspl = 89,
    _30_dBspl = 90,
    _29_dBspl = 91,
    _28_dBspl = 92,
    _27_dBspl = 93,
    _26_dBspl = 94,
    _25_dBspl = 95,
    _24_dBspl = 96,
    _23_dBspl = 97,
    _22_dBspl = 98,
    _21_dBspl = 99,
    _20_dBspl = 100,
    _19_dBspl = 101,
    _18_dBspl = 102,
    _17_dBspl = 103,
    _16_dBspl = 104,
    _15_dBspl = 105,
    _14_dBspl = 106,
    _13_dBspl = 107,
    _12_dBspl = 108,
    _11_dBspl = 109,
    _10_dBspl = 110,
    _9_dBspl = 111,
    _8_dBspl = 112,
    _7_dBspl = 113,
    _6_dBspl = 114,
    _5_dBspl = 115,
    _4_dBspl = 116,
    _3_dBspl = 117,
    _2_dBspl = 118,
    _1_dBspl = 119,
    _0_dBspl = 120,
    M1_dBspl = 121,
    M2_dBspl = 122,
    M3_dBspl = 123,
    M4_dBspl = 124,
    M5_dBspl = 125,
    M6_dBspl = 126,
    M7_dBspl = 127
};

/$Inverse slope of compression characteristics for levels above CK1 corresponding to the first compression ratio.$/
hi double d8FAgcInvcr1 [8845,8846,8847,8848,8849,8850,8851,8852,8853,8854,8855,8856,8857,8858,8859,8860,8861,8862,8863,8864]
{10,000.0000,160,000.0000};

/$Inverse slope of compression characteristics for levels above CK2 corresponding to the second compression ratio.$/
hi double d8FAgcInvcr2 [8865,8866,8867,8868,8869,8870,8871,8872,8873,8874,8875,8876,8877,8878,8879,8880,8881,8882,8883,8884]
{10,000.0000,160,000.0000};

/$Inverse slope of compression characteristics for levels above CK3 corresponding to the thirdd compression ratio.$/
const hi double d8FAgcInvcr3 6540
{-1.99609375,0.99609375};

/$Depending on the actual class the corresponding offsets will be applied$/
hi enum d8FCbfsEn 6168
{
    off = 0,
    on = 1
};

/$classifier switching for FINE$/
hi int d8FClaDecFineOff 6222
{0,63};

/$classifier switching for VAD$/
hi int d8FClaDecFnyOff 6223
{0,63};

/$classifier switching for MIC$/
hi int d8FClaDecMicDirOff 6224
{0,63};

/$classifier switching for SEL$/
hi int d8FClaDecSelOff 6225
{0,63};

/$classifier switching for SPASS$/
hi int d8FClaDecSpassOff 6226
{0,63};

/$classifier switching for SWINE$/
hi int d8FClaDecSwineOff 6227
{0,63};

/$classifier switching for VAD$/
hi int d8FClaDecVadOff 6228
{0,63};

/$Enable datalogging. The datalogging module collects the number of HP/VC/SC-changes, the time and broadband-level in each class and hearing-program, the time spent in audio-streaming mode and the overall logging time$/
hi enum d8FDlgEn 6256
{
    disabled = 0,
    enabled = 1
};

/$Controls startup behavior of the FBC; e.g. GR DMA blocking and FS-SS effect$/
hi enum d8FFbcStartupCtrl 8952
{
    off = 0,
    set1 = 1,
    set2 = 2,
    set3 = 3,
    set4 = 4,
    set5 = 5,
    set6 = 6,
    set7 = 7
};

/$Positive hysteresis for threshold to fallback to non-harmonic mode$/
hi double d8FFcoHarmonicLowHyst 8915
{-7,653.0,7,653.0};

/$Threshold level for fallback to non-harmonic mode$/
hi double d8FFcoHarmonicLowThr 8916
{-7,653.0,7,653.0};

/$Modulation mode of SPC (Spectral Peak Compression)$/
hi enum d8FFcoSpcMode 8917
{
    SPCMC = 0,
    SPCMMO = 1,
    SPCMMI = 2,
    SPCMH = 3,
    SPCMHdC = 4,
    SPCMHdM0 = 5,
    SPCMHdMI = 6,
    invalid = 7
};

/$adjustment of melody indicator pitch$/
hi enum d8FIndMiTranspose 6574
{
    normal = 0,
    _1_octave_down = 1,
    _2_thirds_down = 2,
    _1_third_down = 3,
    _1_third_up = 4,
    _2_thirds_up = 5,
    _1_octave_up = 6,
    _4_thirds_up = 7
};

/$LED sequence selection for the event trigger$/
hi enum d8FIndTrgLed [6575,6576,6577,6578,6579,6580,6581,6582,6583,6584,6585,6586,6587,6588,6589,6590,6591,6592,6593,6594,6595,6596,6597,6598,6599,6600,6601,6602,6603,6604]
{
    none = 0,
    Sequence_1 = 1,
    Sequence_2 = 2,
    Sequence_3 = 3,
    Sequence_4 = 4,
    Sequence_5 = 5,
    Sequence_6 = 6,
    Sequence_7 = 7
};

/$melody selection for every trigger$/
hi int d8FIndTrgMi [6605,6606,6607,6608,6609,6610,6611,6612,6613,6614,6615,6616,6617,6618,6619,6620,6621,6622,6623,6624,6625,6626,6627,6628,6629,6630,6631,6632,6633,6634]
{0,127};

/$voice selection for every trigger$/
hi int d8FIndTrgVi [6635,6636,6637,6638,6639,6640,6641,6642,6643,6644,6645,6646,6647,6648,6649,6650,6651,6652,6653,6654,6655,6656,6657,6658,6659,6660,6661,6662,6663,6664]
{0,15};

/$Enable the IntelliZoom and IntelliLook feature.$/
hi enum d9FIzmEn 9828
{
    disabled = 0,
    enabled = 1
};

/$Shift reference threshold for noise floor calcualtion from base 18 dBSPL+frequency compensation. $/
hi enum d8FMicNoisefloorFact 8417
{
    _0_dB = 0,
    _3_dB = 1,
    _6_dB = 2,
    _9_dB = 3,
    _12_dB = 4,
    M9_dB = 5,
    M6_dB = 6,
    M3_dB = 7
};

/$To allow the adaption of the broadband meta settings for VAD and FANCY in for bands (france feature), two additional 4-band parameters are provided to adjust only two of the 8 broadband settings.$/
hi enum d8FNruMultiAdjEn 8944
{
    off = 0,
    on = 1
};

/$The system supports regular and rechargeable batteries. Because of their different behaviour, the PWM has to be informed of the used battery type in the device.$/
hi enum d8FPwmBatType 8001
{
    regular = 0,
    rechargeable = 1
};

/$The behaviour of a weakening battery is also influenced by the load the battery has to drive which is in a HI mainly defined by the connected receiver. With this parameter it can be destinguished between devices with a normal battery load and high battery load like for example super power devices. According to this parameter the predefined thresholds of the PWM reaction are selected.$/
hi enum d8FPwmHiLoad 8011
{
    normal = 0,
    high_load = 1
};

/$Reminder Timer$/
hi enum d8FRtmEn 8022
{
    disable = 0,
    enable = 1
};

/$Reminder Timer Indication$/
hi enum d8FRtmIndicateAlways 8023
{
    limited_indication = 0,
    unlimited_indication = 1
};

/$Trial Timer Microphone Mute$/
hi enum d8FRtmMicMute 8024
{
    no_mute = 0,
    mic_paths_muted = 1
};

/$reminder timer start indication$/
hi int d8FRtmStart 8025
{0,35064};

/$reminder timer stop indication$/
hi int d8FRtmStop 8026
{0,7665};

/$selection of signal level relation between mic_F and dai$/
hi enum d8FSmxDaiMixGain 8082
{
    M_P_S = 0,
    M_P_SP3dB = 1,
    M_P_SP6dB = 2,
    M_P_SP9dB = 3,
    M_P_SP12dB = 4,
    M_P_SP15dB = 5,
    M_P_SP18dB = 6,
    S = 7
};

/$HP dependent mut of input path$/
hi enum d8FSmxInputMute [8954,8955,8956,8957,8958,8959,8960,8961,8962]
{
    input_not_muted = 0,
    input_muted = 1
};

/$level dependent input mute enable$/
hi enum d8FSmxLevelEn [8093,8094,8095,8096,8097,8098,8099,8100,8101]
{
    off = 0,
    on = 1
};

/$static application gain for melody indicator$/
hi int d8FSmxMelGain 8118
{0,-127};

/$gain application for melody indicator relative to the actual input level$/
hi enum d8FSmxMelLevDep 8119
{
    off = 0,
    M3_dB = 1,
    _0_dB = 2,
    _3_dB = 3,
    _6_dB = 4,
    _9_dB = 5,
    _12_dB = 6,
    _15_dB = 7
};

/$Enable of automatic audio mix mode control.$/
hi enum d8FSmxMixAutoEn [8462,8463]
{
    off = 0,
    on = 1
};

/$Threshold of automatic mix mode control.$/
hi enum d8FSmxMixAutoLev [8464,8465]
{
    _55_dB_SPL = 0,
    _75_dB_SPL = 1
};

/$selection of signal level relation between mic_F and tcoil$/
hi enum d8FSmxTcoilMixGain 8125
{
    M_P_S = 0,
    M_P_SP3dB = 1,
    M_P_SP6dB = 2,
    M_P_SP9dB = 3,
    M_P_SP12dB = 4,
    M_P_SP15dB = 5,
    M_P_SP18dB = 6,
    S = 7
};

/$static application gain for voice indicator$/
hi int d8FSmxVocGain 8127
{0,-127};

/$gain application for voice indicator relative to the actual input level$/
hi enum d8FSmxVocLevDep 8128
{
    off = 0,
    M3_dB = 1,
    _0_dB = 2,
    _3_dB = 3,
    _6_dB = 4,
    _9_dB = 5,
    _12_dB = 6,
    _15_dB = 7
};

/$Enable periodic / automatic learning steps in intervalls defined by f_thi_auto_time.$/
const hi enum d8FThiAutoEn 8133
{
    off = 0,
    periodic = 1
};

/$Time period for periodical training$/
hi ordered enum d8FThiAutoTime 8134
{
    _1_s = 0,
    _1_min = 1,
    _3_min = 2,
    _5_min = 3,
    _10_min = 4,
    _20_min = 5,
    _40_min = 6,
    _80_min = 7
};

/$Training is triggered after user control changes$/
const hi enum d8FThiUserEn 8245
{
    off = 0,
    UC = 1
};

/$Application weight of the learned offsets, determines influence of each single training step.$/
hi ordered enum d8FThiWeight 8246
{
    _1 = 0,
    _1_d_2 = 1,
    _1_d_4 = 2,
    _1_d_8_P_1_d_16 = 3,
    _1_d_8 = 4,
    _1_d_16_P_1_d_32 = 5,
    _1_d_16 = 6,
    _1_d_32_P_1_d_64 = 7,
    _1_d_32 = 8,
    _1_d_64_P_1_d_128 = 9,
    _1_d_64 = 10,
    _1_d_128 = 11,
    _1_d_256 = 12,
    _1_d_512 = 13,
    _1_d_1024 = 14,
    _1_d_2048 = 15
};

/$absolute temporary HP for audio shoe detection$/
hi enum d8FUctAbsHpAdet 8249
{
    HP1 = 0,
    HP2 = 1,
    HP3 = 2,
    HP4 = 3,
    HP5 = 4,
    HP6 = 5,
    not_valid = 6,
    no_HP_change = 7
};

/$absolute temporary HP for telecoil detection$/
hi enum d8FUctAbsHpTdet 8250
{
    HP1 = 0,
    HP2 = 1,
    HP3 = 2,
    HP4 = 3,
    HP5 = 4,
    HP6 = 5,
    not_valid = 6,
    no_HP_change = 7
};

/$first absolute temporary HP for inline command$/
hi enum d8FUctAbsHpTmp1 8251
{
    HP1 = 0,
    HP2 = 1,
    HP3 = 2,
    HP4 = 3,
    HP5 = 4,
    HP6 = 5,
    not_valid = 6,
    no_HP_change = 7
};

/$second absolute temporary HP for inline command$/
hi enum d8FUctAbsHpTmp2 8252
{
    HP1 = 0,
    HP2 = 1,
    HP3 = 2,
    HP4 = 3,
    HP5 = 4,
    HP6 = 5,
    not_valid = 6,
    no_HP_change = 7
};

/$input mix mode sequence for automatic audio shoe HP$/
hi enum d8FUctAdetSeq 8254
{
    no_mix_modes = 0,
    S_M_X_M = 1,
    S_M_X_M_M_M = 2,
    S_M_X_M_M_M_X_M = 3
};

/$direct HP change by repeated PB actuation$/
const hi enum d8FUctDirectHp 8268
{
    toggleHP = 0,
    directHP = 1
};

/$generation of indication trigger for the different HP change causes$/
hi enum d8FUctHpIndEn [8271,8273,8275]
{
    no_indication_trigger = 0,
    indication_trigger = 1
};

/$Power on delay time$/
hi enum d8FUctPonDelay 8280
{
    off = 0,
    _6_s = 1,
    _12_s = 2,
    _18_s = 3
};

/$allow absolute change to HP by RCU$/
hi enum d8FUctRcuAbsHpEn [8283,8284,8285,8286,8287,8288]
{
    off = 0,
    enable = 1
};

/$input mix mode sequence for automatic telecoil HP$/
hi enum d8FUctTdetSeq 8307
{
    no_mix_modes = 0,
    S_M_X_M = 1,
    S_M_X_M_M_M = 2,
    S_M_X_M_M_M_X_M = 3
};

/$If the HP selection mode is enabled by f_uct_toghp_select_en, these bits define if a HP is valid for a HP change. HP1 is always valid in that case.$/
hi enum d8FUctToghpHpEn [8476,8477,8478,8479,8480]
{
    HP_not_valid = 0,
    HP_valid = 1
};

/$activation modes of user control lock$/
const hi enum d8FUctUclMode 8322
{
    no_UC_lock = 0,
    single_device = 1,
    two_devices = 2,
    not_valid = 3
};

/$generation of indication trigger for binaural user control change (all except HP)$/
hi enum d9FUctCtrlIndEn [9451,9452,9453]
{
    no_indication_trigger = 0,
    indication_trigger = 1
};

/$Enable twin-mic wind noise canceller (WNC)$/
hi enum d9FWncEn 9220
{
    off = 0,
    on = 1
};

/$start value for the meta parameter for Fine to set the initial strength of the respective algorithm$/
hi int d8MFinStart 8354
{0,15};

/$To allow a band dependent setting of the FANCY (france feature), four additional parameters address only two of the 8 broadband settings of the meta table.$/
hi int d8MFnyBand [8933,8934,8935,8936]
{0,15};

/$start value for the meta parameter for Fancy to set the initial strength of the respective algorithm$/
hi int d8MFnyStart 8355
{0,15};

/$start value for the meta parameter for Mic to set the initial strength of the respective algorithm$/
const hi ordered enum d9MMicStart 8356
{
    Step01 = 0,
    Step02 = 1,
    Step03 = 2,
    Step04 = 3,
    Step05 = 4,
    Step06 = 5,
    Step07 = 6,
    Step08 = 7,
    Step09 = 8,
    Step10 = 9,
    Step11 = 10,
    Step12 = 11,
    Step13 = 12,
    Step14 = 13,
    Step15 = 14,
    Step16 = 15
};

/$start value for the meta parameter for Spass to set the initial strength of the respective algorithm$/
hi int d8MSpaStart 8357
{0,15};

/$To allow a band dependent setting of the VAD (france feature), four additional parameters address only two of the 8 broadband settings of the meta table.$/
hi int d8MVadBand [8937,8938,8939,8940]
{0,15};

/$start value for the meta parameter for Vad to set the initial strength of the respective algorithm$/
hi int d8MVadStart 8358
{0,15};

/$start value for the meta parameter for Wnc to set the initial strength of the respective algorithm$/
hi int d8MWncStart 8359
{0,15};

/$For the interaction of the aMNR and the AuxINR (microphone vs. Aux-input) with the noise reduction algorithms (VAD, SPASS, FANCY, BISSS) it is required to inform the NR algorithms about the dominant path (i.e. either mic of aux). This can be$/
hi enum d9FMicAmnrNruCtrlSelect 9823
{
    aMNR_controls_NRU = 0,
    AuxINR_controls_NRU = 1
};

/$bitmask defining for each class (SiQ, SiN, N, M, C, Q) whether narrow focus should be available (0) in that class or not (1)$/
hi int d9FClaDecNfcOff 8992
{0,63};

/$SOEFF of VAD$/
hi int d9FClaDecVadSoeff [8979,8980,8981,8982,8983,8984]
{0,7};

/$SOEFF of FNY$/
hi int d9FClaDecFnySoeff [8967,8968,8969,8970,8971,8972]
{0,7};

/$SOEFF of SPASS$/
hi int d9FClaDecSpassSoeff [8973,8974,8975,8976,8977,8978]
{0,7};

/$Binaural WNC: Turn off link in symmetrical wind situation.$/
hi enum d9FMicBwncSymLinkDisEn 9051
{
    off = 0,
    on = 1
};

/$Select input signal for frequency-domain classifier features. Note that time-domain features are computed seperately, use SMX to select signal composition.$/
hi enum d9FClaInSelect 9245
{
    mic_f = 0,
    auxPmic_f = 1,
    aux = 2,
    invalid = 3
};

/$The omni-directional microphone characteristic can be replaced by cardioid directionality. This functionality is controlled by the parameter sel_ replaceomnibycard. If p_sel_ replaceomnibycard is set to true, the anti-cardioid feature is scaled by the factor p_sel_card_anti.$/
const hi enum d9FSelReplaceomnibycard 9255
{
    use_omni = 0,
    use_card = 1
};

/$Enable of FANCY unit. Must also be on for SPASS funccionality. For pure FANCY attenuation, p_mic_spass_mode_select must further be set to "FANCY"$/
hi enum d9FNruFnyEn 9301
{
    off = 0,
    on = 1
};

/$Enable of level dependent noise reduction (LDNR) unit$/
hi enum d9FNruLdnrEn 9300
{
    off = 0,
    on = 1
};

/$Enable switch of MNR unit$/
hi enum d9FNruMnrEn 9305
{
    off = 0,
    on = 1
};

/$Select MNR expansion ratio$/
hi enum d9FNruMnrRatio 9304
{
    _12 = 0,
    _13 = 1
};

/$Enable of VAD unit$/
hi enum d9FNruVadEn 9302
{
    off = 0,
    on = 1
};

/$Enable firmware control input for FANCY$/
hi enum d9FCtrlFnyEnable 9311
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for SPASS$/
hi enum d9FCtrlSpassEnable 9308
{
    disabled = 0,
    enabled = 1
};

/$Enable firmware control input for VAD$/
hi enum d9FCtrlVadEnable 9314
{
    disabled = 0,
    enabled = 1
};

/$Bypass agc level meter to have an instantaneous level$/
hi enum d9FAgcBypassLevelSmooth 9315
{
    off = 0,
    on_bypass_mode = 1
};

/$Enable the ocean-like broad-band modulation of the tinnitus masker$/
hi enum d9FTinOceanEn 9877
{
    off_d_constant_noise = 0,
    on_d_modulated_noise = 1
};

/$Binaural ocean sound generator, enable synchronization of left and right devices$/
hi enum d9FTinOceanSync 9879
{
    monaural = 0,
    binaural = 1
};

/$CBFS ck offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCkOffsetClass1 [6072,6073,6074,6075,6076,6077,6078,6079]
{
    _0_dB = 0,
    M1_dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    M16_dB = 16,
    M17_dB = 17,
    M18_dB = 18,
    M19_dB = 19,
    M20_dB = 20,
    M21_dB = 21,
    M22_dB = 22,
    M23_dB = 23,
    M24_dB = 24,
    M25_dB = 25,
    M26_dB = 26,
    M27_dB = 27,
    M28_dB = 28,
    M29_dB = 29,
    M30_dB = 30,
    M31_dB = 31,
    _1_dB = 32,
    _2_dB = 33,
    _3_dB = 34,
    _4_dB = 35,
    _5_dB = 36,
    _6_dB = 37,
    _7_dB = 38,
    _8_dB = 39,
    _9_dB = 40,
    _10_dB = 41,
    _11_dB = 42,
    _12_dB = 43,
    _13_dB = 44,
    _14_dB = 45,
    _15_dB = 46,
    _16_dB = 47,
    _17_dB = 48,
    _18_dB = 49,
    _19_dB = 50,
    _20_dB = 51,
    _21_dB = 52,
    _22_dB = 53,
    _23_dB = 54,
    _24_dB = 55,
    _25_dB = 56,
    _26_dB = 57,
    _27_dB = 58,
    _28_dB = 59,
    _29_dB = 60,
    _30_dB = 61,
    _31_dB = 62,
    _32_dB = 63
};

/$CBFS ck offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCkOffsetClass2 [6080,6081,6082,6083,6084,6085,6086,6087]
{
    _0_dB = 0,
    M1_dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    M16_dB = 16,
    M17_dB = 17,
    M18_dB = 18,
    M19_dB = 19,
    M20_dB = 20,
    M21_dB = 21,
    M22_dB = 22,
    M23_dB = 23,
    M24_dB = 24,
    M25_dB = 25,
    M26_dB = 26,
    M27_dB = 27,
    M28_dB = 28,
    M29_dB = 29,
    M30_dB = 30,
    M31_dB = 31,
    _1_dB = 32,
    _2_dB = 33,
    _3_dB = 34,
    _4_dB = 35,
    _5_dB = 36,
    _6_dB = 37,
    _7_dB = 38,
    _8_dB = 39,
    _9_dB = 40,
    _10_dB = 41,
    _11_dB = 42,
    _12_dB = 43,
    _13_dB = 44,
    _14_dB = 45,
    _15_dB = 46,
    _16_dB = 47,
    _17_dB = 48,
    _18_dB = 49,
    _19_dB = 50,
    _20_dB = 51,
    _21_dB = 52,
    _22_dB = 53,
    _23_dB = 54,
    _24_dB = 55,
    _25_dB = 56,
    _26_dB = 57,
    _27_dB = 58,
    _28_dB = 59,
    _29_dB = 60,
    _30_dB = 61,
    _31_dB = 62,
    _32_dB = 63
};

/$CBFS ck offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCkOffsetClass3 [6088,6089,6090,6091,6092,6093,6094,6095]
{
    _0_dB = 0,
    M1_dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    M16_dB = 16,
    M17_dB = 17,
    M18_dB = 18,
    M19_dB = 19,
    M20_dB = 20,
    M21_dB = 21,
    M22_dB = 22,
    M23_dB = 23,
    M24_dB = 24,
    M25_dB = 25,
    M26_dB = 26,
    M27_dB = 27,
    M28_dB = 28,
    M29_dB = 29,
    M30_dB = 30,
    M31_dB = 31,
    _1_dB = 32,
    _2_dB = 33,
    _3_dB = 34,
    _4_dB = 35,
    _5_dB = 36,
    _6_dB = 37,
    _7_dB = 38,
    _8_dB = 39,
    _9_dB = 40,
    _10_dB = 41,
    _11_dB = 42,
    _12_dB = 43,
    _13_dB = 44,
    _14_dB = 45,
    _15_dB = 46,
    _16_dB = 47,
    _17_dB = 48,
    _18_dB = 49,
    _19_dB = 50,
    _20_dB = 51,
    _21_dB = 52,
    _22_dB = 53,
    _23_dB = 54,
    _24_dB = 55,
    _25_dB = 56,
    _26_dB = 57,
    _27_dB = 58,
    _28_dB = 59,
    _29_dB = 60,
    _30_dB = 61,
    _31_dB = 62,
    _32_dB = 63
};

/$CBFS ck offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCkOffsetClass4 [6096,6097,6098,6099,6100,6101,6102,6103]
{
    _0_dB = 0,
    M1_dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    M16_dB = 16,
    M17_dB = 17,
    M18_dB = 18,
    M19_dB = 19,
    M20_dB = 20,
    M21_dB = 21,
    M22_dB = 22,
    M23_dB = 23,
    M24_dB = 24,
    M25_dB = 25,
    M26_dB = 26,
    M27_dB = 27,
    M28_dB = 28,
    M29_dB = 29,
    M30_dB = 30,
    M31_dB = 31,
    _1_dB = 32,
    _2_dB = 33,
    _3_dB = 34,
    _4_dB = 35,
    _5_dB = 36,
    _6_dB = 37,
    _7_dB = 38,
    _8_dB = 39,
    _9_dB = 40,
    _10_dB = 41,
    _11_dB = 42,
    _12_dB = 43,
    _13_dB = 44,
    _14_dB = 45,
    _15_dB = 46,
    _16_dB = 47,
    _17_dB = 48,
    _18_dB = 49,
    _19_dB = 50,
    _20_dB = 51,
    _21_dB = 52,
    _22_dB = 53,
    _23_dB = 54,
    _24_dB = 55,
    _25_dB = 56,
    _26_dB = 57,
    _27_dB = 58,
    _28_dB = 59,
    _29_dB = 60,
    _30_dB = 61,
    _31_dB = 62,
    _32_dB = 63
};

/$CBFS ck offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCkOffsetClass5 [6104,6105,6106,6107,6108,6109,6110,6111]
{
    _0_dB = 0,
    M1_dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    M16_dB = 16,
    M17_dB = 17,
    M18_dB = 18,
    M19_dB = 19,
    M20_dB = 20,
    M21_dB = 21,
    M22_dB = 22,
    M23_dB = 23,
    M24_dB = 24,
    M25_dB = 25,
    M26_dB = 26,
    M27_dB = 27,
    M28_dB = 28,
    M29_dB = 29,
    M30_dB = 30,
    M31_dB = 31,
    _1_dB = 32,
    _2_dB = 33,
    _3_dB = 34,
    _4_dB = 35,
    _5_dB = 36,
    _6_dB = 37,
    _7_dB = 38,
    _8_dB = 39,
    _9_dB = 40,
    _10_dB = 41,
    _11_dB = 42,
    _12_dB = 43,
    _13_dB = 44,
    _14_dB = 45,
    _15_dB = 46,
    _16_dB = 47,
    _17_dB = 48,
    _18_dB = 49,
    _19_dB = 50,
    _20_dB = 51,
    _21_dB = 52,
    _22_dB = 53,
    _23_dB = 54,
    _24_dB = 55,
    _25_dB = 56,
    _26_dB = 57,
    _27_dB = 58,
    _28_dB = 59,
    _29_dB = 60,
    _30_dB = 61,
    _31_dB = 62,
    _32_dB = 63
};

/$CBFS ck offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCkOffsetClass6 [6112,6113,6114,6115,6116,6117,6118,6119]
{
    _0_dB = 0,
    M1_dB = 1,
    M2_dB = 2,
    M3_dB = 3,
    M4_dB = 4,
    M5_dB = 5,
    M6_dB = 6,
    M7_dB = 7,
    M8_dB = 8,
    M9_dB = 9,
    M10_dB = 10,
    M11_dB = 11,
    M12_dB = 12,
    M13_dB = 13,
    M14_dB = 14,
    M15_dB = 15,
    M16_dB = 16,
    M17_dB = 17,
    M18_dB = 18,
    M19_dB = 19,
    M20_dB = 20,
    M21_dB = 21,
    M22_dB = 22,
    M23_dB = 23,
    M24_dB = 24,
    M25_dB = 25,
    M26_dB = 26,
    M27_dB = 27,
    M28_dB = 28,
    M29_dB = 29,
    M30_dB = 30,
    M31_dB = 31,
    _1_dB = 32,
    _2_dB = 33,
    _3_dB = 34,
    _4_dB = 35,
    _5_dB = 36,
    _6_dB = 37,
    _7_dB = 38,
    _8_dB = 39,
    _9_dB = 40,
    _10_dB = 41,
    _11_dB = 42,
    _12_dB = 43,
    _13_dB = 44,
    _14_dB = 45,
    _15_dB = 46,
    _16_dB = 47,
    _17_dB = 48,
    _18_dB = 49,
    _19_dB = 50,
    _20_dB = 51,
    _21_dB = 52,
    _22_dB = 53,
    _23_dB = 54,
    _24_dB = 55,
    _25_dB = 56,
    _26_dB = 57,
    _27_dB = 58,
    _28_dB = 59,
    _29_dB = 60,
    _30_dB = 61,
    _31_dB = 62,
    _32_dB = 63
};

/$CBFS cr offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCrOffsetClass1 [6120,6121,6122,6123,6124,6125,6126,6127]
{
    _0d32 = 0,
    M1d32 = 1,
    M2d32 = 2,
    M3d32 = 3,
    M4d32 = 4,
    M5d32 = 5,
    M6d32 = 6,
    M7d32 = 7,
    M8d32 = 8,
    M9d32 = 9,
    M10d32 = 10,
    M11d32 = 11,
    M12d32 = 12,
    M13d32 = 13,
    M14d32 = 14,
    M15d32 = 15,
    M16d32 = 16,
    M17d32 = 17,
    M18d32 = 18,
    M19d32 = 19,
    M20d32 = 20,
    M21d32 = 21,
    M22d32 = 22,
    M23d32 = 23,
    M24d32 = 24,
    M25d32 = 25,
    M26d32 = 26,
    M27d32 = 27,
    M28d32 = 28,
    M29d32 = 29,
    M30d32 = 30,
    M31d32 = 31,
    M32d32 = 32,
    _1d32 = 33,
    _2d32 = 34,
    _3d32 = 35,
    _4d32 = 36,
    _5d32 = 37,
    _6d32 = 38,
    _7d32 = 39,
    _8d32 = 40,
    _9d32 = 41,
    _10d32 = 42,
    _11d32 = 43,
    _12d32 = 44,
    _13d32 = 45,
    _14d32 = 46,
    _15d32 = 47,
    _16d32 = 48,
    _17d32 = 49,
    _18d32 = 50,
    _19d32 = 51,
    _20d32 = 52,
    _21d32 = 53,
    _22d32 = 54,
    _23d32 = 55,
    _24d32 = 56,
    _25d32 = 57,
    _26d32 = 58,
    _27d32 = 59,
    _28d32 = 60,
    _29d32 = 61,
    _30d32 = 62,
    _31d32 = 63
};

/$CBFS cr offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCrOffsetClass2 [6128,6129,6130,6131,6132,6133,6134,6135]
{
    _0d32 = 0,
    M1d32 = 1,
    M2d32 = 2,
    M3d32 = 3,
    M4d32 = 4,
    M5d32 = 5,
    M6d32 = 6,
    M7d32 = 7,
    M8d32 = 8,
    M9d32 = 9,
    M10d32 = 10,
    M11d32 = 11,
    M12d32 = 12,
    M13d32 = 13,
    M14d32 = 14,
    M15d32 = 15,
    M16d32 = 16,
    M17d32 = 17,
    M18d32 = 18,
    M19d32 = 19,
    M20d32 = 20,
    M21d32 = 21,
    M22d32 = 22,
    M23d32 = 23,
    M24d32 = 24,
    M25d32 = 25,
    M26d32 = 26,
    M27d32 = 27,
    M28d32 = 28,
    M29d32 = 29,
    M30d32 = 30,
    M31d32 = 31,
    M32d32 = 32,
    _1d32 = 33,
    _2d32 = 34,
    _3d32 = 35,
    _4d32 = 36,
    _5d32 = 37,
    _6d32 = 38,
    _7d32 = 39,
    _8d32 = 40,
    _9d32 = 41,
    _10d32 = 42,
    _11d32 = 43,
    _12d32 = 44,
    _13d32 = 45,
    _14d32 = 46,
    _15d32 = 47,
    _16d32 = 48,
    _17d32 = 49,
    _18d32 = 50,
    _19d32 = 51,
    _20d32 = 52,
    _21d32 = 53,
    _22d32 = 54,
    _23d32 = 55,
    _24d32 = 56,
    _25d32 = 57,
    _26d32 = 58,
    _27d32 = 59,
    _28d32 = 60,
    _29d32 = 61,
    _30d32 = 62,
    _31d32 = 63
};

/$CBFS cr offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCrOffsetClass3 [6136,6137,6138,6139,6140,6141,6142,6143]
{
    _0d32 = 0,
    M1d32 = 1,
    M2d32 = 2,
    M3d32 = 3,
    M4d32 = 4,
    M5d32 = 5,
    M6d32 = 6,
    M7d32 = 7,
    M8d32 = 8,
    M9d32 = 9,
    M10d32 = 10,
    M11d32 = 11,
    M12d32 = 12,
    M13d32 = 13,
    M14d32 = 14,
    M15d32 = 15,
    M16d32 = 16,
    M17d32 = 17,
    M18d32 = 18,
    M19d32 = 19,
    M20d32 = 20,
    M21d32 = 21,
    M22d32 = 22,
    M23d32 = 23,
    M24d32 = 24,
    M25d32 = 25,
    M26d32 = 26,
    M27d32 = 27,
    M28d32 = 28,
    M29d32 = 29,
    M30d32 = 30,
    M31d32 = 31,
    M32d32 = 32,
    _1d32 = 33,
    _2d32 = 34,
    _3d32 = 35,
    _4d32 = 36,
    _5d32 = 37,
    _6d32 = 38,
    _7d32 = 39,
    _8d32 = 40,
    _9d32 = 41,
    _10d32 = 42,
    _11d32 = 43,
    _12d32 = 44,
    _13d32 = 45,
    _14d32 = 46,
    _15d32 = 47,
    _16d32 = 48,
    _17d32 = 49,
    _18d32 = 50,
    _19d32 = 51,
    _20d32 = 52,
    _21d32 = 53,
    _22d32 = 54,
    _23d32 = 55,
    _24d32 = 56,
    _25d32 = 57,
    _26d32 = 58,
    _27d32 = 59,
    _28d32 = 60,
    _29d32 = 61,
    _30d32 = 62,
    _31d32 = 63
};

/$CBFS cr offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCrOffsetClass4 [6144,6145,6146,6147,6148,6149,6150,6151]
{
    _0d32 = 0,
    M1d32 = 1,
    M2d32 = 2,
    M3d32 = 3,
    M4d32 = 4,
    M5d32 = 5,
    M6d32 = 6,
    M7d32 = 7,
    M8d32 = 8,
    M9d32 = 9,
    M10d32 = 10,
    M11d32 = 11,
    M12d32 = 12,
    M13d32 = 13,
    M14d32 = 14,
    M15d32 = 15,
    M16d32 = 16,
    M17d32 = 17,
    M18d32 = 18,
    M19d32 = 19,
    M20d32 = 20,
    M21d32 = 21,
    M22d32 = 22,
    M23d32 = 23,
    M24d32 = 24,
    M25d32 = 25,
    M26d32 = 26,
    M27d32 = 27,
    M28d32 = 28,
    M29d32 = 29,
    M30d32 = 30,
    M31d32 = 31,
    M32d32 = 32,
    _1d32 = 33,
    _2d32 = 34,
    _3d32 = 35,
    _4d32 = 36,
    _5d32 = 37,
    _6d32 = 38,
    _7d32 = 39,
    _8d32 = 40,
    _9d32 = 41,
    _10d32 = 42,
    _11d32 = 43,
    _12d32 = 44,
    _13d32 = 45,
    _14d32 = 46,
    _15d32 = 47,
    _16d32 = 48,
    _17d32 = 49,
    _18d32 = 50,
    _19d32 = 51,
    _20d32 = 52,
    _21d32 = 53,
    _22d32 = 54,
    _23d32 = 55,
    _24d32 = 56,
    _25d32 = 57,
    _26d32 = 58,
    _27d32 = 59,
    _28d32 = 60,
    _29d32 = 61,
    _30d32 = 62,
    _31d32 = 63
};

/$CBFS cr offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCrOffsetClass5 [6152,6153,6154,6155,6156,6157,6158,6159]
{
    _0d32 = 0,
    M1d32 = 1,
    M2d32 = 2,
    M3d32 = 3,
    M4d32 = 4,
    M5d32 = 5,
    M6d32 = 6,
    M7d32 = 7,
    M8d32 = 8,
    M9d32 = 9,
    M10d32 = 10,
    M11d32 = 11,
    M12d32 = 12,
    M13d32 = 13,
    M14d32 = 14,
    M15d32 = 15,
    M16d32 = 16,
    M17d32 = 17,
    M18d32 = 18,
    M19d32 = 19,
    M20d32 = 20,
    M21d32 = 21,
    M22d32 = 22,
    M23d32 = 23,
    M24d32 = 24,
    M25d32 = 25,
    M26d32 = 26,
    M27d32 = 27,
    M28d32 = 28,
    M29d32 = 29,
    M30d32 = 30,
    M31d32 = 31,
    M32d32 = 32,
    _1d32 = 33,
    _2d32 = 34,
    _3d32 = 35,
    _4d32 = 36,
    _5d32 = 37,
    _6d32 = 38,
    _7d32 = 39,
    _8d32 = 40,
    _9d32 = 41,
    _10d32 = 42,
    _11d32 = 43,
    _12d32 = 44,
    _13d32 = 45,
    _14d32 = 46,
    _15d32 = 47,
    _16d32 = 48,
    _17d32 = 49,
    _18d32 = 50,
    _19d32 = 51,
    _20d32 = 52,
    _21d32 = 53,
    _22d32 = 54,
    _23d32 = 55,
    _24d32 = 56,
    _25d32 = 57,
    _26d32 = 58,
    _27d32 = 59,
    _28d32 = 60,
    _29d32 = 61,
    _30d32 = 62,
    _31d32 = 63
};

/$CBFS cr offset for 6 classes in 8 bands$/
hi ordered enum d8FCbfsCrOffsetClass6 [6160,6161,6162,6163,6164,6165,6166,6167]
{
    _0d32 = 0,
    M1d32 = 1,
    M2d32 = 2,
    M3d32 = 3,
    M4d32 = 4,
    M5d32 = 5,
    M6d32 = 6,
    M7d32 = 7,
    M8d32 = 8,
    M9d32 = 9,
    M10d32 = 10,
    M11d32 = 11,
    M12d32 = 12,
    M13d32 = 13,
    M14d32 = 14,
    M15d32 = 15,
    M16d32 = 16,
    M17d32 = 17,
    M18d32 = 18,
    M19d32 = 19,
    M20d32 = 20,
    M21d32 = 21,
    M22d32 = 22,
    M23d32 = 23,
    M24d32 = 24,
    M25d32 = 25,
    M26d32 = 26,
    M27d32 = 27,
    M28d32 = 28,
    M29d32 = 29,
    M30d32 = 30,
    M31d32 = 31,
    M32d32 = 32,
    _1d32 = 33,
    _2d32 = 34,
    _3d32 = 35,
    _4d32 = 36,
    _5d32 = 37,
    _6d32 = 38,
    _7d32 = 39,
    _8d32 = 40,
    _9d32 = 41,
    _10d32 = 42,
    _11d32 = 43,
    _12d32 = 44,
    _13d32 = 45,
    _14d32 = 46,
    _15d32 = 47,
    _16d32 = 48,
    _17d32 = 49,
    _18d32 = 50,
    _19d32 = 51,
    _20d32 = 52,
    _21d32 = 53,
    _22d32 = 54,
    _23d32 = 55,
    _24d32 = 56,
    _25d32 = 57,
    _26d32 = 58,
    _27d32 = 59,
    _28d32 = 60,
    _29d32 = 61,
    _30d32 = 62,
    _31d32 = 63
};

/$CBFS gain offset for 6 classes in 8 bands, range from -32 to +31dB in 1dB steps. Order is 0 dB, 1dB, 2dB,...31 dB, -32 dB, -31 dB,...-2 dB, -1 dB$/
hi ordered enum d8FCbfsGainOffsetClass1 [6169,6170,6171,6172,6173,6174,6175,6176]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    _16_dB = 16,
    _17_dB = 17,
    _18_dB = 18,
    _19_dB = 19,
    _20_dB = 20,
    _21_dB = 21,
    _22_dB = 22,
    _23_dB = 23,
    _24_dB = 24,
    _25_dB = 25,
    _26_dB = 26,
    _27_dB = 27,
    _28_dB = 28,
    _29_dB = 29,
    _30_dB = 30,
    _31_dB = 31,
    M32_dB = 32,
    M31_dB = 33,
    M30_dB = 34,
    M29_dB = 35,
    M28_dB = 36,
    M27_dB = 37,
    M26_dB = 38,
    M25_dB = 39,
    M24_dB = 40,
    M23_dB = 41,
    M22_dB = 42,
    M21_dB = 43,
    M20_dB = 44,
    M19_dB = 45,
    M18_dB = 46,
    M17_dB = 47,
    M16_dB = 48,
    M15_dB = 49,
    M14_dB = 50,
    M13_dB = 51,
    M12_dB = 52,
    M11_dB = 53,
    M10_dB = 54,
    M9_dB = 55,
    M8_dB = 56,
    M7_dB = 57,
    M6_dB = 58,
    M5_dB = 59,
    M4_dB = 60,
    M3_dB = 61,
    M2_dB = 62,
    M1_dB = 63
};

/$CBFS gain offset for 6 classes in 8 bands, range from -32 to +31dB in 1dB steps. Order is 0 dB, 1dB, 2dB,...31 dB, -32 dB, -31 dB,...-2 dB, -1 dB$/
hi ordered enum d8FCbfsGainOffsetClass2 [6177,6178,6179,6180,6181,6182,6183,6184]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    _16_dB = 16,
    _17_dB = 17,
    _18_dB = 18,
    _19_dB = 19,
    _20_dB = 20,
    _21_dB = 21,
    _22_dB = 22,
    _23_dB = 23,
    _24_dB = 24,
    _25_dB = 25,
    _26_dB = 26,
    _27_dB = 27,
    _28_dB = 28,
    _29_dB = 29,
    _30_dB = 30,
    _31_dB = 31,
    M32_dB = 32,
    M31_dB = 33,
    M30_dB = 34,
    M29_dB = 35,
    M28_dB = 36,
    M27_dB = 37,
    M26_dB = 38,
    M25_dB = 39,
    M24_dB = 40,
    M23_dB = 41,
    M22_dB = 42,
    M21_dB = 43,
    M20_dB = 44,
    M19_dB = 45,
    M18_dB = 46,
    M17_dB = 47,
    M16_dB = 48,
    M15_dB = 49,
    M14_dB = 50,
    M13_dB = 51,
    M12_dB = 52,
    M11_dB = 53,
    M10_dB = 54,
    M9_dB = 55,
    M8_dB = 56,
    M7_dB = 57,
    M6_dB = 58,
    M5_dB = 59,
    M4_dB = 60,
    M3_dB = 61,
    M2_dB = 62,
    M1_dB = 63
};

/$CBFS gain offset for 6 classes in 8 bands, range from -32 to +31dB in 1dB steps. Order is 0 dB, 1dB, 2dB,...31 dB, -32 dB, -31 dB,...-2 dB, -1 dB$/
hi ordered enum d8FCbfsGainOffsetClass3 [6185,6186,6187,6188,6189,6190,6191,6192]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    _16_dB = 16,
    _17_dB = 17,
    _18_dB = 18,
    _19_dB = 19,
    _20_dB = 20,
    _21_dB = 21,
    _22_dB = 22,
    _23_dB = 23,
    _24_dB = 24,
    _25_dB = 25,
    _26_dB = 26,
    _27_dB = 27,
    _28_dB = 28,
    _29_dB = 29,
    _30_dB = 30,
    _31_dB = 31,
    M32_dB = 32,
    M31_dB = 33,
    M30_dB = 34,
    M29_dB = 35,
    M28_dB = 36,
    M27_dB = 37,
    M26_dB = 38,
    M25_dB = 39,
    M24_dB = 40,
    M23_dB = 41,
    M22_dB = 42,
    M21_dB = 43,
    M20_dB = 44,
    M19_dB = 45,
    M18_dB = 46,
    M17_dB = 47,
    M16_dB = 48,
    M15_dB = 49,
    M14_dB = 50,
    M13_dB = 51,
    M12_dB = 52,
    M11_dB = 53,
    M10_dB = 54,
    M9_dB = 55,
    M8_dB = 56,
    M7_dB = 57,
    M6_dB = 58,
    M5_dB = 59,
    M4_dB = 60,
    M3_dB = 61,
    M2_dB = 62,
    M1_dB = 63
};

/$CBFS gain offset for 6 classes in 8 bands, range from -32 to +31dB in 1dB steps. Order is 0 dB, 1dB, 2dB,...31 dB, -32 dB, -31 dB,...-2 dB, -1 dB$/
hi ordered enum d8FCbfsGainOffsetClass4 [6193,6194,6195,6196,6197,6198,6199,6200]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    _16_dB = 16,
    _17_dB = 17,
    _18_dB = 18,
    _19_dB = 19,
    _20_dB = 20,
    _21_dB = 21,
    _22_dB = 22,
    _23_dB = 23,
    _24_dB = 24,
    _25_dB = 25,
    _26_dB = 26,
    _27_dB = 27,
    _28_dB = 28,
    _29_dB = 29,
    _30_dB = 30,
    _31_dB = 31,
    M32_dB = 32,
    M31_dB = 33,
    M30_dB = 34,
    M29_dB = 35,
    M28_dB = 36,
    M27_dB = 37,
    M26_dB = 38,
    M25_dB = 39,
    M24_dB = 40,
    M23_dB = 41,
    M22_dB = 42,
    M21_dB = 43,
    M20_dB = 44,
    M19_dB = 45,
    M18_dB = 46,
    M17_dB = 47,
    M16_dB = 48,
    M15_dB = 49,
    M14_dB = 50,
    M13_dB = 51,
    M12_dB = 52,
    M11_dB = 53,
    M10_dB = 54,
    M9_dB = 55,
    M8_dB = 56,
    M7_dB = 57,
    M6_dB = 58,
    M5_dB = 59,
    M4_dB = 60,
    M3_dB = 61,
    M2_dB = 62,
    M1_dB = 63
};

/$CBFS gain offset for 6 classes in 8 bands, range from -32 to +31dB in 1dB steps. Order is 0 dB, 1dB, 2dB,...31 dB, -32 dB, -31 dB,...-2 dB, -1 dB$/
hi ordered enum d8FCbfsGainOffsetClass5 [6201,6202,6203,6204,6205,6206,6207,6208]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    _16_dB = 16,
    _17_dB = 17,
    _18_dB = 18,
    _19_dB = 19,
    _20_dB = 20,
    _21_dB = 21,
    _22_dB = 22,
    _23_dB = 23,
    _24_dB = 24,
    _25_dB = 25,
    _26_dB = 26,
    _27_dB = 27,
    _28_dB = 28,
    _29_dB = 29,
    _30_dB = 30,
    _31_dB = 31,
    M32_dB = 32,
    M31_dB = 33,
    M30_dB = 34,
    M29_dB = 35,
    M28_dB = 36,
    M27_dB = 37,
    M26_dB = 38,
    M25_dB = 39,
    M24_dB = 40,
    M23_dB = 41,
    M22_dB = 42,
    M21_dB = 43,
    M20_dB = 44,
    M19_dB = 45,
    M18_dB = 46,
    M17_dB = 47,
    M16_dB = 48,
    M15_dB = 49,
    M14_dB = 50,
    M13_dB = 51,
    M12_dB = 52,
    M11_dB = 53,
    M10_dB = 54,
    M9_dB = 55,
    M8_dB = 56,
    M7_dB = 57,
    M6_dB = 58,
    M5_dB = 59,
    M4_dB = 60,
    M3_dB = 61,
    M2_dB = 62,
    M1_dB = 63
};

/$CBFS gain offset for 6 classes in 8 bands, range from -32 to +31dB in 1dB steps. Order is 0 dB, 1dB, 2dB,...31 dB, -32 dB, -31 dB,...-2 dB, -1 dB$/
hi ordered enum d8FCbfsGainOffsetClass6 [6209,6210,6211,6212,6213,6214,6215,6216]
{
    _0_dB = 0,
    _1_dB = 1,
    _2_dB = 2,
    _3_dB = 3,
    _4_dB = 4,
    _5_dB = 5,
    _6_dB = 6,
    _7_dB = 7,
    _8_dB = 8,
    _9_dB = 9,
    _10_dB = 10,
    _11_dB = 11,
    _12_dB = 12,
    _13_dB = 13,
    _14_dB = 14,
    _15_dB = 15,
    _16_dB = 16,
    _17_dB = 17,
    _18_dB = 18,
    _19_dB = 19,
    _20_dB = 20,
    _21_dB = 21,
    _22_dB = 22,
    _23_dB = 23,
    _24_dB = 24,
    _25_dB = 25,
    _26_dB = 26,
    _27_dB = 27,
    _28_dB = 28,
    _29_dB = 29,
    _30_dB = 30,
    _31_dB = 31,
    M32_dB = 32,
    M31_dB = 33,
    M30_dB = 34,
    M29_dB = 35,
    M28_dB = 36,
    M27_dB = 37,
    M26_dB = 38,
    M25_dB = 39,
    M24_dB = 40,
    M23_dB = 41,
    M22_dB = 42,
    M21_dB = 43,
    M20_dB = 44,
    M19_dB = 45,
    M18_dB = 46,
    M17_dB = 47,
    M16_dB = 48,
    M15_dB = 49,
    M14_dB = 50,
    M13_dB = 51,
    M12_dB = 52,
    M11_dB = 53,
    M10_dB = 54,
    M9_dB = 55,
    M8_dB = 56,
    M7_dB = 57,
    M6_dB = 58,
    M5_dB = 59,
    M4_dB = 60,
    M3_dB = 61,
    M2_dB = 62,
    M1_dB = 63
};
