


/$MicMode$/
hi enum d9SwMicMode 12001
{
    Disabled = 0,
    OmniSingle = 1,
    OmniSingleMbat = 2,
    OmniDual = 3,
    SpatialOmni = 4,
    SingleBinDirOmni = 5,
    DirStaticHP = 6,
    DirStaticFlat = 7,
    DirAdapHP = 8,
    DirAdapFlat = 9,
    AutoAdap = 10,
    AutoFix = 11,
    SelDir = 12,
    AutoSelDir = 13,
    NfcStatic = 14,
    NfcAdap = 15,
    AutoNfcAdap = 16,
    SlkStatic = 17,
    SlkAdap = 18,
    AutoSlkAdap = 19,
    AutoNfcSlkAdap = 20,
    AutoNfcSelDirAdap = 21,
    CalOmniF = 22,
    CalOmniR = 23,
    CalDualOmni = 24,
    CalDir = 25,
    TestDir = 26,
    TestAntiCard = 27,
    TestCrosTDmic = 28,
    TestBinEightDirStatic = 29,
    TestBinEightOmniSingle = 30,
    TestWlaNoiser = 31,
    SingleBinDirStatic = 32,
    SingleBinDirAdap = 33,
    AutoSingleBinDirAdap = 34
};

/$broadband output limiter$/
hi ordered enum d8SwOutLim 12003
{
    PC = 0,
    _0dB = 1,
    M3dB = 2,
    M6dB = 3,
    M9dB = 4,
    M12dB = 5,
    M15dB = 6,
    M18dB = 7,
    M21dB = 8
};

/$control to set the cut off frequency for frequency compression. All other fco controls are also included$/
hi enum d8SwFcoFreqCutoff 12012
{
    _1500Hz = 0,
    _1750Hz = 1,
    _2000Hz = 2,
    _2250Hz = 3,
    _2500Hz = 4,
    _2750Hz = 5,
    _3000Hz = 6,
    _3250Hz = 7,
    _3500Hz = 8,
    _3750Hz = 9,
    _4000Hz = 10,
    _4250Hz = 11,
    _4500Hz = 12,
    _4750Hz = 13,
    _5000Hz = 14,
    _5250Hz = 15,
    _5500Hz = 16,
    _5750Hz = 17,
    _6000Hz = 18
};

/$combined control for the exchangeable or configurable HI user control$/
const hi enum d8SwConfigurableUserControl 12016
{
    RockerSwitch = 0,
    PushButton = 1,
    FlatCover = 2,
    NotAvailable = 3
};

/$combined control for CBFS (classification based frequency shaping)$/
hi enum d8SwCbfs 12017
{
    Automatic = 0,
    Class_1 = 1,
    Class_2 = 2,
    Class_3 = 3,
    Class_4 = 4,
    Class_5 = 5,
    Class_6 = 6
};

/$Duration in which the automatic acclimatization target shall be reached$/
hi ordered enum d8SwAacDuration 12019
{
    _1_week = 0,
    _2_weeks = 1,
    _4_weeks = 2,
    _2_months = 3,
    _6_months = 4
};

/$Enable or disable SoundLearning for each program$/
hi enum d8SwThiEnable 12020
{
    Off = 0,
    On = 1
};
