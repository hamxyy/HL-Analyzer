


/$Number of situations active on the chip$/
const hi enum d8Sw_NumberOfActivePrograms 13011
{
    One = 0,
    Two = 1,
    Three = 2,
    Four = 3,
    Five = 4,
    Six = 5
};

/$indicates current selected listening situation for a program$/
const hi enum d8SwCtlListSit 13012
{
    undefined = 0,
    universal = 1,
    ListSit1 = 2,
    ListSit2 = 3,
    ListSit3 = 4,
    ListSit4 = 5,
    ListSit5 = 6,
    ListSit6 = 7,
    ListSit7 = 8,
    ListSit8 = 9,
    ListSit9 = 10,
    ListSit10 = 11,
    ListSit11 = 12,
    ListSit12 = 13,
    ListSit13 = 14,
    ListSit14 = 15,
    ListSit15 = 16,
    ListSit16 = 17,
    ListSit17 = 18,
    ListSit18 = 19,
    ListSit19 = 20,
    ListSit20 = 21,
    ListSit21 = 22,
    ListSit22 = 23,
    ListSit23 = 24,
    ListSit24 = 25,
    ListSit25 = 26,
    ListSit26 = 27,
    ListSit27 = 28,
    ListSit28 = 29,
    ListSit29 = 30,
    ListSit30 = 31
};

/$Enables/Disables wireless VC/SC coupling$/
const hi enum d8SwWlsVcScEn 13013
{
    off = 0,
    on = 1
};

/$Enables/Disables wireless classificator coupling$/
const hi enum d8SwWlsClsEn 13014
{
    off = 0,
    on = 1
};

/$Enables/Disables wireless program coupling$/
const hi enum d8SwWlsPrgEn 13015
{
    off = 0,
    on = 1
};

/$Enables/Disables wireless remote control$/
const hi enum d8SwWlsRcEn 13016
{
    off = 0,
    on = 1
};

/$With this Sw-Control the HI can be configured as master, slave or a equal part of the wireless system$/
const hi enum d8SwWlsMasterSlave 13019
{
    Neutral = 0,
    Master = 1,
    Slave = 2
};

/$indicates if a Mini-Tek is part of the system$/
const hi enum d8SwMiniTekConf 13020
{
    No = 0,
    Yes = 1
};

/$indicates if a Tek is part of the system$/
const hi enum d8SwTekConf 13021
{
    No = 0,
    Yes = 1
};

/$indicates if a ePen is part of the system$/
const hi enum d8SwEPenConf 13022
{
    No = 0,
    Yes = 1
};

/$indicates if a ProPocket is part of the system$/
const hi enum d8SwProPocketConf 13023
{
    No = 0,
    Yes = 1
};

/$indicates if current input mode$/
hi enum d8SwMacroInputModeHp 13026
{
    Microphone = 0,
    Tek_d_miniTek = 1,
    Bluetooth_Phone = 2,
    Telecoil = 3,
    Direct_Audio_InputFM = 4,
    Mini_Tek_Voice_Link = 5,
    MiniTek_FM = 6,
    miniTek_Telecoil = 7
};

/$open optimizer on$/
hi enum d8SwMacroOpenOptimizer 13027
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar1Hp 13030
{
    Step1 = 0,
    Step2 = 1,
    Step3 = 2,
    Step4 = 3,
    Step5 = 4,
    Step6 = 5,
    Step7 = 6,
    Step8 = 7
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar2Hp 13031
{
    Step1 = 0,
    Step2 = 1,
    Step3 = 2,
    Step4 = 3,
    Step5 = 4,
    Step6 = 5,
    Step7 = 6,
    Step8 = 7
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar3Hp 13032
{
    Step1 = 0,
    Step2 = 1,
    Step3 = 2,
    Step4 = 3
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar4Hp 13024
{
    Step1 = 0,
    Step2 = 1,
    Step3 = 2,
    Step4 = 3
};

/$$/
hi enum d8SwMacroSoundBrillianceHp 13033
{
    off = 0,
    on = 1
};

/$$/
hi enum d8SwMacroDAIToggleHp 13034
{
    off = 0,
    on = 1
};

/$enable noise floor$/
hi enum d8SwMacroNoiseFloorEnHp 13035
{
    off = 0,
    on = 1
};

/$$/
hi enum d8SwMacroDAISpeechActFmHp 13036
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar9Hp 13037
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar10Hp 13038
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar11 13039
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar12 13040
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar13 13041
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7
};

/$$/
hi enum d8SwMacroHifiFitEn 13042
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn15 13043
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn16 13044
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn17 13045
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar18 13046
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar19 13047
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar20 13048
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar21 13049
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar22 13050
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7
};

/$free usage for macros $/
hi ordered enum d8SwMacroVar23 13051
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7
};

/$free usage for macros $/
hi enum d8SwMacroVarEn24 13052
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn25 13053
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn26 13054
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn27 13055
{
    off = 0,
    on = 1
};

/$free usage for macros $/
hi enum d8SwMacroVarEn28 13056
{
    off = 0,
    on = 1
};

/$current alert tone scheme$/
hi ordered enum d8SwMacroAlertToneScheme 13060
{
    _1 = 0,
    _2 = 1,
    _3 = 2,
    _4 = 3,
    _5 = 4,
    _6 = 5,
    _7 = 6,
    _8 = 7,
    _9 = 8,
    _10 = 9,
    _11 = 10,
    _12 = 11,
    _13 = 12,
    _14 = 13,
    _15 = 14,
    _16 = 15
};

/$Aux mode used for configuration of the input path$/
hi enum d8SwAuxMode 13061
{
    off_MicOnly = 0,
    Tel = 1,
    MicTel = 2,
    A = 3,
    MicA = 4,
    WLA = 5,
    MicWLA = 6
};

/$Mode for channel MPO:
either 'channel' with up to 48 logical channels (number dependent on crossover frequencies)
or 'broadband' with one setting for all MPO channels$/
hi enum d8SwChanOutLimMode 13176
{
    Channel = 0,
    Broadband = 1
};

/$Defines whether the broadband MPO and/or the channel MPO shall be available in software for limitation of maximum power output.$/
hi enum d8SwMpoType 13177
{
    Channel = 0,
    Broadband = 1,
    Both = 2
};

/$Enable or disable open optimizer$/
hi enum d8SwOpenOptEn 13178
{
    Off = 0,
    On = 1
};

/$it is possible to increase decrease all gains. (see also TD8ParAllowNonAllignedMasterGain in SwParSpec) $/
hi enum d8SwMasterGain 13179
{
    Decrease = 0,
    EverythingPossible = 1,
    Increase = 2
};

/$mic mode for Omni; this control is not stored in HI, it is defined as control just to make read-only macro access possible.$/
const hi enum d8SwMicModeForOmni 13180
{
    Omni_Single = 0,
    Omni_Dual = 1,
    Omni_Opt = 2
};

/$destination frequency for frequency compression$/
hi enum d8SwFcoFreqDestination 13187
{
    _2250Hz = 0,
    _2500Hz = 1,
    _2750Hz = 2,
    _3000Hz = 3,
    _3250Hz = 4,
    _3500Hz = 5,
    _3750Hz = 6,
    _4000Hz = 7,
    _4250Hz = 8,
    _4500Hz = 9,
    _4750Hz = 10,
    _5000Hz = 11,
    _5250Hz = 12,
    _5500Hz = 13,
    _5750Hz = 14,
    _6000Hz = 15,
    _6250Hz = 16,
    _6500Hz = 17,
    _6750Hz = 18,
    _7000Hz = 19,
    _7250Hz = 20,
    _7500Hz = 21,
    _7750Hz = 22,
    _8000Hz = 23
};

/$frequency compression ratio: this control is not stored in HI, it will be calculated on demand$/
const hi double d8SwFcoCompressionRatio 13188
{-1.99999988079071,0.99999988079071};

/$is power on delay on or off$/
hi enum d8SwMacroPowerOnDelay 13059
{
    off = 0,
    _6_s = 1,
    _12_s = 2,
    _18_s = 3
};

/$$/
hi enum d8SwMacroAutoSpeechFocusEnHP 13191
{
    off = 0,
    on = 1
};

/$$/
hi enum d8SwMacroSpassEnHP 13192
{
    off = 0,
    on = 1
};

/$$/
hi enum d8SwMacroConnexxFitEn 13193
{
    off = 0,
    on = 1
};

/$$/
hi enum d8SwMacroVarEn9Hp 13194
{
    off = 0,
    on = 1
};

/$$/
hi enum d8SwMacroVarEn10Hp 13195
{
    off = 0,
    on = 1
};

/$macro variable $/
hi enum d8SwMacroMicActThrHp 13203
{
    low = 0,
    med = 1,
    high = 2
};

/$Short-Press-Action for the case when a configurable user control is configured as RockerSwitch$/
const hi enum d8SwConfigurableUserControlShortPressForRockerSwitch 13204
{
    Program_Change = 0,
    Volume_Change = 1,
    Volume_And_Program_Change = 2,
    Tinnitus_Volume_Change = 3,
    SoundBalance_Change = 4
};

/$Short-Press-Action for the case when a configurable user control is configured as Push Button$/
const hi enum d8SwConfigurableUserControlShortPressForPushButton 13205
{
    Program_Up = 0,
    Volume_Up = 1,
    Volume_Down = 2
};

/$Enable On/Standby feature$/
const hi enum d8SwConfigurableUserControlStandby 13207
{
    Off = 0,
    On = 1
};

/$Enable electronic user control lock (E-Lock) feature$/
const hi enum d8SwConfigurableUserControlELock 13208
{
    Off = 0,
    On = 1
};

/$Defines the number of active gain channels. Please check together with SwNumberOfActiveAgcChanGainLowBandwidth. All p_agc_gain_chan above will be set to the lowest physical value.$/
hi enum d8SwNumberOfActiveAgcChanGain 13209
{
    _33_Channels = 0,
    _34_Channels = 1,
    _35_Channels = 2,
    _36_Channels = 3,
    _37_Channels = 4,
    _38_Channels = 5,
    _39_Channels = 6,
    _40_Channels = 7,
    _41_Channels = 8,
    _42_Channels = 9,
    _43_Channels = 10,
    _44_Channels = 11,
    _45_Channels = 12,
    _46_Channels = 13,
    _47_Channels = 14,
    _48_Channels = 15
};

/$Enable or disable a configurable Rocker Switch$/
const hi enum d8SwConfigurableUserControlRockerSwitchEn 13244
{
    Off = 0,
    On = 1
};

/$Enable or disable a configurable Push Button$/
const hi enum d8SwConfigurableUserControlPushButtonEn 13245
{
    Off = 0,
    On = 1
};

/$Enable or disable a non-configurable Push Button. Use case: HIs with both rocker switch (configurable) and push button (non-configurable)$/
const hi enum d8SwNonConfigurablePushButtonEn 13246
{
    Off = 0,
    On = 1
};

/$Automatic acclimatization gain offsets (not stored in HI)$/
hi ordered enum d8SwAacGainOffset [13247,13248,13249,13250,13251,13252,13253,13254,13255,13256,13257,13258,13259,13260,13261,13262,13263,13264,13265,13266]
{
    M16_dB = 0,
    M15_dB = 1,
    M14_dB = 2,
    M13_dB = 3,
    M12_dB = 4,
    M11_dB = 5,
    M10_dB = 6,
    M9_dB = 7,
    M8_dB = 8,
    M7_dB = 9,
    M6_dB = 10,
    M5_dB = 11,
    M4_dB = 12,
    M3_dB = 13,
    M2_dB = 14,
    M1_dB = 15,
    _0_dB = 16,
    _1_dB = 17,
    _2_dB = 18,
    _3_dB = 19,
    _4_dB = 20,
    _5_dB = 21,
    _6_dB = 22,
    _7_dB = 23,
    _8_dB = 24,
    _9_dB = 25,
    _10_dB = 26,
    _11_dB = 27,
    _12_dB = 28,
    _13_dB = 29,
    _14_dB = 30,
    _15_dB = 31
};

/$Individual Automatic Acclimatization target selected or not$/
hi enum d8SwAacIndividualTargetActive 13267
{
    No = 0,
    Yes = 1
};

/$For CBFS BrandX: Control is to be set by macros to notify the CBFS combined control that the user has selected the CBFS "individual preset".$/
hi enum d8SwCbfsIndividualPresetActive 13270
{
    No = 0,
    Yes = 1
};

/$Store if a First Fit has been performed yet. It will be reset to 'No' by applying a delivery setting.$/
const hi enum d8SwFirstFitPerformed 13271
{
    No = 0,
    Yes = 1
};

/$mic mode for FOG; this control is not stored in HI, it is defined as control just to make read-only macro access possible.$/
const hi enum d8SwMicModeForFog 13273
{
    Omni_Single = 0,
    Omni_Dual = 1,
    Omni_Opt = 2
};

/$indicates if the user shall be allowed to modify the compression settings in UI$/
hi enum d8SwMacroDisableCompressionInUi 13296
{
    No = 0,
    Yes = 1
};

/$indicates if a MicroRCU is part of the system$/
const hi enum d8SwMicroRcuConf 13308
{
    No = 0,
    Yes = 1
};

/$Enable or disable the LED of the hearing instrument.$/
const hi enum d8SwUserControlLedEn 13321
{
    Off = 0,
    On = 1
};

/$Defines the number of active gain channels for HIs with low bandwidth (e.g. Nitro). If set to the first step the control SwNumberOfActiveAgcChanGain is used. All p_agc_gain_chan above will be set to the lowest physical value.$/
hi enum d8SwNumberOfActiveAgcChanGainLowBandwidth 13322
{
    Not_Used = 0,
    _18_Channels = 1,
    _19_Channels = 2,
    _20_Channels = 3,
    _21_Channels = 4,
    _22_Channels = 5,
    _23_Channels = 6,
    _24_Channels = 7,
    _25_Channels = 8,
    _26_Channels = 9,
    _27_Channels = 10,
    _28_Channels = 11,
    _29_Channels = 12,
    _30_Channels = 13,
    _31_Channels = 14,
    _32_Channels = 15
};

/$enable feedback cancellation$/
hi enum d8SwMacroFbcEnHp 13323
{
    off = 0,
    on = 1
};

/$enable wind noise cancellation$/
hi enum d8SwMacroWncEnHp 13324
{
    off = 0,
    on = 1
};

/$Marker for macros if FCO has been enabled during FirstFit (used for measurement settings)$/
hi enum d8SwMacroFcoEn 13325
{
    off = 0,
    on = 1
};

/$Store if an AutoFit has been performed.$/
const hi enum d8SwAutoFitPerformed 13326
{
    No = 0,
    Yes = 1
};

/$State of Noise Reduction View$/
hi enum d8SwMacroNrViewHp 13327
{
    advance = 0,
    basic = 1
};

/$State of Noise Reduction checkbox$/
hi enum d8SwMacroNrEnHp 13328
{
    off = 0,
    on = 1
};

/$State of Noise Reduction Slider position in Basic View (SPASS, SNM and FINE settings).$/
hi int d8SwMacroNrSlHp 13329
{0,7};

/$State of Speech Noise Management checkbox in Advanced Noise Reduction View$/
hi enum d8SwMacroSnmEnHp 13330
{
    off = 0,
    on = 1
};

/$State of Speech Noise Management Radiobutton (0 = broadband, 1 = multichannel).$/
hi int d8SwMacroSnmRbHp 13331
{0,1};

/$State of Speech Noise Management Slider position in Advanced Noise Reduction View.$/
hi int d8SwMacroSnmSlHp 13332
{0,7};

/$State of Sound Smoothing checkbox in Advanced Noise Reduction View.$/
hi enum d8SwMacroSsEnHp 13333
{
    off = 0,
    on = 1
};

/$State of Sound Smoothing Slider position in Advanced Noise Reduction View.$/
hi int d8SwMacroSsSlHp 13334
{0,3};

/$State of SPASS Slider position in Advanced Noise Reduction View.$/
hi int d8SwMacroSpassSlHp 13335
{0,3};

/$State of the Speech-In-Noise checkbox in Speech Noise Management.$/
hi enum d8SwMacroSnmSinEnHp 13336
{
    off = 0,
    on = 1
};

/$Stores the tinnitus noise preset selected within the fitting session.
Hint: The step "Static Custom" shall always be allowed fo backwards compatibility.$/
hi enum d8SwTinnitusNoisePreset 13337
{
    Static_Custom = 0,
    Static_White = 1,
    Static_Pink = 2,
    Static_Speech = 3,
    Static_High_Tone = 4
};

/$Indicate the Recalculate Fit running state to the macros.$/
const hi enum d8SwRecalculateFitRunning 13339
{
    No = 0,
    Yes = 1
};

/$Channel MPO in steps of 3dB for attenuation in comparison to LAmax$/
hi ordered enum d8SwChanOutLim [13127,13128,13129,13130,13131,13132,13133,13134,13135,13136,13137,13138,13139,13140,13141,13142,13143,13144,13145,13146,13147,13148,13149,13150,13151,13152,13153,13154,13155,13156,13157,13158,13159,13160,13161,13162,13163,13164,13165,13166,13167,13168,13169,13170,13171,13172,13173,13174]
{
    _0dB = 0,
    M3dB = 1,
    M6dB = 2,
    M9dB = 3,
    M12dB = 4,
    M15dB = 5,
    M18dB = 6,
    M21dB = 7,
    M24dB = 8,
    M27dB = 9,
    M30dB = 10,
    M33dB = 11,
    M36dB = 12,
    M39dB = 13,
    M42dB = 14,
    M45dB = 15
};

/$Channel MPO in steps of 3dB for attenuation in comparison to LAmax$/
hi ordered enum d8CtlSwChanOutLim_Bb 13175
{
    _0dB = 0,
    M3dB = 1,
    M6dB = 2,
    M9dB = 3,
    M12dB = 4,
    M15dB = 5,
    M18dB = 6,
    M21dB = 7,
    M24dB = 8,
    M27dB = 9,
    M30dB = 10,
    M33dB = 11,
    M36dB = 12,
    M39dB = 13,
    M42dB = 14,
    M45dB = 15
};
