


/$Activate FS when sinusoidal > frequency.
This control combines the low-level controls in fbc and nra block for this feature$/
hi enum d8PhlFbcLevAbsBand 9007
{
    SBF = 0,
    SBF_P_250Hz = 1,
    SBF_P_500Hz = 2,
    SBF_P_750Hz = 3
};

/$FBC Split-band filter cut-off frequency. 
Combines SBF in FBC unit and SBF-dependent logic in channel part but does not include SBF in freqeuncy shift / phase shaker unit (see p_fbc_fs_sbf_pair)$/
hi enum d8PhlFbcSbfFreq 9008
{
    _1150_Hz = 0,
    _1400_Hz = 1,
    _1800_Hz = 2
};

/$FINE activity logging speed for visual benefit and learning noise reduction. 
The following interfaces are influenced: s_lrn_fine_effect, s_fin_active_smooth, s_fin_peakdet_unproc, s_fin_peakdet_proc $/
hi enum d8PhlFineLogTc 9009
{
    Unsmoothed = 0,
    Fast = 1,
    Med = 2,
    Slow = 3
};

/$FINE on/off parameter, includes reset$/
hi enum d8PhlFineOn 9010
{
    off = 0,
    on = 1
};

/$Microphone mode selection$/
hi enum d8PhlMicMode 9014
{
    omni_single = 0,
    omni_opt = 1,
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
hi enum d8PhlNruSpassMode 9021
{
    off = 0,
    FANCY = 1,
    FANCY_auto_nf = 2,
    SPASS = 3,
    SPASS_FANCY_min_gain = 4
};

/$SWINE base configuration. Acutal attenuation strength and frequency range is further influenced by m_wnc_set!$/
hi enum d8PhlSwineConfig 9027
{
    very_low = 0,
    low = 1,
    med = 2,
    high = 3
};

/$Switches wireless unit on or off. If wireless functionality is not needed, keep switched off to reduce power consumption $/
hi enum d8PhlWlsOn 9031
{
    off = 0,
    on = 1,
    e2eOff_WlpOff = 2,
    e2eOff_WlpOn = 3
};

/$configures the sc range includes a off step $/
hi ordered enum d8PhlUctScRange 9059
{
    _0dB = 0,
    _8dB = 1,
    _16dB = 2,
    _24dB = 3,
    _32dB = 4
};

/$configuration for learning$/
const hi enum d8PhlThiMode 9060
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

/$Activate FS when sinusoidal impulse > frequency$/
hi enum d8PhlFbcLevImpBandNra 9063
{
    SBF = 0,
    SBF_P_250Hz = 1,
    SBF_P_500Hz = 2,
    SBF_P_750Hz = 3
};

/$combines thevc range with the switch off functionality$/
hi enum d8PhlVcRange 9064
{
    off = 0,
    _8dB = 1,
    _16dB = 2,
    _24dB = 3,
    _32dB = 4
};

/$combines controls f_thi_range_down_b1-4$/
hi ordered enum d8PhlThiRangeDown 9065
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
hi ordered enum d8PhlThiRangeUp 9066
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
