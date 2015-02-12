


/$FINE activity logging speed for visual benefit and learning noise reduction. 
The following interfaces are influenced: s_lrn_fine_effect, s_fin_active_smooth, s_fin_peakdet_unproc, s_fin_peakdet_proc $/
const hi enum d8PhlFineLogTc 19009
{
    Unsmoothed = 0,
    Fast = 1,
    Med = 2,
    Slow = 3
};

/$FINE on/off parameter, includes reset$/
hi enum d8PhlFineOn 19010
{
    off = 0,
    on = 1
};

/$Microphone mode selection$/
hi enum d9PhlMicMode 19014
{
    omni_single = 0,
    OmniSingleMbat = 1,
    omni_dual = 2,
    spatial_omni = 3,
    DirStaticHP = 4,
    DirStaticFlat = 5,
    DirAdapHP = 6,
    DirAdapFlat = 7,
    AutoAdap = 8,
    AutoFix = 9,
    SelDir = 10,
    AntiCard = 11,
    CalOmniF = 12,
    CalOmniR = 13,
    CalOmniDual = 14,
    CalDir = 15
};

/$SPASS & FANCY configuration and mode selection$/
hi enum d8PhlNruSpassMode 19021
{
    off = 0,
    FANCY = 1,
    FANCY_auto_nf = 2,
    SPASS = 3,
    SPASS_FANCY_min_gain = 4
};

/$SWINE base configuration. Acutal attenuation strength and frequency range is further influenced by m_wnc_set!$/
hi enum d8PhlSwineConfig 19027
{
    very_low = 0,
    low = 1,
    med = 2,
    high = 3
};

/$Switches wireless unit on or off. If wireless functionality is not needed, keep switched off to reduce power consumption $/
const hi enum d9PhlWlsOn 19031
{
    off = 0,
    on = 1
};

/$Configuration of WNC dynamic behavior$/
hi enum d9PhlWncConfigDyn 19032
{
    WNC_min = 0,
    WNC_med = 1,
    WNC_max = 2,
    bWNC_min = 3,
    bWNC_med = 4,
    bWNC_max = 5
};

/$Configuration of static WNC basic strength. Actual attenuation is influenced by m_wnc_set!$/
hi enum d9PhlWncConfigStrength 19033
{
    WNC_min = 0,
    WNC_med = 1,
    WNC_max = 2,
    bWNC_min = 3,
    bWNC_med = 4,
    bWNC_max = 5
};

/$configures the sc range includes a off step $/
hi ordered enum d8PhlUctScRange 19059
{
    _0dB = 0,
    _8dB = 1,
    _16dB = 2,
    _24dB = 3,
    _32dB = 4
};

/$configuration for learning$/
const hi enum d8PhlThiMode 19060
{
    Off = 0,
    TrainGainByVC = 1,
    TrainGainByVcSc = 2,
    TrainGainCrByVC = 3,
    TrainGainCrByVcSc = 4,
    LogGainByVc = 5,
    LogGainByVcSc = 6,
    LogGainCrByVc = 7,
    LogGainCrByVcSc = 8
};

/$combines thevc range with the switch off functionality$/
hi enum d8PhlVcRange 19064
{
    off = 0,
    _8dB = 1,
    _16dB = 2,
    _24dB = 3,
    _32dB = 4
};

/$combines controls f_thi_range_down_b1-4$/
hi ordered enum d8PhlThiRangeDown 19065
{
    _3_dB = 0,
    _4_dB = 1,
    _5_dB = 2,
    _6_dB = 3,
    _8_dB = 4,
    _9_dB = 5,
    _12_dB = 6,
    _16_dB = 7
};

/$combines controls f_thi_range_up_b1-4$/
hi ordered enum d8PhlThiRangeUp 19066
{
    _3_dB = 0,
    _4_dB = 1,
    _5_dB = 2,
    _6_dB = 3,
    _8_dB = 4,
    _9_dB = 5,
    _12_dB = 6,
    _16_dB = 7
};

/$Setting for binaural Wind Noise Canceller$/
hi enum d9PhlMicBwncSetting 19077
{
    WNC_min = 0,
    WNC_med = 1,
    WNC_max = 2,
    bWNC_min = 3,
    bWNC_med = 4,
    bWNC_max = 5
};

/$combines controls for the strength parameterization of the FBC to set the initial strength of the respective algorithm$/
hi enum d9PhlFbcStrength 19080
{
    slow = 0,
    turbo = 1
};

/$configures the tinnitus vc range$/
hi ordered enum d9PhlUctTinvcRange 19083
{
    _0dB = 0,
    _8dB = 1,
    _16dB = 2,
    _24dB = 3,
    _32dB = 4
};

/$Temporal behavior of the noise modulation, defining for instance the depth and the length of the waves and pauses$/
hi enum d9PhlTinOceanSetting 19085
{
    Set_1 = 0,
    Set_2 = 1,
    Set_3 = 2,
    Set_4 = 3,
    Set_5 = 4,
    Set_6 = 5,
    Set_7 = 6,
    Set_8 = 7
};
